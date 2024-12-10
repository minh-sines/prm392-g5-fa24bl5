package com.fu.fe.minhtq.prm392g5fa24bl5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fu.fe.minhtq.prm392g5fa24bl5.model.*;

import java.util.List;

public class RecipeSavedAdapter extends RecyclerView.Adapter<RecipeSavedAdapter.RecipeSavedViewHolder> {
    private final Context context;
    private List<Recipe> recipeList;

    public RecipeSavedAdapter(Context context, List<Recipe> recipeList) {
        this.context = context;
        this.recipeList = recipeList;
    }

    @NonNull
    @Override
    public RecipeSavedAdapter.RecipeSavedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.saved_item, parent, false);
        return new RecipeSavedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeSavedAdapter.RecipeSavedViewHolder holder, int position) {
        holder.titleView.setText(recipeList.get(position).getTitle());
        holder.ingredientView.setText(recipeList.get(position).getDescription());
        holder.stepView.setText(recipeList.get(position).getTime());
        holder.imageView.setImageResource(recipeList.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        if (recipeList != null) {
            return recipeList.size();
        }
        return 0;
    }

    public class RecipeSavedViewHolder extends RecyclerView.ViewHolder {
        TextView titleView;
        TextView ingredientView;
        TextView stepView;

        ImageView imageView;

        ImageButton shareButton;

        public RecipeSavedViewHolder(@NonNull View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.fragment_2_2_title);
            ingredientView = itemView.findViewById(R.id.fragment_2_2_ingredient);
            stepView = itemView.findViewById(R.id.fragment_2_2_step);
            imageView = itemView.findViewById(R.id.fragment_2_2_image);
            shareButton = itemView.findViewById(R.id.shareButton);
            bindingAction();
        }

        private void bindingAction() {
            itemView.setOnClickListener(this::onClickItem);
            shareButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Toast.makeText(itemView.getContext(), "Share button clicked on "+titleView.getText(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        private void onClickItem(View view) {
            Toast.makeText(itemView.getContext(), "Item clicked on "+titleView.getText(), Toast.LENGTH_SHORT).show();
        }

    }
}
