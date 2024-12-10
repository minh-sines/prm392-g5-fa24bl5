package com.fu.fe.minhtq.prm392g5fa24bl5.database;

import static java.security.AccessController.getContext;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.fu.fe.minhtq.prm392g5fa24bl5.R;
import com.fu.fe.minhtq.prm392g5fa24bl5.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class DatabasePrepopulate extends AppCompatActivity {
    private TextView zdp_text1;
    private TextView zdp_text2;
    private TextView zdp_text3;
    private TextView zdp_text4;
    private TextView zdp_text5;
    private TextView zdp_text6;
    private Button button;

    private List<Account> accounts;
    private List<Recipe> recipes;
    private List<Favorite> favorites;

    private List<Heart> hearts;

    private List<Comment> comments;

    private List<Ingredient> ingredients;

    private List<Step> steps;

    private void bindingView(){
        zdp_text1 = findViewById(R.id.zdp_text1);
        zdp_text2 = findViewById(R.id.zdp_text2);
        zdp_text3 = findViewById(R.id.zdp_text3);
        zdp_text4 = findViewById(R.id.zdp_text4);
        zdp_text5 = findViewById(R.id.zdp_text5);
        zdp_text6 = findViewById(R.id.zdp_text6);
        button = findViewById(R.id.button);
    }

    private void bindingAction(){
        button.setOnClickListener(this::loadDatabase);
    }

    private void loadDatabase(View view) {
        AppDatabase instance = AppDatabase.getInstance(this);
        //would get duplicated if insert again


//        instance.accountDAO().insertAccount(new Account("Logan", "logan@gmail.com", "123456",System.currentTimeMillis()));
//        instance.accountDAO().insertAccount(new Account("Jim", "jim@gmail.com", "123456", System.currentTimeMillis()));
//        instance.accountDAO().insertAccount(new Account("Will", "will@gmail.com", "123456", System.currentTimeMillis()));
//        instance.accountDAO().insertAccount(new Account("Smith", "smith@gmail.com", "123456", System.currentTimeMillis()));
//        instance.accountDAO().insertAccount(new Account("Anya", "anya@gmail.com", "123456", System.currentTimeMillis()));
        accounts.add(new Account("Logan", "logan@gmail.com", "123456",System.currentTimeMillis()));
        accounts.add(new Account("Jim", "jim@gmail.com", "123456", System.currentTimeMillis()));
        accounts.add(new Account("Will", "will@gmail.com", "123456", System.currentTimeMillis()));
        accounts.add(new Account("Smith", "smith@gmail.com", "123456", System.currentTimeMillis()));
        accounts.add(new Account("Anya", "anya@gmail.com", "123456", System.currentTimeMillis()));

        instance.accountDAO().insertAccountsList(accounts);
//        List<Account> finalAccounts = accounts;
//        Executors.newSingleThreadExecutor().execute(() -> {
//            instance.accountDAO().insertAccountsList(finalAccounts);
//            new Handler(Looper.getMainLooper()).post(() -> {
//                // Update UI or refresh data back to mainthread
//                Toast.makeText(this, "Account inserted successfully!", Toast.LENGTH_SHORT).show();
//                getListSize();
//            });
//        });

//        instance.recipeDAO().insertRecipe(new Recipe("Bo Beefsteak ", "Sweet and creamy coffee drink.", "60 min", 1, System.currentTimeMillis(), System.currentTimeMillis(), R.drawable.cutecat));
//        instance.recipeDAO().insertRecipe(new Recipe("recipe1_2", "Mon an tu tao 12", "70 min", 1, 0, 0, R.drawable.cutedog));
//        instance.recipeDAO().insertRecipe(new Recipe("recipe1_3","Mon an tu tao 13", "50 min",1, 0,0, R.drawable.cutepenguin));
//        instance.recipeDAO().insertRecipe(new Recipe("recipe2_1", "Mon an tu tao 23", "80 min", 2, 0, 0, R.drawable.cutecat));
//        instance.recipeDAO().insertRecipe(new Recipe("recipe2_2", "Mon an tu tao 24", "90 min", 2, 0, 0, R.drawable.cutedog));


        recipes.add(new Recipe("Classic Pho", "Traditional Vietnamese noodle soup.", "80 min", 1, System.currentTimeMillis(), System.currentTimeMillis(), "recipe_1.png", true));

        recipes.add(new Recipe("Spring Rolls", "Fresh and healthy Vietnamese rolls.", "20 min", 1, System.currentTimeMillis(), System.currentTimeMillis(), "recipe_1.png", true));
        recipes.add(new Recipe("Banh Mi Sandwich", "Crispy baguette with savory fillings.", "15 min", 1, System.currentTimeMillis(), System.currentTimeMillis(), "recipe_1.png", true));
        recipes.add(new Recipe("Green Papaya Salad", "Refreshing salad with a tangy dressing.", "30 min", 1, System.currentTimeMillis(), System.currentTimeMillis(), "recipe_1.png", true));
        recipes.add(new Recipe("Vietnamese Coffee", "Rich and aromatic coffee.", "10 min", 1, System.currentTimeMillis(), System.currentTimeMillis(), "recipe_1.png", true));

        recipes.add(new Recipe("Caramelized Pork Belly", "Savory and sweet pork dish perfect with steamed rice.", "90 min", 2, System.currentTimeMillis(), System.currentTimeMillis(), "recipe_1.png"));
        recipes.add(new Recipe("Chicken Pho", "A lighter take on the classic noodle soup with chicken.", "70 min", 2, System.currentTimeMillis(), System.currentTimeMillis(), "recipe_1.png"));
        recipes.add(new Recipe("Vietnamese Pancakes", "Crispy savory crepes filled with shrimp and pork.", "45 min", 2, System.currentTimeMillis(), System.currentTimeMillis(), "recipe_1.png"));
        recipes.add(new Recipe("Lotus Stem Salad", "A refreshing salad with lotus stems, shrimp, and pork.", "35 min", 3, System.currentTimeMillis(), System.currentTimeMillis(), "recipe_1.png"));
        recipes.add(new Recipe("Mango Sticky Rice", "A tropical dessert with fresh mango and coconut rice.", "40 min", 3, System.currentTimeMillis(), System.currentTimeMillis(), "recipe_1.png"));

        instance.recipeDAO().insertRecipesList(recipes);
//        List<Recipe> finalRecipes = recipes;
//        Executors.newSingleThreadExecutor().execute(() -> {
//            instance.recipeDAO().insertRecipesList(finalRecipes);
//            new Handler(Looper.getMainLooper()).post(() -> {
//                // Update UI or refresh data back to mainthread
//                Toast.makeText(this, "Recipes inserted successfully!", Toast.LENGTH_SHORT).show();
//                getListSize();
//            });
//        });


        favorites.add(new Favorite(2,1, System.currentTimeMillis()));
        favorites.add(new Favorite(2,2, System.currentTimeMillis()));
        favorites.add(new Favorite(2,3, System.currentTimeMillis()));
        favorites.add(new Favorite(3,4, System.currentTimeMillis()));
        favorites.add(new Favorite(4,5, System.currentTimeMillis()));

        instance.favoriteDAO().insertFavoritesList(favorites);

//        List<Favorite> finalFavorites = favorites;
//        Executors.newSingleThreadExecutor().execute(() -> {
//                    instance.favoriteDAO().insertFavoritesList(finalFavorites);
//                    new Handler(Looper.getMainLooper()).post(() -> {
//                        // Update UI or refresh data back to mainthread
//                        Toast.makeText(this, "Favorite inserted successfully!", Toast.LENGTH_SHORT).show();
//                        getListSize();
//                    });
//                });

        hearts.add(new Heart(1,2, System.currentTimeMillis()));
        hearts.add(new Heart(1,3, System.currentTimeMillis()));
        hearts.add(new Heart(1,4, System.currentTimeMillis()));
        hearts.add(new Heart(1,5, System.currentTimeMillis()));
        hearts.add(new Heart(2,2, System.currentTimeMillis()));

        instance.heartDAO().insertHeartsList(hearts);


//        List<Heart> finalHearts = hearts;
//        Executors.newSingleThreadExecutor().execute(() -> {
//                    instance.heartDAO().insertHeartsList(finalHearts);
//                    new Handler(Looper.getMainLooper()).post(() -> {
//                        // Update UI or refresh data back to mainthread
//                        Toast.makeText(this, "Heart inserted successfully!", Toast.LENGTH_SHORT).show();
//                        getListSize();
//                    });
//                });






//        instance.favoriteDAO().insertFavorite(new Favorite(2,1, System.currentTimeMillis()));
//        instance.favoriteDAO().insertFavorite(new Favorite(2,2, System.currentTimeMillis()));
//        instance.favoriteDAO().insertFavorite(new Favorite(2,3, System.currentTimeMillis()));
//        instance.favoriteDAO().insertFavorite(new Favorite(3,4, System.currentTimeMillis()));
//        instance.favoriteDAO().insertFavorite(new Favorite(4,5, System.currentTimeMillis()));


//        instance.recipeDAO().insertRecipeImg(new Recipe_image(1,R.drawable.cutecat));
//        instance.recipeDAO().insertRecipeImg(new Recipe_image(2,R.drawable.cutedog));
//        instance.recipeDAO().insertRecipeImg(new Recipe_image(3,R.drawable.cutepenguin));

        //


        Toast.makeText(this, "Inserted successfully!", Toast.LENGTH_SHORT).show();
        getListSize();
    }

    private void getListSize() {
        int size = 0;
        AppDatabase instance = AppDatabase.getInstance(this);
        accounts = instance.accountDAO().getAllAccounts();
        recipes = instance.recipeDAO().getAllRecipes();
        favorites = instance.favoriteDAO().getAllFavorites();
        hearts = instance.heartDAO().getAllHeart();
//        comments = instance.commentDAO().getAllComments();
//        ingredients = instance.ingredientDAO().getAllIngredients();
//        steps = instance.stepDAO().getAllSteps();
//        List<Recipe_image> recipe_images = instance.recipeDAO().getAllRecipeImg();

        zdp_text1.setText("Database loaded");

        if (!accounts.isEmpty()) {
            size=accounts.size();
        } else size = 0;
        zdp_text2.setText("Accounts: " + size);

        if (!recipes.isEmpty()) {
            size=recipes.size();
        }
        else size = 0;
        zdp_text3.setText("Recipes: " + size);

        if (!favorites.isEmpty()) {
            size=favorites.size();
        } else size = 0;
        zdp_text4.setText("Favorites: " + size);

        if (!hearts.isEmpty()) {
            size=hearts.size();
        } else size = 0;
        zdp_text5.setText("Hearts: " + size);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.z_database_prepopulate);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        bindingView();
        getListSize();
        bindingAction();

    }
}