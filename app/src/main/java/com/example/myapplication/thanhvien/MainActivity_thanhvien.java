package com.example.myapplication.thanhvien;

import android.os.Bundle;

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

    }
}