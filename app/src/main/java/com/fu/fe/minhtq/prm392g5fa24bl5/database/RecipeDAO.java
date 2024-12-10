package com.fu.fe.minhtq.prm392g5fa24bl5.database;

import androidx.room.Dao;
import androidx.room.Insert;
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

    @Query("SELECT * FROM Recipe WHERE [delete] = false")
    List<Recipe> getAllRecipes();
    @Query("SELECT * FROM Recipe WHERE recipe_id = :id")
    Recipe getRecipeById(int id);

    @Query("SELECT * FROM Recipe WHERE created_by = :id")
    List<Recipe> getAllRecipesByAccountId(int id);
}