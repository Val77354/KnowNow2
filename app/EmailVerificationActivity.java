package com.example.knownow;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class EmailVerificationActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private TextView verificationMessage;
    private Button resendButton, checkVerificationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_verification);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        verificationMessage = findViewById(R.id.verificationMessage);
        resendButton = findViewById(R.id.resendVerificationButton);
        checkVerificationButton = findViewById(R.id.checkVerificationButton);

        if (currentUser == null) {
            Toast.makeText(this, "No user is currently signed in.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        String email = currentUser.getEmail();
        verificationMessage.setText("A verification email has been sent to " + email + ". Please check your inbox.");

        resendButton.setOnClickListener(v -> {
            currentUser.sendEmailVerification()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(this, "Verification email resent.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(this, "Failed to resend verification email.", Toast.LENGTH_SHORT).show();
                        }
                    });
        });

        checkVerificationButton.setOnClickListener(v -> {
            currentUser.reload()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            if (currentUser.isEmailVerified()) {
                                Toast.makeText(this, "Email verified!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(EmailVerificationActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(this, "Email is not verified yet. Please check your inbox.", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(this, "Failed to reload user.", Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }
}
