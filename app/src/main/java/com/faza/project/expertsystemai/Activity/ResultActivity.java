package com.faza.project.expertsystemai.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.faza.project.expertsystemai.Application.ExpertSystemAI;
import com.faza.project.expertsystemai.Model.DiseaseChart;
import com.faza.project.expertsystemai.Model.SymptomsChart;
import com.faza.project.expertsystemai.R;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {
    private ArrayList<CheckBox> checkBoxes;
    private boolean isHealty = true;
    private double THRESHOLD = 0.6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        checkBoxes = new ArrayList<>();

        checkBoxes.add((CheckBox) findViewById(R.id.cb_result1));
        checkBoxes.add((CheckBox) findViewById(R.id.cb_result2));
        checkBoxes.add((CheckBox) findViewById(R.id.cb_result3));
        checkBoxes.add((CheckBox) findViewById(R.id.cb_result4));
        checkBoxes.add((CheckBox) findViewById(R.id.cb_result5));

        calculateChild2();
        calculateChild();
        setCheckBoxes();
        setHealty();
    }

    private void calculateChild2() {
        ArrayList<SymptomsChart> symptomsCharts = ExpertSystemAI.getSymptoms();
        ArrayList<DiseaseChart> diseaseCharts = ExpertSystemAI.getChild2();

        ExpertSystemAI.setFalseChild2();

        for (DiseaseChart diseaseChart : diseaseCharts) {
            int trueTotal = 0;
            ArrayList<Integer> symptoms = diseaseChart.getSubBagan();

            for (Integer symptomsIndex : symptoms) {
                boolean isTrue = symptomsCharts.get(symptomsIndex).isTrue();

                if (isTrue)
                    trueTotal++;
            }

            double resultPercentage = (double) trueTotal / symptoms.size();

            if (resultPercentage >= THRESHOLD)
                diseaseChart.setTrue(true);

            diseaseChart.setPercentage(resultPercentage);
        }
    }

    private void calculateChild() {
        ArrayList<DiseaseChart> diseaseCharts = ExpertSystemAI.getChild();
        ArrayList<DiseaseChart> diseaseCharts2 = ExpertSystemAI.getChild2();

        ExpertSystemAI.setFalseChild();

        for (DiseaseChart diseaseChart : diseaseCharts) {
            int trueTotal = 0;
            ArrayList<Integer> symptoms = diseaseChart.getSubBagan();

            for (Integer symptomsIndex : symptoms) {
                boolean isTrue = diseaseCharts2.get(symptomsIndex).isTrue();

                if (isTrue)
                    trueTotal++;
            }

            double resultPercentage = (double) trueTotal / symptoms.size();

            if (resultPercentage >= THRESHOLD)
                diseaseChart.setTrue(true);

            diseaseChart.setPercentage(resultPercentage);
        }
    }

    private void setCheckBoxes() {
        ArrayList<DiseaseChart> diseaseCharts = ExpertSystemAI.getChild();

        for (int index = 0; index < diseaseCharts.size(); index++) {
            String percentage = String.valueOf((int) (diseaseCharts.get(index).getPercentage() * 100));

            checkBoxes.get(index).setText(diseaseCharts.get(index).getName() + " : " + percentage + "%");
            checkBoxes.get(index).setClickable(false);

            if (diseaseCharts.get(index).isTrue()) {
                isHealty = false;
                checkBoxes.get(index).setChecked(true);
            }
        }
    }

    private void setHealty() {
        TextView tvResult = (TextView) findViewById(R.id.tv_result);

        if (isHealty)
            tvResult.setVisibility(View.VISIBLE);
    }
}