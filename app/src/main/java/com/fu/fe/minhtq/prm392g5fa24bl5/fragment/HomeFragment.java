package com.fu.fe.minhtq.prm392g5fa24bl5.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fu.fe.minhtq.prm392g5fa24bl5.HomePage.ItemAdapter;
import com.fu.fe.minhtq.prm392g5fa24bl5.HomePage.Newest_Fragment;
import com.fu.fe.minhtq.prm392g5fa24bl5.HomePage.Popular_Fragment;
import com.fu.fe.minhtq.prm392g5fa24bl5.HomePage.PopurAdap;
import com.fu.fe.minhtq.prm392g5fa24bl5.HomePage.Search;
import com.fu.fe.minhtq.prm392g5fa24bl5.Login.LoginActivity;
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
    private ImageButton btnPopur;
    private ImageButton btnNew;
    private ImageButton btnShare;
    private ImageButton btnLogout;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    private void bindingView(View view) {

        btnLogout = view.findViewById(R.id.imgLogout);
        btnPopur = view.findViewById(R.id.btnPopu);
        btnNew = view.findViewById(R.id.btnNew);
        btnShare = view.findViewById(R.id.btnShare);

        db = AppDatabase.getInstance(getContext());
        itemList = db.recipeDAO().getAllRecipes();
        search_box = view.findViewById(R.id.search_box);
        btnSearch = view.findViewById(R.id.btnsearch);

        rcvPopur = view.findViewById(R.id.rcvSearch);
        rcvNew = view.findViewById(R.id.recyclerFoodNew);
        rcvShare = view.findViewById(R.id.recyclerFoodShare);
        pref = view.getContext().getSharedPreferences("DataPref", MODE_PRIVATE);
        editor = pref.edit();
    }


    private void initRecyclerView() {
        List<Recipe> sortedList = new ArrayList<>(itemList);
        Collections.sort(sortedList, new Comparator<Recipe>() {
            @Override
            public int compare(Recipe o1, Recipe o2) {
                if (o2.getUpdated_at() != o1.getUpdated_at()) {
                    return Long.compare(o2.getUpdated_at(), o1.getUpdated_at());
                }
                return Long.compare(o2.getCreated_at(), o1.getCreated_at());
            }
        });
        List<Recipe> sortedListByHearts = new ArrayList<>(itemList);
        Collections.sort(sortedListByHearts, new Comparator<Recipe>() {
            @Override
            public int compare(Recipe o1, Recipe o2) {
                return Integer.compare(o2.getHeartCount(), o1.getHeartCount()); // Sort by heartCount (descending)
            }
        });

        List<Recipe> limitedList = sortedListByHearts.size() > 6 ? sortedListByHearts.subList(0, 6) : sortedListByHearts;
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
        btnPopur.setOnClickListener(this::onClickPopur);
        btnNew.setOnClickListener(this::onClickNew);
        btnShare.setOnClickListener(this::onClickShare);
        btnLogout.setOnClickListener(this::onClickLogout);
    }

    private void onClickLogout(View view) {

        editor.clear();
        editor.apply();


        Intent intent = new Intent(getContext(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);  // Clears the activity stack
        startActivity(intent);

        getActivity().finish();  // Close the current activity
    }

    private void onClickShare(View view) {
        replaceFragment(new SocialFragment());
    }

    private void onClickNew(View view) {
        replaceFragment(new Newest_Fragment());
    }

    private void onClickPopur(View view) {
        replaceFragment(new Popular_Fragment());
    }
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getParentFragmentManager(); // Or getChildFragmentManager if nested
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment); // Replace with your container ID
        fragmentTransaction.addToBackStack(null); // Optional: add to back stack to enable back navigation
        fragmentTransaction.commit();
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
