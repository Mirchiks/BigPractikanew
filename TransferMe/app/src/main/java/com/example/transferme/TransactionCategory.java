package com.example.transferme;

import androidx.core.app.NotificationCompat;

import com.google.gson.annotations.SerializedName;

public class TransactionCategory {
    @SerializedName("id")
    private String id;
    @SerializedName("avatar_category")
    private String avatar_category;
    @SerializedName("amount")
    private String amount;
    @SerializedName("name")
    private String name;

    public TransactionCategory(String id, String avatar_category, String amount, String name) {
        this.id = id;
        this.avatar_category = avatar_category;
        this.amount = amount;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAvatar_category() {
        return avatar_category;
    }

    public void setAvatar_category(String avatar_category) {
        this.avatar_category = avatar_category;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}