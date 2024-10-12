package com.example.myapplication.thanhvien;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.dao.PhieuMuonDao;
import com.example.myapplication.model.PhieuMuon;

import java.util.ArrayList;

public class PhieuMuonAdapter_thanhvien extends RecyclerView.Adapter<PhieuMuonAdapter_thanhvien.viewholder> {
    private final ArrayList<PhieuMuon> list;
    private final Context context;
 PhieuMuonDao dao;
    PhieuMuonAdapter_thanhvien adapter;

    public PhieuMuonAdapter_thanhvien(ArrayList<PhieuMuon> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_phieumuon_thanhvien, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtphieumuon.setText( "Mã phiếu mượn: " + Integer.toString(list.get(position).getMapm()));
        holder.txtmatv.setText("Ma thanhvien:  " + list.get(position).getMatv());
//        holder.txttentv.setText(list.get(position).getTentv());
//        holder.txtmasach.setText("ma sach:  " + list.get(position).getMasach());
        holder.txttensach.setText( list.get(position).getTensach());
        holder.txtngay.setText(list.get(position).getNgay());
        String trangthai ="";
        adapter = new PhieuMuonAdapter_thanhvien(list, context);
        dao = new PhieuMuonDao(context);
        //DOI MAU CHU
//        if(list.get(position).getGioitinh().equals("Nam")){
//            holder.txtgioi.setTextColor(context.getResources().getColor(R.color.red));
//        }else{
//            holder.txtgioi.setTextColor(context.getResources().getColor(R.color.yellow));
//        }
        if (list.get(position).getTrasach()==1){
            holder.txttrangthai.setTextColor(Color.parseColor("#FF0000"));
        }
        else  if (list.get(position).getTrasach()==3) {
            holder.txttrangthai.setTextColor(Color.parseColor("#9F69FF"));
        }else  if (list.get(position).getTrasach()==0) {
            holder.txttrangthai.setTextColor(Color.parseColor("#009700"));
        }


        if(list.get(position).getTrasach() == 1){
            trangthai ="da tra sach";
        }else if(list.get(position).getTrasach() == 0){
            trangthai ="chua tra sach";
        }else if(list.get(position).getTrasach() == 3){
            trangthai ="cho duyet";
        }else if(list.get(position).getTrasach() == 4){
            trangthai ="da duyet";
        }
        holder.txttrangthai.setText( trangthai);
        holder.txttien.setText(Integer.toString(list.get(position).getTienthue()));

        holder.huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Bạn có muốn hủy ?");
                builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(list.get(position).getTrasach() == 3){
//                            trangthai ="cho duyet";
                            if(dao.delete(list.get(holder.getAdapterPosition()).getMapm())){
                                list.clear();
                                list.addAll(dao.getDSphieumuon());
                                notifyDataSetChanged();
                                dialog.dismiss();
                                Toast.makeText(context, "Đã Hủy", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                              AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Bạn không thể hủy do sách đã duyệt ");
                builder.setNegativeButton("Quay Lại", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                        }


                    }
                });
                builder.setNegativeButton("Quay Lại", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView txtphieumuon, txtmatv, txttentv, tctmatt, txttentt, txtmasach, txttensach, txtngay, txttrangthai, txttien;
        ImageView xoa ;
        Button huy;


        public viewholder(@NonNull View itemView) {
            super(itemView);
            txtphieumuon = itemView.findViewById(R.id.txtmaphieuPM);
            txtmatv = itemView.findViewById(R.id.txtmatv);
            txttentv = itemView.findViewById(R.id.txtthanhvienPM);
//            txtmasach = itemView.findViewById(R.id.txtmasach);
            txttensach = itemView.findViewById(R.id.txttensachPM);
            txtngay = itemView.findViewById(R.id.txtngaythuePM);
            txttrangthai = itemView.findViewById(R.id.txttrangthaiPM);
            txttien = itemView.findViewById(R.id.txttienthuePM);
            xoa = itemView.findViewById(R.id.xoapm);
            huy=itemView.findViewById(R.id.huypm_tv);

        }
    }
}
