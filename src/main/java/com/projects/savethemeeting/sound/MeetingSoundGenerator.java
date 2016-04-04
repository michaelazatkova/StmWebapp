package com.projects.savethemeeting.sound;

import com.projects.savethemeeting.objectmodel.PointOfInterest;
import com.projects.savethemeeting.objectmodel.Record;
import com.projects.savethemeeting.sound.upload.SoundCloudUtils;
import com.projects.savethemeeting.utils.Constants;
import com.projects.savethemeeting.utils.ProcessWrapper;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Michaela on 22.03.2016.
 */
public class MeetingSoundGenerator {
    private SoundCloudUtils soundCloudUtils;

    public void setSoundCloudUtils(SoundCloudUtils soundCloudUtils) {
        this.soundCloudUtils = soundCloudUtils;
    }

    public enum State {
        FREE, BUSY
    }

    private State state = State.FREE;

    public State getState() {
        return state;
    }

    private void busy() {
        state = State.BUSY;
    }

    private void free() {
        state = State.FREE;
    }

    public void createFirstMeetingSound(final Record record, final Timestamp connectedFrom, final Timestamp meetingStarted, final List<PointOfInterest> pointsOfInterest, final String userName) {
        busy();
        Thread process = new Thread(new Runnable() {
            @Override
            public void run() {
                long diff = Math.abs((meetingStarted.getTime() - connectedFrom.getTime()) / 1000) + 1; //difference in seconds
                String directory = new File(record.getPath()).getParent() + File.separator;
                String silence = directory+"silence.mp3";
                try {
                    Process p1 = Runtime.getRuntime().exec(Constants.TOOLS_DIR + "ffmpeg -f lavfi -i aevalsrc=0:0:0:0:0:0::duration=" + diff + " " + silence);
                    redirectOutput(p1.getInputStream());
                    redirectOutput(p1.getErrorStream());
                    int exitStatus = new ProcessWrapper(p1).waitFor(5 * 60 * 1000L);
                    System.out.println("Silence generator finished with status: " + exitStatus);

                    Process p2 = Runtime.getRuntime().exec(Constants.TOOLS_DIR + "ffmpeg -i " + silence + " -i "+record.getPath()+" -filter_complex [0:0][1:0]concat=n=2:v=0:a=1 "+directory+"full.mp3");
                    redirectOutput(p2.getInputStream());
                    redirectOutput(p2.getErrorStream());
                     exitStatus = new ProcessWrapper(p2).waitFor(5 * 60 * 1000L);
                    System.out.println("Silence concatenation finished with status: " + exitStatus);

                    File s = new File(silence);
                    if (s.exists()) {
                        FileUtils.forceDelete(s);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    // ked sa zryhas, free
                    free();
                }
                File parent = new File(record.getPath()).getParentFile();
                soundCloudUtils.uploadTrack(parent.getAbsolutePath() + File.separator + "full.mp3", pointsOfInterest, userName);
            }
        });
        process.start();
    }

    public void regenerateMeetingSound(final Record record, final Timestamp connectedFrom, final Timestamp meetingStarted, final List<PointOfInterest> pointsOfInterest, final String userName) {
        busy();
        Thread process = new Thread(new Runnable() {
            @Override
            public void run() {
                long diff = Math.abs((meetingStarted.getTime() - connectedFrom.getTime()) / 1000) + 1; //difference in seconds
                String directory = new File(record.getPath()).getParent() + File.separator;
                String silence = directory+"silence.mp3";
                String full = directory+"full.mp3";
                String temp = directory+"temp.mp3";
                String output = directory+"output.mp3";
                try {
                    Process p1 = Runtime.getRuntime().exec(Constants.TOOLS_DIR + "ffmpeg -f lavfi -i aevalsrc=0:0:0:0:0:0::duration=" + diff + " " + silence);
                    redirectOutput(p1.getInputStream());
                    redirectOutput(p1.getErrorStream());
                    int exitStatus = new ProcessWrapper(p1).waitFor(5 * 60 * 1000L);
                    System.out.println("Silence generator finished with status: " + exitStatus);

                    Process p2 = Runtime.getRuntime().exec(Constants.TOOLS_DIR + "ffmpeg -i " + silence + " -i "+record.getPath()+" -filter_complex [0:0][1:0]concat=n=2:v=0:a=1 "+temp);
                    redirectOutput(p2.getInputStream());
                    redirectOutput(p2.getErrorStream());
                    exitStatus = new ProcessWrapper(p2).waitFor(5 * 60 * 1000L);
                    System.out.println("Silence concatenation finished with status: " + exitStatus);

                    Process p3 = Runtime.getRuntime().exec(Constants.TOOLS_DIR + "ffmpeg -i "+full+" -i "+temp+" -filter_complex \"[0:0][1:0] amix=inputs=2:duration=longest\" -c:a libmp3lame "+output);
                    redirectOutput(p3.getInputStream());
                    redirectOutput(p3.getErrorStream());
                    exitStatus = new ProcessWrapper(p3).waitFor(5 * 60 * 1000L);
                    System.out.println("Mixing finished with status: " + exitStatus);

                    File f = new File(full);
                    if (f.exists()) {
                        FileUtils.forceDelete(f);
                    }

                    File s = new File(silence);
                    if (s.exists()) {
                        FileUtils.forceDelete(s);
                    }

                    File t = new File(temp);
                    if (t.exists()) {
                        FileUtils.forceDelete(t);
                    }

                    File o = new File(output);
                    if (o.exists()) {
                        o.renameTo(new File(full));
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    // if something went wrong
                    free();
                }
                File parent = new File(record.getPath()).getParentFile();
                soundCloudUtils.uploadTrack(parent.getAbsolutePath() + File.separator + "full.mp3", pointsOfInterest, userName);
            }
        });
        process.start();
    }

    private void redirectOutput(final InputStream stream) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println("MSG ------>   " + line);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
