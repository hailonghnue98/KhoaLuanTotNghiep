package com.example.Exam;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.demo_chungchi.R;

import java.util.ArrayList;

public class CheckAnswerAdapter extends BaseAdapter {

    ArrayList listData;
    LayoutInflater inflater;
    int nopbai;

    public CheckAnswerAdapter(ArrayList listData, Context context) {
        this.listData = listData;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.nopbai = 0;
    }

    public CheckAnswerAdapter(ArrayList listData, Context context, int nopbai) {
        this.listData = listData;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.nopbai = nopbai;
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int i) {
        return listData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Question question = (Question) getItem(i);
        String answer = question.getAnswer();
        String result = question.getResult();
        ViewHolder viewHolder;
        if(view == null)
        {
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.item_gridview_bailam, null);
            viewHolder.frame_cauhoi_bailam = view.findViewById(R.id.frame_cauhoi_bailam);
            viewHolder.txtQuestion = view.findViewById(R.id.txtCauhoi);
            viewHolder.txtAnswer = view.findViewById(R.id.txtTraloi);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        int position = i + 1;

        viewHolder.txtQuestion.setText("Câu " + position + ":");

//        viewHolder.txtAnswer.setText(question.getAnswer());

        if(!question.getAnswer().equals(""))
        {
            viewHolder.frame_cauhoi_bailam.setBackgroundResource(R.drawable.border_btn_blue);
            viewHolder.txtAnswer.setText(getChoiceFromID(question.getChoiceID()));
        }
        else
        {
            viewHolder.txtAnswer.setText("-");
            viewHolder.frame_cauhoi_bailam.setBackgroundResource(R.drawable.border_btn_orange);
        }

        if(nopbai != 0)
        {
            if(!answer.equals(""))
            {
                if(!result.equals(answer))
                {
                    viewHolder.frame_cauhoi_bailam.setBackgroundResource(R.drawable.border_btn_red);
                    viewHolder.txtQuestion.setTextColor(Color.WHITE);
                    viewHolder.txtAnswer.setTextColor(Color.WHITE);
                }
                else
                {
                    viewHolder.frame_cauhoi_bailam.setBackgroundResource(R.drawable.border_btn_green);
                    viewHolder.txtQuestion.setTextColor(Color.WHITE);
                    viewHolder.txtAnswer.setTextColor(Color.WHITE);
                }
            }
            else
            {
                viewHolder.frame_cauhoi_bailam.setBackgroundResource(R.drawable.border_btn_orange);
                viewHolder.txtQuestion.setTextColor(Color.WHITE);
                viewHolder.txtAnswer.setTextColor(Color.WHITE);
            }
        }
        return view;
    }

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

    public String getResult(String result, Question question)
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

    public static class ViewHolder
    {
        TextView txtQuestion, txtAnswer;
        LinearLayout frame_cauhoi_bailam;
    }
}
