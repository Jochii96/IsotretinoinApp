package com.example.isotretinoin2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class CalculateDose extends AppCompatActivity {
/** CalculateDose class, an activity that calculates and displays the daily dose and cumulative dose based on the user's weight.*/
    Button okay, calculate; // Buttons for user interaction
    EditText weight; // EditText for weight input
    TextView doseDaily, doseCumulative; // TextViews to display calculated doses

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_dose);

        // Initialize views
        okay = findViewById(R.id.buttonOk);
        calculate = findViewById(R.id.buttonCalculation);
        weight = findViewById(R.id.weightInput);
        doseDaily = findViewById(R.id.maxDose);
        doseCumulative = findViewById(R.id.maxDose2);

        // Load and display previously saved daily dose from storage
        String content4 = readFromFile("userDailyDose.txt");
        doseDaily.setText(content4);

        // Load and display previously saved cumulative dose from storage
        String content5 = readFromFile("userCumulativeDose.txt");
        doseCumulative.setText(content5);

        // Set click listener for the calculate button
        calculate.setOnClickListener(v -> {
            // Get weight input from EditText
            String content = weight.getText().toString();
            // Save weight input to storage
            writeToFile("userWeight.txt", content);

            // Calculate daily dose and display it
            String content2 = String.valueOf(calculateDose());
            doseDaily.setText(content2 + " mg");
            // Save calculated daily dose to storage
            writeToFile("userDailyDose.txt", content2);

            // Calculate cumulative dose and display it
            String content3 = String.valueOf(calculateDose2());
            doseCumulative.setText(content3 + " mg");
            // Save calculated cumulative dose to storage
            writeToFile("userCumulativeDose.txt", content3);

        });
        // Set click listener for the okay button
        okay.setOnClickListener(v -> {
            // Navigate back to the MainScreen activity
            Intent intent = new Intent(CalculateDose.this, MainScreen.class);
            startActivity(intent);
        });

    }

    private int calculateDose() {
        // Read weight input from storage
        String importKg = readFromFile("userWeight.txt");
        // Convert weight to integer and return it
        int calDose = Integer.parseInt(importKg);
        return calDose;

    }

    private int calculateDose2() {

        // Read weight input from storage
        String importKg = readFromFile("userWeight.txt");
        // Convert weight to integer
        int calDose = Integer.parseInt(importKg);
        // Calculate and return cumulative dose
        int cumulativeDose = calDose * 150;
        return cumulativeDose;

    }

    public void writeToFile(String fileName, String content) {
        // Get the application's files directory
        File path = getApplicationContext().getFilesDir();
        try {
            // Create a FileOutputStream and write content to the specified file
            FileOutputStream writer = new FileOutputStream(new File(path, fileName));
            writer.write(content.getBytes());
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public String readFromFile(String fileName) {
        // Get the application's files directory
        File path = getApplicationContext().getFilesDir();
        // Create a File object for the specified file
        File readFrom = new File(path, fileName);
        byte[] content = new byte[(int) readFrom.length()];
        try {
            // Read the contents of the file into a byte array
            FileInputStream stream = new FileInputStream(readFrom);
            stream.read(content);
            // Convert the byte array to a string and return it
            return new String(content);
        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }

    }

}