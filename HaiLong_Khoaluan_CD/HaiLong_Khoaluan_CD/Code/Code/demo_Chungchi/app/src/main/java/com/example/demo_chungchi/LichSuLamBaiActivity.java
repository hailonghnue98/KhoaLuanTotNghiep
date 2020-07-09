package com.example.demo_chungchi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Exam.Exam;
import com.example.Exam.ExamAdapter;
import com.example.Exam.ExamController;

import java.util.ArrayList;

public class LichSuLamBaiActivity extends AppCompatActivity {

    private GridView gvDethi;
    private ExamAdapter examAdapter;
    private ArrayList<Exam> exams;
    private ExamController examController;

    private ImageView imgBack;
    private int maDe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su_lam_bai);
        addViews();
        addEvents();
    }

    private void addEvents() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        gvDethi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(LichSuLamBaiActivity.this, exams.get(position).getMade() + "", Toast.LENGTH_SHORT).show();
                maDe = exams.get(position).getMade();
                if(maDe != 1000)
                {
                    Intent intent = new Intent(getApplicationContext(), CTLichSuLamBaiActivity.class);
                    intent.putExtra("maDe", maDe);
                    startActivity(intent);
                }
            }
        });
    }

    private void addViews() {
        imgBack = findViewById(R.id.imgBack);

        gvDethi = findViewById(R.id.gvDethi);
        examController = new ExamController(this);
        exams = examController.getExams();
        examAdapter = new ExamAdapter(this, exams);
        gvDethi.setAdapter(examAdapter);
    }
}
