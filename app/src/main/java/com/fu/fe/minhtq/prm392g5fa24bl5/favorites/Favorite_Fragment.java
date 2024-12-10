package com.fu.fe.minhtq.prm392g5fa24bl5.favorites;

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
import com.fu.fe.minhtq.prm392g5fa24bl5.RecipeFavAdapter;
import com.fu.fe.minhtq.prm392g5fa24bl5.database.AppDatabase;
import com.fu.fe.minhtq.prm392g5fa24bl5.model.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class Favorite_Fragment extends Fragment {

    private RecyclerView fav_rcv;

    private List<Recipe> recipeList;

    private void bindingView()
    {
        fav_rcv = getView().findViewById(R.id.fav_rcv);
    }

    private void bindingAction()
    {

    }

    private void fillData()
    {
        AppDatabase instance = AppDatabase.getInstance(getContext());
        recipeList = instance.recipeDAO().getFavoriteRecipesByAccountId(2);

//        recipeList = new ArrayList<>();
//        for(int i = 0; i < 10; i++)
//        {
//            recipeList.add(new Recipe("Title " + i, "Description " + i, "Time " + i, 2, 1, 1, R.drawable.cutecat));
//        }
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
        return inflater.inflate(R.layout.fragment_favorite_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fillData();
        bindingView();
        bindingAction();
        Context context = getContext();
        RecipeFavAdapter adapter = new RecipeFavAdapter(context, recipeList);
        fav_rcv.setAdapter(adapter);
        fav_rcv.setLayoutManager(new LinearLayoutManager(context));
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