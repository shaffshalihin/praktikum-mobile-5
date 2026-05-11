package com.ishmah.praktikum1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class EditProfileActivity extends AppCompatActivity {

    private ImageView ivEditFoto;
    private TextView btnBack, btnDone, tvGantiFotoEdit;
    private EditText etNama, etUsername, etBio, etLink, etPosts, etFollowers, etFollowing;

    private Uri selectedPhotoUri = null;
    private int selectedFotoResId = R.drawable.ic_hodo_profile;

    private ActivityResultLauncher<Intent> galleryLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        // Inisialisasi view
        ivEditFoto      = findViewById(R.id.iv_edit_foto);
        btnBack         = findViewById(R.id.btn_back);
        btnDone         = findViewById(R.id.btn_done);
        tvGantiFotoEdit = findViewById(R.id.tv_ganti_foto_edit);
        etNama          = findViewById(R.id.et_nama);
        etUsername      = findViewById(R.id.et_username);
        etBio           = findViewById(R.id.et_bio);
        etLink          = findViewById(R.id.et_link);
        etPosts         = findViewById(R.id.et_posts);
        etFollowers     = findViewById(R.id.et_followers);
        etFollowing     = findViewById(R.id.et_following);

        // Setup launcher untuk memilih foto dari galeri
        galleryLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Uri uri = result.getData().getData();
                    if (uri != null) {
                        selectedPhotoUri = uri;
                        selectedFotoResId = -1;
                        ivEditFoto.setImageURI(selectedPhotoUri);
                    }
                }
            }
        );

        // Isi field dengan data yang diterima dari MainActivity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            etNama.setText(extras.getString("nama", "HODO.id – Eat With Rhythm"));
            etUsername.setText(extras.getString("username", "hodo.id"));
            etBio.setText(extras.getString("bio",
                "Kitchen/cooking\nHomemade rice bowls bento 🍴, cooked by order\nOrder via WhatsApp 📱"));
            etLink.setText(extras.getString("link", "linktr.ee/HODO.id"));
            etPosts.setText(extras.getString("posts", "12"));
            etFollowers.setText(extras.getString("followers", "77"));
            etFollowing.setText(extras.getString("following", "2"));

            // Tampilkan foto profil saat ini
            String fotoUri = extras.getString("foto_uri", "");
            if (fotoUri != null && !fotoUri.isEmpty()) {
                selectedPhotoUri = Uri.parse(fotoUri);
                ivEditFoto.setImageURI(selectedPhotoUri);
            } else {
                selectedFotoResId = extras.getInt("foto", R.drawable.ic_hodo_profile);
                ivEditFoto.setImageResource(selectedFotoResId);
            }
        }

        // Klik foto atau ikon kamera → buka galeri
        ivEditFoto.setOnClickListener(v -> bukaGaleri());
        tvGantiFotoEdit.setOnClickListener(v -> bukaGaleri());

        // Tombol Back → batalkan perubahan
        btnBack.setOnClickListener(v -> finish());

        // Tombol Done → kirim data kembali ke MainActivity
        btnDone.setOnClickListener(v -> {
            Intent result = new Intent();
            result.putExtra("nama",      etNama.getText().toString().trim());
            result.putExtra("username",  etUsername.getText().toString().trim());
            result.putExtra("bio",       etBio.getText().toString().trim());
            result.putExtra("link",      etLink.getText().toString().trim());
            result.putExtra("posts",     etPosts.getText().toString().trim());
            result.putExtra("followers", etFollowers.getText().toString().trim());
            result.putExtra("following", etFollowing.getText().toString().trim());

            if (selectedPhotoUri != null) {
                result.putExtra("foto_uri", selectedPhotoUri.toString());
                result.putExtra("foto", -1);
            } else {
                result.putExtra("foto_uri", "");
                result.putExtra("foto", selectedFotoResId);
            }

            setResult(RESULT_OK, result);
            finish();
        });
    }

    private void bukaGaleri() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        galleryLauncher.launch(intent);
    }
}
