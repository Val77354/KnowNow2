package com.example.knownow;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserPreferencesActivity extends AppCompatActivity {

    private Button sportsButton, globalNewsButton, scienceButton, entertainmentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_preferences);

        sportsButton = findViewById(R.id.sportsButton);
        globalNewsButton = findViewById(R.id.globalNewsButton);
        scienceButton = findViewById(R.id.scienceButton);
        entertainmentButton = findViewById(R.id.entertainmentButton);

        globalNewsButton.setOnClickListener(v -> fetchGlobalNews());
    }

    private void fetchGlobalNews() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NewsApi newsApi = retrofit.create(NewsApi.class);

        // Replace your API key here
        String apiKey = "b104784f43a84ef1a5589ed518cfc5b8";
        Call<NewsResponse> call = newsApi.getGlobalNews("us", apiKey); // Use "us" for global news
        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Intent intent = new Intent(UserPreferencesActivity.this, NewsActivity.class);
                    intent.putParcelableArrayListExtra("newsArticles", new ArrayList<>(response.body().getArticles()));
                    startActivity(intent);
                } else {
                    Toast.makeText(UserPreferencesActivity.this, "Failed to fetch news", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                Toast.makeText(UserPreferencesActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
