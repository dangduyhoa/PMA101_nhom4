package com.example.myapplication.thanhvien;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.dao.PhieuMuonDao;
import com.example.myapplication.model.PhieuMuon;

import java.util.ArrayList;

public class phieumuon_thanhvien extends AppCompatActivity {

    RecyclerView rcv;
    PhieuMuonDao dao;
    PhieuMuonAdapter_thanhvien adapter;
    ArrayList<PhieuMuon> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phieumuon_thanhvien);

        rcv = findViewById(R.id.rcv_tv);

        dao = new PhieuMuonDao(this);
        SharedPreferences sharedPreferences = this.getSharedPreferences("Thanhvien", Context.MODE_PRIVATE);
        String id = sharedPreferences.getString("matv","");
        list = dao.getDSphieumuon_thanhvien(Integer.parseInt(id));
        Log.d("check", list.size()+"");
        adapter = new PhieuMuonAdapter_thanhvien(list, this);
        rcv.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rcv.setLayoutManager(layoutManager);
        adapter.notifyDataSetChanged();

        Button back = findViewById(R.id.comeback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(phieumuon_thanhvien.this,MainActivity_thanhvien.class);
                startActivity(intent);
            }
        });

    }
}