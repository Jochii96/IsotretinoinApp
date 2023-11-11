package com.example.isotretinoin2;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.timepicker.MaterialTimePicker;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Locale;

public class Profile extends AppCompatActivity {
    /** Profile class, which represents the profile setup screen of the application. Appears only once on the first run of app. */

    Button confirmed, timeSet;
    EditText name, dose, timing1, timing2;
    int hour, minute;
    SharedPreferences sharedPreferences;
    private MaterialTimePicker picker;
    private Calendar calendar;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Check if it's the first run of the application
        Boolean isNotFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("isFirstRun", false);

        if (isNotFirstRun) {
            //show start activity

            // If it's not the first run, show the MainScreen activity
            startActivity(new Intent(Profile.this, MainScreen.class));

        }
        // Mark the application as having run for the first time
        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putBoolean("isFirstRun", true).commit();

        // Find views and buttons in the layout
        timeSet = findViewById(R.id.buttonSet);
        confirmed = findViewById(R.id.buttonConfirm);
        name = (EditText) findViewById(R.id.userName);
        dose = (EditText) findViewById(R.id.userDose);
        timing1 = (EditText) findViewById(R.id.time1);
        timing2 = (EditText) findViewById(R.id.time2);


        // Click listener for the "Confirm" button
        confirmed.setOnClickListener(v -> {

            // Get the input values from the EditText fields
            String content = name.getText().toString();
            writeToFile("userName.txt", content);

            String content2 = dose.getText().toString();
            writeToFile("userDose.txt", content2);

            String content3 = timing1.getText().toString();
            writeToFile("userTime1.txt", content3);

            String content4 = timing2.getText().toString();
            writeToFile("userTime2.txt", content4);

            // Create an intent to navigate to the MainScreen activity
            Intent intent = new Intent(Profile.this, MainScreen.class);
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
        // Method to open a time picker dialog for the second timing EditText
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                hour = selectedHour;
                minute = selectedMinute;
                timing2.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));
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

    private void createNotificationChannel() {
        // Method to create a notification channel for the Alarm Manager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Check if the device's Android version is compatible with creating notification channels

            CharSequence name = "isoReminderReminderChannel";
            String description = "Channel For Alarm Manager";
            // Set the name, description, and importance level for the channel

            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("isoReminder", name, importance);
            channel.setDescription(description);
            // Create a new notification channel with the specified name, importance, and description

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
            // Get the NotificationManager and create the notification channel

        }
    }

    private void setAlarm() {
        // Method to set an alarm using the AlarmManager

        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        // Get the AlarmManager service

        Intent intent = new Intent(this, ReminderReceiver.class);
        // Create an intent for the ReminderReceiver class

        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
        // Create a pending intent for the broadcast receiver

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        // Set a repeating alarm that triggers at the specified calendar time, with an interval of one day

        Toast.makeText(this, "Alarm set Successfully", Toast.LENGTH_SHORT).show();
        // Show a toast message indicating that the alarm has been set

    }

}
