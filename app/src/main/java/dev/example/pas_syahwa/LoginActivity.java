package dev.example.pas_syahwa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Objects.requireNonNull(getSupportActionBar()).hide();

        EditText email = findViewById(R.id.login_edit_text_email);
        EditText password = findViewById(R.id.login_edit_text_password);

        TextView loginButton = findViewById(R.id.login_button);
        TextView signUp = findViewById(R.id.login_sign_up);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (email.getText() == null || email.getText().toString().isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Email can't be empty!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.getText() == null || password.getText().toString().isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Password can't be empty!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(LoginActivity.this, "Logged in!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });
    }
}