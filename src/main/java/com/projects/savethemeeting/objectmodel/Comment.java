package com.projects.savethemeeting.objectmodel;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Michaela on 05.04.2016.
 */
@Entity
@Table(name = "comment", schema = "public")
public class Comment {


    @Id
    @Column(name = "id_comment", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idComment;

    @Column
    private String content;

    @Column
    private Timestamp timestamp;

    @ManyToOne
            @JoinColumn(name="meeting", referencedColumnName = "id_meeting")
    private Meeting meeting;

    @ManyToOne
            @JoinColumn(name="users", referencedColumnName = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_parent_comment", referencedColumnName = "id_comment", nullable = true)
    private Comment parentComment;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public long getIdComment() {
        return idComment;
    }

    public void setIdComment(long idComment) {
        this.idComment = idComment;
    }

    public Comment getParentComment() {
        return parentComment;
    }

    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;

        Comment comment = (Comment) o;

        return idComment == comment.idComment;

    }

    @Override
    public int hashCode() {
        return (int) (idComment ^ (idComment >>> 32));
    }
}
