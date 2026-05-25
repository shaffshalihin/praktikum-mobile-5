package com.ishmah.praktikum1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView ivFotoProfil, ivGantiFoto;
    private TextView tvPosts, tvFollowers, tvFollowing, tvLink;
    private LinearLayout btnEditProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi view
        ivFotoProfil = findViewById(R.id.iv_foto_profil);
        ivGantiFoto = findViewById(R.id.iv_ganti_foto);
        tvPosts = findViewById(R.id.tv_posts);
        tvFollowers = findViewById(R.id.tv_followers);
        tvFollowing = findViewById(R.id.tv_following);
        tvLink = findViewById(R.id.tv_link);
        btnEditProfile = findViewById(R.id.btn_edit_profile);

        // Cek data dari EditProfileActivity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String nama = extras.getString("nama", "hodo.id");
            String username = extras.getString("username", "@hodo.id");
            String bio = extras.getString("bio", "Kitchen/cooking\nHomemade rice bowls bento 🍴, cooked by order\nOrder via WhatsApp 📱");
            String link = extras.getString("link", "linktr.ee/HODO.id");
            String posts = extras.getString("posts", "12");
            String followers = extras.getString("followers", "77");
            String following = extras.getString("following", "2");

            // Update tampilan
            // Nama dan username mungkin ditampilkan di tempat lain
            tvPosts.setText(posts);
            tvFollowers.setText(followers);
            tvFollowing.setText(following);
            tvLink.setText(link);

            // Update foto jika ada
            int fotoResId = extras.getInt("foto", -1);
            if (fotoResId != -1) {
                ivFotoProfil.setImageResource(fotoResId);
            }
        }

        // Klik bagian tengah (Follow, Message, +8) untuk edit profile
        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditProfileActivity.class);

                // Kirim data saat ini
                intent.putExtra("nama", "hodo.id");
                intent.putExtra("username", "@hodo.id");
                intent.putExtra("bio", "Kitchen/cooking\nHomemade rice bowls bento 🍴, cooked by order\nOrder via WhatsApp 📱");
                intent.putExtra("link", tvLink.getText().toString());
                intent.putExtra("posts", tvPosts.getText().toString());
                intent.putExtra("followers", tvFollowers.getText().toString());
                intent.putExtra("following", tvFollowing.getText().toString());

                startActivity(intent);
            }
        });

        // Klik ikon ganti foto
        ivGantiFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Langsung ke halaman edit
                Intent intent = new Intent(MainActivity.this, EditProfileActivity.class);
                startActivity(intent);
            }
        });
    }
}