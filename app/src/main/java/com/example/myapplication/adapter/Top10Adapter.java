package com.example.myapplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Sach;

import java.util.ArrayList;

public class Top10Adapter extends RecyclerView.Adapter<Top10Adapter.viewholder> {
    private final Context context;
    private final ArrayList<Sach> list;

    public Top10Adapter(Context context, ArrayList<Sach> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_top10, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.txtmasach.setText(String.valueOf(list.get(position).getMasach()));
        holder.txttensach.setText(list.get(position).getTensach());
        holder.txtsoluongmuon.setText(String.valueOf(list.get(position).getSoluongmuon()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView txtmasach, txttensach, txtsoluongmuon;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            txtmasach= itemView.findViewById(R.id.txtmasach);
            txttensach= itemView.findViewById(R.id.txttensach);
            txtsoluongmuon= itemView.findViewById(R.id.txtsoluong);
        }
    }
}
