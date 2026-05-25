package com.ishmah.praktikum1;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private ImageView ivFotoProfil;
    private TextView tvGantiFoto, tvPosts, tvFollowers, tvFollowing, tvLink;
    private LinearLayout btnEditProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi view
        ivFotoProfil = findViewById(R.id.iv_foto_profil);
        tvGantiFoto = findViewById(R.id.tv_ganti_foto);
        tvPosts = findViewById(R.id.tv_posts);
        tvFollowers = findViewById(R.id.tv_followers);
        tvFollowing = findViewById(R.id.tv_following);
        tvLink = findViewById(R.id.tv_link);
        btnEditProfile = findViewById(R.id.btn_edit_profile);

        // Cek data dari EditProfileActivity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String link = extras.getString("link", "linktr.ee/HODO.id");
            String posts = extras.getString("posts", "12");
            String followers = extras.getString("followers", "77");
            String following = extras.getString("following", "2");

            tvPosts.setText(posts);
            tvFollowers.setText(followers);
            tvFollowing.setText(following);
            tvLink.setText(link);

            int fotoResId = extras.getInt("foto", -1);
            if (fotoResId != -1) {
                ivFotoProfil.setImageResource(fotoResId);
            }
        }

        // Klik area tombol untuk edit profile
        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditProfileActivity.class);

                intent.putExtra("link", tvLink.getText().toString());
                intent.putExtra("posts", tvPosts.getText().toString());
                intent.putExtra("followers", tvFollowers.getText().toString());
                intent.putExtra("following", tvFollowing.getText().toString());

                startActivity(intent);
            }
        });

        // Klik teks ganti foto (➕)
        tvGantiFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditProfileActivity.class);
                startActivity(intent);
            }
        });

        // ========== TAB LAYOUT FUNCTIONALITY ==========
        setupTabs();
    }

    private void setupTabs() {
        // Inisialisasi tab
        LinearLayout tabPosts = findViewById(R.id.tab_posts);
        LinearLayout tabReels = findViewById(R.id.tab_reels);
        LinearLayout tabTagged = findViewById(R.id.tab_tagged);

        // Inisialisasi konten
        LinearLayout contentPosts = findViewById(R.id.content_posts);
        LinearLayout contentReels = findViewById(R.id.content_reels);
        LinearLayout contentTagged = findViewById(R.id.content_tagged);

        // Ambil icon dan text dari masing-masing tab - CARA YANG LEBIH AMAN
        ImageView iconPosts = tabPosts.findViewById(R.id.icon_posts);
        ImageView iconReels = tabReels.findViewById(R.id.icon_reels);
        ImageView iconTagged = tabTagged.findViewById(R.id.icon_tagged);

        TextView textPosts = tabPosts.findViewById(R.id.text_posts);
        TextView textReels = tabReels.findViewById(R.id.text_reels);
        TextView textTagged = tabTagged.findViewById(R.id.text_tagged);

        // Set default: Posts aktif
        setActiveTab(iconPosts, iconReels, iconTagged, textPosts, textReels, textTagged, contentPosts, contentReels, contentTagged);

        // Klik tab Posts
        tabPosts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setActiveTab(iconPosts, iconReels, iconTagged, textPosts, textReels, textTagged, contentPosts, contentReels, contentTagged);
            }
        });

        // Klik tab Reels
        tabReels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setActiveTab(iconReels, iconPosts, iconTagged, textReels, textPosts, textTagged, contentReels, contentPosts, contentTagged);
            }
        });

        // Klik tab Tagged
        tabTagged.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setActiveTab(iconTagged, iconPosts, iconReels, textTagged, textPosts, textReels, contentTagged, contentPosts, contentReels);
            }
        });
    }

    private void setActiveTab(ImageView activeIcon, ImageView inactiveIcon1, ImageView inactiveIcon2,
                              TextView activeText, TextView inactiveText1, TextView inactiveText2,
                              LinearLayout activeContent, LinearLayout inactiveContent1, LinearLayout inactiveContent2) {
        
        // Set visibility konten
        activeContent.setVisibility(View.VISIBLE);
        inactiveContent1.setVisibility(View.GONE);
        inactiveContent2.setVisibility(View.GONE);

        // Set warna icon - PAKAI ContextCompat
        activeIcon.setColorFilter(ContextCompat.getColor(this, R.color.instagram_blue));
        inactiveIcon1.setColorFilter(ContextCompat.getColor(this, R.color.instagram_gray));
        inactiveIcon2.setColorFilter(ContextCompat.getColor(this, R.color.instagram_gray));

        // Set warna text
        activeText.setTextColor(ContextCompat.getColor(this, R.color.instagram_blue));
        inactiveText1.setTextColor(ContextCompat.getColor(this, R.color.instagram_gray));
        inactiveText2.setTextColor(ContextCompat.getColor(this, R.color.instagram_gray));

        // Set style text
        activeText.setTypeface(null, Typeface.BOLD);
        inactiveText1.setTypeface(null, Typeface.NORMAL);
        inactiveText2.setTypeface(null, Typeface.NORMAL);
    }
}