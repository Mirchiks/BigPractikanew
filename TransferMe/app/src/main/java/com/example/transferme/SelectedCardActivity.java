package com.example.transferme;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.transferme.module.AuthResponse;
import com.example.transferme.module.DataBinding;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class SelectedCardActivity extends AppCompatActivity
        implements MyCardAdapter.OnCardClickListener {

    private RecyclerView cardsRecyclerView;
    private MyCardAdapter cardAdapter;
    private ArrayList<MyCard> cards = new ArrayList<>();
    private String categoryName;
    private String amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selected_card);

        categoryName = getIntent().getStringExtra("category_name");
        amount = getIntent().getStringExtra("amount");

        ImageButton backButton = findViewById(R.id.naz);
        backButton.setOnClickListener(v -> finish());

        cardsRecyclerView = findViewById(R.id.MycardsRecyclerView);
        cardsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cardAdapter = new MyCardAdapter(cards,this);
        cardsRecyclerView.setAdapter(cardAdapter);

        loadCardsFromSupabase();
    }

    @Override
    public void onCardClick(MyCard card) {
        performTransaction(categoryName, amount, card);
    }

    private void loadCardsFromSupabase() {
        AuthResponse authResponse = getSavedAuthResponse();

        if (authResponse == null || authResponse.getAccess_token() == null) {
            Toast.makeText(this, "User not authorized", Toast.LENGTH_SHORT).show();
            return;
        }

        String userId = authResponse.getUser().getId();
        String authToken = authResponse.getAccess_token();

        SupaBaseClient.getInstance().getCards(userId, new SupaBaseClient.SBC_Callback() {
            @Override
            public void onFailure(IOException e) {
                runOnUiThread(() ->
                        Toast.makeText(SelectedCardActivity.this,
                                "Error loading cards: " + e.getMessage(),
                                Toast.LENGTH_SHORT).show());
            }

            @Override
            public void onResponse(String responseBody) {
                try {
                    JSONArray jsonArray = new JSONArray(responseBody);
                    ArrayList<MyCard> loadedCards = new ArrayList<>();

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject cardObj = jsonArray.getJSONObject(i);
                        MyCard card = new MyCard(
                                cardObj.getString(DataBinding.getUuidUser()),
                                cardObj.getString("card_number"),
                                cardObj.getString("card_holder"),
                                cardObj.getString("expiry_date"),
                                cardObj.getString("card_vendor"),
                                cardObj.getString("balance"),
                                cardObj.getString("color_resource")
                        );
                        loadedCards.add(card);
                    }

                    runOnUiThread(() -> {
                        cards.clear();
                        cards.addAll(loadedCards);
                        cardAdapter.notifyDataSetChanged();
                    });
                } catch (JSONException e) {
                    runOnUiThread(() ->
                            Toast.makeText(SelectedCardActivity.this,
                                    "Data processing error",
                                    Toast.LENGTH_SHORT).show());
                }
            }
        });
    }

    private void performTransaction(String category, String amount, MyCard card) {
        String message = String.format(
                "Payment of %s for %s from card %s",
                amount,
                category,
                card.getCardNumber()
        );

        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

        Intent resultIntent = new Intent();
        resultIntent.putExtra("transaction_success", true);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    private AuthResponse getSavedAuthResponse() {
        SharedPreferences prefs = getSharedPreferences("auth_prefs", MODE_PRIVATE);
        String authJson = prefs.getString("auth_response", null);

        if (authJson != null) {
            Gson gson = new Gson();
            return gson.fromJson(authJson, AuthResponse.class);
        }
        return null;
    }
}