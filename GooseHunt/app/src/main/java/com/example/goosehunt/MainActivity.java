package com.example.goosehunt;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ImageView gooseImg;
    int maxX=20, minX=5, maxY=10, minY=20;
    int hits=0, misses=0;
    int allowedMissed=5;
    int width, height;
    final int[] currentX = {0}; // the current location of the goose img ; bottom left corner
    final int[] currentY = {0};
    // how far to move each tick
    int xIncrement, yIncrement;
    final boolean[] goingUp = {true};  // defines which direction the goose is going
    final boolean[] goingRight = {true};
    int speed = 20;
    TextView hitsScore, missesScore, hitOrMiss;
    float[] lastTouchDownXY = new float[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // hook goose img
        gooseImg = findViewById(R.id.gooseImg);
        hitsScore = findViewById(R.id.hits);
        missesScore = findViewById(R.id.misses);
        hitOrMiss = findViewById(R.id.missOutput);
        View bg = findViewById(R.id.background);

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



        updateScore();
        startGoose();

        bg.setOnTouchListener(this::onTouch);


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
                        float x = lastTouchDownXY[0];
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

                handler.postDelayed(this, speed);
            }
        };
        handler.post(runnable);

    }



    public void hit() {

        // reset the location of the goose
        currentX[0] = (int)(Math.random()*(width+1));
        currentY[0] = height;
        gooseImg.setX(currentX[0]);
        gooseImg.setY(currentY[0]);

        // get new movement incrementer
        xIncrement = (int)(Math.random()*(maxX-minX+1)+minX);
        yIncrement = (int)(Math.random()*(maxY-minY+1)+minY);

        hitOrMiss.setX(lastTouchDownXY[0]-80);
        hitOrMiss.setY(lastTouchDownXY[1]-50);
        hitOrMiss.setText("Hit!");
        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(hitOrMiss, "alpha",  1f, 0f);
        fadeOut.setDuration(500);
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(hitOrMiss, "alpha", 0f, 1f);
        fadeIn.setDuration(10);

        final AnimatorSet mAnimationSet = new AnimatorSet();
        mAnimationSet.play(fadeOut).after(fadeIn);
        mAnimationSet.start();


        hits++;
        updateScore();

    }

    public void miss(){
        misses++;
        if(misses > allowedMissed-1){
            startActivity(new Intent(this, GameOver.class));
            finish();
        }
        updateScore();

        hitOrMiss.setX(lastTouchDownXY[0]-80);
        hitOrMiss.setY(lastTouchDownXY[1]-50);
        hitOrMiss.setText("Miss!");
        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(hitOrMiss, "alpha",  1f, 0f);
        fadeOut.setDuration(500);
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(hitOrMiss, "alpha", 0f, 1f);
        fadeIn.setDuration(10);

        final AnimatorSet mAnimationSet = new AnimatorSet();
        mAnimationSet.play(fadeOut).after(fadeIn);
        mAnimationSet.start();

    }


    private void updateScore(){
        missesScore.setText("Miss:"+misses);
        hitsScore.setText("Hits:"+hits);
    }

    private boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            lastTouchDownXY[0] = event.getX();
            lastTouchDownXY[1] = event.getY();

            if ((lastTouchDownXY[0] >= gooseImg.getX() && lastTouchDownXY[0] <= gooseImg.getWidth() + gooseImg.getX()) && (lastTouchDownXY[1] >= gooseImg.getY() && lastTouchDownXY[1] <= gooseImg.getHeight() + gooseImg.getY())) {
                hit();
            } else {
                miss();
            }
        }
        System.out.println("updated:" + lastTouchDownXY[0] + " " + lastTouchDownXY[1]);
        return false;
    }
}