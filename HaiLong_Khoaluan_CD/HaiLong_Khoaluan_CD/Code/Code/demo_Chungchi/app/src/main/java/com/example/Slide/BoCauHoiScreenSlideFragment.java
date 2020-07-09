package com.example.Slide;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.Exam.Question;
import com.example.demo_chungchi.ImageZoomActivity;
import com.example.demo_chungchi.R;

import java.util.ArrayList;

public class BoCauHoiScreenSlideFragment extends Fragment {

    private ArrayList<Question> arr_question;
    public static final String ARG_PAGE = "page";
    public static final String ARG_CHIDAN = "thuTuCauhoi";
    public static final String ARG_XEMDAPAN = "xemDapAn";
    public int checkNopBai = 0, xemDapAn = 0;
    public int dachondapan = 0;
    private int mPageNumber; //So trang hien tai
    private BoCauHoiScreenSlideActivity boCauHoiScreenSlideActivity;

    private TextView txtQuestionNumber, txtQuestion, tvIdQues, tvThongbao, tvGiaithich, tvSubject;
    private ImageView imgQuestion;
    private RadioGroup radioGroup;
    private RadioButton radA, radB, radC, radD;
    private Button btnXemDapAn;
    private LinearLayout layoutGiaithich;
    private Question question;
    ArrayList<String> listAnswer = new ArrayList<>();
    ArrayList<Integer> thuTuCauHoi = new ArrayList<>();


    public BoCauHoiScreenSlideFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_bo_cau_hoi_screen_slide, container, false);

        txtQuestionNumber = rootView.findViewById(R.id.txtQuestionNumber);
        txtQuestion = rootView.findViewById(R.id.txtQuestion);
        tvIdQues = rootView.findViewById(R.id.tvIdQues);
        tvThongbao = rootView.findViewById(R.id.tvThongbao);
        tvGiaithich = rootView.findViewById(R.id.tvGiaithich);
        tvSubject = rootView.findViewById(R.id.tvSubject);
        imgQuestion = rootView.findViewById(R.id.imgQuestion);
        radioGroup = rootView.findViewById(R.id.radGr);
        radA = rootView.findViewById(R.id.radA);
        radB = rootView.findViewById(R.id.radB);
        radC = rootView.findViewById(R.id.radC);
        radD = rootView.findViewById(R.id.radD);
        btnXemDapAn = rootView.findViewById(R.id.btnXemDapAn);
        layoutGiaithich = rootView.findViewById(R.id.layoutGiaithich);

        return rootView;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arr_question = new ArrayList<>();
        boCauHoiScreenSlideActivity = (BoCauHoiScreenSlideActivity) getActivity();
        arr_question = boCauHoiScreenSlideActivity.getData();
        mPageNumber = getArguments().getInt(ARG_PAGE, 0);
        xemDapAn = getArguments().getInt(ARG_XEMDAPAN, 0);
        thuTuCauHoi = getArguments().getIntegerArrayList(ARG_CHIDAN);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        txtQuestionNumber.setText("Câu " + (mPageNumber + 1));
//        imgQuestion.setImageResource(getResources().getIdentifier("question_default", "drawable", "com.example.viewpager"));

        question = getItem(mPageNumber);

        tvSubject.setText(getSubjectFormKey(question.getSubject()));
        txtQuestion.setText(question.getQuestion());
        tvIdQues.setText("ID: " + question.getId());

//        final String image = "question_default";
        final String image = question.getImage();
        if(!image.equals(""))
        {
            imgQuestion.setImageResource(getResources().getIdentifier(image, "drawable", "com.example.demo_chungchi"));
        }

//        radA.setText(question.getAns_a());
//        radB.setText(question.getAns_b());
//        radC.setText(question.getAns_c());
//        radD.setText(question.getAns_d());

//        listAnswer.add(question.getAns_a());
//        listAnswer.add(question.getAns_b());
//        listAnswer.add(question.getAns_c());
//        listAnswer.add(question.getAns_d());

        /*Gan cau hoi theo chi dan*/
        for(int i = 0; i < thuTuCauHoi.size(); i++)
        {
            switch(thuTuCauHoi.get(i))
            {
                case 0:
                    listAnswer.add(question.getAns_a());
                    break;
                case 1:
                    listAnswer.add(question.getAns_b());
                    break;
                case 2:
                    listAnswer.add(question.getAns_c());
                    break;
                case 3:
                    listAnswer.add(question.getAns_d());
                    break;
            }
        }

        radA.setText(listAnswer.get(0));
        radB.setText(listAnswer.get(1));
        radC.setText(listAnswer.get(2));
        radD.setText(listAnswer.get(3));

        if(xemDapAn != 0)
        {

        }

        //Hien thi dap an cua moi cau hoi
        btnXemDapAn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                hienThiDapAn();
                btnXemDapAn.setVisibility(View.INVISIBLE);
            }
        });

        imgQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ImageZoomActivity.class);
                intent.putExtra("imageName", image);
                startActivity(intent);
            }
        });

        //Lưu kết quả chọn của người dùng
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                dachondapan = 1;
                arr_question.get(mPageNumber).setChoiceID(i);
                //arr_question.get(mPageNumber).setAnswer(getChoiceFromID(i));
                arr_question.get(mPageNumber).setAnswer(getChoice(i));
//                Toast.makeText(getActivity(), "Ban chon " + getChoice(i), Toast.LENGTH_SHORT).show();
                if(checkNopBai != 1)
                {
                    if(radA.isChecked())
                    {
                        resetRads();
                        radA.setBackground(getResources().getDrawable(R.drawable.rad_checked_bg));
                    }
                    else if(radB.isChecked())
                    {
                        resetRads();
                        radB.setBackground(getResources().getDrawable(R.drawable.rad_checked_bg));
                    }
                    else if(radC.isChecked())
                    {
                        resetRads();
                        radC.setBackground(getResources().getDrawable(R.drawable.rad_checked_bg));
                    }
                    else if(radD.isChecked())
                    {
                        resetRads();
                        radD.setBackground(getResources().getDrawable(R.drawable.rad_checked_bg));
                    }
                }

            }


        });
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void hienThiDapAn() {
        String result = getResult(question.getResult());
        String answer = question.getAnswer();
        disableRadioButton();
        resetRads();
        markCorrectAnswer(getKeyfromResult(result));

        layoutGiaithich.setVisibility(View.VISIBLE);
        tvGiaithich.setText("Chọn " + getKeyfromResult(getResult(question.getResult())) + " bởi vì: ");
        tvGiaithich.append("\n" + question.getGiaithich());
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void resetRads() {
        radA.setBackground(getResources().getDrawable(R.drawable.border_radius));
        radB.setBackground(getResources().getDrawable(R.drawable.border_radius));
        radC.setBackground(getResources().getDrawable(R.drawable.border_radius));
        radD.setBackground(getResources().getDrawable(R.drawable.border_radius));
    }

    public Question getItem(int i)
    {
        return arr_question.get(i);
    }

    public String getResult(String result)
    {
        if(result.equals("A"))
        {
            return question.getAns_a();
        }
        if(result.equals("B"))
        {
            return question.getAns_b();
        }
        if(result.equals("C"))
        {
            return question.getAns_c();
        }
        if(result.equals("D"))
        {
            return question.getAns_d();
        }
        return "";
    }

    public String getSubjectFormKey(String key)
    {
        if(key.equals("pc"))
        {
            return "Phần cứng";
        }
        if(key.equals("pm"))
        {
            return "Phần mềm";
        }
        if(key.equals("hdh"))
        {
            return "Hệ điều hành";
        }
        if(key.equals("word"))
        {
            return "Microsoft Word";
        }
        if(key.equals("excel"))
        {
            return "Microsoft Excel";
        }
        if(key.equals("ppt"))
        {
            return "Microsoft Powerpoint";
        }
        if(key.equals("mvi"))
        {
            return "Mạng và Internet";
        }
        if(key.equals("ttdt"))
        {
            return "Truyền thông điện tử";
        }
        if(key.equals("sdi"))
        {
            return "Sử dụng Internet";
        }
        return "";
    }

    public String getKeyfromResult(String result)
    {
        if(radA.getText().equals(result))
        {
            return "A";
        }
        if(radB.getText().equals(result))
        {
            return "B";
        }
        if(radC.getText().equals(result))
        {
            return "C";
        }
        if(radD.getText().equals(result))
        {
            return "D";
        }
        return "";
    }

    //Lấy giá trị của radButton
    public String getChoice(int i)
    {
        if(i == R.id.radA)
        {
            return radA.getText().toString();
        }
        if(i == R.id.radB)
        {
            return radB.getText().toString();
        }
        if(i == R.id.radC)
        {
            return radC.getText().toString();
        }
        if(i == R.id.radD)
        {
            return radD.getText().toString();
        }
        return "";
    }

    //Lấy giá trị của radButton chuyển thành đáp án A/B/C/D
    public String getChoiceFromID(int i)
    {
        if(i == R.id.radA)
        {
            return "A";
        }
        if(i == R.id.radB)
        {
            return "B";
        }
        if(i == R.id.radC)
        {
            return "C";
        }
        if(i == R.id.radD)
        {
            return "D";
        }
        return "";
    }

    private void disableRadioButton() {
        radA.setClickable(false);
        radB.setClickable(false);
        radC.setClickable(false);
        radD.setClickable(false);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void markWrongAnswer(String answer) {
        if(answer.equals("A"))
        {
            radA.setBackgroundColor(getResources().getColor(R.color.colorWrongAns));
            radA.setBackground(getResources().getDrawable(R.drawable.rad_checked_wrong));
        }
        else if(answer.equals("B"))
        {
            radB.setBackgroundColor(getResources().getColor(R.color.colorWrongAns));
            radB.setBackground(getResources().getDrawable(R.drawable.rad_checked_wrong));
        }
        else if(answer.equals("C"))
        {
            radC.setBackgroundColor(getResources().getColor(R.color.colorWrongAns));
            radC.setBackground(getResources().getDrawable(R.drawable.rad_checked_wrong));
        }
        else if(answer.equals("D"))
        {
            radD.setBackgroundColor(getResources().getColor(R.color.colorWrongAns));
            radD.setBackground(getResources().getDrawable(R.drawable.rad_checked_wrong));
        }
        else
        {

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void markCorrectAnswer(String answer)
    {

        if(answer.equals("A"))
        {
            radA.setBackgroundColor(getResources().getColor(R.color.colorCorrectAns));
            radA.setBackground(getResources().getDrawable(R.drawable.rad_check_correct));
        }
        else if(answer.equals("B"))
        {
            radB.setBackgroundColor(getResources().getColor(R.color.colorCorrectAns));
            radB.setBackground(getResources().getDrawable(R.drawable.rad_check_correct));
        }
        else if(answer.equals("C"))
        {
            radC.setBackgroundColor(getResources().getColor(R.color.colorCorrectAns));
            radC.setBackground(getResources().getDrawable(R.drawable.rad_check_correct));
        }
        else if(answer.equals("D"))
        {
            radD.setBackgroundColor(getResources().getColor(R.color.colorCorrectAns));
            radD.setBackground(getResources().getDrawable(R.drawable.rad_check_correct));
        }
        else
        {
            return;
        }

    }

    public static BoCauHoiScreenSlideFragment create(int pageNumber, ArrayList<Integer> thuTuCauhoi, int xemDapAn)
    {
        BoCauHoiScreenSlideFragment fragment = new BoCauHoiScreenSlideFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        args.putIntegerArrayList(ARG_CHIDAN, thuTuCauhoi);
        args.putInt(ARG_XEMDAPAN, xemDapAn);
        fragment.setArguments(args);
        return fragment;
    }



}
