package com.example.transferme;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.ImageButton;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.transferme.module.DataBinding;

public class PinActivity extends AppCompatActivity {
    private static final String PIN = "Pin";
    private static final String KEY_PIN = "saved_pin";
    private static final int PIN_LENGTH = 5;

    private TextView titleTextView;
    private ImageView[] dots;
    private StringBuilder enteredPin = new StringBuilder();
    private boolean isCreatingNewPin = false;
    private String firstPin = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.pin_code);
        findViewById(R.id.naz).setOnClickListener(v -> {

            Intent intent = new Intent(this, RegistrActivity.class);
            startActivity(intent);
            finish();
        });
        titleTextView = findViewById(R.id.titletext);
        dots = new ImageView[]{
                findViewById(R.id.pin1),
                findViewById(R.id.pin2),
                findViewById(R.id.pin3),
                findViewById(R.id.pin4),
                findViewById(R.id.pin5)
        };

        SharedPreferences sharedPreferences = getSharedPreferences("Data_binding", MODE_PRIVATE);
        String bearertoken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Imd1dXdlc2FnaWR5aGxrZ216eWdzIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDg5MTkxMzcsImV4cCI6MjA2NDQ5NTEzN30.ZB0EnzdfHTu9zJMzq2l0HsjDsI68-kLEFWhdbAHyjZw";
        DataBinding.saveBearerToken("Bearer " +  bearertoken);
        String uuidUser = sharedPreferences.getString("uuid_user", "default_value");
        DataBinding.saveUuidUser(uuidUser);

        SharedPreferences prefs = getSharedPreferences(PIN, MODE_PRIVATE);
        String savePin = prefs.getString(KEY_PIN, null);



        if (savePin == null) {
            isCreatingNewPin = true;
            titleTextView.setText("Создайте пинкод");
        } else {
            titleTextView.setText("Введите пинкод");
        }

        setupNumberButtons();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setupNumberButtons() {
        int[] buttonIds = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
                R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9};

        for (int id : buttonIds) {
            Button btn = findViewById(id);
            btn.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            v.setBackgroundResource(R.drawable.button_on);
                            btn.setTextColor(getResources().getColor(R.color.white));
                            return true;
                        case MotionEvent.ACTION_UP:
                            v.setBackgroundResource(R.drawable.button_off);
                            btn.setTextColor(getResources().getColor(R.color.black));
                            if (enteredPin.length() < PIN_LENGTH) {
                                enteredPin.append(((Button)v).getText());
                                updateDots();
                                if (enteredPin.length() == PIN_LENGTH) {
                                    checkPin();
                                }
                            }
                            return true;
                        case MotionEvent.ACTION_CANCEL:
                            v.setBackgroundResource(R.drawable.button_off);
                            btn.setTextColor(getResources().getColor(R.color.black));
                            return true;
                    }
                    return false;
                }
            });
        }
    }

    private void updateDots() {
        for (int i = 0; i < dots.length; i++) {
            if (i < enteredPin.length()) {
                dots[i].setImageResource(R.drawable.button_on);
            } else {
                dots[i].setImageResource(R.drawable.button_off);
            }
        }
    }

    private void checkPin() {
        if (isCreatingNewPin) {
            if (firstPin.isEmpty()) {
                firstPin = enteredPin.toString();
                enteredPin.setLength(0);
                titleTextView.setText("Подтвердите пароль");
                updateDots();
            } else {
                if (firstPin.equals(enteredPin.toString())) {
                    savePin(firstPin);
                    proceedToMain();
                } else {
                    Toast.makeText(this, "Пинкоды не совпали", Toast.LENGTH_SHORT).show();
                    resetPin();
                }
            }
        } else {
            SharedPreferences prefs = getSharedPreferences(PIN, MODE_PRIVATE);
            String savePin = prefs.getString(KEY_PIN, "");
            if (savePin.equals(enteredPin.toString())) {
                proceedToMain();
            } else {
                Toast.makeText(this, "Неправильный пинкод попробуйте снова", Toast.LENGTH_SHORT).show();
                resetPin();
            }
        }
    }

    private void savePin(String pin) {
        SharedPreferences.Editor editor = getSharedPreferences(PIN, MODE_PRIVATE).edit();
        editor.putString(KEY_PIN, pin);
        editor.apply();
    }

    private void resetPin() {
        enteredPin.setLength(0);
        firstPin = "";
        updateDots();
        if (isCreatingNewPin) {
            titleTextView.setText("Создайте пинкод");
        }
    }
    private void proceedToMain() {

            startActivity(new Intent(this, Home.class));
            finish();
        }
    }


