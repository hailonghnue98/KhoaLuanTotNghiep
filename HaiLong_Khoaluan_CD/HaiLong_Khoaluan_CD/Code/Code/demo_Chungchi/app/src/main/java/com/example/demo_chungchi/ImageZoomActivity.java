package com.example.demo_chungchi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.github.chrisbanes.photoview.PhotoView;

public class ImageZoomActivity extends AppCompatActivity {

    private PhotoView photoView;
    private ImageView imgClose;
    private String imageName;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_zoom);
        addViews();
        addEvents();
    }

    private void addViews() {
        intent = getIntent();

        photoView = findViewById(R.id.iv_photo);
        imgClose = findViewById(R.id.imgClose);

        imageName = intent.getStringExtra("imageName");
        if(!imageName.equals(""))
        {
            photoView.setImageResource(getResources().getIdentifier(imageName, "drawable", "com.example.demo_chungchi"));
        }
        else
        {
            photoView.setImageResource(R.drawable.question_default);
        }
    }

    private void addEvents() {
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
