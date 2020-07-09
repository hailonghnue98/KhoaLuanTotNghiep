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

public class BoCauHoiScreenSlideActivity extends FragmentActivity {
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    public int NUM_PAGES = 50;
    private static final int NUM_QUESTIONS = 50;
    private int xemDapAn = 0;
    private int vitriDapAn;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;
    private Bundle bundle = new Bundle();
    private TextView tvBCHModuleTitle;
    private ImageView imgPrev, imgNext, imgBack;
    private Button btnDapan, btnDSCauHoi;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter pagerAdapter;

    QuestionController questionController;
    ArrayList<Question> questions;
    Question aQuestion;
    Intent intent;
    String subject = "";
    int limit = 1000;
    ArrayList<Integer> thuTuCauhoi = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bo_cau_hoi_screen_slide);
        addViews();
        addEvents();

    }

    private void addViews() {
        tvBCHModuleTitle = findViewById(R.id.tvBCHModuleTitle);

        btnDapan = findViewById(R.id.btnDapan);
        btnDSCauHoi = findViewById(R.id.btnDSCauHoi);

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
        subject = bundle.getString("subject");

        switch (subject)
        {
            case "pm":
                tvBCHModuleTitle.setText("MODULE 1: PHẦN MỀM");
                break;
            case "pc":
                tvBCHModuleTitle.setText("MODULE 1: PHẦN CỨNG");
                break;
            case "hdh":
                tvBCHModuleTitle.setText("MODULE 1: HỆ ĐIỀU HÀNH");
                break;
            case "word":
                tvBCHModuleTitle.setText("MODULE 2: MICROSOFT WORD");
                break;
            case "excel":
                tvBCHModuleTitle.setText("MODULE 2: MICROSOFT EXCEL");
                break;
            case "ppt":
                tvBCHModuleTitle.setText("MODULE 2: MICROSOFT POWERPOINT");
                break;
            case "mvi":
                tvBCHModuleTitle.setText("MODULE 3: MẠNG VÀ INTERNET");
                break;
            case "ttdt":
                tvBCHModuleTitle.setText("MODULE 3: TRUYỀN THÔNG ĐIỆN TỬ");
                break;
            case "sdi":
                tvBCHModuleTitle.setText("MODULE 3: SỬ DỤNG INTERNET");
                break;
            default:
                break;
        }

        questionController = new QuestionController(this);
        questions = new ArrayList<>();
        aQuestion = new Question();

        questions.addAll(questionController.getQuestionsNoRandom(subject, limit));
        NUM_PAGES = questions.size();

        thuTuCauhoi.add(0);
        thuTuCauhoi.add(1);
        thuTuCauhoi.add(2);
        thuTuCauhoi.add(3);
        Collections.shuffle(thuTuCauhoi);

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

        btnDapan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnDSCauHoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkDSCauHoi();
            }
        });
    }

    public void checkDSCauHoi()
    {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.check_dscauhoi);
        dialog.setTitle("Danh sách câu hỏi:");

        Button btnClose = dialog.findViewById(R.id.btnClose);
        TextView tvModuleTitle = dialog.findViewById(R.id.tvModuleTitle);
        ImageView imgClose = dialog.findViewById(R.id.imgClose);
        GridView gvBaiLam = dialog.findViewById(R.id.gvDSCauHoi);
        ListQuestionAdapter listQuestionAdapter = new ListQuestionAdapter(questions, this);

        gvBaiLam.setAdapter(listQuestionAdapter);

        switch (subject)
        {
            case "pm":
                tvModuleTitle.setText("MODULE 1: PHẦN MỀM");
                break;
            case "pc":
                tvModuleTitle.setText("MODULE 1: PHẦN CỨNG");
                break;
            case "hdh":
                tvModuleTitle.setText("MODULE 1: HỆ ĐIỀU HÀNH");
                break;
            case "word":
                tvModuleTitle.setText("MODULE 2: MICROSOFT WORD");
                break;
            case "excel":
                tvModuleTitle.setText("MODULE 2: MICROSOFT EXCEL");
                break;
            case "ppt":
                tvModuleTitle.setText("MODULE 2: MICROSOFT POWERPOINT");
                break;
            case "mvi":
                tvModuleTitle.setText("MODULE 3: MẠNG VÀ INTERNET");
                break;
            case "ttdt":
                tvModuleTitle.setText("MODULE 3: TRUYỀN THÔNG ĐIỆN TỬ");
                break;
            case "sdi":
                tvModuleTitle.setText("MODULE 3: SỬ DỤNG INTERNET");
                break;
            default:
                break;
        }

        gvBaiLam.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mPager.setCurrentItem(i);
                dialog.dismiss();
            }
        });

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
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

    public ArrayList<Question> refreshData()
    {
        return questionController.getQuestions(subject, limit);
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
            return BoCauHoiScreenSlideFragment.create(position, thuTuCauhoi, xemDapAn);
        }

        @Override
        public int getCount() {
            intent = getIntent();
            bundle = intent.getBundleExtra("bundle");
            subject = bundle.getString("subject");

            questionController = new QuestionController(getApplicationContext());
            questions = new ArrayList<>();
            questions.addAll(questionController.getQuestionsNoRandom(subject, limit));
            NUM_PAGES = questions.size();
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