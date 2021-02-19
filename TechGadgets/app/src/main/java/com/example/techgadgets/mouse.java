package com.example.techgadgets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class mouse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mouse);

        Button btn = findViewById(R.id.btnMouse);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.amazon.com/BENGOO-Changing-Ergonomic-Computer-Adjustable/dp/B00Z9V0NKC/ref=sr_1_6?dchild=1&keywords=mouse&qid=1613762892&sr=8-6")));
            }
        });

    }
}