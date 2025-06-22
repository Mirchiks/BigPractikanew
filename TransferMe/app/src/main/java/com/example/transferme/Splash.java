package com.example.transferme;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Splash extends AppCompatActivity {
    private SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        session = new SessionManager(this);

        new Handler().postDelayed(() -> {
            if (!session.isLoggedIn()) {
                session.logoutUser();
                finish();
                return;
            }else {
                Intent intent = new Intent(Splash.this, PinActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);

    }
}