package com.example.demo_chungchi;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.Exam.DBHelper;
import com.example.Exam.Exam;
import com.example.Exam.ExamAdapter;
import com.example.Exam.ExamController;
import com.example.Slide.ScreenSlideActivity;
import com.example.model.DeThi;
import com.example.model.DeThiAdapter;

import java.io.IOException;
import java.util.ArrayList;

public class ThiActivity extends AppCompatActivity {

    private DBHelper dbHelper = new DBHelper(this);

    private ListView livDeThi;
    private DeThiAdapter adapter;
    private ImageView imgBack;

    private GridView gvDethi;
    private ExamAdapter examAdapter;
    private ArrayList<Exam> exams;
    private ExamController examController;

    private String lamLai;
    private int vitride = 0;
    private Bundle bundle;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thi);
        setTitle("Thi thử");
        addViews();
        addEvents();
    }


    private void addEvents() {
        gvDethi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                bundle = new Bundle();
                bundle.putSerializable("exams", exams);
                vitride = i;
                bundle.putInt("vitride", vitride);
                bundle.putString("lamLai", lamLai);

                intent = new Intent(ThiActivity.this, ScreenSlideActivity.class);
                intent.putExtra("bundle", bundle);
                startActivityForResult(intent, 1);
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void addViews() {
        gvDethi = findViewById(R.id.gvDethi);
        imgBack = findViewById(R.id.imgBack);

        exams = new ArrayList<>();
        examController = new ExamController(this);
//        examController.updateExam(1, 9, 28, 2, 1);
        exams = examController.getExams();

//        addData();

//        addDeNgauNhien();
        examAdapter = new ExamAdapter(ThiActivity.this, exams);
        gvDethi.setAdapter(examAdapter);

        lamLai = "no";

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == 2)
        {
//            Bundle bundle = data.getBundleExtra("bundleExam");
//            exams = (ArrayList<Exam>) bundle.getSerializable("examsResult");

//            Bundle ketqua = bundle.getBundle("ketqua");
//            int id = ketqua.getInt("id", 0);
//            double score = ketqua.getDouble("score", 0);
//            int correct = ketqua.getInt("correct", 0);
//            int wrong = ketqua.getInt("wrong", 0);
//            int passed = ketqua.getInt("passed", 0);
//            bundle.remove("ketqua");
//            examController.updateExam(id, score, correct, wrong, passed);
            exams = examController.getExams();

            examAdapter = new ExamAdapter(ThiActivity.this, exams);
            gvDethi.setAdapter(examAdapter);
            //Toast.makeText(this, score + "", Toast.LENGTH_SHORT).show();
        }
    }


    public ArrayList<Exam> getExams()
    {
        return exams;
    }
}
