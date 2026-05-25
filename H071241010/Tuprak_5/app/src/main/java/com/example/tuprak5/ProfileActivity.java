package com.example.tuprak5;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tuprak5.databinding.ActivityProfileBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {
    private ActivityProfileBinding binding;
    private SharedPrefsManager prefs;
    private Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        prefs = new SharedPrefsManager(this);

        binding.toolbar.setNavigationIcon(android.R.drawable.ic_menu_revert);
        binding.toolbar.setNavigationOnClickListener(v -> finish());

        String username = prefs.getUsername();
        binding.tvUsername.setText(username);
        binding.tvHandle.setText("@" + username.toLowerCase().replace(" ", ""));

        String postsJson = prefs.getPostsData();
        List<Post> postList = new ArrayList<>();
        if (!postsJson.equals("[]") && !postsJson.isEmpty()) {
            Type type = new TypeToken<List<Post>>(){}.getType();
            postList = gson.fromJson(postsJson, type);
        }

        int count = 0;
        for (Post p : postList) {
            if (p.getUsername().equals(username)) {
                count++;
            }
        }

        binding.tvPostCount.setText("Jumlah post: " + count);

        binding.btnEditProfile.setOnClickListener(v -> {
            View dialogView = getLayoutInflater().inflate(R.layout.dialog_edit_profile, null);
            EditText etUsername = dialogView.findViewById(R.id.etUsername);
            Button btnCancel = dialogView.findViewById(R.id.btnCancel);
            Button btnSave = dialogView.findViewById(R.id.btnSave);

            etUsername.setText(prefs.getUsername());

            android.app.AlertDialog dialog = new android.app.AlertDialog.Builder(this)
                    .setView(dialogView)
                    .create();
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialog.show();

            btnCancel.setOnClickListener(v1 -> dialog.dismiss());

            btnSave.setOnClickListener(v1 -> {
                String newUsername = etUsername.getText().toString().trim();
                if (newUsername.isEmpty()) {
                    Toast.makeText(this, "Username tidak boleh kosong", Toast.LENGTH_SHORT).show();
                    return;
                }
                
                if (newUsername.equals(prefs.getUsername())) {
                    dialog.dismiss();
                    return;
                }

                if (prefs.isUserExists(newUsername)) {
                    Toast.makeText(this, "Username sudah digunakan", Toast.LENGTH_SHORT).show();
                    return;
                }

                String oldUsername = prefs.getUsername();
                prefs.updateUserCredential(oldUsername, newUsername);
                prefs.setUsername(newUsername);

                String currentPostsJson = prefs.getPostsData();
                if (!currentPostsJson.equals("[]") && !currentPostsJson.isEmpty()) {
                    Type type = new TypeToken<List<Post>>(){}.getType();
                    List<Post> currentPostList = gson.fromJson(currentPostsJson, type);
                    for (Post p : currentPostList) {
                        if (p.getUsername().equals(oldUsername)) {
                            p.setUsername(newUsername);
                        }
                    }
                    prefs.setPostsData(gson.toJson(currentPostList));
                }

                binding.tvUsername.setText(newUsername);
                binding.tvHandle.setText("@" + newUsername.toLowerCase().replace(" ", ""));
                Toast.makeText(this, "Profil berhasil diupdate", Toast.LENGTH_SHORT).show();
                
                dialog.dismiss();
            });
        });
    }
}
