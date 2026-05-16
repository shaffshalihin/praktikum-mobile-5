package com.example.instagramclone.model;

import android.content.Context;
import com.example.instagramclone.R;
import com.example.instagramclone.utils.SharedPrefManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataDummy {

    private static DataDummy instance;
    private User currentUser;
    private List<Post> homeFeedPosts;
    private Map<String, User> userMap;
    private Map<String, List<Post>> userPostsMap;
    private List<Story> highlightStories;
    private List<Story> homeStories;

    public static final int[] PROFILE_RES = {
        R.drawable.profile1, R.drawable.profile2, R.drawable.profile3,
        R.drawable.profile4, R.drawable.profile5, R.drawable.profile6,
        R.drawable.profile7, R.drawable.profile8, R.drawable.profile9,
        R.drawable.profile10,
    };

    private DataDummy(Context context) {
        initData(context);
    }

    public static DataDummy getInstance(Context context) {
        if (instance == null) instance = new DataDummy(context);
        return instance;
    }

    public static DataDummy getInstance() {
        return instance;
    }

    private void initData(Context context) {
        SharedPrefManager prefManager = new SharedPrefManager(context);
        String currentUsername = prefManager.getCurrentUsername();
        currentUser = prefManager.getUser(currentUsername);

        userMap = new HashMap<>();
        userPostsMap = new HashMap<>();
        homeFeedPosts = new ArrayList<>();
        highlightStories = new ArrayList<>();
        homeStories = new ArrayList<>();

        // Setup default user mappings
        setupUserMap();

        if (currentUsername != null && currentUsername.equals("erchiveess")) {
            setupErchiveessData();
        } else if (currentUser != null) {
            setupNewUserData();
        }
    }

    private void setupUserMap() {
        // Daftarkan beberapa user dummy untuk relasi feed
        userMap.put("lngshot4sho", new User("lngshot4sho", "Long Shot", PROFILE_RES[1], "", 0, 0, 0));
        userMap.put("keonho.cortis", new User("keonho.cortis", "Keonho", PROFILE_RES[2], "", 0, 0, 0));
        userMap.put("woojinl.ngshot", new User("woojinl.ngshot", "Woojin", PROFILE_RES[3], "", 0, 0, 0));
    }

    private void setupErchiveessData() {
        // --- 1. SETUP HOME STORIES ---
        homeStories.add(new Story("hs0", "Cerita Anda", PROFILE_RES[0], "erchiveess"));
        homeStories.add(new Story("hs1", "lngshot4sho", PROFILE_RES[1], "lngshot4sho"));
        homeStories.add(new Story("hs2", "keonho.cortis", PROFILE_RES[2], "keonho.cortis"));
        homeStories.add(new Story("hs3", "woojinl.ngshot", PROFILE_RES[3], "woojinl.ngshot"));
        homeStories.add(new Story("hs4", "profile5", PROFILE_RES[4], "user5"));

        // --- 2. SETUP HOME FEED POSTS (Postingan dari orang lain) ---
        homeFeedPosts.add(new Post("h1", "lngshot4sho", PROFILE_RES[1], R.drawable.post_other1, "Performing live! 🔥", 1245, 89, "2 jam lalu"));
        homeFeedPosts.add(new Post("h2", "keonho.cortis", PROFILE_RES[2], R.drawable.post_other2, "Night vibes ✨", 850, 45, "5 jam lalu"));
        homeFeedPosts.add(new Post("h3", "woojinl.ngshot", PROFILE_RES[3], R.drawable.post_other3, "Studio time 🎸", 2100, 120, "Yesterday"));
        homeFeedPosts.add(new Post("h4", "lngshot4sho", PROFILE_RES[1], R.drawable.post_other4, "On the road again.", 1100, 30, "2 days ago"));

        // --- 3. SETUP PROFILE POSTS (Postingan milik erchiveess) ---
        List<Post> myPosts = new ArrayList<>();
        // Menggunakan img_post1 sampai img_post5 sesuai permintaan
        myPosts.add(new Post("p1", "erchiveess", PROFILE_RES[0], R.drawable.img_post1, "Memories #1", 500, 20, "1 Jan"));
        myPosts.add(new Post("p2", "erchiveess", PROFILE_RES[0], R.drawable.img_post2, "Memories #2", 600, 25, "5 Jan"));
        myPosts.add(new Post("p3", "erchiveess", PROFILE_RES[0], R.drawable.img_post3, "Golden hour ☀️", 750, 40, "10 Jan"));
        myPosts.add(new Post("p4", "erchiveess", PROFILE_RES[0], R.drawable.img_post4, "Coffee break", 420, 15, "15 Jan"));
        myPosts.add(new Post("p5", "erchiveess", PROFILE_RES[0], R.drawable.img_post5, "City lights", 900, 60, "20 Jan"));
        userPostsMap.put("erchiveess", myPosts);

        // --- 4. SETUP HIGHLIGHT STORIES (Muncul di profil erchiveess) ---
        highlightStories.add(new Story("hl1", "Bali", R.drawable.story1, "erchiveess"));
        highlightStories.add(new Story("hl2", "Food", R.drawable.story2, "erchiveess"));
        highlightStories.add(new Story("hl3", "Work", R.drawable.story3, "erchiveess"));
        highlightStories.add(new Story("hl4", "Friends", R.drawable.story4, "erchiveess"));
        highlightStories.add(new Story("hl5", "Concert", R.drawable.story5, "erchiveess"));
    }

    private void setupNewUserData() {
        if (currentUser == null) return;
        userPostsMap.put(currentUser.getUsername(), new ArrayList<>());
        homeStories.add(new Story("hs_me", "Cerita Anda", currentUser.getProfileImageRes(), currentUser.getUsername()));
    }

    public List<Post>  getHomeFeedPosts()  { return homeFeedPosts; }
    public List<Story> getHomeStories()    { return homeStories; }
    public List<Story> getHighlightStories() { return highlightStories; }
    public User        getCurrentUser()    { return currentUser; }

    public List<Post> getPostsForUser(String username) {
        return userPostsMap.containsKey(username) ? userPostsMap.get(username) : new ArrayList<>();
    }

    public User getUserByUsername(String username) {
        if (userMap.containsKey(username)) return userMap.get(username);
        if (currentUser != null && username.equals(currentUser.getUsername())) return currentUser;
        return currentUser;
    }

    public Post findPostById(String postId) {
        if (postId == null) return null;
        for (Post post : homeFeedPosts) {
            if (postId.equals(post.getId())) return post;
        }
        for (List<Post> posts : userPostsMap.values()) {
            for (Post post : posts) {
                if (postId.equals(post.getId())) return post;
            }
        }
        return null;
    }

    public void addProfilePost(Post post) {
        if (currentUser == null) return;
        List<Post> userPosts = userPostsMap.get(currentUser.getUsername());
        if (userPosts == null) {
            userPosts = new ArrayList<>();
            userPostsMap.put(currentUser.getUsername(), userPosts);
        }
        userPosts.add(0, post);
    }

    public static void resetInstance() {
        instance = null;
    }
}
