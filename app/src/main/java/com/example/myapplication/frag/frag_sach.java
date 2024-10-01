package com.example.myapplication.frag;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.SachAdapter;
import com.example.myapplication.dao.LoaiSachDao;
import com.example.myapplication.dao.SachDao;
import com.example.myapplication.model.LoaiSach;
import com.example.myapplication.model.Sach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

public class frag_sach extends Fragment {
    ArrayList<Sach> list;
    SachDao dao;
    SachAdapter adapter;
    RecyclerView rc;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_sach, container,false);
        rc = view.findViewById(R.id.rcvsach);
        dao = new SachDao(getContext());
        list = dao.getDSSach();
        FloatingActionButton fl = view.findViewById(R.id.fltsach);
        //
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rc.setLayoutManager(layoutManager);
        //
        adapter = new SachAdapter(getContext(), list);
        rc.setAdapter(adapter);
        fl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        SearchView srview = view.findViewById(R.id.searchSach);
        srview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<Sach> newlist = new ArrayList<>();
                for (Sach s : list){
                    if (s.getMausac().toLowerCase().contains(newText.toLowerCase())){
                        newlist.add(s);
                    }
                }
                loadriengchotimkiem(newlist);
                return false;
            }
        });


        return view;
    }
    private void loadriengchotimkiem (ArrayList<Sach> list){
        //data
        adapter = new SachAdapter(getContext(), list);
        rc.setAdapter(adapter);
    }
private void showDialog() {
    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
    LayoutInflater inflater = getLayoutInflater();
    View view = inflater.inflate(R.layout.sach_up_add, null);
    builder.setView(view);
    ImageView imgadd = view.findViewById(R.id.txtSach_up_add_A);
    ImageView imgup = view.findViewById(R.id.txtSach_up_add_U);
    AlertDialog alertDialog = builder.create();
    imgadd.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                LayoutInflater inflater1 = getLayoutInflater();
                View view1 = inflater1.inflate(R.layout.item_add_sach, null);
                builder1.setView(view1);
                EditText edtten = view1.findViewById(R.id.txttensach_S_A);
                EditText edttien = view1.findViewById(R.id.txtgiathue_S_A);
                EditText mausac = view1.findViewById(R.id.chonmausacS_A);
                EditText soluong = view1.findViewById(R.id.soluong_A);
                Spinner spn = view1.findViewById(R.id.spnloaisach_S_A);

            //DIALOG
    mausac.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final String[] mausacOptions = new String[]{"#Tim", "#Do", "#Vang"};
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("Chọn mau sac");
            builder.setItems(mausacOptions, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Xử lý khi người dùng chọn giới tính
                    String selectedGioiTinh = mausacOptions[which];
                    mausac.setText(selectedGioiTinh);


                    adapter.notifyDataSetChanged();
                    dialog.dismiss(); // Đóng hộp thoại sau khi chọn
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    });
                SimpleAdapter simpleAdapter = new SimpleAdapter(
                        getContext(),
                        getDSLoaisach(),
                        android.R.layout.simple_list_item_1,
                        new String[]{"tenloai"},
                        new int[]{android.R.id.text1}
                );
                spn.setAdapter(simpleAdapter);
            Button btnup = view1.findViewById(R.id.addS_A);
            Button btnhuy = view1.findViewById(R.id.huyS_A);
            AlertDialog alertDialog = builder1.create();
            btnup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    HashMap<String, Object> hs = (HashMap<String, Object>) spn.getSelectedItem();
                    int maloai = (int) hs.get("maloai");//lay maloai thi get key maloai
                    try{

                        if (edtten.getText().toString().equals("")){
                            Toast.makeText(getContext(), "Nhap hết thông tin", Toast.LENGTH_SHORT).show();
                            return;
                        }else if (edttien.getText().toString().equals("")){
                            Toast.makeText(getContext(), "Nhap hết thông tin", Toast.LENGTH_SHORT).show();
                            return;
                        }else if(soluong.getText().toString().equals("")){
                                Toast.makeText(getContext(), "Nhap hết thông tin", Toast.LENGTH_SHORT).show();
                                return;
                            }else{

                            }
                        String tensach = edtten.getText().toString();
                        int tien = Integer.parseInt(edttien.getText().toString());
                        String mausacS = mausac.getText().toString();
                        int soluongS = Integer.parseInt(soluong.getText().toString());

                        boolean kt = dao.addSach(tensach, tien, maloai, mausacS, soluongS);
                    if(kt){
                        list.clear();
                        list.addAll(dao.getDSSach());
                        adapter.notifyDataSetChanged();
                        alertDialog.dismiss();
                        Toast.makeText(getContext(), "Them thanh cong", Toast.LENGTH_SHORT).show();
                    }
                    }catch (NumberFormatException e){
                        Log.i(TAG, "loi", e);
                    }
                }
            });
            btnhuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                    alertDialog.dismiss();
                }
            });

                alertDialog.show();
        }
    });
    imgup.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder2 = new AlertDialog.Builder(getContext());
            LayoutInflater inflater2 = getLayoutInflater();
            View view2 = inflater2.inflate(R.layout.item_up_sach, null);
            builder2.setView(view2);
            EditText edtma = view2.findViewById(R.id.txtmasach_S_U);
            EditText edtten = view2.findViewById(R.id.txttensach_S_U);
            EditText edttien = view2.findViewById(R.id.txtgiathue_S_U);
            Spinner spn = view2.findViewById(R.id.spnloaisach_S_U);

            SimpleAdapter simpleAdapter = new SimpleAdapter(
                    getContext(),
                    getDSLoaisach(),
                    android.R.layout.simple_list_item_1,
                    new String[]{"tenloai"},
                    new int[]{android.R.id.text1}
            );
            spn.setAdapter(simpleAdapter);
            Button btnup = view2.findViewById(R.id.update_S_U);
            Button btnhuy = view2.findViewById(R.id.huyS_U);
            AlertDialog alertDialog = builder2.create();
           btnup.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   ///
                   HashMap<String, Object> hs = (HashMap<String, Object>) spn.getSelectedItem();
                   int maloai = (int) hs.get("maloai");//lay maloai thi get key maloai
                   try{
                   if (edtma.getText().toString().equals("")){
                       Toast.makeText(getContext(), "Nhap ma", Toast.LENGTH_SHORT).show();
                       return;
                   }else if (edtten.getText().toString().equals("")){
                       Toast.makeText(getContext(), "Nhap hết thông tin", Toast.LENGTH_SHORT).show();
                       return;
                       }else if (edttien.getText().toString().equals("")){
                           Toast.makeText(getContext(), "Nhap hết thông tin", Toast.LENGTH_SHORT).show();
                           return;
//                        }else if(luong  < 1000000){
//                                Toast.makeText(getContext(), "Luong phai lon hon 1 000 000", Toast.LENGTH_SHORT).show();
//                                return;
                       }else{

                       }
                       String tensach = edtten.getText().toString();
                       int masach = Integer.parseInt(edtma.getText().toString());
                       int tien = Integer.parseInt(edttien.getText().toString());

                       boolean kt = dao.UpSach(masach ,tensach, tien, maloai);
                       if(kt){
                           list.clear();
                           list.addAll(dao.getDSSach());
                           adapter.notifyDataSetChanged();
                           alertDialog.dismiss();
                           Toast.makeText(getContext(), "Cap nhat thanh cong", Toast.LENGTH_SHORT).show();
                       }else{
                           Toast.makeText(getContext(), "That bai", Toast.LENGTH_SHORT).show();
                       }
                   }catch (NumberFormatException e){
                       Log.i(TAG, "loi", e);
                   }
               }
           });
            btnhuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                    alertDialog.dismiss();
                }
            });

            alertDialog.show();
        }
    });


    alertDialog.show();
}



    private ArrayList<HashMap<String, Object>> getDSLoaisach() {
        LoaiSachDao loaisachdao = new LoaiSachDao(getContext());
        ArrayList<LoaiSach> list = loaisachdao.getDSLoaiSach();
        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();
        for (LoaiSach ls : list) {
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("maloai", ls.getId());
            hs.put("tenloai", ls.getTenloai());//lay len spinner
            listHM.add(hs);
        }
        return listHM;
    }
}


