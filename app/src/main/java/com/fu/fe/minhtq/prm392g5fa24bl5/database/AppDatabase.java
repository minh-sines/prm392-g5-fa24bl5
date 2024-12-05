package com.fu.fe.minhtq.prm392g5fa24bl5.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.fu.fe.minhtq.prm392g5fa24bl5.model.*;


@Database(entities = {
        Account.class,
        Recipe.class,
        Ingredient.class,
        Step.class,
        Recipe_image.class,
        Recipe_tag.class,
        Tag.class,
        Favorite.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "app_database";
    private static AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context)
    {
        if(instance == null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
//                    .addCallback(roomCallBack)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            //can not call DAO here
//            instance.accountDAO().insertAccount(new Account("test1", "test1", "test1",0));
//            instance.accountDAO().insertAccount(new Account("test2", "test2", "test2", 0));
//            instance.accountDAO().insertAccount(new Account("test3", "test3", "test3", 0));
//            instance.recipeDAO().insertRecipe(new Recipe("recipe1_1", "", "", 1, 0, 0));
//            instance.recipeDAO().insertRecipe(new Recipe("recipe1_2", "", "", 1, 0, 0));
//            instance.recipeDAO().insertRecipe(new Recipe("recipe1_3","", "",1, 0,0));
//            instance.recipeDAO().insertRecipe(new Recipe("recipe2_1", "", "", 2, 0, 0));
//            instance.recipeDAO().insertRecipe(new Recipe("recipe2_2", "", "", 2, 0, 0));
//            instance.recipeDAO().insertRecipe(new Recipe("recipe2_3", "", "", 2, 0, 0));

        }
    };
    public abstract AccountDAO accountDAO();
    public abstract RecipeDAO recipeDAO();
    public abstract IngredientDAO ingredientDAO();
    public abstract FavoriteDAO favoriteDAO();

}
