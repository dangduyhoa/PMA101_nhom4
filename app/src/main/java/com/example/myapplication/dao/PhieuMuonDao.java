package com.example.myapplication.dao;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.myapplication.database.DbHelper;
import com.example.myapplication.model.PhieuMuon;

import java.util.ArrayList;

public class PhieuMuonDao {
    private final DbHelper dbHelper;

    public PhieuMuonDao(Context context) {
        dbHelper  = new DbHelper(context);
    }

    public ArrayList<PhieuMuon> getDSphieumuon(){
        ArrayList<PhieuMuon> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();//
        try{
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT pm.mapm, pm.matv, tv.hoten, pm.matt, pm.masach, sc.tensach, pm.ngay, pm.trasach, pm.tienthue\n" +
                    "FROM PHIEUMUON pm, THANHVIEN tv, THUTHU tt, SACH sc \n" +
                    "WHERE pm.matv = tv.matv and pm.matt = tt.matt AND pm.masach = sc.masach",null);
            if (cursor.getCount() > 0){
                cursor.moveToFirst();
                while (!cursor.isAfterLast()){
                    list.add(new PhieuMuon(cursor.getInt(0), cursor.getInt(1),cursor.getString(2), cursor.getString(3), cursor.getInt(4),cursor.getString(5),cursor.getString(6), cursor.getInt(7),cursor.getInt(8)));
                    cursor.moveToNext();
                }
            }
        }catch (Exception e){
            Log.i(TAG, "loi", e);
        }
        return list;
    }
    public  boolean themphieumuon(PhieuMuon pm){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
//        values.put("mapm",pm.getMapm());
        values.put("matv", pm.getMatv());//gia tri(lay matv)
        values.put("matt", pm.getMatt());
        values.put("masach", pm.getMasach());
        values.put("ngay", pm.getNgay());
        values.put("trasach", pm.getTrasach());
        values.put("tienthue", pm.getTienthue());
        long row = db.insert("phieumuon", null, values);
        return (row>0);
    }

    public  boolean capnhatphieumuon(PhieuMuon pm){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from phieumuon where mapm = ?", new String[]{String.valueOf(pm.getMapm())});
        if(cursor.getCount() > 0) {
            ContentValues values = new ContentValues();
            values.put("mapm",pm.getMapm());
            values.put("matv", pm.getMatv());//gia tri(lay matv)
            values.put("matt", pm.getMatt());
            values.put("masach", pm.getMasach());
            values.put("ngay", pm.getNgay());
            values.put("trasach", pm.getTrasach());
            values.put("tienthue", pm.getTienthue());
            long row = db.update("phieumuon",  values,  "mapm = ?", new String[]{String.valueOf(pm.getMapm())});
            return (row>0);
        }
        return false;
    }
    public  boolean capnhattrangthai(PhieuMuon pm){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from phieumuon where mapm = ?", new String[]{String.valueOf(pm.getMapm())});
        if(cursor.getCount() > 0) {
            ContentValues values = new ContentValues();
            values.put("trasach", pm.getTrasach());
            long row = db.update("phieumuon",  values,  "mapm = ?", new String[]{String.valueOf(pm.getMapm())});
            return (row>0);
        }
        return false;
    }
    public boolean delete(int mapm){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from phieumuon where mapm = ?", new String[]{String.valueOf(mapm)});
        if(cursor.getCount() > 0){
           long kt = db.delete("phieumuon", "mapm =?", new String[]{String.valueOf(mapm)}) ;
            return true;
        }
         return false;
    }
}
