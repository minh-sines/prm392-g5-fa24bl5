package com.fu.fe.minhtq.prm392g5fa24bl5.Social;

import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.fu.fe.minhtq.prm392g5fa24bl5.R;
import com.fu.fe.minhtq.prm392g5fa24bl5.database.AppDatabase;
import com.fu.fe.minhtq.prm392g5fa24bl5.database.CommentDAO;
import com.fu.fe.minhtq.prm392g5fa24bl5.database.FavoriteDAO;
import com.fu.fe.minhtq.prm392g5fa24bl5.database.HeartDAO;
import com.fu.fe.minhtq.prm392g5fa24bl5.database.RecipeDAO;
import com.fu.fe.minhtq.prm392g5fa24bl5.model.Favorite;
import com.fu.fe.minhtq.prm392g5fa24bl5.model.Heart;
import com.fu.fe.minhtq.prm392g5fa24bl5.model.Recipe;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CartViewHolder extends RecyclerView.ViewHolder {

    private ImageView ivUserAvatar;
    private TextView tvUsername;
    private TextView tvDatePost;
    private TextView tvTitle;
    private TextView tvDescription;
    private Button btnSeeMore;
    private ImageView ivImage;
    private Button btnHeart;
    private Button btnComment;
    private Button btnSave;
    private Button btnCartMenu;
    private RecipeDAO recipeDAO;
    private HeartDAO heartDAO;
    private CommentDAO commentDAO;
    private FavoriteDAO favoriteDAO;
    private Recipe recipe;

    public void setRecipe(Recipe r) {
        tvUsername.setText("Nguyễn Văn A"); // fix cứng
        tvDatePost.setText(formatDate(r.created_at));
        tvTitle.setText(r.getTitle());
        tvDescription.setText(r.getDescription());
        ivImage.setImageResource(r.getImage());

        btnHeart.setText(heartDAO.getHeartsByRecipeId(r.recipe_id).size() + "");
        //fix cứng user
        if (heartDAO.isHeart(2, r.recipe_id)) {
            //thay đổi icon nhưng chưa được
            btnHeart.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_heart_fill, 0, 0, 0);
        } else {
            btnHeart.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_heart_round, 0, 0, 0);
        }

        btnComment.setText(commentDAO.getCommentsByRecipeIdOrderByDate(r.recipe_id).size() + "");

        btnSave.setText(favoriteDAO.getFavoritesByRecipeId(r.recipe_id).size() + "");
        if (favoriteDAO.isFavorite(2, r.recipe_id)) {
            btnSave.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_save_fill, 0, 0, 0);
        } else {
            btnSave.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_save_round, 0, 0, 0);
        }

        recipe = r;
    }

    private String formatDate(long longDate) {
        Date date = new Date(longDate);

        // Định dạng ngày tháng năm
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault()); // Chỉnh lại định dạng theo ý muốn
        String formattedDate = dateFormat.format(date);
        return formattedDate;
    }

    private void bindingView() {
        ivUserAvatar = itemView.findViewById(R.id.ivSocialUserAvatar);
        tvUsername = itemView.findViewById(R.id.tvSocialUsername);
        tvDatePost = itemView.findViewById(R.id.tvSocialDatePost);
        tvTitle = itemView.findViewById(R.id.tvSocialTitle);
        tvDescription = itemView.findViewById(R.id.tvSocialDescription);

        btnSeeMore = itemView.findViewById(R.id.btnSocialSeeMore);
        ivImage = itemView.findViewById(R.id.ivSocialImage);

        btnHeart = itemView.findViewById(R.id.btnSocialHeart);
        btnComment = itemView.findViewById(R.id.btnSocialComment);
        btnSave = itemView.findViewById(R.id.btnSocialSave);

        btnCartMenu = itemView.findViewById(R.id.btnSocialCartMenu);

        recipeDAO = AppDatabase.getInstance(itemView.getContext()).recipeDAO();
        heartDAO = AppDatabase.getInstance(itemView.getContext()).heartDAO();
        commentDAO = AppDatabase.getInstance(itemView.getContext()).commentDAO();
        favoriteDAO = AppDatabase.getInstance(itemView.getContext()).favoriteDAO();
    }

    private void bindingAction() {
        btnSeeMore.setOnClickListener(this::onBtnSeeMoreClick);
        btnHeart.setOnClickListener(this::onBtnHeartClick);
        btnComment.setOnClickListener(this::onBtnCommentClick);
        btnSave.setOnClickListener(this::onBtnSaveClick);
        btnCartMenu.setOnClickListener(this::onBtnCartMenuClick);

    }

    private void onBtnSeeMoreClick(View view) {
        Intent i = new Intent(); //view.getContext(), SocialDetailActivity.class
        view.getContext().startActivity(i);
    }

    private void onBtnHeartClick(View view) {
        if (heartDAO.isHeart(2, recipe.recipe_id)) {
            heartDAO.deleteHeart(2, recipe.recipe_id);
            btnHeart.setText(heartDAO.getHeartsByRecipeId(recipe.recipe_id).size() + "");
            btnHeart.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_heart_round, 0, 0, 0);
        } else {
            heartDAO.insertHeart(new Heart(recipe.recipe_id, 2, System.currentTimeMillis()));
            int heart = heartDAO.getHeartsByRecipeId(recipe.recipe_id).size();
            btnHeart.setText(heart + "");
            btnHeart.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_heart_fill, 0, 0, 0);
        }
    }

    private void onBtnCommentClick(View view) {
        Intent i = new Intent(itemView.getContext(), AddComment.class);
        i.putExtra("recipe_id", recipe.getRecipe_id());
        itemView.getContext().startActivity(i);
    }

    private void onBtnSaveClick(View view) {
        if (favoriteDAO.isFavorite(2, recipe.recipe_id)) {
            favoriteDAO.deleteFavorite(2, recipe.recipe_id);
            btnSave.setText(favoriteDAO.getFavoritesByRecipeId(recipe.recipe_id).size() + "");
            btnSave.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_save_round, 0, 0, 0);

        } else {
            favoriteDAO.insertFavorite(new Favorite(2, recipe.recipe_id, System.currentTimeMillis()));
            btnSave.setText(favoriteDAO.getFavoritesByRecipeId(recipe.recipe_id).size() + "");
            btnSave.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_save_fill, 0, 0, 0);
        }
    }

    private void onBtnCartMenuClick(View view) {
        PopupMenu popupMenu = new PopupMenu(itemView.getContext(), view);
        popupMenu.getMenuInflater().inflate(R.menu.popup_social_post, popupMenu.getMenu());

        // Ép buộc hiển thị icon
        try {
            Field field = popupMenu.getClass().getDeclaredField("mPopup");
            field.setAccessible(true);
            Object menuHelper = field.get(popupMenu);
            Class<?> classPopupHelper = Class.forName(menuHelper.getClass().getName());
            Method setForceIcons = classPopupHelper.getMethod("setForceShowIcon", boolean.class);
            setForceIcons.invoke(menuHelper, true);

        } catch (Exception e) {
            e.printStackTrace();
        }

        popupMenu.setOnMenuItemClickListener(this::onMenuItemClick);
        popupMenu.show();
    }

    private boolean onMenuItemClick(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.edit_post) {
            //Code
            Toast.makeText(itemView.getContext(), "Chỉnh sửa bài viết " + recipe.getRecipe_id(), Toast.LENGTH_SHORT).show();
            return true;
        } else if (menuItem.getItemId() == R.id.delete_post) {
            //Code
            Toast.makeText(itemView.getContext(), "Xóa bài viết" + recipe.getRecipe_id(), Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    public CartViewHolder(@NonNull View itemView) {
        super(itemView);
        bindingView();
        bindingAction();
    }
}