package com.example.myapplication.thanhvien;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.dao.SachDao;
import com.example.myapplication.model.Sach;

import java.util.ArrayList;

public class MainActivity_thanhvien extends AppCompatActivity {
    RecyclerView rcv;
    SachDao dao;
    SachAdapter_thanhvien adapter;
    ArrayList<Sach> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_thanhvien);
        rcv = findViewById(R.id.rcvsach_thanhvien);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rcv.setLayoutManager(layoutManager);

        dao = new SachDao(this);
        list = dao.getDSSach();
        adapter = new SachAdapter_thanhvien(this, list);
        rcv.setAdapter(adapter);

        ImageView kho = findViewById(R.id.khoPM);
        kho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_thanhvien.this,phieumuon_thanhvien.class);
                startActivity(intent);
            }
        });

    }
}