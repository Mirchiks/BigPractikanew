package com.example.transferme;

public class CashBack {
    public String category;
    public String time;
    public String amount;

    public CashBack(String category, String time, String amount) {
        this.category = category;
        this.time = time;
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
