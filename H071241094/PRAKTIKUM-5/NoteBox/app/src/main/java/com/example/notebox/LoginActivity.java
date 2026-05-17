package com.example.notebox;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.CycleInterpolator;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

// IMPORT YANG BENAR UNTUK AKSES RESOURCES (LAYOUT, ID, DLL)
import com.example.notebox.R;

public class LoginActivity extends AppCompatActivity {

    private EditText  etUsername, etPassword;
    private CheckBox  cbRemember;
    private TextView  tvError;
    private SharedPrefManager spm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        spm        = SharedPrefManager.getInstance(this);
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        cbRemember = findViewById(R.id.cb_remember);
        tvError    = findViewById(R.id.tv_error);

        Button btnLogin    = findViewById(R.id.btn_login);
        TextView tvRegister = findViewById(R.id.tv_go_register);

        String saved = spm.getSavedUser();
        if (saved != null) etUsername.setText(saved);

        btnLogin.setOnClickListener(v -> doLogin());

        tvRegister.setOnClickListener(v ->
                startActivity(new Intent(this, RegisterActivity.class)));
    }

    private void doLogin() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString();

        if (username.isEmpty() || password.isEmpty()) {
            showError("Username dan password tidak boleh kosong!");
            shakeForm();
            return;
        }

        User user = spm.validateLogin(username, password);
        if (user != null) {
            tvError.setVisibility(android.view.View.GONE);
            spm.setCurrentUser(username);
            if (cbRemember.isChecked()) spm.setSavedUser(username);
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            showError("Username atau password salah!");
            shakeForm();
        }
    }

    private void showError(String msg) {
        tvError.setText(msg);
        tvError.setVisibility(android.view.View.VISIBLE);
    }


    private void shakeForm() {

        ObjectAnimator animUsername = ObjectAnimator.ofFloat(
                etUsername, "translationX",
                0f, -20f, 20f, -14f, 14f, -8f, 8f, 0f);
        animUsername.setDuration(450);
        animUsername.setInterpolator(new CycleInterpolator(1f));
        animUsername.start();


        ObjectAnimator animPassword = ObjectAnimator.ofFloat(
                etPassword, "translationX",
                0f, -20f, 20f, -14f, 14f, -8f, 8f, 0f);
        animPassword.setDuration(450);
        animPassword.setInterpolator(new CycleInterpolator(1f));
        animPassword.start();
    }
}