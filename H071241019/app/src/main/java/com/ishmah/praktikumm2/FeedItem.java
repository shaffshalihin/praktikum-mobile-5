package com.ishmah.praktikumm2;

import android.net.Uri;

public class FeedItem {
    private int profileImageRes;
    private String username;
    private int postImageRes;
    private String postImageUriString;  // Simpan sebagai String, bukan Uri
    private String caption;
    private String likeCount;
    private String commentCount;
    private String repostCount;
    private String sendCount;
    private String timeAgo;

    // Constructor kosong untuk Gson
    public FeedItem() {
    }

    // Constructor untuk gambar dari resource (dummy)
    public FeedItem(int profileImageRes, String username, int postImageRes,
                    String caption, String likeCount, String commentCount,
                    String repostCount, String sendCount, String timeAgo) {
        this.profileImageRes = profileImageRes;
        this.username = username;
        this.postImageRes = postImageRes;
        this.postImageUriString = null;
        this.caption = caption;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.repostCount = repostCount;
        this.sendCount = sendCount;
        this.timeAgo = timeAgo;
    }

    // Constructor untuk gambar dari Uri (upload) - simpan sebagai String
    public FeedItem(int profileImageRes, String username, Uri postImageUri,
                    String caption, String likeCount, String commentCount,
                    String repostCount, String sendCount, String timeAgo) {
        this.profileImageRes = profileImageRes;
        this.username = username;
        this.postImageRes = 0;
        this.postImageUriString = postImageUri != null ? postImageUri.toString() : null;
        this.caption = caption;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.repostCount = repostCount;
        this.sendCount = sendCount;
        this.timeAgo = timeAgo;
    }

    // Getter
    public int getProfileImageRes() { return profileImageRes; }
    public String getUsername() { return username; }
    public int getPostImageRes() { return postImageRes; }

    // Ambil Uri dari String
    public Uri getPostImageUri() {
        return postImageUriString != null ? Uri.parse(postImageUriString) : null;
    }

    public String getCaption() { return caption; }
    public String getLikeCount() { return likeCount; }
    public String getCommentCount() { return commentCount; }
    public String getRepostCount() { return repostCount; }
    public String getSendCount() { return sendCount; }
    public String getTimeAgo() { return timeAgo; }
}