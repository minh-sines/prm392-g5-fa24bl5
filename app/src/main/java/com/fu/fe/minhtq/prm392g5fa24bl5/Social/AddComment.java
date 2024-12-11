package com.fu.fe.minhtq.prm392g5fa24bl5.Social;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.fu.fe.minhtq.prm392g5fa24bl5.database.AccountDAO;
import com.fu.fe.minhtq.prm392g5fa24bl5.database.AppDatabase;
import com.fu.fe.minhtq.prm392g5fa24bl5.database.CommentDAO;
import com.fu.fe.minhtq.prm392g5fa24bl5.database.RecipeDAO;
import com.fu.fe.minhtq.prm392g5fa24bl5.model.Account;
import com.fu.fe.minhtq.prm392g5fa24bl5.model.Comment;
import com.fu.fe.minhtq.prm392g5fa24bl5.model.Recipe;

import java.io.File;
import java.util.List;

public class AddComment extends AppCompatActivity {

    private ImageView ivCommentImage;
    private TextView tvCommentCount;
    private ImageView ivCommentUser;
    private EditText edtAddComment;
    private Button btnSendComment;
    private Button btnCommentBack;
    private RecyclerView rcvComment;
    private int recipeId;
    private List<Comment> data;
    private RecipeDAO recipeDAO;
    private CommentDAO commentDAO;
    private AccountDAO accountDAO;

    private void bindingView() {
        ivCommentImage = findViewById(R.id.ivCommentImage);
        tvCommentCount = findViewById(R.id.tvCommentCount);
        ivCommentUser = findViewById(R.id.ivCommentUser);
        edtAddComment = findViewById(R.id.edtAddComment);
        btnSendComment = findViewById(R.id.btnSendComment);
        btnCommentBack = findViewById(R.id.btnCommentBack);
        rcvComment = findViewById(R.id.rcvComment);

        commentDAO = AppDatabase.getInstance(this).commentDAO();
        recipeDAO = AppDatabase.getInstance(this).recipeDAO();
        accountDAO = AppDatabase.getInstance(this).accountDAO();

        recipeId = getIntent().getIntExtra("recipe_id", 0);
        data = commentDAO.getCommentsByRecipeIdOrderByDate(recipeId);
        Recipe recipe = recipeDAO.getRecipeById(recipeId);
        Account account = accountDAO.getAccountById(recipe.getCreated_by());

        loadImageToImageView(recipe.getMainImage(), this, ivCommentImage);
//        ivCommentImage.setImageResource(R.drawable.cutedog);
        ivCommentUser.setImageResource(R.drawable.img);
//        ivCommentUser.setImageResource(account.getAvatar());
        tvCommentCount.setText(data.size() + " bình luận");
    }

    private void bindingAction() {
        btnCommentBack.setOnClickListener(v -> finish());
        btnSendComment.setOnClickListener(this::onBtnSendCommentClick);
    }

    private void onBtnSendCommentClick(View view) {
        String c = edtAddComment.getText().toString().trim();

        if (c.isEmpty()) {
            Toast.makeText(this, "Nội dung không được để trống", Toast.LENGTH_SHORT).show();
            return;
        }

        Comment comment = new Comment(recipeId, 1, c, System.currentTimeMillis());
        commentDAO.insertComment(comment);

        data.clear();
        data = commentDAO.getCommentsByRecipeIdOrderByDate(recipeId);
        tvCommentCount.setText(data.size() + " bình luận");

        initRecyclerView();

        edtAddComment.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_comment);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        bindingView();
        bindingAction();
        initRecyclerView();
    }


    private void initRecyclerView() {
        CommentAdapter adapter = new CommentAdapter(data);
        rcvComment.setAdapter(adapter);
        rcvComment.setLayoutManager(new LinearLayoutManager(this));
    }

    public void updateComments() {
        data.clear();
        data = commentDAO.getCommentsByRecipeIdOrderByDate(recipeId);
        tvCommentCount.setText(data.size() + " bình luận");

        initRecyclerView();
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