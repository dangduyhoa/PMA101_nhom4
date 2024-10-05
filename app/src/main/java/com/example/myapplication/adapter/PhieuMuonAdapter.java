package com.example.myapplication.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.dao.PhieuMuonDao;
import com.example.myapplication.model.PhieuMuon;

import java.util.ArrayList;

public class PhieuMuonAdapter extends RecyclerView.Adapter<PhieuMuonAdapter.viewholder> {
    private final ArrayList<PhieuMuon> list;
    private final Context context;
 PhieuMuonDao dao;
    PhieuMuonAdapter adapter;

    public PhieuMuonAdapter(ArrayList<PhieuMuon> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_phieumuon, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.txtphieumuon.setText( "Mã phiếu mượn: " + Integer.toString(list.get(position).getMapm()));
//        holder.txtmatv.setText("Ma thanhvien:  " + list.get(position).getMatv());
        holder.txttentv.setText(list.get(position).getTentv());
//        holder.txtmasach.setText("ma sach:  " + list.get(position).getMasach());
        holder.txttensach.setText( list.get(position).getTensach());
        holder.txtngay.setText(list.get(position).getNgay());
        String trangthai ="";
        adapter = new PhieuMuonAdapter(list, context);
        dao = new PhieuMuonDao(context);
        //DOI MAU CHU
//        if(list.get(position).getGioitinh().equals("Nam")){
//            holder.txtgioi.setTextColor(context.getResources().getColor(R.color.red));
//        }else{
//            holder.txtgioi.setTextColor(context.getResources().getColor(R.color.yellow));
//        }


        if(list.get(position).getTrasach() == 1){
            trangthai ="da tra sach";
        }else{
            trangthai ="chua tra sach";
        }
        holder.txttrangthai.setText( trangthai);
        holder.txttien.setText(Integer.toString(list.get(position).getTienthue()));
        holder.xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Ban co muon xoa?");
                builder.setPositiveButton("Xoa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(dao.delete(list.get(holder.getAdapterPosition()).getMapm())){
                            list.clear();
                            list.addAll(dao.getDSphieumuon());
                            notifyDataSetChanged();
                            dialog.dismiss();
                            Toast.makeText(context, "Da xoa", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("Huy", new DialogInterface.OnClickListener() {
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


        public viewholder(@NonNull View itemView) {
            super(itemView);
            txtphieumuon = itemView.findViewById(R.id.txtmaphieuPM);
//            txtmatv = itemView.findViewById(R.id.txtmatv);
            txttentv = itemView.findViewById(R.id.txtthanhvienPM);
//            txtmasach = itemView.findViewById(R.id.txtmasach);
            txttensach = itemView.findViewById(R.id.txttensachPM);
            txtngay = itemView.findViewById(R.id.txtngaythuePM);
            txttrangthai = itemView.findViewById(R.id.txttrangthaiPM);
            txttien = itemView.findViewById(R.id.txttienthuePM);
            xoa = itemView.findViewById(R.id.xoapm);

        }
    }
}
