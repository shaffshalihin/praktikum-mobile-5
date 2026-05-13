package com.example.praktikum_3;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {
    private int id;
    private String title;
    private String author;
    private int year;
    private String blurb;
    private Uri imageUri;
    private Integer imageResId;
    private boolean isLiked;
    private String genre;
    private float rating;
    private int pageCount;

    public Book(int id, String title, String author, int year, String blurb, Uri imageUri, Integer imageResId, String genre, float rating, int pageCount) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.blurb = blurb;
        this.imageUri = imageUri;
        this.imageResId = imageResId;
        this.genre = genre;
        this.rating = rating;
        this.isLiked = false;
        this.pageCount = pageCount;
    }

    protected Book(Parcel in) {
        id = in.readInt();
        title = in.readString();
        author = in.readString();
        year = in.readInt();
        blurb = in.readString();
        imageUri = in.readParcelable(Uri.class.getClassLoader());
        if (in.readByte() == 0) {
            imageResId = null;
        } else {
            imageResId = in.readInt();
        }
        isLiked = in.readByte() != 0;
        genre = in.readString();
        rating = in.readFloat();
        pageCount = in.readInt();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(author);
        dest.writeInt(year);
        dest.writeString(blurb);
        dest.writeParcelable(imageUri, flags);
        if (imageResId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(imageResId);
        }
        dest.writeByte((byte) (isLiked ? 1 : 0));
        dest.writeString(genre);
        dest.writeFloat(rating);
        dest.writeInt(pageCount);
    }

    // Getters and Setters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getYear() { return year; }
    public String getBlurb() { return blurb; }
    public Uri getImageUri() { return imageUri; }
    public Integer getImageResId() { return imageResId; }
    public boolean isLiked() { return isLiked; }
    public void setLiked(boolean liked) { isLiked = liked; }
    public String getGenre() { return genre; }
    public float getRating() { return rating; }
    public int getPageCount() { return pageCount; }
}
