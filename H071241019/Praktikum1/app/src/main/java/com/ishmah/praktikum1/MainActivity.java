package com.ishmah.praktikum1;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private ImageView ivFotoProfil;
    private TextView tvHeaderUsername, tvNama, tvBio, tvLink;
    private TextView tvPosts, tvFollowers, tvFollowing;
    private TextView btnEditProfile;

    private ActivityResultLauncher<Intent> editProfileLauncher;

    // State profil saat ini
    private String currentNama = "HODO.id – Eat With Rhythm";
    private String currentUsername = "hodo.id";
    private String currentBio = "Kitchen/cooking\nHomemade rice bowls bento 🍴, cooked by order\nOrder via WhatsApp 📱";
    private String currentLink = "linktr.ee/HODO.id";
    private String currentPosts = "12";
    private String currentFollowers = "77";
    private String currentFollowing = "2";
    private String currentFotoUri = "";
    private int currentFotoResId = R.drawable.ic_hodo_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi view
        ivFotoProfil   = findViewById(R.id.iv_foto_profil);
        tvHeaderUsername = findViewById(R.id.tv_header_username);
        tvNama         = findViewById(R.id.tv_nama);
        tvBio          = findViewById(R.id.tv_bio);
        tvLink         = findViewById(R.id.tv_link);
        tvPosts        = findViewById(R.id.tv_posts);
        tvFollowers    = findViewById(R.id.tv_followers);
        tvFollowing    = findViewById(R.id.tv_following);
        btnEditProfile = findViewById(R.id.btn_edit_profile);

        // Daftarkan launcher untuk menerima hasil dari EditProfileActivity
        editProfileLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Intent data = result.getData();

                    currentNama      = data.getStringExtra("nama");
                    currentUsername  = data.getStringExtra("username");
                    currentBio       = data.getStringExtra("bio");
                    currentLink      = data.getStringExtra("link");
                    currentPosts     = data.getStringExtra("posts");
                    currentFollowers = data.getStringExtra("followers");
                    currentFollowing = data.getStringExtra("following");
                    currentFotoUri   = data.getStringExtra("foto_uri");
                    currentFotoResId = data.getIntExtra("foto", R.drawable.ic_hodo_profile);

                    // Update tampilan profil
                    if (currentNama != null) tvNama.setText(currentNama);
                    if (currentUsername != null) tvHeaderUsername.setText(currentUsername);
                    if (currentBio != null) tvBio.setText(currentBio);
                    if (currentLink != null) tvLink.setText(currentLink);
                    if (currentPosts != null) tvPosts.setText(currentPosts);
                    if (currentFollowers != null) tvFollowers.setText(currentFollowers);
                    if (currentFollowing != null) tvFollowing.setText(currentFollowing);

                    // Update foto profil
                    if (currentFotoUri != null && !currentFotoUri.isEmpty()) {
                        ivFotoProfil.setImageURI(Uri.parse(currentFotoUri));
                    } else {
                        ivFotoProfil.setImageResource(currentFotoResId);
                    }
                }
            }
        );

        // Tombol Edit Profile
        btnEditProfile.setOnClickListener(v -> bukaEditProfile());

        // Klik foto / tombol ➕ juga buka Edit Profile
        ivFotoProfil.setOnClickListener(v -> bukaEditProfile());
        TextView tvGantiFoto = findViewById(R.id.tv_ganti_foto);
        tvGantiFoto.setOnClickListener(v -> bukaEditProfile());

        setupTabs();
    }

    private void bukaEditProfile() {
        Intent intent = new Intent(MainActivity.this, EditProfileActivity.class);
        intent.putExtra("nama",      currentNama);
        intent.putExtra("username",  currentUsername);
        intent.putExtra("bio",       currentBio);
        intent.putExtra("link",      currentLink);
        intent.putExtra("posts",     currentPosts);
        intent.putExtra("followers", currentFollowers);
        intent.putExtra("following", currentFollowing);
        intent.putExtra("foto_uri",  currentFotoUri);
        intent.putExtra("foto",      currentFotoResId);
        editProfileLauncher.launch(intent);
    }

    private void setupTabs() {
        LinearLayout tabPosts  = findViewById(R.id.tab_posts);
        LinearLayout tabReels  = findViewById(R.id.tab_reels);
        LinearLayout tabTagged = findViewById(R.id.tab_tagged);

        LinearLayout contentPosts  = findViewById(R.id.content_posts);
        LinearLayout contentReels  = findViewById(R.id.content_reels);
        LinearLayout contentTagged = findViewById(R.id.content_tagged);

        ImageView iconPosts  = tabPosts.findViewById(R.id.icon_posts);
        ImageView iconReels  = tabReels.findViewById(R.id.icon_reels);
        ImageView iconTagged = tabTagged.findViewById(R.id.icon_tagged);

        TextView textPosts  = tabPosts.findViewById(R.id.text_posts);
        TextView textReels  = tabReels.findViewById(R.id.text_reels);
        TextView textTagged = tabTagged.findViewById(R.id.text_tagged);

        // Posts aktif secara default
        setActiveTab(iconPosts, iconReels, iconTagged,
                     textPosts, textReels, textTagged,
                     contentPosts, contentReels, contentTagged);

        tabPosts.setOnClickListener(v ->
            setActiveTab(iconPosts, iconReels, iconTagged,
                         textPosts, textReels, textTagged,
                         contentPosts, contentReels, contentTagged));

        tabReels.setOnClickListener(v ->
            setActiveTab(iconReels, iconPosts, iconTagged,
                         textReels, textPosts, textTagged,
                         contentReels, contentPosts, contentTagged));

        tabTagged.setOnClickListener(v ->
            setActiveTab(iconTagged, iconPosts, iconReels,
                         textTagged, textPosts, textReels,
                         contentTagged, contentPosts, contentReels));
    }

    private void setActiveTab(ImageView activeIcon, ImageView inactiveIcon1, ImageView inactiveIcon2,
                               TextView activeText, TextView inactiveText1, TextView inactiveText2,
                               LinearLayout activeContent, LinearLayout inactiveContent1, LinearLayout inactiveContent2) {

        activeContent.setVisibility(View.VISIBLE);
        inactiveContent1.setVisibility(View.GONE);
        inactiveContent2.setVisibility(View.GONE);

        activeIcon.setColorFilter(ContextCompat.getColor(this, R.color.instagram_blue));
        inactiveIcon1.setColorFilter(ContextCompat.getColor(this, R.color.instagram_gray));
        inactiveIcon2.setColorFilter(ContextCompat.getColor(this, R.color.instagram_gray));

        activeText.setTextColor(ContextCompat.getColor(this, R.color.instagram_blue));
        inactiveText1.setTextColor(ContextCompat.getColor(this, R.color.instagram_gray));
        inactiveText2.setTextColor(ContextCompat.getColor(this, R.color.instagram_gray));

        activeText.setTypeface(null, Typeface.BOLD);
        inactiveText1.setTypeface(null, Typeface.NORMAL);
        inactiveText2.setTypeface(null, Typeface.NORMAL);
    }
}
