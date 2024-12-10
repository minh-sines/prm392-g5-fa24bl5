package com.fu.fe.minhtq.prm392g5fa24bl5.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.fu.fe.minhtq.prm392g5fa24bl5.model.Recipe_image;

import java.util.List;

@Dao
public interface Recipe_imageDAO {
    @Insert
    void insertRecipe_image(Recipe_image recipe_image);
    @Query("SELECT * FROM Recipe_image")
    List<Recipe_image> getAllRecipe_images();

    @Query("SELECT * FROM Recipe_image WHERE recipe_id = :recipeId")
    List<Recipe_image> getRecipe_imagesByRecipeId(int recipeId);
    @Delete
    void deleteRecipe_image(Recipe_image recipe_image);
    @Query("DELETE FROM Recipe_image WHERE recipe_id = :recipeId")
    void deleteRecipe_imagesByRecipeId(int recipeId);

    @Query("SELECT image FROM Recipe_image WHERE recipe_id = :recipeId")
    List<String> getImagePathsByRecipeId(int recipeId);
}
