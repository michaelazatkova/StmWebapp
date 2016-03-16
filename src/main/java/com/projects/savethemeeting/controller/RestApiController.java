package com.projects.savethemeeting.controller;

import com.projects.savethemeeting.dao.*;
import com.projects.savethemeeting.objectmodel.*;
import com.projects.savethemeeting.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Date;
import java.util.List;

/**
 * Created by Michaela on 22.02.2016.
 */
@RestController
@RequestMapping("/api")
public class RestApiController {

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

    @RequestMapping(value="/meeting",method = RequestMethod.POST,consumes="application/json" )
    public @ResponseBody ResponseEntity<String> createMeeting(@RequestBody MeetingInfo meeting){
        System.out.println(meeting.getMeetingName());
        Meeting newMeeting = new Meeting(meeting);
        Record record = new Record(Constants.PATH_FOR_RECORDS+meeting.getIdentificator()+".amr");
        User user = meeting.getUser();
        List<PointOfInterest> pois = meeting.getPointsOfInterest();
        UserOnMeeting userOnMeeting = new UserOnMeeting(user,newMeeting,new Date(Date.parse(meeting.getConnectedFrom())),new Date(Date.parse(meeting.getConnectedTo())),record,pois);
        newMeeting.getUsers().add(userOnMeeting);
        user.getMeetings().add(userOnMeeting);
        for (PointOfInterest poi : pois) {
            poi.setUserOnMeeting(userOnMeeting);
        }

        if (!userDao.entityExists(user)) {
            userDao.persist(user);
        }
        if (!meetingDao.entityExists(newMeeting)) {
            meetingDao.persist(newMeeting);
        }
        meetingDao.openCurrentSessionwithTransaction();
        recordDao.persist(record);
        for (PointOfInterest poi : pois) {
            pointOfInterestDao.persist(poi);
        }
        userOnMeetingDao.persist(userOnMeeting);
        meetingDao.closeCurrentSessionwithTransaction();

        return new ResponseEntity<String>("{}", HttpStatus.CREATED);
    }

    @RequestMapping(value="/upload", method= RequestMethod.POST)
    public @ResponseBody String handleFileUpload(@RequestParam("file") MultipartFile file){
        if (!file.isEmpty()) {
            String name = Constants.PATH_FOR_RECORDS + file.getOriginalFilename();
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(name)));
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

    @RequestMapping(value="/skuska",method = RequestMethod.GET)
    public void skuska() {
    Meeting lastMeeting = meetingDao.getLastMeeting();
        List<UserOnMeeting> users = userOnMeetingDao.getUsersOnMeeting(lastMeeting);

    }
}
