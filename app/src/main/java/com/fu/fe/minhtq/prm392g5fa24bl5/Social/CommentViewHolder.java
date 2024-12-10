package com.fu.fe.minhtq.prm392g5fa24bl5.Social;

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
//        ivCommentUserImage.setImageResource();
        tvCommentUsername.setText(account.getName());
        tvComment.setText(c.getComment());

        comment = c;
    }

    private void bindingView() {
        ivCommentUserImage = itemView.findViewById(R.id.ivCommentUserImage);
        tvCommentUsername = itemView.findViewById(R.id.tvCommentUsername);
        tvComment = itemView.findViewById(R.id.tvComment);
        btnCommentMenu = itemView.findViewById(R.id.btnCommentMenu);

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
}
