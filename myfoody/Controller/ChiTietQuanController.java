package com.cntn16.vuongcong.myfoody.Controller;

import android.widget.TextView;

import com.cntn16.vuongcong.myfoody.Controller.Interfaces.ChiTietQuanAnInterface;
import com.cntn16.vuongcong.myfoody.Model.WifiQuanAnModel;

import java.util.ArrayList;
import java.util.List;


public class ChiTietQuanController {

    WifiQuanAnModel wifiQuanAnModel;
    List<WifiQuanAnModel> wifiQuanAnModelList;

    public ChiTietQuanController(){
        wifiQuanAnModel = new WifiQuanAnModel();
        wifiQuanAnModelList = new ArrayList<>();
    }

    public void HienThiDanhSachWifiQuanAn(String maquanan, final TextView txtTenWifi, final TextView txtMatKhauWifi, final TextView txtNgayDangWifi){

        ChiTietQuanAnInterface chiTietQuanAnInterface = new ChiTietQuanAnInterface() {
            @Override
            public void HienThiDanhSachWifi(WifiQuanAnModel wifiQuanAnModel) {
                wifiQuanAnModelList.add(wifiQuanAnModel);
                txtTenWifi.setText(wifiQuanAnModel.getTen());
                txtMatKhauWifi.setText(wifiQuanAnModel.getMatkhau());
                txtNgayDangWifi.setText(wifiQuanAnModel.getNgaydang());
            }
        };

        wifiQuanAnModel.LayDanhSachWifiQuanAn(maquanan,chiTietQuanAnInterface);
    }
}
