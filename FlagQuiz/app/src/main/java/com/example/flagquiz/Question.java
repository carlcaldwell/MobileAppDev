package com.example.flagquiz;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Question extends AppCompatActivity {

    final int QUESTION_AMOUNT = 10;
    int score = 0;
    int questionsPlayed = 0;
    MediaPlayer resultsSound;

    // setup vars for our nodes
    Button a0,a1,a2,a3;
    TextView continueBtn;
    ImageView IvFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        // hook nodes
        a0 = findViewById(R.id.answer0); // first answer btn
        a1 = findViewById(R.id.answer1); // second answer btn
        a2 = findViewById(R.id.answer2); // third answer btn
        a3 = findViewById(R.id.answer3); // fourth answer btn
        continueBtn = findViewById(R.id.tvContinue); // next question button
        IvFlag = findViewById(R.id.ivFlag); // the flag image

        // start up the first question
        runQuestion();
    }

    public void runQuestion(){

        // RESET ALL OLD FORMATTING
        // clear onclick listener
        continueBtn.setOnClickListener(null);
        // reset all old text and colors
        continueBtn.setText(null);
        a0.setBackgroundColor(Color.parseColor("#55AEEA"));
        a1.setBackgroundColor(Color.parseColor("#55AEEA"));
        a2.setBackgroundColor(Color.parseColor("#55AEEA"));
        a3.setBackgroundColor(Color.parseColor("#55AEEA"));

        // create a new question
        QuestionBuilder qb = new QuestionBuilder();

        // set the flag image
        IvFlag.setImageResource(qb.getFlagImg());

        // get/set the text for each country
        a0.setText(qb.getAnswer0());
        a1.setText(qb.getAnswer1());
        a2.setText(qb.getAnswer2());
        a3.setText(qb.getAnswer3());

        // setup handlers/listeners for each button
        // each calls the same method, but different param
        a0.setOnClickListener(e->{submitAnswer(qb, 0);});
        a1.setOnClickListener(e->{submitAnswer(qb, 1);});
        a2.setOnClickListener(e->{submitAnswer(qb, 2);});
        a3.setOnClickListener(e->{submitAnswer(qb, 3);});

    }

    // this method accepts the chosen answer index
    // it checks if its correct.
    // if correct, it changes the button color to green
    // if incorrect, it changes the correct green and the chosen red
    private void submitAnswer(QuestionBuilder qbb, int selected){
        // put the buttons in an array so we can identify them
        Button buttons[] = new Button[]{a0, a1, a2, a3};
        if(qbb.isCorrect(selected)){
            // the user answered correctly.
            // increase the score and change the button color to green
            score++;
            buttons[selected].setBackgroundColor(Color.GREEN);
            resultsSound = MediaPlayer.create(Question.this, R.raw.correct);
            resultsSound.start();
        }else{
            // the user answered incorrectly
            // change their answer red and the correct answer green
            int correctAnswerIndex = qbb.getCorrectAnswer();
            if(selected != correctAnswerIndex){
                buttons[selected].setBackgroundColor(Color.RED);
            }
            buttons[correctAnswerIndex].setBackgroundColor(Color.GREEN);
            resultsSound = MediaPlayer.create(Question.this, R.raw.wrong);
            resultsSound.start();
        }
        // start the next question

        nextQuestion();
    }

    // checks if the game is over.
    // if gameover, direct user to score page : increase score
    // if not game over, direct user to next question.
    private void nextQuestion() {
        questionsPlayed++;
        // check if the game is over.
        if(questionsPlayed >= QUESTION_AMOUNT){
            // game finished. direct the user to the score page
            continueBtn.setText("Game Over! View Results! >>");
            continueBtn.setOnClickListener(ee->{
                // store the final score
                // send the player to the final score page
                SharedPreferences sp = getSharedPreferences("sp", Context.MODE_PRIVATE);
                SharedPreferences.Editor e = sp.edit();
                e.putInt("score", score);
                e.apply();
                startActivity(new Intent(Question.this, FinalScore.class));
                resultsSound.release();
            });
        }else{
            // the user still has questions left.
            // change the text to continue and
            continueBtn.setText("Continue >>");
            continueBtn.setOnClickListener(e->{
                runQuestion();
                resultsSound.release();
            });
        }

    }


}