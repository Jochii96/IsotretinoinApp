package com.example.isotretinoin2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
/**  MainActivity class, which serves as the entry point of the application. */
    Button disclaimer; // Button for starting the disclaimer activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        disclaimer = findViewById(R.id.buttonStart);

        // Set click listener for the disclaimer button
        disclaimer.setOnClickListener(v -> {
            // Navigate to the Disclaimer activity
            Intent intent = new Intent(MainActivity.this, Disclaimer.class);
            startActivity(intent);
        });
    }
}