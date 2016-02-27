package com.projects.savethemeeting.dao;

import com.projects.savethemeeting.objectmodel.Meeting;
import com.projects.savethemeeting.objectmodel.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Michaela on 24.02.2016.
 */

public class MeetingDao  extends HibernateDaoSupport {
    public void storeData() {
        Session session = getSessionFactory().getCurrentSession();

        Meeting meeting = new Meeting();
        meeting.setIdMeeting(1L);
        meeting.setName("test");
        meeting.setStarted(new Date(System.currentTimeMillis()));
        meeting.setDuration(20L*60L*1000L);
        List<Meeting> meetings = new ArrayList<Meeting>();
        meetings.add(meeting);

        List<User> pinkaciUserovia = new ArrayList<User>();
        for(int i = 0; i < 5; ++i) {
            User user = new User();
            user.setUserId(i+1L);
            user.setName(UUID.randomUUID().toString());
            user.setMeetings(meetings);
            pinkaciUserovia.add(user);
            session.save(user);
        }
        meeting.setUsers(pinkaciUserovia);


        // snad zbehne
//       Transaction transaction = session.beginTransaction();
        session.save(meeting);
//        transaction.commit();


    }
}
