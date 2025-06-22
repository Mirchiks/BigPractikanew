package com.example.transferme;

public class Card {
    public String name;
    public String type;
    public String number;
    public String balance;
    public int imageRes;

    public Card(String name, String type, String number, String balance, String s, int imageRes) {
        this.name = name;
        this.type = type;
        this.number = number;
        this.balance = balance;
        this.imageRes = imageRes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }
}

