package com.shabbir.mathgame;

import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

/**
 * Created by Shabbir Ali on 27/10/2020.
 */

public class MainQuestionActivity extends Activity {
    List<QuestionModel> quesList;
    int score = 0;
    int qid = 0;
    static int winingScore = 10;
    static int max = 20;
    static int min = 1;
    QuestionModel currentQ;
    TextView txtQuestion, scored;
    Button button1, button2, button3;
    Random random;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GameHelper db = new GameHelper(this);
        quesList = db.getAllQuestions();
        random = new Random();
        qid = random.nextInt((max - min) + 1) + 1;
        Log.d("xxx", "onCreate: "+qid);
        currentQ = quesList.get(qid);

        txtQuestion = (TextView) findViewById(R.id.txtQuestion);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        scored = (TextView) findViewById(R.id.score);

        setQuestionView();

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                getAnswer(button1.getText().toString());
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAnswer(button2.getText().toString());
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAnswer(button3.getText().toString());
            }
        });
    }

    public void getAnswer(String AnswerString) {

        if (currentQ.getANSWER().equals(AnswerString)) {
            score++;
            scored.setText("Score : " + score);
            if (score != winingScore) {
                qid = random.nextInt((max - min) + 1) + 1;
                Log.d("xxx", "onCreate: "+qid);

                currentQ = quesList.get(qid);
                setQuestionView();
            } else {

                Intent intent = new Intent(MainQuestionActivity.this, WinActivity.class);
                Bundle b = new Bundle();
                b.putInt("score",score);
                intent.putExtras(b);
                startActivity(intent);
                finish();
            }

        } else {

            Intent intent = new Intent(MainQuestionActivity.this,
                    LoseActivity.class);

            Bundle b = new Bundle();
            b.putInt("score", score);
            intent.putExtras(b);
            startActivity(intent);
            finish();
        }

    }

    private void setQuestionView() {

        // the method which will put all things together
        txtQuestion.setText(currentQ.getQUESTION());
        button1.setText(currentQ.getOPTA());
        button2.setText(currentQ.getOPTB());
        button3.setText(currentQ.getOPTC());

    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Close Math Game?")
                .setMessage("Are you sure you want to close the game?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }
}

