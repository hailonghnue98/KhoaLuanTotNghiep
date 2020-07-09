package com.example.demo_chungchi;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TacgiaActivity extends AppCompatActivity {

    ImageView imgBack;
    TextView tvLienhe;
    public static String FACEBOOK_URL = "https://www.facebook.com/hailonghnue98";
    public static String FACEBOOK_PAGE_ID = "hailonghnue98";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tacgia);
        addViews();
        addEvents();
    }

    private void addViews() {
        imgBack = findViewById(R.id.imgBack);
        tvLienhe = findViewById(R.id.tvLienhe);
    }

    private void addEvents() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });

        tvLienhe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                String facebookURL = getFacebookPageURL(TacgiaActivity.this);
                intent.setData(Uri.parse("fb://facewebmodal/f?href=https://www.facebook.com/hailonghnue98"));
                startActivity(intent);
            }
        });
    }

    //method to get the right URL to use in the intent
    public String getFacebookPageURL(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) { //newer versions of fb app
                return "fb://facewebmodal/f?href=" + FACEBOOK_URL;
            } else { //older versions of fb app
                return "fb://page/" + FACEBOOK_PAGE_ID;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return FACEBOOK_URL; //normal web url
        }
    }

}
