package com.example.transferme;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.Random;

public class AddCardActivity extends AppCompatActivity {
    private EditText cardNumber, cardHolder, expiryDate, cvv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcard);

        ImageButton backBtn = findViewById(R.id.naz);
        cardNumber = findViewById(R.id.CardNumber);
        cardHolder = findViewById(R.id.CardHolder);
        expiryDate = findViewById(R.id.ExpiryDate);
        cvv = findViewById(R.id.Cvv);
        Button confirmBtn = findViewById(R.id.btnConfirm);

        expiryDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 2 && before == 0 && count == 1) {
                    expiryDate.setText(s + "/");
                    expiryDate.setSelection(3);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        cardNumber.addTextChangedListener(new TextWatcher() {
            private boolean isFormatting = false;
            private int previousLength = 0;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                previousLength = s.length();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!isFormatting && count > 0 && (s.length() == 4 || s.length() == 9 || s.length() == 14)) {
                    if (before == 0) {
                        isFormatting = true;
                        String withDash = s + "-";
                        cardNumber.setText(withDash);
                        cardNumber.setSelection(withDash.length());
                        isFormatting = false;
                    }
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        backBtn.setOnClickListener(v -> finish());

        confirmBtn.setOnClickListener(v -> validateAndProceed());
    }

    private void validateAndProceed() {
        String rawCardNumber = cardNumber.getText().toString().replaceAll("[^0-9]", "");
        String cardHolderName = cardHolder.getText().toString().trim();
        String expiryText = expiryDate.getText().toString();
        String cvvText = cvv.getText().toString();
        if (rawCardNumber.length() != 16) {
            cardNumber.setError("Введите 16 цифр");
            return;
        }

        if (cardHolderName.isEmpty()) {
            cardHolder.setError("Введите имя");
            return;
        }

         if (!expiryText.matches("(0[1-9]|1[0-2])[/-][0-9]{2}")) {
             expiryDate.setError("Формат: ММ/ГГ или ММ-ГГ");
             return;}

        if (cvvText.length() < 3) {
            cvv.setError("Минимум 3 цифры");
            return;
        }

        String formattedCardNumber = formatCardNumber(rawCardNumber);
        String cardVendor = determineCardVendor(rawCardNumber);
        String balance = generateRandomBalance();

        Intent intent = new Intent(this, CardColorActivity.class);
        intent.putExtra("CARD_NUMBER", formattedCardNumber);
        intent.putExtra("CARD_HOLDER", cardHolderName);
        intent.putExtra("EXPIRY_DATE", expiryText);
        intent.putExtra("CARD_VENDOR", cardVendor);
        intent.putExtra("CARD_BALANCE", balance);
        startActivity(intent);
    }


    private String generateRandomBalance() {
        Random random = new Random();
        double balance = 100.00 + (100000.00 - 100.00) * random.nextDouble();
        return new DecimalFormat("$#,####.00").format(balance);
    }

    @NonNull
    private String formatCardNumber(String cardNumber) {
        return cardNumber.replaceAll("(.{4})", "$1 ").trim();
    }

    @NonNull
    private String determineCardVendor(String cardNumber) {
        if (cardNumber.isEmpty()) return "Visa";
        switch (cardNumber.charAt(0)) {
            case '4': return "Visa";
            case '5': return "MasterCard";
            default: return "Other";
        }
    }

}