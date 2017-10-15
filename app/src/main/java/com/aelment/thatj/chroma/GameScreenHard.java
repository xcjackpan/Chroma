package com.aelment.thatj.chroma;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class GameScreenHard extends AppCompatActivity {

    //UI elements
    private ImageButton button1Hard;
    private ImageButton button2Hard;
    private ImageButton button3Hard;
    private ImageButton button4Hard;
    private ImageButton button5Hard;
    private ImageButton button6Hard;
    private ImageButton button7Hard;
    private ImageButton button8Hard;
    private ImageButton button9Hard;
    private TextView gameTextHard;
    private TextView pointsCountHard;
    private TextView timerHard;
    private TextView readyTimerHard;

    //countdowns
    private CountDownTimer countDownReady;
    private CountDownTimer countDown;

    //variables
    private int points;
    RoundDataHard roundInfoHard;
    boolean hard = true;

    //music
    private boolean musicPlaying;
    private boolean continueMusic;
    private boolean muteMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen_hard);

        //references
        button1Hard = (ImageButton) findViewById(R.id.gameButton1Hard);
        button2Hard = (ImageButton) findViewById(R.id.gameButton2Hard);
        button3Hard = (ImageButton) findViewById(R.id.gameButton3Hard);
        button4Hard = (ImageButton) findViewById(R.id.gameButton4Hard);
        button5Hard = (ImageButton) findViewById(R.id.gameButton5Hard);
        button6Hard = (ImageButton) findViewById(R.id.gameButton6Hard);
        button7Hard = (ImageButton) findViewById(R.id.gameButton7Hard);
        button8Hard = (ImageButton) findViewById(R.id.gameButton8Hard);
        button9Hard = (ImageButton) findViewById(R.id.gameButton9Hard);
        gameTextHard = (TextView) findViewById(R.id.gameTextHard);
        pointsCountHard = (TextView) findViewById(R.id.pointsHard);
        timerHard = (TextView) findViewById(R.id.timerHard);
        readyTimerHard = (TextView) findViewById(R.id.readyTimerHard);

        //variables initialization
        points = 0;

        SharedPreferences sharedPreferencesSettings = getSharedPreferences("settings", Context.MODE_PRIVATE);
        muteMusic = sharedPreferencesSettings.getBoolean("muteMusic", false);

        button1Hard.setVisibility(View.GONE);
        button2Hard.setVisibility(View.GONE);
        button3Hard.setVisibility(View.GONE);
        button4Hard.setVisibility(View.GONE);
        button5Hard.setVisibility(View.GONE);
        button6Hard.setVisibility(View.GONE);
        button7Hard.setVisibility(View.GONE);
        button8Hard.setVisibility(View.GONE);
        button9Hard.setVisibility(View.GONE);
        gameTextHard.setVisibility(View.GONE);
        pointsCountHard.setVisibility(View.GONE);
        timerHard.setVisibility(View.GONE);

        countDown = new CountDownTimer(1600, 100) { //sets up countdown
            @Override
            public void onTick(long millisUntilFinished) {
                timerHard.setText(String.valueOf(millisUntilFinished/100));
            }

            @Override
            public void onFinish() {
                endGame(points, hard);
            }
        };

        countDownReady = new CountDownTimer(3000, 100) { //sets up countdown
            @Override
            public void onTick(long millisUntilFinished) {
                if (millisUntilFinished > 1000) {
                    readyTimerHard.setText(String.valueOf((millisUntilFinished / 1000) + 1));
                } else {
                    readyTimerHard.setText(String.valueOf(1));
                }
            }

            @Override
            public void onFinish() {
                roundInfoHard = drawGameScreenHard(1, 2, 3, 4, 5, 6, 7, 8, 9); //sets up round 1
                button1Hard.setVisibility(View.VISIBLE);
                button2Hard.setVisibility(View.VISIBLE);
                button3Hard.setVisibility(View.VISIBLE);
                button4Hard.setVisibility(View.VISIBLE);
                button5Hard.setVisibility(View.VISIBLE);
                button6Hard.setVisibility(View.VISIBLE);
                button7Hard.setVisibility(View.VISIBLE);
                button8Hard.setVisibility(View.VISIBLE);
                button9Hard.setVisibility(View.VISIBLE);
                gameTextHard.setVisibility(View.VISIBLE);
                pointsCountHard.setVisibility(View.VISIBLE);
                timerHard.setVisibility(View.VISIBLE);
                readyTimerHard.setVisibility(View.GONE);
                countDown.start();
            }
        };

        countDownReady.start();

        button1Hard.setOnClickListener(
                new ImageButton.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (roundInfoHard.getTextColorString() == roundInfoHard.getButton1ColorHard()) {
                            roundInfoHard = drawGameScreenHard(roundInfoHard.getButton1ColorHard(), roundInfoHard.getButton2ColorHard(), roundInfoHard.getButton3ColorHard(), roundInfoHard.getButton4ColorHard(), roundInfoHard.getButton5ColorHard(), roundInfoHard.getButton6ColorHard(), roundInfoHard.getButton7ColorHard(), roundInfoHard.getButton8ColorHard(), roundInfoHard.getButton9ColorHard());
                            points++;
                            String pointString = String.valueOf(points);
                            pointsCountHard.setText(pointString);
                            countDown.cancel();
                            countDown.start();
                        } else {
                            countDown.cancel();
                            endGame(points, hard);
                        }

                    }
                }
        );

        button2Hard.setOnClickListener(
                new ImageButton.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (roundInfoHard.getTextColorString() == roundInfoHard.getButton2ColorHard()) {
                            roundInfoHard = drawGameScreenHard(roundInfoHard.getButton1ColorHard(), roundInfoHard.getButton2ColorHard(), roundInfoHard.getButton3ColorHard(), roundInfoHard.getButton4ColorHard(), roundInfoHard.getButton5ColorHard(), roundInfoHard.getButton6ColorHard(), roundInfoHard.getButton7ColorHard(), roundInfoHard.getButton8ColorHard(), roundInfoHard.getButton9ColorHard());
                            points++;
                            String pointString = String.valueOf(points);
                            pointsCountHard.setText(pointString);
                            countDown.cancel();
                            countDown.start();
                        } else {
                            countDown.cancel();
                            endGame(points, hard);
                        }

                    }
                }
        );

        button3Hard.setOnClickListener(
                new ImageButton.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (roundInfoHard.getTextColorString() == roundInfoHard.getButton3ColorHard()) {
                            roundInfoHard = drawGameScreenHard(roundInfoHard.getButton1ColorHard(), roundInfoHard.getButton2ColorHard(), roundInfoHard.getButton3ColorHard(), roundInfoHard.getButton4ColorHard(), roundInfoHard.getButton5ColorHard(), roundInfoHard.getButton6ColorHard(), roundInfoHard.getButton7ColorHard(), roundInfoHard.getButton8ColorHard(), roundInfoHard.getButton9ColorHard());
                            points++;
                            String pointString = String.valueOf(points);
                            pointsCountHard.setText(pointString);
                            countDown.cancel();
                            countDown.start();
                        } else {
                            countDown.cancel();
                            endGame(points, hard);
                        }

                    }
                }
        );

        button4Hard.setOnClickListener(
                new ImageButton.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (roundInfoHard.getTextColorString() == roundInfoHard.getButton4ColorHard()) {
                            roundInfoHard = drawGameScreenHard(roundInfoHard.getButton1ColorHard(), roundInfoHard.getButton2ColorHard(), roundInfoHard.getButton3ColorHard(), roundInfoHard.getButton4ColorHard(), roundInfoHard.getButton5ColorHard(), roundInfoHard.getButton6ColorHard(), roundInfoHard.getButton7ColorHard(), roundInfoHard.getButton8ColorHard(), roundInfoHard.getButton9ColorHard());
                            points++;
                            String pointString = String.valueOf(points);
                            pointsCountHard.setText(pointString);
                            countDown.cancel();
                            countDown.start();
                        } else {
                            countDown.cancel();
                            endGame(points, hard);
                        }

                    }
                }
        );

        button5Hard.setOnClickListener(
                new ImageButton.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (roundInfoHard.getTextColorString() == roundInfoHard.getButton5ColorHard()) {
                            roundInfoHard = drawGameScreenHard(roundInfoHard.getButton1ColorHard(), roundInfoHard.getButton2ColorHard(), roundInfoHard.getButton3ColorHard(), roundInfoHard.getButton4ColorHard(), roundInfoHard.getButton5ColorHard(), roundInfoHard.getButton6ColorHard(), roundInfoHard.getButton7ColorHard(), roundInfoHard.getButton8ColorHard(), roundInfoHard.getButton9ColorHard());
                            points++;
                            String pointString = String.valueOf(points);
                            pointsCountHard.setText(pointString);
                            countDown.cancel();
                            countDown.start();
                        } else {
                            countDown.cancel();
                            endGame(points, hard);
                        }

                    }
                }
        );

        button6Hard.setOnClickListener(
                new ImageButton.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (roundInfoHard.getTextColorString() == roundInfoHard.getButton6ColorHard()) {
                            roundInfoHard = drawGameScreenHard(roundInfoHard.getButton1ColorHard(), roundInfoHard.getButton2ColorHard(), roundInfoHard.getButton3ColorHard(), roundInfoHard.getButton4ColorHard(), roundInfoHard.getButton5ColorHard(), roundInfoHard.getButton6ColorHard(), roundInfoHard.getButton7ColorHard(), roundInfoHard.getButton8ColorHard(), roundInfoHard.getButton9ColorHard());
                            points++;
                            String pointString = String.valueOf(points);
                            pointsCountHard.setText(pointString);
                            countDown.cancel();
                            countDown.start();
                        } else {
                            countDown.cancel();
                            endGame(points, hard);
                        }

                    }
                }
        );

        button7Hard.setOnClickListener(
                new ImageButton.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (roundInfoHard.getTextColorString() == roundInfoHard.getButton7ColorHard()) {
                            roundInfoHard = drawGameScreenHard(roundInfoHard.getButton1ColorHard(), roundInfoHard.getButton2ColorHard(), roundInfoHard.getButton3ColorHard(), roundInfoHard.getButton4ColorHard(), roundInfoHard.getButton5ColorHard(), roundInfoHard.getButton6ColorHard(), roundInfoHard.getButton7ColorHard(), roundInfoHard.getButton8ColorHard(), roundInfoHard.getButton9ColorHard());
                            points++;
                            String pointString = String.valueOf(points);
                            pointsCountHard.setText(pointString);
                            countDown.cancel();
                            countDown.start();
                        } else {
                            countDown.cancel();
                            endGame(points, hard);
                        }

                    }
                }
        );

        button8Hard.setOnClickListener(
                new ImageButton.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (roundInfoHard.getTextColorString() == roundInfoHard.getButton8ColorHard()) {
                            roundInfoHard = drawGameScreenHard(roundInfoHard.getButton1ColorHard(), roundInfoHard.getButton2ColorHard(), roundInfoHard.getButton3ColorHard(), roundInfoHard.getButton4ColorHard(), roundInfoHard.getButton5ColorHard(), roundInfoHard.getButton6ColorHard(), roundInfoHard.getButton7ColorHard(), roundInfoHard.getButton8ColorHard(), roundInfoHard.getButton9ColorHard());
                            points++;
                            String pointString = String.valueOf(points);
                            pointsCountHard.setText(pointString);
                            countDown.cancel();
                            countDown.start();
                        } else {
                            countDown.cancel();
                            endGame(points, hard);
                        }

                    }
                }
        );

        button9Hard.setOnClickListener(
                new ImageButton.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (roundInfoHard.getTextColorString() == roundInfoHard.getButton9ColorHard()) {
                            roundInfoHard = drawGameScreenHard(roundInfoHard.getButton1ColorHard(), roundInfoHard.getButton2ColorHard(), roundInfoHard.getButton3ColorHard(), roundInfoHard.getButton4ColorHard(), roundInfoHard.getButton5ColorHard(), roundInfoHard.getButton6ColorHard(), roundInfoHard.getButton7ColorHard(), roundInfoHard.getButton8ColorHard(), roundInfoHard.getButton9ColorHard());
                            points++;
                            String pointString = String.valueOf(points);
                            pointsCountHard.setText(pointString);
                            countDown.cancel();
                            countDown.start();
                        } else {
                            countDown.cancel();
                            endGame(points, hard);
                        }

                    }
                }
        );
    }

    public RoundDataHard drawGameScreenHard (int previous1Color, int previous2Color, int previous3Color, int previous4Color, int previous5Color, int previous6Color, int previous7Color, int previous8Color, int previous9Color) {
        GameFunction gameFunction = new GameFunction();
        final RoundDataHard roundInfoHard = gameFunction.newRoundHard(previous1Color, previous2Color, previous3Color, previous4Color, previous5Color, previous6Color, previous7Color, previous8Color, previous9Color);

        switch (roundInfoHard.getButton1ColorHard()) {
            case 1:
                button1Hard.setImageResource(R.drawable.blue);
                break;
            case 2:
                button1Hard.setImageResource(R.drawable.green);
                break;
            case 3:
                button1Hard.setImageResource(R.drawable.yellow);
                break;
            case 4:
                button1Hard.setImageResource(R.drawable.red);
                break;
            case 5:
                button1Hard.setImageResource(R.drawable.purple);
                break;
            case 6:
                button1Hard.setImageResource(R.drawable.orange);
                break;
            case 7:
                button1Hard.setImageResource(R.drawable.brown);
                break;
            case 8:
                button1Hard.setImageResource(R.drawable.pink);
                break;
            case 9:
                button1Hard.setImageResource(R.drawable.beige);
                break;
        }

        switch (roundInfoHard.getButton2ColorHard()) {
            case 1:
                button2Hard.setImageResource(R.drawable.blue);
                break;
            case 2:
                button2Hard.setImageResource(R.drawable.green);
                break;
            case 3:
                button2Hard.setImageResource(R.drawable.yellow);
                break;
            case 4:
                button2Hard.setImageResource(R.drawable.red);
                break;
            case 5:
                button2Hard.setImageResource(R.drawable.purple);
                break;
            case 6:
                button2Hard.setImageResource(R.drawable.orange);
                break;
            case 7:
                button2Hard.setImageResource(R.drawable.brown);
                break;
            case 8:
                button2Hard.setImageResource(R.drawable.pink);
                break;
            case 9:
                button2Hard.setImageResource(R.drawable.beige);
                break;
        }

        switch (roundInfoHard.getButton3ColorHard()) {
            case 1:
                button3Hard.setImageResource(R.drawable.blue);
                break;
            case 2:
                button3Hard.setImageResource(R.drawable.green);
                break;
            case 3:
                button3Hard.setImageResource(R.drawable.yellow);
                break;
            case 4:
                button3Hard.setImageResource(R.drawable.red);
                break;
            case 5:
                button3Hard.setImageResource(R.drawable.purple);
                break;
            case 6:
                button3Hard.setImageResource(R.drawable.orange);
                break;
            case 7:
                button3Hard.setImageResource(R.drawable.brown);
                break;
            case 8:
                button3Hard.setImageResource(R.drawable.pink);
                break;
            case 9:
                button3Hard.setImageResource(R.drawable.beige);
                break;
        }

        switch (roundInfoHard.getButton4ColorHard()) {
            case 1:
                button4Hard.setImageResource(R.drawable.blue);
                break;
            case 2:
                button4Hard.setImageResource(R.drawable.green);
                break;
            case 3:
                button4Hard.setImageResource(R.drawable.yellow);
                break;
            case 4:
                button4Hard.setImageResource(R.drawable.red);
                break;
            case 5:
                button4Hard.setImageResource(R.drawable.purple);
                break;
            case 6:
                button4Hard.setImageResource(R.drawable.orange);
                break;
            case 7:
                button4Hard.setImageResource(R.drawable.brown);
                break;
            case 8:
                button4Hard.setImageResource(R.drawable.pink);
                break;
            case 9:
                button4Hard.setImageResource(R.drawable.beige);
                break;
        }

        switch (roundInfoHard.getButton5ColorHard()) {
            case 1:
                button5Hard.setImageResource(R.drawable.blue);
                break;
            case 2:
                button5Hard.setImageResource(R.drawable.green);
                break;
            case 3:
                button5Hard.setImageResource(R.drawable.yellow);
                break;
            case 4:
                button5Hard.setImageResource(R.drawable.red);
                break;
            case 5:
                button5Hard.setImageResource(R.drawable.purple);
                break;
            case 6:
                button5Hard.setImageResource(R.drawable.orange);
                break;
            case 7:
                button5Hard.setImageResource(R.drawable.brown);
                break;
            case 8:
                button5Hard.setImageResource(R.drawable.pink);
                break;
            case 9:
                button5Hard.setImageResource(R.drawable.beige);
                break;
        }
        switch (roundInfoHard.getButton6ColorHard()) {
            case 1:
                button6Hard.setImageResource(R.drawable.blue);
                break;
            case 2:
                button6Hard.setImageResource(R.drawable.green);
                break;
            case 3:
                button6Hard.setImageResource(R.drawable.yellow);
                break;
            case 4:
                button6Hard.setImageResource(R.drawable.red);
                break;
            case 5:
                button6Hard.setImageResource(R.drawable.purple);
                break;
            case 6:
                button6Hard.setImageResource(R.drawable.orange);
                break;
            case 7:
                button6Hard.setImageResource(R.drawable.brown);
                break;
            case 8:
                button6Hard.setImageResource(R.drawable.pink);
                break;
            case 9:
                button6Hard.setImageResource(R.drawable.beige);
                break;
        }
        switch (roundInfoHard.getButton7ColorHard()) {
            case 1:
                button7Hard.setImageResource(R.drawable.blue);
                break;
            case 2:
                button7Hard.setImageResource(R.drawable.green);
                break;
            case 3:
                button7Hard.setImageResource(R.drawable.yellow);
                break;
            case 4:
                button7Hard.setImageResource(R.drawable.red);
                break;
            case 5:
                button7Hard.setImageResource(R.drawable.purple);
                break;
            case 6:
                button7Hard.setImageResource(R.drawable.orange);
                break;
            case 7:
                button7Hard.setImageResource(R.drawable.brown);
                break;
            case 8:
                button7Hard.setImageResource(R.drawable.pink);
                break;
            case 9:
                button7Hard.setImageResource(R.drawable.beige);
                break;
        }
        switch (roundInfoHard.getButton8ColorHard()) {
            case 1:
                button8Hard.setImageResource(R.drawable.blue);
                break;
            case 2:
                button8Hard.setImageResource(R.drawable.green);
                break;
            case 3:
                button8Hard.setImageResource(R.drawable.yellow);
                break;
            case 4:
                button8Hard.setImageResource(R.drawable.red);
                break;
            case 5:
                button8Hard.setImageResource(R.drawable.purple);
                break;
            case 6:
                button8Hard.setImageResource(R.drawable.orange);
                break;
            case 7:
                button8Hard.setImageResource(R.drawable.brown);
                break;
            case 8:
                button8Hard.setImageResource(R.drawable.pink);
                break;
            case 9:
                button8Hard.setImageResource(R.drawable.beige);
                break;
        }
        switch (roundInfoHard.getButton9ColorHard()) {
            case 1:
                button9Hard.setImageResource(R.drawable.blue);
                break;
            case 2:
                button9Hard.setImageResource(R.drawable.green);
                break;
            case 3:
                button9Hard.setImageResource(R.drawable.yellow);
                break;
            case 4:
                button9Hard.setImageResource(R.drawable.red);
                break;
            case 5:
                button9Hard.setImageResource(R.drawable.purple);
                break;
            case 6:
                button9Hard.setImageResource(R.drawable.orange);
                break;
            case 7:
                button9Hard.setImageResource(R.drawable.brown);
                break;
            case 8:
                button9Hard.setImageResource(R.drawable.pink);
                break;
            case 9:
                button9Hard.setImageResource(R.drawable.beige);
                break;
        }
        switch (roundInfoHard.getTextColorString()) { //textColor is a var from 1-8 that says the color
            case 1:
                gameTextHard.setText("blue.");
                break;
            case 2:
                gameTextHard.setText("green.");
                break;
            case 3:
                gameTextHard.setText("yellow.");
                break;
            case 4:
                gameTextHard.setText("red.");
                break;
            case 5:
                gameTextHard.setText("purple.");
                break;
            case 6:
                gameTextHard.setText("orange.");
                break;
            case 7:
                gameTextHard.setText("brown.");
                break;
            case 8:
                gameTextHard.setText("pink.");
                break;
            case 9:
                gameTextHard.setText("beige");
                break;
        }
        switch (roundInfoHard.getTextColor()) { //textColorTrick is a var from 1-8 that says the color
            case 1:
                gameTextHard.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.blue));
                break;
            case 2:
                gameTextHard.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.green));
                break;
            case 3:
                gameTextHard.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.yellow));
                break;
            case 4:
                gameTextHard.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.red));
                break;
            case 5:
                gameTextHard.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.purple));
                break;
            case 6:
                gameTextHard.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.orange));
                break;
            case 7:
                gameTextHard.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.brown));
                break;
            case 8:
                gameTextHard.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.pink));
                break;
            case 9:
                gameTextHard.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.beige));
                break;
        }
        return roundInfoHard;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            countDown.cancel();
            countDownReady.cancel();
            Intent i = new Intent(this, IntroScreen.class);
            startActivity(i);
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    public void endGame (int points, boolean hard) {
        Intent i = new Intent (this, EndScreen.class);
        i.putExtra ("score", points);
        i.putExtra ("hard", hard);
        continueMusic = true;
        startActivity(i);
    }

    @Override
    protected void onResume() {
        super.onResume();
        button1Hard.setVisibility(View.GONE);
        button2Hard.setVisibility(View.GONE);
        button3Hard.setVisibility(View.GONE);
        button4Hard.setVisibility(View.GONE);
        button5Hard.setVisibility(View.GONE);
        button6Hard.setVisibility(View.GONE);
        button7Hard.setVisibility(View.GONE);
        button8Hard.setVisibility(View.GONE);
        button9Hard.setVisibility(View.GONE);
        gameTextHard.setVisibility(View.GONE);
        pointsCountHard.setVisibility(View.GONE);
        timerHard.setVisibility(View.GONE);
        readyTimerHard.setVisibility(View.VISIBLE);
        countDownReady.start();

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
        countDown.cancel();
        countDownReady.cancel();

        if (!continueMusic) {
            MusicManager.stop();
            musicPlaying = MusicManager.isPlaying();
        }
    }
}
