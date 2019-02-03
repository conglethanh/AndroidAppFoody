package com.cntn16.vuongcong.myfoody.View;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.cntn16.vuongcong.myfoody.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareMediaContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ShareActivity extends AppCompatActivity{
    ShareButton btnShare;
    CallbackManager callbackManager;
    ShareDialog shareDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.activity_main);
        btnShare = (ShareButton)findViewById(R.id.btnShareFB);
        shareDialog = new ShareDialog(this);
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

        String linkHinh = getIntent().getStringExtra("hinhShare");

        if (linkHinh.length()>0){
            StorageReference storageHinhAnh = FirebaseStorage.getInstance().getReference().child("hinhanh").child(linkHinh);
            long TEN_MEGABYTE = 10*1024*1024;
            storageHinhAnh.getBytes(TEN_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {
                    Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                    SharePhoto photo = new SharePhoto.Builder()
                            .setBitmap(bitmap)
                            .build();
                    SharePhotoContent contentPhoto = new SharePhotoContent.Builder()
                            .addPhoto(photo)
                            .build();

                    shareDialog.show(contentPhoto, ShareDialog.Mode.AUTOMATIC);
                }
            });
        }
        else {
            Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.background);
            SharePhoto photo = new SharePhoto.Builder()
                    .setBitmap(image)
                    .build();
            SharePhotoContent contentPhoto = new SharePhotoContent.Builder()
                    .addPhoto(photo)
                    .build();

            shareDialog.show(contentPhoto, ShareDialog.Mode.AUTOMATIC);

        }
    }
}
