package com.indira.tp222.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.indira.tp222.R;
import com.indira.tp222.adapter.FeedAdapter;
import com.indira.tp222.model.DataSource;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView rvPosts;
    private FeedAdapter feedAdapter;
    private BottomNavigationView bottomNavigationView;
    private ImageView ivAdd, ivHeart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi view
        rvPosts = findViewById(R.id.rvPosts);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        ivAdd = findViewById(R.id.add);
        ivHeart = findViewById(R.id.heart);

        // Setup RecyclerView
        rvPosts.setLayoutManager(new LinearLayoutManager(this));
        rvPosts.setNestedScrollingEnabled(false);
        feedAdapter = new FeedAdapter(this, DataSource.getHomeFeed());
        rvPosts.setAdapter(feedAdapter);

        // Bottom Navigation
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.nav_home) {
                return true;
            } else if (itemId == R.id.nav_profile) {
                Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(intent);
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

        ivAdd.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, UploadActivity.class);
            startActivity(intent);
        });

        ivHeart.setOnClickListener(v -> {
            Toast.makeText(this, "Notifications", Toast.LENGTH_SHORT).show();
        });
    }
}