package com.projects.savethemeeting.controller;

import com.projects.savethemeeting.dao.MeetingDao;
import com.projects.savethemeeting.dao.UserDao;
import com.projects.savethemeeting.objectmodel.Meeting;
import com.projects.savethemeeting.objectmodel.User;
import com.projects.savethemeeting.sound.upload.SoundCloudUtils;
import de.voidplus.soundcloud.Comment;
import de.voidplus.soundcloud.SoundCloud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * Created by Michaela on 15.03.2016.
 */

@Controller
public class MeetingController {
    @Autowired
    private MeetingDao meetingDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private SoundCloudUtils soundCloudUtils;

    @RequestMapping("/")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }

    @RequestMapping("/home")
    public ModelAndView welcome() {
        meetingDao.openCurrentSessionwithTransaction();
        Meeting lastMeeting = meetingDao.getLastMeeting();
        List<User> participants = userDao.getUsers(lastMeeting);
        List<Meeting> meetings = meetingDao.getLastMeetings(10);
        meetingDao.closeCurrentSessionwithTransaction();
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("lastMeeting", lastMeeting);
        modelAndView.addObject("participants", participants);
        modelAndView.addObject("lastMeetings",meetings);

        return modelAndView;
    }

    @RequestMapping("/full")
    public ModelAndView fullReport() {
        meetingDao.openCurrentSessionwithTransaction();
        Meeting lastMeeting = meetingDao.getLastMeeting();
        List<User> participants = userDao.getUsers(lastMeeting);
        meetingDao.closeCurrentSessionwithTransaction();
        ModelAndView modelAndView = new ModelAndView("full");
        modelAndView.addObject("lastMeeting", lastMeeting);
        modelAndView.addObject("participants", participants);

        return modelAndView;
    }

    @RequestMapping("/full/{id}")
    public ModelAndView reports(@PathVariable long id) {
        meetingDao.openCurrentSessionwithTransaction();
        Meeting lastMeeting = meetingDao.getMeeting(id);
        List<User> participants = userDao.getUsers(lastMeeting);
        meetingDao.closeCurrentSessionwithTransaction();
        List<Comment> comments = soundCloudUtils.getAllComments(lastMeeting.getIdMeeting());
        ModelAndView modelAndView = new ModelAndView("full");
        modelAndView.addObject("lastMeeting", lastMeeting);
        modelAndView.addObject("participants", participants);


        return modelAndView;
    }

    @RequestMapping("/reports")
    public ModelAndView reports() {
        ModelAndView modelAndView = new ModelAndView("reports");
        meetingDao.openCurrentSessionwithTransaction();
        List<Meeting> meetings = meetingDao.getLastMeetings(-1);
        Collections.sort(meetings, new Meeting.MeetingComparator());
        Map<Meeting, List<User>> resultMap = new TreeMap<Meeting, List<User>>();
        for(Meeting meeting : meetings) {
            List<User> participants = userDao.getUsers(meeting);
            resultMap.put(meeting, participants);
        }

        meetingDao.closeCurrentSessionwithTransaction();
        modelAndView.addObject("resultMap", resultMap);
        return modelAndView;
    }

}
