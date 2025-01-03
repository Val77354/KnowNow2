package com.example.knownow;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class NewsFeedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);

        TextView preferencesTextView = findViewById(R.id.preferencesTextView);

        // Retrieve selected preferences from the intent
        List<String> preferences = getIntent().getStringArrayListExtra("selectedPreferences");

        if (preferences != null && !preferences.isEmpty()) {
            // Display the selected preferences in a TextView
            preferencesTextView.setText("Your Preferences: " + preferences.toString());
        } else {
            preferencesTextView.setText("No preferences selected.");
        }
    }
}
