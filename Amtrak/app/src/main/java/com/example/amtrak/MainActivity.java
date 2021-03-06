package com.example.amtrak;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int hours, minutes, length;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void calculate(View v){
        // grab inputs
        EditText hourInpt = findViewById(R.id.editHour);
        EditText minInpt = findViewById(R.id.editMin);
        EditText lenInpt = findViewById(R.id.editLength);

        // get values
        try {
            hours = Integer.parseInt(hourInpt.getText().toString());
            minutes = Integer.parseInt(minInpt.getText().toString());
            length = Integer.parseInt(lenInpt.getText().toString());
        }catch(Exception e){
            Toast.makeText(MainActivity.this, "Invalid input! All values must be integers!", Toast.LENGTH_SHORT).show();
            return;
        }

        // validate values
        if(hours >= 24 || hours < 0 || minutes >= 60 || minutes < 0 || length > 1500){
            if(hours >= 24 || hours < 0){
                Toast.makeText(MainActivity.this, "Departure Hours Incorrect!", Toast.LENGTH_SHORT).show();
            }
            if(minutes >= 60 | minutes < 0){
                Toast.makeText(MainActivity.this, "Departure Minutes Incorrect!", Toast.LENGTH_SHORT).show();
            }
            if(length > 1500){
                Toast.makeText(MainActivity.this, "Trip Duration must be less than 1500 minutes!", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        // store values in shared prefs
        sp = getSharedPreferences("sp", Context.MODE_PRIVATE);
        SharedPreferences.Editor e = sp.edit();
        e.putInt("hours", hours);
        e.putInt("minutes", minutes);
        e.putInt("length", length);
        e.apply();

        // change activity
        startActivity(new Intent(MainActivity.this, Arrival.class));


    }
}