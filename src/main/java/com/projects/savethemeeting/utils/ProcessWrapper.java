package com.projects.savethemeeting.utils;

import org.hsqldb.lib.InputStreamWrapper;

import java.io.*;

/**
 * Created by Michaela on 29.03.2016.
 */
public class ProcessWrapper extends Thread {
    private Process process;
    private int exitCode = Integer.MIN_VALUE;
    public static ProcessWrapper currentProcess;

    public ProcessWrapper(Process process) {
        this.process = process;
    }

    @Override
    public void run() {
        currentProcess = this;
        try {
            redirectOutput(process.getInputStream());
            redirectOutput(process.getErrorStream());
            exitCode = process.waitFor();
        } catch (InterruptedException e) {
//            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            process.destroy();
            currentProcess = null;
        }
    }

    private void redirectOutput(final InputStream stream) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public final int waitFor(long timeout) {
        this.start();

        try {
            this.join(timeout);
        } catch (InterruptedException e) {
            this.interrupt();
        }

        return exitCode;
    }
}
