package com.dmnstudio.reflexgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.games.Games;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Classic extends AppCompatActivity {

    private MediaPlayer mediaPlayerNewRecord , mediaPlayerFailed , mediaPlayerCorrect;
    private Singleton singleton;
    private Boolean playMusic = true;

    CountDownTimer countDownTimer;
    private ConstraintLayout constraint;
    private TextView timeText , touchText;
    private ImageButton tapImageButton;
    Boolean k=false;
    Boolean onay =true;
    ImageButton againSpeedButton;
    private TextView endHighScore , highScoreTopNumber;

    private float highScore , score;
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classic);

        sharedPreferences = this.getSharedPreferences("com.dmnstudio.reflexgame" , MODE_PRIVATE);

        highScore = sharedPreferences.getFloat("highScoreSpeed" , 100);
        score=0;

        try {
            Games.getLeaderboardsClient(this, GoogleSignIn.getLastSignedInAccount(this))
                    .submitScore(getString(R.string.leaderboard_id_speed), (long)(highScore*1000));

        }catch (Exception e){

        }


        singleton = Singleton.getInstance();
        playMusic=singleton.getPlayMusic();
        mediaPlayerCorrect = MediaPlayer.create(Classic.this , R.raw.coreect5);
        mediaPlayerFailed = MediaPlayer.create(Classic.this , R.raw.failure);
        mediaPlayerNewRecord = MediaPlayer.create(Classic.this , R.raw.highscore);


        highScoreTopNumber = findViewById(R.id.highScoreTopId);
        timeText = findViewById(R.id.timeId);
        touchText = findViewById(R.id.touchId);
        constraint = findViewById(R.id.constraintId);
        tapImageButton = findViewById(R.id.tapImageButtonId);
        timeText.setText("0");
        againSpeedButton = findViewById(R.id.againSpeedId);
        endHighScore = findViewById(R.id.speedHighScoreEndId);

        highScoreTopNumber.setText(String.format("%.3f" , highScore));


        timeStart();


        tapImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                touchText.setTextSize(40);


                againSpeedButton.setVisibility(View.VISIBLE);

                if (k) {
                    countDownTimer.cancel();


                    if (score < highScore) {
                        if (playMusic == true){
                            mediaPlayerNewRecord.start();
                        }

                        touchText.setText("New Record!");
                        endHighScore.setVisibility(View.VISIBLE);
                        endHighScore.setText(String.format("%.3f", score));
                        sharedPreferences.edit().putFloat("highScoreSpeed", score).apply();

                        kaydet();
                    } else {

                        if (playMusic==true){
                            mediaPlayerCorrect.start();
                        }

                        touchText.setText("Play Again!");
                    }
                }


                else {

                    if (playMusic==true){
                        mediaPlayerFailed.start();
                    }

                    touchText.setText("Too Early!");
                    tapImageButton.setBackgroundResource(R.drawable.blue);
                    onay=false;
                }
                playMusic=false;


            }
        });

    }

    public void timeStart(){


        Random random = new Random();
        int waitingTime = (4-random.nextInt(2))*1000;


        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                if (onay==true){

                    touchText.setTextSize(40);
                    tapImageButton.setBackgroundResource(R.drawable.green);
                    touchText.setText("NOW!");
                    k=true;

                    countDownTimer = new CountDownTimer(20000,100) {
                        @Override
                        public void onTick(long millisUntilFinished) {

                            float a = millisUntilFinished;
                            score = 20-(a/1000);

                            timeText.setText(String.format("%.3f" ,  20-(a/1000) ));
                            if (a/1000 < 0.16){
                                timeText.setText("Time Run Out!");
                                againSpeedButton.setVisibility(View.VISIBLE);
                                touchText.setText("Play Again");
                            }

                        }

                        @Override
                        public void onFinish() {

                        }
                    }.start();
                }}
        } , waitingTime);






    }


    private void kaydet(){
        try {
            Games.getLeaderboardsClient(this, GoogleSignIn.getLastSignedInAccount(Classic.this))
                    .submitScore(getString(R.string.leaderboard_id_speed), (long)(score*1000));
        }
        catch (Exception e){
        }
    }



    public void againF(View view){

        Intent intent = new Intent(Classic.this , Classic.class);
        startActivity(intent);
        finish();
    }


    @Override
    protected void onDestroy() {

        singleton.setReklam(singleton.getReklam()+1);

        super.onDestroy();
    }
}

