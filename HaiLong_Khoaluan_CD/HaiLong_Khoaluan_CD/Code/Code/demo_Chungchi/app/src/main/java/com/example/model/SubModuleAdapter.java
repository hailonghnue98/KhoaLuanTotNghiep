package com.example.model;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.demo_chungchi.R;

public class SubModuleAdapter extends ArrayAdapter<SubModule> {
    private int resource;
    private Activity context;

    public SubModuleAdapter(@NonNull Activity context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View customView = inflater.inflate(R.layout.bocauhoi_module_details_item, null);

        SubModule subModule = getItem(position);
        ImageView imgLogo = customView.findViewById(R.id.imgLogo);
        TextView tvTitle = customView.findViewById(R.id.tvTitle);
        imgLogo.setImageResource(subModule.getImage());
        tvTitle.setText(subModule.getTitle());

        return customView;
    }
}
