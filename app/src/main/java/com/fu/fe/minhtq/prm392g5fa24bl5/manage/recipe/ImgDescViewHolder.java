package com.fu.fe.minhtq.prm392g5fa24bl5.manage.recipe;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fu.fe.minhtq.prm392g5fa24bl5.R;

import java.io.File;

public class ImgDescViewHolder extends RecyclerView.ViewHolder {
    private ImageView imgDesc;

    private String imgDescStr1;

    private void bindingView(){
        imgDesc = itemView.findViewById(R.id.imgDescriptionItem);
    }

    private void bindingAction(){
        imgDesc.setOnClickListener(this::onResizeImage);
    }

    private void onResizeImage(View view) {
        Context context = itemView.getContext();
        if (context instanceof Activity) {
            ((Activity) context).runOnUiThread(() ->
                    showImagePopup(imgDescStr1)
            );
        }
    }
    private void showImagePopup(String imagePath) {
        // Tạo Dialog
        Dialog dialog = new Dialog(itemView.getContext()); // Thay "this" bằng context nếu không phải Activity
        dialog.setContentView(R.layout.dialog_image_popup);

        // Tìm ImageView trong layout của dialog
        ImageView imageView = dialog.findViewById(R.id.dialogImageView);

        // Tải ảnh vào ImageView
        loadImageToImageView(imagePath, imageView);

        // Thiết lập kích thước của dialog
        dialog.getWindow().setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );

        // Hiển thị Dialog
        dialog.show();

        // Đóng dialog khi nhấn vào ảnh
        imageView.setOnClickListener(v -> dialog.dismiss());
    }

    private void loadImageToImageView(String fileName, ImageView imageView) {
        try {
            Context context = itemView.getContext();
            File directory = context.getFilesDir(); // Thư mục lưu ảnh
            File file = new File(directory, fileName);

            if (file.exists()) {
                Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                imageView.setImageBitmap(bitmap);
            } else {
                imageView.setImageResource(R.drawable.default_recipe);
            }
        } catch (Exception e) {
            e.printStackTrace();
            imageView.setImageResource(R.drawable.default_recipe);
        }
    }

    public ImgDescViewHolder(@NonNull View itemView) {
        super(itemView);
        bindingView();
        bindingAction();
    }
    public void setData(String imgDescStr){
        imgDescStr1 = imgDescStr;
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
