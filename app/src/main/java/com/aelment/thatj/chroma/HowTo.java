package com.aelment.thatj.chroma;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

public class HowTo extends AppCompatActivity {

    //UI elements
    private Button backButton;

    //music
    private boolean continueMusic;
    private boolean musicPlaying;
    private boolean muteMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to);

        //references
        backButton = (Button) findViewById(R.id.backButton);

        //intents
        final Intent iback = new Intent(this, IntroScreen.class);

        SharedPreferences sharedPreferencesSettings = getSharedPreferences("settings", Context.MODE_PRIVATE);
        muteMusic = sharedPreferencesSettings.getBoolean("muteMusic", false);

        backButton.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        continueMusic = true;
                        startActivity(iback);
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
            Intent i = new Intent(this, IntroScreen.class);
            continueMusic = true;
            startActivity(i);
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
