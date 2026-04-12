package com.example.praktikummobile_1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.praktikummobile_1.adapters.PostAdapter;
import com.example.praktikummobile_1.adapters.StoryAdapter;
import com.example.praktikummobile_1.models.Post;
import com.example.praktikummobile_1.models.Story;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvPosts, rvStories;
    private PostAdapter postAdapter;
    private StoryAdapter storyAdapter;
    private List<Post> postList;
    private List<Story> storyList;
    private ImageView btnGoToProfile, btnGoToPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Stories
        rvStories = findViewById(R.id.rv_stories);
        rvStories.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        storyList = new ArrayList<>();
        storyList.add(new Story(R.drawable.zara, "Cerita Anda"));
        storyList.add(new Story(R.drawable.home9, "nrdndaa"));
        storyList.add(new Story(R.drawable.home8, "jinggasyh"));
        storyList.add(new Story(R.drawable.home7, "araaa"));
        storyList.add(new Story(R.drawable.home6, "zara"));
        storyList.add(new Story(R.drawable.home5, "adilah"));
        storyList.add(new Story(R.drawable.home4, "anisa"));

        // Perbaikan: Menambahkan parameter boolean 'showBorder' (true) agar sesuai dengan constructor baru di StoryAdapter
        storyAdapter = new StoryAdapter(storyList, true, story -> {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("title", "Story");
            intent.putExtra("imageRes", story.getStoryImage());
            startActivity(intent);
        });
        rvStories.setAdapter(storyAdapter);

        // 2. Feed Posts (Data Dummy 10 Item Berbeda)
        rvPosts = findViewById(R.id.rv_posts);
        rvPosts.setLayoutManager(new LinearLayoutManager(this));

        postList = new ArrayList<>();
        postList.add(new Post(R.drawable.highlight1, "nuradinda", R.drawable.home1, "city light"));
        postList.add(new Post(R.drawable.feeds1, "araaa", R.drawable.home2, "OMG !! SO CUTEE !"));
        postList.add(new Post(R.drawable.highlight2, "andijingga", R.drawable.home3, "Beach...."));
        postList.add(new Post(R.drawable.feeds2, "adilahtenri", R.drawable.home4, "heloo, good morning"));
        postList.add(new Post(R.drawable.highlight3, "nashruddin", R.drawable.home5, "ala ala mirror selfie gituu..."));
        postList.add(new Post(R.drawable.feeds3, "afdhal", R.drawable.home6, "take a picture"));
        postList.add(new Post(R.drawable.highlight4, "anisaa", R.drawable.home7, "Safe fliht ! 🍔"));
        postList.add(new Post(R.drawable.feeds4, "anichan", R.drawable.home8, "uww, coffe is my favorite"));
        postList.add(new Post(R.drawable.highlight5, "ainiell", R.drawable.home9, "sejenak..."));
        postList.add(new Post(R.drawable.feeds5, "zaraaa", R.drawable.home10, "wait, lagi sedih jangan diganggu dulu"));

        postAdapter = new PostAdapter(postList, this);
        rvPosts.setAdapter(postAdapter);

        // 3. Navigasi
        btnGoToProfile = findViewById(R.id.btnGoToProfile);
        btnGoToProfile.setOnClickListener(v -> {
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
        });

        btnGoToPost = findViewById(R.id.btnGoToPost);
        btnGoToPost.setOnClickListener(v -> {
            Intent intent = new Intent(this, PostActivity.class);
            startActivity(intent);
        });
    }
}