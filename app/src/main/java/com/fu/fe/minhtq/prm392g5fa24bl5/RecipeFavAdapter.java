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
        holder.imageView.setImageResource(recipeList.get(position).getImage());
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

        ImageButton bookmarkButton;

        Boolean isBookmarked = false;

        public RecipeFavViewHolder(@NonNull View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.fragment_2_1_title);
            descriptionView = itemView.findViewById(R.id.fragment_2_1_ingredient);
            timeView = itemView.findViewById(R.id.fragment_2_1_step);
            imageView = itemView.findViewById(R.id.fragment_2_1_image);
            bookmarkButton = itemView.findViewById(R.id.bookmarkButton);
            bookmarkButton.setSelected(isBookmarked);
            bindingAction();

        }

        private void bindingAction() {
            itemView.setOnClickListener(this::onClickItem);
            bookmarkButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    isBookmarked = !isBookmarked;
                    bookmarkButton.setSelected(isBookmarked);
                    if(isBookmarked)
                        Toast.makeText(itemView.getContext(), "Bookmark removed for "+titleView.getText(), Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(itemView.getContext(), "Bookmark added for "+titleView.getText(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        private void onClickItem(View view) {
            Toast.makeText(itemView.getContext(), "Item clicked on "+titleView.getText(), Toast.LENGTH_SHORT).show();
        }
    }

}
