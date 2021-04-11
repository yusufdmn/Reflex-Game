package com.dmnstudio.reflexgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class VsActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayerNewRecord , mediaPlayerWrong , mediaPlayerCorrect;
    private Singleton singleton;
    private Boolean playMusic = true;

    private ImageButton againButton;
    int score1 , score2;
    TextView score1txt , score2txt , tapText1 , tapTtext2;
    ImageButton player1ImageButton , player2ImageButton;
    Boolean ready=false;
    Boolean bittimi = false;
    private CountDownTimer countDownTimer;
    ImageView kupa1 , kupa2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vs);



        singleton = Singleton.getInstance();
        playMusic=singleton.getPlayMusic();
        mediaPlayerCorrect = MediaPlayer.create(VsActivity.this , R.raw.coreect5);
        mediaPlayerWrong = MediaPlayer.create(VsActivity.this , R.raw.wrong);
        mediaPlayerNewRecord = MediaPlayer.create(VsActivity.this , R.raw.highscore);



        againButton = findViewById(R.id.againButtonVsId);
        score1txt = findViewById(R.id.scorePlayer1Id);
        score2txt = findViewById(R.id.scorePlayer2Id);
        player1ImageButton = findViewById(R.id.player1ImageButtonId);
        player2ImageButton = findViewById(R.id.player2ImageButtonId);
        tapText1 = findViewById(R.id.tapText1Id);
        tapTtext2 = findViewById(R.id.tapText2Id);
        kupa1 = findViewById(R.id.kupa1Id);
        kupa2 = findViewById(R.id.kupa2Id);


        play();



        againButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VsActivity.this , VsActivity.class));
                finish();
            }
        });


        player1ImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ready==true){

                    if (playMusic==true){
                        mediaPlayerCorrect.start();
                    }

                   // player1ImageButton.setImageResource(R.drawable.firstcolor);
                    player1ImageButton.setBackgroundResource(R.drawable.firstcolor);
                    //player2ImageButton.setImageResource(R.drawable.firstcolor);
                    player2ImageButton.setBackgroundResource(R.drawable.firstcolor);
                    ready=false;
                    player1ImageButton.setClickable(false);
                    player2ImageButton.setClickable(false);
                    score1++;
                    score1txt.setText(""+score1);

                    bitir();
                    play();

                }
              /*  else {

                    if (playMusic==true){
                        mediaPlayerWrong.start();
                    }

                    score1--;
                    score1txt.setText(""+score1);
                }

               */
            }
        });

        player2ImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ready==true){

                    if (playMusic==true){
                        mediaPlayerCorrect.start();
                    }

                   // player1ImageButton.setImageResource(R.drawable.firstcolor);
                    player1ImageButton.setBackgroundResource(R.drawable.firstcolor);
                    //player2ImageButton.setImageResource(R.drawable.firstcolor);
                    player2ImageButton.setBackgroundResource(R.drawable.firstcolor);

                    player1ImageButton.setClickable(false);
                    player2ImageButton.setClickable(false);
                    score2++;
                    score2txt.setText(""+score2);
                    ready=false;
                    bitir();
                    play();
                }
/*
                else {

                    if (playMusic==true){
                        mediaPlayerWrong.start();
                    }

                    score2--;
                    score2txt.setText(""+score2);
                }

 */
            }
        });

    }

    public  void play(){

        if (bittimi==false) {
            player1ImageButton.setClickable(true);
            player2ImageButton.setClickable(true);
            Random random = new Random();
            int waitingTime = (4 - random.nextInt(3)) * 1000;

            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {

                    tapText1.setTextSize(35);
                    tapTtext2.setTextSize(35);

                    tapText1.setText("Now!");
                    tapTtext2.setText("Now!");

                    ready = true;

                    player1ImageButton.setImageResource(R.drawable.green);
                    player1ImageButton.setBackgroundResource(R.drawable.green);
                    player2ImageButton.setImageResource(R.drawable.green);
                    player2ImageButton.setBackgroundResource(R.drawable.green);

                }
            }, waitingTime);
        }
}


    public void bitir(){

        tapTtext2.setText("Wait");
        tapText1.setText("Wait");

        if (score1==3){

            if (playMusic==true){
                mediaPlayerNewRecord.start();
            }

            bittimi = true;
            player1ImageButton.setClickable(false);
            player2ImageButton.setClickable(false);
            kupa1.setVisibility(View.VISIBLE);
            tapText1.setText("Won!");
            tapTtext2.setText("Lost...");
            againButton.setVisibility(View.VISIBLE);

        }
        else if (score2==3){

            if (playMusic==true){
                mediaPlayerNewRecord.start();
            }

            bittimi = true;
            player1ImageButton.setClickable(false);
            player2ImageButton.setClickable(false);
            kupa2.setVisibility(View.VISIBLE);
            tapTtext2.setText("Won!");
            tapText1.setText("Lost...");
            againButton.setVisibility(View.VISIBLE);
        }

    }

    @Override
    protected void onDestroy() {

        System.out.println("ilk reklam: " + singleton.getReklam());
        singleton.setReklam(singleton.getReklam()+1);
        System.out.println("2. reklam: " + singleton.getReklam());

        super.onDestroy();
    }

}