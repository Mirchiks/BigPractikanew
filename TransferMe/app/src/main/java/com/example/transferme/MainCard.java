package com.example.transferme;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainCard extends AppCompatActivity {
    private RecyclerView cardsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.glavnay);
        cardsRecyclerView = findViewById(R.id.cardsRecyclerView);
        List<Card> cards = new ArrayList<>();
        cards.add(new Card("Visa", "mastercard", "0445", "$8.7576542", "22", R.drawable.mastercard));
        cards.add(new Card("MasterCard", "visa", "6253", "$5.4321000", "22", R.drawable.mastercard));
        CardAdapter adapter = new CardAdapter(cards);
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                this, LinearLayoutManager.HORIZONTAL, false);
        cardsRecyclerView.setLayoutManager(layoutManager);
        cardsRecyclerView.setAdapter(adapter);
        cardsRecyclerView.setOverScrollMode(View.OVER_SCROLL_ALWAYS);
    }
}
