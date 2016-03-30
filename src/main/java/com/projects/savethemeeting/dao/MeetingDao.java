package com.projects.savethemeeting.dao;

import com.projects.savethemeeting.objectmodel.Meeting;
import com.projects.savethemeeting.objectmodel.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

import java.util.List;

/**
 * Created by Michaela on 24.02.2016.
 */

public class MeetingDao extends BaseDao<Meeting> {

    @Override
    public void persist(Meeting entity) {
        openCurrentSessionwithTransaction();
        super.persist(entity);
        closeCurrentSessionwithTransaction();
    }

    @Override
    public boolean entityExists(Meeting meeting) {
        boolean result = false;
        openCurrentSessionwithTransaction();
        Meeting found = (Meeting)getCurrentSession().get(Meeting.class,meeting.getIdMeeting());
        if (found!=null) {
            result = true;
        }
        closeCurrentSessionwithTransaction();
        return result;
    }

    public Meeting getLastMeeting() {
        Meeting lastMeeting  = (Meeting)getCurrentSession()
                .createCriteria(Meeting.class)
                .addOrder(Order.desc("started"))
                .setMaxResults(1)
                .uniqueResult();
        return lastMeeting;
    }

    public void update(Meeting meeting) {
        openCurrentSessionwithTransaction();
        Meeting old = (Meeting)getCurrentSession().get(Meeting.class,meeting.getIdMeeting());
        if (old.getDuration()<meeting.getDuration()) {
            getCurrentSession().saveOrUpdate(meeting);
        }
        closeCurrentSessionwithTransaction();
    }

    public List<Meeting> getLastMeetings(int number) {
        Criteria criteria = getCurrentSession()
                .createCriteria(Meeting.class)
                .addOrder(Order.desc("started"));
        if(number != -1) {
            criteria.setMaxResults(number);
        }
        return criteria.list();

    }

    public Meeting getMeeting(long id) {
        return (Meeting) getCurrentSession().createQuery("from Meeting where idMeeting = :id").setParameter("id", id).uniqueResult();
    }
}
