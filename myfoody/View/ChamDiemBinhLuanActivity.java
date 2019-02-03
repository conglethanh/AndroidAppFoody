package com.cntn16.vuongcong.myfoody.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cntn16.vuongcong.myfoody.Controller.CapNhatWifiController;
import com.cntn16.vuongcong.myfoody.Model.WifiQuanAnModel;
import com.cntn16.vuongcong.myfoody.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;



public class ChamDiemBinhLuanActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edDiemBinhLuan;
    Button btnDongY;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chamdiembinhluan);

        edDiemBinhLuan = (EditText) findViewById(R.id.edDiemBinhLuan);
        btnDongY = (Button) findViewById(R.id.btnDongY);

        btnDongY.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (edDiemBinhLuan.getText().length()==0)
        {
            Toast.makeText(this,"Bạn hãy nhập điểm số từ 0 - 10",Toast.LENGTH_SHORT).show();
        }
        else{
            if (Double.valueOf(edDiemBinhLuan.getText().toString())>=0
                && Double.valueOf(edDiemBinhLuan.getText().toString())<=10){
                Log.d("chamdiem","ok");
                Intent intent = new Intent();
                intent.putExtra("diemBinhLuan",Double.valueOf(edDiemBinhLuan.getText().toString()));
                setResult(1,intent);
                finish();
            }
            else
            {
                Toast.makeText(this,"Bạn hãy nhập điểm số từ 0 - 10",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
