package com.example.nkechi_nnaji.newquiz.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nkechi_nnaji.newquiz.R;
import com.example.nkechi_nnaji.newquiz.model.Question;

import static com.example.nkechi_nnaji.newquiz.R.string.correct_toast;

public class MainActivity extends AppCompatActivity {

    /**
     * True Button
     */
    private Button mTrueButton;
    /**
     * False Button
     */
    private Button mFalseButton;
    /**
     * Next Button
     */
    private ImageButton mNextButton;
    /**
     * Previous Button
     */
    private ImageButton mPrevButton;
    /**
     * Question TextView
     */
    private TextView mQuestionTextView;

    /**
     * Holds the current question index
     */
    private int mCurrentIndex = 0;

    /**
     * Questions Array
     * Recall constructor with 2 params (int & boolean) in Question model class
     */
    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTrueButton = findViewById(R.id.true_button);
        mFalseButton = findViewById(R.id.false_button);
        mNextButton = findViewById(R.id.next_button);
        mPrevButton = findViewById(R.id.prev_button);
        mQuestionTextView = findViewById(R.id.question_text_view);

        /**
         * Click event for True, False & Next button using Lambda
         * Set module setting to language 1.8
         */
        mTrueButton.setOnClickListener((View view) -> {

            checkAnswer(true);
        });

        mFalseButton.setOnClickListener((View view) -> {
            checkAnswer(false);
        });

        mNextButton.setOnClickListener((View view) -> {
            nextQuestion();
        });

        mPrevButton.setOnClickListener((View view) -> {

            previousQuestion();
        });
    }

    /**
     * Function for next button
     */

    private void nextQuestion() {

        mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;//Iterating through the array of questions
        upDateQuestion();
    }


    /**
     * Function for previous button
     */

    private void previousQuestion() {

        if (mCurrentIndex > 0) {

            mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;//Iterating through the array of questions
            upDateQuestion();
        } else {
            mCurrentIndex = (mQuestionBank.length - 1);
            upDateQuestion();
        }
    }

    /**
     * Function to update Questions
     */

    private void upDateQuestion() {

        int question = mQuestionBank[mCurrentIndex].getTextResId(); // Assign to question value of current index
        mQuestionTextView.setText(question);// Set the textview with question value
    }


    private void checkAnswer(boolean userPressedTrue) {

        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue(); //Answer of current question

        int messageResId = 0;

        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast; // Recall resource ID of a string resource is an int
        } else {
            messageResId = R.string.wrong_toast; // Recall resource ID of a string resource is an int
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
                .show();

    }

}

