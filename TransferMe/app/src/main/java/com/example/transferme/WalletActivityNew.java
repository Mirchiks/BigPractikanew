package com.example.transferme;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.transferme.module.AuthResponse;
import com.example.transferme.module.Category;
import com.example.transferme.module.DataBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class WalletActivityNew extends AppCompatActivity implements MyCardAdapter.OnCardClickListener{
    private RecyclerView MycardsRecyclerView;
    private MyCardAdapter cardAdapter;
    private CategoriaAdapter categoriaAdapter;
    private ImageButton addcard;
    private RecyclerView categoryRelative;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wallet_new);

        addcard = findViewById(R.id.addcard);
        addcard.setOnClickListener(v ->{
            startActivity(new Intent(WalletActivityNew.this, AddCardActivity.class));
        });
        categoryRelative = findViewById(R.id.categoryRelative);
        String userId = DataBinding.getUuidUser();

        String authToken = DataBinding.getBearerToken();

        setupBottomNavigation();
        setupRecyclerView();
        loadCategoryFromSupabase();
        loadCardsFromSupabase(userId);
    }

    private void setupBottomNavigation() {
        ImageButton analyzesButton = findViewById(R.id.AnalyzesButtonMenu);
        ImageButton recordsButton = findViewById(R.id.RecordsButtonMenu);
        ImageButton basketButton = findViewById(R.id.BasketButtonMenu);
        ImageButton profileButton = findViewById(R.id.ProfileButtonMenu);

        analyzesButton.setOnClickListener(v -> startActivity(new Intent(this, Home.class)));
        recordsButton.setOnClickListener(v -> startActivity(new Intent(this, WalletActivityNew.class)));
        basketButton.setOnClickListener(v -> startActivity(new Intent(this, StatActivity.class)));
        profileButton.setOnClickListener(v -> startActivity(new Intent(this, ProfileSetingActivity.class)));
    }

    private void setupRecyclerView() {
        MycardsRecyclerView = findViewById(R.id.MycardsRecyclerView);
    }

    private void loadCardsFromSupabase(String userId) {
        SupaBaseClient supaBaseClient = new SupaBaseClient();
        supaBaseClient.getCards(userId, new SupaBaseClient.SBC_Callback() {
            @Override
            public void onFailure(IOException e) {
                runOnUiThread(() ->
                        Toast.makeText(WalletActivityNew.this, "Ошибка загрузки: " + e.getMessage(), Toast.LENGTH_SHORT).show());
            }

            @Override
            public void onResponse(String responseBody) {
                runOnUiThread(() ->{
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<MyCard>>(){}.getType();
                    List<MyCard> myCards = gson.fromJson(responseBody, type);
                    cardAdapter = new MyCardAdapter(myCards, WalletActivityNew.this);
                    MycardsRecyclerView.setAdapter(cardAdapter);
                    MycardsRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        });
            }
        });
    }

    private void loadCategoryFromSupabase() {
        SupaBaseClient supaBaseClient = new SupaBaseClient();
        supaBaseClient.fetchCategory( new SupaBaseClient.SBC_Callback() {
            @Override
            public void onFailure(IOException e) {
                runOnUiThread(() ->
                        Toast.makeText(WalletActivityNew.this, "Ошибка загрузки: " + e.getMessage(), Toast.LENGTH_SHORT).show());
            }

            @Override
            public void onResponse(String responseBody) {
                runOnUiThread(() ->{
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<Category>>(){}.getType();
                    List<Category> categories = gson.fromJson(responseBody, type);
                    categoriaAdapter = new CategoriaAdapter(getApplicationContext(), categories);
                    categoryRelative.setAdapter(categoriaAdapter);
                    categoryRelative.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
                });
            }
        });
    }

    @Override
    public void onCardClick(MyCard card) {

    }
}