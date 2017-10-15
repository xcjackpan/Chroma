package com.aelment.thatj.chroma;

import android.content.Context;
import android.media.MediaPlayer;
import android.provider.MediaStore;

/**
 * Created by thatj on 2017-08-31.
 */

public class MusicManager {

    private static MediaPlayer mp;

    //HOW THIS WORKS (so you dont forget later):
    //in every activity onResume(), check if music is currently playing through the isPlaying() method, if so, then do not play music
    //in every activity onPause(), check if you want to continueMusic, if you do not, then stop playing music
    //continueMusic will be set to false onResume() in every activity, and will only be set to true before starting an activity that you want the music to continue in

    public static void start(Context context, int songChoice, boolean muted) {
        if (songChoice == 1 && !muted) {
            mp = MediaPlayer.create(context, R.raw.deliberatethought);
            mp.setLooping(true);
            mp.start();
        } else if (songChoice == 2 && !muted) {
            mp = MediaPlayer.create(context, R.raw.digitallemonade);
            mp.setLooping(true);
            mp.start();
        }
    }

    public static void stop() {
        mp.stop();
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
