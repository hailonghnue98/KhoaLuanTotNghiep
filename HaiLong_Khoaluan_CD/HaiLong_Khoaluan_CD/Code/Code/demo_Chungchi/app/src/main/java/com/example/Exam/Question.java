package com.example.Exam;

import java.io.Serializable;

public class Question implements Serializable {
    private int id;
    private String question;
    private String ans_a;
    private String ans_b;
    private String ans_c;
    private String ans_d;
    private String result;
    private String image;
    private int module;
    private String subject;
    private int level;
    private String giaithich;

    private int choiceID = -1; //ho tro check id cua radioGroup
    private String answer = "";

    private int viTri;


    public Question() {
    }

//    public Question(int id, String question, String ans_a, String ans_b, String ans_c, String ans_d, String result, int id_de, String subject, String answer, String giaithich) {
//        this.id = id;
//        this.question = question;
//        this.ans_a = ans_a;
//        this.ans_b = ans_b;
//        this.ans_c = ans_c;
//        this.ans_d = ans_d;
//        this.result = result;
//        this.id_de = id_de;
//        this.subject = subject;
//        this.answer = answer;
//        this.giaithich = giaithich;
//    }


    public Question(int id, String question, String ans_a, String ans_b, String ans_c, String ans_d, String result, String image, int module, String subject, int level, String giaithich, String answer) {
        this.id = id;
        this.question = question;
        this.ans_a = ans_a;
        this.ans_b = ans_b;
        this.ans_c = ans_c;
        this.ans_d = ans_d;
        this.result = result;
        this.image = image;
        this.module = module;
        this.subject = subject;
        this.level = level;
        this.giaithich = giaithich;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAns_a() {
        return ans_a;
    }

    public void setAns_a(String ans_a) {
        this.ans_a = ans_a;
    }

    public String getAns_b() {
        return ans_b;
    }

    public void setAns_b(String ans_b) {
        this.ans_b = ans_b;
    }

    public String getAns_c() {
        return ans_c;
    }

    public void setAns_c(String ans_c) {
        this.ans_c = ans_c;
    }

    public String getAns_d() {
        return ans_d;
    }

    public void setAns_d(String ans_d) {
        this.ans_d = ans_d;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getModule() {
        return module;
    }

    public void setModule(int module) {
        this.module = module;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getGiaithich() {
        return giaithich;
    }

    public void setGiaithich(String giaithich) {
        this.giaithich = giaithich;
    }

    public int getChoiceID() {
        return choiceID;
    }

    public void setChoiceID(int choiceID) {
        this.choiceID = choiceID;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getViTri() {
        return viTri;
    }

    public void setViTri(int viTri) {
        this.viTri = viTri;
    }
}
