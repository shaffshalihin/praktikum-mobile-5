package com.example.notebox;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class SharedPrefManager {

    private static final String PREF_NAME        = "NoteBox_Prefs";
    private static final String KEY_USERS         = "users";
    private static final String KEY_CURRENT_USER  = "currentUser";
    private static final String KEY_SAVED_USER    = "savedUser";
    private static final String KEY_NOTES_PREFIX  = "notes_";
    private static final String KEY_DARK_PREFIX   = "darkMode_";
    private static final String KEY_NOTIF_PREFIX  = "notif_";
    private static final String KEY_ANIM_PREFIX   = "anim_";

    private static SharedPrefManager instance;
    private final SharedPreferences prefs;
    private final Gson gson = new Gson();

    private SharedPrefManager(Context ctx) {
        prefs = ctx.getApplicationContext()
                .getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized SharedPrefManager getInstance(Context ctx) {
        if (instance == null) instance = new SharedPrefManager(ctx);
        return instance;
    }

    public void registerUser(User user) {
        List<User> users = getAllUsers();
        users.add(user);
        prefs.edit().putString(KEY_USERS, gson.toJson(users)).apply();
    }

    public boolean isUsernameTaken(String username) {
        for (User u : getAllUsers()) {
            if (u.getUsername().equalsIgnoreCase(username)) return true;
        }
        return false;
    }

    public User validateLogin(String username, String password) {
        for (User u : getAllUsers()) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }

    private List<User> getAllUsers() {
        String json = prefs.getString(KEY_USERS, null);
        if (json == null) {
            // Seed default account (admin/1234) sama seperti HTML
            List<User> defaults = new ArrayList<>();
            defaults.add(new User("admin", "1234", "Administrator"));
            return defaults;
        }
        Type type = new TypeToken<List<User>>(){}.getType();
        return gson.fromJson(json, type);
    }


    public void setCurrentUser(String username) {
        prefs.edit().putString(KEY_CURRENT_USER, username).apply();
    }

    public String getCurrentUser() {
        return prefs.getString(KEY_CURRENT_USER, null);
    }

    public void setSavedUser(String username) {
        prefs.edit().putString(KEY_SAVED_USER, username).apply();
    }

    public String getSavedUser() {
        return prefs.getString(KEY_SAVED_USER, null);
    }

    public void clearCurrentUser() {
        prefs.edit().remove(KEY_CURRENT_USER).apply();
    }

    public User getUserByUsername(String username) {
        for (User u : getAllUsers()) {
            if (u.getUsername().equals(username)) return u;
        }
        return null;
    }

    public List<Note> getNotes(String username) {
        String json = prefs.getString(KEY_NOTES_PREFIX + username, null);
        if (json == null) return new ArrayList<>();
        Type type = new TypeToken<List<Note>>(){}.getType();
        return gson.fromJson(json, type);
    }

    public void saveNotes(String username, List<Note> notes) {
        prefs.edit().putString(KEY_NOTES_PREFIX + username, gson.toJson(notes)).apply();
    }

    public void addNote(String username, Note note) {
        List<Note> notes = getNotes(username);
        notes.add(0, note);
        saveNotes(username, notes);
    }

    public void deleteNote(String username, long noteId) {
        List<Note> notes = getNotes(username);
        notes.removeIf(n -> n.getId() == noteId);
        saveNotes(username, notes);
    }

    public void clearNotes(String username) {
        prefs.edit().remove(KEY_NOTES_PREFIX + username).apply();
    }


    public boolean isDarkMode(String username) {
        return prefs.getBoolean(KEY_DARK_PREFIX + username, false);
    }

    public void setDarkMode(String username, boolean enabled) {
        prefs.edit().putBoolean(KEY_DARK_PREFIX + username, enabled).apply();
    }

    public boolean isNotifEnabled(String username) {
        return prefs.getBoolean(KEY_NOTIF_PREFIX + username, false);
    }

    public void setNotifEnabled(String username, boolean enabled) {
        prefs.edit().putBoolean(KEY_NOTIF_PREFIX + username, enabled).apply();
    }

    public boolean isAnimEnabled(String username) {
        return prefs.getBoolean(KEY_ANIM_PREFIX + username, true);
    }

    public void setAnimEnabled(String username, boolean enabled) {
        prefs.edit().putBoolean(KEY_ANIM_PREFIX + username, enabled).apply();
    }
}