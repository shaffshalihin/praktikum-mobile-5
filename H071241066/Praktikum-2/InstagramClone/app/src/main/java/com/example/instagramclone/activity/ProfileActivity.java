package com.example.instagramclone.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instagramclone.R;
import com.example.instagramclone.adapter.ProfileGridAdapter;
import com.example.instagramclone.adapter.StoryAdapter;
import com.example.instagramclone.model.DataDummy;
import com.example.instagramclone.model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {

    private ProfileGridAdapter gridAdapter;
    private TextView tvPostCount;
    private TextView tvName;
    private String targetUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        targetUsername = getIntent().getStringExtra("username");
        if (targetUsername == null) targetUsername = "erchiveess";

        boolean isMyProfile = targetUsername.equals("erchiveess");
        User user = DataDummy.getInstance().getUserByUsername(targetUsername);

        TextView tvToolbarUsername = findViewById(R.id.tvToolbarUsername);
        if (tvToolbarUsername != null) tvToolbarUsername.setText(user.getUsername());
        
        ImageView btnAddFeed = findViewById(R.id.btn_nav_send);
        if (btnAddFeed != null) {
            btnAddFeed.setOnClickListener(v -> startActivity(new Intent(this, UploadPostActivity.class)));
        }

        ImageView btnBack = findViewById(R.id.btn_back);
        if (btnBack != null) btnBack.setOnClickListener(v -> finish());

        ImageView imgProfilePicture = findViewById(R.id.imgProfilePicture);
        if (imgProfilePicture != null) imgProfilePicture.setImageResource(user.getProfileImageRes());

        tvName = findViewById(R.id.tvName);
        if (tvName != null) tvName.setText(user.getName());
        
        TextView tvBio = findViewById(R.id.tvBio);
        if (tvBio != null) tvBio.setText(user.getBio());

        tvPostCount = findViewById(R.id.tvPostCount);
        if (tvPostCount != null) {
            tvPostCount.setText(String.valueOf(DataDummy.getInstance().getPostsForUser(targetUsername).size()));
        }
        
        TextView tvFollowerCount = findViewById(R.id.tvFollowerCount);
        if (tvFollowerCount != null) {
            tvFollowerCount.setText(formatCount(user.getFollowerCount()));
        }
        
        TextView tvFollowingCount = findViewById(R.id.tvFollowingCount);
        if (tvFollowingCount != null) {
            tvFollowingCount.setText(formatCount(user.getFollowingCount()));
        }

        RecyclerView rvStories = findViewById(R.id.rv_stories);
        if (rvStories != null) {
            if (isMyProfile) {
                rvStories.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
                rvStories.setAdapter(new StoryAdapter(this, DataDummy.getInstance().getHighlightStories()));
            } else {
                rvStories.setVisibility(View.GONE);
            }
        }

        RecyclerView rvGrid = findViewById(R.id.rv_grid);
        if (rvGrid != null) {
            rvGrid.setLayoutManager(new GridLayoutManager(this, 3));
            gridAdapter = new ProfileGridAdapter(this, DataDummy.getInstance().getPostsForUser(targetUsername));
            rvGrid.setAdapter(gridAdapter);
        }

        Button btnEditProfile = findViewById(R.id.btnEditProfile);
        if (btnEditProfile != null) {
            if (isMyProfile) {
                btnEditProfile.setText("Edit profil");
                btnEditProfile.setOnClickListener(v -> startActivity(new Intent(this, EditProfileActivity.class)));
            } else {
                btnEditProfile.setText("Ikuti");
                btnEditProfile.setOnClickListener(v -> btnEditProfile.setText("Mengikuti ✓"));
            }
        }

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        if (bottomNavigationView != null) {
            bottomNavigationView.setSelectedItemId(R.id.btn_nav_profile_active);
            
            bottomNavigationView.setOnItemSelectedListener(item -> {
                int itemId = item.getItemId();
                if (itemId == R.id.btn_nav_home) {
                    Intent intent = new Intent(this, HomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                    finish();
                    return true;
                } else if (itemId == R.id.btn_nav_send) {
                    startActivity(new Intent(this, UploadPostActivity.class));
                    return true;
                } else if (itemId == R.id.btn_nav_profile_active) {
                    return true;
                }
                return false;
            });
        }
    }

    private String formatCount(int n) {
        if (n >= 1000000) return String.format("%.1fM", n / 1000000.0);
        if (n >= 1000)    return String.format("%.1fK", n / 1000.0);
        return String.valueOf(n);
    }

    @Override
    protected void onResume() {
        super.onResume();
        User user = DataDummy.getInstance().getUserByUsername(targetUsername);
        
        if (tvName != null) tvName.setText(user.getName());
        if (tvPostCount != null) {
            tvPostCount.setText(String.valueOf(DataDummy.getInstance().getPostsForUser(targetUsername).size()));
        }
        
        if (gridAdapter != null) gridAdapter.notifyDataSetChanged();
        
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        if (bottomNavigationView != null) {
            bottomNavigationView.setSelectedItemId(R.id.btn_nav_profile_active);
        }
    }
}
