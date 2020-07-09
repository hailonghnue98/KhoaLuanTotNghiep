package com.example.model;

import android.content.Intent;

import java.io.Serializable;

public class DeThi implements Serializable {
    private int id;
    private String name;
    private String result;
    private int question;
    private int right;
    private int wrong;
    private int percent;
    private double mark;

    public DeThi() {
    }

    public DeThi(int id, String name, int right, int wrong) {
        this.id = id;
        this.name = name.toUpperCase();
        this.right = right;
        this.wrong = wrong;
        this.question = right + wrong;
        this.percent = (int)((double)this.right/(double)this.question*100);
        if(wrong > right)
        {
            result = "FAILED";
        }
        else
        {
            result = "PASS";
        }
    }

    public DeThi(int id, String name, String result, int question, int right, int wrong, double mark) {
        this.id = id;
        this.name = name;
        this.result = result;
        this.question = question;
        this.right = right;
        this.wrong = wrong;
        this.mark = mark;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuestion() {
        return question;
    }

    public void setQuestion(int question) {
        this.question = question;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public int getWrong() {
        return wrong;
    }

    public void setWrong(int wrong) {
        this.wrong = wrong;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
