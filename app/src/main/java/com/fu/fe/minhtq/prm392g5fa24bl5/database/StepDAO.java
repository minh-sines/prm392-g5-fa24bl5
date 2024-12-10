package com.fu.fe.minhtq.prm392g5fa24bl5.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.fu.fe.minhtq.prm392g5fa24bl5.model.Step;

import java.util.List;

@Dao
public interface StepDAO {
    @Insert
    void insertStep(Step step);

    @Query("SELECT * FROM Step WHERE recipe_id = :recipeId")
    List<Step> getStepsByRecipeId(int recipeId);

    @Query("SELECT * FROM Step WHERE step_id = :stepId")
    Step getStepById(int stepId);


}
