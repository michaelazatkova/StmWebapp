package com.projects.savethemeeting.dao;

import com.projects.savethemeeting.objectmodel.User;

/**
 * Created by Michaela on 28.02.2016.
 */
public class UserDao extends  BaseDao<User> {

    @Override
    public void persist(User entity) {
        openCurrentSessionwithTransaction();
        getCurrentSession().save(entity);
        closeCurrentSessionwithTransaction();
    }
}
