package com.ishmah.praktikumm2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvFeed;
    private FeedAdapter feedAdapter;
    private List<FeedItem> feedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvFeed = findViewById(R.id.rvFeed);
        rvFeed.setLayoutManager(new LinearLayoutManager(this));

        feedList = DataDummy.getHomeFeed();
        feedAdapter = new FeedAdapter(this, feedList);
        rvFeed.setAdapter(feedAdapter);

        feedAdapter.setOnItemClickListener(new FeedAdapter.OnItemClickListener() {
            @Override
            public void onProfileClick(FeedItem item) {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                intent.putExtra("USERNAME", item.getUsername());
                startActivity(intent);
            }

            @Override
            public void onPostClick(FeedItem item) {
                openPostDetail(item);
            }

            @Override
            public void onLikeClick(FeedItem item) {
                Toast.makeText(MainActivity.this, "❤️ Menyukai postingan", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCommentClick(FeedItem item) {
                Toast.makeText(MainActivity.this, "💬 Buka komentar", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRepostClick(FeedItem item) {
                Toast.makeText(MainActivity.this, "🔁 Membagikan ulang", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSendClick(FeedItem item) {
                Toast.makeText(MainActivity.this, "📤 Mengirim ke teman", Toast.LENGTH_SHORT).show();
            }
        });

        setupStoryClicks();
        setupBottomNav();
    }

    private void openPostDetail(FeedItem item) {
        Intent intent = new Intent(MainActivity.this, PostDetailActivity.class);
        intent.putExtra("username", item.getUsername());
        intent.putExtra("profile_image_res", item.getProfileImageRes());
        if (item.getPostImageUri() != null) {
            intent.putExtra("post_image_uri", item.getPostImageUri().toString());
        } else {
            intent.putExtra("post_image", item.getPostImageRes());
        }
        intent.putExtra("caption", item.getCaption());
        intent.putExtra("like_count", item.getLikeCount());
        intent.putExtra("comment_count", item.getCommentCount());
        startActivity(intent);
    }

    private void setupStoryClicks() {
        setupStoryClick(R.id.story_hodo, R.drawable.ic_story_hodo1, "hodo.id");
        setupStoryClick(R.id.story_photography, R.drawable.ic_story_photo1, "photography");
        setupStoryClick(R.id.story_126alters, R.drawable.ic_story_126_1, "126alters");
        setupStoryClick(R.id.story_ishmahhjkl, R.drawable.ic_story_ishma_1, "ishmahhjkl");
        setupStoryClick(R.id.story_sre_unhas, R.drawable.ic_story_sre_1, "sre_unhas");
        setupStoryClick(R.id.story_foodie_mksr, R.drawable.ic_story_foodie1, "foodie_mksr");
        setupStoryClick(R.id.story_snapshot_id, R.drawable.ic_story_snap1, "snapshot_id");
        setupStoryClick(R.id.story_alters_style, R.drawable.ic_story_alters1, "alters_style");
        setupStoryClick(R.id.story_wanderlust_ish, R.drawable.ic_story_wander1, "wanderlust_ish");
        setupStoryClick(R.id.story_greentech_unhas, R.drawable.ic_story_green1, "greentech_unhas");
    }

    private void setupBottomNav() {
        CircleImageView btnProfile = findViewById(R.id.btn_main_nav_profile);
        Glide.with(this).load(R.drawable.ic_hodo_profile).circleCrop().into(btnProfile);

        btnProfile.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            intent.putExtra("USERNAME", "hodo.id");
            startActivity(intent);
        });

        ImageView btnAdd = findViewById(R.id.btn_main_nav_add);
        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            intent.putExtra("USERNAME", "hodo.id");
            startActivity(intent);
        });

        findViewById(R.id.btn_main_nav_search).setOnClickListener(v ->
                Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show());

        findViewById(R.id.btn_main_nav_reels).setOnClickListener(v ->
                Toast.makeText(this, "Reels", Toast.LENGTH_SHORT).show());
    }

    private void setupStoryClick(int viewId, int storyImageRes, String username) {
        View view = findViewById(viewId);
        if (view != null) {
            view.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, StoryDetailActivity.class);
                intent.putExtra("story_image", storyImageRes);
                intent.putExtra("username", username);
                startActivity(intent);
            });
        }
    }
}
