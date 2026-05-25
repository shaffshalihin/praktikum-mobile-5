package com.indira.tp222.model;

import com.indira.tp222.R;

public class Post {
    private int profileImage;
    private String username;
    private int postImage;
    private String postImageUri;
    private String caption;
    private int likes;
    private boolean isLiked;
    private String timeAgo;

    // Constructor untuk Home Feed
    public Post(int profileImage, String username, int postImage, String caption, int likes, String timeAgo) {
        this.profileImage = profileImage;
        this.username = username;
        this.postImage = postImage;
        this.caption = caption;
        this.likes = likes;
        this.isLiked = false;
        this.timeAgo = timeAgo;
        this.postImageUri = null;
    }

    // Constructor untuk Profile Post (drawable)
    public Post(int postImage, String caption) {
        this.postImage = postImage;
        this.caption = caption;
        this.profileImage = 0;
        this.username = "";
        this.likes = 0;
        this.isLiked = false;
        this.timeAgo = "";
        this.postImageUri = null;
    }

    // Constructor untuk Profile Post dari upload (URI)
    public Post(String postImageUri, String caption) {
        this.postImageUri = postImageUri;
        this.caption = caption;
        this.postImage = R.drawable.wlpp;
        this.profileImage = 0;
        this.username = "";
        this.likes = 0;
        this.isLiked = false;
        this.timeAgo = "";
    }

    public int getProfileImage() { return profileImage; }
    public String getUsername() { return username; }
    public int getPostImage() { return postImage; }
    public String getPostImageUri() { return postImageUri; }
    public String getCaption() { return caption; }
    public int getLikes() { return likes; }
    public boolean isLiked() { return isLiked; }
    public String getTimeAgo() { return timeAgo; }

    public void setLikes(int likes) { this.likes = likes; }
    public void setLiked(boolean liked) { isLiked = liked; }

    public boolean hasImageUri() {
        return postImageUri != null && !postImageUri.isEmpty();
    }
}