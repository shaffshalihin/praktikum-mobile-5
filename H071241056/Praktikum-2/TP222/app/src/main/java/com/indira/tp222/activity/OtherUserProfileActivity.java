package com.indira.tp222.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.indira.tp222.R;
import com.indira.tp222.adapter.ProfilePostAdapter;
import com.indira.tp222.model.DataSource;
import com.indira.tp222.model.OtherUser;

public class OtherUserProfileActivity extends AppCompatActivity {

    private ImageView ivBack, ivMenu;
    private de.hdodenhof.circleimageview.CircleImageView imgProfile;
    private TextView tvName, tvUsername, tvBio, tvPostCount, tvFollowers, tvFollowing;
    private MaterialButton btnFollow, btnMessage;
    private RecyclerView rvPosts;

    private OtherUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_user_profile);

        // Inisialisasi view
        ivBack = findViewById(R.id.ivBack);
        ivMenu = findViewById(R.id.ivMenu);
        imgProfile = findViewById(R.id.imgProfile);
        tvName = findViewById(R.id.tvName);
        tvUsername = findViewById(R.id.tvUsername);
        tvBio = findViewById(R.id.tvBio);
        tvPostCount = findViewById(R.id.tvPostCount);
        tvFollowers = findViewById(R.id.tvFollowers);
        tvFollowing = findViewById(R.id.tvFollowing);
        btnFollow = findViewById(R.id.btnFollow);
        btnMessage = findViewById(R.id.btnMessage);
        rvPosts = findViewById(R.id.rvPosts);

        // Ambil data dari Intent
        Intent intent = getIntent();
        String username = intent.getStringExtra("USERNAME");

        if (username != null) {
            user = DataSource.getUserByUsername(username);
        } else {
            user = DataSource.getDefaultOtherUser();
        }

        // Setup data profile
        imgProfile.setImageResource(user.getProfileImage());
        tvName.setText(user.getFullName());
        tvUsername.setText("@" + user.getUsername());
        tvBio.setText(user.getBio());
        tvPostCount.setText(String.valueOf(user.getPostCount()));
        tvFollowers.setText(formatNumber(user.getFollowerCount()));
        tvFollowing.setText(formatNumber(user.getFollowingCount()));

        // Setup posts grid
        rvPosts.setLayoutManager(new GridLayoutManager(this, 3));
        ProfilePostAdapter postAdapter = new ProfilePostAdapter(this, user.getUserPosts());
        rvPosts.setAdapter(postAdapter);

        // Follow button click
        btnFollow.setOnClickListener(v -> {
            if (btnFollow.getText().equals("Follow")) {
                btnFollow.setText("Following");
                btnFollow.setBackgroundTintList(getColorStateList(R.color.green));
                Toast.makeText(this, "Following " + user.getUsername(), Toast.LENGTH_SHORT).show();
            } else {
                btnFollow.setText("Follow");
                btnFollow.setBackgroundTintList(getColorStateList(R.color.blue));
                Toast.makeText(this, "Unfollowed " + user.getUsername(), Toast.LENGTH_SHORT).show();
            }
        });

        // Message
        btnMessage.setOnClickListener(v -> {
            Toast.makeText(this, "Message to " + user.getUsername(), Toast.LENGTH_SHORT).show();
        });

        // Back
        ivBack.setOnClickListener(v -> finish());

        // Menu
        ivMenu.setOnClickListener(v -> {
            Toast.makeText(this, "Report user", Toast.LENGTH_SHORT).show();
        });
    }

    private String formatNumber(int number) {
        if (number >= 1000000) {
            return (number / 1000000) + "." + ((number % 1000000) / 100000) + "M";
        } else if (number >= 1000) {
            return (number / 1000) + "." + ((number % 1000) / 100) + "K";
        }
        return String.valueOf(number);
    }
}