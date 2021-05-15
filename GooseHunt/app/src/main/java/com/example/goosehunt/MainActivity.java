package com.example.goosehunt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView gooseImg;
    int maxX=60, minX=10, maxY=60, minY=20;
    int width, height;
    final int[] currentX = {0}; // the current location of the goose img ; bottom left corner
    final int[] currentY = {0};
    // how far to move each tick
    int xIncrement, yIncrement;
    final boolean[] goingUp = {true};  // defines which direction the goose is going
    final boolean[] goingRight = {true};
    int speed = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // hook goose img
        gooseImg = findViewById(R.id.gooseImg);

        // set the height and width
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        width=dm.widthPixels - 150;
        height= dm.heightPixels - 100;

        currentX[0] = (int)(Math.random()*(width+1));
        currentY[0] = height;
        gooseImg.setX(currentX[0]);
        gooseImg.setY(currentY[0]);
        // get new movement incrementer
        xIncrement = (int)(Math.random()*(maxX-minX+1)+minX);
        yIncrement = (int)(Math.random()*(maxY-minY+1)+minY);

        startGoose();

    }

    public void startGoose(){
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if(goingRight[0]){
                    if(currentX[0] + xIncrement > width){
                        currentX[0] = width;
                        goingRight[0] = false;
                        gooseImg.setScaleX(1);
                    }else{
                        currentX[0] = currentX[0] + xIncrement;
                    }
                }else{
                    // going left
                    if(currentX[0] - xIncrement < 0){
                        currentX[0] = 0;
                        goingRight[0] = true;
                        gooseImg.setScaleX(-1);
                    }else{
                        currentX[0] = currentX[0] - xIncrement;
                    }
                }

                if(goingUp[0]){
                    if(currentY[0] + yIncrement > height){
                        currentY[0] = height;
                        goingUp[0] = false;
                    }else{
                        currentY[0] = currentY[0] + yIncrement;
                    }
                }else{
                    // going down
                    if(currentY[0] - yIncrement < 0){
                        currentY[0] = 0;
                        goingUp[0] = true;
                    }else{
                        currentY[0] = currentY[0] - yIncrement;
                    }
                }




                gooseImg.setX(currentX[0]);
                gooseImg.setY(currentY[0]);

                handler.postDelayed(this, speed);  // 1 second delay
            }
        };
        handler.post(runnable);

    }



    public void clickyBoi(View v) {
        Toast.makeText(this, "hit", Toast.LENGTH_SHORT).show();

        // reset the location of the goose
        currentX[0] = (int)(Math.random()*(width+1));
        currentY[0] = height;
        gooseImg.setX(currentX[0]);
        gooseImg.setY(currentY[0]);

        // get new movement incrementer
        xIncrement = (int)(Math.random()*(maxX-minX+1)+minX);
        yIncrement = (int)(Math.random()*(maxY-minY+1)+minY);
    }

    public void miss(View v){
        Toast.makeText(this, "miss", Toast.LENGTH_SHORT).show();
    }

}