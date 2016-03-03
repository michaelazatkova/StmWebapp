package com.projects.savethemeeting.objectmodel;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by Michaela on 28.02.2016.
 */
@Entity
@Table(name = "user_meeting", schema = "public")
public class UserOnMeeting implements Serializable {

    private static final long serialVersionUID = -3948218176035998913L;
    @Id
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_meeting")
    private Meeting meeting;

    @Column(name = "participate_from")
    private Date from;

    @Column(name = "participate_to")
    private Date to;

    @OneToOne
    @JoinColumn(name = "id_record")
    private Record record;

    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }
}
