package com.cntn16.vuongcong.myfoody.View;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.cntn16.vuongcong.myfoody.Adapters.AdapterMonAn;
import com.cntn16.vuongcong.myfoody.Model.DatMon;
import com.cntn16.vuongcong.myfoody.Model.MonAnModel;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.cntn16.vuongcong.myfoody.Adapters.ApdaterBinhLuan;
import com.cntn16.vuongcong.myfoody.Controller.ChiTietQuanController;
import com.cntn16.vuongcong.myfoody.Controller.ThucDonController;
import com.cntn16.vuongcong.myfoody.Model.QuanAnModel;
import com.cntn16.vuongcong.myfoody.Model.TienIchModel;
import com.cntn16.vuongcong.myfoody.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ChiTietQuanAnActivity extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener {

    TextView txtTenQuanAn,txtDiaChi,txtThoiGianHoatDong,txtTrangThaiHoatDong,txtTongSoHinhAnh,txtTongSoBinhLuan,txtTongSoCheckIn,txtTongSoLuuLai,txtTieuDeToolbar,txtGioiHanGia,txtTenWifi,txtMatKhauWifi,txtNgayDangWifi;
    ImageView imHinhAnhQuanAn,imgPlayTrailer;
    Button btnBinhLuan, btnShare, btnCheckin, btnDatMon;
    LinearLayout khungWifi;
    QuanAnModel quanAnModel;
    Toolbar toolbar;
    RecyclerView recyclerViewBinhLuan;
    ApdaterBinhLuan adapterBinhLuan;
    GoogleMap googleMap;
    MapFragment mapFragment;
    LinearLayout khungTienIch;
    View khungTinhNang;
    VideoView videoView;
    RecyclerView recyclerThucDon;

    ChiTietQuanController chiTietQuanController;
    ThucDonController thucDonController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main_chitietquanan);

        quanAnModel = getIntent().getParcelableExtra("quanan");

        txtTenQuanAn = (TextView) findViewById(R.id.txtTenQuanAn);
        txtDiaChi = (TextView) findViewById(R.id.txtDiaChiQuanAn);
        txtThoiGianHoatDong = (TextView) findViewById(R.id.txtThoiGianHoatDong);
        txtTrangThaiHoatDong = (TextView) findViewById(R.id.txtTrangThaiHoatDong);
        txtTongSoBinhLuan = (TextView) findViewById(R.id.tongSoBinhLuan);
        txtTongSoCheckIn = (TextView) findViewById(R.id.tongSoCheckIn);
        txtTongSoHinhAnh = (TextView) findViewById(R.id.tongSoHinhAnh);
        txtTongSoLuuLai = (TextView) findViewById(R.id.tongSoLuuLai);
        imHinhAnhQuanAn = (ImageView) findViewById(R.id.imHinhQuanAn);
        txtTieuDeToolbar = (TextView) findViewById(R.id.txtTieuDeToolbar);
        txtGioiHanGia = (TextView) findViewById(R.id.txtGioiHanGia);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerViewBinhLuan = (RecyclerView) findViewById(R.id.recyclerBinhLuanChiTietQuanAn);
        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        khungTienIch = (LinearLayout) findViewById(R.id.khungTienTich);
        txtTenWifi = (TextView) findViewById(R.id.txtTenWifi);
        txtMatKhauWifi = (TextView) findViewById(R.id.txtMatKhauWifi);
        khungWifi = (LinearLayout) findViewById(R.id.khungWifi);
        txtNgayDangWifi = (TextView) findViewById(R.id.txtNgayDangWifi);
        khungTinhNang = (View) findViewById(R.id.khungTinhNang);
        btnBinhLuan = (Button) findViewById(R.id.btnBinhLuan);
        btnShare = (Button)findViewById(R.id.btnShare);
        btnCheckin =(Button)findViewById(R.id.btnCheckIn);
        btnDatMon = (Button) findViewById(R.id.btnDatMon);
        videoView = (VideoView) findViewById(R.id.videoTrailer);
        imgPlayTrailer = (ImageView) findViewById(R.id.imgPLayTrailer);
        recyclerThucDon = (RecyclerView) findViewById(R.id.recyclerThucDon);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        chiTietQuanController = new ChiTietQuanController();
        thucDonController = new ThucDonController();

        mapFragment.getMapAsync(this);

        HienThiChiTietQuanAn();

        khungTinhNang.setOnClickListener(this);
        btnBinhLuan.setOnClickListener(this);
        btnShare.setOnClickListener(this);
        btnCheckin.setOnClickListener(this);
        btnDatMon.setOnClickListener(this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void HienThiChiTietQuanAn(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");

        String giohientai = dateFormat.format(calendar.getTime());
        String giomocua = quanAnModel.getGiomocua();
        String giodongcua = quanAnModel.getGiodongcua();

        try {
            Date dateHienTai = dateFormat.parse(giohientai);
            Date dateMoCua = dateFormat.parse(giomocua);
            Date dateDongCua = dateFormat.parse(giodongcua);

            if(dateHienTai.after(dateMoCua) && dateHienTai.before(dateDongCua)){
                //gio mo cua
                txtTrangThaiHoatDong.setText(getString(R.string.dangmocua));
            }else{
                //dong cua
                txtTrangThaiHoatDong.setText(getString(R.string.dadongcua));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }


        txtTieuDeToolbar.setText(quanAnModel.getTenquanan());

        txtTenQuanAn.setText(quanAnModel.getTenquanan());
        txtDiaChi.setText(quanAnModel.getChiNhanhQuanAnModelList().get(0).getDiachi());
        txtThoiGianHoatDong.setText(quanAnModel.getGiomocua() + " - " + quanAnModel.getGiodongcua());
        txtTongSoHinhAnh.setText(quanAnModel.getHinhanhquanan().size() + "");
        txtTongSoBinhLuan.setText(quanAnModel.getBinhLuanModelList().size() + "");
        txtThoiGianHoatDong.setText(giomocua + " - " + giodongcua);

        DownLoadHinhTienIch();


        if(quanAnModel.getGiatoida() != 0 && quanAnModel.getGiatoithieu() != 0){
            NumberFormat numberFormat = new DecimalFormat("###,###");
            String giatoithieu = numberFormat.format(quanAnModel.getGiatoithieu()) + " đ";
            String giatoida = numberFormat.format(quanAnModel.getGiatoida()) + " đ";
            txtGioiHanGia.setText( giatoithieu + " - " + giatoida );
        }else{
            txtGioiHanGia.setVisibility(View.INVISIBLE);
        }

        StorageReference storageHinhQuanAn = FirebaseStorage.getInstance().getReference().child("hinhanh").child(quanAnModel.getHinhanhquanan().get(0));
        long ONE_MEGABYTE = 10*1024 * 1024;
        storageHinhQuanAn.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                imHinhAnhQuanAn.setImageBitmap(bitmap);
            }
        });

        if(quanAnModel.getVideogioithieu() != null){
            videoView.setVisibility(View.VISIBLE);
            imgPlayTrailer.setVisibility(View.VISIBLE);
            imHinhAnhQuanAn.setVisibility(View.GONE);
            StorageReference storageVideo = FirebaseStorage.getInstance().getReference().child("video").child(quanAnModel.getVideogioithieu());
            storageVideo.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    videoView.setVideoURI(uri);
                    videoView.seekTo(1);
                }
            });

            imgPlayTrailer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    videoView.start();
                    MediaController mediaController = new MediaController(ChiTietQuanAnActivity.this);
                    videoView.setMediaController(mediaController);
                    imgPlayTrailer.setVisibility(View.GONE);
                }
            });

        }else{
            videoView.setVisibility(View.GONE);
            imgPlayTrailer.setVisibility(View.GONE);
            imHinhAnhQuanAn.setVisibility(View.VISIBLE);
        }

        //Load danh sach binh luan cua quan
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewBinhLuan.setLayoutManager(layoutManager);
        adapterBinhLuan = new ApdaterBinhLuan(this,R.layout.custom_layout_binhluan,quanAnModel.getBinhLuanModelList());
        recyclerViewBinhLuan.setAdapter(adapterBinhLuan);
        adapterBinhLuan.notifyDataSetChanged();

        NestedScrollView nestedScrollViewChiTiet = (NestedScrollView) findViewById(R.id.nestScrollViewChiTiet);
        nestedScrollViewChiTiet.smoothScrollTo(0,0);

        chiTietQuanController.HienThiDanhSachWifiQuanAn(quanAnModel.getMaquanan(),txtTenWifi,txtMatKhauWifi,txtNgayDangWifi);
        khungWifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iDanhSachWifi = new Intent(ChiTietQuanAnActivity.this,CapNhatDanhSachWifiActivity.class);
                iDanhSachWifi.putExtra("maquanan",quanAnModel.getMaquanan());
                startActivity(iDanhSachWifi);
            }
        });

        thucDonController.getDanhSachThucDonQuanAnTheoMa(this,quanAnModel.getMaquanan(),recyclerThucDon);
    }

    @Override
    protected void onStart() {
        super.onStart();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewBinhLuan.setLayoutManager(layoutManager);
        adapterBinhLuan = new ApdaterBinhLuan(this,R.layout.custom_layout_binhluan,quanAnModel.getBinhLuanModelList());
        recyclerViewBinhLuan.setAdapter(adapterBinhLuan);
        adapterBinhLuan.notifyDataSetChanged();

        NestedScrollView nestedScrollViewChiTiet = (NestedScrollView) findViewById(R.id.nestScrollViewChiTiet);
        nestedScrollViewChiTiet.smoothScrollTo(0,0);



    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        double latitude = quanAnModel.getChiNhanhQuanAnModelList().get(0).getLatitude();
        double longitude = quanAnModel.getChiNhanhQuanAnModelList().get(0).getLongitude();

        LatLng latLng = new LatLng(latitude,longitude);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title(quanAnModel.getTenquanan());

        googleMap.addMarker(markerOptions);

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng,14);
        googleMap.moveCamera(cameraUpdate);
    }

    private void DownLoadHinhTienIch(){

        for (String matienich : quanAnModel.getTienich()){
            DatabaseReference nodeTienIch = FirebaseDatabase.getInstance().getReference().child("quanlytienichs").child(matienich);
            nodeTienIch.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    TienIchModel tienIchModel = dataSnapshot.getValue(TienIchModel.class);

                    StorageReference storageHinhQuanAn = FirebaseStorage.getInstance().getReference().child("hinhtienich").child(tienIchModel.getHinhtienich());
                    long ONE_MEGABYTE = 1024 * 1024;
                    storageHinhQuanAn.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                        @Override
                        public void onSuccess(byte[] bytes) {
                            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                            ImageView imageTienIch = new ImageView(ChiTietQuanAnActivity.this);
                            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(100,100);
                            layoutParams.setMargins(10,10,10,10);
                            imageTienIch.setLayoutParams(layoutParams);
                            imageTienIch.setScaleType(ImageView.ScaleType.FIT_XY);
                            imageTienIch.setPadding(5,5,5,5);


                            imageTienIch.setImageBitmap(bitmap);
                            khungTienIch.addView(imageTienIch);
                        }
                    });
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.khungTinhNang:
                Intent iDanDuong = new Intent(this,DanDuongToiQuanAnActivity.class);
                iDanDuong.putExtra("latitude",quanAnModel.getChiNhanhQuanAnModelList().get(0).getLatitude());
                iDanDuong.putExtra("longitude",quanAnModel.getChiNhanhQuanAnModelList().get(0).getLongitude());
                startActivity(iDanDuong);
                break;

            case R.id.btnBinhLuan:
                Intent iBinhLuan = new Intent(this,BinhLuanActivity.class);
                iBinhLuan.putExtra("maquanan",quanAnModel.getMaquanan());
                iBinhLuan.putExtra("tenquan",quanAnModel.getTenquanan());
                iBinhLuan.putExtra("diachi",quanAnModel.getChiNhanhQuanAnModelList().get(0).getDiachi());
                startActivity(iBinhLuan);
                break;
            case R.id.btnShare:
                Intent iShare= new Intent(this,ShareActivity.class);
                iShare.putExtra("hinhShare", quanAnModel.getHinhanhquanan().get(0));
                startActivity(iShare);
                break;
            case R.id.btnCheckIn:
                ShareDialog shareDialog = new ShareDialog(this);
                CallbackManager callbackManager = CallbackManager.Factory.create();
                shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
                    @Override
                    public void onSuccess(Sharer.Result result) {

                    }

                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onError(FacebookException error) {

                    }
                });

                ShareLinkContent contentLink = new ShareLinkContent.Builder()
                        .setContentUrl(Uri.parse("https://www.foody.vn/ho-chi-minh/dia-diem?q="+quanAnModel.getTenquanan()+"&ss=header_search_form"))
                        .build();

                shareDialog.show(contentLink, ShareDialog.Mode.AUTOMATIC);
                break;
            case R.id.btnDatMon:
                if (quanAnModel.isGiaohang()){
                String message=String.format("Thực đơn đặt hàng\n");
                for (DatMon i:AdapterMonAn.datMonList){
                    message+=String.format("%s - Số lượng: %d\n",i.getTenMonAn(),i.getSoLuong());
                }

                Intent smsIntent = new Intent(android.content.Intent.ACTION_VIEW);
                smsIntent.setType("vnd.android-dir/mms-sms");

                if (quanAnModel.getSodienthoai()!=null)
                    smsIntent.putExtra("address",quanAnModel.getSodienthoai());
                else
                    smsIntent.putExtra("address","Số điện thoại bạn có thể lấy trên Blogs");
                smsIntent.putExtra("sms_body",message);
                startActivity(smsIntent);}
                else
                {
                    Toast.makeText(this,"Không hỗ trợ giao hàng!",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
