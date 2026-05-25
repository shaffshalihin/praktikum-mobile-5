package com.indira.tp222.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.indira.tp222.R;
import com.indira.tp222.adapter.HighlightAdapter;
import com.indira.tp222.adapter.ProfilePostAdapter;
import com.indira.tp222.model.DataSource;

public class ProfileActivity extends AppCompatActivity {

    private ImageView ivAdd, ivMenu, imgProfile;
    private TextView tvName, tvUsername, tvBio, tvPostCount, tvFollowers, tvFollowing;
    private RecyclerView rvHighlights, rvProfilePosts;
    private BottomNavigationView bottomNavigationView;

    private String receivedUsername;
    private int receivedProfileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Inisialisasi view
        ivAdd = findViewById(R.id.ivAdd);
        ivMenu = findViewById(R.id.ivMenu);
        imgProfile = findViewById(R.id.imgProfile);
        tvName = findViewById(R.id.tvName);
        tvUsername = findViewById(R.id.tvUsername);
        tvBio = findViewById(R.id.tvBio);
        tvPostCount = findViewById(R.id.tvPostCount);
        tvFollowers = findViewById(R.id.tvFollowers);
        tvFollowing = findViewById(R.id.tvFollowing);
        rvHighlights = findViewById(R.id.rvHighlights);
        rvProfilePosts = findViewById(R.id.rvProfilePosts);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Ambil data dari Intent
        Intent intent = getIntent();
        if (intent != null) {
            receivedUsername = intent.getStringExtra("USERNAME");
            receivedProfileImage = intent.getIntExtra("PROFILE_IMAGE", R.drawable.ic_profile_circle);
        }

        if (receivedUsername == null || receivedUsername.isEmpty()) {
            receivedUsername = "hppn.me";
            receivedProfileImage = R.drawable.ic_profile_circle;
        }

        // Setup data profile
        tvName.setText(receivedUsername);
        tvUsername.setText(receivedUsername);
        tvBio.setText("Bio Instagram");
        tvPostCount.setText(String.valueOf(DataSource.getProfilePosts().size()));
        tvFollowers.setText("100K");
        tvFollowing.setText("91");
        imgProfile.setImageResource(receivedProfileImage);

        // Setup Highlights RecyclerView
        rvHighlights.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        HighlightAdapter highlightAdapter = new HighlightAdapter(this, DataSource.getHighlights());
        rvHighlights.setAdapter(highlightAdapter);

        // Setup Profile Posts RecyclerView
        rvProfilePosts.setLayoutManager(new GridLayoutManager(this, 3));
        ProfilePostAdapter postAdapter = new ProfilePostAdapter(this, DataSource.getProfilePosts());
        rvProfilePosts.setAdapter(postAdapter);

        // Bottom Navigation
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                Intent homeIntent = new Intent(ProfileActivity.this, HomeActivity.class);
                startActivity(homeIntent);
                finish();
                return true;
            } else if (itemId == R.id.nav_profile) {
                return true;
            } else if (itemId == R.id.nav_reels) {
                Toast.makeText(this, "Reels coming soon", Toast.LENGTH_SHORT).show();
                return true;
            } else if (itemId == R.id.nav_message) {
                Toast.makeText(this, "Messages coming soon", Toast.LENGTH_SHORT).show();
                return true;
            } else if (itemId == R.id.nav_search) {
                Toast.makeText(this, "Search coming soon", Toast.LENGTH_SHORT).show();
                return true;
            }
            return false;
        });

        // TOMBOL PLUS - BUKA UPLOAD ACTIVITY
        ivAdd.setOnClickListener(v -> {
            Intent uploadIntent = new Intent(ProfileActivity.this, UploadActivity.class);
            startActivity(uploadIntent);
        });

        // Menu button
        ivMenu.setOnClickListener(v -> {
            Toast.makeText(this, "Menu clicked", Toast.LENGTH_SHORT).show();
        });

        // Edit profile button
        findViewById(R.id.btnEditProfile).setOnClickListener(v -> {
            Toast.makeText(this, "Edit profile clicked", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshProfilePosts();
        tvPostCount.setText(String.valueOf(DataSource.getProfilePosts().size()));
    }

    private void refreshProfilePosts() {
        ProfilePostAdapter newAdapter = new ProfilePostAdapter(this, DataSource.getProfilePosts());
        rvProfilePosts.setAdapter(newAdapter);
    }
}