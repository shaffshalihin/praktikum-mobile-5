package com.ishmah.praktikum1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfileActivity extends AppCompatActivity {

    private static final String PREF_NAME    = "profile_pref";
    private static final String KEY_NAME     = "name";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_BIO      = "bio";
    private static final String KEY_LINK     = "link";
    private static final String KEY_PHOTO    = "photo_uri";
    private static final String KEY_POSTS    = "posts";
    private static final String KEY_FOLLOWERS  = "followers";
    private static final String KEY_FOLLOWING  = "following";

    private CircleImageView ivEditFoto;
    private EditText etNama, etUsername, etBio, etLink, etPosts, etFollowers, etFollowing;
    private SharedPreferences prefs;
    private String photoUri = "";

    private ActivityResultLauncher<Intent> galleryLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        prefs       = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        ivEditFoto  = findViewById(R.id.iv_edit_foto);
        etNama      = findViewById(R.id.et_nama);
        etUsername  = findViewById(R.id.et_username);
        etBio       = findViewById(R.id.et_bio);
        etLink      = findViewById(R.id.et_link);
        etPosts     = findViewById(R.id.et_posts);
        etFollowers = findViewById(R.id.et_followers);
        etFollowing = findViewById(R.id.et_following);

        galleryLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Uri uri = result.getData().getData();
                    if (uri != null) {
                        try {
                            getContentResolver().takePersistableUriPermission(
                                uri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        } catch (Exception ignored) {}
                        photoUri = uri.toString();
                        ivEditFoto.setImageURI(uri);
                    }
                }
            }
        );

        // Load saved data
        photoUri = prefs.getString(KEY_PHOTO, "");
        etNama.setText(prefs.getString(KEY_NAME, ""));
        etUsername.setText(prefs.getString(KEY_USERNAME, ""));
        etBio.setText(prefs.getString(KEY_BIO, ""));
        etLink.setText(prefs.getString(KEY_LINK, ""));
        etPosts.setText(prefs.getString(KEY_POSTS, ""));
        etFollowers.setText(prefs.getString(KEY_FOLLOWERS, ""));
        etFollowing.setText(prefs.getString(KEY_FOLLOWING, ""));

        if (!photoUri.isEmpty()) {
            try { ivEditFoto.setImageURI(Uri.parse(photoUri)); }
            catch (Exception ignored) {}
        }

        ivEditFoto.setOnClickListener(v -> openGallery());
        findViewById(R.id.tv_ganti_foto_edit).setOnClickListener(v -> openGallery());
        findViewById(R.id.tv_change_photo_label).setOnClickListener(v -> openGallery());

        findViewById(R.id.btn_back).setOnClickListener(v -> finish());

        findViewById(R.id.btn_done).setOnClickListener(v -> {
            prefs.edit()
                .putString(KEY_NAME,      etNama.getText().toString().trim())
                .putString(KEY_USERNAME,  etUsername.getText().toString().trim())
                .putString(KEY_BIO,       etBio.getText().toString().trim())
                .putString(KEY_LINK,      etLink.getText().toString().trim())
                .putString(KEY_PHOTO,     photoUri)
                .putString(KEY_POSTS,     etPosts.getText().toString().trim())
                .putString(KEY_FOLLOWERS, etFollowers.getText().toString().trim())
                .putString(KEY_FOLLOWING, etFollowing.getText().toString().trim())
                .apply();

            setResult(RESULT_OK);
            finish();
        });
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("image/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        galleryLauncher.launch(intent);
    }
}
