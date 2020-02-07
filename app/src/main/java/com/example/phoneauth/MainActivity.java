package com.example.phoneauth;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.telephony.*;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class MainActivity extends AppCompatActivity {
    public long doubleClick;
    private Toast backToast;
    DrawerLayout myDrower;
    ActionBarDrawerToggle myDroweTogger;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDrower=(DrawerLayout)findViewById(R.id.myDrawer);
        navigationView=(NavigationView)findViewById(R.id.nav);
        myDroweTogger=new ActionBarDrawerToggle(this,myDrower,R.string.open,R.string.close);

        myDrower.addDrawerListener(myDroweTogger);
        myDroweTogger.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Button btn_main=(Button)findViewById(R.id.btn_main);
        btn_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,SecondActivity.class);
                intent.putExtra("message","Hello you are in second activity !");
                startActivity(intent);
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId()==R.id.feedback)
                {
                    Intent intent=new Intent(MainActivity.this,RatingApp.class);
                    startActivity(intent);
//                    LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//                    PopupWindow pw = new PopupWindow(inflater.inflate(R.layout.rating_activity, null, false),100,100, true);

                    //pw.showAtLocation(findViewById(R.id.myDrawer), Gravity.CENTER, 0, 0);
                }
                return true;
            }
        });

        BottomNavigationView bottomNavigationView=(BottomNavigationView)findViewById(R.id.bottom_navbar);
        bottomNavigationView.setOnNavigationItemSelectedListener(bottomItem);
    }

    BottomNavigationView.OnNavigationItemSelectedListener bottomItem =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedfragment=null;
                    switch (menuItem.getItemId())
                    {
                        case R.id.home_fragment:
                            selectedfragment=new HomeFragment();
                            //Toast.makeText(getApplicationContext(),"Home Fragment",Toast.LENGTH_LONG).show();
                            break;
                        case R.id.profile_fragment:
                            selectedfragment=new ProfileFragment();
                            break;
                        case R.id.setting_fragment:
                            selectedfragment=new SettingFragment();
                            break;
                        default:
                            Toast.makeText(getApplicationContext(),"You are not enter valid list",Toast.LENGTH_LONG).show();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedfragment).commit();
                    return true;
                }
            };

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(myDroweTogger.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
//        final AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);
//        alertDialog.setTitle("Confirmation");
//        alertDialog.setMessage("Are you want to exit this app");
//        alertDialog.setPositiveButton("YES",new DialogInterface.OnClickListener(){
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                finish();
//            }
//        });
//        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.cancel();
//            }
//        });
//        AlertDialog alert=alertDialog.create();
//        alert.show();

        if(doubleClick+2000>System.currentTimeMillis())
        {
            backToast.cancel();
            super.onBackPressed();
        }
        else{
            backToast=Toast.makeText(getApplicationContext(),"Please Press again",Toast.LENGTH_SHORT);
            backToast.show();
        }
        doubleClick=System.currentTimeMillis();
    }
}

