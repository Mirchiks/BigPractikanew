package com.example.transferme;


import androidx.core.app.NotificationCompat;

import com.google.gson.annotations.SerializedName;

public class CategoryTranz {
    private int id;
    @SerializedName("name")
    private String name;

    public CategoryTranz(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}