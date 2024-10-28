package uts.elcha.tasks.model;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import uts.elcha.tasks.R;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(v -> {
            String username = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (username.equals("admin") && password.equals("password")) {

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Tutup LoginActivity agar tidak bisa kembali
            } else {
                Toast.makeText(LoginActivity.this, "Username atau Password salah", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
