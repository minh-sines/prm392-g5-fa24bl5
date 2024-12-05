package com.fu.fe.minhtq.prm392g5fa24bl5.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "ingredient",
        foreignKeys = @ForeignKey(
                entity = Recipe.class,
                parentColumns = "recipe_id",
                childColumns = "recipe_id",
                onDelete = ForeignKey.CASCADE, // Action on delete
                onUpdate = ForeignKey.CASCADE // Action on update
        )
)
public class Ingredient {
    @PrimaryKey(autoGenerate = true)
    public int ingredient_id;
    public int recipe_id;
    public String name;
    public String quantity;

    public Ingredient(int recipe_id, String name, String quantity) {
        this.recipe_id = recipe_id;
        this.name = name;
        this.quantity = quantity;
    }

    public int getIngredient_id() {
        return ingredient_id;
    }

    public void setIngredient_id(int ingredient_id) {
        this.ingredient_id = ingredient_id;
    }

    public int getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(int recipe_id) {
        this.recipe_id = recipe_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
