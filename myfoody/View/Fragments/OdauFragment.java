package com.cntn16.vuongcong.myfoody.View.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;

import com.cntn16.vuongcong.myfoody.Controller.OdauController;
import com.cntn16.vuongcong.myfoody.R;
import com.cntn16.vuongcong.myfoody.View.TaiKhoanActivity;
import com.cntn16.vuongcong.myfoody.View.ThemQuanAnActivity;
import com.cntn16.vuongcong.myfoody.View.TrangChuActivity;


public class OdauFragment extends Fragment implements View.OnClickListener {
    OdauController odauController;
    RecyclerView recyclerOdau;
    ProgressBar progressBar;
    SharedPreferences sharedPreferences;
    NestedScrollView nestedScrollView;
    Location vitrihientai;

    Button  btnThemQuanAn, btnGanToi, btnLocGiaoHang, btnLocDanhGia, btnBlogs,btnTaiKhoan;
    RadioButton rdMoiNhat, rdDanhMuc, rdKhuVuc;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_odau,container,false);
        recyclerOdau = (RecyclerView) view.findViewById(R.id.recyclerOdau);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBarODau);
        nestedScrollView = (NestedScrollView) view.findViewById(R.id.nestScrollViewODau);
        btnThemQuanAn = (Button)view.findViewById(R.id.btnThemQuanAn);
        btnGanToi = (Button)view.findViewById(R.id.btnGanToi);
        rdMoiNhat = (RadioButton) view.findViewById(R.id.rd_moinhat);
        rdDanhMuc = (RadioButton) view.findViewById(R.id.rd_danhmuc);
        rdKhuVuc = (RadioButton) view.findViewById(R.id.rd_khuvuc);

        btnLocGiaoHang =(Button)view.findViewById(R.id.btnLocGiaoHang);
        btnLocDanhGia= (Button)view.findViewById(R.id.btnLocDanhGia);
        btnBlogs = (Button)view.findViewById(R.id.btnBlogs);
        btnTaiKhoan = (Button)view.findViewById(R.id.btnTaiKhoan);

        nestedScrollView.smoothScrollTo(0,0);
        nestedScrollView.setSmoothScrollingEnabled(true);

        sharedPreferences = getContext().getSharedPreferences("toado", Context.MODE_PRIVATE);
        vitrihientai = new Location("");
        vitrihientai.setLatitude(Double.parseDouble(sharedPreferences.getString("latitude","0")));
        vitrihientai.setLongitude(Double.parseDouble(sharedPreferences.getString("longitude","0")));

        odauController = new OdauController(getContext());


        int mode = getActivity().getIntent().getIntExtra("mode",0);
        if (mode == 1)
            odauController.getDanhSachQuanAnGanNhatController(getContext(),nestedScrollView,recyclerOdau,progressBar,vitrihientai);
        else if (mode == 2)
            odauController.getDanhSachQuanAnCoGiaoHangController(getContext(),nestedScrollView,recyclerOdau,progressBar,vitrihientai);
        else if (mode == 3)
            odauController.getDanhSachQuanAnTheoDiemDanhGiaController(getContext(),nestedScrollView,recyclerOdau,progressBar,vitrihientai);
        else
            odauController.getDanhSachQuanAnController(getContext(),nestedScrollView,recyclerOdau,progressBar,vitrihientai);

        rdMoiNhat.setOnClickListener(this);
        rdDanhMuc.setOnClickListener(this);
        rdKhuVuc.setOnClickListener(this);
        btnGanToi.setOnClickListener(this);
        btnLocGiaoHang.setOnClickListener(this);
        btnLocDanhGia.setOnClickListener(this);
        btnBlogs.setOnClickListener(this);
        btnThemQuanAn.setOnClickListener(this);
        btnTaiKhoan.setOnClickListener(this);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rd_danhmuc:
                nestedScrollView.scrollTo(0,0);
                break;
            case R.id.rd_khuvuc:
                nestedScrollView.scrollTo(0,0);
                break;
            case R.id.rd_moinhat:
                Intent intentMoiNhat = new Intent(getContext(), TrangChuActivity.class);
                intentMoiNhat.putExtra("mode",0);
                getActivity().finish();
                startActivity(intentMoiNhat);
                break;
            case R.id.btnGanToi:
                Intent intentGanToi = new Intent(getContext(), TrangChuActivity.class);
                intentGanToi.putExtra("mode",1);
                getActivity().finish();
                startActivity(intentGanToi);
                break;
            case R.id.btnLocGiaoHang:
                Intent intentGiaoHang = new Intent(getContext(), TrangChuActivity.class);
                intentGiaoHang.putExtra("mode",2);
                getActivity().finish();
                startActivity(intentGiaoHang);
                break;
            case R.id.btnLocDanhGia:
                Intent intentDanhGia = new Intent(getContext(), TrangChuActivity.class);
                intentDanhGia.putExtra("mode",3);
                getActivity().finish();
                startActivity(intentDanhGia);
                break;
            case R.id.btnBlogs:
                String url = "https://www.foody.vn/ho-chi-minh/food/bai-viet/an-uong";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                break;
            case R.id.btnThemQuanAn:
                Intent iThemQuanAn = new Intent(getContext(),ThemQuanAnActivity.class);
                startActivity(iThemQuanAn);
                break;
            case R.id.btnTaiKhoan:
                Intent iTaiKhoan = new Intent(getContext(),TaiKhoanActivity.class);
                startActivity(iTaiKhoan);
                break;
        }
    }
}
