package com.example.transferme;

import static android.content.Intent.getIntent;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class TransferNextActivity extends AppCompatActivity {
    private String selectedCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transfer_next);
        selectedCategory = getIntent().getStringExtra("category_name");

        ImageButton backButton = findViewById(R.id.naz);
        backButton.setOnClickListener(v -> finish());

        Button nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(v -> {
            EditText amountEditText = findViewById(R.id.accountAmount);
            String amount = amountEditText.getText().toString();

            if (amount.isEmpty()) {
                Toast.makeText(this, "Please enter amount", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(this, SelectedCardActivity.class);
            intent.putExtra("category_name", selectedCategory);
            intent.putExtra("amount", amount);
            startActivity(intent);
        });
    }
}
