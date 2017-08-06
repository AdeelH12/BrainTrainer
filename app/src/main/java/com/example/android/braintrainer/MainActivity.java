package com.example.android.braintrainer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button go, answer0, answer1, answer2, answer3, playAgain;
    TextView sum, result, points, timer;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswers;
    int score = 0;
    int numberOfQuestions = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        go = (Button) findViewById(R.id.go_button);
        playAgain = (Button) findViewById(R.id.play_again);
        timer = (TextView) findViewById(R.id.timer_textView);
        result = (TextView) findViewById(R.id.result_textView);
        sum = (TextView) findViewById(R.id.sum_textView);
        points = (TextView) findViewById(R.id.points_textView);
        answer0 = (Button) findViewById(R.id.button0);
        answer1 = (Button) findViewById(R.id.button1);
        answer2 = (Button) findViewById(R.id.button2);
        answer3 = (Button) findViewById(R.id.button3);

        playAgain.setVisibility(View.INVISIBLE);


        Random rand = new Random();


        int a = rand.nextInt(21);
        int b = rand.nextInt(21);       // Will generate a random number between 0 & 20

        sum.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswers = rand.nextInt(4);     // randomly generate the location of the correct answer


        new CountDownTimer(30000, 1000) {


            @Override
            public void onTick(long l) {

                timer.setText(String.valueOf(l/1000) + " s ");


            }

            @Override
            public void onFinish() {

                timer.setText("0s");

                playAgain.setVisibility(View.VISIBLE);

                if(score<5){

                    result.setText("You Can Do Better!! " + Integer.toString(score) +  " / " + Integer.toString(numberOfQuestions));

                }else{
                    result.setText("Your Score: " + Integer.toString(score) +  " / " + Integer.toString(numberOfQuestions));
                }


            }
        }.start();
        int incorrectAnswer;

            answers.clear();

        for(int i = 0; i<4; i++){

            if(i == locationOfCorrectAnswers){

                answers.add(a + b);

            }else{

                incorrectAnswer = (rand.nextInt(41));

                while(incorrectAnswer == a + b){

                    incorrectAnswer = (rand.nextInt(41));
                }

                answers.add(incorrectAnswer);
            }


        }

        answer0.setText(Integer.toString(answers.get(0)));
        answer1.setText(Integer.toString(answers.get(1)));
        answer2.setText(Integer.toString(answers.get(2)));
        answer3.setText(Integer.toString(answers.get(3)));

    }



        public void chooseAnswer(View view){

            if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswers))){

                result.setText("Correct!");
                score++;
                //code below plays the sound
                // MediaPlayer mediaPlayer = MediaPlayer.create(this,);
                //mediaPlayer.start();



            }else{

                result.setText("Incorrect!");
            }

            numberOfQuestions++;
            points.setText(Integer.toString(score) +  " / " + Integer.toString(numberOfQuestions));
            Random rand = new Random();


            int a = rand.nextInt(21);
            int b = rand.nextInt(21);       // Will generate a random number between 0 & 20

            sum.setText(Integer.toString(a) + " + " + Integer.toString(b));

            locationOfCorrectAnswers = rand.nextInt(4);     // randomly generate the location of the correct answer

            int incorrectAnswer;

            answers.clear();

            for(int i = 0; i<4; i++){

                if(i == locationOfCorrectAnswers){

                    answers.add(a + b);

                }else{

                    incorrectAnswer = (rand.nextInt(41));

                    while(incorrectAnswer == a + b){

                        incorrectAnswer = (rand.nextInt(41));
                    }

                    answers.add(incorrectAnswer);
                }


            }

            answer0.setText(Integer.toString(answers.get(0)));
            answer1.setText(Integer.toString(answers.get(1)));
            answer2.setText(Integer.toString(answers.get(2)));
            answer3.setText(Integer.toString(answers.get(3)));





            playAgain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    score = 0;
                    numberOfQuestions = 0;
                    result.setText("");
                    playAgain.setVisibility(View.INVISIBLE);
                    timer.setText("30s");
                    points.setText("0/0");
                    new CountDownTimer(30000, 1000) {


                        @Override
                        public void onTick(long l) {

                            timer.setText(String.valueOf(l/1000) + " s ");


                        }

                        @Override
                        public void onFinish() {

                            timer.setText("0s");

                            if(score<5){

                                result.setText("You Can Do Better!! " + Integer.toString(score) +  " / " + Integer.toString(numberOfQuestions));

                            }else{
                                result.setText("Your Score: " + Integer.toString(score) +  " / " + Integer.toString(numberOfQuestions));
                            }


                        }
                    }.start();


                }
            });


            go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                go.setVisibility(View.INVISIBLE);

            }
        });

    }
}