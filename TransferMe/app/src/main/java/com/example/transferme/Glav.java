package com.example.transferme;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Glav extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.glavnay);

        ImageButton analyzesButton = findViewById(R.id.AnalyzesButtonMenu);
        ImageButton recordsButton = findViewById(R.id.RecordsButtonMenu);
        ImageButton basketButton = findViewById(R.id.BasketButtonMenu);
        ImageButton profileButton = findViewById(R.id.ProfileButtonMenu);

        analyzesButton.setOnClickListener(v -> {
            Intent intent = new Intent(Glav.this, Glav.class);
            startActivity(intent);
        });

        recordsButton.setOnClickListener(v -> {
            Intent intent = new Intent(Glav.this, WalletActivity.class);
            startActivity(intent);
        });

        basketButton.setOnClickListener(v -> {
            Intent intent = new Intent(Glav.this, StatActivity.class);
            startActivity(intent);
        });

        profileButton.setOnClickListener(v -> {
            Intent intent = new Intent(Glav.this, ProfileActivity.class);
            startActivity(intent);
        });
    }
}

