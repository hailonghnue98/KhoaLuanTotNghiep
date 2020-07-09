package com.example.demo_chungchi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.Toast;

import com.example.model.MeoExpandableListViewAdapter;
import com.example.model.MeoGroup;
import com.example.model.MeoItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class MeoActivity extends AppCompatActivity {

    private TabHost tabHostMeo;
    private LinkedHashMap<String, MeoGroup> subjects = new LinkedHashMap<>();
    private ArrayList<MeoGroup> listGroup = new ArrayList<>();
    private MeoExpandableListViewAdapter lythuyetAdapter;
    private ExpandableListView elvMeoLyThuyet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meo);
        addControls();
        addEvents();
    }

    private void addEvents() {
        elvMeoLyThuyet.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(MeoActivity.this, groupPosition + " - " + childPosition, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    private void addControls() {
        tabHostMeo = findViewById(R.id.tabHostMeo);
        tabHostMeo.setup();

        TabHost.TabSpec spec;
        spec = tabHostMeo.newTabSpec("Tab ly thuyet");
        spec.setContent(R.id.meo_tab_lythuyet);
        spec.setIndicator("Lý thuyết");
        tabHostMeo.addTab(spec);

        TabHost.TabSpec spec2;
        spec2 = tabHostMeo.newTabSpec("Tab thuc hanh");
        spec2.setContent(R.id.meo_tab_thuchanh);
        spec2.setIndicator("Thực hành");
        tabHostMeo.addTab(spec2);

        elvMeoLyThuyet = findViewById(R.id.elvMeoLyThuyet);
        lythuyetAdapter = new MeoExpandableListViewAdapter(MeoActivity.this, listGroup);
        elvMeoLyThuyet.setAdapter(lythuyetAdapter);

        loadData();
        expandAll();
    }

    private void loadData() {
        addItem("Mẹo Lý Thuyết 1", "Nội dung mẹo lý thuyết 1");
        addItem("Mẹo Lý Thuyết 1", "Nội dung mẹo lý thuyết 2");
        addItem("Mẹo Lý Thuyết 1", "Nội dung mẹo lý thuyết 3");
        addItem("Mẹo Lý Thuyết 1", "Nội dung mẹo lý thuyết 4");
        addItem("Mẹo Lý Thuyết 1", "Nội dung mẹo lý thuyết 5");
        addItem("Mẹo Lý Thuyết 1", "Nội dung mẹo lý thuyết 6");

        addItem("Mẹo Lý Thuyết 2", "Nội dung mẹo lý thuyết 2");
        addItem("Mẹo Lý Thuyết 2", "Nội dung mẹo lý thuyết 2");
        addItem("Mẹo Lý Thuyết 2", "Nội dung mẹo lý thuyết 2");
        addItem("Mẹo Lý Thuyết 2", "Nội dung mẹo lý thuyết 2");
        addItem("Mẹo Lý Thuyết 2", "Nội dung mẹo lý thuyết 2");
        addItem("Mẹo Lý Thuyết 2", "Nội dung mẹo lý thuyết 2");
        addItem("Mẹo Lý Thuyết 2", "Nội dung mẹo lý thuyết 2");
        addItem("Mẹo Lý Thuyết 2", "Nội dung mẹo lý thuyết 2");

        addItem("Mẹo Lý Thuyết 3", "Nội dung mẹo lý thuyết 3");
        addItem("Mẹo Lý Thuyết 3", "Nội dung mẹo lý thuyết 3");
        addItem("Mẹo Lý Thuyết 3", "Nội dung mẹo lý thuyết 3");
        addItem("Mẹo Lý Thuyết 3", "Nội dung mẹo lý thuyết 3");
        addItem("Mẹo Lý Thuyết 3", "Nội dung mẹo lý thuyết 3");
        addItem("Mẹo Lý Thuyết 3", "Nội dung mẹo lý thuyết 3");
        addItem("Mẹo Lý Thuyết 3", "Nội dung mẹo lý thuyết 3");
        addItem("Mẹo Lý Thuyết 3", "Nội dung mẹo lý thuyết 3");

        addItem("Mẹo Lý Thuyết 4", "Nội dung mẹo lý thuyết 4");
        addItem("Mẹo Lý Thuyết 4", "Nội dung mẹo lý thuyết 4");
        addItem("Mẹo Lý Thuyết 4", "Nội dung mẹo lý thuyết 4");
        addItem("Mẹo Lý Thuyết 4", "Nội dung mẹo lý thuyết 4");
        addItem("Mẹo Lý Thuyết 4", "Nội dung mẹo lý thuyết 4");
        addItem("Mẹo Lý Thuyết 4", "Nội dung mẹo lý thuyết 4");
    }

    private void addItem(String groupName, String itemName) {
        MeoGroup groupInfo = subjects.get(groupName);
        if(groupInfo == null)
        {
            groupInfo = new MeoGroup();
            groupInfo.setName(groupName);
            subjects.put(groupName, groupInfo);
            listGroup.add(groupInfo);
        }
        ArrayList<MeoItem> listItem = groupInfo.getListItem();
        int itemIndex = listItem.size();
        itemIndex++;
        MeoItem itemInfo = new MeoItem();
        itemInfo.setIndex(itemIndex);
        itemInfo.setName(itemName);
        listItem.add(itemInfo);
        groupInfo.setListItem(listItem);
        listGroup.set(listGroup.indexOf(groupInfo), groupInfo);
    }

    private void expandAll() {
        int groupCount = lythuyetAdapter.getGroupCount();
        for(int i = 0; i < groupCount; i++)
        {
            elvMeoLyThuyet.expandGroup(i);
        }
    }
}
