package com.projects.savethemeeting.dao;

import com.projects.savethemeeting.objectmodel.Meeting;

/**
 * Created by Michaela on 24.02.2016.
 */

public class MeetingDao extends BaseDao<Meeting> {

    public void persist(Meeting entity) {
        openCurrentSessionwithTransaction();
        getCurrentSession().save(entity);
        closeCurrentSessionwithTransaction();
    }
}
