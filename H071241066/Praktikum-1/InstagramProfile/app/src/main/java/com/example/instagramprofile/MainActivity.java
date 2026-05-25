package com.example.instagramprofile;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private ImageView imgProfilePicture;
    private TextView tvName, tvPronoun, tvBio, tvWebsite, tvMusic, tvToolbarUsername;
    private String currentPhotoUri = "";

    private final ActivityResultLauncher<Intent> editProfileLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Intent data = result.getData();

                    String name      = data.getStringExtra("name");
                    String username  = data.getStringExtra("username");
                    String pronoun   = data.getStringExtra("pronoun");
                    String bio       = data.getStringExtra("bio");
                    String website   = data.getStringExtra("website");
                    String music     = data.getStringExtra("music");
                    String photoUri  = data.getStringExtra("photo_uri");

                    if (name != null && !name.isEmpty())
                        tvName.setText(name);

                    if (username != null && !username.isEmpty())
                        tvToolbarUsername.setText(username);

                    if (pronoun != null)
                        tvPronoun.setText(pronoun.isEmpty() ? "" : " " + pronoun);

                    if (bio != null)
                        tvBio.setText(bio);

                    if (website != null) {
                        if (website.isEmpty()) {
                            tvWebsite.setVisibility(View.GONE);
                        } else {
                            tvWebsite.setVisibility(View.VISIBLE);
                            tvWebsite.setText(website);
                        }
                    }

                    if (music != null)
                        tvMusic.setText(music);

                    if (photoUri != null && !photoUri.isEmpty()) {
                        currentPhotoUri = photoUri;
                        try {
                            Uri uri = Uri.parse(photoUri);
                            InputStream is = getContentResolver().openInputStream(uri);
                            Bitmap bmp = BitmapFactory.decodeStream(is);
                            imgProfilePicture.setImageBitmap(bmp);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgProfilePicture    = findViewById(R.id.imgProfilePicture);
        tvName               = findViewById(R.id.tvName);
        tvPronoun            = findViewById(R.id.tvPronoun);
        tvBio                = findViewById(R.id.tvBio);
        tvWebsite            = findViewById(R.id.tvWebsite);
        tvMusic              = findViewById(R.id.tvMusic);
        tvToolbarUsername    = findViewById(R.id.tvToolbarUsername);

        findViewById(R.id.btnEditProfile).setOnClickListener(v -> {
            Intent intent = new Intent(this, EditProfileActivity.class);
            intent.putExtra("name",     tvName.getText().toString());
            intent.putExtra("username", tvToolbarUsername.getText().toString());
            intent.putExtra("pronoun",  tvPronoun.getText().toString().trim());
            intent.putExtra("bio",      tvBio.getText().toString());
            intent.putExtra("website",
                    tvWebsite.getVisibility() == View.VISIBLE
                            ? tvWebsite.getText().toString() : "");
            intent.putExtra("music",    tvMusic.getText().toString());
            intent.putExtra("photo_uri", currentPhotoUri);

            editProfileLauncher.launch(intent);
        });
    }
}
