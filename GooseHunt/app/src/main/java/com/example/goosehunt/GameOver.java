package com.example.goosehunt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class GameOver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        Button b = findViewById(R.id.mainMenuBtn);
        b.setOnClickListener(e->{
            startActivity(new Intent(this, MainMenu.class));
            finish();
        });
    }
}