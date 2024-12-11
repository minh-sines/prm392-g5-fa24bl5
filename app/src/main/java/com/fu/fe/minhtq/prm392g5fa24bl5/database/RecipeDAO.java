package com.fu.fe.minhtq.prm392g5fa24bl5.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.fu.fe.minhtq.prm392g5fa24bl5.model.*;

import java.util.List;

@Dao
public interface RecipeDAO {

    @Insert
    long insertRecipe(Recipe recipe);

    @Update
    void updateRecipe(Recipe recipe);

    @Query("SELECT * FROM Recipe WHERE [delete] = 0")
    List<Recipe> getAllRecipes();

    @Query("SELECT * FROM Recipe WHERE recipe_id = :id and  [delete] = 0")
    Recipe getRecipeById(int id);

    @Query("SELECT * FROM Recipe WHERE title LIKE :query and  [delete] = 0")
    List<Recipe> searchRecipesByTitle(String query);
    
    @Query("SELECT * FROM Recipe WHERE created_by = :id and  [delete] = 0")
    List<Recipe> getAllRecipesByAccountId(int id);

    @Query("SELECT Recipe.* FROM Recipe INNER JOIN Favorite ON Recipe.recipe_id = Favorite.recipe_id WHERE Favorite.user_id = :id and  Recipe.[delete] = 0")
    List<Recipe> getFavoriteRecipesByAccountId(int id);

    @Insert
    void insertRecipeImg(Recipe_image recipe_image);

    @Query("SELECT * FROM recipe_image")
    List<Recipe_image> getAllRecipeImg();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRecipesList(List<Recipe> recipes);

    @Query("SELECT * FROM Recipe WHERE [delete] = 0 ORDER BY created_at ASC")
    List<Recipe> getRecipeByDate();

}