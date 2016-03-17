package com.projects.savethemeeting.controller;

import com.projects.savethemeeting.dao.MeetingDao;
import com.projects.savethemeeting.dao.UserDao;
import com.projects.savethemeeting.objectmodel.Meeting;
import com.projects.savethemeeting.objectmodel.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Michaela on 15.03.2016.
 */

@Controller
public class MeetingController {
    @Autowired
    private MeetingDao meetingDao;
    @Autowired
    private UserDao userDao;

    @RequestMapping("/")
    public ModelAndView welcome() {
        Meeting lastMeeting = meetingDao.getLastMeeting();
        List<User> participants = userDao.getUsers(lastMeeting);
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("lastMeeting", lastMeeting);
        modelAndView.addObject("participants", participants);

        return modelAndView;
    }

    @RequestMapping("/reports")
    public ModelAndView reports() {
        Meeting lastMeeting = meetingDao.getLastMeeting();

        ModelAndView modelAndView = new ModelAndView("reports");
        modelAndView.addObject("lastMeeting", lastMeeting);

        return modelAndView;
    }
}
