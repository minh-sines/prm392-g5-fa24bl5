package com.fu.fe.minhtq.prm392g5fa24bl5.HomePage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fu.fe.minhtq.prm392g5fa24bl5.R;
import com.fu.fe.minhtq.prm392g5fa24bl5.model.Recipe;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchViewHolder> {
    private List<Recipe> data;
    public SearchAdapter(List<Recipe> data){
        this.data = data;}
    public void setRecipeList(List<Recipe> recipeList) {
        this.data = recipeList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.searchitem, parent, false);
        return new SearchViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        Recipe item = data.get(position);
        holder.setData(item);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
