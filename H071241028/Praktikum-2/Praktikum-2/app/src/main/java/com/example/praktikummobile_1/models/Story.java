package com.example.praktikummobile_1.models;

public class Story {
    private int storyImage; // Gambar/ikon lingkaran highlight
    private String title;    // Judul di bawah highlight

    // Constructor
    public Story(int storyImage, String title) {
        this.storyImage = storyImage;
        this.title = title;
    }

    // Getter
    public int getStoryImage() { return storyImage; }
    public String getTitle() { return title; }
}