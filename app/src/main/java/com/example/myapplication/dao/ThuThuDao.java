package com.example.myapplication.dao;

import static android.content.Context.MODE_PRIVATE;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.database.DbHelper;

public class ThuThuDao {
    private final DbHelper dbHelper;
    SharedPreferences sharedPreferences;

    public ThuThuDao(Context context) {
        dbHelper = new DbHelper(context);
        sharedPreferences = context.getSharedPreferences("Thongtin", MODE_PRIVATE);
    }

    public  boolean login(String matt, String matkhau) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        //matt, hoten, matkhau, loaitaikhoan
        Cursor cursor = db.rawQuery("select * from thuthu where matt =? and matkhau =?", new String[]{matt, matkhau});
        //cmt login.activity roi mang nos sang day(23-26)
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("matt", cursor.getString(0));
            editor.putString("loaitaikhoan", cursor.getString(3));
            //hoten nguoi dang nhap hien thi len header
            editor.putString("hoten", cursor.getString(1));
            editor.commit();//luu du lieu
            return true;
        } else {
            return false;
        }

    }
    public boolean signup(String matt, String ten, String matkhau) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("matt", matt);
        values.put("hoten", ten);
        values.put("matkhau", matkhau);
        values.put("loaitaikhoan", "thuthu");

        long result = db.insert("thuthu", null, values);
        db.close();

        if (result == -1) {
            return false;
        } else {
            // Lưu dữ liệu vào SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("matt", matt);
            editor.putString("loaitaikhoan", "thuthu");
            editor.putString("hoten", ten);
            editor.putString("matkhau", matkhau);
            editor.commit();
            return true;
        }
    }

    public int capnhatpass(String user ,String cu, String moi) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from thuthu where matt = ? and matkhau = ?", new String[]{user, cu});
        if (cursor.getCount() > 0) {
            ContentValues values = new ContentValues();
            values.put("matkhau", moi);
            long kt = db.update("thuthu", values, "matt =?", new String[]{user});
            if (kt == -1)
                return -1;
            return 1;
        }
        return 0;
    }


}
