package com.ishmah.praktikumm2;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UploadDataManager {

    private static final String PREF_NAME = "upload_prefs";
    private SharedPreferences sharedPreferences;
    private Gson gson;
    private String currentUsername;

    public UploadDataManager(Context context, String username) {
        this.currentUsername = username;
        // Beda file pref untuk setiap user
        String prefName = PREF_NAME + "_" + username;
        sharedPreferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    public void saveUploadedFeeds(List<FeedItem> feeds) {
        String json = gson.toJson(feeds);
        sharedPreferences.edit().putString("feeds", json).apply();
    }

    public List<FeedItem> getUploadedFeeds() {
        String json = sharedPreferences.getString("feeds", "");
        if (json.isEmpty()) {
            return new ArrayList<>();
        }
        Type type = new TypeToken<List<FeedItem>>(){}.getType();
        return gson.fromJson(json, type);
    }

    public void addUploadedFeed(FeedItem feed) {
        List<FeedItem> feeds = getUploadedFeeds();
        feeds.add(0, feed);
        saveUploadedFeeds(feeds);
    }
}