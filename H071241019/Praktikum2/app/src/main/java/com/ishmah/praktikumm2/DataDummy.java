package com.ishmah.praktikumm2;

import java.util.ArrayList;
import java.util.List;

public class DataDummy {

    // ==================== DATA FEED HOME (20 ITEM) ====================
    public static List<FeedItem> getHomeFeed() {
        List<FeedItem> list = new ArrayList<>();

        list.add(new FeedItem(R.drawable.ic_hodo_profile, "hodo.id", R.drawable.ic_hodo_post1, "Ayam Mentega spesial!", "1.234", "45", "12", "23", "2 jam lalu"));
        list.add(new FeedItem(R.drawable.ic_photo_profile, "photography", R.drawable.ic_photo_post1, "Airplane view ✈️", "2.567", "78", "23", "45", "4 jam lalu"));
        list.add(new FeedItem(R.drawable.ic_126_profile, "126alters", R.drawable.ic_126_post1, "Kucing ragdoll lucu banget 🐱", "3.890", "112", "34", "56", "6 jam lalu"));
        list.add(new FeedItem(R.drawable.ic_ishma_profile, "ishmahhjkl", R.drawable.ic_ishma_post1, "Ini fotonya di fore TSM ✨", "1.456", "34", "9", "21", "8 jam lalu"));
        list.add(new FeedItem(R.drawable.ic_sre_profile, "sre_unhas", R.drawable.ic_sre_post1, "GREENSCENE FAST FASHION 🌿", "4.567", "234", "67", "89", "12 jam lalu"));
        list.add(new FeedItem(R.drawable.ic_user6_profile, "foodie_mksr", R.drawable.ic_foodie_post1, "Korean BBQ hits! 🔥", "2.100", "95", "30", "47", "14 jam lalu"));
        list.add(new FeedItem(R.drawable.ic_user7_profile, "snapshot_id", R.drawable.ic_snap_post1, "Street photography Korea 📸", "4.320", "187", "55", "88", "16 jam lalu"));
        list.add(new FeedItem(R.drawable.ic_user8_profile, "alters_style", R.drawable.ic_alters_post1, "Flower hunting 🌸", "1.870", "64", "19", "35", "18 jam lalu"));
        list.add(new FeedItem(R.drawable.ic_user9_profile, "wanderlust_ish", R.drawable.ic_wander_post1, "Painting session today 🎨", "3.410", "112", "38", "71", "20 jam lalu"));
        list.add(new FeedItem(R.drawable.ic_user10_profile, "greentech_unhas", R.drawable.ic_green_post1, "Energi angin masa depan 🌬️", "6.123", "401", "110", "155", "22 jam lalu"));
        list.add(new FeedItem(R.drawable.ic_hodo_profile, "hodo.id", R.drawable.ic_hodo_post2, "Ayam Teriyaki favorit 🍗", "892", "23", "5", "11", "1 hari lalu"));
        list.add(new FeedItem(R.drawable.ic_photo_profile, "photography", R.drawable.ic_photo_post2, "You look handsome 📷", "3.210", "89", "28", "43", "2 hari lalu"));
        list.add(new FeedItem(R.drawable.ic_126_profile, "126alters", R.drawable.ic_126_post2, "Bayi ganteng 8 bulan 👶", "2.345", "56", "14", "27", "3 hari lalu"));
        list.add(new FeedItem(R.drawable.ic_ishma_profile, "ishmahhjkl", R.drawable.ic_ishma_post2, "Ini juga fotonya di fore TSM 💕", "987", "21", "6", "14", "4 hari lalu"));
        list.add(new FeedItem(R.drawable.ic_sre_profile, "sre_unhas", R.drawable.ic_sre_post2, "GREENSCENE BYD 🚗", "5.678", "345", "89", "123", "5 hari lalu"));
        list.add(new FeedItem(R.drawable.ic_user6_profile, "foodie_mksr", R.drawable.ic_foodie_post2, "Chocolate cake spesial 🍰", "1.540", "72", "21", "38", "6 hari lalu"));
        list.add(new FeedItem(R.drawable.ic_user7_profile, "snapshot_id", R.drawable.ic_snap_post2, "Street photography Asia 📸", "5.670", "256", "74", "102", "7 hari lalu"));
        list.add(new FeedItem(R.drawable.ic_user8_profile, "alters_style", R.drawable.ic_alters_post2, "Lily flat lay aesthetic 🌸", "2.890", "98", "27", "51", "8 hari lalu"));
        list.add(new FeedItem(R.drawable.ic_user9_profile, "wanderlust_ish", R.drawable.ic_wander_post2, "Portrait in progress 🖌️", "1.230", "41", "12", "24", "9 hari lalu"));
        list.add(new FeedItem(R.drawable.ic_user10_profile, "greentech_unhas", R.drawable.ic_green_post2, "Green energy lights the path 🌳", "2.760", "143", "45", "87", "10 hari lalu"));

        return list;
    }

    // ==================== DATA FEED PROFILE PER USER (5 POST) ====================
    public static List<FeedItem> getProfileFeedForUser(String username) {
        List<FeedItem> list = new ArrayList<>();

        switch (username) {
            case "photography":
                list.add(new FeedItem(R.drawable.ic_photo_profile, "photography", R.drawable.ic_photo_post1, "Airplane view ✈️", "1.234", "45", "12", "23", "2 jam lalu"));
                list.add(new FeedItem(R.drawable.ic_photo_profile, "photography", R.drawable.ic_photo_post2, "You look handsome 📷", "892", "23", "5", "11", "4 jam lalu"));
                list.add(new FeedItem(R.drawable.ic_photo_profile, "photography", R.drawable.ic_photo_post3, "Airplane window view ✈️", "2.101", "67", "18", "34", "6 jam lalu"));
                list.add(new FeedItem(R.drawable.ic_photo_profile, "photography", R.drawable.ic_photo_post4, "Sunset beach vibes 🌅", "3.456", "89", "25", "42", "8 jam lalu"));
                list.add(new FeedItem(R.drawable.ic_photo_profile, "photography", R.drawable.ic_photo_post5, "Sunset at Bali Temple 🌅", "1.789", "34", "10", "18", "10 jam lalu"));
                break;
            case "126alters":
                list.add(new FeedItem(R.drawable.ic_126_profile, "126alters", R.drawable.ic_126_post1, "Kucing ragdoll lucu banget 🐱", "987", "21", "6", "14", "2 jam lalu"));
                list.add(new FeedItem(R.drawable.ic_126_profile, "126alters", R.drawable.ic_126_post2, "Bayi ganteng 8 bulan 👶", "1.789", "34", "8", "19", "4 jam lalu"));
                list.add(new FeedItem(R.drawable.ic_126_profile, "126alters", R.drawable.ic_126_post3, "Bayi perempuan imut 👶", "2.345", "56", "14", "27", "6 jam lalu"));
                list.add(new FeedItem(R.drawable.ic_126_profile, "126alters", R.drawable.ic_126_post4, "I miss u like a lot 💙", "1.234", "28", "7", "15", "8 jam lalu"));
                list.add(new FeedItem(R.drawable.ic_126_profile, "126alters", R.drawable.ic_126_post5, "Cafe vibes ☕", "2.567", "45", "12", "31", "10 jam lalu"));
                break;
            case "ishmahhjkl":
                list.add(new FeedItem(R.drawable.ic_ishma_profile, "ishmahhjkl", R.drawable.ic_ishma_post1, "Ini fotonya di fore TSM ✨", "1.234", "45", "12", "23", "2 jam lalu"));
                list.add(new FeedItem(R.drawable.ic_ishma_profile, "ishmahhjkl", R.drawable.ic_ishma_post2, "Ini juga fotonya di fore TSM 💕", "892", "23", "5", "11", "4 jam lalu"));
                list.add(new FeedItem(R.drawable.ic_ishma_profile, "ishmahhjkl", R.drawable.ic_ishma_post3, "Ini fotonya di TSM 🤍", "2.101", "67", "18", "34", "6 jam lalu"));
                list.add(new FeedItem(R.drawable.ic_ishma_profile, "ishmahhjkl", R.drawable.ic_ishma_post4, "Daily vlog hari ini ☀️", "1.567", "34", "9", "20", "8 jam lalu"));
                list.add(new FeedItem(R.drawable.ic_ishma_profile, "ishmahhjkl", R.drawable.ic_ishma_post5, "Skincare routine ✨", "2.890", "78", "22", "45", "10 jam lalu"));
                break;
            case "sre_unhas":
                list.add(new FeedItem(R.drawable.ic_sre_profile, "sre_unhas", R.drawable.ic_sre_post1, "GREENSCENE FAST FASHION 🌿", "4.567", "234", "67", "89", "2 jam lalu"));
                list.add(new FeedItem(R.drawable.ic_sre_profile, "sre_unhas", R.drawable.ic_sre_post2, "GREENSCENE BYD 🚗", "3.210", "89", "28", "43", "4 jam lalu"));
                list.add(new FeedItem(R.drawable.ic_sre_profile, "sre_unhas", R.drawable.ic_sre_post3, "GREENSCENE SELF REWARD 🌿", "2.345", "56", "14", "27", "6 jam lalu"));
                list.add(new FeedItem(R.drawable.ic_sre_profile, "sre_unhas", R.drawable.ic_sre_post4, "Energi angin, solusi masa depan 🌬️", "1.890", "45", "11", "23", "8 jam lalu"));
                list.add(new FeedItem(R.drawable.ic_sre_profile, "sre_unhas", R.drawable.ic_sre_post5, "SRE UNHAS merch terbaru 🎒", "3.456", "123", "34", "67", "10 jam lalu"));
                break;
            case "foodie_mksr":
                list.add(new FeedItem(R.drawable.ic_user6_profile, "foodie_mksr", R.drawable.ic_foodie_post1, "Korean BBQ favorit! 🔥", "987", "21", "6", "14", "2 jam lalu"));
                list.add(new FeedItem(R.drawable.ic_user6_profile, "foodie_mksr", R.drawable.ic_foodie_post2, "Chocolate cake spesial 🍰", "1.210", "30", "8", "16", "4 jam lalu"));
                list.add(new FeedItem(R.drawable.ic_user6_profile, "foodie_mksr", R.drawable.ic_foodie_post3, "Cheeseburger mantap! 🍔", "2.100", "48", "13", "24", "6 jam lalu"));
                list.add(new FeedItem(R.drawable.ic_user6_profile, "foodie_mksr", R.drawable.ic_foodie_post4, "Buffalo chicken wings 🍗", "1.560", "37", "10", "19", "8 jam lalu"));
                list.add(new FeedItem(R.drawable.ic_user6_profile, "foodie_mksr", R.drawable.ic_foodie_post5, "Chocolate chip cookies 🍪", "3.210", "87", "24", "43", "10 jam lalu"));
                break;
            case "snapshot_id":
                list.add(new FeedItem(R.drawable.ic_user7_profile, "snapshot_id", R.drawable.ic_snap_post1, "Street photography Korea 📸", "4.321", "187", "55", "88", "2 jam lalu"));
                list.add(new FeedItem(R.drawable.ic_user7_profile, "snapshot_id", R.drawable.ic_snap_post2, "Street photography Asia 📸", "2.890", "112", "34", "61", "4 jam lalu"));
                list.add(new FeedItem(R.drawable.ic_user7_profile, "snapshot_id", R.drawable.ic_snap_post3, "Meow under the flowers 🌸", "5.670", "256", "74", "102", "6 jam lalu"));
                list.add(new FeedItem(R.drawable.ic_user7_profile, "snapshot_id", R.drawable.ic_snap_post4, "London Underground 🚇", "3.450", "134", "40", "67", "8 jam lalu"));
                list.add(new FeedItem(R.drawable.ic_user7_profile, "snapshot_id", R.drawable.ic_snap_post5, "Golden sunset at the beach 🌅", "2.100", "78", "22", "41", "10 jam lalu"));
                break;
            case "alters_style":
                list.add(new FeedItem(R.drawable.ic_user8_profile, "alters_style", R.drawable.ic_alters_post1, "Flower hunting 🌸", "1.870", "64", "19", "35", "2 jam lalu"));
                list.add(new FeedItem(R.drawable.ic_user8_profile, "alters_style", R.drawable.ic_alters_post2, "Lily flat lay aesthetic 🌸", "2.340", "78", "22", "41", "4 jam lalu"));
                list.add(new FeedItem(R.drawable.ic_user8_profile, "alters_style", R.drawable.ic_alters_post3, "Slow day mood 📖🎵", "1.450", "51", "15", "28", "6 jam lalu"));
                list.add(new FeedItem(R.drawable.ic_user8_profile, "alters_style", R.drawable.ic_alters_post4, "All you need is love & flowers 🌷", "2.890", "98", "27", "51", "8 jam lalu"));
                list.add(new FeedItem(R.drawable.ic_user8_profile, "alters_style", R.drawable.ic_alters_post5, "New makeup haul! 💄", "1.670", "43", "12", "23", "10 jam lalu"));
                break;
            case "wanderlust_ish":
                list.add(new FeedItem(R.drawable.ic_user9_profile, "wanderlust_ish", R.drawable.ic_wander_post1, "Painting session today 🎨", "1.230", "41", "12", "24", "2 jam lalu"));
                list.add(new FeedItem(R.drawable.ic_user9_profile, "wanderlust_ish", R.drawable.ic_wander_post2, "Portrait in progress 🖌️", "876", "28", "8", "17", "4 jam lalu"));
                list.add(new FeedItem(R.drawable.ic_user9_profile, "wanderlust_ish", R.drawable.ic_wander_post3, "Landscape in oil ✨", "2.100", "67", "18", "34", "6 jam lalu"));
                list.add(new FeedItem(R.drawable.ic_user9_profile, "wanderlust_ish", R.drawable.ic_wander_post4, "Lily series 🌸", "1.567", "53", "15", "27", "8 jam lalu"));
                list.add(new FeedItem(R.drawable.ic_user9_profile, "wanderlust_ish", R.drawable.ic_wander_post5, "Watercolor workspace 🎨", "3.210", "112", "32", "59", "10 jam lalu"));
                break;
            case "greentech_unhas":
                list.add(new FeedItem(R.drawable.ic_user10_profile, "greentech_unhas", R.drawable.ic_green_post1, "Energi angin masa depan 🌬️", "6.123", "401", "110", "155", "2 jam lalu"));
                list.add(new FeedItem(R.drawable.ic_user10_profile, "greentech_unhas", R.drawable.ic_green_post2, "Green energy lights the path 🌳", "4.560", "278", "78", "112", "4 jam lalu"));
                list.add(new FeedItem(R.drawable.ic_user10_profile, "greentech_unhas", R.drawable.ic_green_post3, "Solar energy - alternatif berkelanjutan 🌞", "2.760", "143", "45", "87", "6 jam lalu"));
                list.add(new FeedItem(R.drawable.ic_user10_profile, "greentech_unhas", R.drawable.ic_green_post4, "Energy is Everywhere ⚡", "3.890", "201", "56", "99", "8 jam lalu"));
                list.add(new FeedItem(R.drawable.ic_user10_profile, "greentech_unhas", R.drawable.ic_green_post5, "Grow More, Waste Less 🌱", "2.100", "87", "25", "48", "10 jam lalu"));
                break;
            default: // hodo.id
                list.add(new FeedItem(R.drawable.ic_hodo_profile, "hodo.id", R.drawable.ic_hodo_post1, "Ayam Mentega spesial!", "987", "21", "6", "14", "4 hari lalu"));
                list.add(new FeedItem(R.drawable.ic_hodo_profile, "hodo.id", R.drawable.ic_hodo_post2, "Ayam Teriyaki favorit 🍗", "1.789", "34", "8", "19", "3 hari lalu"));
                list.add(new FeedItem(R.drawable.ic_hodo_profile, "hodo.id", R.drawable.ic_hodo_post3, "Daging Lada Hitam 🍖", "2.345", "56", "14", "27", "2 hari lalu"));
                list.add(new FeedItem(R.drawable.ic_hodo_profile, "hodo.id", R.drawable.ic_hodo_post4, "Curry Katsu special 🍛", "1.234", "28", "7", "15", "1 hari lalu"));
                list.add(new FeedItem(R.drawable.ic_hodo_profile, "hodo.id", R.drawable.ic_hodo_post5, "PMW UNHAS 2026 🏆", "2.567", "45", "12", "31", "5 jam lalu"));
                break;
        }
        return list;
    }

    // ==================== DATA PROFILE (NAMA, BIO, STATS) ====================
    public static ProfileData getProfileData(String username) {
        switch (username) {
            case "photography":
                return new ProfileData("Photography Lovers", "5", "1.234", "567",
                        R.drawable.ic_photo_profile,
                        "📸 Photographer | Capturing moments\n📍 Makassar, Indonesia");
            case "126alters":
                return new ProfileData("126 Alter's", "5", "2.345", "789",
                        R.drawable.ic_126_profile,
                        "👕 Fashion enthusiast | Streetwear\n📍 Jakarta, Indonesia");
            case "ishmahhjkl":
                return new ProfileData("Ishmah Jkl", "5", "987", "234",
                        R.drawable.ic_ishma_profile,
                        "✨ Lifestyle | Daily vlog\n📍 Surabaya, Indonesia");
            case "sre_unhas":
                return new ProfileData("SRE UNHAS", "5", "1.234", "567",
                        R.drawable.ic_sre_profile,
                        "💻 SRE Unhas | Software Engineer\n📍 Makassar, Indonesia");
            case "foodie_mksr":
                return new ProfileData("Foodie Makassar", "5", "3.210", "412",
                        R.drawable.ic_user6_profile,
                        "🍜 Food enthusiast | Makassar\n📍 Makassar, Indonesia");
            case "snapshot_id":
                return new ProfileData("Snapshot ID", "5", "8.765", "1.234",
                        R.drawable.ic_user7_profile,
                        "📸 Travel & Nature Photography\n📍 Bali, Indonesia");
            case "alters_style":
                return new ProfileData("Alters Style", "5", "4.320", "678",
                        R.drawable.ic_user8_profile,
                        "🌸 Flowers & Aesthetic | Beauty\n📍 Jakarta, Indonesia");
            case "wanderlust_ish":
                return new ProfileData("Wanderlust Ish", "5", "2.190", "345",
                        R.drawable.ic_user9_profile,
                        "🎨 Art & Painting | Creative Life\n📍 Indonesia");
            case "greentech_unhas":
                return new ProfileData("GreenTech UNHAS", "5", "5.430", "890",
                        R.drawable.ic_user10_profile,
                        "🌿 Green Tech | Sustainability\n📍 Makassar, Indonesia");
            default: // hodo.id
                return new ProfileData("HODO.id", "5", "77", "2",
                        R.drawable.ic_hodo_profile,
                        "Kitchen/cooking\nHomemade rice bowls bento 🍴\nOrder via WhatsApp 📱");
        }
    }

    // ==================== DATA STORY HIGHLIGHT (7 ITEM PER USER) ====================
    public static List<StoryHighlight> getStoryHighlights(String username) {
        List<StoryHighlight> list = new ArrayList<>();

        switch (username) {
            case "photography":
                list.add(new StoryHighlight(R.drawable.ic_story_photo1, "Portfolio"));
                list.add(new StoryHighlight(R.drawable.ic_story_photo2, "Travel"));
                list.add(new StoryHighlight(R.drawable.ic_story_photo3, "Food"));
                list.add(new StoryHighlight(R.drawable.ic_story_photo4, "Nature"));
                list.add(new StoryHighlight(R.drawable.ic_story_photo5, "City"));
                list.add(new StoryHighlight(R.drawable.ic_story_photo6, "People"));
                list.add(new StoryHighlight(R.drawable.ic_story_photo7, "Art"));
                break;
            case "126alters":
                list.add(new StoryHighlight(R.drawable.ic_story_126_1, "Kucing"));
                list.add(new StoryHighlight(R.drawable.ic_story_126_2, "Bayi"));
                list.add(new StoryHighlight(R.drawable.ic_story_126_3, "Lifestyle"));
                list.add(new StoryHighlight(R.drawable.ic_story_126_4, "Hobby"));
                list.add(new StoryHighlight(R.drawable.ic_story_126_5, "Music"));
                list.add(new StoryHighlight(R.drawable.ic_story_126_6, "Movie"));
                list.add(new StoryHighlight(R.drawable.ic_story_126_7, "Game"));
                break;
            case "ishmahhjkl":
                list.add(new StoryHighlight(R.drawable.ic_story_ishma_1, "Daily"));
                list.add(new StoryHighlight(R.drawable.ic_story_ishma_2, "Vlog"));
                list.add(new StoryHighlight(R.drawable.ic_story_ishma_3, "OOTD"));
                list.add(new StoryHighlight(R.drawable.ic_story_ishma_4, "Makeup"));
                list.add(new StoryHighlight(R.drawable.ic_story_ishma_5, "Skincare"));
                list.add(new StoryHighlight(R.drawable.ic_story_ishma_6, "Cafe"));
                list.add(new StoryHighlight(R.drawable.ic_story_ishma_7, "Friends"));
                break;
            case "sre_unhas":
                list.add(new StoryHighlight(R.drawable.ic_story_sre_1, "Event"));
                list.add(new StoryHighlight(R.drawable.ic_story_sre_2, "Green"));
                list.add(new StoryHighlight(R.drawable.ic_story_sre_3, "Eco"));
                list.add(new StoryHighlight(R.drawable.ic_story_sre_4, "Workshop"));
                list.add(new StoryHighlight(R.drawable.ic_story_sre_5, "Seminar"));
                list.add(new StoryHighlight(R.drawable.ic_story_sre_6, "Campaign"));
                list.add(new StoryHighlight(R.drawable.ic_story_sre_7, "Volunteer"));
                break;
            case "foodie_mksr":
                list.add(new StoryHighlight(R.drawable.ic_story_foodie1, "Kuliner"));
                list.add(new StoryHighlight(R.drawable.ic_story_foodie2, "Review"));
                list.add(new StoryHighlight(R.drawable.ic_story_foodie3, "Resep"));
                list.add(new StoryHighlight(R.drawable.ic_story_foodie4, "Promo"));
                list.add(new StoryHighlight(R.drawable.ic_story_foodie5, "Cafe"));
                list.add(new StoryHighlight(R.drawable.ic_story_foodie6, "Warung"));
                list.add(new StoryHighlight(R.drawable.ic_story_foodie7, "Favorite"));
                break;
            case "snapshot_id":
                list.add(new StoryHighlight(R.drawable.ic_story_snap1, "Landscape"));
                list.add(new StoryHighlight(R.drawable.ic_story_snap2, "Portrait"));
                list.add(new StoryHighlight(R.drawable.ic_story_snap3, "Street"));
                list.add(new StoryHighlight(R.drawable.ic_story_snap4, "Aerial"));
                list.add(new StoryHighlight(R.drawable.ic_story_snap5, "Macro"));
                list.add(new StoryHighlight(R.drawable.ic_story_snap6, "Night"));
                list.add(new StoryHighlight(R.drawable.ic_story_snap7, "BTS"));
                break;
            case "alters_style":
                list.add(new StoryHighlight(R.drawable.ic_story_alters1, "Cats"));
                list.add(new StoryHighlight(R.drawable.ic_story_alters2, "OOTD"));
                list.add(new StoryHighlight(R.drawable.ic_story_alters3, "Haul"));
                list.add(new StoryHighlight(R.drawable.ic_story_alters4, "Thrift"));
                list.add(new StoryHighlight(R.drawable.ic_story_alters5, "Brand"));
                list.add(new StoryHighlight(R.drawable.ic_story_alters6, "Sale"));
                list.add(new StoryHighlight(R.drawable.ic_story_alters7, "Tips"));
                break;
            case "wanderlust_ish":
                list.add(new StoryHighlight(R.drawable.ic_story_wander1, "Travel"));
                list.add(new StoryHighlight(R.drawable.ic_story_wander2, "OOTD"));
                list.add(new StoryHighlight(R.drawable.ic_story_wander3, "Cafe"));
                list.add(new StoryHighlight(R.drawable.ic_story_wander4, "Beauty"));
                list.add(new StoryHighlight(R.drawable.ic_story_wander5, "Food"));
                list.add(new StoryHighlight(R.drawable.ic_story_wander6, "Friends"));
                list.add(new StoryHighlight(R.drawable.ic_story_wander7, "Daily"));
                break;
            case "greentech_unhas":
                list.add(new StoryHighlight(R.drawable.ic_story_green1, "Project"));
                list.add(new StoryHighlight(R.drawable.ic_story_green2, "Event"));
                list.add(new StoryHighlight(R.drawable.ic_story_green3, "Tech"));
                list.add(new StoryHighlight(R.drawable.ic_story_green4, "Green"));
                list.add(new StoryHighlight(R.drawable.ic_story_green5, "Seminar"));
                list.add(new StoryHighlight(R.drawable.ic_story_green6, "Campus"));
                list.add(new StoryHighlight(R.drawable.ic_story_green7, "Team"));
                break;
            default: // hodo.id
                list.add(new StoryHighlight(R.drawable.ic_story_hodo1, "Hindia"));
                list.add(new StoryHighlight(R.drawable.ic_story_hodo2, "Feast"));
                list.add(new StoryHighlight(R.drawable.ic_story_hodo3, "Meidiana"));
                list.add(new StoryHighlight(R.drawable.ic_story_hodo4, "Kucing"));
                list.add(new StoryHighlight(R.drawable.ic_story_hodo5, "Menu 5"));
                list.add(new StoryHighlight(R.drawable.ic_story_hodo6, "Menu 6"));
                list.add(new StoryHighlight(R.drawable.ic_story_hodo7, "Menu 7"));
                break;
        }
        return list;
    }

    // ==================== CLASS PROFILE DATA ====================
    public static class ProfileData {
        public String fullName;
        public String postsCount;
        public String followersCount;
        public String followingCount;
        public int profileImageRes;
        public String bio;

        public ProfileData(String fullName, String postsCount, String followersCount,
                           String followingCount, int profileImageRes, String bio) {
            this.fullName = fullName;
            this.postsCount = postsCount;
            this.followersCount = followersCount;
            this.followingCount = followingCount;
            this.profileImageRes = profileImageRes;
            this.bio = bio;
        }
    }

    // ==================== CLASS STORY HIGHLIGHT ====================
    public static class StoryHighlight {
        public int imageRes;
        public String title;

        public StoryHighlight(int imageRes, String title) {
            this.imageRes = imageRes;
            this.title = title;
        }
    }

    private static String currentUsername = "hodo.id";

    public static void setCurrentUsername(String username) {
        currentUsername = username;
    }

    public static String getCurrentUsername() {
        return currentUsername;
    }
}