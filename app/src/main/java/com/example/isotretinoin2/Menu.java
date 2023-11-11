package com.example.isotretinoin2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Menu extends AppCompatActivity {
/** Menu class, which represents the menu screen of the application. User can navigate through the app options and features*/
    Button home, profile2, appointments, calculatedose, takeabreak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Initialize buttons by finding their corresponding views in the layout
        home = findViewById(R.id.buttonHome);
        profile2 = findViewById(R.id.buttonProfile2);
        appointments = findViewById(R.id.buttonAppointments);
        calculatedose = findViewById(R.id.buttonCalculate);
        takeabreak = findViewById(R.id.buttonBreak);

        // Set click listeners for the buttons

        // Click listener for the "Home" button
        home.setOnClickListener(v -> {
            // Create an intent to navigate to the MainScreen activity
            Intent intent = new Intent(Menu.this, MainScreen.class);
            startActivity(intent);
        });
        // Click listener for the "Profile" button
        profile2.setOnClickListener(v -> {
            // Create an intent to navigate to the ProfileEdit activity
            Intent intent = new Intent(Menu.this, ProfileEdit.class);
            startActivity(intent);
        });
        // Click listener for the "Appointments" button
        appointments.setOnClickListener(v -> {
            // Create an intent to navigate to the Appointments activity
            Intent intent = new Intent(Menu.this, Appointments.class);
            startActivity(intent);
        });
        // Click listener for the "Calculate Dose" button
        calculatedose.setOnClickListener(v -> {
            // Create an intent to navigate to the CalculateDose activity
            Intent intent = new Intent(Menu.this, CalculateDose.class);
            startActivity(intent);
        });
        // Click listener for the "Take a Break" button
        takeabreak.setOnClickListener(v -> {
            // Create an intent to navigate to the TakeBreak activity
            Intent intent = new Intent(Menu.this, TakeBreak.class);
            startActivity(intent);
        });


    }
}