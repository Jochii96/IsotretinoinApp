package com.example.isotretinoin2;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ListSave {
/** Utility class called ListSave. This class provides methods to write and read an ArrayList of strings to/from storage.*/
    public static final String FILENAME = "appointmentsNotes.dat"; // Name of the file used for data storage

    public static void writeData(ArrayList<String> item, Context context) {
        // Method to write data (ArrayList of strings) to storage
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            ObjectOutputStream oas = new ObjectOutputStream(fos);
            oas.writeObject(item); // Write the ArrayList to the file
            oas.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> readData(Context context) {
        // Method to read data (ArrayList of strings) from storage
        ArrayList<String> itemlist = null;

        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            ObjectInputStream ois = new ObjectInputStream(fis);
            itemlist = (ArrayList<String>) ois.readObject(); // Read the ArrayList from the file
        } catch (FileNotFoundException e) {
            itemlist = new ArrayList<>(); // If the file is not found, initialize an empty ArrayList
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return itemlist; // Return the read ArrayList (or the empty one if an exception occurred)
    }
}