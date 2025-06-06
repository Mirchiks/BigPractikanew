package com.example.transferme;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class OnboardingActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onboard2);

        Button btnNext = findViewById(R.id.btnNext);
        ImageButton dot1 = findViewById(R.id.dot1);
        ImageButton dot2 = findViewById(R.id.dot2);
        ImageButton dot3 = findViewById(R.id.dot3);

        btnNext.setOnClickListener(v -> {
            startActivity(new Intent(OnboardingActivity2.this, OnboardingActivity3.class));
        });

        dot1.setOnClickListener(v -> {
            startActivity(new Intent(OnboardingActivity2.this, OnboardingActivity2.class));
        });
        dot2.setOnClickListener(v -> {
            startActivity(new Intent(OnboardingActivity2.this, OnboardingActivity3.class));
        });
        dot3.setOnClickListener(v -> {
            startActivity(new Intent(OnboardingActivity2.this, OnboardingActivity4.class));
        });
    }


}
