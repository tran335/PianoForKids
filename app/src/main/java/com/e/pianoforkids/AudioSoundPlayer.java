package com.e.pianoforkids;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.util.Log;
import android.util.SparseArray;

import java.io.InputStream;

class AudioSoundPlayer {


    private String TAG = "Audio Track INFO: ";
    private SparseArray<PlayThread> threadMap;
    private Context context;
    private static final SparseArray<String> PIANO_SOUND_MAP = new SparseArray<>();
    private static final int MAX_VOLUME = 100, CURRENT_VOLUME = 90;

    static {
        // white keys sounds

        PIANO_SOUND_MAP.put(1, "Piano.mf.C3");
        PIANO_SOUND_MAP.put(2, "Piano.mf.D3");
        PIANO_SOUND_MAP.put(3, "Piano.mf.E3");
        PIANO_SOUND_MAP.put(4, "Piano.mf.F3");
        PIANO_SOUND_MAP.put(5, "Piano.mf.G3");
        PIANO_SOUND_MAP.put(6, "Piano.mf.A3");
        PIANO_SOUND_MAP.put(7, "Piano.mf.B3");
        PIANO_SOUND_MAP.put(8, "Piano.mf.C4");
        PIANO_SOUND_MAP.put(9, "Piano.mf.D4");
        PIANO_SOUND_MAP.put(10, "Piano.mf.E4");
        PIANO_SOUND_MAP.put(11, "Piano.mf.F4");
        PIANO_SOUND_MAP.put(12, "Piano.mf.G4");
        PIANO_SOUND_MAP.put(13, "Piano.mf.A4");
        PIANO_SOUND_MAP.put(14, "Piano.mf.B4");

        // black keys sounds
        PIANO_SOUND_MAP.put(15, "Piano.mf.Db3");
        PIANO_SOUND_MAP.put(16, "Piano.mf.Eb3");
        PIANO_SOUND_MAP.put(17, "Piano.mf.Gb3");
        PIANO_SOUND_MAP.put(18, "Piano.mf.Ab3");
        PIANO_SOUND_MAP.put(19, "Piano.mf.Bb3");
        PIANO_SOUND_MAP.put(20, "Piano.mf.Db4");
        PIANO_SOUND_MAP.put(21, "Piano.mf.Eb4");
        PIANO_SOUND_MAP.put(22, "Piano.mf.Gb4");
        PIANO_SOUND_MAP.put(23, "Piano.mf.Ab4");
        PIANO_SOUND_MAP.put(24, "Piano.mf.Bb4");
    }

    AudioSoundPlayer(Context context) {
        this.context = context;
        threadMap = new SparseArray<>();
    }

    void playNote(int note) {
           if (isNotePlaying(note)) {
        PlayThread thread = new PlayThread(note);
        thread.start();
        threadMap.put(note, thread);
         }
        Log.i(TAG,"Playing "+note +" NOW");
    }

    void stopNote(int note) {
        PlayThread thread = threadMap.get(note);

        if (thread != null) {
            threadMap.remove(note);
        }
       // Log.i(TAG,"Stop  "+note +" completed");
    }

    boolean isNotePlaying(int note) {
        Log.i(TAG,"Note  "+note +" is playing");
        return threadMap.get(note) == null;

    }

    private class PlayThread extends Thread {
        int note;
        AudioTrack audioTrack;

        PlayThread(int note) {
            this.note = note;
        }

        @Override
        public void run() {
            try {
                String path = PIANO_SOUND_MAP.get(note) + ".wav";
                Log.i(TAG,"GET NOTE  "+note +" Completed");

                AssetManager assetManager = context.getAssets();
                AssetFileDescriptor ad = assetManager.openFd(path);

                long fileSize = ad.getLength();
                int bufferSize = 512;
                byte[] buffer = new byte[bufferSize];

                audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, 44100, AudioFormat.CHANNEL_CONFIGURATION_MONO,
                        AudioFormat.ENCODING_PCM_16BIT, bufferSize, AudioTrack.MODE_STREAM);

                float logVolume = (float) (1 - (Math.log(MAX_VOLUME - CURRENT_VOLUME) / Math.log(MAX_VOLUME)));
                Log.i(TAG,"Log Volume  "+logVolume +"  **");
                audioTrack.setVolume(logVolume);

                audioTrack.play();
                Log.i(TAG,"Playing note  "+note +" completed");

                InputStream audioStream;
                int headerOffset = 0x2C;
                long bytesWritten = 0;
                int bytesRead;

                audioStream = assetManager.open(path);
                audioStream.read(buffer, 0, headerOffset);

                while (bytesWritten < fileSize - headerOffset) {
                    bytesRead = audioStream.read(buffer, 0, bufferSize);
                    bytesWritten += audioTrack.write(buffer, 0, bytesRead);
                }

                audioTrack.stop();
                audioTrack.release();

            } catch (Exception ignored) {
            } finally {
                if (audioTrack != null) {
                    audioTrack.release();
                }
            }
        }
    }

}