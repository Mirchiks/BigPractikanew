package com.example.transferme.module;

import com.google.gson.annotations.SerializedName;

public class Category {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("avatar_category")
    private  String avatar_category;

    public Category(int id, String name, String avatar_category) {
        this.id = id;
        this.name = name;
        this.avatar_category = avatar_category;
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

    public String getAvatar_category() {
        return avatar_category;
    }

    public void setAvatar_category(String avatar_category) {
        this.avatar_category = avatar_category;
    }
}




