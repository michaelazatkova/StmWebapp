package com.projects.savethemeeting.dao;

import com.projects.savethemeeting.objectmodel.Meeting;
import com.projects.savethemeeting.objectmodel.Record;

/**
 * Created by Michaela on 14.03.2016.
 */
public class RecordDao extends BaseDao<Record> {

    @Override
    public boolean entityExists(Record entity) {
        return false;
    }

    @Override
    public void persist(Record entity) {
        openCurrentSessionwithTransaction();
        super.persist(entity);
        closeCurrentSessionwithTransaction();
    }
}
