package com.fu.fe.minhtq.prm392g5fa24bl5.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "heart",
        foreignKeys = {
                @ForeignKey(
                        entity = Recipe.class,
                        parentColumns = "recipe_id",
                        childColumns = "recipe_id",
                        onDelete = ForeignKey.CASCADE,
                        onUpdate = ForeignKey.CASCADE
                ),
                @ForeignKey(
                        entity = Account.class,
                        parentColumns = "account_id",
                        childColumns = "user_id",
                        onDelete = ForeignKey.CASCADE,
                        onUpdate = ForeignKey.CASCADE
                )
        },
        indices = {
                @Index(value = {"recipe_id", "user_id"}, unique = true)
        }
)
public class Heart {
    @PrimaryKey(autoGenerate = true)
    public int heart_id;
    public int recipe_id;
    public int user_id;
    public long created_at;

    public Heart(int recipe_id, int user_id, long created_at) {
        this.recipe_id = recipe_id;
        this.user_id = user_id;
        this.created_at = created_at;
    }

    public int getHeart_id() {
        return heart_id;
    }

    public void setHeart_id(int heart_id) {
        this.heart_id = heart_id;
    }

    public int getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(int recipe_id) {
        this.recipe_id = recipe_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(long created_at) {
        this.created_at = created_at;
    }
}
