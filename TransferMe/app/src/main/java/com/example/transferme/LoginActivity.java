package com.example.transferme;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.transferme.module.AuthResponse;
import com.example.transferme.module.DataBinding;
import com.example.transferme.module.LoginReguest;
import com.google.gson.Gson;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {

    private EditText editEmail, editPassword;
    private Button btnlogin,regButton, bntpassword;
    private SessionManager session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        session = new SessionManager(this);

        if (session.isLoggedIn()) {
            redirectToPinActivity();
            finish();
            return;
        }
        regButton = findViewById(R.id.regButton);
        editEmail = findViewById(R.id.editemail);
        editPassword = findViewById(R.id.editpassword);
        btnlogin = findViewById(R.id.log_in);
        bntpassword = findViewById(R.id.resetpassword);

        Button btnlogin = findViewById(R.id.log_in);
        btnlogin.setOnClickListener(v -> validateAndLogin());
        regButton.setOnClickListener(v -> {

            Intent intent = new Intent(this, RegistrActivity.class);
            startActivity(intent);
            finish();
        });
        bntpassword.setOnClickListener(v -> {
            Intent intent = new Intent(this, InputEmailActivity.class);
            startActivity(intent);
            finish();
        });

    }


    private void validateAndLogin() {
        String email = editEmail.getText().toString().trim();
        String password = editPassword.getText().toString().trim();

        if (email.isEmpty()) {
            editEmail.setError("Email is required");
            editEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editEmail.setError("Please enter a valid email");
            editEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            editPassword.setError("Password is required");
            editPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editPassword.setError("Password must be at least 6 characters");
            editPassword.requestFocus();
            return;
        }

        logUser(email, password);
    }

    private void logUser(String email, String password) {
        loginUser(email, password);
        Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show();

    }
    private void loginUser (String email, String password){
        SupaBaseClient supaBaseClient = new SupaBaseClient();
        LoginReguest loginlog = new LoginReguest(email, password);
        supaBaseClient.login(loginlog, new SupaBaseClient.SBC_Callback() {
            @Override
            public void onFailure(IOException e) {
                runOnUiThread(() -> {
                    Log.e("loginUser:onFailure", e.getLocalizedMessage());
                    Toast.makeText(LoginActivity.this, getString(R.string.oshibka), Toast.LENGTH_LONG).show();
                });

            }

            @Override
            public void onResponse(String responseBody) {
                runOnUiThread(() -> {
                    Log.e("loginUser:onResponse", responseBody);
                    Gson gson = new Gson();
                    AuthResponse auth = gson.fromJson(responseBody, AuthResponse.class);
                    DataBinding.saveBearerToken("Bearer " + auth.getAccess_token());
                    DataBinding.saveUuidUser(auth.getUser().getId());
//                        updateProfile(email);
//                        ASession.setLoggedIn(true)
                    SharedPreferences sharedPreferences = getSharedPreferences("Data_binding", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("uuid_user", auth.getUser().getId());
                    editor.apply();
                    session.createSession(
                            "Bearer " + auth.getAccess_token(),
                            auth.getUser().getId(),
                            email
                    );
                    redirectToPinActivity();
                });
            }
        });
    }
    private void redirectToPinActivity() {
        Intent intent = new Intent(this, PinActivity.class);
        startActivity(intent);
        finish();
    }
}

