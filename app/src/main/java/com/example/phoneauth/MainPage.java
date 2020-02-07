package com.example.phoneauth;

import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainPage extends AppCompatActivity {
    RadioButton cricket,gender,bedminton;
    RadioGroup rg;
    ToggleButton tb;
    CheckBox acept,decline;
    ImageButton imageButton;
    Switch switch1;
    Button switchdisplay;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.components_activity);

        switch1=(Switch)findViewById(R.id.switch1);
        switchdisplay=(Button)findViewById(R.id.switchdisplay);
        rg=(RadioGroup) findViewById(R.id.rg);
        cricket=(RadioButton) findViewById(R.id.cricket);
        bedminton=(RadioButton) findViewById(R.id.bedminton);
        tb=(ToggleButton) findViewById(R.id.toggleButton);

        imageButton=(ImageButton)findViewById(R.id.imageButton);
        acept=(CheckBox)findViewById(R.id.acept);
        decline=(CheckBox)findViewById(R.id.decline);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder sb=new StringBuilder();
                int selectedId=rg.getCheckedRadioButtonId();
                if(selectedId==-1)
                {
                    Toast.makeText(getApplicationContext(),"Please select gender",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    gender=(RadioButton) findViewById(selectedId);
                    sb.append(gender.getText());
                }
                if(switch1.isChecked())
                {
                    switchdisplay.setBackgroundColor(Color.GREEN);
                    sb.append("\n"+switch1.getText());
                }
                else{
                    switchdisplay.setBackgroundColor(Color.BLUE);
                    sb.append("\n"+switch1.getText());
                }
                sb.append("\n"+cricket.getText());
                sb.append("\n"+bedminton.getText());
                sb.append("\n"+tb.getText());
                if(acept.isChecked())
                {
                    sb.append("\n Acepted");
                }
                else {
                    sb.append("\n Not Acepted");
                }
                if(decline.isChecked())
                {
                    sb.append("\n Declined");
                }
                else {
                    sb.append("\n Not Declined");
                }
                TextView txt=(TextView)findViewById(R.id.txt);
                txt.setText(sb.toString());
                Toast.makeText(getApplicationContext(),sb.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        switch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(switch1.isChecked())
                {
                    switchdisplay.setBackgroundColor(Color.GREEN);
                }
                else{
                    switchdisplay.setBackgroundColor(Color.BLUE);
                }
            }
        });
    }
}
