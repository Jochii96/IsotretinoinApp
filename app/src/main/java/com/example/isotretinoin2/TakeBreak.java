package com.example.isotretinoin2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class TakeBreak extends AppCompatActivity {
    /**
     The TakeBreak class is an activity that extends AppCompatActivity, representing a screen where users can take a break.*/

    Button approve;
    Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_break);

        // Find the cancel button in the layout by its ID
        cancel = findViewById(R.id.buttonBreakCancel);

        // Set a click listener for the cancel button
        cancel.setOnClickListener(v -> {
            // Create an intent to start the MainScreen activity
            Intent intent = new Intent(TakeBreak.this, MainScreen.class);
            startActivity(intent);
        });
    }
}