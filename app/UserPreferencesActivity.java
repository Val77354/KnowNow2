package com.example.knownow;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class UserPreferencesActivity extends AppCompatActivity {

    private Button sportsButton, globalNewsButton, scienceButton, entertainmentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_preferences);

        // Initialize the buttons
        sportsButton = findViewById(R.id.sportsButton);
        globalNewsButton = findViewById(R.id.globalNewsButton);
        scienceButton = findViewById(R.id.scienceButton);
        entertainmentButton = findViewById(R.id.entertainmentButton);

        // OnClickListener for each preference button
        sportsButton.setOnClickListener(v -> togglePreference(sportsButton));
        globalNewsButton.setOnClickListener(v -> togglePreference(globalNewsButton));
        scienceButton.setOnClickListener(v -> togglePreference(scienceButton));
        entertainmentButton.setOnClickListener(v -> togglePreference(entertainmentButton));
    }

    // Method to toggle button color and user preference
    private void togglePreference(Button button) {
        if (button.getCurrentTextColor() == getResources().getColor(android.R.color.black)) {
            // Change to green color
            button.setTextColor(getResources().getColor(android.R.color.holo_green_light));
            Toast.makeText(this, button.getText() + " selected", Toast.LENGTH_SHORT).show();
        } else {
            // Revert to black color
            button.setTextColor(getResources().getColor(android.R.color.black));
            Toast.makeText(this, button.getText() + " deselected", Toast.LENGTH_SHORT).show();
        }
    }
}
