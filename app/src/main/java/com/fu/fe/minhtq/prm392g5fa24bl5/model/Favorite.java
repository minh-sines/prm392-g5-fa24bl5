package com.fu.fe.minhtq.prm392g5fa24bl5.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorite",
        foreignKeys = {
                @ForeignKey(
                entity = Account.class,
                parentColumns = "account_id",
                childColumns = "user_id",
                onDelete = ForeignKey.CASCADE, // Action on delete
                onUpdate = ForeignKey.CASCADE // Action on update
                ),
                @ForeignKey(
                entity = Recipe.class,
                parentColumns = "recipe_id",
                childColumns = "recipe_id",
                onDelete = ForeignKey.CASCADE, // Action on delete
                onUpdate = ForeignKey.CASCADE // Action on update
                )
        },
        indices = {
                @Index(value = {"user_id", "recipe_id"}, unique = true)
        }
)

public class Favorite {
    @PrimaryKey(autoGenerate = true)
    public int favorite_id;

    public int user_id;

    public int recipe_id;
    public long created_at;

    public Favorite(int user_id, int recipe_id, long created_at) {
        this.user_id = user_id;
        this.recipe_id = recipe_id;
        this.created_at = created_at;
    }

    public int getFavorite_id() {
        return favorite_id;
    }

    public void setFavorite_id(int favorite_id) {
        this.favorite_id = favorite_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(int recipe_id) {
        this.recipe_id = recipe_id;
    }

    public long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(long created_at) {
        this.created_at = created_at;
    }
}
