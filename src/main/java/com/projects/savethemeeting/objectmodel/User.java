package com.projects.savethemeeting.objectmodel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michaela on 24.02.2016.
 */
@Entity
@Table(name = "users", schema = "public")
public class User implements Serializable {

    private static final long serialVersionUID = -4619148560389902972L;

    @Id
    @Column(name = "id_user")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Column
    private String name;
    @Column
    private String email;

    @OneToMany(mappedBy = "user")
    private List<UserOnMeeting> meetings = new ArrayList<UserOnMeeting>();

    public User() {
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long id) {
        this.userId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<UserOnMeeting> getMeetings() {
        return meetings;
    }

    public void setMeetings(List<UserOnMeeting> meetings) {
        this.meetings = meetings;
    }
}
