package com.example.myapplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_name="QLS";
    public DbHelper(@Nullable Context context) {
        super(context, DB_name, null, 26);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String db_thuthu="create table thuthu(matt text primary key," +
                "hoten text," +
                "matkhau text," +
                "loaitaikhoan text)";
        db.execSQL(db_thuthu);
        String db_thanhvien="create table thanhvien(matv integer primary key autoincrement," +
                "hoten text," +
                "namsinh text," +
                "gioitinh text," +
                "luong integer)";
        db.execSQL(db_thanhvien);

        String db_Loai="create table loaisach(maloai integer primary key autoincrement," +
                "tenloai text)";
        db.execSQL(db_Loai);
        String db_sach="create table sach(masach integer primary key autoincrement," +
                "tensach text," +
                "giathue integer," +
                "maloai integer references loaisach(maloai)," +
                "mausac text," +
                "soluong integer)";//cho no chinh xac
        db.execSQL(db_sach);
        String db_phieumuon="create table phieumuon(mapm integer primary key autoincrement," +
                "matv integer references thanhvien(matv)," +
                "matt text references thuthu(matt)," +
                "masach integer references sach(masach)," +
                "ngay text," +
                "trasach integer," +
                "tienthue integer)";
        db.execSQL(db_phieumuon);

        db.execSQL("INSERT INTO loaisach VALUES (1, 'Âm nhạc'),(2,'Công nghệ thông tin'),(3, 'Cuộc sống'), (4, 'Tâm lý học'), (5, 'Sách thiếu nh'), (6, 'Sách tôn giáo'), (7, 'Sách văn học nghệ thuật'), (8, 'Sách truyền cảm hứng'), (9, 'Sách văn hóa xã hội và lịch sử:'), (10, 'Sách dạy nấu ăn')");
        db.execSQL("INSERT INTO sach VALUES (1, 'Vượt qua bản ngã', 2500, 3,'Red',9), (2, 'Java', 1000, 2,'Vang',8), (3, 'Tuổi trẻ băn khoăn', 2000, 4,'Tim',8)");
        db.execSQL("INSERT INTO thuthu VALUES ('ph46289','Duy Hòa','ph46289','admin')");
        db.execSQL("INSERT INTO thanhvien VALUES (1,'Dộ','2004','nam',50000),(2,'Hiếu','2004','nữ',65000),(3,'Thànhh','2004','nữ',65000),(4,'Vũ','2004','nữ',65000),(5,'an','2004','nam',65000)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion != oldVersion){
            db.execSQL("drop table if exists loaisach");
            db.execSQL("drop table if exists sach");
            db.execSQL("drop table if exists thuthu");
            db.execSQL("drop table if exists thanhvien");
            db.execSQL("drop table if exists phieumuon");
            onCreate(db);
        }
    }
}
