package com.example.demo_chungchi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.model.BaiGiang;
import com.example.model.BaiGiangExpandableLvAdapter;
import com.example.model.GroupBaiGiang;

import java.util.ArrayList;

public class HocActivity extends AppCompatActivity {

    private ImageView imgBack;

    private ExpandableListView elvBaiGiang;
    private BaiGiangExpandableLvAdapter baiGiangExpandableLvAdapter;
    private ArrayList<GroupBaiGiang> listGroupBG;
    private ArrayList<BaiGiang> listBaiGiang;
    private GroupBaiGiang groupBaiGiang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoc);
        setTitle("HỌC");
        addViews();
        addEvents();
    }

    private void addViews() {
        imgBack = findViewById(R.id.imgBack);

        listGroupBG = new ArrayList<>();
        listBaiGiang = new ArrayList<>();

        elvBaiGiang = findViewById(R.id.elvBaiGiang);
        baiGiangExpandableLvAdapter = new BaiGiangExpandableLvAdapter(getApplicationContext(), listGroupBG);
        elvBaiGiang.setAdapter(baiGiangExpandableLvAdapter);

        addData();
        expandAll();
    }

    private void expandAll() {
        for(int i = 0; i < baiGiangExpandableLvAdapter.getGroupCount(); i++)
        {
            elvBaiGiang.expandGroup(i);
        }
    }

    private void addData() {
        listBaiGiang = new ArrayList<>();
        listBaiGiang.add(new BaiGiang(1, "Hệ Điều Hành", "mtcb", "1_hdh.pdf"));
        listBaiGiang.add(new BaiGiang(2, "Tệp Tin Và Thư Mục", "mtcb", "2_fnf.pdf"));
        listBaiGiang.add(new BaiGiang(3, "Phần Cứng", "mtcb", "3_pc.pdf"));
        listBaiGiang.add(new BaiGiang(4, "Control Panel", "mtcb", "4_cpanel.pdf"));
        listBaiGiang.add(new BaiGiang(5, "Phần Mềm", "mtcb", "5_pm.pdf"));
        listBaiGiang.add(new BaiGiang(6, "Xử Lý Sự Cố", "mtcb", "6_xulysuco.pdf"));
        groupBaiGiang = new GroupBaiGiang("MODULE 1: MÁY TÍNH CĂN BẢN", listBaiGiang, 1);
        listGroupBG.add(groupBaiGiang);

        listBaiGiang = new ArrayList<>();
        listBaiGiang.add(new BaiGiang(7, "Tính Năng Phổ Biến", "udcc", "7_tinhnangpb.pdf"));
        listBaiGiang.add(new BaiGiang(8, "Microsoft Word", "udcc", "8_word.pdf"));
        listBaiGiang.add(new BaiGiang(9, "Microsoft Excel", "udcc", "9_excel.pdf"));
        listBaiGiang.add(new BaiGiang(10, "Microsoft Powerpoint", "udcc", "10_ppt.pdf"));
        listBaiGiang.add(new BaiGiang(11, "Microsoft Access", "udcc", "11_access.pdf"));
        groupBaiGiang = new GroupBaiGiang("MODULE 2: ỨNG DỤNG CHỦ CHỐT", listBaiGiang, 2);
        listGroupBG.add(groupBaiGiang);

        listBaiGiang = new ArrayList<>();
        listBaiGiang.add(new BaiGiang(12, "World Wide Web", "cstt", "12_www.pdf"));
        listBaiGiang.add(new BaiGiang(13, "Kết Nối Mạng", "cstt", "13_ketnoimang.pdf"));
        listBaiGiang.add(new BaiGiang(14, "Truyền Thông Kỹ Thuật Số", "cstt", "14_truyenthongkts.pdf"));
        listBaiGiang.add(new BaiGiang(15, "Công Dân Kỉ Nguyên Số", "cstt", "15_congdankns.pdf"));
        listBaiGiang.add(new BaiGiang(16, "Tìm Kiếm Thông Tin", "cstt", "16_timkiemtt.pdf"));
        groupBaiGiang = new GroupBaiGiang("MODULE 3: CUỘC SỐNG TRỰC TUYẾN", listBaiGiang, 3);
        listGroupBG.add(groupBaiGiang);

    }

    private void addEvents() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        elvBaiGiang.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(HocActivity.this, listGroupBG.get(groupPosition).getName() + " - " + listGroupBG.get(groupPosition).getListBG().get(childPosition).getFileName(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), PDFViewActivity.class);
                intent.putExtra("baiGiang", listGroupBG.get(groupPosition).getListBG().get(childPosition));
                startActivity(intent);

                return false;
            }
        });
    }

}
