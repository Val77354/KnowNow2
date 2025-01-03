package com.example.knownow;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    EditText signupEmail, signupPassword, signupConfirmPassword;
    Button signUpButton;
    TextView emailError, passwordConfirmError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signupEmail = findViewById(R.id.signupEmail);
        signupPassword = findViewById(R.id.signupPassword);
        signupConfirmPassword = findViewById(R.id.signupConfirmPassword);
        signUpButton = findViewById(R.id.signUpButton);
        emailError = findViewById(R.id.emailError);
        passwordConfirmError = findViewById(R.id.passwordConfirmError);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = signupEmail.getText().toString();
                String password = signupPassword.getText().toString();
                String confirmPassword = signupConfirmPassword.getText().toString();

                // Validate email input
                if (TextUtils.isEmpty(email)) {
                    emailError.setVisibility(View.VISIBLE);
                    emailError.setText("Email is required.");
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    emailError.setVisibility(View.VISIBLE);
                    emailError.setText("Please enter a valid email.");
                } else {
                    emailError.setVisibility(View.GONE);

                    // Validate password input
                    if (TextUtils.isEmpty(password)) {
                        Toast.makeText(SignUpActivity.this, "Password is required.", Toast.LENGTH_SHORT).show();
                    } else if (password.length() < 6) {
                        Toast.makeText(SignUpActivity.this, "Password must be at least 6 characters long.", Toast.LENGTH_SHORT).show();
                    } else if (!password.equals(confirmPassword)) {
                        passwordConfirmError.setVisibility(View.VISIBLE);
                        passwordConfirmError.setText("Passwords do not match.");
                        signupConfirmPassword.setText("");
                    } else {
                        passwordConfirmError.setVisibility(View.GONE);

                        // After successful sign-up, navigate to the UserPreferencesActivity
                        Intent intent = new Intent(SignUpActivity.this, UserPreferencesActivity.class);
                        intent.putExtra("email", email);  // Pass email to the preferences activity
                        startActivity(intent);
                        finish(); // Close this activity
                    }
                }
            }
        });
    }
}
