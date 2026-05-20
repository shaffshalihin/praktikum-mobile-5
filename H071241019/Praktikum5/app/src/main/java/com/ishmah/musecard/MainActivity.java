package com.ishmah.musecard;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private static final String PREF_NAME = "musecard_pref";
    private static final String KEY_NAME = "user_name";
    private static final String KEY_GENRE = "genre";
    private static final String KEY_ARTIST = "artist";
    private static final String KEY_MOOD = "mood";

    private SharedPreferences prefs;
    private TextView tvName, tvGenre, tvMoodEmoji, tvMoodLabel, tvArtist, tvVolumeValue, tvInitials;
    private SeekBar sbVolume;
    private View bar1, bar2, bar3;
    private LinearLayout tracksSection, tracksContainer;
    private ProgressBar progressTracks;
    private AudioManager audioManager;

    private String lastFetchedArtist = "";
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        initViews();
        loadData();
        startFloatingAnimations();
        startEqualizerAnimations();

        View mainCard = findViewById(R.id.main_card);
        if (mainCard != null) {
            Animation entrance = AnimationUtils.loadAnimation(this, R.anim.slide_up_fade);
            mainCard.startAnimation(entrance);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executor.shutdownNow();
    }

    private void initViews() {
        tvName = findViewById(R.id.tv_name);
        tvGenre = findViewById(R.id.tv_genre);
        tvMoodEmoji = findViewById(R.id.tv_mood_emoji);
        tvMoodLabel = findViewById(R.id.tv_mood_label);
        tvArtist = findViewById(R.id.tv_artist);
        tvVolumeValue = findViewById(R.id.tv_volume_value);
        tvInitials = findViewById(R.id.tv_initials);
        sbVolume = findViewById(R.id.sb_volume);
        bar1 = findViewById(R.id.bar1);
        bar2 = findViewById(R.id.bar2);
        bar3 = findViewById(R.id.bar3);
        tracksSection = findViewById(R.id.tracks_section);
        tracksContainer = findViewById(R.id.tracks_container);
        progressTracks = findViewById(R.id.progress_tracks);

        findViewById(R.id.btn_edit).setOnClickListener(v ->
                startActivity(new Intent(this, EditProfileActivity.class)));
        findViewById(R.id.btn_settings).setOnClickListener(v ->
                startActivity(new Intent(this, SettingsActivity.class)));
    }

    private void loadData() {
        String name   = prefs.getString(KEY_NAME,   "Your Name");
        String genre  = prefs.getString(KEY_GENRE,  "");
        String artist = prefs.getString(KEY_ARTIST, "");
        String mood   = prefs.getString(KEY_MOOD,   "😊");

        tvName.setText(name);
        updateAvatar(name);

        tvGenre.setVisibility(genre.isEmpty() ? View.GONE : View.VISIBLE);
        if (!genre.isEmpty()) tvGenre.setText(genre);

        tvMoodEmoji.setText(mood);
        tvMoodLabel.setText(getMoodLabel(mood));
        tvArtist.setText(artist.isEmpty() ? "Not set yet" : artist);

        // Real system media volume
        int maxVol = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int curVol = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        sbVolume.setMax(maxVol);
        sbVolume.setProgress(curVol);
        tvVolumeValue.setText((maxVol > 0 ? curVol * 100 / maxVol : 0) + "%");

        // Fetch real tracks only when artist changes
        if (!artist.isEmpty() && !artist.equals(lastFetchedArtist)) {
            lastFetchedArtist = artist;
            fetchTopTracks(artist);
        } else if (artist.isEmpty()) {
            tracksSection.setVisibility(View.GONE);
            lastFetchedArtist = "";
        }
    }

    private void fetchTopTracks(String artist) {
        tracksSection.setVisibility(View.VISIBLE);
        progressTracks.setVisibility(View.VISIBLE);
        tracksContainer.removeAllViews();

        executor.execute(() -> {
            try {
                String encoded = URLEncoder.encode(artist, "UTF-8");
                URL url = new URL("https://itunes.apple.com/search?term="
                        + encoded + "&media=music&entity=song&limit=5");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(6000);
                conn.setReadTimeout(6000);

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) sb.append(line);
                reader.close();
                conn.disconnect();

                JSONArray results = new JSONObject(sb.toString()).getJSONArray("results");

                handler.post(() -> {
                    progressTracks.setVisibility(View.GONE);
                    tracksContainer.removeAllViews();
                    if (results.length() == 0) {
                        tracksSection.setVisibility(View.GONE);
                        return;
                    }
                    for (int i = 0; i < results.length(); i++) {
                        try {
                            JSONObject item = results.getJSONObject(i);
                            String trackName  = item.optString("trackName", "");
                            String artistName = item.optString("artistName", "");
                            String artUrl     = item.optString("artworkUrl100", "");
                            if (trackName.isEmpty()) continue;

                            View row = LayoutInflater.from(this)
                                    .inflate(R.layout.item_track, tracksContainer, false);
                            ((TextView) row.findViewById(R.id.tv_track_name)).setText(trackName);
                            ((TextView) row.findViewById(R.id.tv_track_artist)).setText(artistName);

                            ImageView ivArt = row.findViewById(R.id.iv_album_art);
                            if (!artUrl.isEmpty()) {
                                Glide.with(this)
                                        .load(artUrl)
                                        .placeholder(R.drawable.bg_circle_avatar)
                                        .circleCrop()
                                        .into(ivArt);
                            }
                            tracksContainer.addView(row);
                        } catch (Exception ignored) {}
                    }
                });
            } catch (Exception e) {
                handler.post(() -> {
                    progressTracks.setVisibility(View.GONE);
                    tracksSection.setVisibility(View.GONE);
                    lastFetchedArtist = ""; // allow retry
                });
            }
        });
    }

    private void updateAvatar(String name) {
        String initials = "?";
        if (name != null && !name.trim().isEmpty()) {
            String[] words = name.trim().split("\\s+");
            if (words.length >= 2 && words[1].length() > 0) {
                initials = ("" + words[0].charAt(0) + words[1].charAt(0)).toUpperCase();
            } else {
                int len = Math.min(2, words[0].length());
                initials = len > 0 ? words[0].substring(0, len).toUpperCase() : "?";
            }
        }
        tvInitials.setText(initials);
    }

    private String getMoodLabel(String emoji) {
        switch (emoji) {
            case "😊": return "Happy";
            case "😎": return "Cool";
            case "😴": return "Chill";
            case "🥳": return "Party";
            case "😢": return "Sad";
            case "🔥": return "Hype";
            case "💕": return "Lovey";
            case "🌙": return "Night";
            case "🎯": return "Focus";
            case "😤": return "Intense";
            default:   return "Vibing";
        }
    }

    private void startFloatingAnimations() {
        int[] ids = {R.id.flower1, R.id.flower2, R.id.flower3, R.id.flower4,
                     R.id.blur_circle1, R.id.blur_circle2,
                     R.id.music_note1, R.id.music_note2};
        for (int i = 0; i < ids.length; i++) {
            View v = findViewById(ids[i]);
            if (v != null) {
                Animation anim = AnimationUtils.loadAnimation(this, R.anim.float_up_down);
                anim.setStartOffset(i * 350L);
                v.startAnimation(anim);
            }
        }
    }

    private void startEqualizerAnimations() {
        animateEqualizerBar(bar1, 0);
        animateEqualizerBar(bar2, 200);
        animateEqualizerBar(bar3, 400);
    }

    private void animateEqualizerBar(View bar, long delay) {
        if (bar == null) return;
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.equalizer_bar);
        anim.setStartOffset(delay);
        bar.startAnimation(anim);
    }
}
