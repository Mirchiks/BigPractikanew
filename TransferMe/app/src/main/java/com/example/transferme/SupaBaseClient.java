package com.example.transferme;

import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.example.transferme.module.AddCard;
import com.example.transferme.module.DataBinding;
import com.example.transferme.module.LoginReguest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class SupaBaseClient {
    public interface SBC_Callback{
        public void onFailure(IOException e);
        public void onResponse(String responseBody);

    }
    private static SupaBaseClient instance;

    public static String DOMAIN_NAME = "https://guuwesagidyhlkgmzygs.supabase.co/";
    public static String REST_PATH = "rest/v1/";
    public static String AUTH_PATH = "auth/v1/";

    public static String API_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Imd1dXdlc2FnaWR5aGxrZ216eWdzIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDg5MTkxMzcsImV4cCI6MjA2NDQ5NTEzN30.ZB0EnzdfHTu9zJMzq2l0HsjDsI68-kLEFWhdbAHyjZw";
    public static String API_KEY_INSTANCE_DELETE = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Imd1dXdlc2FnaWR5aGxrZ216eWdzIiwicm9sZSI6InNlcnZpY2Vfcm9sZSIsImlhdCI6MTc0ODkxOTEzNywiZXhwIjoyMDY0NDk1MTM3fQ.O_hxrWGK2g5_K0GVY6L0af63ZDXa7lgvNPEBsO7PFNw";

    OkHttpClient client = new OkHttpClient();
    public void register(LoginReguest loginRequest, final SBC_Callback callback){
        MediaType mediaType = MediaType.parse("application/json");
        Gson gson = new Gson();
        String json = gson.toJson(loginRequest);
        RequestBody body = RequestBody.create(json, mediaType);
        Request request = new Request.Builder()
                .url(DOMAIN_NAME + AUTH_PATH + "signup")
                .method("POST", body)
                .addHeader("apikey", API_KEY )
                .addHeader("Content-Type", "application/json")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    String responseBody = response.body().string();
                    callback.onResponse(responseBody);
                }else {
                    callback.onFailure(new IOException("Ошибка сервера: " + response));
                }
            }
        });

    };
    public void login(LoginReguest loginRequest, final SBC_Callback callback){


        MediaType mediaType = MediaType.parse("application/json");
        Gson gson = new Gson();
        String json = gson.toJson(loginRequest);
        RequestBody body = RequestBody.create(json, mediaType);
        Request request = new Request.Builder()
                .url(DOMAIN_NAME + AUTH_PATH + "token?grant_type=password")
                .method("POST", body)
                .addHeader("apikey", API_KEY)
                .addHeader("Content-Type", "application/json")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    String responseBody = response.body().string();
                    callback.onResponse(responseBody);
                }else {
                    callback.onFailure(new IOException("Ошибка сервера: " + response));
                }
            }
        });

    }
    public void dataprofile(final SBC_Callback callback){
        Request request = new Request.Builder()
                .url(DOMAIN_NAME + REST_PATH + "profiles?select=*&id=eq." + DataBinding.getUuidUser())
                .addHeader("apikey", API_KEY)
                .addHeader("Authorization", DataBinding.getBearerToken())
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    String responseBody = response.body().string();
                    callback.onResponse(responseBody);
                }else {
                    callback.onFailure(new IOException("Ошибка сервера: " + response));
                }
            }
        });
    }

    public static SupaBaseClient getInstance() {
        if (instance == null) {
            instance = new SupaBaseClient();
        }
        return instance;
    }
    public void getCards(String userId, final SBC_Callback callback) {
        Request request = new Request.Builder()
                .url(DOMAIN_NAME + REST_PATH + "cards?id_user=eq." + userId)
                .addHeader("apikey", API_KEY)
                .addHeader("Authorization", "Bearer " + API_KEY)
                .addHeader("Content-Type", "application/json")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    callback.onResponse(responseBody);
                } else {
                    callback.onFailure(new IOException("Ошибка сервера: " + response.code()));
                }
            }
        });

    }
    public void fetchCategory(final SBC_Callback callback) {
        Request request = new Request.Builder()
                .url(DOMAIN_NAME + REST_PATH + "Category?select=*")
                .addHeader("apikey", API_KEY)
                .addHeader("Authorization", "Bearer " + API_KEY)
                .addHeader("Content-Type", "application/json")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    callback.onResponse(responseBody);
                } else {
                    callback.onFailure(new IOException("Ошибка сервера: " + response.code()));
                }
            }
        });

    }
    public void addCards(AddCard addCard, final SBC_Callback callback){
        MediaType mediaType = MediaType.parse("application/json");
        Gson gson = new Gson();
        String json = gson.toJson(addCard);
        RequestBody body = RequestBody.create(json, mediaType);
        Request request = new Request.Builder()
                .url(DOMAIN_NAME + REST_PATH + "cards")
                .post(body)
                .addHeader("apikey", API_KEY)
                .addHeader("Authorization", "Bearer " + API_KEY)
                .addHeader("Content-Type", "application/json")
                .addHeader("Prefer", "return=minimal")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    String responseBody = response.body().string();
                    callback.onResponse(responseBody);
                }else {
                    callback.onFailure(new IOException("Ошибка сервера: " + response));
                }
            }
        });
    }

    public void getTransactions(SBC_Callback callback) {
        Request request = new Request.Builder()
                .url(DOMAIN_NAME + REST_PATH + "history_tranz?select=*")
                .addHeader("apikey", API_KEY)
                .addHeader("Authorization", "Bearer " + API_KEY)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    callback.onResponse(response.body().string());
                } else {
                    callback.onFailure(new IOException("Ошибка сервера: " + response.code()));
                }
            }
        });
    }
    public void sendRecoveryRequest(InputEmail inputEmail, final SBC_Callback callback) {
        MediaType mediaType = MediaType.parse("application/json");
        Gson gson = new Gson();
        String json = gson.toJson(inputEmail);
        RequestBody body = RequestBody.create(json, mediaType);
        Request request = new Request.Builder()
                .url(DOMAIN_NAME + AUTH_PATH + "otp")
                .post(body)
                .addHeader("apikey", API_KEY)
                .addHeader("Content-Type", "application/json")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    String responseBody = response.body().string();
                    callback.onResponse(responseBody);
                }else {
                    callback.onFailure(new IOException("Ошибка сервера: " + response));
                }
            }
        });
    }
    public void verifyOtp(String email, String code, final SBC_Callback callback) {

        MediaType mediaType = MediaType.parse("application/json");
        String json = String.format(
                "{\"type\":\"email\",\"email\":\"%s\",\"token\":\"%s\"}",
                email, code
        );
        RequestBody body = RequestBody.create(json, mediaType);
        Request request = new Request.Builder()
                .url(DOMAIN_NAME + AUTH_PATH + "verify")
                .post(body)
                .addHeader("apikey", API_KEY_INSTANCE_DELETE)
                .addHeader("Content-Type", "application/json")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    String responseBody = response.body().string();
                    callback.onResponse(responseBody);
                }else {
                    callback.onFailure(new IOException("Ошибка сервера: " + response));
                }
            }
        });
    }
    public void updatePassword(String token, String email, String newPassword, final SBC_Callback callback) {
        MediaType mediaType = MediaType.parse("application/json");
        String json = String.format(
                "{\"email\":\"%s\",\"password\":\"%s\"}",
                email, newPassword
        );
        RequestBody body = RequestBody.create(json, mediaType);
        Request request = new Request.Builder()
                .url(DOMAIN_NAME + AUTH_PATH + "user")
                .put(body)
                .addHeader("apikey", API_KEY)
                .addHeader("Authorization", "Bearer " + token)
                .addHeader("Content-Type", "application/json")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    String responseBody = response.body().string();
                    callback.onResponse(responseBody);
                }else {
                    callback.onFailure(new IOException("Ошибка сервера: " + response));
                }
            }
        });
    }
    public void fetchAllCategoryFilters(String query, final SBC_Callback callback){
        String queryFull = (query != null && !query.isEmpty() ? "&" + query : "");
        Request request = new Request.Builder()
                .url(DOMAIN_NAME + REST_PATH + "history_tranz?select=*,Category(*)" + queryFull)
                .addHeader("apikey", API_KEY)
                .addHeader("Authorization", DataBinding.getBearerToken())
                .build();
                client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onFailure(e);
            }
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    String responseBody = response.body().string();
                    callback.onResponse(responseBody);            }else {
                    callback.onFailure(new IOException("Ошибка сервера: " + response));            }
            }    });
        }

}

