package com.example.playingwiththemes;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    MediaPlayer mpIz = null, mp50 = null;
    Button playBtn, pauseBtn, stopBtn;
    Button playBtn2, pauseBtn2, stopBtn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // hook buttons
        playBtn = findViewById(R.id.btnPlay);
        pauseBtn = findViewById(R.id.btnPause);
        stopBtn = findViewById(R.id.btnStop);
        playBtn2 = findViewById(R.id.btnPlay2);
        pauseBtn2 = findViewById(R.id.btnPause2);
        stopBtn2 = findViewById(R.id.btnStop2);


        // create listeners and handlers
        playBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(mpIz == null){
                    mpIz = MediaPlayer.create(MainActivity.this, R.raw.rainbow );
                }
                mpIz.start();
            }
        });
        pauseBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mpIz.pause();
            }
        });
        stopBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    mpIz.stop();
                    mpIz.release();
                    mpIz = null;
                }catch(Exception e){
                    Toast.makeText(MainActivity.this, "Nothing Playing", Toast.LENGTH_SHORT).show();
                }

            }
        });


        playBtn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(mp50 == null){
                    mp50 = MediaPlayer.create(MainActivity.this, R.raw.hawaii50 );
                }
                mp50.start();
            }
        });
        pauseBtn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mp50.pause();
            }
        });
        stopBtn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    mp50.stop();
                    mp50.release();
                    mp50 = null;
                }catch(Exception e){
                    Toast.makeText(MainActivity.this, "Nothing Playing", Toast.LENGTH_SHORT).show();
                }

            }
        });



    }

}