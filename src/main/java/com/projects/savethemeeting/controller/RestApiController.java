package com.projects.savethemeeting.controller;

import com.projects.savethemeeting.dao.MeetingDao;
import com.projects.savethemeeting.dao.UserDao;
import com.projects.savethemeeting.objectmodel.User;
import com.projects.savethemeeting.objectmodel.UserOnMeeting;
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

    @RequestMapping(value="/meeting",method = RequestMethod.POST,consumes="application/json" )
    public @ResponseBody ResponseEntity<String> createMeeting(@RequestBody Meeting meeting){
        System.out.println(meeting.getMeetingName()); //todo: save meeting to database
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

    @RequestMapping("/test")
    public String createTestData() {
        com.projects.savethemeeting.objectmodel.Meeting meeting = new com.projects.savethemeeting.objectmodel.Meeting();
        meeting.setName("test");
        meeting.setStarted(new Date(System.currentTimeMillis()-20000));
        meeting.setDuration(20L*60L*1000L);
        User janko = new User("Jano","jano@mail.com");
        UserOnMeeting userOnMeeting = new UserOnMeeting();
        userOnMeeting.setFrom(meeting.getStarted());
        userOnMeeting.setTo(new Date(System.currentTimeMillis()));

        janko.getMeetings().add(userOnMeeting);
        userOnMeeting.setUser(janko);

        meeting.getUsers().add(userOnMeeting);
        userOnMeeting.setMeeting(meeting);

        meetingDao.openCurrentSessionwithTransaction();
        meetingDao.getCurrentSession().save(janko);
        meetingDao.getCurrentSession().save(meeting);
        meetingDao.getCurrentSession().save(userOnMeeting);
        meetingDao.closeCurrentSessionwithTransaction();

        return "redirect:/";
    }
}
