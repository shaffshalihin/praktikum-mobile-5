package com.example.activitydanintent;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class Detail extends AppCompatActivity { // Tambahkan extend
    private EditText etUsername;
    private ImageView ivImage;
    private Uri imageUri;

    private final ActivityResultLauncher<String> galleryLauncher = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            result -> {
                if (result != null) {
                    imageUri = result;
                    ivImage.setImageURI(imageUri);
                }
            }
    );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Hubungkan dengan layout XML detail Anda
        setContentView(R.layout.activity_detail);

        etUsername = findViewById(R.id.et_username);
        ivImage = findViewById(R.id.iv_image);
        Button btSave = findViewById(R.id.bt_save);

        ivImage.setOnClickListener(v -> galleryLauncher.launch("image/*"));
        btSave.setOnClickListener(v -> {
            if (imageUri == null){
                return;
            }
            Intent intent = new Intent();
            intent.putExtra(MainActivity.EXTRA_USERNAME, etUsername.getText().toString());
            intent.putExtra(MainActivity.EXTRA_IMAGE, imageUri.toString());
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}