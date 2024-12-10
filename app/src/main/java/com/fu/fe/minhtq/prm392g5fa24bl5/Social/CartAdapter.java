package com.fu.fe.minhtq.prm392g5fa24bl5.Social;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fu.fe.minhtq.prm392g5fa24bl5.R;
import com.fu.fe.minhtq.prm392g5fa24bl5.model.Recipe;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartViewHolder> {

    private List<Recipe> data;

    public CartAdapter(List<Recipe> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.social_detail_item, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Recipe r = data.get(position);
        holder.setRecipe(r);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
