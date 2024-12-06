package com.fu.fe.minhtq.prm392g5fa24bl5.HomePage;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fu.fe.minhtq.prm392g5fa24bl5.R;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity {
private RecyclerView rcvPopur;
private RecyclerView rcvNew;
private RecyclerView rcvShare;
private List<Item> itemList;


    private void data(){
        itemList = new ArrayList<>();

        itemList.add(new Item(R.drawable.img, "aaaaaaa"));
        itemList.add(new Item(R.drawable.img, "bbbbbbbb"));
        itemList.add(new Item(R.drawable.img, "bbbbbbbb"));
        itemList.add(new Item(R.drawable.img, "bbbbbbbb"));
        itemList.add(new Item(R.drawable.img, "bbbbbbbb"));
        itemList.add(new Item(R.drawable.img, "ccccccc"));

    }
    private void bindingView(){
        rcvPopur = findViewById(R.id.rcvPopur);
        rcvNew = findViewById(R.id.recyclerFoodNew);
        rcvShare = findViewById(R.id.recyclerFoodShare);
    }
    private void initRecyclerView(){
        List<Item> limitedList = itemList.size() > 6 ? itemList.subList(0, 6) : itemList;
        List<Item> limitedList3 = itemList.size() > 3 ? itemList.subList(0, 3) : itemList;
        ItemAdapter adapter = new ItemAdapter(limitedList);
        ItemAdapter adapter1 = new ItemAdapter(limitedList3);
        rcvPopur.setAdapter(adapter);
        rcvNew.setAdapter(adapter1);
        rcvShare.setAdapter(adapter1);
        rcvNew.setLayoutManager(new GridLayoutManager(this, 3));
        rcvShare.setLayoutManager(new GridLayoutManager(this, 3));
        rcvPopur.setLayoutManager(new GridLayoutManager(this, 3));
    }
    private void bindingAction(){}

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
        data();
        initRecyclerView();
        bindingAction();

    }
}