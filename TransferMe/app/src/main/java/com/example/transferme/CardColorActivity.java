package com.example.transferme;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import org.json.JSONException;
import org.json.JSONObject;

import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.transferme.module.AddCard;
import com.example.transferme.module.AuthResponse;
import com.example.transferme.module.DataBinding;
import com.google.gson.Gson;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CardColorActivity extends AppCompatActivity {
    private LinearLayout cardView;
    private int selectedColorResId = R.drawable.cardcolor_blue;
    private String cardNumber, cardHolder, expiryDate, cardVendor, balance;
    private TextView cardNumberView, cardHolderView, expiryDateView, balanceView;
    private String id_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newcard);

        cardNumberView = findViewById(R.id.cardNumber);
        cardHolderView = findViewById(R.id.cardName);
        expiryDateView = findViewById(R.id.expiryDate);
        balanceView = findViewById(R.id.balanceAmount);

        initializeViews();
        setupCardData();
        setupColorChangeListeners();

        findViewById(R.id.confirmButton).setOnClickListener(v -> {
                saveCardToSupabase(cardNumber, cardHolder, expiryDate, cardVendor, balance, id_user);
        });
        findViewById(R.id.naz).setOnClickListener(v -> {

            Intent intent = new Intent(this, AddCardActivity.class);
            startActivity(intent);
            finish();
        });
    }


    private void initializeViews() {
        cardView = findViewById(R.id.cardView);
    }

    private void setupCardData() {
        Intent intent = getIntent();
        if (intent != null) {
            cardNumber = intent.getStringExtra("CARD_NUMBER");
            cardHolder = intent.getStringExtra("CARD_HOLDER");
            expiryDate = intent.getStringExtra("EXPIRY_DATE");
            cardVendor = intent.getStringExtra("CARD_VENDOR");
            balance = intent.getStringExtra("CARD_BALANCE");
            id_user = DataBinding.getUuidUser();


            cardNumberView.setText(cardNumber);
            cardHolderView.setText(cardHolder);
            expiryDateView.setText(expiryDate);
            balanceView.setText(balance);
        }
    }

    private void saveCardToSupabase(String cardNumber, String cardHolder, String expiryDate, String cardVendor, String balance, String id_user) {
        SupaBaseClient supaBaseClient = new SupaBaseClient();
        AddCard addCard = new AddCard(cardNumber, cardHolder, expiryDate, cardVendor, balance,"000000", id_user);
        supaBaseClient.addCards(addCard, new SupaBaseClient.SBC_Callback() {
            @Override
            public void onFailure(IOException e) {
                runOnUiThread(() ->
                        Toast.makeText(CardColorActivity.this, "Ошибка сохранения: " + e.getMessage(), Toast.LENGTH_SHORT).show());
            }

            @Override
            public void onResponse(String responseBody) {
                runOnUiThread(() -> {
                    Toast.makeText(CardColorActivity.this, "Карта сохранена", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(CardColorActivity.this, WalletActivityNew.class));
                    finish();
                });
            }
        });
    }

    private void setupColorChangeListeners() {
        setupColorChangeListener(R.id.colorBlue, R.drawable.cardcolor_blue);
        setupColorChangeListener(R.id.colorRed, R.drawable.cardcolor_red);
        setupColorChangeListener(R.id.colorGreen, R.drawable.cardcolor_green);
        setupColorChangeListener(R.id.colorPurple, R.drawable.cardcolor_purple);
    }

    private void setupColorChangeListener(int colorViewId, final int colorResId) {
        ImageView colorView = findViewById(colorViewId);
        colorView.setOnClickListener(v -> {
            cardView.animate()
                    .scaleX(0.95f)
                    .scaleY(0.95f)
                    .setDuration(100)
                    .withEndAction(() -> {
                        cardView.setBackgroundResource(colorResId);
                        cardView.animate()
                                .scaleX(1f)
                                .scaleY(1f)
                                .setDuration(100)
                                .start();
                    })
                    .start();
            selectedColorResId = colorResId;
        });
    }
}

