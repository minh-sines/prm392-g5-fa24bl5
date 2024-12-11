package com.fu.fe.minhtq.prm392g5fa24bl5.manage.recipe;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fu.fe.minhtq.prm392g5fa24bl5.R;

import java.io.File;

public class ImgDescViewHolder extends RecyclerView.ViewHolder {
    private ImageView imgDesc;

    private void bindingView(){
        imgDesc = itemView.findViewById(R.id.imgDescriptionItem);
    }

    private void bindingAction(){

    }
    public ImgDescViewHolder(@NonNull View itemView) {
        super(itemView);
        bindingView();
        bindingAction();
    }
    public void setData(String imgDescStr){
        loadImageToImageView(imgDescStr, itemView.getContext(), imgDesc);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                200,  // Đặt chiều rộng là MATCH_PARENT
                200       // Đặt chiều cao là 200dp (chuyển đổi dp sang px)
        );
        imgDesc.setLayoutParams(layoutParams);
        imgDesc.setScaleType(ImageView.ScaleType.FIT_XY);
    }

    private void loadImageToImageView(String fileName, Context context, ImageView imageView) {
        try {
            // Lấy thư mục private của ứng dụng
            File directory = context.getFilesDir();

            // Tạo đường dẫn tới file ảnh
            File file = new File(directory, fileName);

            // Kiểm tra xem file có tồn tại không
            if (file.exists()) {
                // Đọc ảnh từ file và chuyển thành Bitmap
                Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());

                // Hiển thị ảnh trên ImageView
                imageView.setImageBitmap(bitmap);
            } else {
                // Nếu file không tồn tại, đặt ảnh mặc định
                imageView.setImageResource(R.drawable.default_recipe); // Đổi R.drawable.default_image thành ảnh mặc định của bạn

            }
        } catch (Exception e) {
            e.printStackTrace();
            // Đặt ảnh mặc định nếu có lỗi
            imageView.setImageResource(R.drawable.default_recipe);
        }
    }


}
