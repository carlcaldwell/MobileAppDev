package com.example.feb1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    private Button b1;
    private TextView txt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = findViewById(R.id.btn1);
        txt1 = findViewById(R.id.txtMain);

//        b1.setOnClickListener(this::onClick);
//        txt1.setOnClickListener(this::onClick);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Message", "button 1");
                startActivity(new Intent(MainActivity.this, page2.class));
            }
        });

        txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Message", "txt1");
            }
        });


    }

    public void clickMe(View view){
        Toast.makeText(MainActivity.this, "Hello", Toast.LENGTH_LONG).show();
    }

//    @Override
//    public void onClick(View v) {
//        Toast.makeText(MainActivity.this, "Implemented this!", Toast.LENGTH_LONG).show();
//        Log.i("Message", v.toString());
//    }
}