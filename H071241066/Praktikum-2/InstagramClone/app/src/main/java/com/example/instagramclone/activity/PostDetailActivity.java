package com.example.instagramclone.activity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.instagramclone.R;
import com.example.instagramclone.model.DataDummy;
import com.example.instagramclone.model.Post;

import de.hdodenhof.circleimageview.CircleImageView;

public class PostDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        String postId = getIntent().getStringExtra("post_id");
        Post post = DataDummy.getInstance().findPostById(postId);

        findViewById(R.id.btn_back).setOnClickListener(v -> finish());
        
        if (post == null) return;

        CircleImageView ivProfile = findViewById(R.id.iv_profile);
        TextView tvUsername       = findViewById(R.id.tv_username);
        ImageView ivPost          = findViewById(R.id.iv_post);
        ImageView ivLike          = findViewById(R.id.iv_like);
        ImageView ivBookmark      = findViewById(R.id.iv_bookmark);
        TextView tvLikeCount      = findViewById(R.id.tv_like_count);
        TextView tvCaption        = findViewById(R.id.tv_caption);
        TextView tvTime           = findViewById(R.id.tv_time);

        ivProfile.setImageResource(post.getUserProfileImage());
        tvUsername.setText(post.getUsername());

        if (post.isUploaded()) {
            ivPost.setImageURI(Uri.parse(post.getImageUriString()));
        } else {
            ivPost.setImageResource(post.getImageResId());
        }

        tvLikeCount.setText(post.getLikeCount() + " suka");
        tvCaption.setText(post.getUsername() + "  " + post.getCaption());
        tvTime.setText(post.getTimeAgo());

        ivLike.setImageResource(post.isLiked() ? R.drawable.ic_heart_filled : R.drawable.ic_heart);
        ivBookmark.setImageResource(post.isBookmarked() ? R.drawable.ic_bookmark_filled : R.drawable.ic_bookmark);

        ivLike.setOnClickListener(v -> {
            boolean liked = !post.isLiked();
            post.setLiked(liked);
            post.setLikeCount(liked ? post.getLikeCount() + 1 : post.getLikeCount() - 1);
            ivLike.setImageResource(liked ? R.drawable.ic_heart_filled : R.drawable.ic_heart);
            tvLikeCount.setText(post.getLikeCount() + " suka");
        });

        ivBookmark.setOnClickListener(v -> {
            boolean bm = !post.isBookmarked();
            post.setBookmarked(bm);
            ivBookmark.setImageResource(bm ? R.drawable.ic_bookmark_filled : R.drawable.ic_bookmark);
        });
    }
}
