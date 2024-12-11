package com.fu.fe.minhtq.prm392g5fa24bl5.HomePage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.fu.fe.minhtq.prm392g5fa24bl5.R;
import com.fu.fe.minhtq.prm392g5fa24bl5.favorites.RecipeFavAdapter;
import com.fu.fe.minhtq.prm392g5fa24bl5.model.Recipe;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class Popular_Fragment extends Fragment {
    private RecyclerView popular_rcv;

    private List<Recipe> recipeList;

    private void bindingView()
    {
        popular_rcv = getView().findViewById(R.id.popular_rcv);
    }

    private void bindingAction()
    {

    }

    private void fillData()
    {
        recipeList = new ArrayList<>();
        for(int i = 0; i < 10; i++)
        {
            recipeList.add(new Recipe("Title " + i, "Description " + i, "Time " + i, 1, 1, 1, "recipe_1.png"));
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_popular_, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fillData();
        bindingView();
        bindingAction();
        Context context = getContext();
        RecipeListAdapter adapter = new RecipeListAdapter(context, recipeList);
        popular_rcv.setAdapter(adapter);
        popular_rcv.setLayoutManager(new LinearLayoutManager(context));
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
                imageView.setImageResource(R.drawable.cutepenguin); // Đổi R.drawable.default_image thành ảnh mặc định của bạn

            }
        } catch (Exception e) {
            e.printStackTrace();
            // Đặt ảnh mặc định nếu có lỗi
            imageView.setImageResource(R.drawable.cutepenguin);
        }
    }
}