package com.example.notebox;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

// ── IMPORT RESOURCES YANG SUDAH DIARAHKAN KE PACKAGE ASLI (BENAR)
import com.example.notebox.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText  etUsername, etDisplayName, etPassword, etConfirm;
    private TextView  tvError;
    private SharedPrefManager spm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        spm           = SharedPrefManager.getInstance(this);
        etUsername    = findViewById(R.id.et_reg_username);
        etDisplayName = findViewById(R.id.et_reg_displayname);
        etPassword    = findViewById(R.id.et_reg_password);
        etConfirm     = findViewById(R.id.et_reg_confirm);
        tvError       = findViewById(R.id.tv_reg_error);

        Button   btnRegister = findViewById(R.id.btn_register);
        TextView tvLogin     = findViewById(R.id.tv_go_login);

        btnRegister.setOnClickListener(v -> doRegister());
        tvLogin.setOnClickListener(v -> finish());
    }

    private void doRegister() {
        String username = etUsername.getText().toString().trim();
        String display  = etDisplayName.getText().toString().trim();
        String pass     = etPassword.getText().toString();
        String confirm  = etConfirm.getText().toString();

        if (username.isEmpty() || pass.isEmpty() || display.isEmpty()) {
            showError("Semua kolom wajib diisi!"); return;
        }
        if (username.length() < 3) {
            showError("Username minimal 3 karakter!"); return;
        }
        if (pass.length() < 4) {
            showError("Password minimal 4 karakter!"); return;
        }
        if (!pass.equals(confirm)) {
            showError("Konfirmasi password tidak cocok!"); return;
        }
        if (spm.isUsernameTaken(username)) {
            showError("Username sudah digunakan!"); return;
        }


        spm.registerUser(new User(username, pass, display));
        Toast.makeText(this, "✅ Akun berhasil dibuat! Silakan login.", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    private void showError(String msg) {
        tvError.setText(msg);
        tvError.setVisibility(android.view.View.VISIBLE);
    }
}