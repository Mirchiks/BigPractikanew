package com.example.transferme;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.transferme.module.AddCard;
import com.example.transferme.module.Category;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class HistoryActivity extends AppCompatActivity implements ActivityFilter.FilterListener {
    private RecyclerView historyRecyclerView;
    private HistoryAdapter adapter;
    private List<TransactionCategory> transactionList = new ArrayList<>();
    private SupaBaseClient supaBaseClient;
    private Button btnfilter, btndel;
    private ImageButton btnback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);

        historyRecyclerView = findViewById(R.id.historyRecyclerView);
        btnfilter = findViewById(R.id.filterbnt);
        btnback = findViewById(R.id.naz);
        btndel = findViewById(R.id.deletebtn);
        btnback.setOnClickListener(v-> finish());
        btnfilter.setOnClickListener(v-> loadCategoryFromSupabase());
        btndel.setOnClickListener(v->getAllDelete());
        supaBaseClient = new SupaBaseClient();
        getTransactionHistory();
    }


    private void getTransactionHistory() {
        supaBaseClient.getTransactions(new SupaBaseClient.SBC_Callback() {
            @Override
            public void onFailure(IOException e) {
                runOnUiThread(() -> {
                    Log.e("HistoryActivity", "Ошибка загрузки: " + e.getMessage());
                    Toast.makeText(HistoryActivity.this,
                            "Не удалось загрузить историю", Toast.LENGTH_SHORT).show();
                });
            }

            @Override
            public void onResponse(String responseBody) {

                runOnUiThread(() -> {
                        Log.d("HistoryActivity", "Ответ сервера: " + responseBody);

                        Gson gson = new Gson();
                        Type transactionListType = new TypeToken<List<TransactionCategory>>() {
                        }.getType();
                        transactionList = gson.fromJson(responseBody, transactionListType);
                         adapter = new HistoryAdapter(transactionList, getApplicationContext());
                         historyRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                         historyRecyclerView.setAdapter(adapter);
                });
            }
        });
    }
    private void getAllDelete(){
        SupaBaseClient supaBaseClient = new SupaBaseClient();
        supaBaseClient.deleteAllHistory(new SupaBaseClient.SBC_Callback() {
            @Override
            public void onFailure(IOException e) {
                runOnUiThread(() -> {
                    Log.e("getAllBasketDelete:onFailure", e.getLocalizedMessage());
                });

            }

            @Override
            public void onResponse(String responseBody) {
                runOnUiThread(() -> {
                    Log.e("getAllBasketDelete:onResponse", responseBody);
                    getTransactionHistory();
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
                        Toast.makeText(HistoryActivity.this, "Ошибка загрузки: " + e.getMessage(), Toast.LENGTH_SHORT).show());
            }

            @Override
            public void onResponse(String responseBody) {
                runOnUiThread(() ->{
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<Category>>(){}.getType();
                    List<Category> categories = gson.fromJson(responseBody, type);
                    showFilterDialog(categories);
                });
            }
        });
    }

    @Override
    public void onFilterApplied(Set<String> selectedSpecializations) {

    }
    private void showFilterDialog(List<Category> categoriesList) {
        List<CategoryTranz> categories = new ArrayList<>();
        for (Category category : categoriesList) {
            categories.add(new CategoryTranz(
                    category.getId(),
                    category.getName()));
        }
        ActivityFilter dialog = new ActivityFilter(
                this,            categories,
                new ActivityFilter.FilterListener() {                @Override
                public void onFilterApplied(Set<String> selectedCategories) {
                    applyFilters( selectedCategories);
                } }
        );
        dialog.show();}
    private void applyFilters(Set<String> selectedCategories) {

        StringBuilder query = new StringBuilder();

        if (!selectedCategories.isEmpty()) {
            query.append("id_category=in.(")
                    .append(String.join(",", selectedCategories))
                    .append(")&");
        }
        if (query.length() > 0 && query.charAt(query.length() - 1) == '&') {        query.deleteCharAt(query.length() - 1);
        }
        loadCategoryFilter(query.toString());}
    private void loadCategoryFilter(String query) {
        SupaBaseClient supaBaseClient = new SupaBaseClient();
        supaBaseClient.fetchAllCategoryFilters(query,
                new SupaBaseClient.SBC_Callback() {

            @Override
            public void onFailure(IOException e) {
                runOnUiThread(() -> {
                    Log.e("loadAnalysesFilter:onFailure", e.getLocalizedMessage());
                });
            }
            @Override
            public void onResponse(String responseBody) {
                runOnUiThread(() -> {
                    Log.e("loadAnalysesFilter:onResponse", responseBody);
                    Gson gson = new Gson();
                    Type transactionListType = new TypeToken<List<TransactionCategory>>() {
                    }.getType();
                    transactionList = gson.fromJson(responseBody, transactionListType);
                    adapter = new HistoryAdapter(transactionList, getApplicationContext());
                    historyRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    historyRecyclerView.setAdapter(adapter);
                });        }
        });
    }
}