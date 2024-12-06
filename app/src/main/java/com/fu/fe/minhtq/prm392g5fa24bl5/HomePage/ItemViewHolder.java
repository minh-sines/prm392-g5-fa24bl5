package com.fu.fe.minhtq.prm392g5fa24bl5.HomePage;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fu.fe.minhtq.prm392g5fa24bl5.R;

public class ItemViewHolder extends RecyclerView.ViewHolder {
    private ImageView imgItem;
    private TextView txtItem;
    private void bindingView(){
        imgItem = itemView.findViewById(R.id.imgItemFood);
        txtItem = itemView.findViewById(R.id.txtItemFood);
    }
    public ItemViewHolder(@NonNull View itemView){
        super(itemView);
        bindingView();
        bindingAction();
    }
    private void bindingAction(){
        itemView.setOnClickListener(this:: onClickItemFood);
    }

    private void onClickItemFood(View view) {
        Toast.makeText(itemView.getContext(), txtItem.getText(), Toast.LENGTH_SHORT).show();
    }

    public void setData(Item item) {
        imgItem.setImageResource(item.getImg());
        txtItem.setText(item.getContent());
    }
}
