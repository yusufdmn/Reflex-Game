package com.dmnstudio.reflexgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.games.Games;

import java.util.ArrayList;
import java.util.Random;

public class TimeActivity extends AppCompatActivity {

    private InterstitialAd mInterstitialAd;


    private SharedPreferences sharedPreferences;
    private int highScore;

    MediaPlayer mediaPlayerCorrect , mediaPlayerWrong , mediaPlayerFailure , mediaPlayerRecord;

    private Boolean playMusic;
    private Singleton mySingleton;

    private LinearLayout linearLayoutTime;
    private TextView highScoreEndTime;

    private Boolean durdumu = false;
    private Boolean playSound;
    private ImageButton btn1 , btn2 , btn3 , btn4 , btn5 , btn6 , btn7, btn8 , btn9 ,btn10 , btn11 , btn12 , btn13 , btn14 , btn15 , btn16;
    private ArrayList<ImageButton> buttonArrayList;
    TextView scoreText , timeRemainText , highScoreNumberText;
    private ImageButton againTimeButton;
    private CountDownTimer countDownTimerstart , countDownTimerRemain;
    private  int index;
    private  Boolean clickable=false;
    private int score;
    private ProgressBar progressBar;
    private Handler handler2;
    private TextView endHScoreText , endHScoreNumber , endYScoreText , endYourScoreNumber , newRecordText;
    private ImageView panelTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);



        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                createId();
            }
        });




        sharedPreferences = this.getSharedPreferences("com.dmnstudio.reflexgame" , MODE_PRIVATE);
        highScore = sharedPreferences.getInt("highScoreTime" , 0);

        try {
            Games.getLeaderboardsClient(this, GoogleSignIn.getLastSignedInAccount(this))
                    .submitScore(getString(R.string.leaderboard_id_time), (long)(highScore));
        }
        catch (Exception e){

        }

        mySingleton = Singleton.getInstance();
        playMusic = mySingleton.getPlayMusic();

        mediaPlayerWrong = MediaPlayer.create(TimeActivity.this , R.raw.wrong);
        mediaPlayerCorrect = MediaPlayer.create(TimeActivity.this , R.raw.coreect5);
        mediaPlayerFailure = MediaPlayer.create(TimeActivity.this , R.raw.failure);
        mediaPlayerRecord = MediaPlayer.create(TimeActivity.this , R.raw.highscore);


        progressBar = findViewById(R.id.progresBarId);

        linearLayoutTime = findViewById(R.id.linearTimeId);
        highScoreEndTime = findViewById(R.id.newHighScoreEndTimeId);
        panelTime = findViewById(R.id.panelTimeId);
        endHScoreNumber = findViewById(R.id.timeEndHighScoreNumberId);
        endHScoreText = findViewById(R.id.timeEndHighScoreTextId);
        endYScoreText = findViewById(R.id.timeEndYourScoreTextId);
        endYourScoreNumber = findViewById(R.id.timeEndYourScoreNumberId);

        againTimeButton = findViewById(R.id.againTimeId);
        timeRemainText = findViewById(R.id.timeRemainId);
        scoreText = findViewById(R.id.scoreId);
        highScoreNumberText = findViewById(R.id.highScoreNumberTimeId);
        newRecordText = findViewById(R.id.newRecordId);

        highScoreNumberText.setText(String.valueOf(highScore));

        btn1 = findViewById(R.id.imageButto1);
        btn2 = findViewById(R.id.imageButto2);
        btn3 = findViewById(R.id.imageButto3);
        btn4 = findViewById(R.id.imageButto4);
        btn5 = findViewById(R.id.imageButto5);
        btn6 = findViewById(R.id.imageButto6);
        btn7 = findViewById(R.id.imageButto7);
        btn8 = findViewById(R.id.imageButto8);
        btn9 = findViewById(R.id.imageButto9);
        btn10 = findViewById(R.id.imageButto10);
        btn11 = findViewById(R.id.imageButto11);
        btn12 = findViewById(R.id.imageButto12);
        btn13 = findViewById(R.id.imageButto13);
        btn14 = findViewById(R.id.imageButto14);
        btn15 = findViewById(R.id.imageButto15);
        btn16 = findViewById(R.id.imageButto16);


        buttonArrayList = new ArrayList<>();

        buttonArrayList.add(btn1);
        buttonArrayList.add(btn2);
        buttonArrayList.add(btn3);
        buttonArrayList.add(btn4);
        buttonArrayList.add(btn5);
        buttonArrayList.add(btn6);
        buttonArrayList.add(btn7);
        buttonArrayList.add(btn8);
        buttonArrayList.add(btn9);
        buttonArrayList.add(btn10);
        buttonArrayList.add(btn11);
        buttonArrayList.add(btn12);
        buttonArrayList.add(btn13);
        buttonArrayList.add(btn14);
        buttonArrayList.add(btn15);
        buttonArrayList.add(btn16);


        startCountStartTime();


        againTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TimeActivity.this , TimeActivity.class));
                finish();
            }
        });



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(clickable==true){
                    if (buttonArrayList.get(index) == btn1) {
                        //   buttonArrayList.remove(index);
                        score++;
                        makeGreenTime();

                    }
                    else {
                        yanmakTime();

                    }
                    scoreText.setText(String.valueOf(score));

                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (clickable==true){
                    if (buttonArrayList.get(index) == btn2) {
                        score++;
                        makeGreenTime();
                    }
                    else {
                        yanmakTime();

                    }
                    scoreText.setText(String.valueOf(score));

                }

            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickable==true){

                    if (buttonArrayList.get(index) == btn3){
                        score++;
                        makeGreenTime();
                    }

                    else {
                        yanmakTime();
                    }
                    scoreText.setText(String.valueOf(score));
                }}
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (clickable==true){
                    if (buttonArrayList.get(index) == btn4) {
                        score++;
                        makeGreenTime();
                    }
                    else {
                        yanmakTime();

                    }
                    scoreText.setText(String.valueOf(score));
                }
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickable==true){
                    if (buttonArrayList.get(index) == btn5) {
                        score++;
                        makeGreenTime();
                    }
                    else {
                        yanmakTime();

                    }
                    scoreText.setText(String.valueOf(score));
                }
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickable==true){
                    if (buttonArrayList.get(index) == btn6) {
                        score++;
                        makeGreenTime();
                    }
                    else {
                        yanmakTime();

                    }
                    scoreText.setText(String.valueOf(score));
                }
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickable==true){
                    if (buttonArrayList.get(index) == btn7) {
                        score++;
                        makeGreenTime();
                    }
                    else {
                        yanmakTime();

                    }
                    scoreText.setText(String.valueOf(score));
                }
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickable==true){
                    if (buttonArrayList.get(index) == btn8) {
                        score++;
                        makeGreenTime();
                    }
                    else {
                        yanmakTime();

                    }
                    scoreText.setText(String.valueOf(score));
                }
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickable==true) {
                    if (buttonArrayList.get(index) == btn9) {
                        score++;
                        makeGreenTime();
                    }
                    else {
                        yanmakTime();

                    }
                    scoreText.setText(String.valueOf(score));
                }
            }
        });

        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickable==true){
                    if (buttonArrayList.get(index) == btn10){
                        score++;
                        makeGreenTime();
                    }
                    else {
                        yanmakTime();

                    }
                    scoreText.setText(String.valueOf(score));
                }}
        });

        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (clickable==true){
                    if (buttonArrayList.get(index) == btn11) {
                        score++;
                        makeGreenTime();
                    }
                    else {
                        yanmakTime();

                    }
                    scoreText.setText(String.valueOf(score));
                }
            }
        });

        btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickable==true){
                    if (buttonArrayList.get(index) == btn12) {
                        score++;
                        makeGreenTime();
                    }
                    else {
                        yanmakTime();

                    }
                    scoreText.setText(String.valueOf(score));
                }
            }
        });

        btn13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickable==true){
                    if (buttonArrayList.get(index) == btn13) {
                        score++;
                        makeGreenTime();
                    }
                    else {
                        yanmakTime();

                    }
                    scoreText.setText(String.valueOf(score));
                }
            }
        });

        btn14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickable==true){
                    if (buttonArrayList.get(index) == btn14) {
                        score++;
                        makeGreenTime();
                    }
                    else {
                        yanmakTime();

                    }
                    scoreText.setText(String.valueOf(score));
                }
            }
        });

        btn15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickable==true){
                    if (buttonArrayList.get(index) == btn15) {
                        score++;
                        makeGreenTime();
                    }
                    else {
                        yanmakTime();

                    }
                    scoreText.setText(String.valueOf(score));
                }
            }
        });

        btn16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickable==true) {
                    if (buttonArrayList.get(index) == btn16) {
                        score++;
                        makeGreenTime();
                    }
                    else {
                        yanmakTime();

                    }
                    scoreText.setText(String.valueOf(score));
                }
            }
        });


    }


    @Override
    protected void onPause() {
        durdumu = true;
        super.onPause();
    }

    @Override
    protected void onResume() {
        durdumu = false;
        super.onResume();
    }

    private void makeGreenTime(){

        if (clickable==true && playMusic == true){

            if (mediaPlayerCorrect.isPlaying()){


                mediaPlayerCorrect.seekTo(0);
/*
                mediaPlayerCorrect.stop();
                System.out.println("Stopped");
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mediaPlayerCorrect.start();

                    }
                },75); */
            }
            else {
                mediaPlayerCorrect.start();
            }
        }

        buttonArrayList.get(index).setBackgroundResource(R.drawable.redbutton);

        Random random = new Random();
        index = random.nextInt(buttonArrayList.size());

        buttonArrayList.get(index).setBackgroundResource(R.drawable.greenbutton);

    }



    private  void startCountStartTime(){

        //int progress=0;

        countDownTimerstart = new CountDownTimer(3700,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                timeRemainText.setText(String.valueOf(millisUntilFinished/1000));

            }

            @Override
            public void onFinish() {

                timeRemainText.setText("Start!");

                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        countDownTimerRemain = new CountDownTimer(10000,100) {
                            @Override
                            public void onTick(long millisUntilFinished) {

                                float a = millisUntilFinished;
                                timeRemainText.setText(String.format("%.3f" ,  a/1000 ));

                                progressBar.setProgress((int) a/100);

                            }

                            @Override
                            public void onFinish() {
                                progressBar.setProgress(0);
                                timeRemainText.setText("Finish!");
                                clickable=false;
                                bittiTime();
                            }
                        }.start();
                    }
                }, 1000);




                makeGreenTime();
                clickable=true;
            }
        }.start();

    }


    private void bittiTime(){

        if (durdumu==false && playMusic == true) {
            mediaPlayerRecord.start();
        }

        try {

            Games.getLeaderboardsClient(this, GoogleSignIn.getLastSignedInAccount(this))
                    .submitScore(getString(R.string.leaderboard_id_time), score);
        }
        catch (Exception e){
        }


        if(score > highScore){
            againTimeButton.setVisibility(View.VISIBLE);
            panelTime.setVisibility(View.VISIBLE);
            linearLayoutTime.setVisibility(View.VISIBLE);
            highScoreEndTime.setText(String.valueOf(score));

            sharedPreferences.edit().putInt("highScoreTime" , score).apply();


        }
        else{
            againTimeButton.setVisibility(View.VISIBLE);
            panelTime.setVisibility(View.VISIBLE);
            endYourScoreNumber.setVisibility(View.VISIBLE);
            endYScoreText.setVisibility(View.VISIBLE);
            endHScoreText.setVisibility(View.VISIBLE);
            endHScoreNumber.setVisibility(View.VISIBLE);

            endHScoreNumber.setText(String.valueOf(highScore));
            endYourScoreNumber.setText(scoreText.getText().toString());
        }

        timeRemainText.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);

    }


    private void yanmakTime(){

        score--;

        if (playMusic==true) {

            if (mediaPlayerWrong.isPlaying()){

                mediaPlayerWrong.seekTo(0);

              /*  mediaPlayerWrong.stop();

                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mediaPlayerWrong.start();
                    }
                },70);


               */

            }

            else{
                mediaPlayerWrong.start();

            }

        }
    }




    @Override
    protected void onDestroy() {

        mySingleton.setReklam(mySingleton.getReklam()+1);

       /* if (mInterstitialAd !=null) {
            mInterstitialAd.show(TimeActivity.this);
            System.out.println(2);
        } else {
            System.out.println(1);
            System.out.println("The interstitial wasn't loaded yet.");
        }


        */
        super.onDestroy();
    }

    private void createId() {

        AdRequest adRequest = new AdRequest.Builder().build();
        createAd(adRequest);

    }

    private void createAd(AdRequest adRequest) {

        InterstitialAd.load(this , "ca-app-pub-3940256099942544/1033173712" , adRequest , new InterstitialAdLoadCallback(){

            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                super.onAdLoaded(interstitialAd);
                mInterstitialAd = interstitialAd;

                mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                    @Override
                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                        super.onAdFailedToShowFullScreenContent(adError);
                    }

                    @Override
                    public void onAdShowedFullScreenContent() {
                        super.onAdShowedFullScreenContent();
                        mInterstitialAd=null;
                    }

                    @Override
                    public void onAdDismissedFullScreenContent() {
                        super.onAdDismissedFullScreenContent();
                    }
                });
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                mInterstitialAd = null;
            }
        });

    }





}
