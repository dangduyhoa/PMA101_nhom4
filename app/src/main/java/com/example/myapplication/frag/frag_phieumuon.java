package com.example.myapplication.frag;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.PhieuMuonAdapter;
import com.example.myapplication.dao.PhieuMuonDao;
import com.example.myapplication.dao.SachDao;
import com.example.myapplication.dao.ThanhVienDao;
import com.example.myapplication.model.PhieuMuon;
import com.example.myapplication.model.Sach;
import com.example.myapplication.model.ThanhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class frag_phieumuon extends Fragment {
    RecyclerView rec;
    PhieuMuonDao dao;
    ArrayList<PhieuMuon> list;
    PhieuMuonAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_phieumuon, container, false);

        rec = view.findViewById(R.id.rcvphieumuon);
        FloatingActionButton fltadd = view.findViewById(R.id.fltphieumuon);
        dao = new PhieuMuonDao(getContext());//tai vi minh dang o fragment
        list = dao.getDSphieumuon();
        //
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rec.setLayoutManager(linearLayoutManager);
        adapter = new PhieuMuonAdapter(list, getContext());
        rec.setAdapter(adapter);
        fltadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });


        //TIM KIEM
//        SearchView srview = view.findViewById(R.id.search_ctphieumuon);
//        srview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {

                //tìm kiếm số80 90
//                ArrayList<PhieuMuon> newlist = new ArrayList<>();
//                try {
//                    int mapm = Integer.parseInt(newText); // Chuyển đổi chuỗi nhập vào thành số nguyên
//                    for (PhieuMuon pm : list) {
//                        if (pm.getMapm() == mapm) { // So sánh mã phiếu mượn
//                            newlist.add(pm);
//                        }
//                    }
//                } catch (NumberFormatException e) {
//                    // Xử lý nếu không thể chuyển đổi thành số nguyên (nếu newText không phải là số)
//                }
//

                //TIM TEXT94 99
//                ArrayList<PhieuMuon> newlist = new ArrayList<>();
//                for (PhieuMuon pm : list){
//                    if(pm.getTentv().toLowerCase().contains(newText.toLowerCase())){
//                        newlist.add(pm);
//                    }
//                }

//                loadriengchotimkiem(newlist);
//                return false;
//            }
//        });
        return view;
    }
    private void loadriengchotimkiem (ArrayList<PhieuMuon> list){
        //data
        adapter = new PhieuMuonAdapter(list, getContext());
        rec.setAdapter(adapter);
    }

private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.phieumuon_up_add, null);
        builder.setView(view);
        ImageView imgadd = view.findViewById(R.id.txtphieumuon_up_add_A);
        ImageView imgup = view.findViewById(R.id.txtphieumuon_up_add_U);


        //DIALOG
//    txtgioitinh.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            final String[] gioiTinhOptions = new String[]{"Nam", "Nữ"};
//            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//            builder.setTitle("Chọn giới tính");
//            builder.setItems(gioiTinhOptions, new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    // Xử lý khi người dùng chọn giới tính
//                    String selectedGioiTinh = gioiTinhOptions[which];
//                    txtgioitinh.setText(selectedGioiTinh);
//
//
//                    adapter.notifyDataSetChanged();
//                    dialog.dismiss(); // Đóng hộp thoại sau khi chọn
//                }
//            });
//            AlertDialog alertDialog = builder.create();
//            alertDialog.show();
//        }
//    });


        imgadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                LayoutInflater inflater1 = getLayoutInflater();
                View view1 = inflater1.inflate(R.layout.item_add_phieumuon, null);
                builder1.setView(view1);
                Spinner spntv = view1.findViewById(R.id.spntv);
                Spinner spnsach = view1.findViewById(R.id.spnsach);
                Button btnadd = view1.findViewById(R.id.themPM_A);
                Button btnhuy = view1.findViewById(R.id.huyPM_A);
                getSpnsach(spnsach);
                getSpntv(spntv);
                AlertDialog alertDialog = builder1.create();
                btnadd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    HashMap<String, Object> hmtv = (HashMap<String, Object>) spntv.getSelectedItem();
                    int matv = (int) hmtv.get("matv");
                        HashMap<String, Object> hmsach = (HashMap<String, Object>) spnsach.getSelectedItem();
                        int masach = (int) hmsach.get("masach");
                        int tien = (int) hmsach.get("giathue");
                        themphieumuon(matv, masach, tien);
                        alertDialog.dismiss();
                    }
                });
                btnhuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
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
                View view2 = inflater2.inflate(R.layout.item_up_phieumuon, null);
                builder2.setView(view2);
                Spinner spntv = view2.findViewById(R.id.spntv_U);
                Spinner spnsach = view2.findViewById(R.id.spnsach_U);
                Button btnup = view2.findViewById(R.id.updatePM);
                Button btnhuy = view2.findViewById(R.id.huyPM);
                EditText edtmapm = view2.findViewById(R.id.mapm_U);
                getSpnsach(spnsach);
                getSpntv(spntv);
                AlertDialog alertDialog = builder2.create();
                btnup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HashMap<String, Object> hmtv = (HashMap<String, Object>) spntv.getSelectedItem();
                        int matv = (int) hmtv.get("matv");
                        HashMap<String, Object> hmsach = (HashMap<String, Object>) spnsach.getSelectedItem();
                        int masach = (int) hmsach.get("masach");
                        int tien = (int) hmsach.get("giathue");
                        int mapm = Integer.parseInt(edtmapm.getText().toString());
                        capnhatphieumuon(mapm, matv, masach, tien);
                        alertDialog.dismiss();
                    }
                });
                btnhuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });

                alertDialog.show();
            }
        });
    AlertDialog alertDialog = builder.create();
    alertDialog.show();
}


    private void getSpntv(Spinner spntv){
        ThanhVienDao dao = new ThanhVienDao(getContext());
        ArrayList<ThanhVien> list = dao.getDSThanhVien();
        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();
        for(ThanhVien itemtv : list){
            HashMap<String, Object> hmtv = new HashMap<>();
            hmtv.put("matv", itemtv.getMatv());
            hmtv.put("hoten", itemtv.getHoten());
            listHM.add(hmtv);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(
                getContext(),
                listHM,
                android.R.layout.simple_list_item_1,
                new String[]{"hoten"},
                new int[]{android.R.id.text1}
        );
        spntv.setAdapter(simpleAdapter);

}

    private void getSpnsach(Spinner spnsach){
        SachDao dao = new SachDao(getContext());
        ArrayList<Sach> list = dao.getDSSach();
        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();
        for(Sach itemsach : list){
            HashMap<String, Object> hmsach = new HashMap<>();
            hmsach.put("masach", itemsach.getMasach());
            hmsach.put("tensach", itemsach.getTensach());
            hmsach.put("giathue", itemsach.getGiathue());
            listHM.add(hmsach);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(
                getContext(),
                listHM,
                android.R.layout.simple_list_item_1,
                new String[]{"tensach"},
                new int[]{android.R.id.text1}
        );
        spnsach.setAdapter(simpleAdapter);

    }


private void themphieumuon(int matv, int masach, int gia){
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Thongtin", Context.MODE_PRIVATE);
        String matt = sharedPreferences.getString("matt", "");
        //
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String ngay = simpleDateFormat.format(date);
        //
        PhieuMuon pm = new PhieuMuon(matv, matt,masach, ngay, 0, gia);
        boolean kt = dao.themphieumuon(pm);
        if(kt){
            list.clear();
            list.addAll(dao.getDSphieumuon());
            adapter.notifyDataSetChanged();
            Toast.makeText(getContext(), "ok", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getContext(), "That bai", Toast.LENGTH_SHORT).show();
        }
}



private void capnhatphieumuon(int mapm, int matv, int masach, int tien){
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Thongtin", Context.MODE_PRIVATE);
        String matt = sharedPreferences.getString("matt", "");
        //
    Date date = Calendar.getInstance().getTime();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    String ngay = simpleDateFormat.format(date);

    PhieuMuon pm = new PhieuMuon(mapm, matv, matt, masach, ngay, 0, tien);
    boolean kt = dao.capnhatphieumuon(pm);
    if(kt){
        list.clear();
        list.addAll(dao.getDSphieumuon());
        adapter.notifyDataSetChanged();
        Toast.makeText(getContext(), "ok", Toast.LENGTH_SHORT).show();
    }else{
        Toast.makeText(getContext(), "fail", Toast.LENGTH_SHORT).show();
    }

}





//    private  void showDialog(){
//        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//        LayoutInflater inflater = getLayoutInflater();
//        View view = inflater.inflate(R.layout.phieumuon_up_add, null);
//        builder.setView(view);
//        ImageView imgadd = view.findViewById(R.id.txtphieumuon_up_add_A);
//        ImageView imgaup = view.findViewById(R.id.txtphieumuon_up_add_U);
//        imgadd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
//                LayoutInflater inflater1 = getLayoutInflater();
//                View view1 = inflater1.inflate(R.layout.item_add_phieumuon, null);
//                builder1.setView(view1);
//
//                        Spinner spnthanhvien = view1.findViewById(R.id.spntv);
//                        Spinner spnsach = view1.findViewById(R.id.spnsach);
//                Button btnthem = view1.findViewById(R.id.themPM_A);
//                Button btnhuy = view1.findViewById(R.id.huyPM_A);
//                //        EditText edttien = view.findViewById(R.id.txttien);
//                        // b1 cua ham (goi ra r truyen vao)81-144
//                        getDatathanhvien(spnthanhvien);
//                        //b2 cua ham 111-130
//                        getDatasach(spnsach);
//                        builder1.setView(view1);
//                btnthem.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        // lay ma thanh vien\ minh se lay cai gia tri nguoi dung dang chon roi se ep cai gia tri do qua kieu hashMap.
//                        //lấy giá trị mà người dùng đã chọn, và sau đó ep sang hashMap va lấy mã kiểu int
//                        HashMap<String, Object> hsTV = (HashMap<String, Object>) spnthanhvien.getSelectedItem();
//                        int matv = (int) hsTV.get("matv");
//                        // lay ma sach
//                        HashMap<String, Object> hsSach = (HashMap<String, Object>) spnsach.getSelectedItem();
//                        int masach = (int) hsSach.get("masach");
//                        //
////                int tien = Integer.parseInt(edttien.getText().toString());
//                        int tien =(int) hsSach.get("giathue");
//                        //truyen vao
//                        themphieumuonn(matv, masach, tien);
//                    }
//                });
//                btnhuy.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                    }
//                });
//                AlertDialog alertDialog = builder1.create();
//                alertDialog.show();
//            }
//        });
//
//        imgaup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v){
//            AlertDialog.Builder builder2 = new AlertDialog.Builder(getContext());
//            LayoutInflater inflater2 = getLayoutInflater();
//            View view2 = inflater2.inflate(R.layout.item_up_phieumuon, null);
//                builder2.setView(view2);
//                EditText txma = view2.findViewById(R.id.mapm_U);
//            Spinner spnthanhvien = view2.findViewById(R.id.spntv_U);
//            Spinner spnsach = view2.findViewById(R.id.spnsach_U);
//            Button btnup = view2.findViewById(R.id.updatePM);
//            Button btnhuy = view2.findViewById(R.id.huyPM);
//            //        EditText edttien = view.findViewById(R.id.txttien);
//            // b1 cua ham (goi ra r truyen vao)81-144
//            getDatathanhvien(spnthanhvien);
//            //b2 cua ham 111-130
//            getDatasach(spnsach);
//                        builder2.setView(view2);
//                btnup.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        // lay ma thanh vien\ minh se lay cai gia tri nguoi dung dang chon roi se ep cai gia tri do qua kieu hashMap.
//                        //lấy giá trị mà người dùng đã chọn, và sau đó ep sang hashMap va lấy mã kiểu int
//                        HashMap<String, Object> hsTV = (HashMap<String, Object>) spnthanhvien.getSelectedItem();
//                        int matv = (int) hsTV.get("matv");
//                        // lay ma sach
//                        HashMap<String, Object> hsSach = (HashMap<String, Object>) spnsach.getSelectedItem();
//                        int masach = (int) hsSach.get("masach");
//                        //
////                int tien = Integer.parseInt(edttien.getText().toString());
//                        int tien =(int) hsSach.get("giathue");
//                        int mapm = Integer.parseInt(txma.getText().toString());
//                        //truyen vao
//                        capnhatphieumuonn(mapm, matv, masach, tien);
//                    }
//                });
//                btnhuy.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                    }
//                });
//            AlertDialog alertDialog = builder2.create();
//                alertDialog.show();
//        }
//        });
//
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//    }
//    //b1 cua ham lay du lieu vao spinner thanh vien
//    private void getDatathanhvien(Spinner spnthanhvien){
//        ThanhVienDao dao = new ThanhVienDao(getContext());
//        ArrayList<ThanhVien> list = dao.getDSThanhVien();
//        //Trong cái HashMap nay minh sẽ truyền String và Object
//
//        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();
//        //Sâu khi co HashMap chúng ta cần add cái list này vào listHM.
//        // Mình dùng for-e de duyet toan bộ thông tin của cái list này va mỗi thông tin của cái list này sẽ là thông tin của từng thành viên.
//        // Mỗi lần duyệt xong mình sẽ lấy ra gắn vào cái HashMap của mình
//        //doi cai danh sach cua minh thanh cai hashmap(125-130)
//        for(ThanhVien tv : list){// for-e duyet tung thanh vien trong cai hashMap roi, moi lan duyet xong thi gan vao cai háshmap
//            HashMap<String, Object> hs = new HashMap<>();
//            hs.put("matv", tv.getMatv());//khi luu xong thi lay ma thanh vien , khi di xuong thi minh show
//            hs.put("hoten", tv.getHoten());
//            listHM.add(hs);//(day chi moi la do du lieu len spinner)
//        }
//        //simpleAdapter can một ArrayList ma trong do nó cần HashMap chứ nó không có nhận model.
//        //SimpleAdapter de truyen
//        SimpleAdapter simpleAdapter = new SimpleAdapter(
//                getContext(),
//                listHM,
//                android.R.layout.simple_list_item_1,//layout mac dinh cua he thong
//                new String[]{"hoten"},//truyen du lieu ma minh muon hien thi len
//                new int[]{android.R.id.text1});//hien thi len thang nao minh can truyền id của thẳng đó vô.
//        spnthanhvien.setAdapter(simpleAdapter);
//    }
//
//
//    //b2 cua ham lay du lieu vao spinner sach
//    private void getDatasach(Spinner spnsach){
//        SachDao dao = new SachDao(getContext());
//        ArrayList<Sach> list = dao.getDSSach();
//        //doi cai danh sach cua minh thanh cai hashmap
//        ArrayList<HashMap<String, Object>> listhh = new ArrayList<>();
//        for(Sach s : list){
//            HashMap<String, Object> sa = new HashMap<>();
//            sa.put("masach", s.getMasach());
//            sa.put("tensach", s.getTensach());
//            sa.put("giathue", s.getGiathue());
//            listhh.add(sa);
//        }
//        //SimpleAdapter de truyen
//        SimpleAdapter simpleAdapter = new SimpleAdapter(
//                getContext(),
//                listhh,
//                android.R.layout.simple_list_item_2,//layout mac dinh cua he thong
//                new String[]{"tensach"},
//                new int[]{android.R.id.text2});
//        spnsach.setAdapter(simpleAdapter);
//    }
//    private void themphieumuonn(int matv, int masach, int tien){
//        // lay ma thu thu
//        SharedPreferences shared = getContext().getSharedPreferences("Thongtin", Context.MODE_PRIVATE);
//        String  matt = shared.getString("matt", "");
//
//        // lay ngay hien tai
//        Date cuDate = Calendar.getInstance().getTime();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
//        String ngay = simpleDateFormat.format(cuDate);
//        //
//        dao = new PhieuMuonDao(getContext());
//        PhieuMuon pm = new PhieuMuon(matv,matt, masach, ngay,0,tien);
//        boolean kt = dao.themphieumuon(pm);
//        if(kt){
//            Toast.makeText(getContext(), "Them thanh cong", Toast.LENGTH_SHORT).show();
//            list.clear();
//            list.addAll(dao.getDSphieumuon());
//            adapter.notifyDataSetChanged();
//        }else{
//            Toast.makeText(getContext(), "Them phieu muon that bai", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    private void capnhatphieumuonn(int mapm, int matv, int masach, int tien){
//        // lay ma thu thu
//        SharedPreferences shared = getContext().getSharedPreferences("Thongtin", Context.MODE_PRIVATE);
//        String  matt = shared.getString("matt", "");
//
//        // lay ngay hien tai
//        Date cuDate = Calendar.getInstance().getTime();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
//        String ngay = simpleDateFormat.format(cuDate);
//        //
//        dao = new PhieuMuonDao(getContext());
//        PhieuMuon pm = new PhieuMuon(mapm, matv,matt, masach, ngay,0,tien);
//        boolean kt = dao.capnhatphieumuon(pm);
//        if(kt){
//            Toast.makeText(getContext(), "Cap nhat thanh cong", Toast.LENGTH_SHORT).show();
//            list.clear();
//            list.addAll(dao.getDSphieumuon());
//            adapter.notifyDataSetChanged();
//        }else{
//            Toast.makeText(getContext(), "Cap nhat phieu muon that bai", Toast.LENGTH_SHORT).show();
//        }
//    }
}
