package com.example.model;

import java.io.Serializable;

public class MonHoc implements Serializable {
    private int image;
    private String name;
    private int question;

    public MonHoc() {
    }

    public MonHoc(int image, String name, int question) {
        this.image = image;
        this.name = name;
        this.question = question;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
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

    @Override
    public String toString() {
        return this.name;
    }
}
