package com.example.Slide;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.Exam.Question;
import com.example.demo_chungchi.ImageZoomActivity;
import com.example.demo_chungchi.R;

import java.util.ArrayList;

public class LichSuBaiThiScreenSlidePageFragment extends Fragment {

    private ArrayList<Question> arr_question;
    public static final String ARG_PAGE = "page";
    public static final String ARG_CHIDAN = "thuTuCauhoi";
    public static final String ARG_VITRICAUHOI = "vitriCauHoi";
    private int mPageNumber; //So trang hien tai
    private LichSuBaiThiScreenSlideActivity lichSuBaiThiScreenSlideActivity;

    private TextView txtQuestionNumber, txtQuestion, tvIdQues, tvThongbao, tvGiaithich, tvSubject;
    private ImageView imgQuestion;
    private RadioGroup radioGroup;
    private RadioButton radA, radB, radC, radD;
    private LinearLayout layoutGiaithich;
    private Question question;
    ArrayList<String> listAnswer = new ArrayList<>();
    ArrayList<Integer> thuTuCauHoi = new ArrayList<>();
    private int vitriCauHoi;


    public LichSuBaiThiScreenSlidePageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_lich_su_bai_thi_page, container, false);

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
        layoutGiaithich = rootView.findViewById(R.id.layoutGiaithich);

        return rootView;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arr_question = new ArrayList<>();
        lichSuBaiThiScreenSlideActivity = (LichSuBaiThiScreenSlideActivity) getActivity();
        arr_question = lichSuBaiThiScreenSlideActivity.getData();
        mPageNumber = getArguments().getInt(ARG_PAGE, 0);
        thuTuCauHoi = getArguments().getIntegerArrayList(ARG_CHIDAN);
        vitriCauHoi = getArguments().getInt(ARG_VITRICAUHOI, 0);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        imgQuestion.setImageResource(getResources().getIdentifier("question_default", "drawable", "com.example.viewpager"));

        question = getItem(mPageNumber);

        txtQuestionNumber.setText("Câu " + question.getViTri());
        tvSubject.setText(getSubjectFormKey(question.getSubject()));
        txtQuestion.setText(question.getQuestion());
        tvIdQues.setText("ID: " + question.getId());

        final String image = question.getImage();
        if(!image.equals(""))
        {
            imgQuestion.setImageResource(getResources().getIdentifier(image, "drawable", "com.example.demo_chungchi"));
        }

        radA.setText(question.getAns_a());
        radB.setText(question.getAns_b());
        radC.setText(question.getAns_c());
        radD.setText(question.getAns_d());

//        listAnswer.add(question.getAns_a());
//        listAnswer.add(question.getAns_b());
//        listAnswer.add(question.getAns_c());
//        listAnswer.add(question.getAns_d());

        /*Gan cau hoi theo chi dan*/
//        for(int i = 0; i < thuTuCauHoi.size(); i++)
//        {
//            switch(thuTuCauHoi.get(i))
//            {
//                case 0:
//                    listAnswer.add(question.getAns_a());
//                    break;
//                case 1:
//                    listAnswer.add(question.getAns_b());
//                    break;
//                case 2:
//                    listAnswer.add(question.getAns_c());
//                    break;
//                case 3:
//                    listAnswer.add(question.getAns_d());
//                    break;
//            }
//        }
//
//        radA.setText(listAnswer.get(0));
//        radB.setText(listAnswer.get(1));
//        radC.setText(listAnswer.get(2));
//        radD.setText(listAnswer.get(3));

        hienThiDapAn();

        imgQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ImageZoomActivity.class);
                intent.putExtra("imageName", image);
                startActivity(intent);
            }
        });

    }

    private void hienThiDapAn() {
        String result = question.getResult();
        String answer = question.getAnswer();

        disableRadioButton();
        resetRads();
        markCorrectAnswer(getKeyfromResult(getResult(question.getResult())));
        if(!result.equals(answer))
        {
            if(answer.equals(""))
            {
                tvThongbao.setText("Bạn chưa làm câu này :(");
                tvThongbao.setTextColor(getResources().getColor(R.color.colorChualam));
            }
            else
            {
                markWrongAnswer(question.getAnswer());
                tvThongbao.setText("Tiếc quá, bạn chọn sai rồi :(");
                tvThongbao.setTextColor(getResources().getColor(R.color.colorThongbaoSai));
            }
        }
        else
        {
            tvThongbao.setText("Chính xác!");
            tvThongbao.setTextColor(getResources().getColor(R.color.colorThongbaoCX));
        }

        layoutGiaithich.setVisibility(View.VISIBLE);
        tvGiaithich.setText("Chọn " + getKeyfromResult(getResult(question.getResult())) + " bởi vì: ");
        tvGiaithich.append("\n" + question.getGiaithich());

    }

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

    public static LichSuBaiThiScreenSlidePageFragment create(int pageNumber, ArrayList<Integer> thuTuCauhoi, int vitriCauHoi)
    {
        LichSuBaiThiScreenSlidePageFragment fragment = new LichSuBaiThiScreenSlidePageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        args.putIntegerArrayList(ARG_CHIDAN, thuTuCauhoi);
        args.putInt(ARG_VITRICAUHOI, vitriCauHoi);
        fragment.setArguments(args);
        return fragment;
    }



}
