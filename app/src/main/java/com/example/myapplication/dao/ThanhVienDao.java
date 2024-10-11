package com.example.myapplication.dao;

import static android.content.Context.MODE_PRIVATE;
import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.myapplication.database.DbHelper;
import com.example.myapplication.model.ThanhVien;

import java.util.ArrayList;

public class ThanhVienDao {
    private final DbHelper dbHelper;
    SharedPreferences sharedPreferences;

    public ThanhVienDao(Context context) {
        dbHelper = new DbHelper(context);
        sharedPreferences = context.getSharedPreferences("Thanhvien", MODE_PRIVATE);
    }

    public ArrayList<ThanhVien> getDSThanhVien(){
        ArrayList<ThanhVien> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try{
            Cursor cursor = db.rawQuery("select * from thanhvien", null);
            if(cursor.getCount() > 0){
                cursor.moveToFirst();
                while (!cursor.isAfterLast()){
                    list.add(new ThanhVien(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4) ));
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
    public boolean themthanhvien1(String hoten, String namsinh,String gioitinh,int luong){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("hoten", hoten);
        values.put("namsinh", namsinh);
        values.put("gioitinh", gioitinh);
        values.put("luong", luong);
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

    public  boolean login(String matt, String matkhau) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        //matt, hoten, matkhau, loaitaikhoan
        Cursor cursor = db.rawQuery("select * from thanhvien where matv =? and hoten =?", new String[]{matt, matkhau});
        //cmt login.activity roi mang nos sang day(23-26)
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("matv", cursor.getString(0));
            editor.putString("hoten", cursor.getString(1));
            editor.commit();//luu du lieu
            return true;
        } else {
            return false;
        }

    }
}