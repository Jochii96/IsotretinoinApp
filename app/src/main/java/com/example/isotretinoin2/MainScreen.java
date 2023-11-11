package com.example.isotretinoin2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.util.Calendar;

public class MainScreen extends AppCompatActivity {
/**  MainScreen class, which represents the main screen of the application. Where user can find all of the information on their treatment's progress */
    Button menu, tipsTricks; // Buttons for menu and tips
    TextView impName, calDays, calMonths, userDailyDose, userCumulativeDose; // TextViews for displaying data

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        // Initialize views
        menu = findViewById(R.id.buttonMenu);
        tipsTricks = findViewById(R.id.buttonTips);
        calMonths = findViewById(R.id.importMonths);
        calDays = findViewById(R.id.importDays);
        userDailyDose = findViewById(R.id.importDailyDose);
        impName = findViewById(R.id.importName);
        userCumulativeDose = findViewById(R.id.importCumulativeDose);

        // Set data from files or calculated values to the corresponding TextViews
        String content = readFromFile("userName.txt");
        impName.setText(content + "'s");

        String content2 = readFromFile("userDose.txt");
        userDailyDose.setText(content2 + "mg");

        String content3 = "1";   // Needs to call a function that counts days
        calDays.setText(content3);

        String content4 = "0";   // Needs to call a function that counts months
        calMonths.setText(content4);

        String content5 = String.valueOf(calculateCumulative());
        userCumulativeDose.setText(content5 + "mg");

        // Set click listener for the tips button
        tipsTricks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });

        // Set click listener for the menu button
        menu.setOnClickListener(v -> {
            Intent intent = new Intent(MainScreen.this, Menu.class);
            // Start the Menu activity
            startActivity(intent);
        });

        // Display current date
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());
        TextView textViewDate = findViewById(R.id.textViewDate);
        textViewDate.setText(currentDate);
    }

    @Override
    public void onBackPressed() { // Empty method to disable the back button
    }

    public void openDialog() {
        // Method to open a tips dialog
        TipsTricks exampleDialog = new TipsTricks();
        exampleDialog.show(getSupportFragmentManager(), "example dialog");
    }

    public String readFromFile(String fileName) {
        // Method to read data from a file and return as a string

        // Get the path to the application's files directory
        File path = getApplicationContext().getFilesDir();

        // Create a File object to read from
        File readFrom = new File(path, fileName);

        // Create a byte array to hold the content of the file
        byte[] content = new byte[(int) readFrom.length()];
        try {
            // Create a FileInputStream to read the file
            FileInputStream stream = new FileInputStream(readFrom);
            // Read the content of the file into the byte array
            stream.read(content);
            // Convert the byte array to a string and return it
            return new String(content);
        } catch (Exception e) {
            // Print the stack trace of the exception
            e.printStackTrace();
            // Return the exception as a string
            return e.toString();
        }

    }

    private int calculateCumulative() {
        // Method to calculate the cumulative dose, unfinished

        String importDose = readFromFile("userDose.txt");
        int dailyDose = Integer.parseInt(importDose);
        return dailyDose;


    }
}