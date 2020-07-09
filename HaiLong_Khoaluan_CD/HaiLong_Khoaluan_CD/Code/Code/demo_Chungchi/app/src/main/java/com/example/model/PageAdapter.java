package com.example.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.demo_chungchi.R;

import java.util.ArrayList;

public class PageAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Integer> listPage;

    public PageAdapter(Context context, ArrayList<Integer> listPage) {
        this.context = context;
        this.listPage = listPage;
    }

    @Override
    public int getCount() {
        return listPage.size();
    }

    @Override
    public Object getItem(int position) {
        return listPage.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(this.context).inflate(R.layout.slide_page_gridview_item_layout, null);

        int pageNumber = listPage.get(position);

        TextView tvPageNumber = convertView.findViewById(R.id.tvPageNumber);

        tvPageNumber.setText("Trang " + pageNumber);

        return convertView;
    }
}
