package com.example.thelatestmusicscene;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnStory1, btnStory2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // find buttons
        btnStory1 = findViewById(R.id.btnStoryOne);
        btnStory2 = findViewById(R.id.btnStoryTwo);

        // create listeners
        btnStory1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, StoryOne.class));
            }
        });
        btnStory2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, StoryTwo.class));
            }
        });

    }
}