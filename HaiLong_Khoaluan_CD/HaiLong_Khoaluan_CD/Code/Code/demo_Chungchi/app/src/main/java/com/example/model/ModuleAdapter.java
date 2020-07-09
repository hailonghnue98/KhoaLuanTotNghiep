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

public class ModuleAdapter extends ArrayAdapter<Module> {
    private Activity context;
    private int resource;

    public ModuleAdapter(Activity context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View customView = inflater.inflate(this.resource, null);

        Module module = getItem(position);
        ImageView imgLogo = customView.findViewById(R.id.imgLogo);
        TextView tvTitle = customView.findViewById(R.id.tvTile);
        TextView tvName = customView.findViewById(R.id.tvName);
        imgLogo.setImageResource(module.getImage());
        tvTitle.setText(module.getTitle());
        tvName.setText(module.getName());

        return customView;
    }
}
