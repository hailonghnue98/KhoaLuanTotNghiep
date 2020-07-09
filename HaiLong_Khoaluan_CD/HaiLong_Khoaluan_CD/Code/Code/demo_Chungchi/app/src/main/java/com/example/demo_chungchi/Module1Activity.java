package com.example.demo_chungchi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.Slide.BoCauHoiScreenSlideActivity;
import com.example.model.SubModule;
import com.example.model.SubModuleAdapter;

public class Module1Activity extends AppCompatActivity {

    private ImageView imgBack;
    private ListView lvModule1;
    private SubModuleAdapter subModuleAdapter;
    private String subject = "";
    private Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module1);
        addViews();
        addEvents();
    }

    private void addEvents() {
        lvModule1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0)
                {
                    Intent intent = new Intent(getApplicationContext(), BoCauHoiScreenSlideActivity.class);
                    subject = "pc";
                    bundle = new Bundle();
                    bundle.putString("subject", subject);
                    intent.putExtra("bundle", bundle);
                    startActivity(intent);
                }
                else if(position == 1)
                {
                    Intent intent = new Intent(getApplicationContext(), BoCauHoiScreenSlideActivity.class);
                    subject = "pm";
                    bundle = new Bundle();
                    bundle.putString("subject", subject);
                    intent.putExtra("bundle", bundle);
                    startActivity(intent);
                }
                else if(position == 2)
                {
                    Intent intent = new Intent(getApplicationContext(), BoCauHoiScreenSlideActivity.class);
                    subject = "hdh";
                    bundle = new Bundle();
                    bundle.putString("subject", subject);
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
        lvModule1 = findViewById(R.id.lvModule1);
        subModuleAdapter = new SubModuleAdapter(this, R.layout.bocauhoi_module_details_item);
        addData();
        lvModule1.setAdapter(subModuleAdapter);
    }

    private void addData() {
        subModuleAdapter.add(new SubModule(R.drawable.mainboard1, "PHẦN CỨNG"));
        subModuleAdapter.add(new SubModule(R.drawable.software, "PHẦN MỀM"));
        subModuleAdapter.add(new SubModule(R.drawable.linux, "HỆ ĐIỀU HÀNH"));
    }
}
