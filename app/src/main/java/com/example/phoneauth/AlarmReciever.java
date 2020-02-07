package com.example.phoneauth;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.SystemClock;
import android.provider.Settings;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


public class AlarmReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {



           intent.putExtra("message","You are Successful Finished notification");
           NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "chennel_id_1");
           builder.setSmallIcon(R.drawable.ic_sms);
           builder.setContentTitle("Congratulations");
           builder.setContentText("You are winning 10Lac rupees");
           //builder.setPriority(NotificationCompat.PRIORITY_HIGH);
           builder.setAutoCancel(true);
           builder.setPriority(NotificationCompat.PRIORITY_HIGH);
           // Set the intent that will fire when the user taps the notification

           Intent intent2 = new Intent(context, Notifications.class);
           //intent2.putExtra("message","You are Successful Finished notification");
           PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, intent2, 0);
           builder.setContentIntent(pendingIntent);

           NotificationManagerCompat notificationManager=NotificationManagerCompat.from(context);
           //notificationManager.notify(1, builder.build());

        for (int i = 0; i <3 ; i++) {
            notificationManager.notify(1, builder.build());
            SystemClock.sleep(25000);
        }



//        MediaPlayer mediaPlayer=MediaPlayer.create(context,Settings.System.DEFAULT_RINGTONE_URI);
//        mediaPlayer.start();
    }
}
