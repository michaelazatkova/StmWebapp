package com.projects.savethemeeting.controller;

import com.projects.savethemeeting.dao.MeetingDao;
import com.projects.savethemeeting.dao.UserDao;
import com.projects.savethemeeting.objectmodel.Meeting;
import com.projects.savethemeeting.objectmodel.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Michaela on 15.03.2016.
 */

@Controller
public class MeetingController {
    @Autowired
    private MeetingDao meetingDao;
    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @RequestMapping("/")
    public String home() {
        return "redirect:/home";
    }

    @RequestMapping("/home")
    public ModelAndView welcome(HttpServletRequest request) {
        // parse userId
        long userId = Long.parseLong(request.getUserPrincipal().getName());

        // fetch data from db
        meetingDao.openCurrentSessionwithTransaction();
        Meeting lastMeeting = meetingDao.getLastMeeting(userId);
        List<User> participants = new ArrayList<User>();
        List<Meeting> meetings = new ArrayList<Meeting>();
        if(lastMeeting != null) {
            participants = userDao.getUsers(lastMeeting);
            meetings = meetingDao.getLastMeetings(10, userId);
        } else {
            lastMeeting = new Meeting();
        }
        meetingDao.closeCurrentSessionwithTransaction();

        // add them to view
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("lastMeeting", lastMeeting);
        modelAndView.addObject("participants", participants);
        modelAndView.addObject("lastMeetings",meetings);

        return modelAndView;
    }

    @RequestMapping("/full")
    public ModelAndView fullReport(HttpServletRequest request) {
        // parse userId
        long userId = Long.parseLong(request.getUserPrincipal().getName());

        // fetch data from db
        meetingDao.openCurrentSessionwithTransaction();
        Meeting lastMeeting = meetingDao.getLastMeeting(userId);
        List<User> participants = userDao.getUsers(lastMeeting);
        meetingDao.closeCurrentSessionwithTransaction();

        // add them to view
        ModelAndView modelAndView = new ModelAndView("full");
        modelAndView.addObject("lastMeeting", lastMeeting);
        modelAndView.addObject("participants", participants);

        return modelAndView;
    }

    @RequestMapping("/full/{id}")
    public ModelAndView reports(@PathVariable long id) {
        // fetch meetings from db
        meetingDao.openCurrentSessionwithTransaction();
        Meeting lastMeeting = meetingDao.getMeeting(id);
        List<User> participants = userDao.getUsers(lastMeeting);
        meetingDao.closeCurrentSessionwithTransaction();

        // add it to view
        ModelAndView modelAndView = new ModelAndView("full");
        modelAndView.addObject("lastMeeting", lastMeeting);
        modelAndView.addObject("participants", participants);

        return modelAndView;
    }

    @RequestMapping("/reports")
    public ModelAndView reports(HttpServletRequest request) {
        // parse userId
        long userId = Long.parseLong(request.getUserPrincipal().getName());

        // fetch data from db
        meetingDao.openCurrentSessionwithTransaction();
        List<Meeting> meetings = meetingDao.getLastMeetings(-1, userId);
        Map<Meeting, List<User>> resultMap = new HashMap<Meeting, List<User>>();
        for(Meeting meeting : meetings) {
            List<User> participants = userDao.getUsers(meeting);
            resultMap.put(meeting, participants);
        }
        meetingDao.closeCurrentSessionwithTransaction();

        // add them to view
        ModelAndView modelAndView = new ModelAndView("reports");
        modelAndView.addObject("resultMap", resultMap);

        return modelAndView;
    }

}
