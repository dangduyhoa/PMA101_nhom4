package com.example.myapplication.thanhvien;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.LoaiSachAdapter;
import com.example.myapplication.adapter.SachAdapter;
import com.example.myapplication.dao.LoaiSachDao;
import com.example.myapplication.dao.SachDao;
import com.example.myapplication.model.LoaiSach;
import com.example.myapplication.model.Sach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class fag_sach extends Fragment {
    RecyclerView rcv;
    SachDao dao;
    SachAdapter_thanhvien adapter;
    ArrayList<Sach> list;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fag_sach, container, false);

        return view;
    }

}
