package com.example.soundboard;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void playICantGoBack(View v){
        MediaPlayer mp = MediaPlayer.create(MainActivity.this, R.raw.icantgoback );
        mp.start();
    }
    public void playInThisVideo(View v){
        MediaPlayer mp = MediaPlayer.create(MainActivity.this, R.raw.inthisvideo );
        mp.start();
    }
    public void playIThinkItsGoodPractice(View v){
        MediaPlayer mp = MediaPlayer.create(MainActivity.this, R.raw.ithinkthatsgoodpractice );
        mp.start();
    }
    public void playPreviousVideo(View v){
        MediaPlayer mp = MediaPlayer.create(MainActivity.this, R.raw.previousvideo );
        mp.start();
    }
    public void playRandomlyCreatingObjects(View v){
        MediaPlayer mp = MediaPlayer.create(MainActivity.this, R.raw.randomlycreateobjects );
        mp.start();
    }
    public void playSeeYaThere(View v){
        MediaPlayer mp = MediaPlayer.create(MainActivity.this, R.raw.seeyathere );
        mp.start();
    }
    public void playSomewhereOverTheRainbow(View v){
        MediaPlayer mp = MediaPlayer.create(MainActivity.this, R.raw.somewhereovertherainbow );
        mp.start();
    }
    public void playStopIt(View v){
        MediaPlayer mp = MediaPlayer.create(MainActivity.this, R.raw.stopit );
        mp.start();
    }
    public void playTheButtonWontWork(View v){
        MediaPlayer mp = MediaPlayer.create(MainActivity.this, R.raw.thebuttonwontwork );
        mp.start();
    }
    public void playHawaii(View v){
        MediaPlayer mp = MediaPlayer.create(MainActivity.this, R.raw.hawaii50 );
        mp.start();
    }


}