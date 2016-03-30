package com.projects.savethemeeting.sound;

import com.projects.savethemeeting.objectmodel.PointOfInterest;
import com.projects.savethemeeting.objectmodel.Record;
import com.projects.savethemeeting.sound.merge.AudioMix;
import com.projects.savethemeeting.sound.upload.SoundCloudUtils;
import com.projects.savethemeeting.utils.Constants;
import com.projects.savethemeeting.utils.ProcessWrapper;

import java.io.File;
import java.io.IOException;
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

    public void createFirstMeetingSound(final Record record, final Timestamp connectedFrom, final Timestamp meetingStarted,final List<PointOfInterest> pointsOfInterest, final String userName) {
        busy();
        Thread process = new Thread(new Runnable() {
            @Override
            public void run() {
                long diff = Math.abs((meetingStarted.getTime() - connectedFrom.getTime()) / 1000) + 1; //difference in seconds

                File batchFile = new File(Constants.TOOLS_DIR + "createFirst.bat");
                String[] command = {batchFile.getAbsolutePath(), String.valueOf(diff), record.getPath()};
                final ProcessBuilder processBuilder = new ProcessBuilder(command);
                processBuilder.directory(batchFile.getParentFile());
                processBuilder.redirectErrorStream(true);
                processBuilder.redirectOutput(new File(batchFile.getParent() + "/output.txt"));
                try {
                    final Process process = processBuilder.start();
                    final int exitStatus = new ProcessWrapper(process).waitFor(30 * 60 * 1000L);
                    System.out.println("Generator finished with status: " + exitStatus);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    // ked sa zryhas, free
                    free();
                }
                File parent = new File(record.getPath()).getParentFile();
                soundCloudUtils.uploadTrack(parent.getAbsolutePath() + File.separator + "full.mp3",pointsOfInterest,userName);
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
                files.add(parentPath+ File.separator + "new.mp3");
                files.add(parentPath+ File.separator + "full.mp3");

                String resultFile = parentPath + File.separator + "full.mp3";
                AudioMix.mixFiles(files,resultFile);

                //soundCloudUtils.uploadTrack(resultFile);
                free();
            }
        });
        process.start();
    }
}
