package com.example.dell.braintrainer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button button0;
    Button playAgain;
    Button button1;
    Button button2;
    Button button3;
    RelativeLayout gameRelativeLayout;
    TextView timerTextView;
    TextView sumTextView;
    ArrayList<Integer> answers=new ArrayList<Integer>();
    int locationofcorrectanswers;
    TextView pointsTextView;
    int noOfQuestions=0;
    TextView resultTextView;
    int score=0;
    Button startbutton;
    ImageView imageView;
    public void playAgain(View view){
        score=0;
        noOfQuestions=0;
        timerTextView.setText("30s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        generateQuestion();
        playAgain=findViewById(R.id.playAgainButton);
        playAgain.setVisibility(View.INVISIBLE);
        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l/1000) + "s");

            }

            @Override
            public void onFinish() {
                playAgain.setVisibility(View.VISIBLE);
                resultTextView.setText("Your score:" + Integer.toString(score) + "/" + Integer.toString(noOfQuestions ));
                timerTextView.setText("0s");

            }
        }.start();


    }
    public void generateQuestion(){
        Random rand=new Random();
        int a=rand.nextInt(21);
        int b=rand.nextInt(21);
        int incorrectAnswer;
        sumTextView.setText(Integer.toString(a)+ " + " + Integer.toString(b));
        locationofcorrectanswers=rand.nextInt(4);
        answers.clear();
        for(int i=0; i<4; i++){
            if(i == locationofcorrectanswers){
                answers.add(a + b);
            }
            else{
                incorrectAnswer=rand.nextInt(41);
                while(incorrectAnswer== a + b){
                    incorrectAnswer=rand.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));



    }
    public void chooseAnswer(View view){
        if(view.getTag().toString().equals(Integer.toString(locationofcorrectanswers))){
            Log.i("correct","correct");
            score++;
            resultTextView.setText("Dimaag to hai bhai!");
        }
        else{
            resultTextView.setText(" Abe Nalle!");
        }
        noOfQuestions++;
        pointsTextView.setText(Integer.toString(score) + "/" + Integer.toString(noOfQuestions));
        generateQuestion();
    }
    public void start(View view){
        startbutton.setVisibility(View.INVISIBLE);
        imageView.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(RelativeLayout.VISIBLE);
        playAgain=findViewById(R.id.playAgainButton);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=findViewById(R.id.imageView);
        pointsTextView=findViewById(R.id.pointsTextView);
        timerTextView=findViewById(R.id.timerTextView);
        startbutton=findViewById(R.id.button);
         button0=findViewById(R.id.button0);
         gameRelativeLayout=findViewById(R.id.GameRelativeLayout);
        resultTextView=findViewById(R.id.resultTextView);
         button1=findViewById(R.id.button1);
         button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        sumTextView=findViewById(R.id.sumTextView);
        playAgain(findViewById(R.id.playAgainButton));





    }
}
