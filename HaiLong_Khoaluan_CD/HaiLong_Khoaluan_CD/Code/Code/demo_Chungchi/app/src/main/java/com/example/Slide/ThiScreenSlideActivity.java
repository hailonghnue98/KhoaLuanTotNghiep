package com.example.Slide;

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

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.Exam.CheckAnswerAdapter;
import com.example.Exam.ExamController;
import com.example.Exam.Question;
import com.example.Exam.QuestionController;
import com.example.Fragment.ResultFragment;
import com.example.demo_chungchi.R;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ThiScreenSlideActivity extends FragmentActivity {
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 50;
    private static final int NUM_QUESTIONS = 50;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;
    private TextView txtKiemtra, tvXemdiem, tvBailam;
    private Button btnXemBaiLam, btnNopBai;
    private ImageView imgPrev, imgNext;
    private CounterClass timer;
    private Bundle bundle = new Bundle();

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter pagerAdapter;

    QuestionController questionController;
    ArrayList<Question> questions;
    Question aQuestion;
    Intent intent;
    String maModule;
    int maDe;

    int nopbai = 0;
    ArrayList<Integer> vitriCauhoi = new ArrayList<>();

    TextView txtTimer;
    ResultFragment resultFragment = new ResultFragment();
    Bundle kqLambai = new Bundle();
    String strTGLB;
    int tgLambai = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thi_screen_slide);
        addViews();
        addEvents();

    }

    private void addViews() {
        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(pagerAdapter);
        mPager.setPageTransformer(true, new DepthPageTransformer());

        intent = getIntent();

        bundle = intent.getBundleExtra("bundle");

        maModule = bundle.getString("maModule", "0");

        questionController = new QuestionController(this);
        questions = new ArrayList<>();
        aQuestion = new Question();


        if(maModule.equals("1"))
        {
            questions.addAll(questionController.getQuestions("pc", 10, 1));
            questions.addAll(questionController.getQuestions("pc", 5, 2));
            questions.addAll(questionController.getQuestions("pc", 5, 3));

            questions.addAll(questionController.getQuestions("pm", 5, 1));
            questions.addAll(questionController.getQuestions("pm", 5, 2));
            questions.addAll(questionController.getQuestions("pm", 5, 3));

            questions.addAll(questionController.getQuestions("hdh", 5, 1));
            questions.addAll(questionController.getQuestions("hdh", 5, 2));
            questions.addAll(questionController.getQuestions("hdh", 5, 3));
        }
        else if(maModule.equals("2"))
        {
            questions.addAll(questionController.getQuestions("word", 10, 1));
            questions.addAll(questionController.getQuestions("word", 5, 2));
            questions.addAll(questionController.getQuestions("word", 5, 3));


            questions.addAll(questionController.getQuestions("excel", 5, 1));
            questions.addAll(questionController.getQuestions("excel", 5, 2));
            questions.addAll(questionController.getQuestions("excel", 5, 3));


            questions.addAll(questionController.getQuestions("ppt", 5, 1));
            questions.addAll(questionController.getQuestions("ppt", 5, 2));
            questions.addAll(questionController.getQuestions("ppt", 5, 3));


        }
        else if(maModule.equals("3"))
        {
            questions.addAll(questionController.getQuestions("mvi", 10, 1));
            questions.addAll(questionController.getQuestions("mvi", 5, 2));
            questions.addAll(questionController.getQuestions("mvi", 5, 3));

            questions.addAll(questionController.getQuestions("ttdt", 5, 1));
            questions.addAll(questionController.getQuestions("ttdt", 5, 2));
            questions.addAll(questionController.getQuestions("ttdt", 5, 3));

            questions.addAll(questionController.getQuestions("sdi", 5, 1));
            questions.addAll(questionController.getQuestions("sdi", 5, 2));
            questions.addAll(questionController.getQuestions("sdi", 5, 3));
        }

//        if(maModule.equals("1"))
//        {
//            questions.addAll(questionController.getQuestions("pc", 20));
//            questions.addAll(questionController.getQuestions("pm", 15));
//            questions.addAll(questionController.getQuestions("hdh", 15));
//        }
//        else if(maModule.equals("2"))
//        {
//            questions.addAll(questionController.getQuestions("word", 20));
//            questions.addAll(questionController.getQuestions("excel", 15));
//            questions.addAll(questionController.getQuestions("ppt", 15));
//        }
//        else if(maModule.equals("3"))
//        {
//            questions.addAll(questionController.getQuestions("mvi", 20));
//            questions.addAll(questionController.getQuestions("ttdt", 15));
//            questions.addAll(questionController.getQuestions("sdi", 15));
//        }

        Collections.shuffle(questions);

        txtTimer = findViewById(R.id.txtTime);
        txtKiemtra = findViewById(R.id.txtKiemtra);
        tvXemdiem = findViewById(R.id.tvXemdiem);
        tvBailam = findViewById(R.id.tvBailam);
        btnXemBaiLam = findViewById(R.id.btnXemBaiLam);
        btnNopBai = findViewById(R.id.btnNopBai);
        imgPrev = findViewById(R.id.imgPrev);
        imgNext = findViewById(R.id.imgNext);


        timer = new CounterClass(3600*1000, 1000);
        timer.start();

        vitriCauhoi.add(0);
        vitriCauhoi.add(1);
        vitriCauhoi.add(2);
        vitriCauhoi.add(3);
        Collections.shuffle(vitriCauhoi);

    }

    private void addEvents() {

        txtKiemtra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer();
            }
        });

        txtTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        tvBailam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBailam();
            }
        });

        tvXemdiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFragment(resultFragment);
            }
        });

        btnXemBaiLam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBailam_Nopbai();
            }
        });

        btnNopBai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer();
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

    public void checkBailam_Nopbai()
    {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.check_bailam_layout);
        dialog.setTitle("Bài làm:");
        CheckAnswerAdapter checkAnswerAdapter = new CheckAnswerAdapter(questions, this, 1);
        GridView gvBaiLam = dialog.findViewById(R.id.gvBallam);
        gvBaiLam.setAdapter(checkAnswerAdapter);

        gvBaiLam.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mPager.setCurrentItem(i);
                dialog.dismiss();
            }
        });

        Button btnClose;
        btnClose = dialog.findViewById(R.id.btnClose);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        ImageView imgClose;
        imgClose = dialog.findViewById(R.id.imgClose);

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void checkBailam()
    {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.check_bailam_layout);
        dialog.setTitle("Bài làm:");
        CheckAnswerAdapter checkAnswerAdapter = new CheckAnswerAdapter(questions, this);
        GridView gvBaiLam = dialog.findViewById(R.id.gvBallam);
        gvBaiLam.setAdapter(checkAnswerAdapter);

        gvBaiLam.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mPager.setCurrentItem(i);
                dialog.dismiss();
            }
        });

        Button btnClose;
        btnClose = dialog.findViewById(R.id.btnClose);

        ImageView imgClose;
        imgClose = dialog.findViewById(R.id.imgClose);

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

    public void checkAnswer()
    {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.check_answer_dialog);
        dialog.setTitle("Bài làm:");
        CheckAnswerAdapter checkAnswerAdapter = new CheckAnswerAdapter(questions, this);
        GridView gvBaiLam = dialog.findViewById(R.id.gvBallam);
        gvBaiLam.setAdapter(checkAnswerAdapter);

        gvBaiLam.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mPager.setCurrentItem(i);
                dialog.dismiss();
            }
        });

        Button btnClose, btnFinish;
        ImageView imgClose;

        btnClose = dialog.findViewById(R.id.btnClose);
        btnFinish = dialog.findViewById(R.id.btnEnd);

        imgClose = dialog.findViewById(R.id.imgClose);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishExam();
                showFragment(resultFragment);
                dialog.dismiss();
            }
        });

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void showFragment(Fragment fragment)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contain, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
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

    public void finishExam()
    {
        int corrects = 0, wrongs = 0, miss = 0, passed;
        double scores;


        for(int i = 0; i < questions.size(); i++)
        {
            aQuestion = questions.get(i);
            String answer = aQuestion.getAnswer();
            String result = aQuestion.getResult();
            if(answer.equals(result))
            {
                corrects++;
            }
            else if(answer.equals(""))
            {
                miss++;
            }
        }

//        scores = ((double)corrects/30)*10;
        scores = corrects*20;
        wrongs = NUM_QUESTIONS - corrects - miss;


        if(scores >= 650)
        {
            passed = 1;
        }else{
            passed = 0;
        }

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String ngayThi = simpleDateFormat.format(date);

        ExamController examController = new ExamController(this);
        examController.insertExam(Integer.parseInt(maModule), ngayThi, corrects, wrongs, miss, (int)scores, tgLambai, passed);

        maDe = examController.maxIndex();

        String testStr = "";

        for(int i = 0; i < questions.size(); i++)
        {
            Question cauhoi = questions.get(i);
            examController.insertBaiThi(maDe, cauhoi.getId(), cauhoi.getQuestion(), cauhoi.getAns_a(), cauhoi.getAns_b(), cauhoi.getAns_c(), cauhoi.getAns_d(), cauhoi.getResult(), cauhoi.getImage(), cauhoi.getModule(), cauhoi.getSubject(), cauhoi.getLevel(), cauhoi.getGiaithich(), cauhoi.getAnswer());
            testStr += cauhoi.getAnswer();
        }

        Toast.makeText(this, testStr + "", Toast.LENGTH_SHORT).show();

//        Toast.makeText(getApplicationContext(), "Your scores: " + formatNumber(scores) + "\nCorrect answers: " + corrects + "/50", Toast.LENGTH_LONG).show();

        nopbai = 1;
        timer.cancel();

        int phut, giay;
        phut = tgLambai/60;
        giay = tgLambai%60;
        strTGLB = String.format("%02d:%02d", phut, giay);

        kqLambai.putInt("maDe", maDe);
        kqLambai.putInt("cauDung", corrects);
        kqLambai.putInt("cauSai", wrongs);
        kqLambai.putInt("cauChualam", miss);
        kqLambai.putInt("tongCau", NUM_QUESTIONS);
        kqLambai.putString("diem", formatNumber(scores));
        kqLambai.putString("tgLambai", strTGLB);
        kqLambai.putBundle("bundle", bundle);
        kqLambai.putInt("passed", passed);
        kqLambai.putString("maModule", maModule);
        resultFragment.setArguments(kqLambai);
//        showFragment(resultFragment);


        txtKiemtra.setVisibility(View.GONE);
        tvBailam.setVisibility(View.VISIBLE);
        tvXemdiem.setVisibility(View.VISIBLE);
        btnNopBai.setVisibility(View.GONE);
        btnXemBaiLam.setVisibility(View.VISIBLE);
        tvBailam.setVisibility(View.GONE);

        mPager.setAdapter(pagerAdapter);
        mPager.setCurrentItem(NUM_PAGES);


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
            if (nopbai != 1)
            {
                checkAnswer();
            }
            else
            {

            }
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
            return ThiScreenSlidePageFragment.create(position, nopbai, vitriCauhoi);
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

    public class CounterClass extends CountDownTimer {

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            String countTime = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished), TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
            txtTimer.setText(countTime); //SetText cho textview hiện thị thời gian.
            tgLambai++;
        }

        @Override
        public void onFinish() {
            txtTimer.setText("00:00");  //SetText cho textview hiện thị thời gian.
            finishExam();
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