package com.projects.savethemeeting.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

import java.util.Date;

/**
 * Created by Michaela on 03.03.2016.
 */
public class Meeting {

    private String meetingName;
    private String duration;
    private Date started;
    private String identificator;

    public Meeting() {
    }

    private static final long serialVersionUID = 5729804337476029211L;

    public Meeting(String meetingName, String duration, Date started, String identificator) {
        this.meetingName = meetingName;
        this.duration = duration;
        this.started = started;
        this.identificator = identificator;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="MMM d, yyyy h:mm:ss a")
    public Date getStarted() {
        return started;
    }

    public void setStarted(Date started) {
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
}
