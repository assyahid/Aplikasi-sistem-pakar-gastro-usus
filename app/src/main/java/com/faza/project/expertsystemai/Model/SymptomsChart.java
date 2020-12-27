package com.faza.project.expertsystemai.Model;

/**
 * Dibuat oleh Faza Zulfika Permana Putra
 * Tanggal 9 Mei 2017
 */

public class SymptomsChart {
    private boolean isTrue;
    private String name, question;

    public SymptomsChart() {
        this(null);
    }

    public SymptomsChart(String name) {
        this(name, null);
    }

    public SymptomsChart(String name, String question) {
        this.name = name;
        this.question = question;
        this.isTrue = false;
    }

    public boolean isTrue() {
        return isTrue;
    }

    public void setTrue(boolean aTrue) {
        isTrue = aTrue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}