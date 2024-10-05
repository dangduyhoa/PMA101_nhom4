package com.example.myapplication.frag;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.Top10Adapter;
import com.example.myapplication.dao.thong_keDao;
import com.example.myapplication.model.Sach;

import java.util.ArrayList;

public class frag_top19 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_top10, container, false);
        RecyclerView rcv = view.findViewById(R.id.rectop10);
        //
        thong_keDao thongcThong_keDao = new thong_keDao(getContext());
        ArrayList<Sach> list = thongcThong_keDao.getTop10();
        //
        RecyclerView.LayoutManager nager = new LinearLayoutManager(getContext());
        rcv.setLayoutManager(nager);
        //
        Top10Adapter adapter = new Top10Adapter(getContext(), list);
        rcv.setAdapter(adapter);
        return view;
    }
}
