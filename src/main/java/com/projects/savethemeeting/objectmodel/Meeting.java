package com.projects.savethemeeting.objectmodel;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * Created by Michaela on 24.02.2016.
 */
@Entity
@Table(name="meeting",schema = "public")
public class Meeting {
    @Id
    @Column(name="id_meeting")
    @GeneratedValue
    private long idMeeting;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "users", joinColumns = {
            @JoinColumn(name = "id_user", nullable = false)
    }, inverseJoinColumns = { @JoinColumn(name = "id_meeting")})
    private List<User> users;

    @Column
    private String name;

    @Column
    private Date started;

    @Column
    private long duration;

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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
