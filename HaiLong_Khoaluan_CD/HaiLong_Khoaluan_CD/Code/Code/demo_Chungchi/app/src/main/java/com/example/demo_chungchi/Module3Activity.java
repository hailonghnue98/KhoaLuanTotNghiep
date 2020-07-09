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
import com.example.model.Module;
import com.example.model.SubModule;
import com.example.model.SubModuleAdapter;

public class Module3Activity extends AppCompatActivity {

    private ListView lvModule3;
    private ImageView imgBack;
    private SubModuleAdapter subModuleAdapter;
    private String subject = "";
    private Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module3);
        addViews();
        addEvents();
    }

    private void addEvents() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        lvModule3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0)
                {
                    Intent intent = new Intent(getApplicationContext(), BoCauHoiScreenSlideActivity.class);
                    subject = "mvi";
                    bundle = new Bundle();
                    bundle.putString("subject", subject);
                    intent.putExtra("bundle", bundle);
                    startActivity(intent);
                }
                else if(position == 1)
                {
                    Intent intent = new Intent(getApplicationContext(), BoCauHoiScreenSlideActivity.class);
                    subject = "ttdt";
                    bundle = new Bundle();
                    bundle.putString("subject", subject);
                    intent.putExtra("bundle", bundle);
                    startActivity(intent);
                }
                else if(position == 2)
                {
                    Intent intent = new Intent(getApplicationContext(), BoCauHoiScreenSlideActivity.class);
                    subject = "sdi";
                    bundle = new Bundle();
                    bundle.putString("subject", subject);
                    intent.putExtra("bundle", bundle);
                    startActivity(intent);
                }
            }
        });
    }

    private void addViews() {
        lvModule3 = findViewById(R.id.lvModule3);
        imgBack = findViewById(R.id.imgBack);
        subModuleAdapter = new SubModuleAdapter(this, R.layout.bocauhoi_module_details_item);
        addData();
        lvModule3.setAdapter(subModuleAdapter);
    }

    private void addData() {
        subModuleAdapter.add(new SubModule(R.drawable.mangvainternet, "MẠNG VÀ INTERNET"));
        subModuleAdapter.add(new SubModule(R.drawable.gmail, "THƯ ĐIỆN TỬ"));
        subModuleAdapter.add(new SubModule(R.drawable.explorer, "SỬ DỤNG INTERNET"));
    }
}
