package com.example.myapplication.dao;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.myapplication.database.DbHelper;
import com.example.myapplication.model.ThanhVien;

import java.util.ArrayList;

public class ThanhVienDao {
    private final DbHelper dbHelper;

    public ThanhVienDao(Context context) {
        dbHelper = new DbHelper(context);
    }

    public ArrayList<ThanhVien> getDSThanhVien(){
        ArrayList<ThanhVien> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try{
            Cursor cursor = db.rawQuery("select * from thanhvien", null);
            if(cursor.getCount() > 0){
                cursor.moveToFirst();
                while (!cursor.isAfterLast()){
                    list.add(new ThanhVien(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getDouble(4) ));
                    cursor.moveToNext();
                }
            }
        }catch (Exception e){
            Log.i(TAG, "loi", e);
        }
        return list;
    }
    public boolean themthanhvien(String hoten, String namsinh){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("hoten", hoten);
        values.put("namsinh", namsinh);
        long kt = db.insert("thanhvien" , null, values);
        return (kt>0);
    }

    public  boolean capnhatthanhvien(ThanhVien tv){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from thanhvien where matv = ?", new String[]{String.valueOf(tv.getMatv())});
        if(cursor.getCount() > 0) {
            ContentValues values = new ContentValues();

            values.put("hoten", tv.getHoten());
            values.put("namsinh", tv.getNamsinh());

            long kt = db.update("thanhvien", values, "matv =?", new String[]{String.valueOf(String.valueOf(tv.getMatv()))});
            return (kt>0);
        }
        return false;
    }
    public  boolean deleteThanhVien(int matv){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from thanhvien where matv =? ", new String[]{String.valueOf(matv)});
        if(cursor.getCount() > 0){
            long kt = sqLiteDatabase.delete("thanhvien", "matv = ?", new String[]{String.valueOf(matv)});
            return true;
        }
        return false;
    }
}