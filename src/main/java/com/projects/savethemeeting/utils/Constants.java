package com.projects.savethemeeting.utils;

import java.io.File;

/**
 * Created by Michaela on 03.03.2016.
 */
public interface Constants {
    String PATH_FOR_RECORDS = System.getProperty("user.home") + File.separator + "app-root" +File.separator+ "data" +File.separator + "records" + File.separator;
    String TOOLS_DIR = System.getProperty("user.home") + File.separator + "app-root" +File.separator+ "data" +File.separator + "tools" +  File.separator;
    //ffmpeg -f lavfi -i aevalsrc=0:0:0:0:0:0::duration=10 silence.wav
    //ffmpeg -i silence.wav -i skuska2.wav -filter_complex [0:0][1:0]concat=n=2:v=0:a=1 skuska2.wav
}
