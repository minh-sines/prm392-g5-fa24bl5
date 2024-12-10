package com.fu.fe.minhtq.prm392g5fa24bl5.manage.recipe;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fu.fe.minhtq.prm392g5fa24bl5.R;

import java.util.List;

public class ImgDescAdapter extends RecyclerView.Adapter<ImgDescViewHolder>{

    private List<String> imgDescList;

    public ImgDescAdapter(List<String> imgDescList) {
        this.imgDescList = imgDescList;
    }

    @NonNull
    @Override
    public ImgDescViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.description_image, parent, false);
        return new ImgDescViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImgDescViewHolder holder, int position) {
        String imgDesc = imgDescList.get(position);
        holder.setData(imgDesc);
    }

    @Override
    public int getItemCount() {
        return imgDescList.size();
    }
}
