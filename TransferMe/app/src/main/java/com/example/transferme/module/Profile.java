package com.example.transferme.module;

public class Profile {
    private String id;
    private String email;
    private String username;
    private String phone;
    private String address;

    private String avatar_url;

    public Profile(String id, String email, String username, String phone, String address, String avatar_url) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.phone = phone;
        this.address = address;
        this.avatar_url = avatar_url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }
}

