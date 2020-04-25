package com.example.deltafactorize;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final String TAG ="MainActivity" ;
    private EditText num1;
    private Button factorize;
    private Button option1;
    private Button option2;
    private Button option3;
    private TextView result;
    private TextView gamescore;
    private TextView nithish;
    private ConstraintLayout appback;
    private int counter;
    int croption;
    int crfactor;
    int oth1;
    int oht2;
    static int score = 0;
    int higscore;
    SharedPreferences highscore;
    SharedPreferences.Editor updhighscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: ");

        highscore = getSharedPreferences("hscore",MODE_PRIVATE);
        updhighscore = highscore.edit();
        higscore = highscore.getInt("higscore",0);
        num1 = (EditText)findViewById(R.id.etnum1);
        nithish = (TextView)findViewById(R.id.txthighscore);
        nithish.setText("Highscore:  "+higscore);
        factorize = (Button)findViewById(R.id.btnfactorize);
        result = (TextView)findViewById(R.id.txtviewid);
        option1 = (Button)findViewById(R.id.opt1);
        option2 = (Button)findViewById(R.id.opt2);
        option3 = (Button)findViewById(R.id.opt3);
        gamescore = (TextView)findViewById(R.id.txtscore);
        gamescore.setText("Score: " + score);
        appback = (ConstraintLayout)findViewById(R.id.appbackground);


        option1.setVisibility(View.GONE);
        option2.setVisibility(View.GONE);
        option3.setVisibility(View.GONE);
        factorize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (num1.getText().toString().matches(""))
                {
                    Log.d(TAG, "onClick: Enter the number");
                    toastMessage("Enter the number");
                }
                else
                {
                    process();
                }


            }
        });

    }
    private void toastMessage(String message){
        Toast.makeText(MainActivity.this,message,Toast.LENGTH_SHORT).show();
    }
    public void process() {
        factorize.setEnabled(false);
        option1.setVisibility(View.VISIBLE);
        option2.setVisibility(View.VISIBLE);
        option3.setVisibility(View.VISIBLE);

        int number1 = Integer.parseInt(num1.getText().toString());
        int factorNumber = 1;
        List<Integer> factors = new ArrayList<>();

        while (factorNumber <= number1) {
            if (number1 % factorNumber == 0) {
                factors.add(factorNumber);
            }
            factorNumber++;
        }
        Random a = new Random();
        crfactor = factors.get(a.nextInt(factors.size()));

        if(number1>7) {
            oth1 = a.nextInt(number1);
            while (number1 % oth1 == 0) {
                oth1 = a.nextInt(number1);
            }
            oht2 = a.nextInt(number1);
            while (number1 % oht2 == 0 || oth1 == oht2) {
                oht2 = a.nextInt(number1);
            }
            croption = a.nextInt(3) + 1;
            if (croption == 1) {
                option1.setText("" + crfactor);
                option2.setText("" + oth1);
                option3.setText("" + oht2);
            } else if (croption == 2) {
                option1.setText("" + oth1);
                option2.setText("" + crfactor);
                option3.setText("" + oht2);
            } else {
                option1.setText("" + oth1);
                option2.setText("" + oht2);
                option3.setText("" + crfactor);
            }
        }
        else
        {
            oth1 = a.nextInt(20)+number1;
            while (number1 % oth1 == 0) {
                oth1 = a.nextInt(20)+number1;
            }
            oht2 = a.nextInt(20)+number1;
            while (number1 % oht2 == 0 || oth1 == oht2) {
                oht2 = a.nextInt(20)+number1;
            }
            croption = a.nextInt(3) + 1;
            if (croption == 1) {
                option1.setText("" + crfactor);
                option2.setText("" + oth1);
                option3.setText("" + oht2);
            } else if (croption == 2) {
                option1.setText("" + oth1);
                option2.setText("" + crfactor);
                option3.setText("" + oht2);
            } else {
                option1.setText("" + oth1);
                option2.setText("" + oht2);
                option3.setText("" + crfactor);
            }
        }
        end();
    }


    void end() {

        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (croption == 1)
                {
                    option1.setBackgroundColor(Color.GREEN);
                    score = score +5;
                    gamescore.setText("Score: " + score);
                    appback.setBackgroundColor(Color.rgb(200,255,0));

                }
                else
                {
                    option1.setBackgroundColor(Color.rgb(255,61,0));
                    result.setText("The correct answer is  " + crfactor);
                    appback.setBackgroundColor(Color.RED);

                    if(score > higscore)
                    {
                        higscore = score;
                        score = 0;
                        updhighscore.putInt("higscore",higscore);
                        updhighscore.commit();
                        gamescore.setText("Score: " + score);
                        nithish.setText("High score"+ higscore);
                    }
                    else
                    {
                        score = 0;
                        gamescore.setText("Score: " + score);
                    }
                }


                option1.setEnabled(false);
                option2.setEnabled(false);
                option3.setEnabled(false);
                CountDownTimer b = new CountDownTimer(1000,1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        startActivity(new Intent(MainActivity.this,MainActivity.class));
                        MainActivity.this.finish();
                    }
                }.start();

            }
        });
        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (croption == 2)
                {
                    option2.setBackgroundColor(Color.GREEN);
                    score = score +5;
                    gamescore.setText("Score: " + score);
                    appback.setBackgroundColor(Color.rgb(200,255,0));                }
                else
                {
                    option2.setBackgroundColor(Color.rgb(255,61,0));
                    result.setText("The correct answer is  " + crfactor);
                    appback.setBackgroundColor(Color.RED);

                    if(score > higscore)
                    {
                        higscore = score;
                        score = 0;
                        updhighscore.putInt("higscore",higscore);
                        updhighscore.commit();
                        gamescore.setText("Score: " + score);
                        nithish.setText("High score"+ higscore);
                    }
                    else
                    {
                        score = 0;
                        gamescore.setText("Score: " + score);
                    }
                }


                option1.setEnabled(false);
                option2.setEnabled(false);
                option3.setEnabled(false);
                CountDownTimer b = new CountDownTimer(1000,1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        startActivity(new Intent(MainActivity.this,MainActivity.class));
                        MainActivity.this.finish();
                    }
                }.start();
            }
        });
        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (croption == 3)
                {
                    option3.setBackgroundColor(Color.GREEN);
                    score = score +5;
                    gamescore.setText("Score: " + score);
                    appback.setBackgroundColor(Color.rgb(200,255,0));                }
                else
                {
                    option3.setBackgroundColor(Color.rgb(255,61,0));
                    result.setText("The correct answer is  " + crfactor);
                    appback.setBackgroundColor(Color.RED);


                    if(score > higscore)
                    {
                        higscore = score;
                        score = 0;
                        updhighscore.putInt("higscore",higscore);
                        updhighscore.commit();
                        gamescore.setText("Score: " + score);
                        nithish.setText("High score"+ higscore);
                    }
                    else
                    {
                        score = 0;
                        gamescore.setText("Score: " + score);
                    }
                }

                option1.setEnabled(false);
                option2.setEnabled(false);
                option3.setEnabled(false);
                CountDownTimer b = new CountDownTimer(1000,1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        startActivity(new Intent(MainActivity.this,MainActivity.class));
                        MainActivity.this.finish();

                    }
                }.start();
            }
        });
    }
    @Override
    public void onBackPressed(){

    }
}
