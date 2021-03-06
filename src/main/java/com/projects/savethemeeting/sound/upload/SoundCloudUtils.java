package com.projects.savethemeeting.sound.upload;


import com.projects.savethemeeting.objectmodel.PointOfInterest;
import de.voidplus.soundcloud.Comment;
import de.voidplus.soundcloud.SoundCloud;
import de.voidplus.soundcloud.Track;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michaela on 21.03.2016.
 */
public class SoundCloudUtils extends Thread {
    private SoundCloud soundcloud = new SoundCloud("cb5ef3b1acde0f9998eafecfb2356678","598edc71dc99298c8c25c65a9d2f3b8a");

    public void uploadTrack(final String path, final List<PointOfInterest> points, final String userName) {
        soundcloud.login("savethemeeting.stm@gmail.com","491992");

        Runnable upload = new Runnable() {
            @Override
            public void run() {
                String trackName = (new File(path)).getParentFile().getName();

                    System.out.println("Uploading track " + trackName + " to SOUNDCLOUD....");
                    Track track = new Track();
                    track.setFile(path);
                    track.setTitle(trackName);

                    // POST track
                    Track t = soundcloud.postTrack(track);
                    int trackID = t.getId();
                    for (PointOfInterest poi : points) {
                        Comment comment = new Comment();
                        comment.setBody(userName + " said: This is interesting!");
                        comment.setTimestamp((int) (poi.getTimeOffset()));
                        soundcloud.postCommentToTrack(trackID, comment);
                    }
                    System.out.println("Upload completed....");
                }
        };
        upload.run();

    }

    public List<Comment> getAllComments(long meetingId) {
        soundcloud.login("savethemeeting.stm@gmail.com","491992");
        ArrayList<Track> tracks = soundcloud.getMeTracks();
        Track track;
        for (Track t: tracks) {
            if (t.getTitle().equals(String.valueOf(meetingId))) {
               return soundcloud.getCommentsFromTrack(t.getId());
            }
        }
        return new ArrayList<Comment>();
    }

    public static void main(String[] args) {
        final SoundCloud soundcloud = new SoundCloud("cb5ef3b1acde0f9998eafecfb2356678","598edc71dc99298c8c25c65a9d2f3b8a");
        soundcloud.login("savethemeeting.stm@gmail.com","491992");

        final String path = "c:\\Users\\Michaela\\app-root\\data\\records\\1459766952920\\full.mp3";
        Runnable upload = new Runnable() {
            @Override
            public void run() {
                String trackName = (new File(path)).getParentFile().getName();

                soundcloud.postTrack(new Track(trackName,path));
            }
        };
        upload.run();
    }


}
