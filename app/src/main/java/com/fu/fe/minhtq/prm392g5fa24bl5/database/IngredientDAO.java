package com.fu.fe.minhtq.prm392g5fa24bl5.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.fu.fe.minhtq.prm392g5fa24bl5.model.*;

import java.util.List;

@Dao
public interface IngredientDAO {
    @Insert
    void insertIngredient(Ingredient ingredient);

    @Query("SELECT * FROM Ingredient")
    List<Ingredient> getAllIngredients();
    @Query("SELECT * FROM Ingredient WHERE recipe_id = :recipeId")
    List<Ingredient> getIngredientsByRecipeId(int recipeId);

    @Query("DELETE FROM Ingredient WHERE recipe_id = :recipeId")
    void deleteIngredientsByRecipeId(int recipeId);

    @Delete
    void deleteIngredient(Ingredient ingredient);
}
