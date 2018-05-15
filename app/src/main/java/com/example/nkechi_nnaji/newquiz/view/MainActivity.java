package com.example.nkechi_nnaji.newquiz.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nkechi_nnaji.newquiz.R;
import com.example.nkechi_nnaji.newquiz.model.Question;

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

    private static final String TAG = "MainActivity";

    /**
     * Key-value pair for storing state in Bundle
     */

    private static final String KEY_INDEX = "index";

    /**
     * Score tab
     */

    private int score = 0;

    /**
     * Total score possible
     */

    private int possibleScore = mQuestionBank.length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate(Bundle) called");

        //Save the value of savedInstanceState to mCurrentIndex
        //onCreate will pick the value each time an activity is destroyed and
        //recreated.
        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }



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
            mTrueButton.setEnabled(false);
            mFalseButton.setEnabled(false);
        });

        mFalseButton.setOnClickListener((View view) -> {
            checkAnswer(false);
            mFalseButton.setEnabled(false);
            mTrueButton.setEnabled(false);
        });

        mNextButton.setOnClickListener((View view) -> {
            nextQuestion();
        });

        mPrevButton.setOnClickListener((View view) -> {

            previousQuestion();
        });

        upDateQuestion();

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
        mTrueButton.setEnabled(true);
        mFalseButton.setEnabled(true);
        mNextButton.setEnabled(true);
        mPrevButton.setEnabled(false);


    }


    private void checkAnswer(boolean userPressedTrue) {

        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue(); //Answer of current question

        int messageResId = 0;
        score = 0;

        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast; // Recall resource ID of a string resource is an int
            score++;
        } else {
            messageResId = R.string.wrong_toast; // Recall resource ID of a string resource is an int
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
                .show();

    }


    @Override
    public void onStart() {
        super.onStart();

        Log.d(TAG, "onStart() called");

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    /**
     * onSaveInstanceState callback for writing the value of mCurrentIndex to the
     * bundle with the constant as its key.
     * This would be read back to onCreate(Bundle)
     *
     * @param savedInstanceState
     */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

}

