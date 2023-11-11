package com.example.isotretinoin2;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Appointments extends AppCompatActivity {
    /**
     The Appointments class is an activity that allows users to manage a list of appointments.
     */


    ListView listView; // ListView to display the appointments
    EditText item; // EditText for user input
    Button add; // Button to add appointments
    ArrayList<String> itemlist = new ArrayList<>(); // ArrayList to store the appointments
    ArrayAdapter<String> arrayAdapter; // ArrayAdapter to bind the ArrayList to the ListView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);

        // Initialize views
        item = findViewById(R.id.inputNotes);
        add = findViewById(R.id.buttonAddNote);
        listView = findViewById(R.id.appointmentsList);

        // Load previously saved appointments from storage
        itemlist = ListSave.readData(this);

        // Create ArrayAdapter and bind it to the ListView
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, itemlist);
        listView.setAdapter(arrayAdapter);

        // Set click listener for the add button
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the appointment text from the EditText
                String itemName = item.getText().toString();
                // Add the appointment to the list
                itemlist.add(itemName);
                // Clear the EditText
                item.setText("");
                // Save the updated list to storage
                ListSave.writeData(itemlist, getApplicationContext());
                // Notify the ArrayAdapter that the data set has changed
                arrayAdapter.notifyDataSetChanged();
            }
        });

        // Set click listener for the appointments in the ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                // Create an AlertDialog to confirm deletion
                AlertDialog.Builder alert = new AlertDialog.Builder(Appointments.this);
                alert.setTitle("Delete");
                alert.setMessage("Do you want to delete this item from list?");
                alert.setCancelable(false);
                // Set negative button click listener (No)
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Cancel the dialog
                        dialog.cancel();
                    }
                });
                // Set positive button click listener (Yes)
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Remove the appointment from the list
                        itemlist.remove(position);
                        // Notify the ArrayAdapter that the data set has changed
                        arrayAdapter.notifyDataSetChanged();
                        // Save the updated list to storage
                        ListSave.writeData(itemlist, getApplicationContext());
                    }
                });
                // Create and show the AlertDialog
                AlertDialog alertDialog = alert.create();
                alertDialog.show();
            }
        });
    }

}