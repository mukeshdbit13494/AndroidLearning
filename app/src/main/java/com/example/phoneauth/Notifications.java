package com.example.phoneauth;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.sql.Time;
import java.util.Calendar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Notifications extends AppCompatActivity {
    TextView tv_message;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_xml);
        Intent intent=getIntent();
        String message=intent.getStringExtra("message");


        int h=Calendar.getInstance().getTime().getHours();
        int m=Calendar.getInstance().getTime().getMinutes();
        if(h>12)
        {
            h-=12;
        }
        tv_message=(TextView)findViewById(R.id.notification_message);
        tv_message.setText(h+":"+m+"\n"+message);
    }
}
