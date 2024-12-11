package com.fu.fe.minhtq.prm392g5fa24bl5.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fu.fe.minhtq.prm392g5fa24bl5.HomePage.ItemAdapter;
import com.fu.fe.minhtq.prm392g5fa24bl5.HomePage.PopurAdap;
import com.fu.fe.minhtq.prm392g5fa24bl5.HomePage.Search;
import com.fu.fe.minhtq.prm392g5fa24bl5.R;
import com.fu.fe.minhtq.prm392g5fa24bl5.database.AppDatabase;
import com.fu.fe.minhtq.prm392g5fa24bl5.model.Recipe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HomeFragment extends Fragment {

    private AppDatabase db;
    private RecyclerView rcvPopur;
    private RecyclerView rcvNew;
    private RecyclerView rcvShare;
    private List<Recipe> itemList;
    private ImageButton btnSearch;
    private EditText search_box;

    private void bindingView(View view) {
        db = AppDatabase.getInstance(getContext());
        itemList = db.recipeDAO().getAllRecipes();
        search_box = view.findViewById(R.id.search_box);
        btnSearch = view.findViewById(R.id.btnsearch);

        rcvPopur = view.findViewById(R.id.rcvSearch);
        rcvNew = view.findViewById(R.id.recyclerFoodNew);
        rcvShare = view.findViewById(R.id.recyclerFoodShare);
    }

    private void initRecyclerView() {
        List<Recipe> sortedList = new ArrayList<>(itemList);
        Collections.sort(sortedList, new Comparator<Recipe>() {
            @Override
            public int compare(Recipe o1, Recipe o2) {
                return Long.compare(o2.getCreated_at(), o1.getCreated_at());
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

        rcvNew.setLayoutManager(new GridLayoutManager(getContext(), 3));
        rcvShare.setLayoutManager(new GridLayoutManager(getContext(), 3));
        rcvPopur.setLayoutManager(new GridLayoutManager(getContext(), 3));
    }

    private void bindingAction() {
        btnSearch.setOnClickListener(this::onClick);
    }

    private void onClick(View view) {
        String searchText = search_box.getText().toString().trim();

        if (!searchText.isEmpty()) {
            // Tìm kiếm các recipe theo title
            Intent intent = new Intent(getContext(), Search.class);
            intent.putExtra("search_query", searchText); // Truyền query tìm kiếm
            startActivity(intent);
        } else {
            Toast.makeText(getContext(), "Please enter a search term", Toast.LENGTH_SHORT).show();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_home_page, container, false);
        bindingView(view);
        initRecyclerView();
        bindingAction();
        return view;
    }
}
