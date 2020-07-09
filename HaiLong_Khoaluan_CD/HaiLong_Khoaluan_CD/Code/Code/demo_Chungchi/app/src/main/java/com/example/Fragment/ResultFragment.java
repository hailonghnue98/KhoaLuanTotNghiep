package com.example.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.Slide.ThiScreenSlideActivity;
import com.example.demo_chungchi.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResultFragment extends Fragment {

    FragmentManager fragmentManager;
    ThiScreenSlideActivity thiScreenSlideActivity;
    Bundle bundle = new Bundle();
    int id;
    int correct, wrong, miss;
    double score;
    int passed;
    String module;

    public ResultFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_result, container, false);
        thiScreenSlideActivity = (ThiScreenSlideActivity) getActivity();
        bundle = getArguments().getBundle("bundle");

        final TextView tvMade, tvModule, tvKetqua, tvCaudung, tvCausai, tvChualam, tvDiem, tvThoigian;
        ImageView imgCaudung, imgCausai, imgChualam, imgDiem, imgThoigian;
        Button btnXemlai, btnLamlai, btnThoat;
        fragmentManager = getActivity().getSupportFragmentManager();

        tvMade = view.findViewById(R.id.tvMaDe);
        tvModule = view.findViewById(R.id.tvModule);
        tvKetqua = view.findViewById(R.id.tvKetqua);
        tvCaudung = view.findViewById(R.id.tvCaudung);
        tvCausai = view.findViewById(R.id.tvCausai);
        tvChualam = view.findViewById(R.id.tvChualam);
        tvDiem = view.findViewById(R.id.tvDiem);
        tvThoigian = view.findViewById(R.id.tvThoigian);

        imgCaudung = view.findViewById(R.id.imgCaudung);
        imgCausai = view.findViewById(R.id.imgCausai);
        imgDiem = view.findViewById(R.id.imgDiem);
        imgThoigian = view.findViewById(R.id.imgThoigian);

        btnXemlai = view.findViewById(R.id.btnXemlai);
        btnLamlai = view.findViewById(R.id.btnLamlai);
        btnThoat = view.findViewById(R.id.btnThoat);

        id = getArguments().getInt("maDe", 0);
        module = getArguments().getString("maModule", "");
        score = Double.parseDouble(getArguments().getString("diem"));
        correct = getArguments().getInt("cauDung", 0);
        wrong = getArguments().getInt("cauSai", 0);
        miss = getArguments().getInt("cauChualam", 0);
        passed = getArguments().getInt("passed", 0);


        tvMade.setText("Mã đề: " + id);
        int tongCau = getArguments().getInt("tongCau", 0);
        tvCaudung.setText(getArguments().getInt("cauDung", 0) + "/" + tongCau);
        tvCausai.setText(getArguments().getInt("cauSai", 0) + "/" + tongCau);
        tvChualam.setText(getArguments().getInt("cauChualam", 0) + "/" + tongCau);
        tvDiem.setText((int)score+"");
//        if(getArguments().getDouble("diem", 0) >= 5 )

        if(module == "1")
        {
            tvModule.setText("MODULE 1: MÁY TÍNH CĂN BẢN");
        }
        else if(module == "2")
        {
            tvModule.setText("MODULE 2: ỨNG DỤNG CHỦ CHỐT");
        }
        else if(module == "3")
        {
            tvModule.setText("MODULE 3: CUỘC SỐNG TRỰC TUYẾN");
        }

        if(passed != 0)
        {
            tvKetqua.setText("Đỗ");
            tvKetqua.setTextColor(getResources().getColor(R.color.colorCaudung));
        }
        else
        {
            tvKetqua.setText("Trượt");
            tvKetqua.setTextColor(getResources().getColor(R.color.colorCausai));
        }
        tvThoigian.setText(getArguments().getString("tgLambai", "abc"));

        btnXemlai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager.popBackStack();
            }
        });

        btnLamlai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
                Intent intent = new Intent(getContext(), ThiScreenSlideActivity.class);
                bundle.putString("maModule", getArguments().getString("maModule"));
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            }
        });

        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });


        return view;
    }


}
