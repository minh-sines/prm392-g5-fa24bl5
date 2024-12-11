package com.fu.fe.minhtq.prm392g5fa24bl5.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.fu.fe.minhtq.prm392g5fa24bl5.model.*;

import java.util.List;

@Dao
public interface CommentDAO {
    @Insert
    void insertComment(Comment comment);

    @Insert
    void insertCommentsList(List<Comment> comments);

    @Query("SELECT * FROM Comment where recipe_id = :recipeId")
    List<Comment> getAllCommentsByRecipeId(int recipeId);
    @Query("SELECT * FROM Comment where recipe_id = :recipeId ORDER BY created_at DESC")
    List<Comment> getCommentsByRecipeIdOrderByDate(int recipeId);

    @Query("DELETE FROM Comment WHERE comment_id = :commentId")
    void deleteComment(int commentId);

    @Query("DELETE FROM Comment WHERE user_id = :userId AND recipe_id = :recipeId")
    void deleteCommentsByUserIdRecipeId(int userId, int recipeId);
}
