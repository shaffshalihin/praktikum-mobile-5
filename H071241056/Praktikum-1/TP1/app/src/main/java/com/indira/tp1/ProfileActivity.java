package com.indira.tp1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    Button btnEditProfile;
    TextView tvName, tvUsername, headerUsername, tvBio;
    ImageView imgProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);

        btnEditProfile = findViewById(R.id.btnEditProfile);
        tvName = findViewById(R.id.tvName);
        tvUsername = findViewById(R.id.tvUsername);
        headerUsername = findViewById(R.id.headerUsername);
        tvBio = findViewById(R.id.tvBio);
        imgProfile = findViewById(R.id.imgProfile);

        btnEditProfile.setOnClickListener(v -> {

            Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);

            intent.putExtra("name", tvName.getText().toString());
            intent.putExtra("username", tvUsername.getText().toString());
            intent.putExtra("bio", tvBio.getText().toString());

            startActivityForResult(intent, 1);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {

            String name = data.getStringExtra("name");
            String username = data.getStringExtra("username");
            String bio = data.getStringExtra("bio");

            tvName.setText(name);
            tvUsername.setText(username);
            headerUsername.setText(username);
            tvBio.setText(bio);

            String imageUriString = data.getStringExtra("imageUri");

            if (imageUriString != null && !imageUriString.isEmpty()) {
                Uri imageUri = Uri.parse(imageUriString);
                imgProfile.setImageURI(imageUri);
            }
        }
    }
}