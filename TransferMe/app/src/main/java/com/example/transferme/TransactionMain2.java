package com.example.transferme;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TransactionMain2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.glavnay);
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Transaction(
                "Johnny Bairstow",
                "23 December 2020",
                "- $254.23",
                R.drawable.maaan));

        transactionList.add(new Transaction(
                "Jonny sfsfdsff",
                "24 December 2020",
                "- $375.50",
                R.drawable.maaan));

        RecyclerView recyclerView = findViewById(R.id.outgoingRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new TransactionAdapter(transactionList));
    }
}