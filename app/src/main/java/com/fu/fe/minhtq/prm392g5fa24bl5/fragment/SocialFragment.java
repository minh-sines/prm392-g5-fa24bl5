package com.fu.fe.minhtq.prm392g5fa24bl5.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.fu.fe.minhtq.prm392g5fa24bl5.R;
import com.fu.fe.minhtq.prm392g5fa24bl5.Social.CartAdapter;
import com.fu.fe.minhtq.prm392g5fa24bl5.database.AppDatabase;
import com.fu.fe.minhtq.prm392g5fa24bl5.database.RecipeDAO;
import com.fu.fe.minhtq.prm392g5fa24bl5.manage.recipe.CreateRecipe;
import com.fu.fe.minhtq.prm392g5fa24bl5.model.Recipe;

import java.util.List;

public class SocialFragment extends Fragment {

    private Toolbar tbSocial;
    private ImageView ivSocialAvatar;
    private Button btnSocialCreateRecipe;
    private RecyclerView rcvSocialPost;
    private List<Recipe> data;
    private RecipeDAO recipeDAO;

    private void bindingView() {
        tbSocial = getView().findViewById(R.id.tbSocial);
        ivSocialAvatar = getView().findViewById(R.id.ivSocialAvatar);
        btnSocialCreateRecipe = getView().findViewById(R.id.btnSocialCreateRecipe);
        rcvSocialPost = getView().findViewById(R.id.rcvSocialPost);

        recipeDAO = AppDatabase.getInstance(getContext()).recipeDAO();
        data = recipeDAO.getRecipeByDate();
    }

    private void bindingAction() {
        btnSocialCreateRecipe.setOnClickListener(this::onClickBtnCreateRecipe);
    }

    private void onClickBtnCreateRecipe(View view) {
        Intent intent = new Intent(getContext(), CreateRecipe.class);
        startActivity(intent);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.social_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.suggest_accout_item) {
            Toast.makeText(getContext(), "profile item", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.search_item) {

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        bindingView();
        if (getActivity() != null) {
            AppCompatActivity activity = (AppCompatActivity) getActivity();
            activity.setSupportActionBar(tbSocial);
            if (activity.getSupportActionBar() != null) {
                activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
            }
        }
        bindingAction();
        initRecyclerView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_social, container, false);
    }

    private void initRecyclerView() {
        CartAdapter adapter = new CartAdapter(data);
        rcvSocialPost.setAdapter(adapter);
        rcvSocialPost.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}