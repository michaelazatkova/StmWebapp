package com.projects.savethemeeting.objectmodel;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Michaela on 10.03.2016.
 */
@Entity
@Table(name = "point", schema = "public")
public class PointOfInterest implements Serializable {


    private static final long serialVersionUID = 1150393899111990720L;
    @Id
    @Column(name = "id_point", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPoint;

    @Column
    private long timeOffset; // o
    // offset from meeting start in milliseconds

    @ManyToOne
    @JoinColumns ({
            @JoinColumn(name="meeting", referencedColumnName = "id_meeting"),
            @JoinColumn(name="users", referencedColumnName = "id_user"),
    })
    private UserOnMeeting userOnMeeting;

    public PointOfInterest() {

    }

    public PointOfInterest(long timeOffset) {
        this.timeOffset = timeOffset;
    }

    public long getTimeOffset() {
        return timeOffset;
    }

    public void setTimeOffset(long timeOffset) {
        this.timeOffset = timeOffset;
    }

    public UserOnMeeting getUserOnMeeting() {
        return userOnMeeting;
    }

    public void setUserOnMeeting(UserOnMeeting userOnMeeting) {
        this.userOnMeeting = userOnMeeting;
    }

    public long getIdPoint() {
        return idPoint;
    }

    public void setIdPoint(long idPoint) {
        this.idPoint = idPoint;
    }

    @Override
    public String toString() {
        return "PointOfInterest{" +
                "timeOffset=" + timeOffset +
                '}';
    }
}
