package com.fu.fe.minhtq.prm392g5fa24bl5;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fu.fe.minhtq.prm392g5fa24bl5.model.Recipe;

import java.util.ArrayList;
import java.util.List;

public class TestFavoriteActivity extends AppCompatActivity {
    private RecyclerView test_fav_rcv;

    private List<Recipe> recipeList;

    private void fillData()
    {
        recipeList = new ArrayList<>();
        for(int i = 0; i < 10; i++)
        {
            recipeList.add(new Recipe("Title " + i, "Description " + i, "Time " + i, R.drawable.logo, 1, 1));
        }
    }
    private void bindingView()
    {
        test_fav_rcv = findViewById(R.id.test_fav_rcv);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_test_favorite);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        fillData();
        bindingView();
        initRecyclerView();
    }

    private void initRecyclerView() {
        RecipeFavAdapter adapter = new RecipeFavAdapter(this, recipeList);
        test_fav_rcv.setAdapter(adapter);
        test_fav_rcv.setLayoutManager(new LinearLayoutManager(this));
    }
}