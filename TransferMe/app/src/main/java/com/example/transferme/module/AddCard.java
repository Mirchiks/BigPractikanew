package com.example.transferme.module;

import com.google.gson.annotations.SerializedName;

public class AddCard {
        @SerializedName("card_number")
        private String card_number;
        @SerializedName("card_holder")
        private String card_holder;
        @SerializedName("expiry_date")
        private String expiry_date;
        @SerializedName("card_vendor")
        private String card_vendor;
        @SerializedName("balance")
        private String balance;
        @SerializedName("color_resources")
        private String color_resources;
        @SerializedName("id_user")
        private String id_user;


    public AddCard(String id_user, String card_number, String card_holder, String expiry_date, String card_vendor, String balance, String color_resources) {
        this.card_number = card_number;
        this.card_holder = card_holder;
        this.expiry_date = expiry_date;
        this.card_vendor = card_vendor;
        this.balance = balance;
        this.color_resources = color_resources;
        this.id_user = id_user;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public String getCard_holder() {
        return card_holder;
    }

    public void setCard_holder(String card_holder) {
        this.card_holder = card_holder;
    }

    public String getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(String expiry_date) {
        this.expiry_date = expiry_date;
    }

    public String getCard_vendor() {
        return card_vendor;
    }

    public void setCard_vendor(String card_vendor) {
        this.card_vendor = card_vendor;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getColor_resources() {
        return color_resources;
    }

    public void setColor_resources(String color_resources) {
        this.color_resources = color_resources;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }
}
