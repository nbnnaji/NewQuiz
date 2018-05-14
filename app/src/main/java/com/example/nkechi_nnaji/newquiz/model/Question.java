package com.example.nkechi_nnaji.newquiz.model;

public class Question {
    /**
     *
     * @return mTextResId
     */

    public int getTextResId() {
        return mTextResId;
    }

    /**
     *
     * @param textResId
     */
    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    /**
     *
     * @return mAnswerTrue
     */
    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    /**
     *
     * @param answerTrue
     */
    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }

    /**
     * mTextResId is an int because it will hold the resource ID (Always an int)
     * of a string resource for the question.
     *
     */
    private int mTextResId;
    private boolean mAnswerTrue;

    public Question(int mTextResId, boolean mAnswerTrue) {
        this.mTextResId = mTextResId;
        this.mAnswerTrue = mAnswerTrue;
    }
}
