package com.example.myapplication.frag;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.dao.thong_keDao;

import java.util.Calendar;

public class frg_doanhthufragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_doanhthu, container, false);
        EditText txtstart = view.findViewById(R.id.txtstart);
        EditText txtend = view.findViewById(R.id.txtEnd);
        Button btbthongke = view.findViewById(R.id.btnthongke);
        TextView txtkq= view.findViewById(R.id.ketqua);

        Calendar calendar = Calendar.getInstance();
        txtstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                //ngay, thang pai co 2 so
                               String ngay ="";
                               String thang ="";
                               if(dayOfMonth < 10){
                                   ngay ="0" + dayOfMonth;
                               }else {
                                   ngay = String.valueOf(dayOfMonth);
                               }
                               if((month + 1) <10){
                                   thang = "0" + dayOfMonth;
                               }else{
                                    thang = String.valueOf((month + 1));
                               }
                                txtstart.setText(year +"/" + thang + "/" + ngay);
                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );
                    datePickerDialog.show();
            }
        });

        //
        txtend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                //ngay, thang pai co 2 so
                                String ngay ="";
                                String thang ="";
                                if(dayOfMonth < 10){
                                    ngay ="0" + dayOfMonth;
                                }else {
                                    ngay = String.valueOf(dayOfMonth);
                                }
                                if((month + 1) <10){
                                    thang = "0" + (month + 1);
                                }else{
                                    thang = String.valueOf((month + 1));
                                }
                                txtend.setText(year +"/" + thang + "/" + ngay);
                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );
                datePickerDialog.show();
            }
        });
        //
        btbthongke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thong_keDao dao = new thong_keDao(getContext());
                String ngaydau =txtstart.getText().toString();
                String ngaycuoi =txtend.getText().toString();
                int doanhthu = dao.getDoanhThu(ngaydau, ngaycuoi);
                txtkq.setText(doanhthu + "NVD");
            }
        });
        return view;
    }
}
