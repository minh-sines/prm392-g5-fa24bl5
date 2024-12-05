package com.fu.fe.minhtq.prm392g5fa24bl5.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
@Entity( tableName = "recipe_image",
        foreignKeys = @ForeignKey(
                entity = Recipe.class,
                parentColumns = "recipe_id",
                childColumns = "recipe_id",
                onDelete = ForeignKey.CASCADE, // Action on delete
                onUpdate = ForeignKey.CASCADE // Action on update
        )
)
public class Recipe_image {
@PrimaryKey(autoGenerate = true)
    public int recipe_image_id;
    public int recipe_id;
    public int image;

    public Recipe_image(int recipe_id, int image) {
        this.recipe_id = recipe_id;
        this.image = image;
    }

        public int getRecipe_image_id() {
                return recipe_image_id;
        }

        public void setRecipe_image_id(int recipe_image_id) {
                this.recipe_image_id = recipe_image_id;
        }

        public int getRecipe_id() {
                return recipe_id;
        }

        public void setRecipe_id(int recipe_id) {
                this.recipe_id = recipe_id;
        }

        public int getImage() {
                return image;
        }

        public void setImage(int image) {
                this.image = image;
        }
}
