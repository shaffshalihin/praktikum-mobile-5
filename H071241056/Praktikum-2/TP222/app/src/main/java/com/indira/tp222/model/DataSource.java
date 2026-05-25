package com.indira.tp222.model;

import com.indira.tp222.R;
import java.util.ArrayList;
import java.util.List;

public class DataSource {

    public static List<Post> getHomeFeed() {
        List<Post> feeds = new ArrayList<>();

        feeds.add(new Post(R.drawable.post1, "johndoe", R.drawable.post10, "Sunset photography is my passion 🌅", 1245, "2 HOURS AGO"));
        feeds.add(new Post(R.drawable.post2, "janesmith", R.drawable.post9, "Working on new sketches 🎨", 892, "4 HOURS AGO"));
        feeds.add(new Post(R.drawable.post3, "traveler_joe", R.drawable.post8, "Exploring the mountains of Asia ⛰️", 2341, "6 HOURS AGO"));
        feeds.add(new Post(R.drawable.post4, "foodie_gal", R.drawable.post7, "Best ramen in town! 🍜", 5678, "1 DAY AGO"));
        feeds.add(new Post(R.drawable.post5, "urban_explorer", R.drawable.post6, "City lights never sleep 🌃", 3456, "1 DAY AGO"));
        feeds.add(new Post(R.drawable.post6, "johndoe", R.drawable.post5, "Chilling by the beach", 789, "2 DAYS AGO"));
        feeds.add(new Post(R.drawable.post7, "janesmith", R.drawable.post4, "Exhibition day! ✨", 4321, "2 DAYS AGO"));
        feeds.add(new Post(R.drawable.post8, "traveler_joe", R.drawable.post3, "Packing for the next trip", 2100, "3 DAYS AGO"));
        feeds.add(new Post(R.drawable.post9, "foodie_gal", R.drawable.post2, "Homemade pizza 🍕", 1567, "3 DAYS AGO"));
        feeds.add(new Post(R.drawable.post10, "urban_explorer", R.drawable.post1, "Ancient architecture", 987, "4 DAYS AGO"));

        return feeds;
    }

    private static List<Post> profilePosts = new ArrayList<>();
    static {
        profilePosts.add(new Post(R.drawable.post1, "My first post"));
        profilePosts.add(new Post(R.drawable.post2, "Amazing View"));
        profilePosts.add(new Post(R.drawable.post3, "Project completed"));
        profilePosts.add(new Post(R.drawable.post4, "Hello World"));
        profilePosts.add(new Post(R.drawable.post5, "Holiday"));
        profilePosts.add(new Post(R.drawable.post6, "Coding life"));
    }

    public static List<Post> getProfilePosts() {
        return new ArrayList<>(profilePosts);
    }

    public static void addProfilePost(Post newPost) {
        profilePosts.add(0, newPost);
    }

    public static OtherUser getUserByUsername(String username) {
        if (username == null) return getDefaultOtherUser();

        switch (username) {
            case "johndoe":
                return getJohnDoeProfile();
            case "janesmith":
                return getJaneSmithProfile();
            case "traveler_joe":
                return getTravelerJoeProfile();
            case "foodie_gal":
                return getFoodieGalProfile();
            case "urban_explorer":
                return getUrbanExplorerProfile();
            default:
                return getDefaultOtherUser(username);
        }
    }


    public static OtherUser getJohnDoeProfile() {
        List<Post> posts = new ArrayList<>();
        posts.add(new Post(R.drawable.wlpp, "Sunset photography 🌅"));
        posts.add(new Post(R.drawable.post1, "Mountain view ⛰️"));
        posts.add(new Post(R.drawable.post2, "City lights 🌃"));
        posts.add(new Post(R.drawable.post3, "Beach vibes 🏖️"));
        return new OtherUser("johndoe", "John Doe", "Photographer | Traveler 📸", R.drawable.post1, posts.size(), 15000, 1200, posts);
    }

    public static OtherUser getJaneSmithProfile() {
        List<Post> posts = new ArrayList<>();
        posts.add(new Post(R.drawable.post4, "My artwork 🎨"));
        posts.add(new Post(R.drawable.post5, "Painting process"));
        posts.add(new Post(R.drawable.wlpp, "Art exhibition"));
        return new OtherUser("janesmith", "Jane Smith", "Artist | Creator 🎨", R.drawable.post2, posts.size(), 8900, 750, posts);
    }

    public static OtherUser getTravelerJoeProfile() {
        List<Post> posts = new ArrayList<>();
        posts.add(new Post(R.drawable.post1, "Exploring Asia 🌏"));
        posts.add(new Post(R.drawable.post2, "Europe tour 🚂"));
        posts.add(new Post(R.drawable.post3, "Adventure time!"));
        posts.add(new Post(R.drawable.post4, "Travel tips"));
        posts.add(new Post(R.drawable.post5, "Hidden gems"));
        return new OtherUser("traveler_joe", "Joe Traveler", "Full time traveler ✈️", R.drawable.post3, posts.size(), 45600, 2300, posts);
    }

    public static OtherUser getFoodieGalProfile() {
        List<Post> posts = new ArrayList<>();
        posts.add(new Post(R.drawable.wlpp, "Food heaven 🍜"));
        posts.add(new Post(R.drawable.post1, "Restaurant review"));
        posts.add(new Post(R.drawable.post2, "Home cooking"));
        return new OtherUser("foodie_gal", "Foodie Gal", "Food blogger 🍕", R.drawable.post3, posts.size(), 23400, 1800, posts);
    }

    public static OtherUser getUrbanExplorerProfile() {
        List<Post> posts = new ArrayList<>();
        posts.add(new Post(R.drawable.post3, "City exploration"));
        posts.add(new Post(R.drawable.post4, "Street photography"));
        posts.add(new Post(R.drawable.post5, "Architecture"));
        return new OtherUser("urban_explorer", "Urban Explorer", "City photographer 📷", R.drawable.post5, posts.size(), 12300, 950, posts);
    }

    public static OtherUser getDefaultOtherUser(String username) {
        List<Post> posts = new ArrayList<>();
        posts.add(new Post(R.drawable.wlpp, "Welcome to my profile!"));
        return new OtherUser(username, username, "Bio here", R.drawable.foto, posts.size(), 100, 100, posts);
    }

    public static OtherUser getDefaultOtherUser() {
        return getDefaultOtherUser("User");
    }

    public static List<Highlight> getHighlights() {
        List<Highlight> highlights = new ArrayList<>();

        // Highlight 1 - Travel (3 foto)
        List<String> travelStories = new ArrayList<>();
        travelStories.add(String.valueOf(R.drawable.post1));
        travelStories.add(String.valueOf(R.drawable.post2));
        travelStories.add(String.valueOf(R.drawable.post3));
        highlights.add(new Highlight("Travel", travelStories));

        // Highlight 2 - Food (2 foto)
        List<String> foodStories = new ArrayList<>();
        foodStories.add(String.valueOf(R.drawable.post4));
        foodStories.add(String.valueOf(R.drawable.post5));
        highlights.add(new Highlight("Food", foodStories));

        // Highlight 3 - Art (4 foto)
        List<String> artStories = new ArrayList<>();
        artStories.add(String.valueOf(R.drawable.wlpp));
        artStories.add(String.valueOf(R.drawable.foto));
        artStories.add(String.valueOf(R.drawable.post1));
        artStories.add(String.valueOf(R.drawable.post2));
        highlights.add(new Highlight("Art", artStories));

        // Highlight 4 - Fitness (2 foto)
        List<String> fitnessStories = new ArrayList<>();
        fitnessStories.add(String.valueOf(R.drawable.post3));
        fitnessStories.add(String.valueOf(R.drawable.post4));
        highlights.add(new Highlight("Fitness", fitnessStories));

        // Highlight 5 - Nature (3 foto)
        List<String> natureStories = new ArrayList<>();
        natureStories.add(String.valueOf(R.drawable.post5));
        natureStories.add(String.valueOf(R.drawable.wlpp));
        natureStories.add(String.valueOf(R.drawable.foto));
        highlights.add(new Highlight("Nature", natureStories));

        // Highlight 6 - City (2 foto)
        List<String> cityStories = new ArrayList<>();
        cityStories.add(String.valueOf(R.drawable.post1));
        cityStories.add(String.valueOf(R.drawable.post2));
        highlights.add(new Highlight("City", cityStories));

        // Highlight 7 - Memories (3 foto)
        List<String> memoriesStories = new ArrayList<>();
        memoriesStories.add(String.valueOf(R.drawable.post3));
        memoriesStories.add(String.valueOf(R.drawable.post4));
        memoriesStories.add(String.valueOf(R.drawable.post5));
        highlights.add(new Highlight("Memories", memoriesStories));

        return highlights;
    }
}