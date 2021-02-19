package com.example.techgadgets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class usb extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usb);

        Button btn = findViewById(R.id.btnUsb);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.amazon.com/Extension-AINOPE-Material-Compatible-Printer-Black/dp/B087CM8ZJQ/ref=sr_1_3?crid=AMJIFIVO04KE&dchild=1&keywords=usb+extension+cable&qid=1613762552&sprefix=usb+extend%2Caps%2C601&sr=8-3\n")));
            }
        });
    }
}