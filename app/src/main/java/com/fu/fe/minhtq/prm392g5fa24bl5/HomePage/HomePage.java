package com.fu.fe.minhtq.prm392g5fa24bl5.HomePage;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fu.fe.minhtq.prm392g5fa24bl5.R;
import com.fu.fe.minhtq.prm392g5fa24bl5.database.AppDatabase;
import com.fu.fe.minhtq.prm392g5fa24bl5.model.Recipe;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HomePage extends AppCompatActivity {

    private AppDatabase db;
    private RecyclerView rcvPopur;
    private RecyclerView rcvNew;
    private RecyclerView rcvShare;
    private List<Recipe> itemList;
    private ImageButton btnSearch;
    private EditText search_box;

    private void bindingView() {
        search_box = findViewById(R.id.search_box);
        search_box.setText("");
        btnSearch = findViewById(R.id.btnsearch);

        db = AppDatabase.getInstance(this);
        itemList = db.recipeDAO().getAllRecipes();

        rcvPopur = findViewById(R.id.rcvSearch);
        rcvNew = findViewById(R.id.recyclerFoodNew);
        rcvShare = findViewById(R.id.recyclerFoodShare);
    }

    private void initRecyclerView() {

        List<Recipe> sortedList = new ArrayList<>(itemList);
        Collections.sort(sortedList, new Comparator<Recipe>() {
            @Override
            public int compare(Recipe o1, Recipe o2) {
                return Long.compare(o2.getCreated_at(), o1.getCreated_at()); // Sắp xếp giảm dần
            }
        });

        List<Recipe> limitedList = itemList.size() > 6 ? itemList.subList(0, 6) : itemList;
        List<Recipe> limitedList3 = sortedList.size() > 3 ? sortedList.subList(0, 3) : sortedList;

        ItemAdapter adapter = new ItemAdapter(limitedList);
        ItemAdapter adapter1 = new ItemAdapter(limitedList3);
        PopurAdap popurAdap = new PopurAdap(limitedList);


        rcvPopur.setAdapter(popurAdap);
        rcvNew.setAdapter(adapter1);
        rcvShare.setAdapter(adapter1);


        rcvNew.setLayoutManager(new GridLayoutManager(this, 3));
        rcvShare.setLayoutManager(new GridLayoutManager(this, 3));
        rcvPopur.setLayoutManager(new GridLayoutManager(this, 3));
    }

    private void initData() {
        itemList = db.recipeDAO().getAllRecipes();
        initRecyclerView();
    }

    private void bindingAction() {
        btnSearch.setOnClickListener(this :: onClick);
    }

    private void onClick(View view) {
        // Lấy nội dung từ EditText
        String searchText = search_box.getText().toString().trim();

        if (!searchText.isEmpty()) {
            // Tìm kiếm các recipe theo title
            Intent intent = new Intent(HomePage.this, Search.class);
            intent.putExtra("search_query", searchText); // Truyền query tìm kiếm
            startActivity(intent);

        } else {
            Toast.makeText(this, "Please enter a search term", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        bindingView();
        initData();
        bindingAction();
    }

    private void loadImageToImageView(String fileName, Context context, ImageView imageView) {
        try {
            // Lấy thư mục private của ứng dụng
            File directory = context.getFilesDir();

            // Tạo đường dẫn tới file ảnh
            File file = new File(directory, fileName);

            // Kiểm tra xem file có tồn tại không
            if (file.exists()) {
                // Đọc ảnh từ file và chuyển thành Bitmap
                Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());

                // Hiển thị ảnh trên ImageView
                imageView.setImageBitmap(bitmap);
            } else {
                // Nếu file không tồn tại, đặt ảnh mặc định
                imageView.setImageResource(R.drawable.default_recipe); // Đổi R.drawable.default_image thành ảnh mặc định của bạn

            }
        } catch (Exception e) {
            e.printStackTrace();
            // Đặt ảnh mặc định nếu có lỗi
            imageView.setImageResource(R.drawable.default_recipe);
        }
    }
}