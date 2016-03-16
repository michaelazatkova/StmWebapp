package com.projects.savethemeeting.controller;

import com.projects.savethemeeting.dao.MeetingDao;
import com.projects.savethemeeting.objectmodel.Meeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Michaela on 15.03.2016.
 */

@Controller
public class MeetingController {
    @Autowired
    private static MeetingDao meetingDao;

    public ModelAndView handleRequest(HttpServletRequest arg0,
                                      HttpServletResponse arg1) throws Exception {

        Meeting lastMeeting = meetingDao.getLastMeeting();
        return new ModelAndView("WEB-INF/views/last.jsp","lastMeeting",lastMeeting);
    }
}
