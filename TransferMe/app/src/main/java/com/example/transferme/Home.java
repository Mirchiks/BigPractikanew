package com.example.transferme;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Arrays;
import java.util.List;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        setupCardsRecyclerView();
        setupTransactionsRecyclerView(
                R.id.incomingRecyclerView,
                Arrays.asList(
                        new Transaction("Johnny Bairstow", "23 December 2020", "+ $54.23", R.drawable.man),
                        new Transaction("Jonny sfsf", "24 December 2020", "+ $75.50", R.drawable.man)
                )
        );
        setupTransactionsRecyclerView(
                R.id.outgoingRecyclerView, Arrays.asList(
                        new Transaction("Johnny Bairstow", "23 December 2020", "- $254.23", R.drawable.maaan),
                        new Transaction("Jonny sfsfdsff", "24 December 2020", "- $375.50", R.drawable.maaan)
                )
        );
        setupBottomMenu();
    }
    private void setupCardsRecyclerView() {
        RecyclerView cardsRecyclerView = findViewById(R.id.cardRecyclerView);
        List<Card> cards = Arrays.asList(
                new Card("Visa", "mastercard", "0445", "$8.7576542","", R.drawable.mastercard),
                new Card("MasterCard", "visa", "6253", "$5.4321000","",R.drawable.mastercard)
        );
        cardsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        cardsRecyclerView.setAdapter(new CardAdapter(cards));
    }
    private void setupTransactionsRecyclerView(int recyclerViewId, List<Transaction> transactions) {
        RecyclerView recyclerView = findViewById(recyclerViewId);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new TransactionAdapter(transactions));
    }

    private void setupBottomMenu() {
        ImageButton analyzesButton = findViewById(R.id.AnalyzesButtonMenu);
        ImageButton recordsButton = findViewById(R.id.RecordsButtonMenu);
        ImageButton basketButton = findViewById(R.id.BasketButtonMenu);
        ImageButton profileButton = findViewById(R.id.ProfileButtonMenu);

        analyzesButton.setOnClickListener(v -> {
            Intent intent = new Intent(Home.this, Home.class);
            startActivity(intent);
        });

        recordsButton.setOnClickListener(v -> {
            Intent intent = new Intent(Home.this, WalletActivityNew.class);
            startActivity(intent);
        });

        basketButton.setOnClickListener(v -> {
            Intent intent = new Intent(Home.this, StatActivity.class);
            startActivity(intent);
        });

        profileButton.setOnClickListener(v -> {
            Intent intent = new Intent(Home.this, ProfileSetingActivity.class);
            startActivity(intent);
        });
    }
}

