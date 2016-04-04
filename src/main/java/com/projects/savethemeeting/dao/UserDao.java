package com.projects.savethemeeting.dao;

import com.projects.savethemeeting.objectmodel.Meeting;
import com.projects.savethemeeting.objectmodel.User;
import com.projects.savethemeeting.objectmodel.UserOnMeeting;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michaela on 28.02.2016.
 */
public class UserDao extends  BaseDao<User> {

    @Override
    public void persist(User entity) {
        openCurrentSessionwithTransaction();
        super.persist(entity);
        closeCurrentSessionwithTransaction();
    }

    @Override
    public boolean entityExists(User user) {
        boolean result = false;
        openCurrentSessionwithTransaction();
        User found = (User)getCurrentSession().get(User.class,user.getFbID());
        if (found!=null) {
            result = true;
        }
        closeCurrentSessionwithTransaction();
         return result;
    }

    public List<User> getUsers(Meeting meeting) {
        List<User> participants = new ArrayList<User>();
        List<UserOnMeeting> users = getCurrentSession()
                .createCriteria(UserOnMeeting.class)
                .add(Restrictions.eq("meeting", meeting)).list();
        for(UserOnMeeting oum: users) {
            participants.add(oum.getUser());
        }
        return participants;
    }

    public List<String> getAllUserNames() {
        Query query = getCurrentSession()
                .createQuery("select name from User");
        return query.list();

    }
}
