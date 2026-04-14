package com.example.instagramclone.activity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.instagramclone.R;

public class StoryDetailActivity extends AppCompatActivity {

    private Handler handler = new Handler();
    private int progress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_detail);

        String storyTitle = getIntent().getStringExtra("story_title");
        int storyRes      = getIntent().getIntExtra("story_res", R.drawable.story1);

        ImageView ivStory   = findViewById(R.id.iv_story);
        TextView tvTitle    = findViewById(R.id.tv_story_title);
        ProgressBar pbStory = findViewById(R.id.progress_story);
        ImageView btnClose  = findViewById(R.id.btn_close);

        tvTitle.setText(storyTitle);
        ivStory.setImageResource(storyRes);
        btnClose.setOnClickListener(v -> finish());

        pbStory.setMax(100);
        pbStory.setProgress(0);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                progress += 2;
                pbStory.setProgress(progress);
                
                if (progress < 100) {
                    handler.postDelayed(this, 100);
                } else {
                    finish();
                }
            }
        };
        handler.postDelayed(runnable, 100);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
