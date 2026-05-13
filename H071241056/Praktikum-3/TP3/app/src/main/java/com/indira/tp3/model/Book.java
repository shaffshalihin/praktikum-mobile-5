package com.indira.tp3.model;

import android.net.Uri;
import java.io.Serializable;

public class Book implements Serializable {
    private String title, author, year, blurb, genre, rating, review;
    private Uri coverImageUri;
    private boolean isLiked;
    private String coverImageString;

    public Book(String title, String author, String year, String blurb, String genre, String rating, String review, Uri coverImageUri, boolean isLiked) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.blurb = blurb;
        this.genre = genre;
        this.rating = rating;
        this.review = review;
        this.coverImageUri = coverImageUri;
        this.isLiked = isLiked;
        if (coverImageUri != null) {
            this.coverImageString = coverImageUri.toString();
        }
    }

    // Getters
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getYear() { return year; }
    public String getBlurb() { return blurb; }
    public String getGenre() { return genre; }
    public String getRating() { return rating; }
    public String getReview() { return review; }
    public Uri getCoverImageUri() {
        if (coverImageString != null && !coverImageString.isEmpty()) {
            return Uri.parse(coverImageString);
        }
        return coverImageUri;
    }
    public boolean isLiked() { return isLiked; }
    public void setLiked(boolean liked) { isLiked = liked; }
    public String getCoverImageString() { return coverImageString; }
    public void setCoverImageString(String coverImageString) { this.coverImageString = coverImageString; }
}