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
    private static final long serialVersionUID = -7879888870758729313L;

    @Id
    @Column(name = "id_user")
    private long fbID;

    @Column
    private String name;

    @Column
    private String email;

    @ManyToOne
    @JoinColumn(name = "id_user_role", referencedColumnName = "id_user_role")
    private UserRole idUserRole;

    @OneToMany(mappedBy = "user")
    private List<UserOnMeeting> meetings = new ArrayList<UserOnMeeting>();

    public long getFbID() {
        return fbID;
    }

    public void setFbID(long id) {
        this.fbID = id;
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

    public UserRole getIdUserRole() {
        return idUserRole;
    }

    public void setIdUserRole(UserRole idUserRole) {
        this.idUserRole = idUserRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        return fbID == user.fbID;

    }

    @Override
    public int hashCode() {
        return (int) (fbID ^ (fbID >>> 32));
    }
}
