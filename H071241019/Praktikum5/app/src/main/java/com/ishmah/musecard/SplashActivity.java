package com.ishmah.musecard;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private final Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        View logo     = findViewById(R.id.splash_logo);
        View title    = findViewById(R.id.splash_title);
        View subtitle = findViewById(R.id.splash_subtitle);
        View dots     = findViewById(R.id.splash_dots);

        int[] flowerIds = {
            R.id.splash_flower1, R.id.splash_flower2,
            R.id.splash_flower3, R.id.splash_flower4
        };
        Animation floatAnim = AnimationUtils.loadAnimation(this, R.anim.float_up_down);
        for (int i = 0; i < flowerIds.length; i++) {
            View flower = findViewById(flowerIds[i]);
            if (flower != null) {
                Animation a = AnimationUtils.loadAnimation(this, R.anim.float_up_down);
                a.setStartOffset(i * 250L);
                flower.startAnimation(a);
            }
        }

        // Logo: scale in at t=0
        handler.postDelayed(() -> {
            logo.setVisibility(View.VISIBLE);
            logo.startAnimation(AnimationUtils.loadAnimation(this, R.anim.splash_logo_in));
        }, 100);

        // Title: slide up at t=300ms
        handler.postDelayed(() -> {
            title.setVisibility(View.VISIBLE);
            title.startAnimation(AnimationUtils.loadAnimation(this, R.anim.splash_title_in));
        }, 400);

        // Subtitle: fade in at t=600ms
        handler.postDelayed(() -> {
            subtitle.setVisibility(View.VISIBLE);
            subtitle.startAnimation(AnimationUtils.loadAnimation(this, R.anim.splash_fade_in));
        }, 700);

        // Dots: fade in at t=900ms
        handler.postDelayed(() -> {
            dots.setVisibility(View.VISIBLE);
            dots.startAnimation(AnimationUtils.loadAnimation(this, R.anim.splash_fade_in));
        }, 950);

        // Navigate to MainActivity at t=2500ms
        handler.postDelayed(() -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }, 2500);
    }

    @Override
    public void onBackPressed() {
        // Intentionally empty — can't back out of splash
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
