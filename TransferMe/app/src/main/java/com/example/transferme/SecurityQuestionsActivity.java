package com.example.transferme;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SecurityQuestionsActivity extends AppCompatActivity {

    private ImageButton backButton;
    private Spinner spinnerQuestion1, spinnerQuestion2, spinnerQuestion3;
    private Button btnSave;
    private List<String> securityQuestions;
    private Set<String> selectedQuestions = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.security);

        initViews();
        loadSecurityQuestions();
        setupSpinners();
        saveSecurityQuestions();
        backButton.setOnClickListener(v -> finish());

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SecurityQuestionsActivity.this,
                        "Будет доступно в следующем обновлении",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initViews() {
        backButton = findViewById(R.id.naz);
        spinnerQuestion1 = findViewById(R.id.spinnerQuestion1);
        spinnerQuestion2 = findViewById(R.id.spinnerQuestion2);
        spinnerQuestion3 = findViewById(R.id.spinnerQuestion3);

        btnSave = findViewById(R.id.btnSave);
    }

    private void loadSecurityQuestions() {
        securityQuestions = new ArrayList<>();
        securityQuestions.add("-");
        securityQuestions.add("What was your First School's name?");
        securityQuestions.add("What is your cast?");
        securityQuestions.add("Name of your place of birth?");
        securityQuestions.add("What is your elder brother's name?");
        securityQuestions.add("What is your Grandfather's name?");
        securityQuestions.add("What is your Mother's name?");
        securityQuestions.add("What was your first pet's name?");
        securityQuestions.add("What is your favorite book?");
    }

    private void setupSpinners() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                securityQuestions
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerQuestion1.setAdapter(adapter);
        spinnerQuestion2.setAdapter(adapter);
        spinnerQuestion3.setAdapter(adapter);
        addSpinnerListeners();
    }

    private void addSpinnerListeners() {
        AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateSelectedQuestions();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        };

        spinnerQuestion1.setOnItemSelectedListener(listener);
        spinnerQuestion2.setOnItemSelectedListener(listener);
        spinnerQuestion3.setOnItemSelectedListener(listener);
    }

    private void updateSelectedQuestions() {
        selectedQuestions.clear();
        addIfSelected(spinnerQuestion1);
        addIfSelected(spinnerQuestion2);
        addIfSelected(spinnerQuestion3);
    }

    private void addIfSelected(Spinner spinner) {
        if (spinner.getSelectedItemPosition() > 0) {
            selectedQuestions.add(spinner.getSelectedItem().toString());
        }
    }
    private void saveSecurityQuestions() {
        if (selectedQuestions.size() < 3) {
            Toast.makeText(this, "Please select at least 3 different questions", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this, "Security questions saved successfully", Toast.LENGTH_SHORT).show();
        finish();
    }
}
