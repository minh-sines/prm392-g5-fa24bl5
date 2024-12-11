package com.fu.fe.minhtq.prm392g5fa24bl5.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.fu.fe.minhtq.prm392g5fa24bl5.model.*;

import java.util.List;

@Dao
public interface HeartDAO {
    @Insert
    void insertHeart(Heart heart);
    @Query("SELECT * FROM Heart")
    List<Heart> getAllHeart();
    @Query("SELECT exists(Select 1 from Heart where user_id = :userId and recipe_id = :recipeId)")
    boolean isHeart(int userId, int recipeId);

    @Query("DELETE FROM Heart WHERE user_id = :userId AND recipe_id = :recipeId")
    void deleteHeart(int userId, int recipeId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertHeartsList(List<Heart> hearts);
}
