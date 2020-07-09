package com.example.demo_chungchi;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.example.MainActivity;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EasySplashScreen config = new EasySplashScreen(SplashScreen.this)
                .withFullScreen()
                .withTargetActivity(MainActivity.class)
                .withSplashTimeOut(4000)
                .withBackgroundColor(Color.WHITE)
                .withLogo(R.drawable.ic3_logo_2)
                .withHeaderText("TRƯỜNG ĐẠI HỌC SƯ PHẠM HÀ NỘI")
                .withFooterText("COPYRIGHT BY HAI LONG - FIT.HNUE")
                .withBeforeLogoText("KHOA CÔNG NGHỆ THÔNG TIN")
                .withAfterLogoText("MY IC3");
        //Set text color
//        config.getHeaderTextView().setTextColor(Color.WHITE);
//        config.getFooterTextView().setTextColor(Color.WHITE);
//        config.getBeforeLogoTextView().setTextColor(Color.WHITE);
//        config.getAfterLogoTextView().setTextColor(Color.WHITE);
        config.getHeaderTextView().setTextSize(14);
        config.getHeaderTextView().setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        //Set to view
        View view = config.create();

        //Set view to content view
        setContentView(view);
    }
}
