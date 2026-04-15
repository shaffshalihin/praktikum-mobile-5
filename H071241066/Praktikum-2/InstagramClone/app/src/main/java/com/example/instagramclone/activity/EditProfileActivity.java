package com.example.instagramclone.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.instagramclone.R;
import com.example.instagramclone.model.DataDummy;
import com.example.instagramclone.model.User;

public class EditProfileActivity extends AppCompatActivity {

    private EditText etName, etUsername, etPronoun, etBio, etWebsite, etMusic, etGender;
    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        currentUser = DataDummy.getInstance().getCurrentUser();

        etName = findViewById(R.id.etName);
        etUsername = findViewById(R.id.etUsername);
        etPronoun = findViewById(R.id.etPronoun);
        etBio = findViewById(R.id.etBio);
        etWebsite = findViewById(R.id.etWebsite);
        etMusic = findViewById(R.id.etMusic);
        etGender = findViewById(R.id.etGender);
        ImageView imgProfile = findViewById(R.id.imgProfileEdit);
        ImageView btnBack = findViewById(R.id.btnBack);
        Button btnSave = findViewById(R.id.btnSave);

        etName.setText(currentUser.getName());
        etUsername.setText(currentUser.getUsername());
        etPronoun.setText(currentUser.getPronoun());
        etBio.setText(currentUser.getBio());
        etWebsite.setText(currentUser.getWebsite());
        etMusic.setText(currentUser.getMusic());
        etGender.setText(currentUser.getGender());
        imgProfile.setImageResource(currentUser.getProfileImageRes());

        btnBack.setOnClickListener(v -> finish());

        btnSave.setOnClickListener(v -> {
            saveProfile();
        });
    }

    private void saveProfile() {
        String newName = etName.getText().toString().trim();
        String newUsername = etUsername.getText().toString().trim();
        
        if (newName.isEmpty() || newUsername.isEmpty()) {
            Toast.makeText(this, "Nama dan Username tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        currentUser.setName(newName);
        currentUser.setUsername(newUsername);
        currentUser.setPronoun(etPronoun.getText().toString().trim());
        currentUser.setBio(etBio.getText().toString().trim());
        currentUser.setWebsite(etWebsite.getText().toString().trim());
        currentUser.setMusic(etMusic.getText().toString().trim());
        currentUser.setGender(etGender.getText().toString().trim());

        Toast.makeText(this, "Profil diperbarui", Toast.LENGTH_SHORT).show();
        finish();
    }
}
