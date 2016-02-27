package com.projects.savethemeeting.objectmodel;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Michaela on 24.02.2016.
 */
@Entity
@Table(name="users",schema = "public")
public class User {

    @Id
    @Column(name="id_user")
    @GeneratedValue
    private long userId;

    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
    private List<Meeting> meetings;

    @Column
    private String name;

    @Column
    private String email;

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

    public List<Meeting> getMeetings() {
        return meetings;
    }

    public void setMeetings(List<Meeting> meetings) {
        this.meetings = meetings;
    }
}
