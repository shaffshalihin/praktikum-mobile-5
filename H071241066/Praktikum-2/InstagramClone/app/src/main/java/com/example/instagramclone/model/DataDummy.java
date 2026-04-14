package com.example.instagramclone.model;

import com.example.instagramclone.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataDummy {

    private static DataDummy instance;

    private User currentUser;

    private List<Post> homeFeedPosts;

    private Map<String, User>       userMap;
    
    private Map<String, List<Post>> userPostsMap;

    private List<Story> highlightStories;
    
    private List<Story> homeStories;

    public static final int[] PROFILE_RES = {
        R.drawable.profile1, R.drawable.profile2, R.drawable.profile3,
        R.drawable.profile4, R.drawable.profile5, R.drawable.profile6,
        R.drawable.profile7, R.drawable.profile8, R.drawable.profile9,
        R.drawable.profile10,
    };

    public static final int[] OTHER_POST_RES = {
        R.drawable.post_other1, R.drawable.post_other2, R.drawable.post_other3,
        R.drawable.post_other4, R.drawable.post_other5, R.drawable.post_other6,
        R.drawable.post_other7, R.drawable.post_other8, R.drawable.post_other9,
        R.drawable.post_other10,
    };

    public static final int[] MY_POST_RES = {
        R.drawable.img_post1, R.drawable.img_post2, R.drawable.img_post3,
        R.drawable.img_post4, R.drawable.img_post5,
    };

    public static final int[] STORY_RES = {
        R.drawable.story1, R.drawable.story2, R.drawable.story3, R.drawable.story4,
        R.drawable.story5, R.drawable.story6, R.drawable.story7,
    };

    private DataDummy() { initData(); }

    public static DataDummy getInstance() {
        if (instance == null) instance = new DataDummy();
        return instance;
    }

    private void initData() {
        currentUser = new User("erchiveess","morejoyrideplease she",
            PROFILE_RES[0], "yg dekat\" aj\n@keonho.cortis @woojinl.ngshot 's noona",
            5, 76, 221);

        userMap = new HashMap<>();
        userMap.put("erchiveess", currentUser);
        userMap.put("lngshot4sho",    new User("lngshot4sho",    "LNGSHOT Official",   PROFILE_RES[1], "Official account of LNGSHOT 🎵", 120, 50200, 312));
        userMap.put("nnajebadside",   new User("nnajebadside",   "Naje Bad Side",      PROFILE_RES[2], "📸 photographer & traveler", 34, 980, 540));
        userMap.put("cakeby2112_",    new User("cakeby2112_",    "Cake by 21.12",      PROFILE_RES[3], "🎂 Custom cake orders open! DM me", 67, 2340, 210));
        userMap.put("nisyejadiara",   new User("nisyejadiara",   "Nisye Jadiara",      PROFILE_RES[4], "just vibes 🌿", 22, 430, 390));
        userMap.put("dayggongju",     new User("dayggongju",     "Day Gongju",         PROFILE_RES[5], "kpop & photocard collector 📮", 88, 3210, 760));
        userMap.put("syukronnthxx",   new User("syukronnthxx",   "Syukron",            PROFILE_RES[6], "✨ late night thoughts", 15, 220, 180));
        userMap.put("archves1",       new User("archves1",       "Archives",           PROFILE_RES[7], "archive account 🗃️", 45, 560, 300));
        userMap.put("keonho.cortis",  new User("keonho.cortis",  "Keonho CORTIS",      PROFILE_RES[8], "CORTIS member 🎤", 200, 98400, 890));
        userMap.put("woojinl.ngshot", new User("woojinl.ngshot", "Woojin LNGSHOT",     PROFILE_RES[9], "LNGSHOT member | Woojin 🌸", 310, 145000, 1020));

        userPostsMap = new HashMap<>();

        List<Post> myPosts = new ArrayList<>();
        myPosts.add(new Post("p1","erchiveess",PROFILE_RES[0],MY_POST_RES[0],"Dawin · Jumpshot 🎵 take by : icuss",10,1,"5 hari yang lalu"));
        myPosts.add(new Post("p2","erchiveess",PROFILE_RES[0],MY_POST_RES[1],"RYUL of LNGSHOT · Thinking 💭",24,3,"1 minggu yang lalu"));
        myPosts.add(new Post("p3","erchiveess",PROFILE_RES[0],MY_POST_RES[2],"uni lyfe moment 📚 Balance 21",18,2,"2 minggu yang lalu"));
        myPosts.add(new Post("p4","erchiveess",PROFILE_RES[0],MY_POST_RES[3],"JoyRide · CORTIS 🎶",31,5,"3 minggu yang lalu"));
        myPosts.add(new Post("p5","erchiveess",PROFILE_RES[0],MY_POST_RES[4],"vibes only ✨",45,7,"1 bulan yang lalu"));
        userPostsMap.put("erchiveess", myPosts);

        addPosts("lngshot4sho", PROFILE_RES[1], 0);
        addPosts("nnajebadside", PROFILE_RES[2], 1);
        addPosts("cakeby2112_", PROFILE_RES[3], 2);
        addPosts("nisyejadiara", PROFILE_RES[4], 3);
        addPosts("dayggongju", PROFILE_RES[5], 4);
        addPosts("syukronnthxx", PROFILE_RES[6], 5);
        addPosts("archves1", PROFILE_RES[7], 6);
        addPosts("keonho.cortis", PROFILE_RES[8], 7);
        addPosts("woojinl.ngshot", PROFILE_RES[9], 8);

        homeFeedPosts = new ArrayList<>();
        homeFeedPosts.add(new Post("h1","lngshot4sho",   PROFILE_RES[1],OTHER_POST_RES[0],"WOOJIN of LNGSHOT performing Boo Thang 🔥 #lngshot",1245,89,"2 jam yang lalu"));
        homeFeedPosts.add(new Post("h2","nnajebadside",  PROFILE_RES[2],OTHER_POST_RES[1],"Golden hour vibes ✨",432,21,"3 jam yang lalu"));
        homeFeedPosts.add(new Post("h3","cakeby2112_",   PROFILE_RES[3],OTHER_POST_RES[2],"New cake drop! 🎂 custom orders open DM me",876,54,"5 jam yang lalu"));
        homeFeedPosts.add(new Post("h4","nisyejadiara",  PROFILE_RES[4],OTHER_POST_RES[3],"Weekend getaway 🌿",321,18,"6 jam yang lalu"));
        homeFeedPosts.add(new Post("h5","dayggongju",    PROFILE_RES[5],OTHER_POST_RES[4],"Training Day photocard HD scan #LNGSHOT #RYUL",2341,167,"8 jam yang lalu"));
        homeFeedPosts.add(new Post("h6","syukronnthxx",  PROFILE_RES[6],OTHER_POST_RES[5],"Late night thoughts 🌙",198,12,"10 jam yang lalu"));
        homeFeedPosts.add(new Post("h7","archves1",      PROFILE_RES[7],OTHER_POST_RES[6],"Archive dump 📸",567,34,"12 jam yang lalu"));
        homeFeedPosts.add(new Post("h8","keonho.cortis", PROFILE_RES[8],OTHER_POST_RES[7],"Studio session 🎵 new music coming soon",3421,289,"1 hari yang lalu"));
        homeFeedPosts.add(new Post("h9","woojinl.ngshot",PROFILE_RES[9],OTHER_POST_RES[8],"Cherry blossom season 🌸 #spring",5678,423,"1 hari yang lalu"));
        homeFeedPosts.add(new Post("h10","archves1",     PROFILE_RES[7],OTHER_POST_RES[9],"Behind the scenes 🎬",234,15,"2 hari yang lalu"));

        highlightStories = new ArrayList<>();
        String[] titles = {"uni lyfe","📸","🧸💗","🏃","shs","travel","food"};
        for (int i = 0; i < titles.length; i++) {
            highlightStories.add(new Story("s"+(i+1), titles[i], STORY_RES[i % STORY_RES.length], "erchiveess"));
        }
            
        homeStories = new ArrayList<>();
        homeStories.add(new Story("hs0", "Cerita Anda", PROFILE_RES[0], "erchiveess"));
        homeStories.add(new Story("hs1", "lngshot4sho", PROFILE_RES[1], "lngshot4sho"));
        homeStories.add(new Story("hs2", "nnajebadside", PROFILE_RES[2], "nnajebadside"));
        homeStories.add(new Story("hs3", "cakeby2112_", PROFILE_RES[3], "cakeby2112_"));
        homeStories.add(new Story("hs4", "nisyejadiara", PROFILE_RES[4], "nisyejadiara"));
        homeStories.add(new Story("hs5", "dayggongju", PROFILE_RES[5], "dayggongju"));
        homeStories.add(new Story("hs6", "syukronnthxx", PROFILE_RES[6], "syukronnthxx"));
        homeStories.add(new Story("hs7", "archves1", PROFILE_RES[7], "archves1"));
        homeStories.add(new Story("hs8", "keonho.cortis", PROFILE_RES[8], "keonho.cortis"));
        homeStories.add(new Story("hs9", "woojinl.ngshot", PROFILE_RES[9], "woojinl.ngshot"));
    }
    
    private void addPosts(String user, int prof, int startIdx) {
        List<Post> posts = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int resIdx = (startIdx + i) % OTHER_POST_RES.length;
            posts.add(new Post(user+i, user, prof, OTHER_POST_RES[resIdx], "Caption post "+i+" by "+user, 100+i, 5+i, i+" hari yang lalu"));
        }
        userPostsMap.put(user, posts);
    }

    public List<Post>  getHomeFeedPosts()               { return homeFeedPosts; }
    public List<Story> getHighlightStories()            { return highlightStories; }
    public List<Story> getHomeStories()                 { return homeStories; }
    public User        getCurrentUser()                 { return currentUser; }

    public List<Post>  getPostsForUser(String username) {
        List<Post> posts = userPostsMap.get(username);
        return posts != null ? posts : new ArrayList<>();
    }

    public User getUserByUsername(String username) {
        User u = userMap.get(username);
        return u != null ? u : currentUser;
    }

    public void addProfilePost(Post post) {
        if (!userPostsMap.containsKey("erchiveess")) {
            userPostsMap.put("erchiveess", new ArrayList<>());
        }
        userPostsMap.get("erchiveess").add(0, post);
        homeFeedPosts.add(0, post);
        currentUser.setPostCount(currentUser.getPostCount() + 1);
    }

    public Post findPostById(String id) {
        if (id == null) return null;
        for (Post p : homeFeedPosts) if (id.equals(p.getId())) return p;
        for (List<Post> list : userPostsMap.values())
            for (Post p : list) if (id.equals(p.getId())) return p;
        return null;
    }
}
