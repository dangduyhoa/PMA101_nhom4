package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class manhinhchao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinhchao);

        // Lấy thời gian hiện tại
        Calendar calendar = Calendar.getInstance();

        // Lấy ngày, tháng, năm và thứ
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0
        int year = calendar.get(Calendar.YEAR);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        // Chuyển đổi số thứ trong tuần thành tên
        String dayOfWeekName = "";
        switch (dayOfWeek) {
            case Calendar.SUNDAY:
                dayOfWeekName = "Chủ Nhật";
                break;
            case Calendar.MONDAY:
                dayOfWeekName = "Thứ Hai";
                break;
            case Calendar.TUESDAY:
                dayOfWeekName = "Thứ Ba";
                break;
            case Calendar.WEDNESDAY:
                dayOfWeekName = "Thứ Tư";
                break;
            case Calendar.THURSDAY:
                dayOfWeekName = "Thứ Năm";
                break;
            case Calendar.FRIDAY:
                dayOfWeekName = "Thứ Sáu";
                break;
            case Calendar.SATURDAY:
                dayOfWeekName = "Thứ Bảy";
                break;
        }

        // Hiển thị thông tin
        TextView textView = findViewById(R.id.textView);
//        textView.setText("Hôm nay là: " + dayOfWeekName + ", " + day + "/" + month + "/" + year);
        textView.setText(dayOfWeekName);








        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(manhinhchao.this,luachon.class));

            }
        },4000);
     }
}