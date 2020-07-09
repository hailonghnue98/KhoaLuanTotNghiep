package com.example.model;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.demo_chungchi.R;

public class DeThiAdapter extends ArrayAdapter<DeThi> {
    private Activity activity;
    private int resource;

    public DeThiAdapter(Activity activity, int resource) {
        super(activity, resource);
        this.activity = activity;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = this.activity.getLayoutInflater();
        View customView = inflater.inflate(resource, null);
        TextView txtTenDeThi = customView.findViewById(R.id.txtTenDeThi);
        TextView txtRight = customView.findViewById(R.id.txtRight);
        TextView txtWrong = customView.findViewById(R.id.txtWrong);
        TextView txtResult = customView.findViewById(R.id.txtResult);
        TextView txtPercent = customView.findViewById(R.id.txtPercent);
        DeThi dethi = getItem(position);
        txtTenDeThi.setText(dethi.getName());
        txtRight.setText(dethi.getRight()+"/50");
        txtWrong.setText(dethi.getWrong()+"/50");
        txtResult.setText(dethi.getResult());
        txtPercent.setText(dethi.getPercent() + "%");
        if(txtResult.getText().equals("FAILED"))
        {
            txtResult.setTextColor(Color.RED);
        }
        return customView;
    }
}
