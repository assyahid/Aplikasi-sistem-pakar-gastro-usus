package com.faza.project.expertsystemai.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.faza.project.expertsystemai.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnStart = (Button) findViewById(R.id.btn_start);
        btnStart.setOnClickListener(new StartClickListener());
    }

    private class StartClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent questionIntent = new Intent(MainActivity.this, QuestionActivity.class);
            startActivity(questionIntent);
        }
    }
}