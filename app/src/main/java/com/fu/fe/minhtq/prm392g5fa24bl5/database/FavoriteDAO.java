package com.fu.fe.minhtq.prm392g5fa24bl5.database;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.fu.fe.minhtq.prm392g5fa24bl5.model.*;

import java.util.List;

@Dao
public interface FavoriteDAO {
    @Insert
    void insertFavorite(Favorite favorite);
    @Query("SELECT * FROM Favorite")
    List<Favorite> getAllFavorites();
    @Query("SELECT exists(Select 1 from Favorite where user_id = :userId and recipe_id = :recipeId)")
    boolean isFavorite(int userId, int recipeId);

    @Query("DELETE FROM Favorite WHERE user_id = :userId AND recipe_id = :recipeId")
    void deleteFavorite(int userId, int recipeId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFavoritesList(List<Favorite> favorites);
}
