package com.example.wunna.braintrainner;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int score,numberOfQuestion;
    TextView pointText,checkText,sumText,timerText;
    ArrayList<Integer> answers=new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    Button buttonOne,buttonTwo,buttonThree,buttonFour;
    Button go,playAgain;
    RelativeLayout relativeLayout;

    public void playAgain(View view){
        score=0;
        numberOfQuestion=0;
        pointText.setText("00/00");
        checkText.setText("");
        playAgain.setVisibility(View.INVISIBLE);
        new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long l) {
                timerText.setText(Integer.valueOf((int) (l/1000))+"s");
            }

            @Override
            public void onFinish() {
                timerText.setText("0s");
                checkText.setText("Your score is "+score+"/"+numberOfQuestion);
                playAgain.setVisibility(View.VISIBLE);
            }
        }.start();

    }

    public void newQuestion(){
        Random rand=new Random();
        int a=rand.nextInt(21);
        int b=rand.nextInt(21);
        answers.clear();
        sumText.setText(a+"+"+b);
        int incorrectAnswer;
        locationOfCorrectAnswer=rand.nextInt(4);
        Log.i("location of Ans", String.valueOf(locationOfCorrectAnswer));
        for (int i=0;i<4;i++){
            if (i==locationOfCorrectAnswer){
                answers.add(a+b);

            }
            else {
                incorrectAnswer=rand.nextInt(41);
                while (incorrectAnswer==a+b){
                    incorrectAnswer=rand.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }
        }
        buttonOne.setText(Integer.toString(answers.get(0)));
        buttonTwo.setText(Integer.toString(answers.get(1)));
        buttonThree.setText(Integer.toString(answers.get(2)));
        buttonFour.setText(Integer.toString(answers.get(3)));
    }

    public void chooseAnswer(View view){
        numberOfQuestion++;
        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
         score++;
            checkText.setText("Correct !");
        }
        else {
            checkText.setText("Wrong");
        }
        if (score<=9&&numberOfQuestion<=9) pointText.setText("0"+score+"/0"+numberOfQuestion);
        else if (score<=9&&numberOfQuestion>9)pointText.setText("0"+score+"/"+numberOfQuestion);
        else pointText.setText(score+"/"+numberOfQuestion);
        newQuestion();
    }

    public void start(View view){
        go.setVisibility(View.INVISIBLE);
        relativeLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.playAgain));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        go=(Button) findViewById(R.id.Go);
        buttonOne= (Button) findViewById(R.id.btnOne);
        buttonTwo= (Button) findViewById(R.id.btnTwo);
        buttonThree= (Button) findViewById(R.id.btnThree);
        buttonFour= (Button) findViewById(R.id.btnFour);
        sumText=(TextView) findViewById(R.id.sumText);
        pointText=(TextView) findViewById(R.id.PointTextView);
        checkText=(TextView) findViewById(R.id.check);
        timerText= (TextView) findViewById(R.id.timerTextView);
        playAgain=(Button)findViewById(R.id.playAgain);
        relativeLayout= (RelativeLayout) findViewById(R.id.relativeLayout);
        newQuestion();

        playAgain(findViewById(R.id.playAgain));

    }
}
