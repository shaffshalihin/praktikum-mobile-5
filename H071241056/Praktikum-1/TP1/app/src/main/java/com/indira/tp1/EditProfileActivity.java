package com.indira.tp1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class EditProfileActivity extends AppCompatActivity {

    EditText etName, etUsername, etBio;
    Button btnSave;
    ImageView imgProfile;
    TextView imgEdit;
    Uri imageUri;
    String oldName, oldUsername, oldBio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        etName = findViewById(R.id.etName);
        etUsername = findViewById(R.id.etUsername);
        etBio = findViewById(R.id.etBio);
        btnSave = findViewById(R.id.btnSave);
        imgEdit = findViewById(R.id.imgEdit);
        imgProfile = findViewById(R.id.imgProfile);

        Intent intent = getIntent();
        oldName = intent.getStringExtra("name");
        oldUsername = intent.getStringExtra("username");
        oldBio = intent.getStringExtra("bio");

        etName.setText(oldName);
        etUsername.setText(oldUsername);
        etBio.setText(oldBio);

        imgEdit.setOnClickListener(v -> {

            Intent galleryIntent = new Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            );

            startActivityForResult(galleryIntent, 2);
        });

        btnSave.setOnClickListener(v -> {

            String name = etName.getText().toString();
            String username = etUsername.getText().toString();
            String bio = etBio.getText().toString();

            if (name.isEmpty()) name = oldName;
            if (username.isEmpty()) username = oldUsername;
            if (bio.isEmpty()) bio = oldBio;

            Intent resultIntent = new Intent();

            resultIntent.putExtra("name", name);
            resultIntent.putExtra("username", username);
            resultIntent.putExtra("bio", bio);

            if (imageUri != null) {
                resultIntent.putExtra("imageUri", imageUri.toString());
            }

            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2 && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            imgProfile.setImageURI(imageUri);
        }
    }
}