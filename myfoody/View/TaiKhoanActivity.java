package com.cntn16.vuongcong.myfoody.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cntn16.vuongcong.myfoody.R;

public class TaiKhoanActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    TextView txtTieuDeToolbar;
    SharedPreferences sharedPreferences;
    TextView tvTen, tvEmail;
    Button btnDangXuat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_taikhoan);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        txtTieuDeToolbar = (TextView) findViewById(R.id.txtTieuDeToolbar);
        btnDangXuat = (Button)findViewById(R.id.btnDangXuat);
        tvTen=(TextView)findViewById(R.id.tvTenUser);
        tvEmail=(TextView)findViewById(R.id.tvThongTinUser);
        sharedPreferences = getSharedPreferences("luudangnhap",MODE_PRIVATE);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        String tenuser = sharedPreferences.getString("tenuser","Tên user");
        String email = sharedPreferences.getString("email","Email");

        tvEmail.setText(email);
        tvTen.setText(tenuser);

        txtTieuDeToolbar.setText("Tài khoản");
        btnDangXuat.setOnClickListener(this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnDangXuat:
                Intent intent = new Intent(this,DangNhapActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
        }
    }
}