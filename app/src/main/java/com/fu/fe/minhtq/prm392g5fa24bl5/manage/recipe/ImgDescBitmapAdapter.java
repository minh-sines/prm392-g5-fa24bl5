package com.fu.fe.minhtq.prm392g5fa24bl5.manage.recipe;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fu.fe.minhtq.prm392g5fa24bl5.R;

import java.util.List;

public class ImgDescBitmapAdapter extends RecyclerView.Adapter<ImgDescBitmapViewHolder>{

    private List<Bitmap> imageList;

    public ImgDescBitmapAdapter(List<Bitmap> imageList) {
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public ImgDescBitmapViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.description_image_del, parent, false);
        return new ImgDescBitmapViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImgDescBitmapViewHolder holder, int position) {
        Bitmap imgDesc = imageList.get(position);
        holder.setData(imgDesc);
        holder.imgDel.setOnClickListener(v -> {
            // Gọi hàm onDeleteImage với view và vị trí ảnh
            onDeleteImage(v, holder.getAdapterPosition());
        });
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }
    private void onDeleteImage(View view, int position) {
        // Xóa ảnh tại vị trí được chỉ định
        imageList.remove(position);

        // Thông báo cho adapter để cập nhật RecyclerView
        this.notifyItemRemoved(position);
        this.notifyItemRangeChanged(position, imageList.size());
    }
}
