package com.ishmah.praktikumm2;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DataManager {

    private static final String PREF_NAME = "praktikum2_pref";
    private static final String KEY_UPLOADED_FEEDS = "uploaded_feeds_" + DataDummy.getCurrentUsername();

    private SharedPreferences sharedPreferences;
    private Gson gson;

    public DataManager(Context context, String username) {
        String prefName = PREF_NAME + "_" + username;
        sharedPreferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    // Simpan daftar feed yang diupload
    public void saveUploadedFeeds(List<FeedItem> feeds) {
        String json = gson.toJson(feeds);
        sharedPreferences.edit().putString(KEY_UPLOADED_FEEDS, json).apply();
    }

    // Ambil daftar feed yang diupload
    public List<FeedItem> getUploadedFeeds() {
        String json = sharedPreferences.getString(KEY_UPLOADED_FEEDS, "");
        if (json.isEmpty()) {
            return new ArrayList<>();
        }
        Type type = new TypeToken<List<FeedItem>>(){}.getType();
        return gson.fromJson(json, type);
    }

    // Tambah feed baru
    public void addUploadedFeed(FeedItem feed) {
        List<FeedItem> feeds = getUploadedFeeds();
        feeds.add(0, feed);
        saveUploadedFeeds(feeds);
    }
}