package com.example.goosehunt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView gooseImg;
    int width, height;
    int currentX, currentY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gooseImg = findViewById(R.id.gooseImg);

        // get height/width
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        width = dm.widthPixels;
        height = dm.heightPixels;


    }


    public void left(View v){
        gooseImg.setLeft(gooseImg.getLeft() - 20);
    }
    public void right(View v){
        gooseImg.setLeft(gooseImg.getLeft() + 20);
    }
    public void up(View v){
        gooseImg.setTop(gooseImg.getTop() - 20);
    }
    public void down(View v){
        gooseImg.setTop(gooseImg.getTop() + 20);
    }
}