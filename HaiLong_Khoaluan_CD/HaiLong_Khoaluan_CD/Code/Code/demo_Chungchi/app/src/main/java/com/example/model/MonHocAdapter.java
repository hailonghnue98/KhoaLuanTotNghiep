package com.example.model;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demo_chungchi.R;

public class MonHocAdapter extends ArrayAdapter<MonHoc> {
    Activity context;
    int resource;
    public MonHocAdapter(Activity context, int resource)
    {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View customView = inflater.inflate(this.resource, null);

        ImageView imgIcon = customView.findViewById(R.id.imgWordIcon);
        TextView txtName = customView.findViewById(R.id.txtWord);
        TextView txtQuestion = customView.findViewById(R.id.txtSubWord);
        MonHoc m = getItem(position);
        imgIcon.setImageResource(m.getImage());
        txtName.setText(m.getName());
        txtQuestion.setText(m.getQuestion() + " Câu");

        return customView;

    }
}
