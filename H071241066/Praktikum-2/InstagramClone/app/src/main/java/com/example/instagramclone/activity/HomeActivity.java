package com.example.instagramclone.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instagramclone.R;
import com.example.instagramclone.adapter.FeedAdapter;
import com.example.instagramclone.adapter.StoryAdapter;
import com.example.instagramclone.model.DataDummy;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    private FeedAdapter feedAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        RecyclerView rvStories = findViewById(R.id.rv_stories);
        rvStories.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvStories.setAdapter(new StoryAdapter(this, DataDummy.getInstance().getHomeStories()));

        RecyclerView rvFeed = findViewById(R.id.rv_feed);
        rvFeed.setLayoutManager(new LinearLayoutManager(this));
        feedAdapter = new FeedAdapter(this, DataDummy.getInstance().getHomeFeedPosts());
        rvFeed.setAdapter(feedAdapter);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.btn_nav_home);
        
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.btn_nav_home) {
                return true;
            } else if (itemId == R.id.btn_nav_profile_active) {
                Intent intent = new Intent(this, ProfileActivity.class);
                intent.putExtra("username", "erchiveess");
                startActivity(intent);
                return true;
            } else if (itemId == R.id.btn_nav_send) {
                startActivity(new Intent(this, UploadPostActivity.class));
                return true;
            }
            return false;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (feedAdapter != null) feedAdapter.notifyDataSetChanged();
        
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        if (bottomNavigationView != null) {
            bottomNavigationView.setSelectedItemId(R.id.btn_nav_home);
        }
    }
}
