package dev.example.pas_syahwa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Objects.requireNonNull(getSupportActionBar()).hide();

        EditText name = findViewById(R.id.sign_up_edit_text_name);
        EditText email = findViewById(R.id.sign_up_edit_text_email);
        EditText password = findViewById(R.id.sign_up_edit_text_password);


        Button btnSignUp = findViewById(R.id.btn_signup);
        TextView Login = findViewById(R.id.txtLogin);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText() == null || name.getText().toString().isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Name can't be empty!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (email.getText() == null || email.getText().toString().isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Email can't be empty!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.getText() == null || password.getText().toString().isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Password can't be empty!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(SignUpActivity.this, "Signed up!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });



        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            }
        });
    }
}