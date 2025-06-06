package com.example.transferme;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class OnboardingActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onboard4);

        Button btnGetStarted = findViewById(R.id.starting);
        ImageButton dot1 = findViewById(R.id.dot1);
        ImageButton dot2 = findViewById(R.id.dot2);
        ImageButton dot3 = findViewById(R.id.dot3);


        btnGetStarted.setOnClickListener(v -> {
            SharedPreferences preferences = getSharedPreferences("app_prefs", MODE_PRIVATE);
            preferences.edit().putBoolean("onboarding_complete", true).apply();

            startActivity(new Intent(OnboardingActivity4.this, Glav.class));
            finish();
        });

        dot1.setOnClickListener(v -> {
            startActivity(new Intent(OnboardingActivity4.this, OnboardingActivity2.class));
        });
        dot2.setOnClickListener(v -> {
            startActivity(new Intent(OnboardingActivity4.this, OnboardingActivity3.class));
        });
        dot2.setOnClickListener(v -> {
            startActivity(new Intent(OnboardingActivity4.this, OnboardingActivity4.class));
        });
    }
}
