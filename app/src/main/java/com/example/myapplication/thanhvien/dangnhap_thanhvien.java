package com.example.myapplication.thanhvien;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.dangnhap;
import com.example.myapplication.dao.ThanhVienDao;
import com.example.myapplication.dao.ThuThuDao;

public class dangnhap_thanhvien extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap_thanhvien);

        EditText txtuser = findViewById(R.id.txtuser);
        EditText txtpass = findViewById(R.id.txtpass);
        Button btndn = findViewById(R.id.btnlogin);
        ThanhVienDao dao = new ThanhVienDao(this);
        btndn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = txtuser.getText().toString();
                String pass = txtpass.getText().toString();
                if(dao.login(user, pass)){
                    startActivity(new Intent(dangnhap_thanhvien.this, MainActivity_thanhvien.class));
                }else{
                    Toast.makeText(dangnhap_thanhvien.this, "Nhap sai", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}