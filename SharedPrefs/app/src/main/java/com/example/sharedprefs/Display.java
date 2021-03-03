package com.example.sharedprefs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Display extends AppCompatActivity {

    TextView txtInfo1, txtInfo2;
    String info1, info2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        // grab prefs
//        SharedPreferences sp = getSharedPreferences("sp", Context.MODE_PRIVATE);
//        String info1 = sp.getString("info1", "");
//        String info2 = sp.getString("info2", "");


        try {
            FileInputStream fio = new FileInputStream("text.txt");
            BufferedReader bis = new BufferedReader(new InputStreamReader(fio));

            String buffer;
            ArrayList<String> values = new ArrayList<>();

            while((buffer = bis.readLine()) != null){
                values.add(buffer);
                System.out.println(buffer);
            }

            info1 = values.get(0);
            info2 = values.get(1);

            System.out.println(values.get(0));
            // set labels
            txtInfo1 = findViewById(R.id.labelInfo1);
            txtInfo2 = findViewById(R.id.labelInfo2);
            txtInfo1.setText(values.get(0));
            txtInfo2.setText(info2);

        }catch(Exception e){
            Toast.makeText(Display.this, "Finished Reading", Toast.LENGTH_SHORT).show();
        }

    }

}