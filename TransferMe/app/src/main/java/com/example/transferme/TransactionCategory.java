package com.example.transferme;

import androidx.core.app.NotificationCompat;

import com.example.transferme.module.Category;
import com.google.gson.annotations.SerializedName;

public class TransactionCategory {
    @SerializedName("id")
    private String id;
    @SerializedName("id_category")
    private int id_category;
    @SerializedName("Category")
    private Category category;
    @SerializedName("amount")
    private String amount;


    public TransactionCategory(String id, int id_category, Category category, String amount) {
        this.id = id;
        this.id_category = id_category;
        this.category = category;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getId_category() {
        return id_category;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}