package com.example.transferme;

import com.google.gson.annotations.SerializedName;

public class TranzAdd {
    @SerializedName("amount")
    private String amount;
    @SerializedName("id_category")
    private int id_category;
    @SerializedName("id_profiles")
    private String id_profiles;

    public TranzAdd(String amount, int id_category, String id_profiles) {
        this.amount = amount;
        this.id_category = id_category;
        this.id_profiles = id_profiles;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public int getId_category() {
        return id_category;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }

    public String getId_profiles() {
        return id_profiles;
    }

    public void setId_profiles(String id_profiles) {
        this.id_profiles = id_profiles;
    }
}


