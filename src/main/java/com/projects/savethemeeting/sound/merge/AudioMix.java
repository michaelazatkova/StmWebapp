package com.projects.savethemeeting.sound.merge;/*
 *	AudioConcat.java
 *
 *	This file is part of jsresources.org
 */

/*
 * Copyright (c) 1999 - 2001 by Matthias Pfisterer
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * - Redistributions of source code must retain the above copyright notice,
 *   this list of conditions and the following disclaimer.
 * - Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 * FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 * COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
|<---            this code is formatted to fit into 80 columns             --->|
*/

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

/*	If the compilation fails because this class is not available,
	get gnu.getopt from the URL given in the comment below.
*/

public class AudioMix {
    private static final int MODE_NONE = 0;
    private static final int MODE_MIXING = 1;
    private static final int MODE_FLOATMIXING = 2;
    private static final int MODE_CONCATENATION = 3;

    /**
     * Flag for debugging messages.
     * If true, some messages are dumped to the console
     * during operation.
     */
    private static boolean DEBUG = false;


    public static void mixFiles(List<String> soundFilesNames,String strOutputFilename ) {

        AudioFormat audioFormat = null;
        List audioInputStreamList = new ArrayList();

        for (String strFilename : soundFilesNames) {

            File soundFile = new File(strFilename);

			/*
			 *	We have to read in the sound file.
			 */
            AudioInputStream audioInputStream = null;
            try {
                audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            } catch (Exception e) {
				/*
				 *	In case of an exception, we dump the exception
				 *	including the stack trace to the console output.
				 *	Then, we exit the program.
				 */
                e.printStackTrace();
                System.exit(1);
            }
            AudioFormat format = audioInputStream.getFormat();
			/*
			  The first input file determines the audio format. This stream's
			  AudioFormat is stored. All other streams are checked against
			  this format.
			 */
            if (audioFormat == null) {
                audioFormat = format;
                if (DEBUG) {
                    out("AudioConcat.main(): format: " + audioFormat);
                }
            } else if (!audioFormat.matches(format)) {
                // TODO: try to convert
                out("AudioConcat.main(): WARNING: AudioFormats don't match");
                out("AudioConcat.main(): master format: " + audioFormat);
                out("AudioConcat.main(): this format: " + format);
            }
            audioInputStreamList.add(audioInputStream);
        }

        AudioInputStream audioInputStream = new MixingAudioInputStream(audioFormat, audioInputStreamList);

        File outputFile = new File(strOutputFilename);
        try {
            AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (DEBUG) {
            out("AudioConcat.main(): before exit");
        }
        System.exit(0);
    }

    private static void out(String strMessage) {
        System.out.println(strMessage);
    }


    public static void main(String[] args) {

    }

}

/*** AudioMix.java ***/