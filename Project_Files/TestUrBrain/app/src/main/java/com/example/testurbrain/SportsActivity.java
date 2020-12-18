package com.example.testurbrain;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class SportsActivity extends AppCompatActivity {

    TextView tvNumber, tvQuestion, tvScore;
    Button btnSubmit, btnQuit;
    RadioGroup answersgrp;
    RadioButton rb1, rb2, rb3, rb4;
    ActionBar actionBar;

    String[] questions = {
            "When were the first recorded olympics held?",
            "The term 'beamer' is associated with",
            "Bull fighting is the national game of which country?",
            "In which game the term 'Putting' is used?",
            "The 2017 Indian Premier League (IPL 2017) first match on 5 April, 2017 was held in ?",
            "Against which team did Virender Sehwag make his one day international debut?",
            "With which game does Davis Cup is associated",
            "Who is the first Indian woman to win an Asian Games gold in 400m run",
            "Which batsman started his international cricketing career at the age of 16?",
            "Where did MS Dhoni make his ODI debut ?"
    };
    String[] answers = {"776BC","Cricket","Spain","Golf","Hyderabad","Pakistan","Lawn Tennis","P.T. Usha","Sachin Tendulkar","Chiittagong"};
    String[] opt = {
            "825BC","776BC","320BC","80AD",
            "Football","Hockey","Chess","Cricket",
            "Spain","Portugal","Hungary","Poland",
            "Hockey","Chess","Golf","Billiards",
            "Banglore","Delhi","Hyderabad","Kolkata",
            "New Zealand","Sri lanka","Pakistan","South Africa",
            "Hockey","Table Tennis","Lawn Tennis","Polo",
            "M.L. Valsamma","P.T. Usha","K.Malleshwari","Kamaljit Sandhu",
            "Suresh Raina","Sachin Tendulkar","Rahul Dravid","Piyush Chawla",
            "Chiittagong","Dhaka","Hyderabad","Vishakhapatnam"
    };

    String statement;

    int flag=0;
    int marks=0,correct=0,wrong=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_space);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Sports");

        tvNumber = findViewById(R.id.tvNumber);
        tvQuestion = findViewById(R.id.tvQuestion);
        tvScore = findViewById(R.id.tvScore);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnQuit = findViewById(R.id.btnQuit);
        answersgrp = findViewById(R.id.answersgrp);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);

        statement = "Question "+ (flag + 1);

        tvNumber.setText(statement);
        tvQuestion.setText(questions[flag]);
        rb1.setText(opt[0]);
        rb2.setText(opt[1]);
        rb3.setText(opt[2]);
        rb4.setText(opt[3]);
        tvScore.setText("Score: 0");

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(answersgrp.getCheckedRadioButtonId() == -1)
                {
                    Toast.makeText(getApplicationContext(), "Please select one choice", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    RadioButton rbSelected = findViewById(answersgrp.getCheckedRadioButtonId());

                    String ansText = rbSelected.getText().toString();

                    if(ansText.equals(answers[flag])) {
                        correct++;
                        Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        wrong++;
                        Toast.makeText(getApplicationContext(), "Wrong", Toast.LENGTH_SHORT).show();
                    }

                    flag++;

                    if (tvScore != null)
                        tvScore.setText("Score: "+correct);

                    if(flag<questions.length)
                    {
                        statement = "Question "+ (flag + 1);

                        tvNumber.setText(statement);
                        tvQuestion.setText(questions[flag]);
                        rb1.setText(opt[flag*4]);
                        rb2.setText(opt[flag*4 +1]);
                        rb3.setText(opt[flag*4 +2]);
                        rb4.setText(opt[flag*4 +3]);
                    }
                    else
                    {
                        marks=correct;
                        Intent intent = new Intent(getApplicationContext(),ResultActivity.class);
                        intent.putExtra("correct", correct);
                        intent.putExtra("wrong", wrong);
                        Log.d("CHECK", correct + " " + wrong);
                        startActivity(intent);
                    }

                    answersgrp.clearCheck();
                }
            }
        });

        btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(SportsActivity.this, R.style.AlertDialogStyle));
                builder.setTitle("Please Confirm");
                builder.setMessage("Are you sure you want to quit this quiz?");
                builder.setIcon(R.drawable.warning);
                builder.setCancelable(false);

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent intent = new Intent(SportsActivity.this, QuizActivity.class);
                        startActivity(intent);
                    }
                });

                builder.show();
            }
        });

    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(SportsActivity.this, R.style.AlertDialogStyle));
        builder.setTitle("Please Confirm");
        builder.setMessage("Are you sure you want to quit this quiz?");
        builder.setIcon(R.drawable.warning);
        builder.setCancelable(false);

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Intent intent = new Intent(SportsActivity.this, QuizActivity.class);
                startActivity(intent);
            }
        });

        builder.show();
    }
}
