package com.example.flagquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mp = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // play menu music, if not playing
        if(mp == null){
            mp = MediaPlayer.create(MainActivity.this, R.raw.music);
            mp.setLooping(true);
            mp.start();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        // restart sound
        if(mp == null){
            mp = MediaPlayer.create(MainActivity.this, R.raw.music);
            mp.setLooping(true);
            mp.start();
        }
    }

    // start the game by calling the questions activity
    public void startGame(View v){
        mp.release();
        mp = null;
        startActivity(new Intent(MainActivity.this, Question.class));
    }
}