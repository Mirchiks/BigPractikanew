package com.example.transferme;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class StatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stat);

        ImageButton analyzesButton = findViewById(R.id.AnalyzesButtonMenu);
        ImageButton recordsButton = findViewById(R.id.RecordsButtonMenu);
        ImageButton basketButton = findViewById(R.id.BasketButtonMenu);
        ImageButton profileButton = findViewById(R.id.ProfileButtonMenu);

        analyzesButton.setOnClickListener(v -> {
            Intent intent = new Intent(StatActivity.this, Home.class);
            startActivity(intent);
        });

        recordsButton.setOnClickListener(v -> {
            Intent intent = new Intent(StatActivity.this, WalletActivityNew.class);
            startActivity(intent);
        });

        basketButton.setOnClickListener(v -> {
            Intent intent = new Intent(StatActivity.this, StatActivity.class);
            startActivity(intent);
        });

        profileButton.setOnClickListener(v -> {
            Intent intent = new Intent(StatActivity.this, ProfileSetingActivity.class);
            startActivity(intent);
        });

    }
}
