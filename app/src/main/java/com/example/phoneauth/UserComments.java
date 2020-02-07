package com.example.phoneauth;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class UserComments extends AppCompatActivity {
    TextView tv_UserName;
    EditText et_comments;
    Button btn_comments;
    DatabaseReference databaseCommentsReference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_comments);

        tv_UserName = (TextView) findViewById(R.id.tv_hearder_name);
        et_comments = (EditText) findViewById(R.id.et_comments);
        btn_comments = (Button) findViewById(R.id.btn_comments);

        Intent intent = getIntent();

        tv_UserName.setText("Hello "+intent.getStringExtra(UserDataRead.USER_NAME));
        String id = intent.getStringExtra(UserDataRead.USER_ID);

        databaseCommentsReference = FirebaseDatabase.getInstance().getReference("Comments").child(id);

        btn_comments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addComments();
            }
        });

    }
    public void addComments(){
        String description=et_comments.getText().toString();
        if(!description.isEmpty())
        {
            String id=databaseCommentsReference.push().getKey();
            CommentList commentList=new CommentList(id,description);
            databaseCommentsReference.child(id).setValue(commentList);
            Toast.makeText(getApplicationContext(),"Comment Successful saved",Toast.LENGTH_LONG).show();
        }
        else {
            et_comments.setText("*Please Write comments ");
            et_comments.setTextColor(Color.RED);
            Toast.makeText(getApplicationContext(),"Opps an Error!",Toast.LENGTH_LONG).show();
        }
    }

}
