package com.example.transferme;
import android.content.Intent;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.os.Bundle;
import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class InputEmailActivity extends AppCompatActivity {
    private EditText emailEditText;
    private Button sendCodeButton;
    private ImageButton imageButtonBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_recovery);

        emailEditText = findViewById(R.id.editemailpassword);
        sendCodeButton = findViewById(R.id.nextrecovery);
        imageButtonBack = findViewById(R.id.nazad);
        imageButtonBack.setOnClickListener(v -> finish());

        updateButton(false);

        emailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                validateEmail();
            }
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) { updateButton(validateEmail());}
        });

        sendCodeButton.setOnClickListener(v -> {
            if (validateEmail()) {
                sendRecoveryRequest();
            }
        });
    }
    private boolean validateEmail() {
        String email = emailEditText.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            emailEditText.setError("Введите email");
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError("Неверный формат email");
            return false;
        }

        emailEditText.setError(null);
        updateButton(true);
        return true;
    }
    private void updateButton(boolean isActive) {
        if(isActive == true){
            sendCodeButton.setBackgroundResource(R.drawable.button_on);
            sendCodeButton.setEnabled(true);
        }else {
            sendCodeButton.setBackgroundResource(R.drawable.button_off);
            sendCodeButton.setEnabled(false);
        }
    }
    private void sendRecoveryRequest(){
        String email = emailEditText.getText().toString().trim();
        SupaBaseClient supaBaseClient = new SupaBaseClient();
        InputEmail inputEmail = new InputEmail("email", email);
        supaBaseClient.sendRecoveryRequest(inputEmail, new SupaBaseClient.SBC_Callback() {
            @Override
            public void onFailure(IOException e) {
                runOnUiThread(() -> {
                    Log.e("sendRecoveryRequest:onFailure", e.getLocalizedMessage());
                });

            }

            @Override
            public void onResponse(String responseBody) {
                runOnUiThread(() -> {
                    Log.e("sendRecoveryRequest:onResponse", responseBody);
                    Intent intent = new Intent(InputEmailActivity.this, EmailCodeActivity.class);
                    intent.putExtra("email", email);
                    startActivity(intent);
                });
            }
        });
    }
}
