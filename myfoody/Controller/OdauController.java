package com.cntn16.vuongcong.myfoody.Controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.cntn16.vuongcong.myfoody.Model.BinhLuanModel;
import com.cntn16.vuongcong.myfoody.Model.ChiNhanhQuanAnModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.cntn16.vuongcong.myfoody.Adapters.AdapterRecyclerOdau;
import com.cntn16.vuongcong.myfoody.Controller.Interfaces.OdauInterface;
import com.cntn16.vuongcong.myfoody.Model.QuanAnModel;
import com.cntn16.vuongcong.myfoody.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;



public class OdauController {
    Context context;
    QuanAnModel quanAnModel;
    AdapterRecyclerOdau adapterRecyclerOdau;
    int itemdaco = 3;

    public OdauController(Context context){
        this.context = context;
        quanAnModel = new QuanAnModel();
    }

    //tim khoang cach min khi truyen vao chi nhanh quan an model list
    public double getMinKhoangCach(List<ChiNhanhQuanAnModel> in){
        ChiNhanhQuanAnModel min = in.get(0);
        for (ChiNhanhQuanAnModel i:in){
            if (min.getKhoangcach()>i.getKhoangcach()){
                min = i;
            }
        }
        return min.getKhoangcach();
    }

    public void getDanhSachQuanAnController(Context context, NestedScrollView nestedScrollView, RecyclerView recyclerOdau, final ProgressBar progressBar, final Location vitrihientai){

        final List<QuanAnModel> quanAnModelList = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerOdau.setLayoutManager(layoutManager);
        adapterRecyclerOdau = new AdapterRecyclerOdau(context,quanAnModelList, R.layout.custom_layout_recyclerview_odau);
        recyclerOdau.setAdapter(adapterRecyclerOdau);

        progressBar.setVisibility(View.VISIBLE);

        final OdauInterface odauInterface = new OdauInterface() {
            @Override
            public void getDanhSachQuanAnModel(final QuanAnModel quanAnModel) {
                /*final List<Bitmap> bitmaps = new ArrayList<>();
                Log.d("kiemtra",quanAnModel.getMaquanan()+quanAnModel.getTenquanan()+" "+quanAnModel.getHinhanhquanan());
                for(String linkhinh : quanAnModel.getHinhanhquanan()){

                    StorageReference storageHinhAnh = FirebaseStorage.getInstance().getReference().child("hinhanh").child(linkhinh);
                    long ONE_MEGABYTE = 1024 * 1024;
                    storageHinhAnh.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                        @Override
                        public void onSuccess(byte[] bytes) {
                            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                            bitmaps.add(bitmap);
                            quanAnModel.setBitmapList(bitmaps);

                            if(quanAnModel.getBitmapList().size() == quanAnModel.getHinhanhquanan().size()){
                                quanAnModelList.add(quanAnModel);
                                adapterRecyclerOdau.notifyDataSetChanged();
                                progressBar.setVisibility(View.GONE);
                            }

                        }
                    });
                }*/
                quanAnModelList.add(quanAnModel);

                adapterRecyclerOdau.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
            }
        };

        /*nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if(v.getChildAt(v.getChildCount() - 1) !=null){
                    if(scrollY >= (v.getChildAt(v.getChildCount() - 1)).getMeasuredHeight() - v.getMeasuredHeight()){
                        itemdaco += 3;
                        quanAnModel.getDanhSachQuanAn(odauInterface,vitrihientai,itemdaco,itemdaco-3);
                    }
                }
            }
        });*/

        quanAnModel.getDanhSachQuanAn(odauInterface,vitrihientai,itemdaco,0);

    }

    public void getDanhSachQuanAnGanNhatController(Context context, NestedScrollView nestedScrollView, RecyclerView recyclerOdau, final ProgressBar progressBar, final Location vitrihientai){

        final List<QuanAnModel> quanAnModelList = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerOdau.setLayoutManager(layoutManager);
        adapterRecyclerOdau = new AdapterRecyclerOdau(context,quanAnModelList, R.layout.custom_layout_recyclerview_odau);
        recyclerOdau.setAdapter(adapterRecyclerOdau);

        progressBar.setVisibility(View.VISIBLE);

        final OdauInterface odauInterface = new OdauInterface() {
            @Override
            public void getDanhSachQuanAnModel(final QuanAnModel quanAnModel) {
                /*final List<Bitmap> bitmaps = new ArrayList<>();
                Log.d("kiemtra",quanAnModel.getMaquanan()+quanAnModel.getTenquanan()+" "+quanAnModel.getHinhanhquanan());
                for(String linkhinh : quanAnModel.getHinhanhquanan()){

                    StorageReference storageHinhAnh = FirebaseStorage.getInstance().getReference().child("hinhanh").child(linkhinh);
                    long ONE_MEGABYTE = 1024 * 1024;
                    storageHinhAnh.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                        @Override
                        public void onSuccess(byte[] bytes) {
                            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                            bitmaps.add(bitmap);
                            quanAnModel.setBitmapList(bitmaps);

                            if(quanAnModel.getBitmapList().size() == quanAnModel.getHinhanhquanan().size()){
                                quanAnModelList.add(quanAnModel);
                                adapterRecyclerOdau.notifyDataSetChanged();
                                progressBar.setVisibility(View.GONE);
                            }

                        }
                    });
                }*/
                quanAnModelList.add(quanAnModel);

                for (int i=0;i<quanAnModelList.size()-1;i++)
                    for (int j = i+1;j<quanAnModelList.size();j++)
                    {
                        if (getMinKhoangCach(quanAnModelList.get(i).getChiNhanhQuanAnModelList())>
                                getMinKhoangCach(quanAnModelList.get(j).getChiNhanhQuanAnModelList())){
                            QuanAnModel temp = quanAnModelList.get(i);
                            quanAnModelList.set(i,quanAnModelList.get(j));
                            quanAnModelList.set(j,temp);
                        }
                    }

                adapterRecyclerOdau.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
            }
        };

        /*nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if(v.getChildAt(v.getChildCount() - 1) !=null){
                    if(scrollY >= (v.getChildAt(v.getChildCount() - 1)).getMeasuredHeight() - v.getMeasuredHeight()){
                        itemdaco += 3;
                        quanAnModel.getDanhSachQuanAn(odauInterface,vitrihientai,itemdaco,itemdaco-3);
                    }
                }
            }
        });*/

        quanAnModel.getDanhSachQuanAn(odauInterface,vitrihientai,itemdaco,0);

    }

    public void getDanhSachQuanAnCoGiaoHangController(Context context, NestedScrollView nestedScrollView, RecyclerView recyclerOdau, final ProgressBar progressBar, final Location vitrihientai){

        final List<QuanAnModel> quanAnModelList = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerOdau.setLayoutManager(layoutManager);
        adapterRecyclerOdau = new AdapterRecyclerOdau(context,quanAnModelList, R.layout.custom_layout_recyclerview_odau);
        recyclerOdau.setAdapter(adapterRecyclerOdau);

        progressBar.setVisibility(View.VISIBLE);

        final OdauInterface odauInterface = new OdauInterface() {
            @Override
            public void getDanhSachQuanAnModel(final QuanAnModel quanAnModel) {
                if (quanAnModel.isGiaohang())
                    quanAnModelList.add(quanAnModel);

                adapterRecyclerOdau.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
            }
        };

        quanAnModel.getDanhSachQuanAn(odauInterface,vitrihientai,itemdaco,0);

    }

    public void getDanhSachQuanAnTheoDiemDanhGiaController(Context context, NestedScrollView nestedScrollView, RecyclerView recyclerOdau, final ProgressBar progressBar, final Location vitrihientai){

        final List<QuanAnModel> quanAnModelList = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerOdau.setLayoutManager(layoutManager);
        adapterRecyclerOdau = new AdapterRecyclerOdau(context,quanAnModelList, R.layout.custom_layout_recyclerview_odau);
        recyclerOdau.setAdapter(adapterRecyclerOdau);

        progressBar.setVisibility(View.VISIBLE);

        final OdauInterface odauInterface = new OdauInterface() {
            @Override
            public void getDanhSachQuanAnModel(final QuanAnModel quanAnModel) {
                quanAnModelList.add(quanAnModel);

                for (int i=0;i<quanAnModelList.size()-1;i++)
                    for (int j = i+1;j<quanAnModelList.size();j++)
                    {
                        if (tinhDiemDanhGiaTB(quanAnModelList.get(i).getBinhLuanModelList())<
                                tinhDiemDanhGiaTB(quanAnModelList.get(j).getBinhLuanModelList())){
                            QuanAnModel temp = quanAnModelList.get(i);
                            quanAnModelList.set(i,quanAnModelList.get(j));
                            quanAnModelList.set(j,temp);
                        }
                    }

                adapterRecyclerOdau.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
            }
        };

        quanAnModel.getDanhSachQuanAn(odauInterface,vitrihientai,itemdaco,0);

    }

    public double tinhDiemDanhGiaTB(List<BinhLuanModel> in){
        double tb = 0;
        for (BinhLuanModel temp:in){
            tb+=temp.getChamdiem();
        }
        tb/=in.size();
        return tb;
    }

}
