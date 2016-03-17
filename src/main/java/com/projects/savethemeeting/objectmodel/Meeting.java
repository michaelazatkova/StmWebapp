package com.projects.savethemeeting.objectmodel;

import com.projects.savethemeeting.controller.MeetingInfo;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michaela on 24.02.2016.
 */
@Entity
@Table(name = "meeting", schema = "public")
public class Meeting implements Serializable{

    private static final long serialVersionUID = 498932459446755089L;

    public Meeting(MeetingInfo meetingInfo) {
        this.idMeeting = Long.parseLong(meetingInfo.getIdentificator());
        this.name = meetingInfo.getMeetingName();
        this.started = new Timestamp(Timestamp.parse(meetingInfo.getStarted()));
        this.duration = meetingInfo.getDuration();
    }

    public Meeting() {
    }

    @Id
    @Column(name = "id_meeting", unique = true, nullable = false)
    private long idMeeting;

    @Column
    private String name;
    @Column
    private Timestamp started;
    @Column
    private long duration;

    @OneToMany(mappedBy = "meeting")
    private List<UserOnMeeting> users = new ArrayList<UserOnMeeting>();


    public long getIdMeeting() {
        return idMeeting;
    }

    public void setIdMeeting(long id) {
        this.idMeeting = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getStarted() {
        return started;
    }

    public void setStarted(Timestamp started) {
        this.started = started;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public List<UserOnMeeting> getUsers() {
        return users;
    }

    public void setUsers(List<UserOnMeeting> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Meeting)) return false;

        Meeting meeting = (Meeting) o;

        return idMeeting == meeting.idMeeting;

    }

    @Override
    public int hashCode() {
        return (int) (idMeeting ^ (idMeeting >>> 32));
    }
}
