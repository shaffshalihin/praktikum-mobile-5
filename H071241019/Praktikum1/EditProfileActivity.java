package com.ishmah.praktikum1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class EditProfileActivity extends AppCompatActivity {

    private ImageView ivEditFoto;
    private TextView btnBack, btnDone, tvGantiFotoEdit;
    private EditText etLink, etPosts, etFollowers, etFollowing, etNama, etUsername, etBio;

    private int selectedFoto = R.drawable.ic_hodo_profile;
    private int[] fotoOptions = {
            R.drawable.ic_hodo_profile,
            R.drawable.ic_story_1,
            R.drawable.ic_story_2,
            R.drawable.ic_story_3,
            R.drawable.ic_story_4,
            R.drawable.ic_story_5,
            R.drawable.ic_story_6,
            R.drawable.ic_story_7
    };
    private int fotoIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        // Inisialisasi view
        ivEditFoto = findViewById(R.id.iv_edit_foto);
        btnBack = findViewById(R.id.btn_back);
        btnDone = findViewById(R.id.btn_done);
        tvGantiFotoEdit = findViewById(R.id.tv_ganti_foto_edit);
        etNama = findViewById(R.id.et_nama);
        etUsername = findViewById(R.id.et_username);
        etBio = findViewById(R.id.et_bio);
        etLink = findViewById(R.id.et_link);
        etPosts = findViewById(R.id.et_posts);
        etFollowers = findViewById(R.id.et_followers);
        etFollowing = findViewById(R.id.et_following);

        // Ambil data dari MainActivity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            etLink.setText(extras.getString("link"));
            etPosts.setText(extras.getString("posts"));
            etFollowers.setText(extras.getString("followers"));
            etFollowing.setText(extras.getString("following"));
        }

        // Klik foto untuk ganti
        ivEditFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gantiFoto();
            }
        });

        // Klik teks kamera untuk ganti foto
        tvGantiFotoEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gantiFoto();
            }
        });

        // Tombol back
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Tombol Done
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditProfileActivity.this, MainActivity.class);

                intent.putExtra("link", etLink.getText().toString());
                intent.putExtra("posts", etPosts.getText().toString());
                intent.putExtra("followers", etFollowers.getText().toString());
                intent.putExtra("following", etFollowing.getText().toString());
                intent.putExtra("foto", selectedFoto);

                startActivity(intent);
                finish();
            }
        });
    }

    private void gantiFoto() {
        fotoIndex = (fotoIndex + 1) % fotoOptions.length;
        selectedFoto = fotoOptions[fotoIndex];
        ivEditFoto.setImageResource(selectedFoto);
    }
}