package com.ishmah.praktikumm2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    // Top Bar
    private TextView btnPlus, tvToolbarUsername;
    private ImageView btnMenuHamburger;

    // Profile Info
    private CircleImageView ivProfilePhoto;
    private TextView tvPostCount, tvFollowersCount, tvFollowingCount;
    private TextView tvFullName, tvBio;

    // Action Buttons (pakai LinearLayout)
    private LinearLayout btnEditProfile, btnShareProfile, btnAddPost, btnFollow;
    private TextView tvFollowText;
    private boolean isFollowing = false;

    // Story Highlights
    private RecyclerView rvHighlight;
    private StoryHighlightAdapter storyHighlightAdapter;
    private List<DataDummy.StoryHighlight> storyHighlights;

    // Tab Layout
    private LinearLayout tabPosts, tabReels, tabTagged;
    private ImageView iconPosts, iconReels, iconTagged;
    private View indicatorPosts, indicatorReels, indicatorTagged;
    private LinearLayout contentReels, contentTagged;

    // Content
    private RecyclerView rvProfileGrid;
    private ProfileFeedAdapter feedAdapter;
    private List<FeedItem> allFeedList;
    private List<FeedItem> uploadedFeeds;

    // Bottom Navigation
    private ImageView btnNavHome, btnNavSearch, btnNavAddPost, btnNavReels;
    private CircleImageView btnNavProfile;

    private String currentUsername;
    private UploadDataManager uploadDataManager;

    private final ActivityResultLauncher<Intent> editProfileLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Intent data = result.getData();
                    if (data.getData() != null) {
                        Glide.with(ProfileActivity.this).load(data.getData()).circleCrop().into(ivProfilePhoto);
                    }
                    String posts = data.getStringExtra("posts");
                    String followers = data.getStringExtra("followers");
                    String following = data.getStringExtra("following");
                    if (posts != null) tvPostCount.setText(posts);
                    if (followers != null) tvFollowersCount.setText(followers);
                    if (following != null) tvFollowingCount.setText(following);
                }
            });

    private final ActivityResultLauncher<Intent> uploadLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    try {
                        Intent data = result.getData();
                        String imageUriString = data.getStringExtra("image_uri");
                        String caption = data.getStringExtra("caption");

                        if (imageUriString == null || imageUriString.isEmpty()) {
                            Toast.makeText(ProfileActivity.this, "Gambar tidak valid", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Uri imageUri = Uri.parse(imageUriString);
                        int profileRes = getProfileImageRes(currentUsername);

                        FeedItem newItem = new FeedItem(
                                profileRes,
                                currentUsername,
                                imageUri,
                                caption,
                                "0", "0", "0", "0", "Baru saja"
                        );

                        uploadDataManager.addUploadedFeed(newItem);
                        loadAllFeeds();
                        feedAdapter.notifyDataSetChanged();
                        rvProfileGrid.scrollToPosition(0);
                        tvPostCount.setText(String.valueOf(allFeedList.size()));

                        Toast.makeText(ProfileActivity.this, "✅ Feed baru ditambahkan!", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(ProfileActivity.this, "Gagal upload: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        currentUsername = getIntent().getStringExtra("USERNAME");
        if (currentUsername == null) {
            currentUsername = DataDummy.getCurrentUsername();
        }
        DataDummy.setCurrentUsername(currentUsername);

        uploadDataManager = new UploadDataManager(this, currentUsername);

        initViews();
        setupProfileData();
        loadAllFeeds();
        setupStoryHighlights();
        setupTabs();
        setupBottomNav();
        setupListeners();

        hideFeaturesForOtherUsers();
    }

    private void initViews() {
        // Top Bar
        btnPlus = findViewById(R.id.btn_plus);
        tvToolbarUsername = findViewById(R.id.tv_toolbar_username);
        btnMenuHamburger = findViewById(R.id.btn_menu_hamburger);

        // Profile Info
        ivProfilePhoto = findViewById(R.id.iv_profile_photo);
        tvPostCount = findViewById(R.id.tv_post_count);
        tvFollowersCount = findViewById(R.id.tv_followers_count);
        tvFollowingCount = findViewById(R.id.tv_following_count);
        tvFullName = findViewById(R.id.tv_full_name);
        tvBio = findViewById(R.id.tv_bio);

        // Action Buttons (LinearLayout)
        btnEditProfile = findViewById(R.id.btn_edit_profile);
        btnShareProfile = findViewById(R.id.btn_share_profile);
        btnAddPost = findViewById(R.id.btn_add_post);
        btnFollow = findViewById(R.id.btn_follow);
        tvFollowText = findViewById(R.id.tv_follow_text);

        // Story Highlights
        rvHighlight = findViewById(R.id.rv_highlight);

        // Tab Layout
        tabPosts = findViewById(R.id.tab_posts);
        tabReels = findViewById(R.id.tab_reels);
        tabTagged = findViewById(R.id.tab_tagged);
        iconPosts = findViewById(R.id.icon_posts);
        iconReels = findViewById(R.id.icon_reels);
        iconTagged = findViewById(R.id.icon_tagged);
        indicatorPosts = findViewById(R.id.indicator_posts);
        indicatorReels = findViewById(R.id.indicator_reels);
        indicatorTagged = findViewById(R.id.indicator_tagged);
        contentReels = findViewById(R.id.content_reels);
        contentTagged = findViewById(R.id.content_tagged);

        // Content Grid
        rvProfileGrid = findViewById(R.id.rv_profile_grid);

        // Bottom Navigation
        btnNavHome = findViewById(R.id.btn_nav_home);
        btnNavSearch = findViewById(R.id.btn_nav_search);
        btnNavAddPost = findViewById(R.id.btn_nav_add_post);
        btnNavReels = findViewById(R.id.btn_nav_reels);
        btnNavProfile = findViewById(R.id.btn_nav_profile);
    }

    private void hideFeaturesForOtherUsers() {
        if (!currentUsername.equals("hodo.id")) {
            if (btnEditProfile != null) btnEditProfile.setVisibility(View.GONE);
            if (btnAddPost != null) btnAddPost.setVisibility(View.GONE);
            if (btnPlus != null) btnPlus.setVisibility(View.GONE);
            if (btnFollow != null) btnFollow.setVisibility(View.VISIBLE);

            android.widget.TextView badgePlus = findViewById(R.id.badge_plus);
            if (badgePlus != null) badgePlus.setVisibility(View.GONE);
        }
    }

    private int getProfileImageRes(String username) {
        DataDummy.ProfileData profileData = DataDummy.getProfileData(username);
        if (profileData != null) {
            return profileData.profileImageRes;
        }
        return R.drawable.ic_hodo_profile;
    }

    private void setupProfileData() {
        DataDummy.ProfileData profileData = DataDummy.getProfileData(currentUsername);

        if (profileData != null) {
            tvToolbarUsername.setText(currentUsername);
            tvFullName.setText(profileData.fullName);
            tvBio.setText(profileData.bio);
            tvPostCount.setText(profileData.postsCount);
            tvFollowersCount.setText(profileData.followersCount);
            tvFollowingCount.setText(profileData.followingCount);
            Glide.with(this).load(profileData.profileImageRes).circleCrop().into(ivProfilePhoto);
        } else {
            tvToolbarUsername.setText(currentUsername);
            tvFullName.setText(currentUsername);
            tvBio.setText("");
            tvPostCount.setText("0");
            tvFollowersCount.setText("0");
            tvFollowingCount.setText("0");
            Glide.with(this).load(R.drawable.ic_hodo_profile).circleCrop().into(ivProfilePhoto);
        }
    }

    private void loadAllFeeds() {
        allFeedList = new ArrayList<>();

        uploadedFeeds = uploadDataManager.getUploadedFeeds();
        if (uploadedFeeds != null && !uploadedFeeds.isEmpty()) {
            for (FeedItem feed : uploadedFeeds) {
                if (feed.getUsername().equals(currentUsername)) {
                    allFeedList.add(feed);
                }
            }
        }

        List<FeedItem> dummyFeeds = DataDummy.getProfileFeedForUser(currentUsername);
        if (dummyFeeds != null && !dummyFeeds.isEmpty()) {
            allFeedList.addAll(dummyFeeds);
        }

        tvPostCount.setText(String.valueOf(allFeedList.size()));

        feedAdapter = new ProfileFeedAdapter(this, allFeedList);
        rvProfileGrid.setLayoutManager(new GridLayoutManager(this, 3));
        rvProfileGrid.setAdapter(feedAdapter);
    }

    private void setupStoryHighlights() {
        storyHighlights = DataDummy.getStoryHighlights(currentUsername);

        if (storyHighlights != null && !storyHighlights.isEmpty()) {
            rvHighlight.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            storyHighlightAdapter = new StoryHighlightAdapter(storyHighlights, story -> {
                Intent intent = new Intent(ProfileActivity.this, StoryDetailActivity.class);
                intent.putExtra("story_image", story.imageRes);
                intent.putExtra("story_name", story.title);
                intent.putExtra("username", currentUsername);
                startActivity(intent);
            });
            rvHighlight.setAdapter(storyHighlightAdapter);
        }
    }

    private void setupTabs() {
        rvProfileGrid.setVisibility(View.VISIBLE);
        contentReels.setVisibility(View.GONE);
        contentTagged.setVisibility(View.GONE);
        updateTabColors(0);

        tabPosts.setOnClickListener(v -> {
            rvProfileGrid.setVisibility(View.VISIBLE);
            contentReels.setVisibility(View.GONE);
            contentTagged.setVisibility(View.GONE);
            updateTabColors(0);
        });

        tabReels.setOnClickListener(v -> {
            rvProfileGrid.setVisibility(View.GONE);
            contentReels.setVisibility(View.VISIBLE);
            contentTagged.setVisibility(View.GONE);
            updateTabColors(1);
        });

        tabTagged.setOnClickListener(v -> {
            rvProfileGrid.setVisibility(View.GONE);
            contentReels.setVisibility(View.GONE);
            contentTagged.setVisibility(View.VISIBLE);
            updateTabColors(2);
        });
    }

    private void updateTabColors(int activeTab) {
        int blue = ContextCompat.getColor(this, R.color.instagram_blue);
        int gray = ContextCompat.getColor(this, R.color.instagram_gray);
        int transparent = android.R.color.transparent;

        iconPosts.setColorFilter(activeTab == 0 ? blue : gray);
        iconReels.setColorFilter(activeTab == 1 ? blue : gray);
        iconTagged.setColorFilter(activeTab == 2 ? blue : gray);

        indicatorPosts.setBackgroundColor(activeTab == 0 ? blue : getResources().getColor(transparent));
        indicatorReels.setBackgroundColor(activeTab == 1 ? blue : getResources().getColor(transparent));
        indicatorTagged.setBackgroundColor(activeTab == 2 ? blue : getResources().getColor(transparent));
    }

    private void setupBottomNav() {
        btnNavProfile.setBorderColor(ContextCompat.getColor(this, R.color.instagram_blue));

        btnNavHome.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });

        btnNavSearch.setOnClickListener(v -> {
            Toast.makeText(this, "Search feature coming soon", Toast.LENGTH_SHORT).show();
        });

        btnNavAddPost.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, UploadActivity.class);
            uploadLauncher.launch(intent);
        });

        btnNavReels.setOnClickListener(v -> {
            Toast.makeText(this, "Reels feature coming soon", Toast.LENGTH_SHORT).show();
        });

        btnNavProfile.setOnClickListener(v -> {
            // Already on profile
        });
    }

    private void setupListeners() {
        btnPlus.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, UploadActivity.class);
            uploadLauncher.launch(intent);
        });

        btnMenuHamburger.setOnClickListener(v -> {
            Toast.makeText(this, "Menu", Toast.LENGTH_SHORT).show();
        });

        btnEditProfile.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
            editProfileLauncher.launch(intent);
        });

        btnShareProfile.setOnClickListener(v -> {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out " + currentUsername + "'s profile on HODO app!");
            startActivity(Intent.createChooser(shareIntent, "Share profile via"));
        });

        if (btnFollow != null) {
            btnFollow.setOnClickListener(v -> {
                isFollowing = !isFollowing;
                if (isFollowing) {
                    btnFollow.setBackgroundResource(R.drawable.bg_btn_profile_action);
                    if (tvFollowText != null) {
                        tvFollowText.setText("Mengikuti");
                        tvFollowText.setTextColor(android.graphics.Color.BLACK);
                    }
                } else {
                    btnFollow.setBackgroundResource(R.drawable.bg_btn_follow);
                    if (tvFollowText != null) {
                        tvFollowText.setText("Ikuti");
                        tvFollowText.setTextColor(android.graphics.Color.WHITE);
                    }
                }
            });
        }

        btnAddPost.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, UploadActivity.class);
            uploadLauncher.launch(intent);
        });
    }
}