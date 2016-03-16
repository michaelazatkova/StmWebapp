package com.projects.savethemeeting.dao;

import com.projects.savethemeeting.objectmodel.User;

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
}
