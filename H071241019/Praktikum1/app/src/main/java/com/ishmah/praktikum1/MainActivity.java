package com.ishmah.praktikum1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private static final String PREF_NAME   = "profile_pref";
    private static final String KEY_NAME    = "name";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_BIO     = "bio";
    private static final String KEY_LINK      = "link";
    private static final String KEY_PHOTO     = "photo_uri";
    private static final String KEY_POSTS     = "posts";
    private static final String KEY_FOLLOWERS = "followers";
    private static final String KEY_FOLLOWING = "following";

    private CircleImageView ivFotoProfil;
    private TextView tvHeaderUsername, tvNama, tvBio, tvLink;
    private SharedPreferences prefs;
    private ActivityResultLauncher<Intent> editLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefs            = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        ivFotoProfil     = findViewById(R.id.iv_foto_profil);
        tvHeaderUsername = findViewById(R.id.tv_header_username);
        tvNama           = findViewById(R.id.tv_nama);
        tvBio            = findViewById(R.id.tv_bio);
        tvLink           = findViewById(R.id.tv_link);

        editLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> { if (result.getResultCode() == RESULT_OK) loadProfile(); }
        );

        View btnEdit   = findViewById(R.id.btn_edit_profile);
        View badgePlus = findViewById(R.id.tv_ganti_foto);

        btnEdit.setOnClickListener(v -> goEdit());
        ivFotoProfil.setOnClickListener(v -> goEdit());
        if (badgePlus != null) badgePlus.setOnClickListener(v -> goEdit());

        setupPostClicks();
        setupTabs();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadProfile();
    }

    private void goEdit() {
        editLauncher.launch(new Intent(this, EditProfileActivity.class));
    }

    private void loadProfile() {
        String name      = prefs.getString(KEY_NAME, "");
        String username  = prefs.getString(KEY_USERNAME, "");
        String bio       = prefs.getString(KEY_BIO, "");
        String link      = prefs.getString(KEY_LINK, "");
        String photoUri  = prefs.getString(KEY_PHOTO, "");
        String posts     = prefs.getString(KEY_POSTS, "0");
        String followers = prefs.getString(KEY_FOLLOWERS, "0");
        String following = prefs.getString(KEY_FOLLOWING, "0");

        tvHeaderUsername.setText(username.isEmpty() ? "username" : username);

        tvNama.setText(name);
        tvNama.setVisibility(name.isEmpty() ? View.GONE : View.VISIBLE);

        tvBio.setText(bio);
        tvBio.setVisibility(bio.isEmpty() ? View.GONE : View.VISIBLE);

        tvLink.setText(link);
        tvLink.setVisibility(link.isEmpty() ? View.GONE : View.VISIBLE);

        android.widget.TextView tvPosts = findViewById(R.id.tv_posts);
        android.widget.TextView tvFollowers = findViewById(R.id.tv_followers);
        android.widget.TextView tvFollowing = findViewById(R.id.tv_following);
        if (tvPosts != null)     tvPosts.setText(posts.isEmpty() ? "0" : posts);
        if (tvFollowers != null) tvFollowers.setText(followers.isEmpty() ? "0" : followers);
        if (tvFollowing != null) tvFollowing.setText(following.isEmpty() ? "0" : following);

        if (!photoUri.isEmpty()) {
            try {
                ivFotoProfil.setImageURI(Uri.parse(photoUri));
            } catch (Exception ignored) {
                ivFotoProfil.setImageResource(R.drawable.ic_hodo_profile);
            }
        } else {
            ivFotoProfil.setImageResource(R.drawable.ic_hodo_profile);
        }
    }

    private void setupPostClicks() {
        int[] postViewIds = {
            R.id.iv_post_1, R.id.iv_post_2, R.id.iv_post_3,
            R.id.iv_post_4, R.id.iv_post_5, R.id.iv_post_6,
            R.id.iv_post_7, R.id.iv_post_8, R.id.iv_post_9
        };
        for (int i = 0; i < postViewIds.length; i++) {
            final int index = i;
            View v = findViewById(postViewIds[i]);
            if (v != null) v.setOnClickListener(view -> openPost(index));
        }
    }

    private void openPost(int index) {
        Intent intent = new Intent(this, PostDetailActivity.class);
        intent.putExtra("post_index", index);
        startActivity(intent);
    }

    private void setupTabs() {
        LinearLayout tabPosts  = findViewById(R.id.tab_posts);
        LinearLayout tabReels  = findViewById(R.id.tab_reels);
        LinearLayout tabTagged = findViewById(R.id.tab_tagged);

        LinearLayout cPosts  = findViewById(R.id.content_posts);
        LinearLayout cReels  = findViewById(R.id.content_reels);
        LinearLayout cTagged = findViewById(R.id.content_tagged);

        ImageView iPosts  = findViewById(R.id.icon_posts);
        ImageView iReels  = findViewById(R.id.icon_reels);
        ImageView iTagged = findViewById(R.id.icon_tagged);

        View indPosts  = findViewById(R.id.indicator_posts);
        View indReels  = findViewById(R.id.indicator_reels);
        View indTagged = findViewById(R.id.indicator_tagged);

        tabPosts.setOnClickListener(v ->
            switchTab(cPosts, cReels, cTagged, iPosts, iReels, iTagged, indPosts, indReels, indTagged));
        tabReels.setOnClickListener(v ->
            switchTab(cReels, cPosts, cTagged, iReels, iPosts, iTagged, indReels, indPosts, indTagged));
        tabTagged.setOnClickListener(v ->
            switchTab(cTagged, cPosts, cReels, iTagged, iPosts, iReels, indTagged, indPosts, indReels));
    }

    private void switchTab(LinearLayout show, LinearLayout h1, LinearLayout h2,
                           ImageView iActive, ImageView i1, ImageView i2,
                           View indActive, View ind1, View ind2) {
        show.setVisibility(View.VISIBLE);
        h1.setVisibility(View.GONE);
        h2.setVisibility(View.GONE);

        iActive.setAlpha(1.0f);
        i1.setAlpha(0.35f);
        i2.setAlpha(0.35f);

        indActive.setVisibility(View.VISIBLE);
        ind1.setVisibility(View.INVISIBLE);
        ind2.setVisibility(View.INVISIBLE);
    }
}
