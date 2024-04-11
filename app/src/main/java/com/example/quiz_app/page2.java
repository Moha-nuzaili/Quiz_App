package com.example.quiz_app;

import android.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

public class page2 extends AppCompatActivity implements View.OnClickListener {
    TextView questionTextView;
    TextView totalQuestionTextView;
    Button ansA, ansB, ansC, ansD;
    Button btn_submit;

    int score = 0;
    private QuestionAnswer questionsAnswer;
    int totalQuestion;

//    int min = 0;
//    int max = 4;
//    int randomInt = (int)(Math.random() * (max - min + 1) + min);
    int currentQuestionIndex = 0;
    String selectedAnswer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        questionsAnswer = new QuestionAnswer();
        totalQuestion = questionsAnswer.question.size();

        totalQuestionTextView = findViewById(R.id.total_question);
        questionTextView = findViewById(R.id.question);
        ansA = findViewById(R.id.ans_a);
        ansB = findViewById(R.id.ans_b);
        ansC = findViewById(R.id.ans_c);
        ansD = findViewById(R.id.ans_d);
        btn_submit = findViewById(R.id.btn_submit);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        btn_submit.setOnClickListener(this);

        totalQuestionTextView.setText("Total question: " + totalQuestion);

        loadNewQuestion();
    }

    private void loadNewQuestion() {
        if (currentQuestionIndex == totalQuestion) {
            finishQuiz();
            return;
        }
        questionTextView.setText(questionsAnswer.question.get(currentQuestionIndex));
        ansA.setText(questionsAnswer.choices.get(currentQuestionIndex)[0]);
        ansB.setText(questionsAnswer.choices.get(currentQuestionIndex)[1]);
        ansC.setText(questionsAnswer.choices.get(currentQuestionIndex)[2]);
        ansD.setText(questionsAnswer.choices.get(currentQuestionIndex)[3]);

        selectedAnswer = "";
    }

    private void finishQuiz() {
        String passStatus;
        if (score >= totalQuestion * 0.6) {
            passStatus = "Passed";
        } else {
            passStatus = "Failed";
        }

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(passStatus)
                .setMessage("Your Score is " + score + " Out of " + totalQuestion)
                .setPositiveButton("Restart", ((dialog, i) -> restartQuiz()))
                .setNegativeButton("Return to Main Menu", ((dialog, i) -> {
                    Intent intent = new Intent(page2.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }))
                .setCancelable(false)
                .show();
    }


    private void restartQuiz() {
        score = 0;
        currentQuestionIndex = 0;
        loadNewQuestion();
    }

    @Override
    public void onClick(View view) {
        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);

        Button clickedButton = (Button) view;

        if (clickedButton.getId() == R.id.btn_submit) {
            if (!selectedAnswer.isEmpty()) {
                System.out.println(selectedAnswer);
                System.out.println(QuestionAnswer.correctAnswers.get(currentQuestionIndex));
                if (selectedAnswer.equals(QuestionAnswer.correctAnswers.get(currentQuestionIndex))) {
                    score++;
                } else {
                    clickedButton.setBackgroundColor(Color.MAGENTA);
                }
                currentQuestionIndex++;
                loadNewQuestion();
            } else {
                // Handle case where no answer is selected
            }
        } else {
            selectedAnswer = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.YELLOW);
        }
    }
}
