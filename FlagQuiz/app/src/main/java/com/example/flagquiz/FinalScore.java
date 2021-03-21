package com.example.flagquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class FinalScore extends AppCompatActivity {

    private final int SCORE_TO_WIN = 7;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_score);

        // hook nodes
        TextView txtScore = findViewById(R.id.txtFinalScore);
        TextView txtCongrats = findViewById(R.id.txtCongrat);


        // get the players score
        SharedPreferences sp = getSharedPreferences("sp", Context.MODE_PRIVATE);
        score = sp.getInt("score", 0);
        txtScore.setText(score+"/10");

        if(score >= SCORE_TO_WIN){
            // the player wins.
            txtCongrats.setText("You Did It!");
        }else{
            // the player loses.
            txtCongrats.setText("Game Over\n:(");
        }

        Button again = findViewById(R.id.btnPlayAgain);
        again.setOnClickListener(e->{
            startActivity(new Intent(FinalScore.this, MainActivity.class));
        });
    }
}