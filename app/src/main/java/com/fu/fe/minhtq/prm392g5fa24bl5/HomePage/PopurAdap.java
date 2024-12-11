package com.fu.fe.minhtq.prm392g5fa24bl5.HomePage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fu.fe.minhtq.prm392g5fa24bl5.R;
import com.fu.fe.minhtq.prm392g5fa24bl5.model.Recipe;

import java.util.List;

public class PopurAdap extends RecyclerView.Adapter<PopurViewHolder>{

    private List<Recipe> data;
    public PopurAdap(List<Recipe> data){
        this.data = data;}
    @NonNull
    @Override
    public PopurViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.recipeimage, parent, false);
        return new PopurViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PopurViewHolder holder, int position) {
        Recipe item = data.get(position);
        holder.setData(item);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}

