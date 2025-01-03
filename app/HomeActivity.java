package com.example.knownow;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize Views
        TextView welcomeMessage = findViewById(R.id.welcomeMessage);
        Button logoutButton = findViewById(R.id.logoutButton);

        // Set a welcome message
        String username = getIntent().getStringExtra("username"); // Pass username from MainActivity
        welcomeMessage.setText("Welcome, " + (username != null ? username : "User") + "!");

        // Logout Button Click Listener
        logoutButton.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut(); // Log out from Firebase if using it
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
