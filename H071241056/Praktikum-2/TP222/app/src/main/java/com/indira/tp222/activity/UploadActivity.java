package com.indira.tp222.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.indira.tp222.R;
import com.indira.tp222.model.DataSource;
import com.indira.tp222.model.Post;

public class UploadActivity extends AppCompatActivity {

    private ImageView ivPreview, ivClose;
    private TextView tvNoImage;
    private EditText etCaption;
    private Button btnSelectImage, btnUpload;
    private Uri selectedImageUri;

    private final ActivityResultLauncher<String> galleryLauncher = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            uri -> {
                if (uri != null) {
                    // Ambil izin akses permanen (READ)
                    final int takeFlags = Intent.FLAG_GRANT_READ_URI_PERMISSION;
                    try {
                        getContentResolver().takePersistableUriPermission(uri, takeFlags);
                    } catch (SecurityException e) {
                        e.printStackTrace();
                    }

                    selectedImageUri = uri;
                    ivPreview.setImageURI(uri);
                    ivPreview.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    tvNoImage.setVisibility(View.GONE);
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        ivPreview = findViewById(R.id.ivPreview);
        ivClose = findViewById(R.id.ivClose);
        tvNoImage = findViewById(R.id.tvNoImage);
        etCaption = findViewById(R.id.etCaption);
        btnSelectImage = findViewById(R.id.btnSelectImage);
        btnUpload = findViewById(R.id.btnUpload);

        ivClose.setOnClickListener(v -> finish());

        btnSelectImage.setOnClickListener(v -> galleryLauncher.launch("image/*"));

        btnUpload.setOnClickListener(v -> {
            String caption = etCaption.getText().toString().trim();

            if (caption.isEmpty()) {
                Toast.makeText(this, "Please add a caption", Toast.LENGTH_SHORT).show();
                return;
            }

            if (selectedImageUri == null) {
                Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT).show();
                return;
            }

            // Simpan URI sebagai string
            String imageUriString = selectedImageUri.toString();

            // Debug
            android.util.Log.d("UPLOAD", "Saving URI: " + imageUriString);
            android.util.Log.d("UPLOAD", "Caption: " + caption);

            // Buat post baru
            Post newPost = new Post(imageUriString, caption);
            DataSource.addProfilePost(newPost);

            // Debug: cek jumlah post setelah upload
            android.util.Log.d("UPLOAD", "Total posts: " + DataSource.getProfilePosts().size());
            android.util.Log.d("UPLOAD", "New post URI: " + DataSource.getProfilePosts().get(0).getPostImageUri());

            Toast.makeText(this, "Post uploaded! Total posts: " + DataSource.getProfilePosts().size(), Toast.LENGTH_LONG).show();

            // Kembali ke Home
            Intent intent = new Intent(UploadActivity.this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });
    }
}