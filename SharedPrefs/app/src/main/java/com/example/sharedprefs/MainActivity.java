package com.example.sharedprefs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.FileObserver;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText info1, info2;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get the values
        sp = getSharedPreferences("sp", Context.MODE_PRIVATE);
        info1 = findViewById(R.id.editTextInfo1);
        info2 = findViewById(R.id.editTxtInfo2);

    }

    public void submitForm(View v) throws IOException {

        // create shared pref object
//        SharedPreferences.Editor e = sp.edit();
//        e.putString("info1", info1.getText().toString());
//        e.putString("info2", info2.getText().toString());
//        e.apply();
        String inf1 = info1.getText().toString();
        String inf2 = info2.getText().toString();


        try{
            // write to file
            FileOutputStream fos = openFileOutput("file.txt", Context.MODE_PRIVATE);
            OutputStreamWriter osr = new OutputStreamWriter(fos);
            osr.write(inf1 +"\n"+ inf2);
            osr.close();
            Toast.makeText(MainActivity.this, "Write Finished", Toast.LENGTH_SHORT).show();

        }catch(Exception e){
            Toast.makeText(MainActivity.this, "Error opening file", Toast.LENGTH_LONG).show();
        }


        // start new activity
        startActivity(new Intent(MainActivity.this, Display.class));
    }

}