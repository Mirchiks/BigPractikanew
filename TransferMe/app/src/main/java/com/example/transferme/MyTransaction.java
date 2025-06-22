package com.example.transferme;

public class MyTransaction {
    private String id;
    private String poluchatel_id;
    private String otpavitel_id;
    private double amount;
    private String description;
    private String status;
    private String data;
    private int id_category;
    private int id_rate;

    public MyTransaction(String id, String poluchatel_id, String otpavitel_id, double amount, String description, String status, String data, int id_category, int id_rate) {
        this.id = id;
        this.poluchatel_id = poluchatel_id;
        this.otpavitel_id = otpavitel_id;
        this.amount = amount;
        this.description = description;
        this.status = status;
        this.data = data;
        this.id_category = id_category;
        this.id_rate = id_rate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPoluchatel_id() {
        return poluchatel_id;
    }

    public void setPoluchatel_id(String poluchatel_id) {
        this.poluchatel_id = poluchatel_id;
    }

    public String getOtpavitel_id() {
        return otpavitel_id;
    }

    public void setOtpavitel_id(String otpavitel_id) {
        this.otpavitel_id = otpavitel_id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getId_category() {
        return id_category;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }

    public int getId_rate() {
        return id_rate;
    }

    public void setId_rate(int id_rate) {
        this.id_rate = id_rate;
    }
}
