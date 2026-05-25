package com.indira.tp3.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.indira.tp3.R;
import com.indira.tp3.fragments.HomeFragment;
import com.indira.tp3.fragments.FavoritesFragment;
import com.indira.tp3.fragments.AddBookFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE = 100;
    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkAndRequestPermissions();

        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(item -> {
            replaceFragmentById(item.getItemId());
            return true;
        });

        // Check if we came from DetailActivity with a target fragment
        int targetFragmentId = getIntent().getIntExtra("TARGET_FRAGMENT", R.id.nav_home);
        bottomNav.setSelectedItemId(targetFragmentId);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        int targetFragmentId = intent.getIntExtra("TARGET_FRAGMENT", R.id.nav_home);
        bottomNav.setSelectedItemId(targetFragmentId);
    }

    private void replaceFragmentById(int itemId) {
        Fragment selected = null;
        if (itemId == R.id.nav_home) {
            selected = new HomeFragment();
        } else if (itemId == R.id.nav_favorites) {
            selected = new FavoritesFragment();
        } else if (itemId == R.id.nav_add) {
            selected = new AddBookFragment();
        }
        if (selected != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selected).commit();
        }
    }

    private void checkAndRequestPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_MEDIA_IMAGES},
                        PERMISSION_REQUEST_CODE);
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        PERMISSION_REQUEST_CODE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            }
        }
    }
}
