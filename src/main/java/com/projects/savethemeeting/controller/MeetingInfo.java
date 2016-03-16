package com.projects.savethemeeting.controller;


import com.projects.savethemeeting.objectmodel.PointOfInterest;
import com.projects.savethemeeting.objectmodel.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michaela on 09.02.2016.
 */
public class MeetingInfo {

    private String meetingName;
    private long duration;
    private String started;
    private String identificator;
    private User user;
    private String connectedFrom;
    private String connectedTo;
    private List<PointOfInterest> pointsOfInterest = new ArrayList<PointOfInterest>();

    public MeetingInfo() {

    }

    public MeetingInfo(String meetingName, long duration, String started, String identificator, User user, String connectedFrom, String connectedTo, List<PointOfInterest> pointsOfInterest) {
        this.meetingName = meetingName;
        this.duration = duration;
        this.started = started;
        this.identificator = identificator;
        this.user = user;
        this.connectedFrom = connectedFrom;
        this.connectedTo = connectedTo;
        this.pointsOfInterest = pointsOfInterest;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getStarted() {
        return started;
    }

    public void setStarted(String started) {
        this.started = started;
    }

    public String getMeetingName() {
        return meetingName;
    }

    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }

    public String getIdentificator() {
        return identificator;
    }

    public void setIdentificator(String identificator) {
        this.identificator = identificator;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getConnectedFrom() {
        return connectedFrom;
    }

    public void setConnectedFrom(String connectedFrom) {
        this.connectedFrom = connectedFrom;
    }

    public String getConnectedTo() {
        return connectedTo;
    }

    public void setConnectedTo(String connectedTo) {
        this.connectedTo = connectedTo;
    }

    public List<PointOfInterest> getPointsOfInterest() {
        return pointsOfInterest;
    }

    public void addPointOfInterest(PointOfInterest p) {
        pointsOfInterest.add(p);

    }

}
