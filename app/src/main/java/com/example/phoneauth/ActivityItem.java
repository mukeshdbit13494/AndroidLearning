package com.example.phoneauth;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityItem extends AppCompatActivity {
    CheckBox item1,item2,item3,item4;
    TextView price;
    public int cost=0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        item1=(CheckBox)findViewById(R.id.item1);
        item2=(CheckBox)findViewById(R.id.item2);
        item3=(CheckBox)findViewById(R.id.item3);
        item4=(CheckBox)findViewById(R.id.item4);
        price=(TextView) findViewById(R.id.price);


        item1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked)
                {
                    cost+=200;
                    price.setText(""+cost);
                }
                else{
                    cost-=200;
                    price.setText(""+cost);
                }
            }
        });

        item2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked)
                {
                    cost+=250;
                    price.setText(""+cost);
                }
                else{
                    cost-=250;
                    price.setText(""+cost);
                }
            }
        });
        item3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked)
                {
                    cost+=150;
                    price.setText(""+cost);
                }
                else{
                    cost-=150;
                    price.setText(""+cost);
                }
            }
        });
        item4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked)
                {
                    cost+=225;
                    price.setText(""+cost);
                }
                else{
                    cost-=225;
                    price.setText(""+cost);
                }
            }
        });

    }
}
