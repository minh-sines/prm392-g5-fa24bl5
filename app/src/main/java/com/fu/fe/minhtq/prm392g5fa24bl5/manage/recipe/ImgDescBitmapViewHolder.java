package com.fu.fe.minhtq.prm392g5fa24bl5.manage.recipe;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fu.fe.minhtq.prm392g5fa24bl5.R;

public class ImgDescBitmapViewHolder extends RecyclerView.ViewHolder{

    public ImageView imgDesc;
    public ImageView imgDel;

    private void bindingView(){
        imgDesc = itemView.findViewById(R.id.imgDescriptionItem);
        imgDel = itemView.findViewById(R.id.btnDeleteImage);
    }

    private void bindingAction(){
        imgDel.setOnClickListener(this::onDeleteImage);
    }

    private void onDeleteImage(View view) {

    }



    public void setData(Bitmap imgDescBitmap){
        new Thread(() -> {
            Bitmap resizedBitmap = resizeBitmap(imgDescBitmap, 200, 200); // Resize ảnh

            // Cập nhật giao diện trên UI thread
            imgDesc.post(() -> {
                imgDesc.setImageBitmap(resizedBitmap);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        200,  // Chiều rộng cố định là 200px
                        250   // Chiều cao cố định là 200px
                );
                imgDesc.setLayoutParams(layoutParams);
                imgDesc.setScaleType(ImageView.ScaleType.FIT_XY);
            });
        }).start();
    }

    private Bitmap resizeBitmap(Bitmap originalBitmap, int width, int height) {
        return Bitmap.createScaledBitmap(originalBitmap, width, height, true);
    }



    public ImgDescBitmapViewHolder(@NonNull View itemView) {
        super(itemView);
        bindingView();
        bindingAction();
    }
}
