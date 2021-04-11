package com.dmnstudio.reflexgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.games.Games;

import java.security.spec.ECField;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class ClassicActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    private LinearLayout linearLayout;
    MediaPlayer mediaPlayerCorrect , mediaPlayerWrong , mediaPlayerFailure , mediaPlayerRecord;
    private  Boolean playMusic;
    private Singleton mySingleton;
    private ImageButton btn1 , btn2 , btn3 , btn4 , btn5 , btn6 , btn7, btn8 , btn9 ,btn10 , btn11 , btn12 , btn13 , btn14 , btn15 , btn16;
    private ArrayList<ImageButton> buttonArrayList;
    TextView timerText , highScoreText , startTimerText;
    private int index;
    private CountDownTimer countDownTimer , countDownTimerStart;
    Boolean clickable=false;
    private ImageButton againButton;
    private ImageView panel;
    private TextView endHighNumberText , endScoreNumberText , endHighYazi , endYourScoreYazi;
    private TextView highScoreNumber , tryAgainId , highScoreEndNumber;
    private  float score,highScore;
    private double scoree;
    private Boolean durdumu = false;
    private int pp;
    private static DecimalFormat df = new DecimalFormat("0.000");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classic2);


        sharedPreferences = this.getSharedPreferences("com.dmnstudio.reflexgame" , MODE_PRIVATE);
        highScore = sharedPreferences.getFloat("highScore" , 100);
        score=0f;

        try {
            Games.getLeaderboardsClient(this, GoogleSignIn.getLastSignedInAccount(this))
                    .submitScore(getString(R.string.leaderboard_id), (long)(highScore*1000));

        }catch (Exception e){

        }


/*

        Games.getLeaderboardsClient(this, GoogleSignIn.getLastSignedInAccount(this))
                .submitScore(getString(R.string.leaderboard_id), (long)(score*1000));

 */


        mySingleton = Singleton.getInstance();
        playMusic = mySingleton.getPlayMusic();
        System.out.println(playMusic);


        mediaPlayerWrong = MediaPlayer.create(ClassicActivity.this , R.raw.wrong);
        mediaPlayerCorrect = MediaPlayer.create(ClassicActivity.this , R.raw.coreect5);
        mediaPlayerFailure = MediaPlayer.create(ClassicActivity.this , R.raw.failure);
        mediaPlayerRecord = MediaPlayer.create(ClassicActivity.this , R.raw.highscore);


        linearLayout = findViewById(R.id.linearId);
        highScoreEndNumber = findViewById(R.id.highScoreendNumberId);
        panel = findViewById(R.id.panelId);
        endScoreNumberText = findViewById(R.id.ypurScoreNumberEndId);
        endHighNumberText = findViewById(R.id.highNumberEndId);
        endYourScoreYazi = findViewById(R.id.yourScoreTextId);
        endHighYazi = findViewById(R.id.highTextEndId);
        tryAgainId = findViewById(R.id.tryAgainId);
        highScoreNumber = findViewById(R.id.highScoreNumberId);

        timerText = findViewById(R.id.timerClassicId);
        highScoreText = findViewById(R.id.highScoreYaziId);
        startTimerText = findViewById(R.id.stratTimerId);

        againButton = findViewById(R.id.againButtonId);

        btn1 = findViewById(R.id.imageButton1);
        btn2 = findViewById(R.id.imageButton2);
        btn3 = findViewById(R.id.imageButton3);
        btn4 = findViewById(R.id.imageButton4);
        btn5 = findViewById(R.id.imageButton5);
        btn6 = findViewById(R.id.imageButton6);
        btn7 = findViewById(R.id.imageButton7);
        btn8 = findViewById(R.id.imageButton8);
        btn9 = findViewById(R.id.imageButton9);
        btn10 = findViewById(R.id.imageButton10);
        btn11 = findViewById(R.id.imageButton11);
        btn12 = findViewById(R.id.imageButton12);
        btn13 = findViewById(R.id.imageButton13);
        btn14 = findViewById(R.id.imageButton14);
        btn15 = findViewById(R.id.imageButton15);
        btn16 = findViewById(R.id.imageButton16);


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

        highScoreNumber.setText(String.format("%.3f" , highScore));

        startCountStartTime();



        againButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClassicActivity.this , ClassicActivity.class);
                startActivity(intent);
                finish();
            }
        });



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(clickable==true){
                    if (buttonArrayList.get(index) == btn1) {
                        btn1.setVisibility(View.INVISIBLE);
                        buttonArrayList.remove(index);
                        makeGreen();
                    }
                    else {
                        yanmakClassic();


                    }

                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (clickable==true){

                    if (buttonArrayList.get(index) == btn2) {
                        btn2.setVisibility(View.INVISIBLE);
                        buttonArrayList.remove(index);
                        makeGreen();
                    }

                    else {
                        yanmakClassic();


                    }
                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickable==true){

                    if (buttonArrayList.get(index) == btn3){
                        btn3.setVisibility(View.INVISIBLE);
                        buttonArrayList.remove(index);
                        makeGreen();
                    }

                    else {
                        yanmakClassic();

                    }
                }
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (clickable==true){
                    if (buttonArrayList.get(index) == btn4) {
                        btn4.setVisibility(View.INVISIBLE);
                        buttonArrayList.remove(index);
                        makeGreen();
                    }
                    else {
                        yanmakClassic();


                    }
                }
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickable==true){
                    if (buttonArrayList.get(index) == btn5) {
                        btn5.setVisibility(View.INVISIBLE);
                        buttonArrayList.remove(index);
                        makeGreen();
                    }
                    else {
                        yanmakClassic();


                    }
                }
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickable==true){
                    if (buttonArrayList.get(index) == btn6) {
                        btn6.setVisibility(View.INVISIBLE);
                        buttonArrayList.remove(index);
                        makeGreen();
                    }
                    else {
                        yanmakClassic();


                    }
                }
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickable==true){
                    if (buttonArrayList.get(index) == btn7) {
                        btn7.setVisibility(View.INVISIBLE);
                        buttonArrayList.remove(index);
                        makeGreen();
                    }
                    else {
                        yanmakClassic();


                    }
                }
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickable==true){
                    if (buttonArrayList.get(index) == btn8) {
                        btn8.setVisibility(View.INVISIBLE);
                        buttonArrayList.remove(index);
                        makeGreen();
                    }
                    else {
                        yanmakClassic();


                    }
                }
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickable==true) {
                    if (buttonArrayList.get(index) == btn9) {
                        btn9.setVisibility(View.INVISIBLE);
                        buttonArrayList.remove(index);
                        makeGreen();
                    }
                    else {
                        yanmakClassic();


                    }
                }
            }
        });

        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickable == true) {
                    if (buttonArrayList.get(index) == btn10) {
                        btn10.setVisibility(View.INVISIBLE);
                        buttonArrayList.remove(index);
                        makeGreen();

                    } else {
                        yanmakClassic();


                    }
                }
            }
        });

        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (clickable==true){
                    if (buttonArrayList.get(index) == btn11) {
                        btn11.setVisibility(View.INVISIBLE);
                        buttonArrayList.remove(index);
                        makeGreen();
                    }
                    else {
                        yanmakClassic();


                    }
                }
            }
        });

        btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickable==true){
                    if (buttonArrayList.get(index) == btn12) {
                        btn12.setVisibility(View.INVISIBLE);
                        buttonArrayList.remove(index);
                        makeGreen();
                    }
                    else {
                        yanmakClassic();


                    }
                }
            }
        });

        btn13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickable==true){
                    if (buttonArrayList.get(index) == btn13) {
                        btn13.setVisibility(View.INVISIBLE);
                        buttonArrayList.remove(index);
                        makeGreen();
                    }
                    else {
                        yanmakClassic();


                    }
                }
            }
        });

        btn14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickable==true){
                    if (buttonArrayList.get(index) == btn14) {
                        btn14.setVisibility(View.INVISIBLE);
                        buttonArrayList.remove(index);
                        makeGreen();
                    }
                    else {
                        yanmakClassic();


                    }
                }
            }
        });

        btn15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickable==true){
                    if (buttonArrayList.get(index) == btn15) {
                        btn15.setVisibility(View.INVISIBLE);
                        buttonArrayList.remove(index);
                        makeGreen();
                    }
                    else {
                        yanmakClassic();


                    }
                }
            }
        });

        btn16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickable==true) {
                    if (buttonArrayList.get(index) == btn16) {
                        btn16.setVisibility(View.INVISIBLE);
                        buttonArrayList.remove(index);
                        makeGreen();
                    }
                    else {
                        yanmakClassic();

                    }
                }
            }
        });



    }


    @Override
    protected void onPause() {
        durdumu=true;
        super.onPause();
    }

    @Override
    protected void onResume() {
        durdumu=false;
        super.onResume();
    }



    private void makeGreen(){

        if (buttonArrayList.size()<16 && playMusic == true){

            if (mediaPlayerCorrect.isPlaying()){


                mediaPlayerCorrect.seekTo(0);

                /* mediaPlayerCorrect.stop();
                System.out.println("Stopped");
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mediaPlayerCorrect.start();

                    }
                },75);


                */
            }
            else {
                mediaPlayerCorrect.start();
            }

        }

        if (buttonArrayList.size()>0) {

            Random random = new Random();
            index = random.nextInt(buttonArrayList.size());
            buttonArrayList.get(index).setBackgroundResource(R.drawable.greenbutton);
        }
        else {
            bittiClassic();
        }


    }




    private  void startCountStartTime(){



        countDownTimerStart = new CountDownTimer(3700,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                startTimerText.setText(String.valueOf(millisUntilFinished/1000));

            }

            @Override
            public void onFinish() {
                startTimerText.setText("Start!");
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startTimerText.setVisibility(View.INVISIBLE);
                    }
                }, 1000);


                countDownTimer = new CountDownTimer(50000,100) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                        float a = millisUntilFinished;
                        score = 50-(a/1000) ;
                        timerText.setText(String.format("%.3f" ,  50-(a/1000) ));

                    }

                    @Override
                    public void onFinish() {
                        timerText.setText("Time Run Out!");
                        yanmakClassic();
                    }
                }.start();

                makeGreen();
                clickable=true;
            }
        }.start();

    }


    private void yanmakClassic(){


        if (durdumu==false && playMusic==true) {
            mediaPlayerFailure.start();
        }

        countDownTimer.cancel();
        //timerText.setText("Try Again");
        startTimerText.setVisibility(View.INVISIBLE);
        againButton.setVisibility(View.VISIBLE);
        clickable=false;
        panel.setVisibility(View.VISIBLE);
//        endHighYazi.setVisibility(View.VISIBLE);
        //      endYourScoreYazi.setVisibility(View.VISIBLE);
        //    endScoreNumberText.setVisibility(View.VISIBLE);
        //  endHighNumberText.setVisibility(View.VISIBLE);
        //endScoreNumberText.setText(timerText.getText().toString());

        tryAgainId.setVisibility(View.VISIBLE);

    }


    private void bittiClassic(){


        try {



            if (durdumu==false && playMusic==true) {
                mediaPlayerRecord.start();
            }

            countDownTimer.cancel();

            //  score = Double.valueOf(timerText.getText().toString());


            try {

                Games.getLeaderboardsClient(this, GoogleSignIn.getLastSignedInAccount(this))
                        .submitScore(getString(R.string.leaderboard_id), (long)(score*1000));
            }
            catch (Exception e){
            }

            if (score < highScore){
                sharedPreferences.edit().putFloat("highScore" ,score).apply();
                panel.setVisibility(View.VISIBLE);
                highScoreEndNumber.setText(String.format("%.3f" , score));
                linearLayout.setVisibility(View.VISIBLE);

            }
            else {
                panel.setVisibility(View.VISIBLE);
                endHighYazi.setVisibility(View.VISIBLE);
                endYourScoreYazi.setVisibility(View.VISIBLE);
                endScoreNumberText.setVisibility(View.VISIBLE);
                endHighNumberText.setVisibility(View.VISIBLE);
                endScoreNumberText.setText(timerText.getText().toString());
                endHighNumberText.setText(String.format("%.3f" , highScore));
            }

            againButton.setVisibility(View.VISIBLE);
            clickable=false;

        }

        catch (Exception e){
        }





    }






    @Override
    protected void onDestroy() {

        mySingleton.setReklam(mySingleton.getReklam()+1);

        super.onDestroy();
    }
}


