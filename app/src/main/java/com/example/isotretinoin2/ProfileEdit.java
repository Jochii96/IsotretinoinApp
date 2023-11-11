package com.example.isotretinoin2;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.timepicker.MaterialTimePicker;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Locale;


public class ProfileEdit extends AppCompatActivity {

/** ProfileEdit class, which represents the profile edit screen, accessible from the menu*/

    Button confirmed, timeSet;
    EditText name, dose, timing1, timing2;
    int hour, minute;

    private MaterialTimePicker picker;
    private Calendar calendar;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        // Find views and buttons in the layout
        timeSet = findViewById(R.id.buttonSet);
        confirmed = findViewById(R.id.buttonConfirm);
        name = (EditText) findViewById(R.id.userName);
        dose = (EditText) findViewById(R.id.userDose);
        timing1 = (EditText) findViewById(R.id.time1);
        timing2 = (EditText) findViewById(R.id.time2);

        // Read and set the values from the stored files to the corresponding EditText fields
        String content = readFromFile("userName.txt");
        name.setText(content);

        String content2 = readFromFile("userDose.txt");
        dose.setText(content2);

        String content3 = readFromFile("userTime1.txt");
        timing1.setText(content3);

        String content4 = readFromFile("userTime2.txt");
        timing2.setText(content4);


        confirmed.setOnClickListener(v -> {
            // Retrieve the values from the EditText fields
            String content5 = name.getText().toString();
            writeToFile("userName.txt", content5); // Write the value to the corresponding file

            String content6 = dose.getText().toString();
            writeToFile("userDose.txt", content6);

            String content7 = timing1.getText().toString();
            writeToFile("userTime1.txt", content7);

            String content8 = timing2.getText().toString();
            writeToFile("userTime2.txt", content8);

            // Start the MainScreen activity
            Intent intent = new Intent(ProfileEdit.this, MainScreen.class);
            startActivity(intent);
        });


    }

    public void popTimePicker(View view) {
        // Method to open a time picker dialog for the first timing EditText
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                hour = selectedHour;
                minute = selectedMinute;
                timing1.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));
                // Set the selected hour and minute to the respective variables
                // Update the text of the timing1 EditText to display the selected time in the format HH:MM
            }
        };

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, hour, minute, true);
        // Create a new TimePickerDialog instance with the current context (this)
        timePickerDialog.setTitle("Select Time");
        // Set the title of the time picker dialog to "Select Time"
        timePickerDialog.show();
        // Show the time picker dialog
    }

    public void popTimePicker2(View view) {
        // Method to open a time picker dialog for the first timing EditText
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                hour = selectedHour;
                minute = selectedMinute;
                timing1.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));
                // Set the selected hour and minute to the respective variables
                // Update the text of the timing1 EditText to display the selected time in the format HH:MM
            }
        };

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, hour, minute, true);
        // Create a new TimePickerDialog instance with the current context (this)
        timePickerDialog.setTitle("Select Time");
        // Set the title of the time picker dialog to "Select Time"
        timePickerDialog.show();
        // Show the time picker dialog
    }


    public void writeToFile(String fileName, String content) {
        // Method to write data to a file

        File path = getApplicationContext().getFilesDir();
        // Get the application's files directory using getFilesDir()
        try {
            FileOutputStream writer = new FileOutputStream(new File(path, fileName));
            // Create a FileOutputStream to write data to the specified file
            writer.write(content.getBytes());
            // Convert the content string to bytes and write it to the file using the writer
            writer.close();
            // Close the writer to release resources and ensure data is written successfully

        } catch (Exception e) {
            e.printStackTrace();
            // If an exception occurs during the file write operation, print the stack trace
        }
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
}