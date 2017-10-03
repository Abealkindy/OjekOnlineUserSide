package com.rosinante24.ojekonlineuserside.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.rosinante24.ojekonlineuserside.Fonts.Fonts;
import com.rosinante24.ojekonlineuserside.Helper.SessionManager;
import com.rosinante24.ojekonlineuserside.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.text_splash)
    TextView textSplash;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        Fonts.MontserratExtraLight(this, textSplash);

        sessionManager = new SessionManager(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (sessionManager.isLogin()) {
                    startActivity(new Intent(SplashActivity.this, MainMapsActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();
                }

            }
        }, 3000);
    }
}
