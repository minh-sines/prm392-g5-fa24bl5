package com.fu.fe.minhtq.prm392g5fa24bl5.database;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.fu.fe.minhtq.prm392g5fa24bl5.model.*;

import java.util.List;

@Dao
public interface FavoriteDAO {
    @Insert
    void insertFavorite(Favorite favorite);
    @Query("SELECT * FROM Favorite")
    List<Favorite> getAllFavorites();
}
