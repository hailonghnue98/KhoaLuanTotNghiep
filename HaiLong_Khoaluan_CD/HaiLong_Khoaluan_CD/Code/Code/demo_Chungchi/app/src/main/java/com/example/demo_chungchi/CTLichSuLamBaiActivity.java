package com.example.demo_chungchi;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Exam.Exam;
import com.example.Exam.ExamController;
import com.example.Exam.Question;
import com.example.Exam.QuestionAdapter;
import com.example.Exam.QuestionController;
import com.example.Slide.LichSuBaiThiScreenSlideActivity;
import com.example.model.TabCauHoiAdapter;

import java.util.ArrayList;

public class CTLichSuLamBaiActivity extends AppCompatActivity {

    private TextView tvTongDiem, tvTime, tvModule, tvMade, tvKetqua;
    private TabHost tabHostKQBaiThi;
    private ImageView imgBack;
    private GridView gvTatcacauhoi, gvCaudung, gvCausai, gvCauchualam;
    private TabCauHoiAdapter tabCauHoiAdapter;

    private ArrayList<Question> questions, cauDung, cauSai, cauChuaLam;
    private QuestionController questionController;
    private QuestionAdapter questionAdapter;
    private ExamController examController;
    private Exam currentExam;
    private int maDe;
    private Intent intent;
    private Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ctlich_su_lam_bai);
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

        gvTatcacauhoi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(CTLichSuLamBaiActivity.this, questions.get(position).getViTri() + "-" + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), LichSuBaiThiScreenSlideActivity.class);
                bundle.putSerializable("currentExam", currentExam);
                bundle.putInt("vitriCauHoi", position);
                bundle.putSerializable("questions", questions);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            }
        });

        gvCaudung.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), LichSuBaiThiScreenSlideActivity.class);
                bundle.putSerializable("currentExam", currentExam);
                bundle.putInt("vitriCauHoi", (cauDung.get(position).getViTri() - 1));
                bundle.putSerializable("questions", questions);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            }
        });

        gvCausai.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), LichSuBaiThiScreenSlideActivity.class);
                bundle.putSerializable("currentExam", currentExam);
                bundle.putInt("vitriCauHoi", (cauSai.get(position).getViTri() - 1));
                bundle.putSerializable("questions", questions);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            }
        });

        gvCauchualam.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), LichSuBaiThiScreenSlideActivity.class);
                bundle.putSerializable("currentExam", currentExam);
                bundle.putInt("vitriCauHoi", (cauChuaLam.get(position).getViTri() - 1));
                bundle.putSerializable("questions", questions);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            }
        });
    }

    private void addViews() {
        intent = getIntent();

        tvTongDiem = findViewById(R.id.tvTongDiem);
        tvTime = findViewById(R.id.tvTime);
        tvModule = findViewById(R.id.tvModule);
        tvMade = findViewById(R.id.tvMade);
        tvKetqua = findViewById(R.id.tvKetqua);

        imgBack = findViewById(R.id.imgBack);

        tabHostKQBaiThi = findViewById(R.id.tabHostKQBaiThi);

        maDe = intent.getIntExtra("maDe", 0);

        questionController = new QuestionController(getApplicationContext());
        questions = questionController.getQuestionsCTBT(maDe + "");

        cauDung = new ArrayList<>();
        cauSai = new ArrayList<>();
        cauChuaLam = new ArrayList<>();

        for(int i = 0; i < questions.size(); i++)
        {
            questions.get(i).setViTri(i+1);
        }

        for(int i = 0; i < questions.size(); i++)
        {
            String dapAn = questions.get(i).getResult();
            String luaChon = questions.get(i).getAnswer();
            if(dapAn.equals(luaChon))
            {
                cauDung.add(questions.get(i));
            }
            else
            {
                if(!luaChon.equals(""))
                {
                    cauSai.add(questions.get(i));
                }
                else
                {
                    cauChuaLam.add(questions.get(i));
                }
            }
        }

        tabHostKQBaiThi.setup();

        /*Create tab Tat ca cau hoi*/
        TabHost.TabSpec tabSpec = tabHostKQBaiThi.newTabSpec("Tất cả");

        View view = LayoutInflater.from(this).inflate(R.layout.tab_title, null);
        LinearLayout bottomBorder = view.findViewById(R.id.bottomBorder);
        ImageView imgIcon = view.findViewById(R.id.imgIcon);
        TextView tvTitle = view.findViewById(R.id.tvTitle);

        imgIcon.setImageResource(R.drawable.allquestion);
        tvTitle.setText(questions.size() + "");
        tabSpec.setIndicator(view);

        tabSpec.setContent(R.id.kqbaithi_tab_all);
        tabHostKQBaiThi.addTab(tabSpec);
        /*End Create tab Tat ca cau hoi*/

        /*Create tab Cau Dung*/
        TabHost.TabSpec tabSpec2 = tabHostKQBaiThi.newTabSpec("Câu đúng");
        tabSpec2.setContent(R.id.kqbaithi_tab_caudung);

        view = LayoutInflater.from(this).inflate(R.layout.tab_title, null);

        bottomBorder = view.findViewById(R.id.bottomBorder);
        imgIcon = view.findViewById(R.id.imgIcon);
        tvTitle = view.findViewById(R.id.tvTitle);
        imgIcon.setImageResource(R.drawable.checked_mark);
        tvTitle.setText(cauDung.size() + "");
        tabSpec2.setIndicator(view);

        tabHostKQBaiThi.addTab(tabSpec2);
        /*End Create tab Cau Dung*/

        /*Create tab Cau Sai*/
        TabHost.TabSpec tabSpec3 = tabHostKQBaiThi.newTabSpec("Câu sai");
        tabSpec3.setContent(R.id.kqbaithi_tab_causai);

        view = LayoutInflater.from(this).inflate(R.layout.tab_title, null);

        bottomBorder = view.findViewById(R.id.bottomBorder);
        imgIcon = view.findViewById(R.id.imgIcon);
        tvTitle = view.findViewById(R.id.tvTitle);
        imgIcon.setImageResource(R.drawable.wrong_mark);
        tvTitle.setText(cauSai.size() + "");
        tabSpec3.setIndicator(view);

        tabHostKQBaiThi.addTab(tabSpec3);
        /*End Create tab Cau Sai*/

        /*Create tab Cau Chua Lam*/
        TabHost.TabSpec tabSpec4 = tabHostKQBaiThi.newTabSpec("Câu chưa làm");
        tabSpec4.setContent(R.id.kqbaithi_tab_cauchualam);

        view = LayoutInflater.from(this).inflate(R.layout.tab_title, null);

        bottomBorder = view.findViewById(R.id.bottomBorder);
        imgIcon = view.findViewById(R.id.imgIcon);
        tvTitle = view.findViewById(R.id.tvTitle);
        imgIcon.setImageResource(R.drawable.warning_mark);
        tvTitle.setText(cauChuaLam.size() + "");
        tabSpec4.setIndicator(view);

        tabHostKQBaiThi.addTab(tabSpec4);
        /*End Create tab Cau Chua Lam*/

        final int tabColor = Color.parseColor("#cce6ff");
        LinearLayout botBorder = tabHostKQBaiThi.getCurrentTabView().findViewById(R.id.bottomBorder);

        tabHostKQBaiThi.getTabWidget().getChildAt(0).setBackgroundColor(tabColor);
        botBorder.setVisibility(View.VISIBLE);
        tabHostKQBaiThi.getTabContentView().getChildAt(tabHostKQBaiThi.getCurrentTab()).setBackgroundColor(tabColor);

        tabHostKQBaiThi.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                Toast.makeText(CTLichSuLamBaiActivity.this, tabHostKQBaiThi.getCurrentTab() + "", Toast.LENGTH_SHORT).show();
                LinearLayout botBorder;

                for(int i = 0; i < tabHostKQBaiThi.getTabWidget().getChildCount(); i++)
                {
                    botBorder = tabHostKQBaiThi.getTabWidget().getChildAt(i).findViewById(R.id.bottomBorder);
                    botBorder.setVisibility(View.GONE);
                    tabHostKQBaiThi.getTabWidget().getChildAt(i).setBackgroundColor(Color.WHITE);
                    tabHostKQBaiThi.getTabContentView().getChildAt(tabHostKQBaiThi.getCurrentTab()).setBackgroundColor(Color.WHITE);
                }

                botBorder = tabHostKQBaiThi.getCurrentTabView().findViewById(R.id.bottomBorder);
                botBorder.setVisibility(View.VISIBLE);
                tabHostKQBaiThi.getTabWidget().getChildAt(tabHostKQBaiThi.getCurrentTab()).setBackgroundColor(tabColor);
                tabHostKQBaiThi.getTabContentView().getChildAt(tabHostKQBaiThi.getCurrentTab()).setBackgroundColor(tabColor);
            }
        });

        gvTatcacauhoi = findViewById(R.id.gvTatcacauhoi);
        gvCaudung = findViewById(R.id.gvCaudung);
        gvCausai = findViewById(R.id.gvCausai);
        gvCauchualam = findViewById(R.id.gvCauchualam);

        examController = new ExamController(getApplicationContext());
        currentExam = examController.getExams(maDe + "");

        tvTongDiem.setText("Tổng điểm: " + (int)currentExam.getDiemSo());

        int minute, second, time = currentExam.getTgLam();
        String strTime = "";

        minute = time/60;
        if(minute < 10)
        {
            strTime += "0" + minute + ":";
        }
        else
        {
            strTime += minute + ":";
        }
        second = time%60;
        if(second < 10)
        {
            strTime += "0" + second;
        }
        else
        {
            strTime += second;
        }
        tvTime.setText(strTime);

        if(currentExam.getPassed() == 1)
        {
            tvKetqua.setText("ĐỖ");
            tvKetqua.setTextColor(Color.GREEN);
        }
        else
        {
            tvKetqua.setText("TRƯỢT");
            tvKetqua.setTextColor(Color.RED);
        }

        if(currentExam.getMaModule() == 1)
        {
            tvModule.setText("MODULE 1: MTCB");
        }
        else if(currentExam.getMaModule() == 2)
        {
            tvModule.setText("MODULE 2: UDCC");
        }
        else if(currentExam.getMaModule() == 3)
        {
            tvModule.setText("MODULE 3: CSTT");
        }

        tvMade.setText("Mã đề: " + maDe);

        questionAdapter = new QuestionAdapter(getApplicationContext(), questions, 1);
        gvTatcacauhoi.setAdapter(questionAdapter);

        questionAdapter = new QuestionAdapter(getApplicationContext(), cauDung, 1);
        gvCaudung.setAdapter(questionAdapter);

        questionAdapter = new QuestionAdapter(getApplicationContext(), cauSai, 1);
        gvCausai.setAdapter(questionAdapter);

        questionAdapter = new QuestionAdapter(getApplicationContext(), cauChuaLam, 1);
        gvCauchualam.setAdapter(questionAdapter);



    }
}
