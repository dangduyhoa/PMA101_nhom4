package com.example.myapplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.dao.SachDao;
import com.example.myapplication.model.Sach;

import java.util.ArrayList;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.holder> {
    private final Context context;
    private final ArrayList<Sach> list;
    SachDao dao;

    public SachAdapter(Context context, ArrayList<Sach> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_sach, parent, false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        holder.txtmasach.setText( "Mã sách: " + String.valueOf(list.get(position).getMasach()));
        holder.txttensach.setText(list.get(position).getTensach());
        holder.txtgiathue.setText(Integer.toString(list.get(position).getGiathue()));
        holder.txtmaloai.setText(Integer.toString(list.get(position).getMaloai()));
        holder.txttenloai.setText(list.get(position).getTenloai());
        holder.txtmausac.setText(list.get(position).getMausac());
        holder.txtsoluong.setText(Integer.toString(list.get(position).getSoluong()));
        dao = new SachDao(context);
        //DOI MAU CHU
        if(list.get(position).getMausac().equals("Do")){
            holder.txtmausac.setTextColor(context.getResources().getColor(R.color.red));
        }else if(list.get(position).getMausac().equals("Vang")){
            holder.txtmausac.setTextColor(context.getResources().getColor(R.color.yellow));
        }else{
            holder.txtmausac.setTextColor(context.getResources().getColor(R.color.tim));
        }

        holder.xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dao.deletesach(list.get(holder.getAdapterPosition()).getMasach())){
                    list.clear();
                    list.addAll(dao.getDSSach());
                    notifyDataSetChanged();
                    Toast.makeText(context, "Thanh cong", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class holder extends RecyclerView.ViewHolder {
        TextView txtmasach, txttensach,txtgiathue, txtmaloai, txttenloai, txtmausac, txtsoluong;
        ImageView xoa;
        public holder(@NonNull View itemView) {
            super(itemView);
            txtmasach= itemView.findViewById(R.id.txtmasach_S);
            txttensach= itemView.findViewById(R.id.txttensach_S);
            txtgiathue= itemView.findViewById(R.id.txtgiathue_S);
            txtmaloai= itemView.findViewById(R.id.txtloaisach_S);
            txttenloai= itemView.findViewById(R.id.txttenloai_S);
            txtmausac= itemView.findViewById(R.id.txtmausac_S);
            txtsoluong= itemView.findViewById(R.id.txtsoluong_S);
            xoa = itemView.findViewById(R.id.xoasach);
        }
    }
}
