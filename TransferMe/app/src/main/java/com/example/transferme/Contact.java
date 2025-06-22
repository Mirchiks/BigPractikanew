package com.example.transferme;

public class Contact {
    private String id;
    private String fullName;
    private String phone;
    private String avatarUrl;
    private boolean isFavorite;
    private boolean isOnline;

    public Contact(String id, String fullName, String phone, String avatarUrl, boolean isFavorite, boolean isOnline) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.avatarUrl = avatarUrl;
        this.isFavorite = isFavorite;
        this.isOnline = isOnline;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
