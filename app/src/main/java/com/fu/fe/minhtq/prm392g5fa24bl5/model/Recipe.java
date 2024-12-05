package com.fu.fe.minhtq.prm392g5fa24bl5.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "recipe",
        foreignKeys = @ForeignKey(
                entity = Account.class, // Reference the parent entity
                parentColumns = "account_id", // Column in the parent table
                childColumns = "created_by", // Column in the child table
                onDelete = ForeignKey.CASCADE, // Action on delete
                onUpdate = ForeignKey.CASCADE // Action on update
        )
)
public class Recipe {
    @PrimaryKey(autoGenerate = true)
    public int recipe_id;
    public String title;
    public String description;
    public String time;
    //    @ForeignKey(entity = Account.class, parentColumns = "account_id", childColumns = "created_by")
    public int created_by;
    public long created_at;
    public long updated_at;

    public Recipe(String title, String description, String time, int created_by, long created_at, long updated_at) {
        this.title = title;
        this.description = description;
        this.time = time;
        this.created_by = created_by;
        this.created_at = created_at;
    }
    @Ignore
    public Recipe(String title, int created_by) {
        this.title = title;
        this.created_by = created_by;
    }

    public int getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(int recipe_id) {
        this.recipe_id = recipe_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getCreated_by() {
        return created_by;
    }

    public void setCreated_By(int created_by) {
        this.created_by = created_by;
    }

    public long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(long created_at) {
        this.created_at = created_at;
    }

    public long getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(long updated_at) {
        this.updated_at = updated_at;
    }
}
