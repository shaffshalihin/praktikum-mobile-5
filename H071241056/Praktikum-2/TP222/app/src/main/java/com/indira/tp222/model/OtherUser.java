package com.indira.tp222.model;

import java.util.ArrayList;
import java.util.List;

public class OtherUser {
    private String username;
    private String fullName;
    private String bio;
    private int profileImage;
    private int postCount;
    private int followerCount;
    private int followingCount;
    private List<Post> userPosts;

    public OtherUser(String username, String fullName, String bio, int profileImage,
                     int postCount, int followerCount, int followingCount, List<Post> userPosts) {
        this.username = username;
        this.fullName = fullName;
        this.bio = bio;
        this.profileImage = profileImage;
        this.postCount = postCount;
        this.followerCount = followerCount;
        this.followingCount = followingCount;
        this.userPosts = userPosts;
    }

    public String getUsername() { return username; }
    public String getFullName() { return fullName; }
    public String getBio() { return bio; }
    public int getProfileImage() { return profileImage; }
    public int getPostCount() { return postCount; }
    public int getFollowerCount() { return followerCount; }
    public int getFollowingCount() { return followingCount; }
    public List<Post> getUserPosts() { return userPosts; }
}