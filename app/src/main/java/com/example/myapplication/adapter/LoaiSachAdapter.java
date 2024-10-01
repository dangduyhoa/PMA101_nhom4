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
import com.example.myapplication.dao.LoaiSachDao;
import com.example.myapplication.model.LoaiSach;

import java.util.ArrayList;

public class LoaiSachAdapter extends RecyclerView.Adapter<LoaiSachAdapter.viewholder> {
    private final Context context;
    private final ArrayList<LoaiSach> list;
    LoaiSachDao dao;

    public LoaiSachAdapter(Context context, ArrayList<LoaiSach> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_loaisach, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.txtmasach.setText( "Mã sách: " + String.valueOf(list.get(position).getId()));
        holder.txttensach.setText(list.get(position).getTenloai());
        dao = new LoaiSachDao(context);

        holder.xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Ban co muon xoa?");
                builder.setPositiveButton("Xoa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(dao.deleteloaisach(list.get(holder.getAdapterPosition()).getId())){
                            list.clear();
                            list.addAll(dao.getDSLoaiSach());
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
        TextView txtmasach, txttensach;
        ImageView xoa;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            txtmasach= itemView.findViewById(R.id.txtmaLS);
            txttensach= itemView.findViewById(R.id.txttenLS);
            xoa = itemView.findViewById(R.id.xoaloaisach);
        }
    }
}
