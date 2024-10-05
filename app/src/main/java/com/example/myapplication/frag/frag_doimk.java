package com.example.myapplication.frag;

import android.content.Context;
import android.content.SharedPreferences;
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

public class frag_doimk extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_doimk, container, false);

        EditText txtcu = view.findViewById(R.id.cu);
        EditText txtmoi = view.findViewById(R.id.moi);
        EditText txtlaimoi = view.findViewById(R.id.laimoi);
        Button btndoi = view.findViewById(R.id.doi);
        btndoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cu = txtcu.getText().toString();
                String moi = txtmoi.getText().toString();
                String laimoi = txtlaimoi.getText().toString();
                if (cu.equals("") || moi.equals("") || laimoi.equals("")){
                    Toast.makeText(getContext(), "Nhap day du thong tin", Toast.LENGTH_SHORT).show();
                }
                if(moi.equals(laimoi)){
                    SharedPreferences sharedPreferences = getContext().getSharedPreferences("Thongtin", Context.MODE_PRIVATE);
                    String matt = sharedPreferences.getString("matt", "");
                    //cap nhat
                    ThuThuDao dao = new ThuThuDao(getContext());
                    int kt = dao.capnhatpass(matt, cu, moi);
                    if(kt == 1) {
//                        Intent intent = new Intent(getContext(), dangnhap.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                        startActivity(intent);
                        Toast.makeText(getContext(), "Thanh cong", Toast.LENGTH_SHORT).show();
                    }else if(kt == 0){
                        Toast.makeText(getContext(), "mat khau cu khong dung", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getContext(), "That bai", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getContext(), "khong trung mat khau", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }


}
