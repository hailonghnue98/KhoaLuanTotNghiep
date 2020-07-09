package com.example.Exam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demo_chungchi.R;

import java.util.ArrayList;

public class ListQuestionAdapter extends BaseAdapter {
    ArrayList listData;
    LayoutInflater layoutInflater;
    Context context;

    public ListQuestionAdapter(ArrayList listData, Context context) {
        this.listData = listData;
        this.context = context;
        this.layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Question question = (Question) listData.get(position);
        convertView = layoutInflater.inflate(R.layout.check_dscauhoi_item, null);

        ImageView imgDSCHLogo = convertView.findViewById(R.id.imgDSCHLogo);
        TextView tvDSCHTitle = convertView.findViewById(R.id.tvDSCHTitle);
        TextView tvDSCHId = convertView.findViewById(R.id.tvDSCHId);
        TextView tvDSCHContent = convertView.findViewById(R.id.tvDSCHContent);

        imgDSCHLogo.setImageResource(R.drawable.question_default);
        if(!question.getImage().equals(""))
        {
            imgDSCHLogo.setImageResource(this.context.getResources().getIdentifier(question.getImage(), "drawable", "com.example.demo_chungchi"));
        }
        else
        {
            imgDSCHLogo.setImageResource(R.drawable.question_default);
        }

        tvDSCHTitle.setText("Câu " + (position + 1) + ": ");
        tvDSCHId.setText("(ID: " + question.getId() + ")");
        tvDSCHContent.setText(question.getQuestion());

        return convertView;
    }

    public class QuestionHolder
    {

    }
}
