package com.ishmah.praktikumm2;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

public class StoryDetailActivity extends AppCompatActivity {

    private ImageView ivStoryImage;
    private CircleImageView ivProfile;
    private TextView tvUsername, tvTime, ivMenu, ivClose;
    private ImageView ivLike, ivSend;  // GANTI JADI ImageView
    private EditText etSendMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_detail);

        initViews();
        loadData();
        setupListeners();
    }

    private void initViews() {
        ivStoryImage = findViewById(R.id.iv_story_image);
        ivProfile = findViewById(R.id.iv_profile);
        tvUsername = findViewById(R.id.tv_username);
        tvTime = findViewById(R.id.tv_time);
        ivMenu = findViewById(R.id.iv_menu);
        ivClose = findViewById(R.id.iv_close);
        ivLike = findViewById(R.id.iv_like);
        ivSend = findViewById(R.id.iv_send);
        etSendMessage = findViewById(R.id.et_send_message);
    }

    private void loadData() {
        int storyImage = getIntent().getIntExtra("story_image", R.drawable.ic_story_1);
        Glide.with(this).load(storyImage).centerCrop().into(ivStoryImage);

        // Ambil username: dari key "username" (ProfileActivity) atau "story_name" (MainActivity)
        String username = getIntent().getStringExtra("username");
        if (username == null || username.isEmpty()) {
            username = getIntent().getStringExtra("story_name");
        }
        if (username == null || username.isEmpty()) {
            username = "hodo.id";
        }
        tvUsername.setText(username);
        tvTime.setText("2 jam lalu");
        Glide.with(this).load(getProfileImageForUser(username)).circleCrop().into(ivProfile);
    }

    private int getProfileImageForUser(String username) {
        switch (username) {
            case "photography":   return R.drawable.ic_photo_profile;
            case "126alters":     return R.drawable.ic_126_profile;
            case "ishmahhjkl":   return R.drawable.ic_ishma_profile;
            case "sre_unhas":     return R.drawable.ic_sre_profile;
            case "foodie_mksr":   return R.drawable.ic_user6_profile;
            case "snapshot_id":   return R.drawable.ic_user7_profile;
            case "alters_style":  return R.drawable.ic_user8_profile;
            case "wanderlust_ish":return R.drawable.ic_user9_profile;
            case "greentech_unhas":return R.drawable.ic_user10_profile;
            default:              return R.drawable.ic_hodo_profile;
        }
    }

    private void setupListeners() {
        ivClose.setOnClickListener(v -> finish());

        ivMenu.setOnClickListener(v -> {
            Toast.makeText(this, "Menu", Toast.LENGTH_SHORT).show();
        });

        ivLike.setOnClickListener(v -> {
            Toast.makeText(this, "❤️ Menyukai story", Toast.LENGTH_SHORT).show();
        });

        ivSend.setOnClickListener(v -> {
            Toast.makeText(this, "✈️ Mengirim ke teman", Toast.LENGTH_SHORT).show();
        });

        etSendMessage.setOnClickListener(v -> {
            Toast.makeText(this, "✉️ Send message", Toast.LENGTH_SHORT).show();
        });
    }
}