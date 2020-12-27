package com.faza.project.expertsystemai.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.faza.project.expertsystemai.Application.ExpertSystemAI;
import com.faza.project.expertsystemai.Model.SymptomsChart;
import com.faza.project.expertsystemai.R;

public class QuestionActivity extends AppCompatActivity {
    private int symptomsIndex, activeSymptomsIndex;
    private int TOTAL_QUESTION = ExpertSystemAI.getSymptoms().size();
    private ImageButton ibtnBack, ibtnForward;
    private RelativeLayout rlPreviousAnswer;
    private TextView tvStep, tvPreviousAnswer, tvQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        ibtnBack = (ImageButton) findViewById(R.id.ibtn_back);
        ibtnForward = (ImageButton) findViewById(R.id.ibtn_forward);

        ibtnBack.setOnClickListener(new BackForwardClickListener());
        ibtnForward.setOnClickListener(new BackForwardClickListener());

        Button btnYes = (Button) findViewById(R.id.btn_yes);
        Button btnNo = (Button) findViewById(R.id.btn_no);

        btnYes.setOnClickListener(new AnswerClickListener());
        btnNo.setOnClickListener(new AnswerClickListener());

        rlPreviousAnswer = (RelativeLayout) findViewById(R.id.rl_previous_answer);
        tvStep = (TextView) findViewById(R.id.tv_step);
        tvPreviousAnswer = (TextView) findViewById(R.id.tv_previous_answer);
        tvQuestion = (TextView) findViewById(R.id.tv_question);
        tvQuestion.setOnTouchListener(new OnSwipeTouchListener());

        setQuestion();
    }

    private void setQuestion() {
        if (symptomsIndex == TOTAL_QUESTION) {
            Intent resultIntent = new Intent(QuestionActivity.this, ResultActivity.class);
            startActivity(resultIntent);

            finish();
        } else {
            String question = ExpertSystemAI.getSymptoms().get(activeSymptomsIndex).getQuestion();

            if (activeSymptomsIndex < symptomsIndex) {
                String previousAnswer;
                boolean isTrue = ExpertSystemAI.getSymptoms().get(activeSymptomsIndex).isTrue();

                if (isTrue)
                    previousAnswer = "Yes";
                else
                    previousAnswer = "No";

                rlPreviousAnswer.setVisibility(View.VISIBLE);
                tvPreviousAnswer.setText(previousAnswer);
            } else
                rlPreviousAnswer.setVisibility(View.GONE);

            if (activeSymptomsIndex == 0)
                ibtnBack.setVisibility(View.INVISIBLE);
            else
                ibtnBack.setVisibility(View.VISIBLE);

            if (activeSymptomsIndex == symptomsIndex)
                ibtnForward.setVisibility(View.INVISIBLE);
            else
                ibtnForward.setVisibility(View.VISIBLE);

            tvStep.setText((activeSymptomsIndex + 1) + " / " + TOTAL_QUESTION);
            tvQuestion.setText(question);
        }
    }

    private class BackForwardClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            int id = v.getId();

            switch (id) {
                case R.id.ibtn_back:
                    if (activeSymptomsIndex > 0)
                        activeSymptomsIndex--;
                    break;
                case R.id.ibtn_forward:
                    if (activeSymptomsIndex < symptomsIndex)
                        activeSymptomsIndex++;
                    break;
            }

            setQuestion();
        }
    }

    private class OnSwipeTouchListener implements View.OnTouchListener {
        private final GestureDetector gestureDetector;

        private OnSwipeTouchListener() {
            gestureDetector = new GestureDetector(QuestionActivity.this, new GestureListener());
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return gestureDetector.onTouchEvent(event);
        }

        private final class GestureListener extends GestureDetector.SimpleOnGestureListener {
            private static final int SWIPE_THRESHOLD = 100;
            private static final int SWIPE_VELOCITY_THRESHOLD = 100;

            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                boolean result = false;

                try {
                    float diffY = e2.getY() - e1.getY();
                    float diffX = e2.getX() - e1.getX();
                    if (Math.abs(diffX) > Math.abs(diffY)) {
                        if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                            if (diffX > 0) {
                                onSwipeRight();
                            } else {
                                onSwipeLeft();
                            }
                            result = true;
                        }
                    }
                    else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffY > 0) {
                            onSwipeBottom();
                        } else {
                            onSwipeTop();
                        }
                        result = true;
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

                return result;
            }
        }

        private void onSwipeRight() {
            if (activeSymptomsIndex > 0)
                activeSymptomsIndex--;

            setQuestion();
        }

        private void onSwipeLeft() {
            if (activeSymptomsIndex < symptomsIndex)
                activeSymptomsIndex++;

            setQuestion();
        }

        private void onSwipeTop() {
            // Do nothing
        }

        private void onSwipeBottom() {
            // Do nothing
        }
    }

    private class AnswerClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            int id = v.getId();

            SymptomsChart symptoms = ExpertSystemAI.getSymptoms().get(activeSymptomsIndex);

            switch (id) {
                case R.id.btn_yes:
                    symptoms.setTrue(true);
                    break;
                case R.id.btn_no:
                    symptoms.setTrue(false);
                    break;
            }

            if (activeSymptomsIndex == symptomsIndex)
                symptomsIndex++;

            activeSymptomsIndex++;
            setQuestion();
        }
    }
}