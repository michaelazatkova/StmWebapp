package com.projects.savethemeeting.dao;

import com.projects.savethemeeting.objectmodel.Meeting;
import com.projects.savethemeeting.objectmodel.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

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
        openCurrentSessionwithTransaction();
        Meeting lastMeeting  = (Meeting)getCurrentSession()
                .createCriteria(Meeting.class)
                .addOrder(Order.desc("started"))
                .setMaxResults(1)
                .uniqueResult();
        closeCurrentSessionwithTransaction();
        return lastMeeting;
    }

}
