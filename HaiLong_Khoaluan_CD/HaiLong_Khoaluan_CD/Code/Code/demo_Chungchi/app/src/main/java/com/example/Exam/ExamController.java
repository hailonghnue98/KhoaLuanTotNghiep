package com.example.Exam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExamController {
    private DBHelper dbHelper;

    public ExamController(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void insertExam(int maModule, String ngayThi, int cauDung, int cauSai, int cauChualam, int diemSo, int tgLam, int ketQua)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("mamodule", maModule);
        contentValues.put("ngaythi", ngayThi);
        contentValues.put("caudung", cauDung);
        contentValues.put("causai", cauSai);
        contentValues.put("cauchualam", cauChualam);
        contentValues.put("diemso", diemSo);
        contentValues.put("thoigianlam", tgLam);
        contentValues.put("ketqua", ketQua);
        db.insert("baithi", null, contentValues);
//        db.update("dekt", contentValues, "id = " + contentValues.get("id"), null);
        db.close();
    }

    public int maxIndex()
    {
        int maxIndex = 0;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT max(made) FROM baithi", null);
        cursor.moveToFirst();
        do {
            maxIndex = cursor.getInt(0);
        }while (cursor.moveToNext());
        return maxIndex;
    }

    public void insertBaiThi(int maDe, int maCauhoi, String ndCauhoi, String cauA, String cauB, String cauC, String cauD, String dapAn, String anh, int maModule, String chuDe, int level, String giaiThich, String luaChon)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("made", maDe);
        contentValues.put("macauhoi", maCauhoi);
        contentValues.put("noidungcauhoi", ndCauhoi);
        contentValues.put("cauhoi_a", cauA);
        contentValues.put("cauhoi_b", cauB);
        contentValues.put("cauhoi_c", cauC);
        contentValues.put("cauhoi_d", cauD);
        contentValues.put("dapan", dapAn);
        contentValues.put("anh", anh);
        contentValues.put("mamodule", maModule);
        contentValues.put("chude", chuDe);
        contentValues.put("level", level);
        contentValues.put("giaithich", giaiThich);
        contentValues.put("luachon", luaChon);
        db.insert("chitietbaithi", null, contentValues);

        db.close();
    }

    public String test()
    {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String result;
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM chitietbaithi where made = 6", null);
        cursor.moveToFirst();
        do {
            result = cursor.getString(0);
        }while(cursor.moveToNext());
        db.close();
        return result;
    }

    public ArrayList<Exam> getExams()
    {
        ArrayList<Exam> listExam = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM baithi order by made desc", null);
        cursor.moveToFirst();
        do{
            Exam exam;
            exam = new Exam(cursor.getInt(0), cursor.getInt(1), cursor.getString(2) , cursor.getInt(3), cursor.getInt(4), cursor.getInt(5), cursor.getDouble(6), cursor.getInt(7), cursor.getInt(8));
            listExam.add(exam);
        }while (cursor.moveToNext());

        return listExam;
    }

    public Exam getExams(String maDe)
    {
        Exam exam;

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM baithi WHERE made = '" + maDe + "'", null);
        cursor.moveToFirst();
        do{
            exam = new Exam(cursor.getInt(0), cursor.getInt(1), cursor.getString(2) , cursor.getInt(3), cursor.getInt(4), cursor.getInt(5), cursor.getDouble(6), cursor.getInt(7), cursor.getInt(8));
        }while (cursor.moveToNext());

        return exam;
    }
}
