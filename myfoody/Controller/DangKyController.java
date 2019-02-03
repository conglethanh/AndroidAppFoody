package com.cntn16.vuongcong.myfoody.Controller;

import com.cntn16.vuongcong.myfoody.Model.ThanhVienModel;



public class DangKyController {
    ThanhVienModel thanhVienModel;

    public DangKyController(){
        thanhVienModel = new ThanhVienModel();
    }

    public void ThemThongTinThanhVienController(ThanhVienModel thanhVienModel,String uid){
        thanhVienModel.ThemThongTinThanhVien(thanhVienModel,uid);
    }
}
