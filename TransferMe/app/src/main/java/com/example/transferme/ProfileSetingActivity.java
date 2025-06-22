package com.example.transferme;


import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.transferme.module.Profile;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class ProfileSetingActivity extends AppCompatActivity {


    private ImageButton avatar,btnChange;
    private EditText accountNumber, username, email, phone, address;
    private Button btnLogout, btnhistory, btnsecurity;
    private static final String PIN = "Pin";
    private static final String KEY_PIN = "saved_pin";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profileee);
        avatar = findViewById(R.id.avatarka);
        accountNumber = findViewById(R.id.accountNumber);
        username = findViewById(R.id.tvUsername);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        address = findViewById(R.id.address);
        btnLogout = findViewById(R.id.logout);
        btnhistory = findViewById(R.id.history);
        btnsecurity = findViewById(R.id.securitybtn);
        btnChange = findViewById(R.id.btnChangePassword);
        loadUserData();
           setupClickListeners();
        getProfileCard();

        ImageButton analyzesButton = findViewById(R.id.AnalyzesButtonMenu);
        ImageButton recordsButton = findViewById(R.id.RecordsButtonMenu);
        ImageButton basketButton = findViewById(R.id.BasketButtonMenu);
        ImageButton profileButton = findViewById(R.id.ProfileButtonMenu);

        analyzesButton.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileSetingActivity.this, Home.class);
            startActivity(intent);
        });

        recordsButton.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileSetingActivity.this, WalletActivityNew.class);
            startActivity(intent);
        });

        basketButton.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileSetingActivity.this, StatActivity.class);
            startActivity(intent);
        });

        profileButton.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileSetingActivity.this, ProfileSetingActivity.class);
            startActivity(intent);
        });

        btnhistory.setOnClickListener(v -> {

            Intent intent = new Intent(this, HistoryActivity.class);
            startActivity(intent);
            finish();
        });
        btnsecurity.setOnClickListener(v -> {

            Intent intent = new Intent(this, SecurityQuestionsActivity.class);
            startActivity(intent);
            finish();
        });
    }
    private void getProfileCard(){
        SupaBaseClient supaBaseClient = new SupaBaseClient();
        supaBaseClient.dataprofile(new SupaBaseClient.SBC_Callback() {
            @Override
            public void onFailure(IOException e) {
                runOnUiThread(() -> {
                    Log.e("getProfileCard:onFailure", e.getLocalizedMessage());
                });

            }

            @Override
            public void onResponse(String responseBody) {
                runOnUiThread(() -> {
                    Log.e("getProfileCard:onResponse", responseBody);
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<Profile>>(){}.getType();
                    List<Profile> profileList = gson.fromJson(responseBody, type);
                    if(profileList != null && !profileList.isEmpty()) {
                        Profile profile = profileList.get(0);
                        String url = "https://ubotxdvkhvusymbhrgvy.supabase.co/storage/v1/object/public/avatars/";
                        Glide.with(getApplicationContext())
                                .load(url + profile.getAvatar_url())
                                .placeholder(R.drawable.maaan)
                                .error(R.drawable.google)
                                .into(avatar);
                        username.setText(profile.getUsername());
                        email.setText(profile.getEmail());
                        phone.setText(profile.getPhone());
                        address.setText(profile.getAddress());
                    }
                });
            }
        });

    }
    private void loadUserData() {

        accountNumber.setEnabled(false);
        email.setEnabled(false);
        phone.setEnabled(false);
        username.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        address.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
    }

    private void setupClickListeners() {

        avatar.setOnClickListener(v -> {
            openImagePicker();
        });
        btnChange.setOnClickListener(v -> {
          Intent intent = new Intent(this, InputEmailActivity.class);
           startActivity(intent);
     });

        btnLogout.setOnClickListener(v -> logoutUser());
        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                saveUserData();
            }
        });

        address.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                saveUserData();
            }
        });
    }

    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            avatar.setImageURI(imageUri);
        }
    }

    private void saveUserData() {
        SharedPreferences.Editor editor = getSharedPreferences("UserPrefs", MODE_PRIVATE).edit();
        editor.putString("username", username.getText().toString());
        editor.putString("address", address.getText().toString());
        editor.apply();
    }

    private void logoutUser() {
        Button btnLogout = findViewById(R.id.logout);
        btnLogout.setOnClickListener(v -> {
            SharedPreferences.Editor editor = getSharedPreferences(PIN, MODE_PRIVATE).edit();editor.remove(KEY_PIN);
            editor.apply();
            SessionManager sessionManager = new SessionManager(this);
            sessionManager.logoutUser();
        });
}}