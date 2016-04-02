package com.projects.savethemeeting.sound;

import com.projects.savethemeeting.objectmodel.PointOfInterest;
import com.projects.savethemeeting.objectmodel.Record;
import com.projects.savethemeeting.sound.merge.AudioMix;
import com.projects.savethemeeting.sound.upload.SoundCloudUtils;
import com.projects.savethemeeting.utils.Constants;
import com.projects.savethemeeting.utils.ProcessWrapper;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
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

    public void regenerateMeetingSound(final Record record, final Timestamp connectedFrom, final Timestamp meetingStarted) {
        busy();
        Thread process = new Thread(new Runnable() {
            @Override
            public void run() {
                long diff = Math.abs((meetingStarted.getTime() - connectedFrom.getTime()) / 1000) + 1; //difference in seconds

                File batchFile = new File(Constants.TOOLS_DIR + "regenerate.bat");
                String[] command = {batchFile.getAbsolutePath(), String.valueOf(diff), record.getPath()};
                final ProcessBuilder processBuilder = new ProcessBuilder(command);
                processBuilder.directory(batchFile.getParentFile());
                processBuilder.redirectErrorStream(true);
                processBuilder.redirectOutput(new File(batchFile.getParent() + "/output.txt"));
                try {
                    final Process process = processBuilder.start();
                    final int exitStatus = process.waitFor();
                    System.out.println("Processed finished with status: " + exitStatus);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String parentPath = new File(record.getPath()).getParent();
                List<String> files = new ArrayList<String>();
                files.add(parentPath + File.separator + "new.mp3");
                files.add(parentPath + File.separator + "full.mp3");

                String resultFile = parentPath + File.separator + "full.mp3";
                AudioMix.mixFiles(files, resultFile);

                //soundCloudUtils.uploadTrack(resultFile);
                free();
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
