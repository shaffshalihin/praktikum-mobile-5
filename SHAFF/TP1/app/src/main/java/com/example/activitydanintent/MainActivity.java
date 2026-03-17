package com.example.activitydanintent;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_USERNAME = "extra_username";
    public static final String EXTRA_IMAGE = "extra_image";
    private TextView tvUsername;
    private ImageView ivImage;

    private final ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    String username = result.getData().getStringExtra(EXTRA_USERNAME);
                    String imgStr = result.getData().getStringExtra(EXTRA_IMAGE);

                    tvUsername.setText(username);
                    ivImage.setImageURI(Uri.parse(imgStr));
                }
            }
    );


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        tvUsername = findViewById(R.id.tv_username);
        ivImage = findViewById(R.id.iv_image);

        Button editProfileButton = findViewById(R.id.bt_edit_profile);
        editProfileButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Detail.class);
            launcher.launch(intent);
        });
    }
}
