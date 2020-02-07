package com.example.phoneauth;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RatingApp extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Button yes,no;
        final RatingBar ratingBar;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rating_activity);

         yes=(Button)findViewById(R.id.yes_rate);
         no=(Button)findViewById(R.id.cancel_rate);
         ratingBar=(RatingBar)findViewById(R.id.rating);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rate=String.valueOf(ratingBar.getRating());
                Toast.makeText(getApplicationContext(),rate,Toast.LENGTH_SHORT).show();
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
