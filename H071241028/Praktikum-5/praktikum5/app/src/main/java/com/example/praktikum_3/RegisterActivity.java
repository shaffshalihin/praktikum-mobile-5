package com.example.praktikum_3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import com.example.praktikum_3.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Terapkan tema sebelum super.onCreate
        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        boolean isDarkMode = sharedPreferences.getBoolean("isDarkMode", false);
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Logika Tombol Register
        binding.btnRegister.setOnClickListener(v -> {
            String fullName = binding.etFullName.getText().toString().trim();
            String nimStr = binding.etNim.getText().toString().trim();
            String email = binding.etEmail.getText().toString().trim();
            String username = binding.etUsername.getText().toString().trim();
            String password = binding.etPassword.getText().toString().trim();

            if (fullName.isEmpty() || nimStr.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Harap isi semua bidang", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                int nim = Integer.parseInt(nimStr);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("fullName", fullName);
                editor.putInt("nim", nim);
                editor.putString("email", email);
                editor.putString("username", username);
                editor.putString("password", password);
                editor.apply();

                Toast.makeText(this, "Registrasi Berhasil!", Toast.LENGTH_SHORT).show();
                
                // Setelah registrasi, langsung arahkan ke Login
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            } catch (NumberFormatException e) {
                Toast.makeText(this, "NIM harus berupa angka", Toast.LENGTH_SHORT).show();
            }
        });

        // PERBAIKAN: Logika Klik "Masuk di sini"
        binding.tvLoginLink.setOnClickListener(v -> {
            // Mengarahkan kembali ke LoginActivity
            finish();
        });
    }
}