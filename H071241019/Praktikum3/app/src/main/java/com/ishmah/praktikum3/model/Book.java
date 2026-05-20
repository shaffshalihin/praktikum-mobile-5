package com.ishmah.praktikum3.model;

public class Book {
    private int id;
    private String title;
    private String author;
    private int year;
    private String genre;
    private String blurb;
    private String review;
    private float rating;
    private int coverResId;
    private String coverUri;
    private boolean liked;
    private long addedTimestamp;

    // Constructor kosong
    public Book() {}

    // Constructor untuk dummy data
    public Book(int id, String title, String author, int year, String genre,
                String blurb, String review, float rating, int coverResId) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.genre = genre;
        this.blurb = blurb;
        this.review = review;
        this.rating = rating;
        this.coverResId = coverResId;
        this.liked = false;
        this.addedTimestamp = System.currentTimeMillis() - (id * 1000L * 60 * 60);
    }

    // Constructor dengan cover URL
    public Book(int id, String title, String author, int year, String genre,
                String blurb, String review, float rating, int coverResId, String coverUri) {
        this(id, title, author, year, genre, blurb, review, rating, coverResId);
        this.coverUri = coverUri;
    }

    // Getter dan Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public String getBlurb() { return blurb; }
    public void setBlurb(String blurb) { this.blurb = blurb; }

    public String getReview() { return review; }
    public void setReview(String review) { this.review = review; }

    public float getRating() { return rating; }
    public void setRating(float rating) { this.rating = rating; }

    public int getCoverResId() { return coverResId; }
    public void setCoverResId(int coverResId) { this.coverResId = coverResId; }

    public String getCoverUri() { return coverUri; }
    public void setCoverUri(String coverUri) { this.coverUri = coverUri; }

    public boolean isLiked() { return liked; }
    public void setLiked(boolean liked) { this.liked = liked; }

    public long getAddedTimestamp() { return addedTimestamp; }
    public void setAddedTimestamp(long addedTimestamp) { this.addedTimestamp = addedTimestamp; }
}