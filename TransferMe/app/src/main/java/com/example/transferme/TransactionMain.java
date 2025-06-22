package com.example.transferme;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TransactionMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.glavnay);
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Transaction(
                "Johnny Bairstow",
                "23 December 2020",
                "+ $54.23",
                R.drawable.man));

        transactionList.add(new Transaction(
                "Jonny sfsf",
                "24 December 2020",
                "+ $75.50",
                R.drawable.man));

        RecyclerView recyclerView = findViewById(R.id.incomingRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new TransactionAdapter(transactionList));
    }
}
