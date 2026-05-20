package com.ishmah.musecard;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

public class SettingsActivity extends AppCompatActivity {

    private static final String PREF_NAME   = "musecard_pref";
    private static final String KEY_DARK_MODE = "dark_mode";

    private SharedPreferences prefs;
    private SwitchCompat switchDarkMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        initViews();
    }

    private void initViews() {
        switchDarkMode = findViewById(R.id.switch_dark_mode);
        View rowAbout  = findViewById(R.id.row_about);
        View rowReset  = findViewById(R.id.row_reset);

        findViewById(R.id.btn_back).setOnClickListener(v -> finish());

        // Reflect current saved state (already applied by Application)
        switchDarkMode.setChecked(prefs.getBoolean(KEY_DARK_MODE, false));

        switchDarkMode.setOnCheckedChangeListener((btn, isChecked) -> {
            prefs.edit().putBoolean(KEY_DARK_MODE, isChecked).apply();
            // Switch night mode globally — the system will recreate all activities
            AppCompatDelegate.setDefaultNightMode(isChecked
                    ? AppCompatDelegate.MODE_NIGHT_YES
                    : AppCompatDelegate.MODE_NIGHT_NO);
        });

        rowAbout.setOnClickListener(v -> showAboutDialog());
        rowReset.setOnClickListener(v -> showResetDialog());
    }

    private void showAboutDialog() {
        new AlertDialog.Builder(this)
            .setTitle("MuseCard 🎵")
            .setMessage("MuseCard v1.0 🎵\n\nMade with 💕 for Praktikum Mobile\nYour personal music identity card")
            .setPositiveButton("OK 💕", null)
            .show();
    }

    private void showResetDialog() {
        new AlertDialog.Builder(this)
            .setTitle("Reset Data? 🥺")
            .setMessage("Semua data musikmu akan dihapus dan dikembalikan ke default...")
            .setPositiveButton("Reset 😢", (dialog, which) -> {
                prefs.edit().clear().apply();
                Toast.makeText(this, "Data berhasil direset 🌸", Toast.LENGTH_SHORT).show();
                finish();
            })
            .setNegativeButton("Batal 💕", null)
            .show();
    }
}
