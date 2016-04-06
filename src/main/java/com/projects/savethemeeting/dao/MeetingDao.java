package com.projects.savethemeeting.dao;

import com.projects.savethemeeting.objectmodel.Comment;
import com.projects.savethemeeting.objectmodel.Meeting;
import com.projects.savethemeeting.objectmodel.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Michaela on 24.02.2016.
 */

public class MeetingDao extends BaseDao<Meeting> {

    @Override
    public void persist(Meeting entity) {
        openCurrentSessionwithTransaction();
        super.persist(entity);
        closeCurrentSessionwithTransaction();
    }

    @Override
    public boolean entityExists(Meeting meeting) {
        boolean result = false;
        openCurrentSessionwithTransaction();
        Meeting found = (Meeting) getCurrentSession().get(Meeting.class, meeting.getIdMeeting());
        if (found != null) {
            result = true;
        }
        closeCurrentSessionwithTransaction();
        return result;
    }

    public Meeting getLastMeeting(long userId) {
        Meeting lastMeeting = (Meeting) getCurrentSession()
                .createQuery("select m from Meeting m join m.users as u where u.user.fbID = :id order by m.started")
                .setParameter("id", userId)
                .setMaxResults(1)
                .uniqueResult();
        return lastMeeting;
    }

    public void update(Meeting meeting) {
        openCurrentSessionwithTransaction();
        Meeting old = (Meeting) getCurrentSession().get(Meeting.class, meeting.getIdMeeting());
        if (old.getDuration() < meeting.getDuration()) {
            getCurrentSession().saveOrUpdate(meeting);
        }
        closeCurrentSessionwithTransaction();
    }

    public List<Meeting> getLastMeetings(int number, long userId) {
        Query query = getCurrentSession()
                .createQuery("select m from Meeting m join m.users as u where u.user.fbID = :id order by m.started")
                .setParameter("id", userId);
        if (number != -1) {
            query.setMaxResults(number);
        }
        return query.list();

    }

    public Meeting getMeeting(long id) {
        return (Meeting) getCurrentSession().createQuery("from Meeting where idMeeting = :id").setParameter("id", id).uniqueResult();
    }

    public void createComment(long mid, long uid, String content, Comment parent, Timestamp timestamp) {
        Meeting m = (Meeting) getCurrentSession().get(Meeting.class, mid);
        User u = (User) getCurrentSession().get(User.class, uid);
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setMeeting(m);
        comment.setUser(u);
        comment.setParentComment(parent);
        comment.setTimestamp(timestamp);
        getCurrentSession().save(comment);
    }


    public List<Comment> getComments(Meeting meeting) {
        List<Comment> comments = getCurrentSession()
                .createQuery("select c from Comment c where c.meeting = :meeting order by c.id")
                .setParameter("meeting", meeting)
                .list();
        return comments;
    }

    public Comment getComment(long commentId) {
        Comment comment = (Comment) getCurrentSession()
                .createQuery("select c from Comment c where c.id = :id order by c.id")
                .setParameter("id", commentId)
                .uniqueResult();
        return comment;
    }

}
