package com.fu.fe.minhtq.prm392g5fa24bl5.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fu.fe.minhtq.prm392g5fa24bl5.HomePage.Item;
import com.fu.fe.minhtq.prm392g5fa24bl5.HomePage.ItemAdapter;
import com.fu.fe.minhtq.prm392g5fa24bl5.R;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    private RecyclerView rcvPopur;
    private RecyclerView rcvNew;
    private RecyclerView rcvShare;
    private List<Item> itemList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }
    private void data() {
        itemList = new ArrayList<>();

        itemList.add(new Item(R.drawable.img, "aaaaaaa"));
        itemList.add(new Item(R.drawable.img, "bbbbbbbb"));
        itemList.add(new Item(R.drawable.img, "bbbbbbbb"));
        itemList.add(new Item(R.drawable.img, "bbbbbbbb"));
        itemList.add(new Item(R.drawable.img, "bbbbbbbb"));
        itemList.add(new Item(R.drawable.img, "ccccccc"));
    }

    private void bindingView(View view) {
        rcvPopur = view.findViewById(R.id.rcvPopur);
        rcvNew = view.findViewById(R.id.recyclerFoodNew);
        rcvShare = view.findViewById(R.id.recyclerFoodShare);
    }

    private void initRecyclerView() {
        List<Item> limitedList = itemList.size() > 6 ? itemList.subList(0, 6) : itemList;
        List<Item> limitedList3 = itemList.size() > 3 ? itemList.subList(0, 3) : itemList;
        ItemAdapter adapter = new ItemAdapter(limitedList);
        ItemAdapter adapter1 = new ItemAdapter(limitedList3);
        rcvPopur.setAdapter(adapter);
        rcvNew.setAdapter(adapter1);
        rcvShare.setAdapter(adapter1);
        rcvNew.setLayoutManager(new GridLayoutManager(getContext(), 3));
        rcvShare.setLayoutManager(new GridLayoutManager(getContext(), 3));
        rcvPopur.setLayoutManager(new GridLayoutManager(getContext(), 3));
    }

    private void bindingAction() {
        // Add any event handling code here if needed
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_home_page, container, false);
        bindingView(view);
        data();
        initRecyclerView();
        bindingAction();
        return view;
    }
}