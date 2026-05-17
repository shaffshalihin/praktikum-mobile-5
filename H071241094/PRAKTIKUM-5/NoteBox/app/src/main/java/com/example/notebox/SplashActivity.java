package com.example.notebox;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

// ── IMPORT RESOURCES YANG SUDAH DIARAHKAN KE PACKAGE ASLI (BENAR)
import com.example.notebox.R;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(() -> {
            SharedPrefManager spm = SharedPrefManager.getInstance(this);
            String current = spm.getCurrentUser();

            if (current != null) {
                startActivity(new Intent(this, MainActivity.class));
            } else {
                startActivity(new Intent(this, LoginActivity.class));
            }
            finish();
        }, 1200);
    }
}