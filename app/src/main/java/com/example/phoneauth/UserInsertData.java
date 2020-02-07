package com.example.phoneauth;

import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class UserInsertData extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Button btn_save;
    TextView tv_header,tv_name,tv_number,tv_email,tv_gender,tv_dob;
    EditText et_name,et_number,et_email,et_dob;
    Spinner sp_goal;
    RadioGroup rg_gender;
    RadioButton rb_gender;
    CheckBox cb_acept;
    DatabaseReference databaseReference;
    String goal=null;
    Context context;
    String item[]={"Set Goal","Goverment Job","Private Job","Sports","Business","Investments","Marketing","Development","Education","Scientist"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_data);
        btn_save=(Button)findViewById(R.id.btn_save);
        et_name=(EditText) findViewById(R.id.et_name);
        et_number=(EditText) findViewById(R.id.et_number);
        et_email=(EditText) findViewById(R.id.et_email);
        et_dob=(EditText) findViewById(R.id.et_dob);
        rg_gender=(RadioGroup)findViewById(R.id.rg_gender);
        //rb_gender=(RadioButton)findViewById(rg_gender.getCheckedRadioButtonId());
        sp_goal = (Spinner) findViewById(R.id.sp_goal);
        cb_acept=(CheckBox)findViewById(R.id.cb_acept);

        databaseReference= FirebaseDatabase.getInstance().getReference("User");

        sp_goal.setOnItemSelectedListener(this);
        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,item);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        sp_goal.setAdapter(aa);

        final Calendar calendar=Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener dob=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                String myFormat = "dd/MM/yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                et_dob.setText(sdf.format(calendar.getTime()));
            }
        };
        et_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(
                  UserInsertData.this,dob,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            goal=item[position];
            Toast.makeText(this,"Your goal is "+item[position],Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void saveData()
    {
        String name=et_name.getText().toString();
        String number=et_number.getText().toString();
        String email=et_email.getText().toString();
        int genderid=rg_gender.getCheckedRadioButtonId();
        rb_gender=(RadioButton)findViewById(genderid);
        String gender=rb_gender.getText().toString();
        String dob1=et_dob.getText().toString();
        if(cb_acept.isChecked())
        {
            String acept=cb_acept.getText().toString();
            String id=databaseReference.push().getKey();
            Users users=new Users(id,name,number,email,gender,dob1,goal,acept);
            databaseReference.child(id).setValue(users);
            sendSMS(number,name);
            Toast.makeText(getApplicationContext(),"Data Saved Successfully",Toast.LENGTH_LONG).show();
        }

    }
    public  void sendSMS(String no,String Name)
    {
        String message="Hello "+Name+"\nThankyou for Register your self in My App";
        Intent intent=new Intent(getApplicationContext(),UserInsertData.class);
        PendingIntent pi=PendingIntent.getActivity(getApplicationContext(), 0, intent,0);

        SmsManager sms=SmsManager.getDefault();
        sms.sendTextMessage(no,null,message,pi,null);
    }
}
