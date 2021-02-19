package com.example.techgadgets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class hdmi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hdmi);

        Button btn = findViewById(R.id.btnHdmi);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.amazon.com/SGEYR-Switcher-Selector-Control-Support/dp/B07MMRTS4S/ref=sr_1_8?dchild=1&keywords=hdmi+switcher&qid=1613762724&sr=8-8")));
            }
        });

    }
}