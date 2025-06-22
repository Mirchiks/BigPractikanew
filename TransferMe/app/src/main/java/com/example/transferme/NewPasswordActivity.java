package com.example.transferme;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class NewPasswordActivity extends AppCompatActivity {
    private EditText passwordEditText;
    private Button changePasswordButton;
    private String token, email;
    private ImageButton imageButtonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_new);

        token = getIntent().getStringExtra("access_token");
        email = getIntent().getStringExtra("email");
        passwordEditText = findViewById(R.id.editpassword);
        changePasswordButton = findViewById(R.id.newpassword);
        imageButtonBack = findViewById(R.id.nazad2);
        imageButtonBack.setOnClickListener(v -> finish());

        updateButton(false);
        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                validatePassword();
            }
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) { updateButton(validatePassword());}
        });

        changePasswordButton.setOnClickListener(v -> {
            if (validatePassword()) {
                changePassword(token, email);
            }
        });

    }
    private void updateButton(boolean isActive) {
        if(isActive == true){
            changePasswordButton.setBackgroundResource(R.drawable.button_on);
            changePasswordButton.setEnabled(true);
        }else {
            changePasswordButton.setBackgroundResource(R.drawable.button_off);
            changePasswordButton.setEnabled(false);
        }
    }
    private void changePassword(String token, String email){
        String newPassword = passwordEditText.getText().toString().trim();
        SupaBaseClient supaBaseClient = new SupaBaseClient();
        supaBaseClient.updatePassword(token,email,newPassword, new SupaBaseClient.SBC_Callback() {
            @Override
            public void onFailure(IOException e) {
                runOnUiThread(() -> {
                    Log.e("changePassword:onFailure", e.getLocalizedMessage());
                });

            }

            @Override
            public void onResponse(String responseBody) {
                runOnUiThread(() -> {
                    Log.e("changePassword:onResponse", responseBody);
                    startActivity(new Intent(NewPasswordActivity.this, LoginActivity.class));
                    finishAffinity();

                });
            }
        });
    }
    private boolean validatePassword() {
        String password = passwordEditText.getText().toString().trim();

        if (TextUtils.isEmpty(password)) {
            passwordEditText.setError("Введите пароль");
            return false;
        }

        if (password.length() < 6) {
            passwordEditText.setError("Пароль меньше 6 знаков");
            return false;
        }
        passwordEditText.setError(null);
        updateButton(true);
        return true;
    }
}
