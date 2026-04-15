package com.example.instagramclone.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.instagramclone.R;
import com.example.instagramclone.model.DataDummy;
import com.example.instagramclone.model.Post;

public class UploadPostActivity extends AppCompatActivity {

    private ImageView ivPreview;
    private EditText etCaption;
    private Uri selectedImageUri;
    private static int counter = 200;

    private final ActivityResultLauncher<Intent> picker = registerForActivityResult(
        new ActivityResultContracts.StartActivityForResult(),
        result -> {
            if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                selectedImageUri = result.getData().getData();
                ivPreview.setImageURI(selectedImageUri);
                
                if (selectedImageUri != null) {
                    try {
                        getContentResolver().takePersistableUriPermission(selectedImageUri,
                                Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_post);

        ivPreview        = findViewById(R.id.iv_preview);
        etCaption        = findViewById(R.id.et_caption);
        Button btnPick   = findViewById(R.id.btn_pick_image);
        Button btnShare  = findViewById(R.id.btn_share);
        ImageView btnClose = findViewById(R.id.btn_close);

        btnClose.setOnClickListener(v -> finish());

        btnPick.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");
            picker.launch(intent);
        });

        btnShare.setOnClickListener(v -> {
            if (selectedImageUri == null) {
                Toast.makeText(this, "Pilih gambar terlebih dahulu!", Toast.LENGTH_SHORT).show();
                return;
            }
            String caption = etCaption.getText().toString().trim();
            if (caption.isEmpty()) {
                Toast.makeText(this, "Tambahkan caption!", Toast.LENGTH_SHORT).show();
                return;
            }

            counter++;
            Post newPost = new Post(
                "upload_" + counter,
                "erchiveess",
                DataDummy.getInstance().getCurrentUser().getProfileImageRes(),
                selectedImageUri.toString(),
                caption, 0, 0, "Baru saja"
            );
            
            DataDummy.getInstance().addProfilePost(newPost);
            
            Toast.makeText(this, "Postingan berhasil dibagikan! 🎉", Toast.LENGTH_SHORT).show();
            
            finish();
        });
    }
}
