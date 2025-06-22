package com.example.transferme;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageButton avatarButton;
    private ImageButton backButton;
    private Button saveButton;
    private EditText firstNameEditText;
    private EditText lastNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        avatarButton = findViewById(R.id.avatarka);
        backButton = findViewById(R.id.naz);
        saveButton = findViewById(R.id.save_button);
        firstNameEditText = findViewById(R.id.first_name);
        lastNameEditText = findViewById(R.id.last_name);

        backButton.setOnClickListener(v -> {

            Intent intent = new Intent(this, RegistrActivity.class);
            startActivity(intent);
            finish();
        });
        avatarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImageChooser();
            }
        });
        saveButton.setOnClickListener(v -> {

            Intent intent = new Intent(this, PinActivity.class);
            startActivity(intent);
            finish();
        });
    }
    private void openImageChooser() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            avatarButton.setImageURI(imageUri);
        }
    }
    private void saveProfile() {
        String firstName = firstNameEditText.getText().toString().trim();
        String lastName = lastNameEditText.getText().toString().trim();

        if (firstName.isEmpty() || lastName.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this, "Profile saved successfully", Toast.LENGTH_SHORT).show();
        finish();
    }

}
