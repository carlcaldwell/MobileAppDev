package com.example.amtrak;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Arrival extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrival);

        // get shared prefs
        SharedPreferences sp = getSharedPreferences("sp", Context.MODE_PRIVATE);
        int hours = sp.getInt("hours", 0);
        int minutes = sp.getInt("minutes", 0);
        int length = sp.getInt("length", 0);

        // calculate trip length
        int totalMins = minutes + length;

        while(totalMins >= 60){
            hours++;
            totalMins-=60;
        }
        // account for leading zero in minute
        String minsString = totalMins < 10? minsString = "0"+totalMins : ""+totalMins;

        boolean isRedEye = false;
        while(hours > 23){
            isRedEye = true;
            hours-=24;
        }


        // output the requirements
        // arrival time
        TextView arrival = findViewById(R.id.txtArrivalTime);
        arrival.setText(hours+":"+minsString);
        // red eye
        LinearLayout redeye = findViewById(R.id.redEyeDisplay);
        if(isRedEye){
            redeye.setAlpha(1);
        }else{
            redeye.setAlpha(0);
        }
    }

    public void goBack(View v){
        // change activity
        startActivity(new Intent(Arrival.this, MainActivity.class));
    }

}