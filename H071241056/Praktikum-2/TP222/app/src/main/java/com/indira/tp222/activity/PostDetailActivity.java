package com.indira.tp222.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.indira.tp222.R;

public class PostDetailActivity extends AppCompatActivity {

    private ImageView ivBack, ivPostImage, ivLike, ivComment, ivShare, ivSave;
    private TextView tvUsername, tvUsernameCaption, tvCaption, tvLikes, tvTimeAgo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        ivBack = findViewById(R.id.ivBack);
        ivPostImage = findViewById(R.id.ivPostImage);
        ivLike = findViewById(R.id.ivLike);
        ivComment = findViewById(R.id.ivComment);
        ivShare = findViewById(R.id.ivShare);
        ivSave = findViewById(R.id.ivSave);
        tvUsername = findViewById(R.id.tvUsername);
        tvUsernameCaption = findViewById(R.id.tvUsernameCaption);
        tvCaption = findViewById(R.id.tvCaption);
        tvLikes = findViewById(R.id.tvLikes);
        tvTimeAgo = findViewById(R.id.tvTimeAgo);

        Intent intent = getIntent();
        String postImageUri = intent.getStringExtra("POST_IMAGE_URI");
        int postImageRes = intent.getIntExtra("POST_IMAGE_RES", R.drawable.wlpp);
        String postCaption = intent.getStringExtra("POST_CAPTION");

        if (postImageUri != null && !postImageUri.isEmpty()) {
            try {
                Uri imageUri = Uri.parse(postImageUri);
                ivPostImage.setImageURI(imageUri);
                ivPostImage.setScaleType(ImageView.ScaleType.CENTER_CROP);

                // Debug
                android.util.Log.d("PostDetailActivity", "Loading URI: " + postImageUri);
            } catch (Exception e) {
                ivPostImage.setImageResource(R.drawable.wlpp);
                android.util.Log.e("PostDetailActivity", "Error loading URI: " + e.getMessage());
            }
        } else {
            ivPostImage.setImageResource(postImageRes);
            ivPostImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }

        tvUsername.setText("hppn.me");
        tvUsernameCaption.setText("hppn.me");
        tvCaption.setText(postCaption != null ? postCaption : "No caption");
        tvLikes.setText("1,234 likes");
        tvTimeAgo.setText("2 HOURS AGO");

        ivLike.setOnClickListener(v -> {
            Toast.makeText(this, "Liked!", Toast.LENGTH_SHORT).show();
            ivLike.setImageResource(R.drawable.heart_filled);
        });

        ivBack.setOnClickListener(v -> finish());
    }
}