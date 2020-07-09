package com.example.model;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.demo_chungchi.R;

import java.util.ArrayList;

public class MeoExpandableListViewAdapter extends BaseExpandableListAdapter {
    private Activity context;
    private ArrayList<MeoGroup> listGroup;

    public MeoExpandableListViewAdapter(Activity context, ArrayList<MeoGroup> listGroup) {
        this.context = context;
        this.listGroup = listGroup;
    }

    @Override
    public int getGroupCount() {
        return listGroup.size();
    }

    @Override
    public int getChildrenCount(int i) {
        ArrayList<MeoItem> listChild = listGroup.get(i).getListItem();
        return listChild.size();
    }

    @Override
    public Object getGroup(int i) {
        return listGroup.get(i);
    }

    @Override
    public Object getChild(int i, int childPosition) {
        ArrayList<MeoItem> listChild = listGroup.get(i).getListItem();
        return listChild.get(childPosition);
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
        MeoGroup groupInfo = listGroup.get(i);
        if(view == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.meo_expandable_group, null);
        }
        TextView txtMeoGroupName = view.findViewById(R.id.txtMeoGroupName);
        txtMeoGroupName.setText(groupInfo.getName());
        return view;
    }

    @Override
    public View getChildView(int i, int childPosition, boolean b, View view, ViewGroup viewGroup) {
        MeoItem itemInfo = (MeoItem) getChild(i, childPosition);
        if(view == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.meo_expandable_item, null);
        }
        TextView txtMeoItemIndex = view.findViewById(R.id.txtMeoItemIndex);
        TextView txtMeoItemName = view.findViewById(R.id.txtMeoItemName);
        txtMeoItemIndex.setText(itemInfo.getIndex()+". ");
        txtMeoItemName.setText(itemInfo.getName());
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
