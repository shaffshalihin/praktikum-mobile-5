package com.example.notebox;

public class User {
    private String username;
    private String password;
    private String displayName;

    public User(String username, String password, String displayName) {
        this.username = username;
        this.password = password;
        this.displayName = displayName;
    }

    public String getUsername()    { return username; }
    public String getPassword()    { return password; }
    public String getDisplayName() { return displayName; }

    public String getInitial() {
        if (displayName != null && !displayName.isEmpty())
            return String.valueOf(displayName.charAt(0)).toUpperCase();
        if (username != null && !username.isEmpty())
            return String.valueOf(username.charAt(0)).toUpperCase();
        return "?";
    }
}
