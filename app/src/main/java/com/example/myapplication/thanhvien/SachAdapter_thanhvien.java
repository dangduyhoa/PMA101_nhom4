package com.example.myapplication.thanhvien;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.PhieuMuonAdapter;
import com.example.myapplication.dao.PhieuMuonDao;
import com.example.myapplication.dao.SachDao;
import com.example.myapplication.model.PhieuMuon;
import com.example.myapplication.model.Sach;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class SachAdapter_thanhvien extends RecyclerView.Adapter<SachAdapter_thanhvien.holder> {
    private final Context context;
    private final ArrayList<Sach> list;
    SachDao dao;



    public SachAdapter_thanhvien(Context context, ArrayList<Sach> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_sach_thanhvien, parent, false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtmasach.setText(Integer.toString(list.get(position).getMasach()));
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

        SharedPreferences sharedPreferences = context.getSharedPreferences("Thanhvien", Context.MODE_PRIVATE);
holder.datPM.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Đặt trước sách ?");
        builder.setMessage("Bạn có chắc chắn muốn thực hiện hành động này?");

        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int maTv = Integer.parseInt(sharedPreferences.getString("matv",""));
                String matt = "ph46289";
                int maSach = Integer.parseInt(holder.txtmasach.getText().toString());
                Date date = Calendar.getInstance().getTime();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String ngay = simpleDateFormat.format(date);
                int giathue = Integer.parseInt(holder.txtgiathue.getText().toString());
                PhieuMuon pm = new PhieuMuon(maTv,matt,maSach,ngay,3,giathue);

                PhieuMuonDao dao1 = new PhieuMuonDao(context);
                ArrayList<PhieuMuon> list1 = new ArrayList<>();
                PhieuMuonAdapter adapter1 = new PhieuMuonAdapter(list1,context);
                boolean kt = dao1.themphieumuon(pm);
                if(kt){
                    list1.clear();
                    list1.addAll(dao1.getDSphieumuon());
                    adapter1.notifyDataSetChanged();
                    showDialog();
                }else{
                    Toast.makeText(context, "That bai", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Xử lý khi người dùng chọn "Không"
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();


    }
});


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class holder extends RecyclerView.ViewHolder {
        TextView txtmasach, txttensach,txtgiathue, txtmaloai, txttenloai, txtmausac, txtsoluong;
        ImageView datPM;

        public holder(@NonNull View itemView) {
            super(itemView);
            txtmasach= itemView.findViewById(R.id.txtmasach_S);
            txttensach= itemView.findViewById(R.id.txttensach_S);
            txtgiathue= itemView.findViewById(R.id.txtgiathue_S);
            txtmaloai= itemView.findViewById(R.id.txtloaisach_S);
            txttenloai= itemView.findViewById(R.id.txttenloai_S);
            txtmausac= itemView.findViewById(R.id.txtmausac_S);
            txtsoluong= itemView.findViewById(R.id.txtsoluong_S);
            datPM=itemView.findViewById(R.id.datphieumuon);

        }
    }


    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Dialog Title")
                .setMessage("Đặt trước thành công chờ xác nhận")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Handle OK button action
                        dialog.cancel();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show(); // Show the dialog
    }
}
