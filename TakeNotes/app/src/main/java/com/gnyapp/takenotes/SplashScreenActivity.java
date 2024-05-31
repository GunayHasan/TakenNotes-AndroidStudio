package com.gnyapp.takenotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class SplashScreenActivity extends AppCompatActivity {

    protected AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        fireSplashScreen();

        TextView appName = findViewById(R.id.title);
        appName.setText("Taken Notes");
        appName.startAnimation(fadeIn);
        fadeIn.setDuration(1500);

    }

    private void fireSplashScreen() {

        int splashScreenTimeOut = 3000;
        new Handler().postDelayed(() -> {
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(30);
            check();
            finish();
        }, splashScreenTimeOut);

    }

    private void check() {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if(account != null) {
            Intent i = new Intent(SplashScreenActivity.this, MainPageActivity.class);
            startActivity(i);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }else {
            Intent i = new Intent(SplashScreenActivity.this, MainPageActivity.class);
            startActivity(i);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    }
}