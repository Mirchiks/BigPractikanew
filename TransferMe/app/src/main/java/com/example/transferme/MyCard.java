package com.example.transferme;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MyCard{
    @SerializedName("id_user")
    private String user_id;
    @SerializedName("card_number")
    private String cardNumber;
    @SerializedName("card_holder")
    private String cardHolder;
    @SerializedName("expiry_date")
    private String expiryDate;
    @SerializedName("card_vendor")
    private String cardVendor;
    @SerializedName("balance")
    private String balance;
    @SerializedName("color_resources")
    private String cardColor;

    public MyCard(String user_id, String cardNumber, String cardHolder, String expiryDate, String cardVendor, String balance, String cardColor) {
        this.user_id = user_id;
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.expiryDate = expiryDate;
        this.cardVendor = cardVendor;
        this.balance = balance;
        this.cardColor = cardColor;
    }


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCardVendor() {
        return cardVendor;
    }

    public void setCardVendor(String cardVendor) {
        this.cardVendor = cardVendor;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getCardColor() {
        return cardColor;
    }

    public void setCardColor(String cardColor) {
        this.cardColor = cardColor;
    }
}