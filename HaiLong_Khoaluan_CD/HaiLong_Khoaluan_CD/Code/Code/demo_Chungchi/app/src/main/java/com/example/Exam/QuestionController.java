package com.example.Exam;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class QuestionController {
    private DBHelper dbHelper;

    public QuestionController(Context context) {
        dbHelper = new DBHelper(context);
    }

    public ArrayList<Question> getQuestions(String subject, int limit)
    {
        ArrayList<Question> listQuestion = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM cauhoi WHERE subject = '" + subject + "' ORDER BY RANDOM() LIMIT " + limit, null);
        cursor.moveToFirst();
        do{
            Question item;
            item = new Question(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getInt(8), cursor.getString(9), cursor.getInt(10), cursor.getString(11), "");
            listQuestion.add(item);
        }while (cursor.moveToNext());
        return listQuestion;
    }

    public ArrayList<Question> getQuestions(String subject, int limit, int level)
    {
        ArrayList<Question> listQuestion = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM cauhoi WHERE subject = '" + subject + "' and level = " + level + " ORDER BY RANDOM() LIMIT " + limit, null);
        cursor.moveToFirst();
        do{
            Question question;
            question = new Question(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getInt(8), cursor.getString(9), cursor.getInt(10), cursor.getString(11), "");
            listQuestion.add(question);
        }while (cursor.moveToNext());
        return listQuestion;
    }

    public ArrayList<Question> getQuestionsNoRandom(String subject, int limit)
    {
        ArrayList<Question> listQuestion = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM cauhoi WHERE subject = '" + subject + "' ORDER BY id LIMIT " + limit, null);
        cursor.moveToFirst();
        do{
            Question item;
//            item = new Question(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getInt(7), cursor.getString(8), "", cursor.getString(9));
            item = new Question(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getInt(8), cursor.getString(9), cursor.getInt(10), cursor.getString(11), "");
            listQuestion.add(item);
        }while (cursor.moveToNext());
        return listQuestion;
    }

    public ArrayList<Question> getQuestionsCTBT(String made)
    {
        ArrayList<Question> listQuestion = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT macauhoi, noidungcauhoi, cauhoi_a, cauhoi_b, cauhoi_c, cauhoi_d, dapan, anh, mamodule, chude, level, giaithich, luachon FROM chitietbaithi WHERE made = '" + made + "' LIMIT " + 50, null);
        cursor.moveToFirst();
        do{
            Question item;
            item = new Question(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getInt(8), cursor.getString(9), cursor.getInt(10), cursor.getString(11), cursor.getString(12));
            listQuestion.add(item);
        }while (cursor.moveToNext());
        return listQuestion;
    }
}
