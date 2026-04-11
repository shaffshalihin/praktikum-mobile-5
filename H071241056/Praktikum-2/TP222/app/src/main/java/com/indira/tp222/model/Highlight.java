package com.indira.tp222.model;

import java.util.ArrayList;
import java.util.List;

public class Highlight {
    private String title;
    private List<String> storyImageUris;  // List URI untuk multiple story

    // Constructor dengan multiple images
    public Highlight(String title, List<String> storyImageUris) {
        this.title = title;
        this.storyImageUris = storyImageUris;
    }

    // Constructor dengan single image (untuk kompatibilitas)
    public Highlight(int coverImage, String title) {
        this.title = title;
        this.storyImageUris = new ArrayList<>();
        this.storyImageUris.add(String.valueOf(coverImage));
    }

    public String getTitle() { return title; }
    public List<String> getStoryImageUris() { return storyImageUris; }
    public int getStoryCount() { return storyImageUris.size(); }
}