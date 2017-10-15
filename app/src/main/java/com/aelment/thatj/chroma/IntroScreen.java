package com.aelment.thatj.chroma;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import static android.media.AudioManager.AUDIOFOCUS_GAIN;
import static android.media.AudioManager.AUDIOFOCUS_LOSS;
import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT;
import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK;

public class IntroScreen extends AppCompatActivity {

    //UI elements
    private Button twobytwo;
    private Button threebythree;
    private Button howToPlay;
    private ImageButton muteButton;
    private ImageView animateSquare1;
    private ImageView animateSquare2;
    private ImageView animateSquare3;
    private ImageView animateSquare4;
    private ImageView animateSquare5;
    private ImageView animateSquare6;
    private ImageView animateSquare7;
    private ImageView animateSquare8;

    //music
    private boolean muteMusic;
    private boolean continueMusic;
    private boolean musicPlaying;

    //animation
    Animation fade1;
    Animation fade2;
    Animation fade3;
    Animation fade4;
    Animation fade5;
    Animation fade6;
    Animation fade7;
    Animation fade8;

    //HOW THIS WORKS (so you dont forget later):
    //there is a newRound method in GameFunction that will randomize numbers to determine what colors buttons will be, which of the buttons will be correct, what the trick color of the text will be
    //this newRound method returns all those randomized numbers as a RoundData class (this class just stores the randomized numbers)
    //there is a drawGameScreen method that will take the randomized numbers and assign colors to the buttons and the text as well as the string of the text
    //this method is called whenever the user answers correctly
    //there is a clicklistener on the buttons that checks if the color of the button (represented by an integer in RoundData) is the same as the String of the text (represented by an integer in RoundData)
    //if wrong, an endscreen method is called
    //if correct, drawscreen is called again
    //note that inside drawscreen is a call to newRound() which generates new random numbers

    //ANIMATIONS
    //create an "anim" folder in /res
    //alpha animations affect transparency (alpha quality)
    //8 different alpha animations for the squares (differing only by length) because the squares should fade in and out at different times
    //AnimationUtils.loadAnimation(context, R.anim.NAME) simply sets an Animation object in Java equal to the specified animation in the res/anim folder

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_screen);

        //references
        twobytwo = (Button) findViewById(R.id.twobytwo);
        threebythree = (Button) findViewById(R.id.threebythree);
        howToPlay = (Button) findViewById(R.id.howToPlay);
        muteButton = (ImageButton) findViewById(R.id.muteButton);
        animateSquare1 = (ImageView) findViewById(R.id.animateSquare1);
        animateSquare2 = (ImageView) findViewById(R.id.animateSquare2);
        animateSquare3 = (ImageView) findViewById(R.id.animateSquare3);
        animateSquare4 = (ImageView) findViewById(R.id.animateSquare4);
        animateSquare5 = (ImageView) findViewById(R.id.animateSquare5);
        animateSquare6 = (ImageView) findViewById(R.id.animateSquare6);
        animateSquare7 = (ImageView) findViewById(R.id.animateSquare7);
        animateSquare8 = (ImageView) findViewById(R.id.animateSquare8);

        //intents
        final Intent ieasy = new Intent(this, GameScreen.class);
        final Intent ihard = new Intent(this, GameScreenHard.class);
        final Intent ihowto = new Intent(this, HowTo.class);

        fade1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade1);
        fade2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade2);
        fade3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade3);
        fade4 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade4);
        fade5 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade5);
        fade6 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade6);
        fade7 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade7);
        fade8 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade8);

        animateSquare1.startAnimation(fade1);
        animateSquare2.startAnimation(fade2);
        animateSquare3.startAnimation(fade3);
        animateSquare4.startAnimation(fade4);
        animateSquare5.startAnimation(fade5);
        animateSquare6.startAnimation(fade6);
        animateSquare7.startAnimation(fade7);
        animateSquare8.startAnimation(fade8);

        final SharedPreferences sharedPreferencesSettings = getSharedPreferences("settings", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferencesSettings.edit();

        muteMusic = sharedPreferencesSettings.getBoolean("muteMusic", false);
        if (muteMusic) {
            muteButton.setImageResource(R.drawable.muted);
        } else if (!muteMusic) {
            muteButton.setImageResource(R.drawable.unmuted);
        }

        twobytwo.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(ieasy);
                    }
                }
        );

        threebythree.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(ihard);
                    }
                }
        );

        howToPlay.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        continueMusic = true;
                        startActivity(ihowto);
                    }
                }
        );

        muteButton.setOnClickListener(
                new ImageButton.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (muteMusic) {
                            muteMusic = false;
                            muteButton.setImageResource(R.drawable.unmuted);
                            if (!MusicManager.isPlaying()) {
                                MusicManager.start(getApplicationContext(), 1, muteMusic);
                            }
                            editor.putBoolean("muteMusic", muteMusic);
                            editor.apply();
                        } else if (!muteMusic) {
                            muteMusic = true;
                            muteButton.setImageResource(R.drawable.muted);
                            //am.abandonAudioFocus(mAudioFocusChangeListener);
                            if (MusicManager.isPlaying()) {
                                MusicManager.stop(getApplicationContext());
                            }
                            editor.putBoolean("muteMusic", muteMusic);
                            editor.apply();
                        }
                    }
                }
        );
    }

    @Override
    protected void onResume() {
        super.onResume();
        musicPlaying = MusicManager.isPlaying();
        continueMusic = false;
        if (!musicPlaying) {
            MusicManager.start(this, 1, muteMusic);
            musicPlaying = MusicManager.isPlaying();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (!continueMusic) {
            MusicManager.stop(this);
            musicPlaying = MusicManager.isPlaying();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finishAffinity();
        }

        return super.onKeyDown(keyCode, event);
    }
}