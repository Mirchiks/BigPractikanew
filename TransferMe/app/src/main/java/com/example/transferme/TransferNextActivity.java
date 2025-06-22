package com.example.transferme;

import static android.content.Intent.getIntent;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.transferme.module.AddCard;
import com.example.transferme.module.DataBinding;

import java.io.DataInput;
import java.io.IOException;


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
            int id_category = getIntent().getIntExtra("id_category", 0);
            String name = getIntent().getStringExtra("name");
            String avatar = getIntent().getStringExtra("avatar");
            saveTranzToSupabase(id_category, name, avatar, amount);
            if (amount.isEmpty()) {
                Toast.makeText(this, "Please enter amount", Toast.LENGTH_SHORT).show();
                return;
            }

        });

    }
    private void saveTranzToSupabase(int id_category, String name, String avatar, String amount) {
        SupaBaseClient supaBaseClient = new SupaBaseClient();
        TranzAdd tranzAdd = new TranzAdd(amount ,id_category, DataBinding.getUuidUser());
        supaBaseClient.addTransaction(tranzAdd, new SupaBaseClient.SBC_Callback() {
            @Override
            public void onFailure(IOException e) {
                runOnUiThread(() ->
                        Toast.makeText(TransferNextActivity.this, "Ошибка сохранения: " + e.getMessage(), Toast.LENGTH_SHORT).show());
            }

            @Override
            public void onResponse(String responseBody) {
                runOnUiThread(() -> {
                    Toast.makeText(TransferNextActivity.this, "Транзакция сохранена", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(TransferNextActivity.this, WalletActivityNew.class));
                    finish();
                });
            }
        });
    }
}
