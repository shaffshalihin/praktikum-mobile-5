package com.ishmah.praktikumm2;

public class StoryItem {
    private int storyImageRes;
    private String storyName;
    private String likeCount;
    private String commentCount;
    private String sendCount;

    public StoryItem(int storyImageRes, String storyName,
                     String likeCount, String commentCount, String sendCount) {
        this.storyImageRes = storyImageRes;
        this.storyName = storyName;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.sendCount = sendCount;
    }

    public int getStoryImageRes() { return storyImageRes; }
    public String getStoryName() { return storyName; }
    public String getLikeCount() { return likeCount; }
    public String getCommentCount() { return commentCount; }
    public String getSendCount() { return sendCount; }
}