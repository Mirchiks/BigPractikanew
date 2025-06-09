package com.example.transferme;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (shouldShowOnboarding()) {
            startActivity(new Intent(this, OnboardingActivity1.class));
            finish();
        } else {
            setContentView(R.layout.sing_up);

        }
    }
    private boolean shouldShowOnboarding() {
        return true;
    }
}