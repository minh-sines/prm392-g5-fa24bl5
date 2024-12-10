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
    public String image;
    public boolean is_published = false;

    public boolean delete = false;

    public int heartCount = 0;
    public int saveCount = 0;
    public int commentCount = 0;

    public Recipe(String title, String description, String time, int created_by, long created_at, long updated_at, String image) {
        this.title = title;
        this.description = description;
        this.time = time;
        this.created_by = created_by;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.image = image;
    }
    @Ignore
    public Recipe(String title, int created_by) {
        this.title = title;
        this.created_by = created_by;
    }

    @Ignore
    public Recipe(String title, String description, String time, int created_by, long created_at, long updated_at, String image, boolean is_published) {
        this.title = title;
        this.description = description;
        this.time = time;
        this.created_by = created_by;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.image = image;
        this.is_published = is_published;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPublished(boolean is_published) {
        this.is_published = is_published;
    }

    public boolean isPublished() {
        return is_published;
    }

    public int getHeartCount() {
        return heartCount;
    }

    public void setHeartCount(int heartCount) {
        this.heartCount = heartCount;
    }

    public int getSaveCount() {
        return saveCount;
    }

    public void setSaveCount(int saveCount) {
        this.saveCount = saveCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }
}