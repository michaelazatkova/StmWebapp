package com.projects.savethemeeting.objectmodel;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

/**
 * Created by Michaela on 28.02.2016.
 */
@Entity
@Table(name = "user_meeting", schema = "public")
public class UserOnMeeting implements Serializable {


    private static final long serialVersionUID = -2512843611208620141L;
    @Id
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_meeting")
    private Meeting meeting;

    @Column(name = "participate_from")
    private Timestamp from;

    @Column(name = "participate_to")
    private Timestamp to;

    @OneToOne
    @JoinColumn(name = "id_record")
    private Record record;

    @OneToMany( mappedBy="userOnMeeting")
    public List<PointOfInterest> pointsOfIterest; //unidirectional

    public UserOnMeeting(User user,Meeting meeting, Timestamp from, Timestamp to, Record record,List<PointOfInterest> pointsOfIterest) {
        this.meeting = meeting;
        this.from = from;
        this.to = to;
        this.record = record;
        this.user = user;
        this.pointsOfIterest = pointsOfIterest;
    }

    public UserOnMeeting() {
    }

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

    public Timestamp getFrom() {
        return from;
    }

    public void setFrom(Timestamp from) {
        this.from = from;
    }

    public Timestamp getTo() {
        return to;
    }

    public void setTo(Timestamp to) {
        this.to = to;
    }

    public Record getRecord() {
        return record;
    }

    public List<PointOfInterest> getPointsOfIterest() {
        return pointsOfIterest;
    }

    public void setRecord(Record record) {
        this.record = record;
    }

    public void setPointsOfIterest(List<PointOfInterest> pointsOfIterest) {
        this.pointsOfIterest = pointsOfIterest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserOnMeeting)) return false;

        UserOnMeeting that = (UserOnMeeting) o;

        if (!user.equals(that.user)) return false;
        return meeting.equals(that.meeting);

    }

    @Override
    public int hashCode() {
        int result = user.hashCode();
        result = 31 * result + meeting.hashCode();
        return result;
    }
}
