package com.ishmah.praktikumm2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

public class PostDetailActivity extends AppCompatActivity {

    private ImageView ivPostImage, ivBack, ivLike, ivComment, ivRepost, ivSend, ivSave;
    private TextView ivMenu, tvUsername, tvCaptionUsername, tvCaption, tvLikeCount, tvCommentCount;
    private CircleImageView ivProfile;

    private boolean isLiked = false;
    private String currentUsername = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        initViews();
        loadData();
        setupListeners();
    }

    private void initViews() {
        ivPostImage = findViewById(R.id.iv_post_image);
        ivBack = findViewById(R.id.iv_back);
        ivMenu = findViewById(R.id.iv_menu);
        ivProfile = findViewById(R.id.iv_profile);
        tvUsername = findViewById(R.id.tv_username);
        ivLike = findViewById(R.id.iv_like);
        ivComment = findViewById(R.id.iv_comment);
        ivRepost = findViewById(R.id.iv_repost);
        ivSend = findViewById(R.id.iv_send);
        ivSave = findViewById(R.id.iv_save);
        tvCaptionUsername = findViewById(R.id.tv_caption_username);
        tvCaption = findViewById(R.id.tv_caption);
        tvLikeCount = findViewById(R.id.tv_like_count);
        tvCommentCount = findViewById(R.id.tv_comment_count);
    }

    private void loadData() {
        Intent intent = getIntent();

        // Load post image
        String imageUriString = intent.getStringExtra("post_image_uri");
        int postImageRes = intent.getIntExtra("post_image", 0);

        if (imageUriString != null && !imageUriString.isEmpty()) {
            Glide.with(this).load(Uri.parse(imageUriString)).centerCrop().into(ivPostImage);
        } else if (postImageRes != 0) {
            Glide.with(this).load(postImageRes).centerCrop().into(ivPostImage);
        }

        // Load user data
        currentUsername = intent.getStringExtra("username");
        if (currentUsername == null || currentUsername.isEmpty()) {
            currentUsername = "hodo.id";
        }

        int profileImageRes = intent.getIntExtra("profile_image_res", 0);
        if (profileImageRes == 0) {
            profileImageRes = R.drawable.ic_hodo_profile;
        }

        String caption = intent.getStringExtra("caption");
        String likeCount = intent.getStringExtra("like_count");
        String commentCount = intent.getStringExtra("comment_count");

        // Set all text immediately
        tvUsername.setText(currentUsername);
        tvCaptionUsername.setText(currentUsername);

        if (caption != null) tvCaption.setText(caption);
        if (likeCount != null) tvLikeCount.setText(likeCount + " likes");
        if (commentCount != null) tvCommentCount.setText(commentCount + " komentar");

        // Load profile image
        Glide.with(this).load(profileImageRes).circleCrop().into(ivProfile);
    }

    private void goToProfile() {
        Intent intent = new Intent(PostDetailActivity.this, ProfileActivity.class);
        intent.putExtra("USERNAME", currentUsername);
        startActivity(intent);
    }

    private void setupListeners() {
        ivBack.setOnClickListener(v -> finish());

        ivProfile.setOnClickListener(v -> goToProfile());
        tvUsername.setOnClickListener(v -> goToProfile());
        tvCaptionUsername.setOnClickListener(v -> goToProfile());

        ivMenu.setOnClickListener(v ->
                Toast.makeText(this, "Menu", Toast.LENGTH_SHORT).show());

        ivLike.setOnClickListener(v -> {
            if (isLiked) {
                ivLike.setColorFilter(ContextCompat.getColor(this, R.color.instagram_dark));
                isLiked = false;
            } else {
                ivLike.setColorFilter(ContextCompat.getColor(this, R.color.instagram_red));
                isLiked = true;
            }
        });

        ivComment.setOnClickListener(v ->
                Toast.makeText(this, "Buka komentar", Toast.LENGTH_SHORT).show());

        ivRepost.setOnClickListener(v ->
                Toast.makeText(this, "Membagikan ulang", Toast.LENGTH_SHORT).show());

        ivSend.setOnClickListener(v ->
                Toast.makeText(this, "Mengirim ke teman", Toast.LENGTH_SHORT).show());

        ivSave.setOnClickListener(v ->
                Toast.makeText(this, "Menyimpan", Toast.LENGTH_SHORT).show());
    }
}
