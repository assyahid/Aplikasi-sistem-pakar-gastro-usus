package com.faza.project.expertsystemai.Application;

import android.app.Application;
import android.content.res.Resources;

import com.faza.project.expertsystemai.Model.SymptomsChart;
import com.faza.project.expertsystemai.Model.DiseaseChart;
import com.faza.project.expertsystemai.R;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Dibuat oleh Faza Zulfika Permana Putra
 * Tanggal 9 Mei 2017
 */

public class ExpertSystemAI extends Application {
    private static DiseaseChart root;
    private static ArrayList<DiseaseChart> child, child2;
    private static ArrayList<SymptomsChart> symptoms;

    private static String ROOT_NAME;
    private static String[] CHILD_NAME, CHILD2_NAME, SYMPTOMS, SYMPTOMS_QUESTIONS;

    @Override
    public void onCreate() {
        super.onCreate();

        Resources resources = getApplicationContext().getResources();

        ROOT_NAME = getString(R.string.root);
        CHILD_NAME = resources.getStringArray(R.array.child);
        CHILD2_NAME = resources.getStringArray(R.array.child2);
        SYMPTOMS = resources.getStringArray(R.array.symptoms);
        SYMPTOMS_QUESTIONS = resources.getStringArray(R.array.symptoms_questions);

        setSymptoms();
        setChild2();
        setChild();
        setRoot();
    }

    private void setSymptoms() {
        int total = SYMPTOMS.length;
        symptoms = new ArrayList<>();

        for (int index = 0; index < total; index++) {
            SymptomsChart symptomsChart = new SymptomsChart();

            symptomsChart.setName(SYMPTOMS[index]);
            symptomsChart.setQuestion(SYMPTOMS_QUESTIONS[index]);

            symptoms.add(symptomsChart);
        }
    }

    private void setChild2() {
        int total = CHILD2_NAME.length;
        child2 = new ArrayList<>();

        Integer disease1[] = { 0, 1, 3, 4 };
        Integer disease2[] = { 3, 4, 5 };
        Integer disease3[] = { 3, 6 };
        Integer disease4[] = { 3, 7, 8 };
        Integer disease5[] = { 7, 9 };
        Integer disease6[] = { 3, 4, 8, 10 };
        Integer disease7[] = { 3, 7, 10, 11 };
        Integer disease8[] = { 3, 12 };
        Integer disease9[] = { 0, 1, 2, 3, 4 };
        Integer disease10[] = { 13, 14 };
        Integer disease11[] = { 13, 15 };
        Integer disease12[] = { 13, 16 };
        Integer disease13[] = { 17, 18 };

        for (int index = 0; index < total; index++) {
            Integer disease[] = null;
            DiseaseChart diseaseChart = new DiseaseChart();

            switch (index) {
                case 0:
                    disease = disease1;
                    break;
                case 1:
                    disease = disease2;
                    break;
                case 2:
                    disease = disease3;
                    break;
                case 3:
                    disease = disease4;
                    break;
                case 4:
                    disease = disease5;
                    break;
                case 5:
                    disease = disease6;
                    break;
                case 6:
                    disease = disease7;
                    break;
                case 7:
                    disease = disease8;
                    break;
                case 8:
                    disease = disease9;
                    break;
                case 9:
                    disease = disease10;
                    break;
                case 10:
                    disease = disease11;
                    break;
                case 11:
                    disease = disease12;
                    break;
                case 12:
                    disease = disease13;
                    break;
            }

            ArrayList<Integer> diseaseList = new ArrayList<>(Arrays.asList(disease));

            diseaseChart.setName(CHILD2_NAME[index]);
            diseaseChart.setSubBagan(diseaseList);

            child2.add(diseaseChart);
        }
    }

    private void setChild() {
        int total = CHILD_NAME.length;
        child = new ArrayList<>();

        Integer disease1[] = { 0, 1, 2, 3, 9 };
        Integer disease2[] = { 0, 1, 2, 4, 10 };
        Integer disease3[] = { 0, 1, 2, 5, 6, 9 };
        Integer disease4[] = { 1, 7, 11 };
        Integer disease5[] = { 2, 5, 8, 12 };

        for (int index = 0; index < total; index++) {
            Integer disease[] = null;
            DiseaseChart diseaseChart = new DiseaseChart();

            switch (index) {
                case 0:
                    disease = disease1;
                    break;
                case 1:
                    disease = disease2;
                    break;
                case 2:
                    disease = disease3;
                    break;
                case 3:
                    disease = disease4;
                    break;
                case 4:
                    disease = disease5;
                    break;
            }

            ArrayList<Integer> diseaseList = new ArrayList<>(Arrays.asList(disease));

            diseaseChart.setName(CHILD_NAME[index]);
            diseaseChart.setSubBagan(diseaseList);

            child.add(diseaseChart);
        }
    }

    private void setRoot() {
        Integer disease[] = { 0, 1, 2, 3, 4 };
        ArrayList<Integer> diseaseList = new ArrayList<>(Arrays.asList(disease));

        root = new DiseaseChart();
        root.setName(ROOT_NAME);
        root.setSubBagan(diseaseList);
    }

    public static DiseaseChart getRoot() {
        return root;
    }

    public static ArrayList<DiseaseChart> getChild() {
        return child;
    }

    public static ArrayList<DiseaseChart> getChild2() {
        return child2;
    }

    public static ArrayList<SymptomsChart> getSymptoms() {
        return symptoms;
    }

    public static String getRootName() {
        return ROOT_NAME;
    }

    public static void setFalseChild() {
        for (DiseaseChart diseaseChart : child)
            diseaseChart.setTrue(false);
    }

    public static void setFalseChild2() {
        for (DiseaseChart diseaseChart : child2)
            diseaseChart.setTrue(false);
    }
}