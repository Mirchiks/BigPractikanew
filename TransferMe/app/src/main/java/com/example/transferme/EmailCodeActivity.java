package com.example.transferme;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class EmailCodeActivity extends AppCompatActivity {
    private EditText editpin;
    private Button sendCodeButton;
    private ImageButton imageButtonBack;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_pin);
        editpin = findViewById(R.id.editpincode);
        sendCodeButton = findViewById(R.id.next);
        imageButtonBack = findViewById(R.id.nazad1);
        imageButtonBack.setOnClickListener(v -> finish());
        email = getIntent().getStringExtra("email");
        sendCodeButton.setOnClickListener(v -> verifyCode(email));
    }
    private void verifyCode(String email) {

        String code = editpin.getText().toString().trim();

        SupaBaseClient supaBaseClient = new SupaBaseClient();
        supaBaseClient.verifyOtp(email, code, new SupaBaseClient.SBC_Callback() {
            @Override
            public void onFailure(IOException e) {
                runOnUiThread(() -> {
                    Log.e("verifyCode:onFailure", e.getLocalizedMessage());
                    Toast.makeText(EmailCodeActivity.this, "Не правильный пин-код", Toast.LENGTH_LONG).show();
                });

            }

            @Override
            public void onResponse(String responseBody) {
                runOnUiThread(() -> {
                    Log.e("verifyCode:onResponse", responseBody);
                    String token = extractAccessToken(responseBody);
                    Intent intent = new Intent(EmailCodeActivity.this, NewPasswordActivity.class);
                    intent.putExtra("access_token", token);
                    intent.putExtra("email", email);
                    startActivity(intent);


                });
            }
        });

    }
    private String extractAccessToken(String json) {
        try {
            JSONObject obj = new JSONObject(json);
            return obj.getString("access_token");
        } catch (JSONException e) {
            return "";
        }
    }
}
