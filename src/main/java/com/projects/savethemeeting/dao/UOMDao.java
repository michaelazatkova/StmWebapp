package com.projects.savethemeeting.dao;

import com.projects.savethemeeting.objectmodel.Meeting;
import com.projects.savethemeeting.objectmodel.User;
import com.projects.savethemeeting.objectmodel.UserOnMeeting;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by Michaela on 14.03.2016.
 */
public class UOMDao extends BaseDao<UserOnMeeting> {

    @Override
    public boolean entityExists(UserOnMeeting entity) {
        return false;
    }

    public List<UserOnMeeting> getUsersOnMeeting(Meeting meeting) {
        openCurrentSessionwithTransaction();
        List<UserOnMeeting> users = getCurrentSession()
                .createCriteria(UserOnMeeting.class)
                .add(Restrictions.eq("meeting", meeting)).list();
        closeCurrentSessionwithTransaction();
        return users;
    }
}
