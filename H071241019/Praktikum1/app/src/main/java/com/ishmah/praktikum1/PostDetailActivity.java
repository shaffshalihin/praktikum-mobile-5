package com.ishmah.praktikum1;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import de.hdodenhof.circleimageview.CircleImageView;

public class PostDetailActivity extends AppCompatActivity {

    private static final String PREF_NAME = "profile_pref";

    private static final int[] POST_DRAWABLES = {
        R.drawable.ic_post_1, R.drawable.ic_post_2, R.drawable.ic_post_3,
        R.drawable.ic_post_4, R.drawable.ic_post_5, R.drawable.ic_post_6,
        R.drawable.ic_post_7, R.drawable.ic_post_8, R.drawable.ic_post_9
    };

    private static final String[] CAPTIONS = {
        " Nasi bowl ayam teriyaki spesial hari ini! 🍱",
        " Bento box favorit pelanggan setia kami 🥢",
        " Fresh salad bowl dengan dressing homemade 🥗",
        " Ayam bakar madu dengan sambal matah 🔥",
        " Rice bowl salmon teriyaki — sold out tiap hari! 🐟",
        " Bento sehat untuk bekal kerja kamu 💼",
        " Promo spesial akhir pekan, order sekarang! 🎉",
        " Tumis sayur campur dengan ayam crispy 🥦",
        " Menu baru: Chicken Katsu Rice Bowl 🍚"
    };

    private static final int[] LIKES    = { 128, 245, 312, 87, 193, 156, 74, 421, 98 };
    private static final int[] COMMENTS = { 24, 38, 51, 12, 29, 44, 8, 67, 15 };

    private ImageView ivPostImage, ivBack, ivLike, ivComment, ivRepost, ivSend, ivSave;
    private TextView ivMenu, tvUsername, tvCaptionUsername, tvCaption, tvLikeCount, tvCommentCount;
    private CircleImageView ivProfile;

    private boolean isLiked = false;
    private int currentLikes = 0;

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
        ivBack      = findViewById(R.id.iv_back);
        ivMenu      = findViewById(R.id.iv_menu);
        ivProfile   = findViewById(R.id.iv_profile);
        tvUsername  = findViewById(R.id.tv_username);
        ivLike      = findViewById(R.id.iv_like);
        ivComment   = findViewById(R.id.iv_comment);
        ivRepost    = findViewById(R.id.iv_repost);
        ivSend      = findViewById(R.id.iv_send);
        ivSave      = findViewById(R.id.iv_save);
        tvCaptionUsername = findViewById(R.id.tv_caption_username);
        tvCaption         = findViewById(R.id.tv_caption);
        tvLikeCount       = findViewById(R.id.tv_like_count);
        tvCommentCount    = findViewById(R.id.tv_comment_count);
    }

    private void loadData() {
        int index = getIntent().getIntExtra("post_index", 0);
        if (index < 0 || index >= POST_DRAWABLES.length) index = 0;

        ivPostImage.setImageResource(POST_DRAWABLES[index]);

        SharedPreferences prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        String username = prefs.getString("username", "");
        if (username.isEmpty()) username = "username";
        String photoUri = prefs.getString("photo_uri", "");

        tvUsername.setText(username);
        tvCaptionUsername.setText(username + " ");
        tvCaption.setText(CAPTIONS[index]);

        currentLikes = LIKES[index];
        tvLikeCount.setText(currentLikes + " likes");
        tvCommentCount.setText("View all " + COMMENTS[index] + " comments");

        if (!photoUri.isEmpty()) {
            try { ivProfile.setImageURI(Uri.parse(photoUri)); }
            catch (Exception ignored) { ivProfile.setImageResource(R.drawable.ic_hodo_profile); }
        } else {
            ivProfile.setImageResource(R.drawable.ic_hodo_profile);
        }
    }

    private void setupListeners() {
        ivBack.setOnClickListener(v -> finish());

        ivMenu.setOnClickListener(v ->
            Toast.makeText(this, "Menu", Toast.LENGTH_SHORT).show());

        ivLike.setOnClickListener(v -> {
            if (isLiked) {
                ivLike.setColorFilter(ContextCompat.getColor(this, R.color.instagram_dark));
                currentLikes--;
                isLiked = false;
            } else {
                ivLike.setColorFilter(ContextCompat.getColor(this, R.color.instagram_red));
                currentLikes++;
                isLiked = true;
            }
            tvLikeCount.setText(currentLikes + " likes");
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
