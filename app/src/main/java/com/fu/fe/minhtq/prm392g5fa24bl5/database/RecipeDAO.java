package com.fu.fe.minhtq.prm392g5fa24bl5.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.fu.fe.minhtq.prm392g5fa24bl5.model.*;

import java.util.List;

@Dao
public interface RecipeDAO {

    @Insert
    void insertRecipe(Recipe recipe);

    @Query("SELECT * FROM Recipe")
    List<Recipe> getAllRecipes();

    @Query("SELECT * FROM Recipe WHERE created_by = :id")
    List<Recipe> getAllRecipesByAccountId(int id);

    @Query("SELECT Recipe.* FROM Recipe INNER JOIN Favorite ON Recipe.recipe_id = Favorite.recipe_id WHERE Favorite.user_id = :id")
    List<Recipe> getFavoriteRecipesByAccountId(int id);

    @Insert
    void insertRecipeImg(Recipe_image recipe_image);

    @Query("SELECT * FROM recipe_image")
    List<Recipe_image> getAllRecipeImg();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRecipesList(List<Recipe> recipes);



}