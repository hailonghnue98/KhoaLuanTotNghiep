package com.example.demo_chungchi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.model.PageAdapter;

import java.util.ArrayList;

public class ShowPagesActivity extends AppCompatActivity {

    private TextView tvSlideName;
    private ImageView imgClose;
    private GridView gvPages;
    private PageAdapter pageAdapter;
    private ArrayList<Integer> listPage;
    private int pageCount;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_pages);
        addViews();
        addEvents();
    }

    private void addViews() {
        tvSlideName = findViewById(R.id.tvSlideName);
        imgClose = findViewById(R.id.imgClose);
        gvPages = findViewById(R.id.gvPages);

        intent = getIntent();
        pageCount = intent.getIntExtra("pageCount", 0);

        listPage = new ArrayList<>();

        for(int i = 1; i <= pageCount; i++)
        {
            listPage.add(i);
        }

        pageAdapter = new PageAdapter(getApplicationContext(), listPage);

        gvPages.setAdapter(pageAdapter);
    }

    private void addEvents() {
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        gvPages.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ShowPagesActivity.this, listPage.get(position) + "", Toast.LENGTH_SHORT).show();
                intent.putExtra("pickedPage", listPage.get(position) - 1);
                setResult(102, intent);
                finish();
            }
        });
    }

}
