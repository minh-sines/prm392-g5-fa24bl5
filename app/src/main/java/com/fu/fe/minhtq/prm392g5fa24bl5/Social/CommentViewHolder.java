package com.fu.fe.minhtq.prm392g5fa24bl5.Social;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.fu.fe.minhtq.prm392g5fa24bl5.R;
import com.fu.fe.minhtq.prm392g5fa24bl5.database.AccountDAO;
import com.fu.fe.minhtq.prm392g5fa24bl5.database.AppDatabase;
import com.fu.fe.minhtq.prm392g5fa24bl5.database.CommentDAO;
import com.fu.fe.minhtq.prm392g5fa24bl5.model.Account;
import com.fu.fe.minhtq.prm392g5fa24bl5.model.Comment;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class CommentViewHolder extends RecyclerView.ViewHolder {

    private ImageView ivCommentUserImage;
    private TextView tvCommentUsername;
    private TextView tvComment;
    private Button btnCommentMenu;
    private AccountDAO accountDAO;
    private CommentDAO commentDAO;
    private Comment comment;

    public void setComment(Comment c) {
        Account account = accountDAO.getAccountById(c.getUser_id());
        CommentDAO commentDAO = AppDatabase.getInstance(itemView.getContext()).commentDAO();
//        ivCommentUserImage.setImageResource(account.getAvatar());
        ivCommentUserImage.setImageResource(R.drawable.img);
        tvCommentUsername.setText("Nguyễn Văn B");
//        tvCommentUsername.setText(account.getName());
        tvComment.setText(c.getComment());

        SharedPreferences pref = itemView.getContext().getSharedPreferences("DataPref", Context.MODE_PRIVATE);
        if (pref.getInt("user_id", 0) == c.getUser_id()) {
            btnCommentMenu.setVisibility(View.VISIBLE);
        } else {
            btnCommentMenu.setVisibility(View.GONE);
        }

        comment = c;
    }

    private void bindingView() {
        ivCommentUserImage = itemView.findViewById(R.id.ivCommentUserImage);
        tvCommentUsername = itemView.findViewById(R.id.tvCommentUsername);
        tvComment = itemView.findViewById(R.id.tvComment);

        //kiểm tra user_id == user hiện tại?
        btnCommentMenu = itemView.findViewById(R.id.btnCommentMenu);
        //nếu không thì visible = gone

        accountDAO = AppDatabase.getInstance(itemView.getContext()).accountDAO();
        commentDAO = AppDatabase.getInstance(itemView.getContext()).commentDAO();
    }

    private void bindingAction() {
        btnCommentMenu.setOnClickListener(this::onBtnCommentMenuClick);
    }

    private void onBtnCommentMenuClick(View view) {
        PopupMenu popupMenu = new PopupMenu(itemView.getContext(), view);
        popupMenu.getMenuInflater().inflate(R.menu.popup_comment_menu, popupMenu.getMenu());

        // Ép buộc hiển thị icon
        try {
            Field field = popupMenu.getClass().getDeclaredField("mPopup");
            field.setAccessible(true);
            Object menuHelper = field.get(popupMenu);
            Class<?> classPopupHelper = Class.forName(menuHelper.getClass().getName());
            Method setForceIcons = classPopupHelper.getMethod("setForceShowIcon", boolean.class);
            setForceIcons.invoke(menuHelper, true);

        } catch (Exception e) {
            e.printStackTrace();
        }

        popupMenu.setOnMenuItemClickListener(this::onMenuItemClick);
        popupMenu.show();
    }

    private boolean onMenuItemClick(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.edit_comment) {
            //Code
            Toast.makeText(itemView.getContext(), "Chỉnh sửa bình luận " + comment.getComment_id(), Toast.LENGTH_SHORT).show();
            return true;
        } else if (menuItem.getItemId() == R.id.delete_comment) {
            commentDAO.deleteComment(comment.getComment_id());

            if (itemView.getContext() instanceof AddComment) {
                AddComment activity = (AddComment) itemView.getContext();
                activity.updateComments();
            }

            Toast.makeText(itemView.getContext(), "Xóa bình luận thành công", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    public CommentViewHolder(@NonNull View itemView) {
        super(itemView);
        bindingView();
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
