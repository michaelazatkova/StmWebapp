package com.projects.savethemeeting.controller;

import com.projects.savethemeeting.dao.*;
import com.projects.savethemeeting.objectmodel.*;
import com.projects.savethemeeting.sound.MeetingSoundGenerator;
import com.projects.savethemeeting.sound.upload.SoundCloudUtils;
import com.projects.savethemeeting.utils.Constants;
import com.projects.savethemeeting.utils.ProcessWrapper;
import com.projects.savethemeeting.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Michaela on 22.02.2016.
 */
@RestController
@RequestMapping("/api")
public class RestApiController {
    @Autowired
    private MeetingSoundGenerator meetingSoundGenerator;
    @Autowired
    private MeetingDao meetingDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private RecordDao recordDao;
    @Autowired
    private POIDao pointOfInterestDao;
    @Autowired
    private UOMDao userOnMeetingDao;

    /**
     * BACKLOG LOGIC
     */
    private Queue<MeetingInfo> backLog = new LinkedList<MeetingInfo>();
    private BacklogThread backlogThread = new BacklogThread();

    private class BacklogThread extends Thread {
        private boolean running;

        @Override
        public void run() {
            while (running) {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    // skip
                }

                if (!backLog.isEmpty()) {
                    if (meetingSoundGenerator.getState() == MeetingSoundGenerator.State.FREE) {
                        System.out.println("Dequeuing object from queue and process is starting...");
                        handleNewMeeting(backLog.remove());
                    }
                } else {
                    running = false;
                }
            }
        }

        @Override
        public synchronized void start() {
            running = true;
            super.start();
        }
    }

    @RequestMapping("/killall")
    public
    @ResponseBody
    boolean killSoundProcessing() {
        if (ProcessWrapper.currentProcess != null) {
            ProcessWrapper.currentProcess.interrupt();

            return true;
        }

        return false;
    }

    @RequestMapping(value = "/meeting", method = RequestMethod.POST, consumes = "application/json")
    public
    @ResponseBody
    ResponseEntity<String> createMeeting(@RequestBody MeetingInfo meeting) {
        if (meetingSoundGenerator.getState() == MeetingSoundGenerator.State.BUSY && backLog.isEmpty()) {
            System.err.println("We're sorry, but SoundGenerator is BUSY! Object is stored in queue and will be dequeued in next operation.");
            backLog.add(meeting);

            // if thread is running, skip this
            if (!backlogThread.running) {
                backlogThread.start();
            }
        } else {
            handleNewMeeting(meeting);
        }
        return new ResponseEntity<String>("{}", HttpStatus.CREATED);
    }

    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<String> createComment(@RequestParam("mid") long mid,
                                         @RequestParam("uid") long uid,
                                         @RequestParam("text") String text,
                                         @RequestParam(value = "pid", required = false) Long pid) {
        meetingDao.openCurrentSessionwithTransaction();
        if (pid != null) {
            Comment parentComment = meetingDao.getComment(pid);
            meetingDao.createComment(mid, uid, text, parentComment,new Timestamp(new Date().getTime()) );
        } else meetingDao.createComment(mid, uid, text, null, new Timestamp(new Date().getTime()));
        meetingDao.closeCurrentSessionwithTransaction();

        return new ResponseEntity<String>("{}", HttpStatus.CREATED);
    }

    private void handleNewMeeting(MeetingInfo meeting) {
        System.out.println("New meeting -> " + meeting.getMeetingName() + " has arrived :)");
        Meeting newMeeting = new Meeting(meeting);
        User user = meeting.getUser();
        Record record = new Record(Constants.PATH_FOR_RECORDS + meeting.getIdentificator() + File.separator + user.getFbID() + ".amr");
        List<PointOfInterest> pois = meeting.getPointsOfInterest();
        UserOnMeeting userOnMeeting = new UserOnMeeting(user, newMeeting, Util.getTimestampFromString(meeting.getConnectedFrom()), Util.getTimestampFromString(meeting.getConnectedTo()), record, pois);
        newMeeting.getUsers().add(userOnMeeting);
        user.getMeetings().add(userOnMeeting);
        for (PointOfInterest poi : pois) {
            poi.setUserOnMeeting(userOnMeeting);
        }

        recordDao.persist(record);

        if (!userDao.entityExists(user)) {
            userDao.persist(user);
        }
        if (!meetingDao.entityExists(newMeeting)) {
            meetingDao.persist(newMeeting);
            meetingSoundGenerator.createFirstMeetingSound(record, userOnMeeting.getFrom(), newMeeting.getStarted(), meeting.getPointsOfInterest(), user.getName());
        } else {
            meetingDao.update(newMeeting);
            meetingSoundGenerator.regenerateMeetingSound(record, userOnMeeting.getFrom(), newMeeting.getStarted(), meeting.getPointsOfInterest(), user.getName());
        }
        meetingDao.openCurrentSessionwithTransaction();

        for (PointOfInterest poi : pois) {
            pointOfInterestDao.persist(poi);
        }
        userOnMeetingDao.persist(userOnMeeting);
        meetingDao.closeCurrentSessionwithTransaction();
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public
    @ResponseBody
    String handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("user") String userID) {
        if (!file.isEmpty()) {
            String recordName = file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf("."));
            String name = Constants.PATH_FOR_RECORDS + recordName + File.separator + userID + ".amr";
            File record = new File(name);
            record.getParentFile().mkdirs();
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(record));
                stream.write(bytes);
                stream.close();
                System.out.println("You successfully uploaded " + name + "!");

                return "OK";
            } catch (Exception e) {
                System.out.println("You failed to upload " + name + " => " + e.getMessage());
                return "FAILED";
            }
        } else {
            System.out.println("You failed to upload file because the file was empty.");
            return "FAILED";
        }
    }
}
