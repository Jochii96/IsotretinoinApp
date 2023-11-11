package com.example.isotretinoin2;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class ReminderReceiver extends BroadcastReceiver {
    /**
     The ReminderReceiver class extends BroadcastReceiver and overrides the onReceive method, which will be called when the broadcast is received.
     */

    @SuppressLint("MissingPermission")
    @Override
    public void onReceive(Context context, Intent intent) {

        // Create an intent to start the ReminderEmpty activity
        Intent i = new Intent(context, MainScreen.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, i, PendingIntent.FLAG_IMMUTABLE);

        // Build the notification using NotificationCompat.Builder
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "isoReminder").setSmallIcon(R.drawable.ic_launcher_background).setContentTitle("Isotretinoin Alarm Manager").setContentText("Take your medication please").setAutoCancel(true).setDefaults(NotificationCompat.DEFAULT_ALL).setPriority(NotificationCompat.PRIORITY_HIGH).setContentIntent(pendingIntent);

        // Notify the notification using NotificationManagerCompat
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(123, builder.build());


    }
}

