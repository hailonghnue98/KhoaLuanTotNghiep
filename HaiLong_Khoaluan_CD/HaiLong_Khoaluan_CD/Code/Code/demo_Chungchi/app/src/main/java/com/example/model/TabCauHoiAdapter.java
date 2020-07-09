package com.example.model;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demo_chungchi.R;

import java.util.ArrayList;

public class TabCauHoiAdapter extends BaseAdapter {

    Activity context;
    String[] question = new String[]{"Câu 1", "Câu 2", "Câu 3", "Câu 4", "Câu 5", "Câu 6", "Câu 7", "Câu 8"};
    int resource;

    public TabCauHoiAdapter(Activity context, int resource) {
        this.context = context;
        this.resource = resource;
    }

    @Override
    public int getCount() {
        return question.length;
    }

    @Override
    public Object getItem(int position) {
        return question[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = this.context.getLayoutInflater();
        convertView = layoutInflater.inflate(this.resource, null);


        TextView tvName = convertView.findViewById(R.id.tvName);

        tvName.setText(question[position]);

        return convertView;
    }
}
