package com.example.transferme;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.transferme.module.AuthResponse;
import com.example.transferme.module.DataBinding;
import com.example.transferme.module.LoginReguest;
import com.google.gson.Gson;

import java.io.IOException;

public class RegistrActivity extends AppCompatActivity {
    private EditText editEmail, editPassword, editPassword2;
    private Button signUpButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sing_up);
        editEmail = findViewById(R.id.editemail);
        editPassword = findViewById(R.id.editpassword);
        editPassword2 = findViewById(R.id.editepassword2);
        signUpButton = findViewById(R.id.sing_up);

        signUpButton.setOnClickListener(v -> {
            registerUser(
                    editEmail.getText().toString().trim(),
                    editPassword.getText().toString().trim()
            );
        });

    }


        private void registerUser (String email, String password){
            SupaBaseClient supaBaseClient = new SupaBaseClient();
            LoginReguest loginRequest = new LoginReguest(email, password);
            supaBaseClient.register(loginRequest, new SupaBaseClient.SBC_Callback() {
                @Override
                public void onFailure(IOException e) {
                    runOnUiThread(() -> {
                        Log.e("registerUser:onFailure", e.getLocalizedMessage());
                        Toast.makeText(RegistrActivity.this, getString(R.string.oshibka), Toast.LENGTH_LONG).show();
                    });

                }

                @Override
                public void onResponse(String responseBody) {
                    runOnUiThread(() -> {
                        Log.e("registerUser:onResponse", responseBody);
                        Gson gson = new Gson();
                        AuthResponse auth = gson.fromJson(responseBody, AuthResponse.class);
                        DataBinding.saveBearerToken("Bearer " + auth.getAccess_token());
                        DataBinding.saveUuidUser(auth.getUser().getId());
//                        updateProfile(email);
//                        ASession.setLoggedIn(true);

                        Log.e("registerUser:onResponse", auth.getUser().getId());

                    });
                }
            });
        }

}
