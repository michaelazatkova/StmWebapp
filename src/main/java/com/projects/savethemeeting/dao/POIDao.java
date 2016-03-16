package com.projects.savethemeeting.dao;

import com.projects.savethemeeting.objectmodel.PointOfInterest;

/**
 * Created by Michaela on 14.03.2016.
 */
public class POIDao extends BaseDao<PointOfInterest> {

    @Override
    public boolean entityExists(PointOfInterest entity) {
        return false;
    }
}
