package com.example.phoneauth;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;

public class Notify extends Application {
    public  static final String CHENNEL_ID="chennel_id_1";
    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChennel();
    }
    public void createNotificationChennel()
    {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            NotificationChannel channel=new NotificationChannel(
                    CHENNEL_ID,"chennel 1", NotificationManager.IMPORTANCE_HIGH
            );
            channel.setDescription("Notification chennel1");

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
