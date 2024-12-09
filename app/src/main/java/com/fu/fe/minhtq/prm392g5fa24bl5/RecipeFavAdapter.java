package com.fu.fe.minhtq.prm392g5fa24bl5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fu.fe.minhtq.prm392g5fa24bl5.model.Recipe;

import java.util.List;

public class RecipeFavAdapter extends RecyclerView.Adapter<RecipeFavAdapter.RecipeFavViewHolder> {
    private final Context context;
    private List<Recipe> recipeList;

    public RecipeFavAdapter(Context context, List<Recipe> recipeList) {
        this.context = context;
        this.recipeList = recipeList;
    }

    @NonNull
    @Override
    public RecipeFavAdapter.RecipeFavViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.favorite_item, parent, false);
        return new RecipeFavViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecipeFavAdapter.RecipeFavViewHolder holder, int position) {
        holder.titleView.setText(recipeList.get(position).getTitle());
        holder.descriptionView.setText(recipeList.get(position).getDescription());
        holder.timeView.setText(recipeList.get(position).getTime());
        holder.imageView.setImageResource(recipeList.get(position).getCreated_by());
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public static class RecipeFavViewHolder extends RecyclerView.ViewHolder {
        TextView titleView;
        TextView descriptionView;
        TextView timeView;
        ImageView imageView;

        public RecipeFavViewHolder(@NonNull View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.fragment_2_1_title);
            descriptionView = itemView.findViewById(R.id.fragment_2_1_ingredient);
            timeView = itemView.findViewById(R.id.fragment_2_1_step);
            imageView = itemView.findViewById(R.id.fragment_2_1_image);
        }
    }

}
