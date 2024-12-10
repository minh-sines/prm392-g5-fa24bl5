package com.fu.fe.minhtq.prm392g5fa24bl5.favorites;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.fu.fe.minhtq.prm392g5fa24bl5.R;
import com.fu.fe.minhtq.prm392g5fa24bl5.RecipeSavedAdapter;
import com.fu.fe.minhtq.prm392g5fa24bl5.model.Recipe;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class Saved_Fragment extends Fragment {

    private RecyclerView saved_rcv;

    private List<Recipe> recipeList;

    private FloatingActionButton fab;

    private void bindingView()
    {
        saved_rcv = getView().findViewById(R.id.saved_rcv);
        fab = getView().findViewById(R.id.fab);
    }

    private void bindingAction()
    {
        fab.setOnClickListener(this::onClickFab);
    }

    private void onClickFab(View view) {
        Toast.makeText(getActivity(), "Clicked add new recipe", Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(getActivity(), TestRecyclerViewActivity.class);
//        intent.putExtra("test", "test");
//        startActivity(intent);
    }

    private void fillData()
    {
        recipeList = new ArrayList<>();
        for(int i = 0; i < 10; i++)
        {
            recipeList.add(new Recipe("Title " + i, "Description " + i, "Time " + i, 2, 1, 1, R.drawable.cutecat));
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
        return inflater.inflate(R.layout.fragment_saved_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fillData();
        bindingView();
        bindingAction();
        Context context = view.getContext();
        RecipeSavedAdapter adapter = new RecipeSavedAdapter(context, recipeList);
        saved_rcv.setAdapter(adapter);
        saved_rcv.setLayoutManager(new LinearLayoutManager(context));
        saved_rcv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    fab.hide();
                } else {
                    fab.show();
                }
            }
        });

    }
}