package com.example.Exam;

import android.app.Activity;
import android.content.Context;
import android.opengl.Visibility;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.demo_chungchi.R;

import java.util.ArrayList;

public class QuestionAdapter extends BaseAdapter {

    Context context;
    ArrayList<Question> questions;
    int mode = 0;

    public QuestionAdapter(Context context, ArrayList<Question> questions, int mode) {
        this.context = context;
        this.questions = questions;
        this.mode = mode;
    }

    @Override
    public int getCount() {
        return questions.size();
    }

    @Override
    public Object getItem(int position) {
        return questions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return questions.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        convertView = inflater.inflate(R.layout.kqbaithi_gridview_cauhoi_item, null);

        Question aQuestion = questions.get(position);

        LinearLayout linearLayout = convertView.findViewById(R.id.cauhoiItem);
        TextView tvName = convertView.findViewById(R.id.tvName);
        ImageView imgIcon = convertView.findViewById(R.id.imgIcon);

        tvName.setText("Câu " + aQuestion.getViTri());

        String dapAn = aQuestion.getResult();
        String luaChon = aQuestion.getAnswer();

        if(luaChon.equals(""))
        {
            imgIcon.setImageResource(R.drawable.warning_mark);
        }
        else
        {
            if(!dapAn.equals(luaChon))
            {
                imgIcon.setImageResource(R.drawable.wrong_mark);
            }
        }

        if(mode == 1)
        {
            if(!luaChon.equals(dapAn))
            {

            }
        }
        else if(mode == 2)
        {

        }
        else if(mode == 3)
        {

        }
        else
        {

        }

        return convertView;
    }
}
