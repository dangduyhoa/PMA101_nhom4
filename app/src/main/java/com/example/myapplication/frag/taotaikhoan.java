package com.example.myapplication.frag;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.dao.ThuThuDao;

public class taotaikhoan extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.taotaikhoan, container, false);
        EditText txtma = view.findViewById(R.id.ma_tk);
        EditText txtten = view.findViewById(R.id.ten_tk);
        EditText txtpass = view.findViewById(R.id.matkhau_tk);
        Button btndk = view.findViewById(R.id.dangky);
        ThuThuDao dao = new ThuThuDao(getContext());
        btndk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = txtma.getText().toString();
                String ten = txtten.getText().toString();
                String pass = txtpass.getText().toString();
                if(dao.signup(user,ten, pass)){
                    Toast.makeText(getContext(), "Thành công", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "Nhap sai", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}
