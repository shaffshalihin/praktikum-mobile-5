package com.ishmah.musecard;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.ContextCompat;

import com.google.android.flexbox.FlexboxLayout;

public class EditProfileActivity extends AppCompatActivity {

    private static final String PREF_NAME = "musecard_pref";
    private static final String KEY_NAME   = "user_name";
    private static final String KEY_GENRE  = "genre";
    private static final String KEY_ARTIST = "artist";
    private static final String KEY_MOOD   = "mood";
    private static final String KEY_REPEAT = "repeat_mode";

    private SharedPreferences prefs;
    private EditText etName, etArtist;
    private TextView tvVolumePercent;
    private SeekBar sbVolume;
    private SwitchCompat switchRepeat;
    private FlexboxLayout chipContainer;
    private GridLayout moodGrid;
    private AudioManager audioManager;
    private int maxVolume;

    private String selectedGenre = "";
    private String selectedMood  = "😊";

    private final String[] genres = {
        "Pop 🎤", "Jazz 🎷", "R&B 🎶", "K-Pop 💫",
        "Classical 🎻", "EDM ⚡", "Indie 🌙", "Hip-Hop 🎧"
    };
    private final String[] moods = {
        "😊", "😎", "😴", "🥳", "😢",
        "🔥", "💕", "🌙", "🎯", "😤"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        initViews();
        loadExistingData();
        setupChips();
        setupMoodPicker();
        setupVolumeListener();
        startFloatingAnimations();
    }

    private void initViews() {
        etName        = findViewById(R.id.et_name);
        etArtist      = findViewById(R.id.et_artist);
        tvVolumePercent = findViewById(R.id.tv_volume_percent);
        sbVolume      = findViewById(R.id.sb_volume);
        switchRepeat  = findViewById(R.id.switch_repeat);
        chipContainer = findViewById(R.id.chip_container);
        moodGrid      = findViewById(R.id.mood_grid);

        sbVolume.setMax(maxVolume);

        findViewById(R.id.btn_back).setOnClickListener(v -> finish());
        findViewById(R.id.btn_save).setOnClickListener(v -> {
            saveData();
            animateSaveButton(v);
        });
    }

    private void loadExistingData() {
        etName.setText(prefs.getString(KEY_NAME, ""));
        etArtist.setText(prefs.getString(KEY_ARTIST, ""));
        selectedGenre = prefs.getString(KEY_GENRE, "");
        selectedMood  = prefs.getString(KEY_MOOD,  "😊");
        switchRepeat.setChecked(prefs.getBoolean(KEY_REPEAT, false));

        // Show actual system media volume
        int curVol = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        sbVolume.setProgress(curVol);
        tvVolumePercent.setText((maxVolume > 0 ? curVol * 100 / maxVolume : 0) + "%");
    }

    private void setupChips() {
        chipContainer.removeAllViews();
        for (String genre : genres) {
            TextView chip = new TextView(this);
            chip.setText(genre);
            chip.setPadding(dpToPx(16), dpToPx(8), dpToPx(16), dpToPx(8));
            chip.setTextSize(13f);
            chip.setSingleLine(true);

            FlexboxLayout.LayoutParams params = new FlexboxLayout.LayoutParams(
                FlexboxLayout.LayoutParams.WRAP_CONTENT,
                FlexboxLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(dpToPx(4), dpToPx(4), dpToPx(4), dpToPx(4));
            chip.setLayoutParams(params);

            updateChipStyle(chip, genre.equals(selectedGenre));

            chip.setOnClickListener(v -> {
                selectedGenre = genre;
                v.startAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_pop));
                for (int i = 0; i < chipContainer.getChildCount(); i++) {
                    View child = chipContainer.getChildAt(i);
                    if (child instanceof TextView) {
                        updateChipStyle((TextView) child, child == v);
                    }
                }
            });
            chipContainer.addView(chip);
        }
    }

    private void updateChipStyle(TextView chip, boolean selected) {
        chip.setBackground(ContextCompat.getDrawable(this,
                selected ? R.drawable.bg_chip_selected : R.drawable.bg_chip_unselected));
        chip.setTextColor(selected ? Color.WHITE
                : ContextCompat.getColor(this, R.color.text_primary));
    }

    private void setupMoodPicker() {
        moodGrid.removeAllViews();
        for (String emoji : moods) {
            TextView moodBtn = new TextView(this);
            moodBtn.setText(emoji);
            moodBtn.setTextSize(26f);
            moodBtn.setGravity(Gravity.CENTER);
            moodBtn.setPadding(dpToPx(4), dpToPx(4), dpToPx(4), dpToPx(4));

            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width  = dpToPx(54);
            params.height = dpToPx(54);
            params.setMargins(dpToPx(3), dpToPx(3), dpToPx(3), dpToPx(3));
            moodBtn.setLayoutParams(params);

            updateMoodStyle(moodBtn, emoji.equals(selectedMood));

            moodBtn.setOnClickListener(v -> {
                selectedMood = emoji;
                v.startAnimation(AnimationUtils.loadAnimation(this, R.anim.bounce));
                for (int i = 0; i < moodGrid.getChildCount(); i++) {
                    View child = moodGrid.getChildAt(i);
                    if (child instanceof TextView) {
                        updateMoodStyle((TextView) child, child == v);
                    }
                }
            });
            moodGrid.addView(moodBtn);
        }
    }

    private void updateMoodStyle(TextView btn, boolean selected) {
        btn.setBackground(selected
                ? ContextCompat.getDrawable(this, R.drawable.bg_mood_selected) : null);
        btn.setScaleX(selected ? 1.2f : 1.0f);
        btn.setScaleY(selected ? 1.2f : 1.0f);
    }

    private void setupVolumeListener() {
        sbVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    // Directly change actual media volume
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
                }
                int pct = maxVolume > 0 ? progress * 100 / maxVolume : 0;
                tvVolumePercent.setText(pct + "%");
            }
            @Override public void onStartTrackingTouch(SeekBar s) {}
            @Override public void onStopTrackingTouch(SeekBar s)  {}
        });
    }

    private void saveData() {
        String name   = etName.getText().toString().trim();
        String artist = etArtist.getText().toString().trim();
        int pct = maxVolume > 0 ? sbVolume.getProgress() * 100 / maxVolume : 0;

        prefs.edit()
            .putString(KEY_NAME,   name.isEmpty() ? "Your Name" : name)
            .putString(KEY_GENRE,  selectedGenre)
            .putString(KEY_ARTIST, artist)
            .putString(KEY_MOOD,   selectedMood)
            .putInt("volume",      pct)
            .putBoolean(KEY_REPEAT, switchRepeat.isChecked())
            .apply();
    }

    private void animateSaveButton(View btn) {
        btn.animate().scaleX(0.95f).scaleY(0.95f).setDuration(100)
            .withEndAction(() ->
                btn.animate().scaleX(1f).scaleY(1f).setDuration(150)
                    .withEndAction(() -> btn.postDelayed(this::finish, 350))
                    .start())
            .start();
    }

    private void startFloatingAnimations() {
        int[] ids = {R.id.flower1, R.id.flower2};
        for (int i = 0; i < ids.length; i++) {
            View v = findViewById(ids[i]);
            if (v != null) {
                Animation anim = AnimationUtils.loadAnimation(this, R.anim.float_up_down);
                anim.setStartOffset(i * 500L);
                v.startAnimation(anim);
            }
        }
    }

    private int dpToPx(int dp) {
        return Math.round(dp * getResources().getDisplayMetrics().density);
    }
}
