package com.example.isotretinoin2;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Disclaimer extends AppCompatActivity {
/** Disclaimer class, an activity that displays a disclaimer to the user and requires their agreement before proceeding.*/
    Button agreed; // Button for user agreement

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disclaimer);

        // Initialize views
        TextView textView = (TextView) findViewById(R.id.textView);
        //Allows scrolling movement in this text field
        textView.setMovementMethod(new ScrollingMovementMethod());

        agreed = findViewById(R.id.buttonAgree);

        // Set click listener for the agreement button
        agreed.setOnClickListener(v -> {
            // Navigate to the Profile activity
            Intent intent = new Intent(Disclaimer.this, Profile.class);
            startActivity(intent);
        });
    }
}