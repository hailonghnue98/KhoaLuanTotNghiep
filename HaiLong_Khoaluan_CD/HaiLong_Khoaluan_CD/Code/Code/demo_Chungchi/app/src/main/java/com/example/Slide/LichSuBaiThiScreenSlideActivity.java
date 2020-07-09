package com.example.Slide;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Exam.CheckAnswerAdapter;
import com.example.Exam.Exam;
import com.example.Exam.ExamController;
import com.example.Exam.ListQuestionAdapter;
import com.example.Exam.Question;
import com.example.Exam.QuestionController;
import com.example.Fragment.ResultFragment;
import com.example.demo_chungchi.R;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class LichSuBaiThiScreenSlideActivity extends FragmentActivity {
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    public int NUM_PAGES = 50;
    private static final int NUM_QUESTIONS = 50;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;
    private Bundle bundle = new Bundle();
    private TextView tvTitle, tvMade;
    private ImageView imgPrev, imgNext, imgBack;
    private Button btnBailam, btnTrolai;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter pagerAdapter;

    QuestionController questionController;
    ArrayList<Question> questions;
    int vitriCauHoi;
    Question aQuestion;
    Intent intent;
    ArrayList<Integer> thuTuCauhoi = new ArrayList<>();
    Exam currentExam;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su_bai_thi_screen_slide);
        addViews();
        addEvents();

    }

    private void addViews() {
        tvTitle = findViewById(R.id.tvTitle);
        tvMade = findViewById(R.id.tvMade);

        btnBailam = findViewById(R.id.btnBailam);
        btnTrolai = findViewById(R.id.btnTrolai);

        imgPrev = findViewById(R.id.imgPrev);
        imgNext = findViewById(R.id.imgNext);
        imgBack = findViewById(R.id.imgBack);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(pagerAdapter);
        mPager.setPageTransformer(true, new DepthPageTransformer());

        intent = getIntent();
        bundle = intent.getBundleExtra("bundle");
        vitriCauHoi = bundle.getInt("vitriCauHoi", 0);
        questions = (ArrayList<Question>) bundle.getSerializable("questions");
        currentExam = (Exam) bundle.getSerializable("currentExam");

        tvMade.setText(currentExam.getMade() + "");

        if(currentExam.getMaModule() == 1)
        {
            tvTitle.setText("MODULE 1: MÁY TÍNH CĂN BẢN");
        }
        else if(currentExam.getMaModule() == 2)
        {
            tvTitle.setText("MODULE 2: ỨNG DỤNG CHỦ CHỐT");
        }
        else if(currentExam.getMaModule() == 3)
        {
            tvTitle.setText("MODULE 3: CUỘC SỐNG TRỰC TUYẾN");
        }

        aQuestion = new Question();

        thuTuCauhoi.add(0);
        thuTuCauhoi.add(1);
        thuTuCauhoi.add(2);
        thuTuCauhoi.add(3);
        Collections.shuffle(thuTuCauhoi);

        mPager.setCurrentItem(vitriCauHoi);
    }

    private void addEvents() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        imgPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mPager.getCurrentItem() > 0)
                {
                    mPager.setCurrentItem(mPager.getCurrentItem() - 1);
                }
            }
        });

        imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mPager.getCurrentItem() < NUM_PAGES)
                {
                    mPager.setCurrentItem(mPager.getCurrentItem() + 1);
                }
                if(mPager.getCurrentItem() == NUM_PAGES - 1)
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "Câu cuối rồi đấy!", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

    }

    public String getResult(String result)
    {
        if(result.equals("A"))
        {
            return aQuestion.getAns_a();
        }
        if(result.equals("B"))
        {
            return aQuestion.getAns_b();
        }
        if(result.equals("C"))
        {
            return aQuestion.getAns_c();
        }
        if(result.equals("D"))
        {
            return aQuestion.getAns_d();
        }
        return "";
    }


    public String formatNumber(Double value)
    {
        String pattern = "###,###.##";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        String format = decimalFormat.format(value);
        return format;
    }

    public ArrayList<Question> getData()
    {
        return questions;
    }


    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.

        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return LichSuBaiThiScreenSlidePageFragment.create(position, thuTuCauhoi, vitriCauHoi);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

    public class DepthPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.75f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0f);

            } else if (position <= 0) { // [-1,0]
                // Use the default slide transition when moving to the left page
                view.setAlpha(1f);
                view.setTranslationX(0f);
                view.setScaleX(1f);
                view.setScaleY(1f);

            } else if (position <= 1) { // (0,1]
                // Fade the page out.
                view.setAlpha(1 - position);

                // Counteract the default slide transition
                view.setTranslationX(pageWidth * -position);

                // Scale the page down (between MIN_SCALE and 1)
                float scaleFactor = MIN_SCALE
                        + (1 - MIN_SCALE) * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0f);
            }
        }
    }



    // Phương thức xoa database viết vào hàm MainActivity
    //        try {
//            db.deleteDataBase();
//            Toast.makeText(this, "Xóa thành công", Toast.LENGTH_SHORT).show();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            Toast.makeText(this, "bi loi rui", Toast.LENGTH_SHORT).show();
//        }
}