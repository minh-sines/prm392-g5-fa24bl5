package com.fu.fe.minhtq.prm392g5fa24bl5.database;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.fu.fe.minhtq.prm392g5fa24bl5.R;
import com.fu.fe.minhtq.prm392g5fa24bl5.model.*;

import java.util.List;

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
        instance.accountDAO().insertAccount(new Account("Logan", "logan@gmail.com", "123",0));
        instance.accountDAO().insertAccount(new Account("Jim", "jim@gmail.com", "123", 0));
        instance.accountDAO().insertAccount(new Account("Will", "will@gmail.com", "123", 0));
//        instance.recipeDAO().insertRecipe(new Recipe("recipe1_1", "", "", 1, 0, 0));
//        instance.recipeDAO().insertRecipe(new Recipe("recipe1_2", "", "", 1, 0, 0));
//        instance.recipeDAO().insertRecipe(new Recipe("recipe1_3","", "",1, 0,0));
//        instance.recipeDAO().insertRecipe(new Recipe("recipe2_1", "", "", 2, 0, 0));
//        instance.recipeDAO().insertRecipe(new Recipe("recipe2_2", "", "", 2, 0, 0));
//        instance.recipeDAO().insertRecipe(new Recipe("recipe2_3", "", "", 2, 0, 0));

        accounts = instance.accountDAO().getAllAccounts();
        recipes = instance.recipeDAO().getAllRecipes();
        favorites = instance.favoriteDAO().getAllFavorites();

        zdp_text1.setText("Database loaded");
        zdp_text2.setText("Accounts: " + accounts.size());
        zdp_text3.setText("Recipes: " + recipes.size());
        zdp_text4.setText("Favorites: " + favorites.size());


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
        bindingAction();
    }
}