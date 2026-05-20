package com.ishmah.praktikumm2;

import android.content.ContentValues;
import android.content.Intent;

import com.bumptech.glide.Glide;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;
import java.io.OutputStream;

public class UploadActivity extends AppCompatActivity {

    private ImageView ivPostImage;
    private TextView ivBack;
    private EditText etCaption;
    private Button btnUpload;
    private Uri selectedImageUri;
    private Uri savedImageUri;

    private final ActivityResultLauncher<Intent> galleryLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    selectedImageUri = result.getData().getData();
                    if (selectedImageUri != null) {
                        Glide.with(UploadActivity.this).load(selectedImageUri).centerCrop().into(ivPostImage);
                    }
                } else {
                    Toast.makeText(this, "Tidak ada gambar dipilih", Toast.LENGTH_SHORT).show();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        initViews();
        setupListeners();
    }

    private void initViews() {
        ivPostImage = findViewById(R.id.iv_post_image);
        ivBack = findViewById(R.id.iv_back);
        etCaption = findViewById(R.id.et_caption);
        btnUpload = findViewById(R.id.btn_upload);
    }

    private void setupListeners() {
        ivBack.setOnClickListener(v -> finish());

        ivPostImage.setOnClickListener(v -> openGallery());

        btnUpload.setOnClickListener(v -> {
            if (selectedImageUri == null) {
                Toast.makeText(this, "Pilih gambar terlebih dahulu", Toast.LENGTH_SHORT).show();
                return;
            }

            String caption = etCaption.getText().toString().trim();
            if (caption.isEmpty()) {
                caption = "No caption";
            }

            // Simpan gambar ke storage permanen
            savedImageUri = saveImageToStorage(selectedImageUri);

            if (savedImageUri != null) {
                // Kirim Uri sebagai String
                Intent resultIntent = new Intent();
                resultIntent.putExtra("image_uri", savedImageUri.toString());
                resultIntent.putExtra("caption", caption);
                setResult(RESULT_OK, resultIntent);
                finish();
                Toast.makeText(this, "✅ Feed berhasil diupload!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Gagal menyimpan gambar", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Uri saveImageToStorage(Uri sourceUri) {
        try {
            String fileName = "feed_" + System.currentTimeMillis() + ".jpg";

            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.DISPLAY_NAME, fileName);
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
            values.put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/Praktikum2");

            Uri uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

            if (uri != null) {
                InputStream inputStream = getContentResolver().openInputStream(sourceUri);
                OutputStream outputStream = getContentResolver().openOutputStream(uri);

                byte[] buffer = new byte[1024];
                int length;
                while ((length = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, length);
                }

                outputStream.close();
                inputStream.close();

                return uri;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryLauncher.launch(intent);
    }
}