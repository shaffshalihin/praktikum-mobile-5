package com.indira.tp222.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.indira.tp222.R;
import com.indira.tp222.adapter.StoryPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class StoryDetailActivity extends AppCompatActivity {

    private ImageView ivClose, ivMenu, ivSend;
    private TextView tvUsername, tvTime, tvPageIndicator;
    private EditText etReply;
    private ViewPager2 viewPager;

    private List<String> storyImages;
    private String highlightTitle;
    private int currentPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_detail);

        // Inisialisasi view
        ivClose = findViewById(R.id.ivClose);
        ivMenu = findViewById(R.id.ivMenu);
        ivSend = findViewById(R.id.ivSend);
        tvUsername = findViewById(R.id.tvUsername);
        tvTime = findViewById(R.id.tvTime);
        tvPageIndicator = findViewById(R.id.tvPageIndicator);
        etReply = findViewById(R.id.etReply);
        viewPager = findViewById(R.id.viewPager);

        // Ambil data dari Intent
        Intent intent = getIntent();
        highlightTitle = intent.getStringExtra("HIGHLIGHT_TITLE");
        storyImages = intent.getStringArrayListExtra("STORY_IMAGES");

        if (storyImages == null) {
            storyImages = new ArrayList<>();
            storyImages.add(String.valueOf(R.drawable.wlpp));
        }

        // Setup ViewPager2
        StoryPagerAdapter adapter = new StoryPagerAdapter(this, storyImages);
        viewPager.setAdapter(adapter);

        // Update page indicator
        tvPageIndicator.setText((currentPosition + 1) + " / " + storyImages.size());

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                currentPosition = position;
                tvPageIndicator.setText((position + 1) + " / " + storyImages.size());
            }
        });

        // Set data
        tvUsername.setText(highlightTitle != null ? highlightTitle : "Story");
        tvTime.setText("· Just now");

        // Close button
        ivClose.setOnClickListener(v -> finish());

        // Send message
        ivSend.setOnClickListener(v -> {
            String message = etReply.getText().toString().trim();
            if (!message.isEmpty()) {
                Toast.makeText(this, "Message sent: " + message, Toast.LENGTH_SHORT).show();
                etReply.setText("");
            } else {
                Toast.makeText(this, "Type a message first", Toast.LENGTH_SHORT).show();
            }
        });
    }
}