package com.example.transferme;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class SessionManager {
    private static SharedPreferences sharedPref;
    private static final String PREF_NAME = "AuthPref";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String KEY_ACCESS_TOKEN = "accessToken";
    private static final String KEY_USER_ID = "userId";
    private static final String KEY_EMAIL = "email";

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context context;

    public SessionManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    public void createSession(String accessToken, String userId, String email) {
        editor.putBoolean(KEY_IS_LOGGED_IN, true);
        editor.putString(KEY_ACCESS_TOKEN, accessToken);
        editor.putString(KEY_USER_ID, userId);
        editor.putString(KEY_EMAIL, email);
        editor.apply();
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public String getAccessToken() {
        return pref.getString(KEY_ACCESS_TOKEN, null);
    }

    public String getUserId() {
        return pref.getString(KEY_USER_ID, null);
    }

    public String getEmail() {
        return pref.getString(KEY_EMAIL, null);
    }

    public void logoutUser() {
        editor.clear();
        editor.apply();

        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
    public void clearSession() {
        SharedPreferences.Editor editor = sharedPref.edit();    editor.remove(KEY_IS_LOGGED_IN);
        editor.apply();}
}
