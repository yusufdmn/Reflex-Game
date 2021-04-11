package com.dmnstudio.reflexgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.games.Games;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {


    /*

        implementation 'com.google.android.gms:play-services-location:18.0.0'
    implementation 'com.google.android.gms:play-services-games:21.0.0'

        <meta-data android:name="com.google.android.gms.games.APP_ID"
            android:value="@string/app_id" />
        <meta-data android:name="com.google.android.gms.version"
            android:value="@integer/google_play_services_version"/>
     */
    private InterstitialAd mInterstitialAd;


    private Boolean ilkGirişMi = true;

    private static final int RC_LEADERBOARD_UI = 9004;

    private Boolean connected = false;

    private Boolean playMusic;
    private Button classicButton , speedButton , vsButton , timeButton;
    private ImageButton rateUsButton , leaderBoardButton , settingsButton , shareButton;
    private SharedPreferences sharedPreferences;

    private Singleton mySingleton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sharedPreferences = this.getSharedPreferences("com.dmnstudio.reflexgame" , MODE_PRIVATE);
        playMusic = sharedPreferences.getBoolean("playMusic" , true);

        mySingleton = Singleton.getInstance();


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                createId();
            }
        });

        System.out.println(mySingleton.getReklam());

        if (mySingleton.getReklam()>1){
            if (mInterstitialAd !=null) {
                mInterstitialAd.show(MainActivity.this);
                mySingleton.setReklam(0);
                System.out.println(2);
            } else {
                System.out.println(1);
                System.out.println("The interstitial wasn't loaded yet.");
            }
        }


        //GoogleSignInOptions signInOptions = GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN;

        try {
            GoogleSignInOptions googleSignInOptions =
                    new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN)
                            .requestScopes(Games.SCOPE_GAMES_SNAPSHOTS)
                            .build();

        }catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }

        ilkGirişMi = sharedPreferences.getBoolean("ilkGiris" , true);

  /*
        if (ilkGirişMi == true){
            startSignInIntent();
            sharedPreferences.edit().putBoolean("ilkGiris" , false).apply();
            System.out.println("İlk Giriş : " + ilkGirişMi);
        }



        Games.getLeaderboardsClient(this, GoogleSignIn.getLastSignedInAccount(this))
                .submitScore(getString(R.string.leaderboard_id), 1337);

        Games.getLeaderboardsClient(this, GoogleSignIn.getLastSignedInAccount(this))
                .submitScore(getString(R.string.leaderboard_id_time), 14);



         */








        timeButton = findViewById(R.id.timeButtonId);
        classicButton = findViewById(R.id.classicButtonId);
        speedButton = findViewById(R.id.speedButtonId);
        vsButton = findViewById(R.id.vsButtonId);
        rateUsButton = findViewById(R.id.rateUsId);
        leaderBoardButton = findViewById(R.id.leaderBoardId);
        settingsButton = findViewById(R.id.settingsId);
        shareButton = findViewById(R.id.sahreButtonId);

        musicSettings();



        leaderBoardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (connected == true){
                    try {
                        showLeaderboard();
                        System.out.println("Leader Board Açılıyor");
                    }catch (Exception e){
                        System.out.println(e.getLocalizedMessage());
                    }

                }

                else {


//                    signInSilently();
                    try {
                        startSignInIntent();
                        System.out.println("cc");
                    }catch (Exception e){
                        System.out.println(e.getLocalizedMessage());
                    }

                }
            }
        });


        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (playMusic==true){
                    playMusic = false;
                    // mySingleton.setPlayMusic(false);
                    //sharedPreferences.edit().putBoolean("playMusic" , false).apply();
                }
                else{
                    playMusic = true;
                    //   mySingleton.setPlayMusic(true);
                    // sharedPreferences.edit().putBoolean("playMusic" , true).apply();
                }

                musicSettings();
            }
        });

        rateUsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW ,
                        Uri.parse("https://play.google.com/store/apps/details?id="+getPackageName())));
            }
        });



        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                String Body="https://play.google.com/store/apps/details?id="+getPackageName();
                String icerik = "Download";
                shareIntent.putExtra(Intent.EXTRA_TEXT , Body);
                shareIntent.putExtra(Intent.EXTRA_SUBJECT , icerik);
                startActivity(Intent.createChooser(shareIntent , "Share"));

            }
        });





        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this , TimeActivity.class));
            }
        });

        classicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this , ClassicActivity.class));
            }
        });

        speedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this , Classic.class));
            }
        });

        vsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this , VsActivity.class));
            }
        });
    }



    private void musicSettings(){

        if (playMusic == true){
            settingsButton.setBackgroundResource(R.drawable.unmute);
        }
        else {
            settingsButton.setBackgroundResource(R.drawable.mute);
        }
        mySingleton.setPlayMusic(playMusic);
        sharedPreferences.edit().putBoolean("playMusic" , playMusic).apply();

    }








    private void signInSilently() {
        GoogleSignInOptions signInOptions = GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN;
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (GoogleSignIn.hasPermissions(account, signInOptions.getScopeArray())) {
            System.out.println("içerde sanırım");
            connected=true;
            // Already signed in.
            // The signed in account is stored in the 'account' variable.
            GoogleSignInAccount signedInAccount = account;
        } else {
            // Haven't been signed-in before. Try the silent sign-in first.
            GoogleSignInClient signInClient = GoogleSignIn.getClient(this, signInOptions);
            signInClient
                    .silentSignIn()
                    .addOnCompleteListener(
                            this,
                            new OnCompleteListener<GoogleSignInAccount>() {
                                @Override
                                public void onComplete(@NonNull Task<GoogleSignInAccount> task) {
                                    if (task.isSuccessful()) {
                                        System.out.println("successful");
                                        ilkGirişMi=false;
                                        connected = true;
                                        // The signed in account is stored in the task's result.
                                        GoogleSignInAccount signedInAccount = task.getResult();
                                    } else {
                                        System.out.println("başarısız");

                                        System.out.println("İlk Giriş11 : " + ilkGirişMi);

                                        if (ilkGirişMi == true) {
                                            startSignInIntent();
                                            ilkGirişMi=false;
                                            sharedPreferences.edit().putBoolean("ilkGiris", false).apply();
                                            System.out.println("İlk Giriş : " + ilkGirişMi);
                                        }

                                        connected = false;

                                        // Player will need to sign-in explicitly using via UI.
                                        // See [sign-in best practices](http://developers.google.com/games/services/checklist) for guidance on how and when to implement Interactive Sign-in,
                                        // and [Performing Interactive Sign-in](http://developers.google.com/games/services/android/signin#performing_interactive_sign-in) for details on how to implement
                                        // Interactive Sign-in.
                                    }
                                }
                            });
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                createId();
            }
        });


        if (mySingleton.getReklam()>2){
            if (mInterstitialAd !=null) {
                mInterstitialAd.show(MainActivity.this);
                mySingleton.setReklam(0);

            } else {
                System.out.println("The interstitial wasn't loaded yet.");
            }
        }

        try {
            signInSilently();

        }catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }
    }







    private void startSignInIntent() {
        System.out.println(77);
        GoogleSignInClient signInClient = GoogleSignIn.getClient(this,
                GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN);
        Intent intent = signInClient.getSignInIntent();
        startActivityForResult(intent, 1);
        System.out.println(99);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println(12);
        if (requestCode == 1) {
            System.out.println(22);
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                System.out.println(33);
                if (ilkGirişMi==false) {
                    showLeaderboard();
                }
                connected = true;
                // The signed in account is stored in the result.
                GoogleSignInAccount signedInAccount = result.getSignInAccount();
            } else {
                String message = result.getStatus().getStatusMessage();
                if (message == null || message.isEmpty()) {
                    message = "Access your account to be able to see the Leader Board";
                }
                //new AlertDialog.Builder(this).setMessage(message)
                //      .setNeutralButton(android.R.string.ok, null).show();
            }
        }
    }





    private void showLeaderboard() {



        Games.getLeaderboardsClient(this , GoogleSignIn.getLastSignedInAccount(this))   // Tüm leader boardları gösterir.
                .getAllLeaderboardsIntent()
                .addOnSuccessListener(new OnSuccessListener<Intent>() {
                    @Override
                    public void onSuccess(Intent intent) {
                        startActivityForResult(intent, RC_LEADERBOARD_UI);

                    }
                });


     /*   Games.getLeaderboardsClient(this, GoogleSignIn.getLastSignedInAccount(this))
                .getLeaderboardIntent(getString(R.string.leaderboard_id))
                .addOnSuccessListener(new OnSuccessListener<Intent>() {
                    @Override
                    public void onSuccess(Intent intent) {
                        startActivityForResult(intent, RC_LEADERBOARD_UI);
                    }
                });

        Games.getLeaderboardsClient(this, GoogleSignIn.getLastSignedInAccount(this))
                .getLeaderboardIntent(getString(R.string.leaderboard_id_time))
                .addOnSuccessListener(new OnSuccessListener<Intent>() {
                    @Override
                    public void onSuccess(Intent intent) {
                        startActivityForResult(intent, RC_LEADERBOARD_UI);
                    }

               });

      */

    }


    private void createId() {

        AdRequest adRequest = new AdRequest.Builder().build();
        createAd(adRequest);

    }

    private void createAd(AdRequest adRequest) {

        //ca-app-pub-2309141602496848/6262290244
        InterstitialAd.load(this , "ca-app-pub-2309141602496848/6262290244" , adRequest , new InterstitialAdLoadCallback(){

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

