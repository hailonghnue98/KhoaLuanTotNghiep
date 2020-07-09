package com.example.model;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demo_chungchi.R;

import java.util.ArrayList;

public class BaiGiangExpandableLvAdapter extends BaseExpandableListAdapter {
    private Context context;
    private ArrayList<GroupBaiGiang> listGroupBG;

    public BaiGiangExpandableLvAdapter(Context context, ArrayList<GroupBaiGiang> listGroupBG) {
        this.context = context;
        this.listGroupBG = listGroupBG;
    }

    @Override
    public int getGroupCount() {
        return listGroupBG.size();
    }

    @Override
    public int getChildrenCount(int i) {
        ArrayList<BaiGiang> listBG = listGroupBG.get(i).getListBG();
        return listBG.size();
    }

    @Override
    public Object getGroup(int i) {
        return listGroupBG.get(i);
    }

    @Override
    public Object getChild(int i, int childPosition) {
        ArrayList<BaiGiang> listBG = listGroupBG.get(i).getListBG();
        return listBG.get(childPosition);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        GroupBaiGiang groupInfo = listGroupBG.get(i);
        if(view == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.baigiang_expandable_group, null);
        }

        ImageView imgGroupIcon = view.findViewById(R.id.imgGroupIcon);
        TextView tvBGGroupName = view.findViewById(R.id.tvBGGroupName);

        tvBGGroupName.setText(groupInfo.getName());

        if(groupInfo.getModule() == 1)
        {
            imgGroupIcon.setImageResource(R.drawable.mtcb);
        }
        else if(groupInfo.getModule() == 2)
        {
            imgGroupIcon.setImageResource(R.drawable.udcc);
        }
        else if(groupInfo.getModule() == 3)
        {
            imgGroupIcon.setImageResource(R.drawable.cstt);
        }

        return view;
    }

    @Override
    public View getChildView(int i, int childPosition, boolean b, View view, ViewGroup viewGroup) {
        BaiGiang baiGiang = (BaiGiang) getChild(i, childPosition);
        if(view == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.baigiang_expandable_item, null);
        }

        ImageView imgItemIcon = view.findViewById(R.id.imgItemIcon);
        TextView tvItemIndex = view.findViewById(R.id.tvItemIndex);
        TextView tvItemName = view.findViewById(R.id.tvItemName);

        tvItemIndex.setText(baiGiang.getMaBaiGiang() + ". ");
        tvItemName.setText(baiGiang.getTenBaiGiang());
        imgItemIcon.setImageResource(this.context.getResources().getIdentifier(baiGiang.getAnhBaiGiang(), "drawable", "com.example.demo_chungchi"));

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
