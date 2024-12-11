package com.fu.fe.minhtq.prm392g5fa24bl5.manage.recipe;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fu.fe.minhtq.prm392g5fa24bl5.R;
import com.fu.fe.minhtq.prm392g5fa24bl5.database.AppDatabase;
import com.fu.fe.minhtq.prm392g5fa24bl5.model.Favorite;
import com.fu.fe.minhtq.prm392g5fa24bl5.model.Ingredient;
import com.fu.fe.minhtq.prm392g5fa24bl5.model.Recipe;
import com.fu.fe.minhtq.prm392g5fa24bl5.model.Recipe_image;
import com.fu.fe.minhtq.prm392g5fa24bl5.model.Step;

import java.io.File;
import java.util.List;

public class DetailRecipe extends AppCompatActivity {

    private AppDatabase db;
    private List<String> imgDescList;
    private int recipeId;
    private Recipe recipe;
    private ImageView mainImage;
    private Button deleteButton;
    private Button editButton;
    private ImageView likeIcon;
    private ImageView commentIcon;

    private ImageView bookmarkIcon;

    private TextView recipeTitle;
    private TextView recipeDescription;
    private TextView timeText;
    private TextView ingredientsTitle;
    private TextView ingredientsList;
    private TextView stepsTitle;
    private TextView step1;
    private TextView step2;

    private TextView likeCount;
    private TextView commentCount;
    private LinearLayout stepLayout;
    private RecyclerView recyclerViewImageGallery;

    private void bindingView(){
        db = AppDatabase.getInstance(this);
        recipe = db.recipeDAO().getRecipeById(recipeId);
        if(recipe == null){
            Toast.makeText(this, "Recipe not found", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        mainImage = findViewById(R.id.main_img);

        recipeTitle = findViewById(R.id.recipe_title);
        recipeDescription = findViewById(R.id.recipe_description);
        bookmarkIcon = findViewById(R.id.bookmarkIcon);

        timeText = findViewById(R.id.time_text);
        ingredientsTitle = findViewById(R.id.ingredients_title);
        ingredientsList = findViewById(R.id.ingredients_list);
        stepsTitle = findViewById(R.id.steps_title);
        stepLayout = findViewById(R.id.steps_container);

        recyclerViewImageGallery = findViewById(R.id.recyclerViewImageGallery);
        imgDescList = db.recipe_imageDAO().getImagePathsByRecipeId(recipeId);


    }

    private void initData(){
        initMainImage();
        initRecipeInfo();
        initIngredient();
        initStep();
        initRecyclerView();
        initBookmark();
    }

    private void initBookmark() {
        int favouriteCount = db.favoriteDAO().isRecipeBookmarked(1, recipeId);

        if (favouriteCount > 0) {
            bookmarkIcon.setImageResource(R.drawable.bookmark_1);
        } else {
            bookmarkIcon.setImageResource(R.drawable.bookmark_unadded);
        }
    }

    private void initRecyclerView() {

        ImgDescAdapter adapter = new ImgDescAdapter(imgDescList);
        recyclerViewImageGallery.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewImageGallery.setLayoutManager(layoutManager);
    }

    private void initStep() {
        List<Step> steps = db.stepDAO().getStepsByRecipeId(recipeId);

        if (steps != null && !steps.isEmpty()) {
            // Clear any previous steps in the layout
            stepLayout.removeAllViews();

            // Duyệt qua danh sách các bước và cập nhật TextView cho từng bước
            for (int i = 0; i < steps.size(); i++) {
                Step step = steps.get(i);

                // Tạo TextView cho tiêu đề Bước
                TextView stepTitle = new TextView(this);
                stepTitle.setText("Bước " + (i + 1));  // Thêm số thứ tự của bước
                stepTitle.setTextSize(18);  // Cỡ chữ lớn hơn cho tiêu đề
                stepTitle.setTextColor(Color.parseColor("#669900"));  // Màu sắc tiêu đề
                stepTitle.setPadding(16, 16, 16, 8);  // Thêm khoảng cách

                // Tạo TextView cho mô tả chi tiết của từng bước
                TextView stepText = new TextView(this);
                stepText.setText(step.getDetail());
                stepText.setTextSize(16);
                stepText.setTextColor(Color.parseColor("#333333"));  // Màu sắc của mô tả
                stepText.setPadding(16, 8, 16, 8);  // Thêm khoảng cách giữa các bước

                // Thêm tiêu đề Bước và mô tả vào layout
                stepLayout.addView(stepTitle);
                stepLayout.addView(stepText);

                // Nếu có ảnh, hiển thị ảnh tương ứng
                if (step.getImage() != null && !step.getImage().isEmpty()) {
                    ImageView stepImage = new ImageView(this);
                    loadImageToImageView(step.getImage(), this, stepImage); // Hàm load ảnh từ file

                    // Đảm bảo ảnh chiếm toàn bộ chiều ngang của layout và có chiều cao cố định
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            400 // Đặt chiều cao cố định cho ảnh (ví dụ: 400dp)
                    );
                    stepImage.setLayoutParams(layoutParams);
                    stepImage.setScaleType(ImageView.ScaleType.CENTER_CROP); // Đảm bảo ảnh được crop cho phù hợp

                    // Thêm ảnh vào layout của bước
                    stepLayout.addView(stepImage);
                }
            }
        }
    }



    private void initIngredient() {
        // Lấy danh sách các nguyên liệu từ cơ sở dữ liệu
        List<Ingredient> ingredients = db.ingredientDAO().getIngredientsByRecipeId(recipeId);

        // Tạo một StringBuilder để nối chuỗi các nguyên liệu
        StringBuilder ingredientString = new StringBuilder();

        // Duyệt qua danh sách nguyên liệu và nối lại
        for (int i = 0; i < ingredients.size(); i++) {
            ingredientString.append(ingredients.get(i).getName());

            // Thêm dấu phẩy nếu không phải là nguyên liệu cuối cùng
            if (i < ingredients.size() - 1) {
                ingredientString.append(", ");
            }
        }

        // Lưu chuỗi nguyên liệu vào TextView
        ingredientsList.setText(ingredientString.toString());
    }

    private void initRecipeInfo() {

        recipeTitle.setText(recipe.getTitle());
        recipeDescription.setText(recipe.getDescription());
        timeText.setText(recipe.getTime() + " phút");
    }

    private void initMainImage() {
        loadImageToImageView(recipe.getMainImage(), this, mainImage);
    }

    private void bindingAction(){
        bookmarkIcon.setOnClickListener(this::onBookmarkClick);
    }

    private void onBookmarkClick(View view) {
        int favouriteCount = db.favoriteDAO().isRecipeBookmarked(1, recipeId);
        if (favouriteCount > 0) {
            db.favoriteDAO().deleteFavorite(1, recipeId);
            bookmarkIcon.setImageResource(R.drawable.bookmark_unadded);
            Toast.makeText(this, "Đã xóa khỏi danh sách yêu thích", Toast.LENGTH_SHORT).show();
        }else{
            Favorite favorite = new Favorite(1, recipeId, System.currentTimeMillis());
            db.favoriteDAO().insertFavorite(favorite);
            bookmarkIcon.setImageResource(R.drawable.bookmark_1);

            Toast.makeText(this, "Đã thêm vào danh sách yêu thích", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail_recipe);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        recipeId = getIntent().getIntExtra("recipeId", 7);

        bindingView();
        bindingAction();
        initData();


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