package com.example;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.Exam.DBHelper;
import com.example.demo_chungchi.BoCauHoiActivity;
import com.example.demo_chungchi.HocActivity;
import com.example.demo_chungchi.LichSuLamBaiActivity;
import com.example.demo_chungchi.R;
import com.example.demo_chungchi.TacgiaActivity;
import com.example.demo_chungchi.ThiThuActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.io.File;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public LinearLayout lnlHoc, lnlThi, lnlMeo, lnlCauSai, lnlBocauhoi, lnlCongDong;
    public ImageView btnHoc, btnThi, btnMeo, btnCauSai, btnBocauhoi, btnCongDong;
    private int dem = 0;
    TextView tvThithu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("My IC3");
        addControls();
        addEvents();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                String emailAddr = "tranhailong0807@gmail.com";
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Phản Hồi Của Người Dùng");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{emailAddr});
                startActivity(Intent.createChooser(emailIntent, "Gửi phản hồi"));
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        DBHelper db = new DBHelper(this);

        String storage_folder = "/myapp/db";

        File f = new File(Environment.getExternalStorageDirectory(), storage_folder);
        if (!f.exists()) {
            f.mkdirs();
        }

//        try {
//            db.deleteDataBase();
//            Toast.makeText(this, "Xóa thành công", Toast.LENGTH_SHORT).show();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            Toast.makeText(this, "bi loi rui", Toast.LENGTH_SHORT).show();
//        }
//
//        try {
//            db.createDataBase();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    private void addEvents() {
        lnlHoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enableViews(false);
                Intent intent = new Intent(MainActivity.this, HocActivity.class);dem = 0;
                startActivity(intent);
            }
        });
        btnHoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enableViews(false);
                Intent intent = new Intent(MainActivity.this, HocActivity.class);dem = 0;
                startActivity(intent);
            }
        });

        lnlThi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enableViews(false);
                Intent intent = new Intent(MainActivity.this, ThiThuActivity.class);dem = 0;
                startActivity(intent);
            }
        });
        btnThi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enableViews(false);
                Intent intent = new Intent(MainActivity.this, ThiThuActivity.class);dem = 0;
                startActivity(intent);
            }
        });

        lnlMeo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enableViews(false);
                Intent intent = new Intent(Intent.ACTION_VIEW);dem = 0;
                intent.setData(Uri.parse("https://hourofcode.vn/kinh-nghiem-lam-bai-thi-ic3-dat-hieu-qua-nhat/"));
                startActivity(intent);
            }
        });

        btnMeo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enableViews(false);
                Intent intent = new Intent(Intent.ACTION_VIEW);dem = 0;
                intent.setData(Uri.parse("https://hourofcode.vn/kinh-nghiem-lam-bai-thi-ic3-dat-hieu-qua-nhat/"));
                startActivity(intent);
            }
        });

        lnlCauSai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enableViews(false);
                Intent intent = new Intent(MainActivity.this, LichSuLamBaiActivity.class);dem = 0;
                startActivity(intent);
            }
        });

        btnCauSai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enableViews(false);
                Intent intent = new Intent(MainActivity.this, LichSuLamBaiActivity.class);dem = 0;
                startActivity(intent);
            }
        });

        lnlBocauhoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enableViews(false);
                Intent intent = new Intent(MainActivity.this, BoCauHoiActivity.class);
                dem = 0;
                startActivity(intent);
            }
        });

        btnBocauhoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enableViews(false);
                Intent intent = new Intent(MainActivity.this, BoCauHoiActivity.class);
                dem = 0;
                startActivity(intent);
            }
        });

        lnlCongDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enableViews(false);
                Intent intent = new Intent(Intent.ACTION_VIEW);dem = 0;
                intent.setData(Uri.parse("http://haint86.blogspot.com/2015/03/tai-lieu-on-thi-ic3-gs4.html"));
                startActivity(intent);
            }
        });

        btnCongDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enableViews(false);
                Intent intent = new Intent(Intent.ACTION_VIEW);dem = 0;
                intent.setData(Uri.parse("http://haint86.blogspot.com/2015/03/tai-lieu-on-thi-ic3-gs4.html"));
                startActivity(intent);
            }
        });

    }

    private void addControls() {
        tvThithu = findViewById(R.id.tvThithu);

        lnlHoc = findViewById(R.id.lnlHoc);
        lnlThi = findViewById(R.id.lnlThi);
        lnlMeo = findViewById(R.id.lnlMeo);
        lnlCauSai = findViewById(R.id.lnlCauSai);
        lnlBocauhoi = findViewById(R.id.lnlLuyentap);
        lnlCongDong = findViewById(R.id.lnlCongDong);

        btnHoc = findViewById(R.id.btnHoc);
        btnThi = findViewById(R.id.btnThi);
        btnMeo = findViewById(R.id.btnMeo);
        btnCauSai = findViewById(R.id.btnCauSai);
        btnBocauhoi = findViewById(R.id.btnBocauhoi);
        btnCongDong = findViewById(R.id.btnCongDong);
    }

    public void enableViews(boolean b)
    {
        lnlThi.setEnabled(b);
        btnThi.setEnabled(b);

        lnlCauSai.setEnabled(b);
        btnCauSai.setEnabled(b);

        lnlHoc.setEnabled(b);
        btnHoc.setEnabled(b);

        lnlBocauhoi.setEnabled(b);
        btnBocauhoi.setEnabled(b);

        lnlMeo.setEnabled(b);
        btnMeo.setEnabled(b);

        lnlCongDong.setEnabled(b);
        btnCongDong.setEnabled(b);
    }

    @Override
    protected void onResume() {
        super.onResume();
        enableViews(true);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if(dem == 0)
            {
                Toast.makeText(this, "Press back one more time to exit", Toast.LENGTH_SHORT).show();
                dem = 1;
            }
            else
            {
                super.onBackPressed();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(getApplicationContext(), TacgiaActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_thi) {
            // Handle the camera action
            Intent intent = new Intent(MainActivity.this, ThiThuActivity.class);dem = 0;
            startActivity(intent);
        } else if (id == R.id.nav_lsthi) {
            Intent intent = new Intent(MainActivity.this, LichSuLamBaiActivity.class);dem = 0;
            startActivity(intent);
        } else if (id == R.id.nav_bocauhoi) {
            Intent intent = new Intent(MainActivity.this, BoCauHoiActivity.class);
            dem = 0;
            startActivity(intent);
        } else if (id == R.id.nav_ontap) {
            Intent intent = new Intent(MainActivity.this, HocActivity.class);dem = 0;
            startActivity(intent);
        } else if (id == R.id.nav_share) {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Insert subject here");
            String appUrl = "https://www.google.com";
            shareIntent.putExtra(Intent.EXTRA_TEXT, appUrl);
            startActivity(Intent.createChooser(shareIntent, "Chia sẻ ứng dụng"));
        } else if (id == R.id.nav_send) {
//            String emailAddr = "tranhailong0807@gmail.com";
//            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"));
//            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Phản Hồi Của Người Dùng");
//            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{emailAddr});
//            startActivity(Intent.createChooser(emailIntent, "Gửi phản hồi"));

            Intent intent = new Intent(getApplicationContext(), TacgiaActivity.class);
            startActivity(intent);
            return true;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.clearFocus();
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

}
