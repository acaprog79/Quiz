package com.alejandria.geoquiz;

/**
 * Created by 660253192 on 11/30/2017.
 */

public class Question {
    private int mTextResId;
    private boolean mAnswerTrue;

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public Question(int mTextResId, boolean mAnswerTrue){
        mTextResId = mTextResId;
        mAnswerTrue = mAnswerTrue;
    }
}
