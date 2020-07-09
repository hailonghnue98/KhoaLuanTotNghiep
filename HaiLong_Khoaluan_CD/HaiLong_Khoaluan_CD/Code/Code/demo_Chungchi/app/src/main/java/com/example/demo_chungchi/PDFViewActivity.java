package com.example.demo_chungchi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.model.BaiGiang;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnPageScrollListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;

public class PDFViewActivity extends AppCompatActivity {

    private TextView tvSlideName, tvPage, tvVeDau, tvVeCuoi;
    private PDFView pdfView;
    private ImageView imgClose;
    private BaiGiang baiGiang;
    private String fileName = "1_hdh.pdf";
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfview);
        addViews();
        addEvents();
    }

    private void addViews() {
        tvSlideName = findViewById(R.id.tvSlideName);
        tvPage = findViewById(R.id.tvPage);
        tvVeDau = findViewById(R.id.tvVeDau);
        tvVeCuoi = findViewById(R.id.tvVeCuoi);
        imgClose = findViewById(R.id.imgClose);
        pdfView = findViewById(R.id.pdfView);

        intent = getIntent();

        baiGiang = (BaiGiang) intent.getSerializableExtra("baiGiang");

        fileName = baiGiang.getFileName();

        setupPDFView(fileName);

        tvSlideName.setText(baiGiang.getMaBaiGiang() + ". " + baiGiang.getTenBaiGiang());
        tvPage.setText("1/" + pdfView.getPageCount());
    }

    private void setupPDFView(String fileName) {

        pdfView.fromAsset(fileName)
                .enableSwipe(true) // allows to block changing pages using swipe
                //Start Enable flip like ViewPager
                .swipeHorizontal(true)
                .pageSnap(true)
                .autoSpacing(true)
                .pageFling(true)
                //End Enable flip like ViewPager
                .enableDoubletap(true)
                .defaultPage(0)
                // allows to draw something on the current page, usually visible in the middle of the screen
                .scrollHandle(new DefaultScrollHandle(this, true))
                .onPageScroll(new OnPageScrollListener() {
                    @Override
                    public void onPageScrolled(int page, float positionOffset) {
                        tvPage.setText((pdfView.getCurrentPage() + 1) + "/" + pdfView.getPageCount());
//                        Toast.makeText(PDFViewActivity.this, (pdfView.getCurrentPage() + 1) + "/" + pdfView.getPageCount(), Toast.LENGTH_SHORT).show();
                    }
                })
                .enableAntialiasing(true) // improve rendering a little bit on low-res screens
                // spacing between pages in dp. To define spacing color, set view background
                .fitEachPage(true) // fit each page to the view, else smaller pages are scaled relative to largest page.
                .load();
    }

    private void addEvents() {
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ShowPagesActivity.class);
                intent.putExtra("pageCount", pdfView.getPageCount());
                startActivityForResult(intent, 101);
//                pdfView.jumpTo(10, true);
            }
        });

        tvVeDau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pdfView.jumpTo(0, true);
                tvPage.setText((pdfView.getCurrentPage() + 1) + "/" + pdfView.getPageCount());

            }
        });


        tvVeCuoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pdfView.jumpTo(pdfView.getPageCount() - 1, true);
                tvPage.setText((pdfView.getCurrentPage() + 1) + "/" + pdfView.getPageCount());
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 101 && resultCode == 102)
        {
            int pickedPage = data.getIntExtra("pickedPage", 0);
            pdfView.jumpTo(pickedPage);
            tvPage.setText((pdfView.getCurrentPage() + 1) + "/" + pdfView.getPageCount());

        }
    }
}
