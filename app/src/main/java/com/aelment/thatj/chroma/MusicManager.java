package com.aelment.thatj.chroma;

import android.app.Application;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.provider.MediaStore;

import static android.media.AudioManager.*;

/**
 * Created by thatj on 2017-08-31.
 */

public class MusicManager extends Application{

    private static MediaPlayer mp;

    //HOW THIS WORKS (so you dont forget later):
    //in every activity onResume(), check if music is currently playing through the isPlaying() method, if so, then do not play music
    //in every activity onPause(), check if you want to continueMusic, if you do not, then stop playing music
    //continueMusic will be set to false onResume() in every activity, and will only be set to true before starting an activity that you want the music to continue in

    public static void start(Context context, int songChoice, boolean muted) {
        AudioManager am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        int result = am.requestAudioFocus(null, AudioManager.STREAM_MUSIC, AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK);

        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED && songChoice == 1 && !muted) {
            mp = MediaPlayer.create(context, R.raw.deliberatethought);
            mp.setLooping(true);
            mp.start();
        } else if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED && songChoice == 2 && !muted) {
            mp = MediaPlayer.create(context, R.raw.digitallemonade);
            mp.setLooping(true);
            mp.start();
        }
    }

    public static void stop(Context context) {
        try {
            AudioManager am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
            am.abandonAudioFocus(null);
            mp.stop();
        } catch (NullPointerException e) {

        }
    }

    public static boolean isPlaying() {
        boolean isPlaying = false;
        //mp is not initialized so you need a try/catch block for null object reference errors
        try {
            if (mp.isPlaying()) {
                isPlaying = true;
            } else if (!mp.isPlaying()) {
                isPlaying = false;
            }
        } catch (NullPointerException e) {
            isPlaying = false;
        }

        return isPlaying;
    }
}
