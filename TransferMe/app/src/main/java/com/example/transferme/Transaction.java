package com.example.transferme;

public class  Transaction {
    public String senderName;
    public String date;
    public String amount;
    public int avatarResId;

    public Transaction(String senderName, String date, String amount, int avatarResId) {
        this.senderName = senderName;
        this.date = date;
        this.amount = amount;
        this.avatarResId = avatarResId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public int getAvatarResId() {
        return avatarResId;
    }

    public void setAvatarResId(int avatarResId) {
        this.avatarResId = avatarResId;
    }
}


