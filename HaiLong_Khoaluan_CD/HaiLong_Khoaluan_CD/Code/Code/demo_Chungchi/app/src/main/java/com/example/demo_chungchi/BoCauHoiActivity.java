package com.example.demo_chungchi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.model.Module;
import com.example.model.ModuleAdapter;

public class BoCauHoiActivity extends AppCompatActivity {

    private ImageView imgBack;
    private GridView gvBocauhoi;
    private ModuleAdapter moduleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bo_cau_hoi);
        addViews();
        addEvents();
    }

    private void addEvents() {
        gvBocauhoi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0)
                {
                    Intent intent = new Intent(getApplicationContext(), Module1Activity.class);
                    startActivity(intent);
                }
                else if(position == 1)
                {
                    Intent intent = new Intent(getApplicationContext(), Module2Activity.class);
                    startActivity(intent);
                }
                else if(position == 2)
                {
                    Intent intent = new Intent(getApplicationContext(), Module3Activity.class);
                    startActivity(intent);
                }
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void addViews() {
        imgBack = findViewById(R.id.imgBack);
        gvBocauhoi = findViewById(R.id.gvBocauhoi);
        moduleAdapter = new ModuleAdapter(this, R.layout.bocauhoi_module_item);
        addData();
        gvBocauhoi.setAdapter(moduleAdapter);
    }

    private void addData() {
        moduleAdapter.add(new Module("MÁY TÍNH CĂN BẢN", "MODULE 1", R.drawable.mtcb));
        moduleAdapter.add(new Module("ỨNG DỤNG CHỦ CHỐT", "MODULE 2", R.drawable.udcc));
        moduleAdapter.add(new Module("CUỘC SỐNG TRỰC TUYẾN", "MODULE 3", R.drawable.cstt));
    }
}
