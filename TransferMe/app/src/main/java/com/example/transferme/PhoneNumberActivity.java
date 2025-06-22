package com.example.transferme;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PhoneNumberActivity extends AppCompatActivity {

    private EditText phoneNumberEditText;
    private Button confirmButton;
    private ImageButton backButton;
    private SharedPreferences sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone);

        sharedPrefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);

        phoneNumberEditText = findViewById(R.id.phone_number);
        confirmButton = findViewById(R.id.save_button);
        backButton = findViewById(R.id.naz);

        backButton.setOnClickListener(v -> {
            startActivity(new Intent(this, ProfileActivity.class));
            finish();
        });
        confirmButton.setOnClickListener(v -> savePhoneNumber());
    }

    private void savePhoneNumber() {
        String phoneNumber = phoneNumberEditText.getText().toString().trim();
        if (TextUtils.isEmpty(phoneNumber)) {
            showToast("Введите номер телефона");
            return;
        }
        if (phoneNumber.length() < 10) {
            showToast("Номер слишком короткий");
            return;
        }
        sharedPrefs.edit()
                .putString("phoneNumber", phoneNumber)
                .apply();

        showToast("Номер сохранен");
        startActivity(new Intent(this, Home.class));
        finish();
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
