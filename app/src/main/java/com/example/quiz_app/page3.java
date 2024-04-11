package com.example.quiz_app;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class page3 extends AppCompatActivity {

    EditText questionEditText, choice1EditText, choice2EditText, choice3EditText, choice4EditText;
    RadioGroup choicesRadioGroup;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        questionEditText = findViewById(R.id.questionEditText);
        choice1EditText = findViewById(R.id.choice1EditText);
        choice2EditText = findViewById(R.id.choice2EditText);
        choice3EditText = findViewById(R.id.choice3EditText);
        choice4EditText = findViewById(R.id.choice4EditText);
        choicesRadioGroup = findViewById(R.id.choicesRadioGroup);
        submitButton = findViewById(R.id.submitButton);


        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(page3.this, MainActivity.class);
                startActivity(intent);
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createQuiz();
            }
        });
    }


    private void createQuiz() {
        String question = questionEditText.getText().toString();
        String a = choice1EditText.getText().toString();
        String b = choice2EditText.getText().toString();
        String c = choice3EditText.getText().toString();
        String d = choice4EditText.getText().toString();

        int selectedChoiceId = choicesRadioGroup.getCheckedRadioButtonId();
        RadioButton selectedChoiceRadioButton = findViewById(selectedChoiceId);

        String precorrectChoice = selectedChoiceRadioButton.getText().toString();

        String correctChoice = "";

        if(precorrectChoice.equals("Choice 1")){
            correctChoice = a;
        } else if (precorrectChoice.equals("Choice 2")) {
            correctChoice = b;
        } else if (precorrectChoice.equals("Choice 3")) {
            correctChoice = c;
        }
        else if(precorrectChoice.equals("Choice 4")){
            correctChoice = d;
        }

        QuestionAnswer.question.add(question);
        QuestionAnswer.choices.add(new String[]{a, b, c, d});
        QuestionAnswer.correctAnswers.add(correctChoice);

        Toast.makeText(this, "Quiz added successfully!", Toast.LENGTH_SHORT).show();

        printQuestionAnswerContents();

        questionEditText.setText("");
        choice1EditText.setText("");
        choice2EditText.setText("");
        choice3EditText.setText("");
        choice4EditText.setText("");
        choicesRadioGroup.clearCheck();
    }

    private void printQuestionAnswerContents() {
        System.out.println("Current Questions:");
        for (String q : QuestionAnswer.question) {
            System.out.println(q);
        }

        System.out.println("Current Choices:");
        for (String[] c : QuestionAnswer.choices) {
            for (String choice : c) {
                System.out.print(choice + ", ");
            }
            System.out.println();
        }

        System.out.println("Current Correct Answers:");
        for (String ans : QuestionAnswer.correctAnswers) {
            System.out.println(ans);
        }
    }

}
