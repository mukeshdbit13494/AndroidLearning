package com.example.phoneauth;

import android.app.ActionBar;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import static com.example.phoneauth.Notify.CHENNEL_ID;

public class SecondActivity extends AppCompatActivity {

    NotificationManagerCompat notificationManager;
    NotificationCompat.Builder builder;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        notificationManager=NotificationManagerCompat.from(this);

        int h= Calendar.getInstance().getTime().getHours();
        int m=Calendar.getInstance().getTime().getMinutes();
        if(h>12)
        {
            h-=12;
        }
        if(h==3 && m==33)
        {
            notification();
        }
//        final Intent intent=getIntent();
//        Toast.makeText(this,intent.getStringExtra("message"),Toast.LENGTH_LONG).show();
        Button btn_close=(Button)findViewById(R.id.btn_close);
        Button btn_user=(Button)findViewById(R.id.btn_user);
        Button btn_open=(Button)findViewById(R.id.btn_open);
        Button btn_list=(Button)findViewById(R.id.btn_list);
        Button btn_others=(Button)findViewById(R.id.btn_other);
        Button btn_user_read=(Button)findViewById(R.id.btn_user_read);
        Button btn_map=(Button)findViewById(R.id.btn_map);
        Button btn_speech=(Button)findViewById(R.id.speech);
        Button btn_scanner=(Button)findViewById(R.id.btn_scanner);
        Button btn_other2=(Button)findViewById(R.id.other2);

        btn_other2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this,ActivityItem.class));
            }
        });

        btn_scanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this,ActivityScanner.class));
            }
        });

        btn_speech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SecondActivity.this,ActivitySpeech.class);
                startActivity(intent);
            }
        });

        btn_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SecondActivity.this,MapsActivity.class);
                startActivity(intent);
            }
        });

        btn_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SecondActivity.this,MainPage.class);
                startActivity(intent);
            }
        });
        btn_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SecondActivity.this, UserInsertData.class);
                startActivity(intent);
            }
        });
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(SecondActivity.this,EmailSignin.class);
                startActivity(intent);
            }
        });
        btn_user_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SecondActivity.this,UserDataRead.class);
                startActivity(intent);
            }
        });
        btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SecondActivity.this,ThirdActivity.class);
                startActivity(intent);
            }
        });
        btn_others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(SecondActivity.this,Notifications.class);
//                intent.putExtra("message", "This is a notification message");
//                startActivity(intent);

               // notification();

                confirmation();

            }
        });
    }

    public void notification()
    {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHENNEL_ID);
        builder.setSmallIcon(R.drawable.ic_sms);
        builder.setContentTitle("Congratulations");
        builder.setContentText("You are winning 10Lac rupees");
        builder.setPriority(NotificationCompat.PRIORITY_HIGH);
        builder.setAutoCancel(true);
        builder.setCategory(NotificationCompat.CATEGORY_MESSAGE);// Set the intent that will fire when the user taps the notification

        Intent intent = new Intent(this, Notifications.class);
        intent.putExtra("message","You are Successful Finished notification");
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        Notification builder1 = builder.build();
        notificationManager.notify(1, builder1);
        Toast.makeText(this,"Notification send",Toast.LENGTH_LONG).show();
    }
    public void confirmation(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure You wanted to make decision");
                alertDialogBuilder.setPositiveButton("yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                remainder(System.currentTimeMillis()+5000);
                            }
                        });

        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    public void remainder(long time)
    {
        Intent intent=new Intent(this, AlarmReciever.class);
//        intent.putExtra("notice","Congratulation Mr. you are win 10 Lakh vash price");

        PendingIntent pendingIntent= PendingIntent.getBroadcast(this,1,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,time, AlarmManager.INTERVAL_DAY,pendingIntent);

        Toast.makeText(getApplicationContext(),"Done !",Toast.LENGTH_LONG).show();
    }


}
