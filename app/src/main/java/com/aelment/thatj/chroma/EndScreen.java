package com.aelment.thatj.chroma;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EndScreen extends AppCompatActivity {

    //UI elements
    private TextView score;
    private TextView bestScore;
    private TextView gameMode;
    private Button retryButton;
    private Button menuButton;

    //variables
    private int scoreInt;
    private int highScore;
    private int highScoreHard;
    private boolean hard;

    //music
    private boolean muteMusic;
    private boolean musicPlaying;
    private boolean continueMusic;


    @Override
    protected void onResume() {
        super.onResume();
        musicPlaying = MusicManager.isPlaying();
        continueMusic = false;
        if (!musicPlaying) {
            MusicManager.start(this, 2, muteMusic);
            musicPlaying = MusicManager.isPlaying();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (!continueMusic) {
            MusicManager.stop();
            musicPlaying = MusicManager.isPlaying();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);

        //references
        retryButton = (Button) findViewById(R.id.retryButton);
        menuButton = (Button) findViewById(R.id.menuButton);
        gameMode = (TextView) findViewById(R.id.gameMode);
        score = (TextView) findViewById(R.id.score);
        bestScore = (TextView) findViewById(R.id.bestScore);

        //intents
        final Intent imenu = new Intent(this, IntroScreen.class);
        final Intent iretryeasy = new Intent (this, GameScreen.class);
        final Intent iretryhard = new Intent (this, GameScreenHard.class);

        SharedPreferences sharedPreferences = getSharedPreferences("highScoreSave", Context.MODE_PRIVATE);
        SharedPreferences sharedPreferencesSettings = getSharedPreferences("settings", Context.MODE_PRIVATE);
        muteMusic = sharedPreferencesSettings.getBoolean("muteMusic", false);

        Bundle scoreData = getIntent().getExtras(); //gets information of the score
        if (scoreData == null) {
            return;
        } scoreInt = scoreData.getInt("score");

        Bundle modeData = getIntent().getExtras(); //gets information of the game mode
        if (modeData == null) {
            return;
        } hard = modeData.getBoolean("hard");

        if (hard) {
            highScoreHard = sharedPreferences.getInt("highscoreHard", 0);
            if (scoreInt > highScoreHard) {
                highScore(scoreInt, hard);
            }
            bestScore.setText("best: " + String.valueOf(sharedPreferences.getInt("highscoreHard", 0)));
        } else if (!hard) {
            highScore = sharedPreferences.getInt("highscoreEasy", 0);
            if (scoreInt > highScore) { //sets up highscores
                highScore(scoreInt, hard);
            }
            bestScore.setText("best: " + String.valueOf(sharedPreferences.getInt("highscoreEasy", 0)));
        }

        score.setText("score: " + String.valueOf(scoreInt));
        if (hard) {
            gameMode.setText("3x3");
        } else if (!hard) {
            gameMode.setText("2x2");
        }

        menuButton.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(imenu);
                    }
                }
        );

        retryButton.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (hard == true) {
                            continueMusic = true;
                            startActivity(iretryhard);
                        } else if (hard == false) {
                            continueMusic = true;
                            startActivity(iretryeasy);
                        }
                    }
                }
        );
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (hard == true) {
                final Intent iretryhard = new Intent (this, GameScreenHard.class);
                continueMusic = true;
                startActivity(iretryhard);
            } else if (hard == false) {
                final Intent iretryeasy = new Intent (this, GameScreen.class);
                continueMusic = true;
                startActivity(iretryeasy);
            }
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    public void highScore (int scoreInt, boolean hard) {
        SharedPreferences sharedPreferences = getSharedPreferences("highScoreSave", Context.MODE_PRIVATE);
        //creates a sharedpreferences file that only this app can edit
        //sharedpreferences persist

        SharedPreferences.Editor editor = sharedPreferences.edit();
        //sets up an object that can add things to the file

        if (hard == false) {
            editor.putInt("highscoreEasy", scoreInt); //give this object two parameters, a String key and an integer
            editor.apply(); //applies the changes
        } else {
            editor.putInt("highscoreHard", scoreInt); //give this object two parameters, a String key and an integer
            editor.apply();
        }
    }
}
