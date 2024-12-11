package com.fu.fe.minhtq.prm392g5fa24bl5.HomePage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fu.fe.minhtq.prm392g5fa24bl5.R;
import com.fu.fe.minhtq.prm392g5fa24bl5.model.Recipe;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder>{

    private List<Recipe> data;
    public ItemAdapter(List<Recipe> data){
        this.data = data;}
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.fooditem, parent, false);
        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Recipe item = data.get(position);
        holder.setData(item);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
