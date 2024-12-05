package com.fu.fe.minhtq.prm392g5fa24bl5.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
@Entity(tableName = "recipe_tag",
        foreignKeys = {
                @ForeignKey(
                        entity = Recipe.class,
                        parentColumns = "recipe_id",
                        childColumns = "recipe_id",
                        onDelete = ForeignKey.CASCADE, // Action on delete
                        onUpdate = ForeignKey.CASCADE // Action on update
                ),
                @ForeignKey(
                        entity = Tag.class,
                        parentColumns = "tag_id",
                        childColumns = "tag_id",
                        onDelete = ForeignKey.CASCADE, // Action on delete
                        onUpdate = ForeignKey.CASCADE // Action on update
                )
        }
)
public class Recipe_tag {
    @PrimaryKey(autoGenerate = true)
    public int recipe_tag_id;

    public int recipe_id;

    public int tag_id;

    public Recipe_tag(int recipe_id, int tag_id) {
        this.recipe_id = recipe_id;
        this.tag_id = tag_id;
    }

    public int getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(int recipe_id) {
        this.recipe_id = recipe_id;
    }

    public int getTag_id() {
        return tag_id;
    }

    public void setTag_id(int tag_id) {
        this.tag_id = tag_id;
    }
}
