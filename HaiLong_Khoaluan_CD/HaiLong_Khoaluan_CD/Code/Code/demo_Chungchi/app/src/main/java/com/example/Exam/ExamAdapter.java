package com.example.Exam;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.demo_chungchi.R;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ExamAdapter extends ArrayAdapter<Exam> {

    public ExamAdapter(@NonNull Context context, ArrayList<Exam> exam) {
        super(context, 0, exam);
    }

    public String formatNumber(Double value)
    {
        String pattern = "###,###.##";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        String format = decimalFormat.format(value);
        return format;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null)
        {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.test_dethi, parent, false);
        }

        int minute = 0, second = 0, time = 0;
        String strTime = "";

        TextView tvMaDe = convertView.findViewById(R.id.tvMaDe);
        TextView tvModule = convertView.findViewById(R.id.tvModule);
        TextView tvCorrect = convertView.findViewById(R.id.tvCorrect);
        TextView tvWrong = convertView.findViewById(R.id.tvWrong);
        TextView tvMiss = convertView.findViewById(R.id.tvMiss);
        TextView tvScore = convertView.findViewById(R.id.tvScore);
        TextView tvResult = convertView.findViewById(R.id.tvResult);
        TextView tvDate = convertView.findViewById(R.id.tvDate);
        TextView tvTime = convertView.findViewById(R.id.tvTime);

        ImageView imgCorrect = convertView.findViewById(R.id.imgCorrect);
        ImageView imgWrong = convertView.findViewById(R.id.imgWrong);
        ImageView imgMiss = convertView.findViewById(R.id.imgMiss);
        ImageView imgKQlambai = convertView.findViewById(R.id.imgKQlambai);

        Exam exam = getItem(position);

        if(exam != null)
        {
            tvMaDe.setText(exam.getMade() + "");

            if(exam.getMaModule() == 1)
            {
                tvModule.setText("MODULE 1: MÁY TÍNH CĂN BẢN");
            }
            else if(exam.getMaModule() == 2)
            {
                tvModule.setText("MODULE 2: ỨNG DỤNG CHỦ CHỐT");
            }
            else if(exam.getMaModule() == 3)
            {
                tvModule.setText("MODULE 3: CUỘC SỐNG TRỰC TUYẾN");
            }

            tvScore.setText((int)exam.getDiemSo() + "");

            if(exam.getPassed() == 1)
            {
                tvResult.setText("ĐỖ");
                tvResult.setTextColor(Color.GREEN);
                imgKQlambai.setImageResource(R.drawable.laughing);
            }
            else
            {
                tvResult.setText("TRƯỢT");
                tvResult.setTextColor(Color.RED);
                imgKQlambai.setImageResource(R.drawable.crying);
            }

            tvCorrect.setText(exam.getCauDung() + "/50");
            tvMiss.setText(exam.getCauChuaLam() + "/50");
            tvWrong.setText(exam.getCauSai() + "/50");

            time = exam.getTgLam();

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

            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            tvDate.setText(simpleDateFormat.format(date));

        }

        return convertView;
    }
}
