package com.example.transferme;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class WalletActivity extends AppCompatActivity {
    private ImageButton btnBack;
    private Button addButton;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.wallet);

            btnBack = findViewById(R.id.naz);
            btnBack.setOnClickListener(v -> {

                Intent intent = new Intent(this, Home.class);
                startActivity(intent);
                finish();
            });
            addButton = findViewById(R.id.addcard);
            addButton.setOnClickListener(v -> {

                Intent intent = new Intent(this, AddCardActivity.class);
                startActivity(intent);
                finish();
            });

            RecyclerView cardsRecyclerView = findViewById(R.id.cardsRecyclerView);
            cardsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

            RecyclerView currencyRecyclerView = findViewById(R.id.currencyRecycle);
            currencyRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

            List<Currency> currencies = new ArrayList<>();
            currencies.add(new Currency("USD", "72.26"));
            currencies.add(new Currency("Euro", "34.48"));
            currencies.add(new Currency("Yen", "85.31"));

            CurrencyAdapter currencyAdapter = new CurrencyAdapter(currencies);
            currencyRecyclerView.setAdapter(currencyAdapter);

            RecyclerView cashBackRecyclerView = findViewById(R.id.CashBack);
            cashBackRecyclerView.setLayoutManager(new LinearLayoutManager(this));

            List<CashBack> cashBacks = new ArrayList<>();
            cashBacks.add(new CashBack("Entertainment", "4:34 PM", "$5.84"));
            cashBacks.add(new CashBack("Food Delivery", "6:07 PM", "$3.50"));
            cashBacks.add(new CashBack("Sarah", "12:23 AM", "$2.99"));

            CashBackAdapter cashBackAdapter = new CashBackAdapter(cashBacks);
            cashBackRecyclerView.setAdapter(cashBackAdapter);
        }
    }


