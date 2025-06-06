package com.example.transferme;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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