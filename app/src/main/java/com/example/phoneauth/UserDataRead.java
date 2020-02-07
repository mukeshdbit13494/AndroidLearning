package com.example.phoneauth;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class UserDataRead extends AppCompatActivity {
    public  static final String USER_ID="userId";
    public  static final String USER_NAME="userName";
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    List<Users> usersList;
    TextView textView;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_data);

        recyclerView=(RecyclerView)findViewById(R.id.user_recycleView);
        databaseReference= FirebaseDatabase.getInstance().getReference("User");

        usersList=new ArrayList<>();

        //textView=(TextView)findViewById(R.id.user_recycleView1);

//        Users[] users=new Users[]{new Users("25","Mukesh singh","9557707079","mukesh@gmail.com","male","","","")};
//        Users users1=new Users("26","sant singh","9557707070","mukesh@gmail.com","male","","","");
//        usersList.add(users);
//        usersList.add(users1);

         progressDialog=new ProgressDialog(this);
        // progressDialog.setTitle("Please wait");
         progressDialog.setProgress(0);
         progressDialog.setMax(100);
        // progressDialog.setMessage("Loding...");
        progressDialog.getWindow().setGravity(Gravity.CENTER_VERTICAL);
        progressDialog.getWindow().setLayout(200,250);

         new MyTask().execute();
    }
    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                usersList.clear();
                for(DataSnapshot usersSnapshot : dataSnapshot.getChildren())
                {
                    Users users=usersSnapshot.getValue(Users.class);
                    usersList.add(users);
                }
                //textView.setText(usersList.get(2).getName()+"\n"+usersList.get(2).getNumber()+"\n"+usersList.get(2).getEmail());

                UserListAdapter adapter=new UserListAdapter(usersList);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(adapter);
                progressDialog.hide();
                //progressDialog.cancel();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public class MyTask extends AsyncTask<Void, Void, Void> {
        public void onPreExecute() {
            progressDialog.show();
        }
        public Void doInBackground(Void... unused) {
            return null;
        }
    }
}
