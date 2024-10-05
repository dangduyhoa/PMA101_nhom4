package com.example.myapplication.dao;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.myapplication.database.DbHelper;
import com.example.myapplication.model.Sach;

import java.util.ArrayList;

public class thong_keDao {
    private final DbHelper dbHelper;

    public thong_keDao(Context context) {
        dbHelper = new DbHelper(context);
    }

    public ArrayList<Sach> getTop10(){
        ArrayList<Sach> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select pm.masach, s.tensach, count(pm.masach) from phieumuon pm, sach s where pm.masach = s.masach group by pm.masach, s.tensach order by count(pm.masach) desc limit 10", null);
        cursor.moveToFirst();
        for (int i = 0 ; i < 10 ; i++){
            if(!cursor.isAfterLast()){
                list.add(new Sach(cursor.getInt(0), cursor.getString(1), cursor.getInt(2)));
                cursor.moveToNext();
            }

        }

        return list;
    }
    //Doanh thu
    public int getDoanhThu(String ngaybatdau, String ngayketthuc){//2022/09/30
        ngaybatdau = ngaybatdau.replace("/","");
        ngayketthuc = ngayketthuc.replace("/","");
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select sum(tienthue) from phieumuon where substr(ngay,7) ||substr(ngay, 4,2) || substr(ngay,1,2) between ? and ?", new String[]{ngaybatdau, ngayketthuc});
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            return cursor.getInt(0);
        }
        return 0;
    }
}
