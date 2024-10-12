package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;


import com.example.myapplication.frag.frag_doimk;
import com.example.myapplication.frag.frag_loaisach;
import com.example.myapplication.frag.frag_phieumuon;
import com.example.myapplication.frag.frag_sach;
import com.example.myapplication.frag.frag_thanhvien;
import com.example.myapplication.frag.frag_top19;
import com.example.myapplication.frag.frg_doanhthufragment;
import com.example.myapplication.frag.taotaikhoan;
import com.example.myapplication.thanhvien.fag_sach;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout dra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NavigationView nar = findViewById(R.id.nar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        FrameLayout navigationView = findViewById(R.id.framlayout);
        dra = findViewById(R.id.dra);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.baseline_menu_24);


        nar.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                if (item.getItemId() == R.id.phieumuon) {
                    Fragment fragment = new frag_phieumuon();
                    fragmentManager.beginTransaction().replace(R.id.framlayout, fragment).commit();
                } else if (item.getItemId() == R.id.loaisach) {
                    Fragment fragment = new frag_loaisach();
                    fragmentManager.beginTransaction().replace(R.id.framlayout, fragment).commit();
                } else if (item.getItemId() == R.id.taotk) {
                    Fragment fragment = new taotaikhoan();
                    fragmentManager.beginTransaction().replace(R.id.framlayout, fragment).commit();
                } else if (item.getItemId() == R.id.sach) {
                    Fragment fragment = new frag_sach();
                    fragmentManager.beginTransaction().replace(R.id.framlayout, fragment).commit();
                } else if (item.getItemId() == R.id.thanhvien) {
                    Fragment fragment = new frag_thanhvien();
                    fragmentManager.beginTransaction().replace(R.id.framlayout, fragment).commit();
                } else if (item.getItemId() == R.id.matkhau) {
                    Fragment fragment = new frag_doimk();
                    fragmentManager.beginTransaction().replace(R.id.framlayout, fragment).commit();
                } else if (item.getItemId() == R.id.top10) {
                    Fragment fragment = new frag_top19();
                    fragmentManager.beginTransaction().replace(R.id.framlayout, fragment).commit();
                } else if (item.getItemId() == R.id.doanhthu) {
                    Fragment fragment = new frg_doanhthufragment();
                    fragmentManager.beginTransaction().replace(R.id.framlayout, fragment).commit();
                } else if (item.getItemId() == R.id.thoat) {
                    showDialog();
                }else if(item.getItemId() == R.id.sach_thanhvien){
                    Fragment fragment = new fag_sach();
                    fragmentManager.beginTransaction().replace(R.id.framlayout, fragment).commit();
                }else {
                    Fragment fragment = new frag_phieumuon();
                    fragmentManager.beginTransaction().replace(R.id.framlayout, fragment).commit();
                }
                dra.closeDrawer(GravityCompat.START);
                setTitle(item.getTitle());
                return false;
            }
        });


        SharedPreferences sharedPreferences = getSharedPreferences("Thongtin", MODE_PRIVATE);
        String loaiTK = sharedPreferences.getString("loaitaikhoan", "");
        Menu menu = null;
        if (!loaiTK.equals("admin")) {
            menu = nar.getMenu();
            menu.findItem(R.id.taotk).setVisible(false);
        }
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            dra.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("")
                .setMessage("Xác nhận thoát thành viên")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        startActivity(new Intent(MainActivity.this, luachon.class));
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
        dialog.show(); // Show the dialog
    }
}