package com.example.myapplicationn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataStorage {
    public static ArrayList<ModelPost> homeFeedList    = new ArrayList<>();
    public static ArrayList<ModelPost> profileFeedList = new ArrayList<>();
    public static ArrayList<ModelPost> highlightList   = new ArrayList<>();

    public static Map<String, ArrayList<ModelPost>> userHighlightMap = new HashMap<>();

    // ── Highlight akun sendiri (7 item) ──────────────────────────────────────
    public static void initHighlightIfEmpty() {
        if (!highlightList.isEmpty()) return;
        String[] names  = {"Hindia",".Feast","Meidiana","Kucing","Moments","Family","Friends"};
        int[]    images = {
                R.drawable.baskara4,  R.drawable.adnan6,  R.drawable.a4,
                R.drawable.hl4,  R.drawable.hl5,  R.drawable.hl6,
                R.drawable.hl7
        };
        for (int i = 0; i < names.length; i++)
            highlightList.add(new ModelPost(names[i], 0, images[i], ""));
    }

    // ── Highlight per user (masing-masing 7 item, foto unik) ─────────────────
    public static void initUserHighlightsIfEmpty() {
        if (!userHighlightMap.isEmpty()) return;

        ArrayList<ModelPost> adnan = new ArrayList<>();
        adnan.add(new ModelPost("Nina24",    0, R.drawable.adnan3,  ""));
        adnan.add(new ModelPost("Live",    0, R.drawable.adnan5,  ""));
        adnan.add(new ModelPost("Nina23", 0, R.drawable.adnan6, ""));
        adnan.add(new ModelPost("Nina22",   0, R.drawable.adnan4, ""));
        adnan.add(new ModelPost("Family",0, R.drawable.adnan2, ""));
        adnan.add(new ModelPost(".Feast",      0, R.drawable.adnan1, ""));
        adnan.add(new ModelPost("Moment",    0, R.drawable.adnan5, ""));
        userHighlightMap.put("adnandenan", adnan);

        ArrayList<ModelPost> madrid = new ArrayList<>();
        madrid.add(new ModelPost("Madrid",   0, R.drawable.r5, ""));
        madrid.add(new ModelPost("Jersey",   0, R.drawable.r6, ""));
        madrid.add(new ModelPost("ViniJr", 0, R.drawable.r4, ""));
        madrid.add(new ModelPost("World",      0, R.drawable.r2, ""));
        madrid.add(new ModelPost("UCL", 0, R.drawable.r6, ""));
        madrid.add(new ModelPost("King", 0, R.drawable.r1, ""));
        madrid.add(new ModelPost("Moments",     0, R.drawable.r3, ""));
        userHighlightMap.put("realmadrid", madrid);

        ArrayList<ModelPost> angel = new ArrayList<>();
        angel.add(new ModelPost("Project",  0, R.drawable.hl22, ""));
        angel.add(new ModelPost("UI/UX",    0, R.drawable.hl23, ""));
        angel.add(new ModelPost("Tips",     0, R.drawable.hl24, ""));
        angel.add(new ModelPost("Tutorial", 0, R.drawable.hl25, ""));
        angel.add(new ModelPost("Tools",    0, R.drawable.hl26, ""));
        angel.add(new ModelPost("Coding",   0, R.drawable.hl1, ""));
        angel.add(new ModelPost("Review",   0, R.drawable.hl2, ""));
        userHighlightMap.put("angelctrna_", angel);

        ArrayList<ModelPost> photo = new ArrayList<>();
        photo.add(new ModelPost("Portrait",  0, R.drawable.hl13, ""));
        photo.add(new ModelPost("Landscape", 0, R.drawable.hl14, ""));
        photo.add(new ModelPost("Behind",    0, R.drawable.hl15, ""));
        photo.add(new ModelPost("Studio",    0, R.drawable.hl16, ""));
        photo.add(new ModelPost("Street",    0, R.drawable.hl17, ""));
        photo.add(new ModelPost("Golden",    0, R.drawable.hl18, ""));
        photo.add(new ModelPost("Macro",     0, R.drawable.hl19, ""));
        userHighlightMap.put("photography_id", photo);

        ArrayList<ModelPost> travel = new ArrayList<>();
        travel.add(new ModelPost("Bali",       0, R.drawable.hl3, ""));
        travel.add(new ModelPost("Raja Ampat", 0, R.drawable.hl4, ""));
        travel.add(new ModelPost("Lombok",     0, R.drawable.hl5, ""));
        travel.add(new ModelPost("Jogja",      0, R.drawable.hl6, ""));
        travel.add(new ModelPost("Labuan",     0, R.drawable.hl7, ""));
        travel.add(new ModelPost("Flores",     0, R.drawable.hl8, ""));
        travel.add(new ModelPost("Sumba",      0, R.drawable.hl9, ""));
        userHighlightMap.put("traveler_indo", travel);

        ArrayList<ModelPost> mano = new ArrayList<>();
        mano.add(new ModelPost("Kota",    0, R.drawable.hl10, ""));
        mano.add(new ModelPost("Wisata",  0, R.drawable.hl11, ""));
        mano.add(new ModelPost("Kuliner", 0, R.drawable.hl12, ""));
        mano.add(new ModelPost("Pantai",  0, R.drawable.hl13, ""));
        mano.add(new ModelPost("Budaya",  0, R.drawable.hl14, ""));
        mano.add(new ModelPost("Event",   0, R.drawable.hl15, ""));
        mano.add(new ModelPost("Alam",    0, R.drawable.hl16, ""));
        userHighlightMap.put("manokwari_kita", mano);

        ArrayList<ModelPost> meidiana = new ArrayList<>();
        meidiana.add(new ModelPost("lw",   0, R.drawable.m6, ""));
        meidiana.add(new ModelPost("l", 0, R.drawable.m5, ""));
        meidiana.add(new ModelPost("m",     0, R.drawable.m4, ""));
        meidiana.add(new ModelPost("t",       0, R.drawable.m3, ""));
        meidiana.add(new ModelPost("k",   0, R.drawable.m2, ""));
        meidiana.add(new ModelPost("mm",    0, R.drawable.m1, ""));
        meidiana.add(new ModelPost("s",  0, R.drawable.baskara3, ""));
        userHighlightMap.put("meidianatahir", meidiana);

        ArrayList<ModelPost> life = new ArrayList<>();
        life.add(new ModelPost("Morning",  0, R.drawable.hl24, ""));
        life.add(new ModelPost("Food",     0, R.drawable.hl25, ""));
        life.add(new ModelPost("Outfit",   0, R.drawable.hl26, ""));
        life.add(new ModelPost("Fitness",  0, R.drawable.hl1, ""));
        life.add(new ModelPost("Skincare", 0, R.drawable.hl2, ""));
        life.add(new ModelPost("Travel",   0, R.drawable.hl3, ""));
        life.add(new ModelPost("Quotes",   0, R.drawable.hl4, ""));
        userHighlightMap.put("lifestyle_daily", life);

        ArrayList<ModelPost> news = new ArrayList<>();
        news.add(new ModelPost("Teknologi", 0, R.drawable.hl5, ""));
        news.add(new ModelPost("Ekonomi",   0, R.drawable.hl6, ""));
        news.add(new ModelPost("Nasional",  0, R.drawable.hl7, ""));
        news.add(new ModelPost("Dunia",     0, R.drawable.hl8, ""));
        news.add(new ModelPost("Olahraga",  0, R.drawable.hl9, ""));
        news.add(new ModelPost("Hiburan",   0, R.drawable.hl10, ""));
        news.add(new ModelPost("Sains",     0, R.drawable.hl11, ""));
        userHighlightMap.put("news_update", news);

        ArrayList<ModelPost> art = new ArrayList<>();
        art.add(new ModelPost("Digital",  0, R.drawable.hl12, ""));
        art.add(new ModelPost("Sketch",   0, R.drawable.hl13, ""));
        art.add(new ModelPost("Color",    0, R.drawable.hl14, ""));
        art.add(new ModelPost("3D",       0, R.drawable.hl15, ""));
        art.add(new ModelPost("Portrait", 0, R.drawable.hl16, ""));
        art.add(new ModelPost("Mural",    0, R.drawable.hl17, ""));
        art.add(new ModelPost("Process",  0, R.drawable.hl18, ""));
        userHighlightMap.put("art_showcase", art);
    }

    public static Map<String, ArrayList<ModelPost>> userFeedMap = new HashMap<>();

    // ── Feed per user (masing-masing 5 postingan unik) ────────────────────────
    public static void initUserFeedsIfEmpty() {
        if (!userFeedMap.isEmpty()) return;

        // adnan_denan
        ArrayList<ModelPost> adnanFead = new ArrayList<>();
        adnanFead.add(new ModelPost("adnandenan", "Nina Adnan", R.drawable.adnan1, R.drawable.adnan2, "Popi dan Nina"));
        adnanFead.add(new ModelPost("adnandenan", "Nina Adnan", R.drawable.adnan1, R.drawable.adnan3, "Popi, Momi, dan Nina"));
        adnanFead.add(new ModelPost("adnandenan", "Nina Adnan", R.drawable.adnan1, R.drawable.adnan5, "Maret"));
        adnanFead.add(new ModelPost("adnandenan", "Nina Adnan", R.drawable.adnan1, R.drawable.adnan6, "Heyyo"));
        adnanFead.add(new ModelPost("adnandenan", "Nina Adnan", R.drawable.adnan1, R.drawable.adnan4, "Riga the cat"));
        userFeedMap.put("adnandenan", adnanFead);

        // real_madrid
        ArrayList<ModelPost> madridFeed = new ArrayList<>();
        madridFeed.add(new ModelPost("realmadrid", "Real Madrid C.F.", R.drawable.r5, R.drawable.r2, "Jude Bellingham"));
        madridFeed.add(new ModelPost("realmadrid", "Real Madrid C.F.", R.drawable.r5, R.drawable.r4, "Vini Jr️"));
        madridFeed.add(new ModelPost("realmadrid", "Real Madrid C.F.", R.drawable.r5, R.drawable.r1, "Kylian Mbappe"));
        madridFeed.add(new ModelPost("realmadrid", "Real Madrid C.F.", R.drawable.r5, R.drawable.r3, "Dean Huijsen"));
        madridFeed.add(new ModelPost("realmadrid", "Real Madrid C.F.", R.drawable.r5, R.drawable.r6, "king"));
        userFeedMap.put("realmadrid", madridFeed);

        // angel_dev
        ArrayList<ModelPost> angelFeed = new ArrayList<>();
        angelFeed.add(new ModelPost("angelctrna_", "Angel Developer", R.drawable.a1, R.drawable.a2, "Slicing UI di Android Studio"));
        angelFeed.add(new ModelPost("angelctrna_", "Angel Developer", R.drawable.a1, R.drawable.a3, "Debug is my daily routine"));
        angelFeed.add(new ModelPost("angelctrna_", "Angel Developer", R.drawable.a2, R.drawable.a6, "Dark mode developer life"));
        angelFeed.add(new ModelPost("angelctrna_", "Angel Developer", R.drawable.a2, R.drawable.a5, "Git commit, push, repeat"));
        angelFeed.add(new ModelPost("angelctrna_", "Angel Developer", R.drawable.a1, R.drawable.a4, "Weekend coding session"));
        userFeedMap.put("angelctrna_", angelFeed);

        // photography_id
        ArrayList<ModelPost> photoFeed = new ArrayList<>();
        photoFeed.add(new ModelPost("photography_id", "Photography ID", R.drawable.foto_profil4, R.drawable.hl13, "Golden hour yang memukau"));
        photoFeed.add(new ModelPost("photography_id", "Photography ID", R.drawable.foto_profil4, R.drawable.hl14, "Portrait dengan cahaya alami"));
        photoFeed.add(new ModelPost("photography_id", "Photography ID", R.drawable.foto_profil4, R.drawable.hl15, "Street photography di kota tua"));
        photoFeed.add(new ModelPost("photography_id", "Photography ID", R.drawable.foto_profil4, R.drawable.hl16, "Macro shot — dunia kecil yang indah"));
        photoFeed.add(new ModelPost("photography_id", "Photography ID", R.drawable.foto_profil4, R.drawable.hl17, "Long exposure malam hari"));
        userFeedMap.put("photography_id", photoFeed);

        // traveler_indo
        ArrayList<ModelPost> travelFeed = new ArrayList<>();
        travelFeed.add(new ModelPost("traveler_indo", "Traveler Indonesia", R.drawable.foto_profil5, R.drawable.hl18, "Hidden gem Sulawesi Selatan"));
        travelFeed.add(new ModelPost("traveler_indo", "Traveler Indonesia", R.drawable.foto_profil5, R.drawable.hl19, "Raja Ampat, surga bawah laut"));
        travelFeed.add(new ModelPost("traveler_indo", "Traveler Indonesia", R.drawable.foto_profil5, R.drawable.hl20, "Pantai Lombok yang memesona"));
        travelFeed.add(new ModelPost("traveler_indo", "Traveler Indonesia", R.drawable.foto_profil5, R.drawable.hl21, "Sunrise di Gunung Bromo"));
        travelFeed.add(new ModelPost("traveler_indo", "Traveler Indonesia", R.drawable.foto_profil5, R.drawable.hl22, "Labuan Bajo, destinasi impian"));
        userFeedMap.put("traveler_indo", travelFeed);

        // manokwari_kita
        ArrayList<ModelPost> manoFeed = new ArrayList<>();
        manoFeed.add(new ModelPost("manokwari_kita", "Manokwari Kita", R.drawable.foto_profil6, R.drawable.hl23, "Kota Manokwari dari ketinggian"));
        manoFeed.add(new ModelPost("manokwari_kita", "Manokwari Kita", R.drawable.foto_profil6, R.drawable.hl24, "Pantai Pasir Putih Manokwari"));
        manoFeed.add(new ModelPost("manokwari_kita", "Manokwari Kita", R.drawable.foto_profil6, R.drawable.hl25, "Kuliner khas Papua Barat yang lezat"));
        manoFeed.add(new ModelPost("manokwari_kita", "Manokwari Kita", R.drawable.foto_profil6, R.drawable.hl26, "Budaya lokal yang kaya dan unik"));
        manoFeed.add(new ModelPost("manokwari_kita", "Manokwari Kita", R.drawable.foto_profil6, R.drawable.feed10, "Alam Papua Barat yang memukau"));
        userFeedMap.put("manokwari_kita", manoFeed);

        // meidianatahir
        ArrayList<ModelPost> meidianaFeed = new ArrayList<>();
        meidianaFeed.add(new ModelPost("meidianatahir", "Meidiana Tahir", R.drawable.m1, R.drawable.m2, "My Love"));
        meidianaFeed.add(new ModelPost("meidianatahir", "Meidiana Tahir", R.drawable.m1, R.drawable.m3, "Kucing"));
        meidianaFeed.add(new ModelPost("meidianatahir", "Meidiana Tahir", R.drawable.m1, R.drawable.m4, "Sayangku"));
        meidianaFeed.add(new ModelPost("meidianatahir", "Meidiana Tahir", R.drawable.m1, R.drawable.m5, "aku menemukan tenang, di seseorang yang tak pernah aku rencanakan"));
        meidianaFeed.add(new ModelPost("meidianatahir", "Meidiana Tahir", R.drawable.m1, R.drawable.m6, "Lagi Pengen aja"));
        userFeedMap.put("meidianatahir", meidianaFeed);

        // lifestyle_daily
        ArrayList<ModelPost> lifeFeed = new ArrayList<>();
        lifeFeed.add(new ModelPost("lifestyle_daily", "Lifestyle Daily", R.drawable.foto_profil8, R.drawable.feed2, "Morning routine untuk produktivitas"));
        lifeFeed.add(new ModelPost("lifestyle_daily", "Lifestyle Daily", R.drawable.foto_profil8, R.drawable.feed4, "OOTD hari ini — casual chic"));
        lifeFeed.add(new ModelPost("lifestyle_daily", "Lifestyle Daily", R.drawable.foto_profil8, R.drawable.feed6, "Meal prep mingguan yang sehat"));
        lifeFeed.add(new ModelPost("lifestyle_daily", "Lifestyle Daily", R.drawable.foto_profil8, R.drawable.feed8, "Workout pagi — stay fit!"));
        lifeFeed.add(new ModelPost("lifestyle_daily", "Lifestyle Daily", R.drawable.foto_profil8, R.drawable.feed10, "Skincare routine malam hari"));
        userFeedMap.put("lifestyle_daily", lifeFeed);

        // news_update
        ArrayList<ModelPost> newsFeed = new ArrayList<>();
        newsFeed.add(new ModelPost("news_update", "News Update ID", R.drawable.foto_profil9, R.drawable.hl21, "BREAKING: AI semakin canggih!"));
        newsFeed.add(new ModelPost("news_update", "News Update ID", R.drawable.foto_profil9, R.drawable.hl22, "Ekonomi Indonesia triwulan ini"));
        newsFeed.add(new ModelPost("news_update", "News Update ID", R.drawable.foto_profil9, R.drawable.hl23, "Teknologi hijau masa depan"));
        newsFeed.add(new ModelPost("news_update", "News Update ID", R.drawable.foto_profil9, R.drawable.hl24, "Olahraga nasional: highlight minggu ini"));
        newsFeed.add(new ModelPost("news_update", "News Update ID", R.drawable.foto_profil9, R.drawable.hl25, "Sains: penemuan terbaru peneliti Indonesia"));
        userFeedMap.put("news_update", newsFeed);

        // art_showcase
        ArrayList<ModelPost> artFeed = new ArrayList<>();
        artFeed.add(new ModelPost("art_showcase", "Art Showcase", R.drawable.foto_profil10, R.drawable.hl8, "Digital art — eksplorasi warna"));
        artFeed.add(new ModelPost("art_showcase", "Art Showcase", R.drawable.foto_profil10, R.drawable.hl9, "Sketsa karakter original"));
        artFeed.add(new ModelPost("art_showcase", "Art Showcase", R.drawable.foto_profil10, R.drawable.hl10, "Mural kota — seni untuk semua"));
        artFeed.add(new ModelPost("art_showcase", "Art Showcase", R.drawable.foto_profil10, R.drawable.hl11, "3D modeling WIP"));
        artFeed.add(new ModelPost("art_showcase", "Art Showcase", R.drawable.foto_profil10, R.drawable.hl12, "Portrait commission — terima kasih klien!"));
        userFeedMap.put("art_showcase", artFeed);
    }
    // ── Home Feed ─────────────────────────────────────────────────────────────
    public static void initHomeFeedIfEmpty() {
        if (!homeFeedList.isEmpty()) return;

        Object[][] data = {
                {
                        "adnandenan", "Nina Adnan",
                        R.drawable.adnan1, R.drawable.adnan2,
                        "An athlete on stage",
                        "92,9rb", "2.690",
                        "Diikuti oleh wordfangs, meidianatahir, dan 10 lainnya"
                },
                {
                        "realmadrid", "Real Madrid",
                        R.drawable.r5, R.drawable.r6,
                        "15 UCL nih Senggol dong",
                        "181JT", "64",
                        "Diikuti oleh angelctrna_, fand_, dan 5 lainnya"
                },
                {
                        "angel_dev", "Angel Developer",
                        R.drawable.a1, R.drawable.a2,
                        "Angello",
                        "1,2 Rb", "340",
                        "Diikuti oleh realmadrid, meidianatahir, dan 3 lainnya"
                },
                {
                        "photography_id", "Photography ID",
                        R.drawable.foto_profil4, R.drawable.feed4,
                        "Golden hour di pantai tidak pernah mengecewakan.",
                        "45,3 Rb", "210",
                        "Diikuti oleh lens_hunter, photo_nusa, dan 8 lainnya"
                },
                {
                        "traveler_indo", "Traveler Indonesia",
                        R.drawable.foto_profil5, R.drawable.feed5,
                        "Hidden gem di Sulawesi Selatan wajib dikunjungi!",
                        "12,7 Rb", "890",
                        "Diikuti oleh jalan_jalan_id, backpacker_asia, dan 4 lainnya"
                },
                {
                        "manokwari_kita", "Manokwari Kita",
                        R.drawable.foto_profil6, R.drawable.feed6,
                        "Pemandangan kota Manokwari dari ketinggian. Cantik!",
                        "5,6 Rb", "302",
                        "Diikuti oleh papua_explore, west_papua_id, dan 2 lainnya"
                },
                {
                        "meidianatahir", "Meidiana Tahir",
                        R.drawable.m1, R.drawable.m6,
                        "Meidiana tahir",
                        "78,9 Rb", "156",
                        "Diikuti oleh angelle_, baskaraputra, dan 6 lainnya"
                },
                {
                        "lifestyle_daily", "Lifestyle Daily",
                        R.drawable.foto_profil8, R.drawable.feed8,
                        "Menikmati kopi pagi untuk produktivitas hari ini.",
                        "23,1 Rb", "512",
                        "Diikuti oleh hidup_sehat_id, morning_vibes, dan 7 lainnya"
                },
                {
                        "news_update", "News Update ID",
                        R.drawable.foto_profil9, R.drawable.feed9,
                        "BREAKING: Teknologi AI semakin canggih membantu developer.",
                        "156 Rb", "88",
                        "Diikuti oleh info_terkini, berita_hari_ini, dan 12 lainnya"
                },
                {
                        "art_showcase", "Art Showcase",
                        R.drawable.foto_profil10, R.drawable.feed10,
                        "Ekspresi seni tanpa batas dalam setiap sapuan warna.",
                        "9,4 Rb", "430",
                        "Diikuti oleh seni_nusantara, lukis_digital, dan 3 lainnya"
                }
        };

        for (Object[] d : data) {
            homeFeedList.add(new ModelPost(
                    (String) d[0], (String) d[1],
                    (int)    d[2], (int)    d[3],
                    (String) d[4], (String) d[5],
                    (String) d[6], (String) d[7]
            ));
        }
    }

    // ── Profile Feed akun sendiri ─────────────────────────────────────────────

    public static void initProfileFeedIfEmpty() {
        for (ModelPost p : profileFeedList) {
            if (p.getImageResId() == R.drawable.feed1) return;
        }

        int[] images = {
                R.drawable.feed1, R.drawable.feed2, R.drawable.feed3,
                R.drawable.feed4, R.drawable.feed5
        };
        String[] captions = {
                "Sunset view", "Good food", "Weekend vibes",
                "Nature walk", "City lights"
        };
        for (int i = 0; i < images.length; i++) {
            profileFeedList.add(new ModelPost(
                    "angelctrna_", "angel catrina sobbu",
                    R.drawable.foto_profil,
                    images[i], captions[i]
            ));
        }
    }
    }
