package com.fu.fe.minhtq.prm392g5fa24bl5.HomePage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fu.fe.minhtq.prm392g5fa24bl5.R;
import com.fu.fe.minhtq.prm392g5fa24bl5.database.AppDatabase;
import com.fu.fe.minhtq.prm392g5fa24bl5.model.Favorite;
import com.fu.fe.minhtq.prm392g5fa24bl5.model.Recipe;

import java.io.File;
import java.util.List;

public class RecipeListAdapter extends RecyclerView.Adapter<com.fu.fe.minhtq.prm392g5fa24bl5.HomePage.RecipeListAdapter.RecipeListViewHolder> {
    private final Context context;
    private List<Recipe> recipeList;

    public RecipeListAdapter(Context context, List<Recipe> recipeList) {
        this.context = context;
        this.recipeList = recipeList;
    }

    @NonNull
    @Override
    public com.fu.fe.minhtq.prm392g5fa24bl5.HomePage.RecipeListAdapter.RecipeListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.newest_item, parent, false);
        return new com.fu.fe.minhtq.prm392g5fa24bl5.HomePage.RecipeListAdapter.RecipeListViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull com.fu.fe.minhtq.prm392g5fa24bl5.HomePage.RecipeListAdapter.RecipeListViewHolder holder, int position) {
        Recipe recipe = recipeList.get(position);
        holder.titleView.setText(recipeList.get(position).getTitle());
        holder.descriptionView.setText(recipeList.get(position).getDescription());
        holder.timeView.setText(recipeList.get(position).getTime());
//        holder.imageView.setImageResource(recipeList.get(position).getImage());
        holder.recipe_id.setText(String.valueOf(recipeList.get(position).getRecipe_id()));
        holder.loadImageToImageView(recipeList.get(position).getImage(), context, holder.imageView);
    }


    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public static class RecipeListViewHolder extends RecyclerView.ViewHolder {
        TextView titleView;
        TextView descriptionView;
        TextView timeView;
        ImageView imageView;

        ImageButton bookmarkButton;

        Boolean isBookmarked = false;

        TextView recipe_id;
//        AppDatabase instance = AppDatabase.getInstance(itemView.getContext());

        public RecipeListViewHolder(@NonNull View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.new_recipe_title);
            descriptionView = itemView.findViewById(R.id.new_recipe_ingredient);
            timeView = itemView.findViewById(R.id.newest_recipe_step);
            imageView = itemView.findViewById(R.id.new_recipe_image);
            bookmarkButton = itemView.findViewById(R.id.bookmarkButton);
            bookmarkButton.setSelected(isBookmarked);
            recipe_id = itemView.findViewById(R.id.newest_recipe__id);
            //hide ID
            recipe_id.setVisibility(View.GONE);
//            isBookmarked = instance.favoriteDAO().isFavorite(1, Integer.parseInt(recipe_id.getText().toString()));
//            bookmarkButton.setImageResource(isBookmarked ? R.drawable.bookmark_added : R.drawable.bookmark_unadd);
            bindingAction();
        }

        private void bindingAction() {
            itemView.setOnClickListener(this::onClickItem);
//            bookmarkButton.setOnClickListener(new View.OnClickListener(){
//                @Override
//                public void onClick(View view) {
//                    //click change state
//                    isBookmarked = !isBookmarked;
//                    bookmarkButton.setImageResource(isBookmarked ? R.drawable.bookmark_added : R.drawable.bookmark_unadd);
//                    if(isBookmarked) {
//                        instance.favoriteDAO().deleteFavorite(2, Integer.parseInt(recipe_id.getText().toString()));
//                        Toast.makeText(itemView.getContext(), "Bookmark removed for " + recipe_id.getText().toString(), Toast.LENGTH_SHORT).show();
//                    }
//                    else {
//                        instance.favoriteDAO().insertFavorite(new Favorite(2, Integer.parseInt(recipe_id.getText().toString()), System.currentTimeMillis()));
//                        Toast.makeText(itemView.getContext(), "Bookmark added for " + recipe_id.getText().toString(), Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
        }

        private void onClickItem(View view) {
            Toast.makeText(itemView.getContext(), "Item clicked on "+titleView.getText(), Toast.LENGTH_SHORT).show();
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
                    imageView.setImageResource(R.drawable.cutepenguin); // Đổi R.drawable.default_image thành ảnh mặc định của bạn

                }
            } catch (Exception e) {
                e.printStackTrace();
                // Đặt ảnh mặc định nếu có lỗi
                imageView.setImageResource(R.drawable.cutepenguin);
            }
        }
    }

}
