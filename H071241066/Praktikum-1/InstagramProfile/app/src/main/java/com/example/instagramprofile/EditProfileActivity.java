package com.example.instagramprofile;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import java.io.InputStream;

public class EditProfileActivity extends AppCompatActivity {

    private ImageView imgProfileEdit;
    private EditText etName, etUsername, etPronoun, etBio, etWebsite, etMusic;
    private String selectedPhotoUri = "";

    private final ActivityResultLauncher<String> pickImageLauncher =
            registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
                if (uri == null) return;

                try {
                    getContentResolver().takePersistableUriPermission(
                            uri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
                } catch (Exception ignored) {}

                selectedPhotoUri = uri.toString();

                try {
                    InputStream is = getContentResolver().openInputStream(uri);
                    Bitmap bmp = BitmapFactory.decodeStream(is);
                    imgProfileEdit.setImageBitmap(bmp);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Gagal memuat foto", Toast.LENGTH_SHORT).show();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        imgProfileEdit = findViewById(R.id.imgProfileEdit);
        etName         = findViewById(R.id.etName);
        etUsername     = findViewById(R.id.etUsername);
        etPronoun      = findViewById(R.id.etPronoun);
        etBio          = findViewById(R.id.etBio);
        etWebsite      = findViewById(R.id.etWebsite);
        etMusic        = findViewById(R.id.etMusic);
        TextView tvChangePhoto  = findViewById(R.id.tvChangePhoto);
        AppCompatButton btnSave = findViewById(R.id.btnSave);

        Intent incoming = getIntent();
        etName.setText(incoming.getStringExtra("name"));
        etUsername.setText(incoming.getStringExtra("username"));
        etPronoun.setText(incoming.getStringExtra("pronoun"));
        etBio.setText(incoming.getStringExtra("bio"));
        etWebsite.setText(incoming.getStringExtra("website"));
        etMusic.setText(incoming.getStringExtra("music"));
        
        String incomingPhotoUri = incoming.getStringExtra("photo_uri");
        if (incomingPhotoUri != null && !incomingPhotoUri.isEmpty()) {
            selectedPhotoUri = incomingPhotoUri;
            try {
                Uri uri = Uri.parse(selectedPhotoUri);
                InputStream is = getContentResolver().openInputStream(uri);
                Bitmap bmp = BitmapFactory.decodeStream(is);
                imgProfileEdit.setImageBitmap(bmp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        imgProfileEdit.setOnClickListener(v -> pickImageLauncher.launch("image/*"));
        tvChangePhoto.setOnClickListener(v -> pickImageLauncher.launch("image/*"));

        btnSave.setOnClickListener(v -> {
            String name     = etName.getText().toString().trim();
            String username = etUsername.getText().toString().trim();

            if (name.isEmpty()) {
                etName.setError("Nama tidak boleh kosong");
                etName.requestFocus();
                return;
            }
            if (username.isEmpty()) {
                etUsername.setError("Username tidak boleh kosong");
                etUsername.requestFocus();
                return;
            }
            
            Intent result = new Intent();
            result.putExtra("name",      name);
            result.putExtra("username",  username);
            result.putExtra("pronoun",   etPronoun.getText().toString().trim());
            result.putExtra("bio",       etBio.getText().toString().trim());
            result.putExtra("website",   etWebsite.getText().toString().trim());
            result.putExtra("music",     etMusic.getText().toString().trim());
            result.putExtra("photo_uri", selectedPhotoUri); 
            
            setResult(RESULT_OK, result);
            finish();
        });
    }
}
