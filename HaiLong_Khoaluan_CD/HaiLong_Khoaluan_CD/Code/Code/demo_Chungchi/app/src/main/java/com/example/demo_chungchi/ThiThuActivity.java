package com.example.demo_chungchi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.Slide.ThiScreenSlideActivity;
import com.example.model.Module;
import com.example.model.ModuleAdapter;

public class ThiThuActivity extends AppCompatActivity {

    private ImageView imgBack;
    private GridView gvBocauhoi;
    private ModuleAdapter moduleAdapter;
    private Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thi_thu);
        addViews();
        addEvents();
    }

    private void addEvents() {
        gvBocauhoi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0)
                {
                    Intent intent = new Intent(getApplicationContext(), ThiScreenSlideActivity.class);
                    bundle.putString("maModule", "1");
                    intent.putExtra("bundle", bundle);
                    startActivity(intent);
                }
                else if(position == 1)
                {
                    Intent intent = new Intent(getApplicationContext(), ThiScreenSlideActivity.class);
                    bundle.putString("maModule", "2");
                    intent.putExtra("bundle", bundle);
                    startActivity(intent);
                }
                else if(position == 2)
                {
                    Intent intent = new Intent(getApplicationContext(), ThiScreenSlideActivity.class);
                    bundle.putString("maModule", "3");
                    intent.putExtra("bundle", bundle);
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
