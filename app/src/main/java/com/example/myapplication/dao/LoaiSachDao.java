package com.example.myapplication.dao;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.myapplication.database.DbHelper;
import com.example.myapplication.model.LoaiSach;

import java.util.ArrayList;

public class   LoaiSachDao {
    private final DbHelper dbHelper;

    public LoaiSachDao(Context context) {
        dbHelper  = new DbHelper(context);
    }
    public ArrayList<LoaiSach> getDSLoaiSach(){
        ArrayList<LoaiSach> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try{
            Cursor cursor= db.rawQuery("select * from loaisach", null);
            if(cursor.getCount() > 0){
                cursor.moveToFirst();
                while (!cursor.isAfterLast()){
                    list.add(new LoaiSach(cursor.getInt(0), cursor.getString(1)));
                    cursor.moveToNext();
                }
            }
        }catch (Exception e){
            Log.i(TAG, "loi",e);
        }
        return list;
    }
    public boolean themloaisach(String tenloai){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenloai", tenloai);
        long row = db.insert("loaisach", null, values);
        return (row >0);
    }
    public  boolean capnhatloaisach(LoaiSach ls){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from loaisach where maloai = ?", new String[]{String.valueOf(ls.getId())});
        if(cursor.getCount() > 0) {
            ContentValues values = new ContentValues();
            values.put("tenloai", ls.getTenloai());
            long kt = db.update("loaisach", values, "maloai =?", new String[]{String.valueOf(ls.getId())});
            return (kt> 0);
        }
        return false;
    }
    public boolean deleteloaisach(int maloai){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from loaisach where maloai= ? ", new String[]{String.valueOf(maloai)});
        if(cursor.getCount() > 0){
            long kt = db.delete("loaisach", "maloai = ?", new String[]{String.valueOf(maloai)});
            return true;
        }
        return false;
    }
}
