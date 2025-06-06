package com.example.transferme;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class OnboardingActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onboard1);
        Button btnNext = findViewById(R.id.btn);
        btnNext.setOnClickListener(v -> {
            startActivity(new Intent(OnboardingActivity1.this, OnboardingActivity2.class));
        });


    }

}
