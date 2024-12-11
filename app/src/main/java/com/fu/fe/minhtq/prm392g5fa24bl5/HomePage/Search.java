package com.fu.fe.minhtq.prm392g5fa24bl5.HomePage;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fu.fe.minhtq.prm392g5fa24bl5.R;
import com.fu.fe.minhtq.prm392g5fa24bl5.database.AppDatabase;
import com.fu.fe.minhtq.prm392g5fa24bl5.database.RecipeDAO;
import com.fu.fe.minhtq.prm392g5fa24bl5.model.Recipe;

import java.util.ArrayList;
import java.util.List;

public class Search extends AppCompatActivity {
    private RecyclerView rcvSearch;
    private SearchAdapter searchAdapter;
    private AppDatabase db;

    private TextView textViewSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search); // Sử dụng layout bạn đã cung cấp

        // Khởi tạo View
        rcvSearch = findViewById(R.id.rcvSearch);
        textViewSearch = findViewById(R.id.txttitleSearch);
        db = AppDatabase.getInstance(this);

        // Thiết lập RecyclerView
        rcvSearch.setLayoutManager(new LinearLayoutManager(this));
        rcvSearch.setAdapter(searchAdapter);

        // Lấy query tìm kiếm từ Intent nếu có
        String query = getIntent().getStringExtra("search_query");

        if (query != null && !query.isEmpty()) {
            // Đặt giá trị của query vào TextView
            textViewSearch.setText("Các công thức liên quan đến: " + query);
            searchRecipes(query);
        }
    }

    private void searchRecipes(String query) {
        if (query != null && !query.isEmpty()) {
            List<Recipe> searchResults = db.recipeDAO().searchRecipesByTitle("%" + query + "%");
            searchAdapter = new SearchAdapter(searchResults);
            rcvSearch.setAdapter(searchAdapter);
        }
    }
}
