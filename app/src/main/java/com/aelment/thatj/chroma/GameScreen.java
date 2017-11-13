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

public class GameScreen extends AppCompatActivity {

    //UI elements
    private ImageButton button1;
    private ImageButton button2;
    private ImageButton button3;
    private ImageButton button4;
    private TextView gameText;
    private TextView pointsCount;
    private TextView readyTimer;
    private CircleTimer circleTimer;

    //variables
    private int points = 0;
    private CountDownTimer countDown;
    private CountDownTimer countDownReady;
    RoundData roundInfo;
    boolean hard = false;

    //music
    private boolean musicPlaying;
    private boolean continueMusic;
    private boolean muteMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        //references
        button1 = (ImageButton) findViewById(R.id.gameButton1);
        button2 = (ImageButton) findViewById(R.id.gameButton2);
        button3 = (ImageButton) findViewById(R.id.gameButton3);
        button4 = (ImageButton) findViewById(R.id.gameButton4);
        gameText = (TextView) findViewById(R.id.gameText);
        pointsCount = (TextView) findViewById(R.id.points);
        readyTimer = (TextView) findViewById(R.id.readyTimer);
        circleTimer = (CircleTimer) findViewById(R.id.circle);

        SharedPreferences sharedPreferencesSettings = getSharedPreferences("settings", Context.MODE_PRIVATE);
        muteMusic = sharedPreferencesSettings.getBoolean("muteMusic", false);

        button1.setVisibility(View.GONE);
        button2.setVisibility(View.GONE);
        button3.setVisibility(View.GONE);
        button4.setVisibility(View.GONE);
        gameText.setVisibility(View.GONE);
        pointsCount.setVisibility(View.GONE);
        circleTimer.setVisibility(View.GONE);

        final CircleTimer.TimerAnimation animation = new CircleTimer.TimerAnimation(circleTimer, 360);
        animation.setDuration(1600);

        countDownReady = new CountDownTimer(3000, 100) { //sets up countdown
            @Override
            public void onTick(long millisUntilFinished) {
                if (millisUntilFinished > 1000) {
                    readyTimer.setText(String.valueOf((millisUntilFinished / 1000) + 1));
                } else {
                    readyTimer.setText(String.valueOf(1));
                }
            }

            @Override
            public void onFinish() {
                roundInfo = drawGameScreen(1, 2, 3, 4); //sets up round 1
                button1.setVisibility(View.VISIBLE);
                button2.setVisibility(View.VISIBLE);
                button3.setVisibility(View.VISIBLE);
                button4.setVisibility(View.VISIBLE);
                gameText.setVisibility(View.VISIBLE);
                pointsCount.setVisibility(View.VISIBLE);
                circleTimer.setVisibility(View.VISIBLE);
                readyTimer.setVisibility(View.GONE);
                countDown.start();
                circleTimer.startAnimation(animation);

            }
        };

        countDown = new CountDownTimer(1600, 100) { //sets up countdown
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                endGame(points, hard);
            }
        };

        countDownReady.start();

        button1.setOnClickListener(
                new ImageButton.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (roundInfo.getTextColor() == roundInfo.button1Color) {
                            roundInfo = drawGameScreen(roundInfo.getButton1Color(), roundInfo.getButton2Color(), roundInfo.getButton3Color(), roundInfo.getButton4Color());
                            points++;
                            String pointString = String.valueOf(points);
                            pointsCount.setText(pointString);
                            countDown.cancel();
                            countDown.start();
                            circleTimer.clearAnimation();
                            circleTimer.startAnimation(animation);
                        } else {
                            countDown.cancel();
                            endGame(points, hard);
                        }

                    }
                }
        );

        button2.setOnClickListener(
                new ImageButton.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (roundInfo.getTextColor() == roundInfo.button2Color) {
                            roundInfo = drawGameScreen(roundInfo.getButton1Color(), roundInfo.getButton2Color(), roundInfo.getButton3Color(), roundInfo.getButton4Color());
                            points++;
                            String pointString = String.valueOf(points);
                            pointsCount.setText(pointString);
                            countDown.cancel();
                            countDown.start();
                            circleTimer.clearAnimation();
                            circleTimer.startAnimation(animation);
                        } else {
                            countDown.cancel();
                            endGame(points, hard);
                        }

                    }
                }
        );

        button3.setOnClickListener(
                new ImageButton.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (roundInfo.getTextColor() == roundInfo.button3Color) {
                            roundInfo = drawGameScreen(roundInfo.getButton1Color(), roundInfo.getButton2Color(), roundInfo.getButton3Color(), roundInfo.getButton4Color());
                            points++;
                            String pointString = String.valueOf(points);
                            pointsCount.setText(pointString);
                            countDown.cancel();
                            countDown.start();
                            circleTimer.clearAnimation();
                            circleTimer.startAnimation(animation);
                        } else {
                            countDown.cancel();
                            endGame(points, hard);
                        }

                    }
                }
        );

        button4.setOnClickListener(
                new ImageButton.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (roundInfo.getTextColor() == roundInfo.button4Color) {
                            roundInfo = drawGameScreen(roundInfo.getButton1Color(), roundInfo.getButton2Color(), roundInfo.getButton3Color(), roundInfo.getButton4Color());
                            points++;
                            String pointString = String.valueOf(points);
                            pointsCount.setText(pointString);
                            countDown.cancel();
                            countDown.start();
                            circleTimer.clearAnimation();
                            circleTimer.startAnimation(animation);
                        } else {
                            countDown.cancel();
                            endGame(points, hard);
                        }

                    }
                }
        );
    }

    @Override
    protected void onResume() {
        super.onResume();
        button1.setVisibility(View.GONE);
        button2.setVisibility(View.GONE);
        button3.setVisibility(View.GONE);
        button4.setVisibility(View.GONE);
        gameText.setVisibility(View.GONE);
        pointsCount.setVisibility(View.GONE);
        circleTimer.setVisibility(View.GONE);

        readyTimer.setVisibility(View.VISIBLE);
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
        circleTimer.clearAnimation();
        countDown.cancel();
        countDownReady.cancel();

        if (!continueMusic) {
            MusicManager.stop(this);
            musicPlaying = MusicManager.isPlaying();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            circleTimer.clearAnimation();
            countDown.cancel();
            countDownReady.cancel();
            Intent i = new Intent(this, IntroScreen.class);
            startActivity(i);
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    public RoundData drawGameScreen (int previous1color, int previous2color, int previous3color, int previous4color) {
        GameFunction gameFunction = new GameFunction(); //create instance of GameFunction class
        final RoundData roundInfo = gameFunction.newRound(previous1color, previous2color, previous3color, previous4color); //sets up the initial round
        //calls the newRound method which returns a roundInfo, this roundInfo is used to draw the game screen

        switch (roundInfo.getButton1Color()) {
            case 1:
                button1.setImageResource(R.drawable.blue);
                break;
            case 2:
                button1.setImageResource(R.drawable.green);
                break;
            case 3:
                button1.setImageResource(R.drawable.yellow);
                break;
            case 4:
                button1.setImageResource(R.drawable.red);
                break;
            case 5:
                button1.setImageResource(R.drawable.purple);
                break;
            case 6:
                button1.setImageResource(R.drawable.orange);
                break;
            case 7:
                button1.setImageResource(R.drawable.brown);
                break;
            case 8:
                button1.setImageResource(R.drawable.pink);
                break;
            case 9:
                button1.setImageResource(R.drawable.beige);
                break;
        }

        switch (roundInfo.getButton2Color()) {
            case 1:
                button2.setImageResource(R.drawable.blue);
                break;
            case 2:
                button2.setImageResource(R.drawable.green);
                break;
            case 3:
                button2.setImageResource(R.drawable.yellow);
                break;
            case 4:
                button2.setImageResource(R.drawable.red);
                break;
            case 5:
                button2.setImageResource(R.drawable.purple);
                break;
            case 6:
                button2.setImageResource(R.drawable.orange);
                break;
            case 7:
                button2.setImageResource(R.drawable.brown);
                break;
            case 8:
                button2.setImageResource(R.drawable.pink);
                break;
            case 9:
                button2.setImageResource(R.drawable.beige);
                break;
        }

        switch (roundInfo.getButton3Color()) {
            case 1:
                button3.setImageResource(R.drawable.blue);
                break;
            case 2:
                button3.setImageResource(R.drawable.green);
                break;
            case 3:
                button3.setImageResource(R.drawable.yellow);
                break;
            case 4:
                button3.setImageResource(R.drawable.red);
                break;
            case 5:
                button3.setImageResource(R.drawable.purple);
                break;
            case 6:
                button3.setImageResource(R.drawable.orange);
                break;
            case 7:
                button3.setImageResource(R.drawable.brown);
                break;
            case 8:
                button3.setImageResource(R.drawable.pink);
                break;
            case 9:
                button3.setImageResource(R.drawable.beige);
                break;
        }

        switch (roundInfo.getButton4Color()) {
            case 1:
                button4.setImageResource(R.drawable.blue);
                break;
            case 2:
                button4.setImageResource(R.drawable.green);
                break;
            case 3:
                button4.setImageResource(R.drawable.yellow);
                break;
            case 4:
                button4.setImageResource(R.drawable.red);
                break;
            case 5:
                button4.setImageResource(R.drawable.purple);
                break;
            case 6:
                button4.setImageResource(R.drawable.orange);
                break;
            case 7:
                button4.setImageResource(R.drawable.brown);
                break;
            case 8:
                button4.setImageResource(R.drawable.pink);
                break;
            case 9:
                button4.setImageResource(R.drawable.beige);
                break;
        }//repeat this for all the colours

        switch (roundInfo.getTextColor()) { //textColor is a var from 1-8 that says the color
            case 1:
                gameText.setText("blue.");
                break;
            case 2:
                gameText.setText("green.");
                break;
            case 3:
                gameText.setText("yellow.");
                break;
            case 4:
                gameText.setText("red.");
                break;
            case 5:
                gameText.setText("purple.");
                break;
            case 6:
                gameText.setText("orange.");
                break;
            case 7:
                gameText.setText("brown.");
                break;
            case 8:
                gameText.setText("pink.");
                break;
            case 9:
                gameText.setText("beige.");
                break;
        }

        switch (roundInfo.getTextColorTrick()) { //textColorTrick is a var from 1-8 that says the color
            case 1:
                gameText.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.blue));
                break;
            case 2:
                gameText.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.green));
                break;
            case 3:
                gameText.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.yellow));
                break;
            case 4:
                gameText.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.red));
                break;
            case 5:
                gameText.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.purple));
                break;
            case 6:
                gameText.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.orange));
                break;
            case 7:
                gameText.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.brown));
                break;
            case 8:
                gameText.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.pink));
                break;
            case 9:
                gameText.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.beige));
                break;
        }
        return roundInfo;
    }

    public void endGame (int points, boolean hard) {
        Intent i = new Intent(this, EndScreen.class);
        i.putExtra ("score", points);
        i.putExtra ("hard", hard);
        continueMusic = true;
        startActivity(i);
    }
}
