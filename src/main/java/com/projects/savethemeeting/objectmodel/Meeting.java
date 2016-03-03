package com.projects.savethemeeting.objectmodel;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michaela on 24.02.2016.
 */
@Entity
@Table(name = "meeting", schema = "public")
public class Meeting implements Serializable{

    private static final long serialVersionUID = 498932459446755089L;

    @Id
    @Column(name = "id_meeting", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idMeeting;

    @Column
    private String name;
    @Column
    private Date started;
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

    public Date getStarted() {
        return started;
    }

    public void setStarted(Date started) {
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
}
