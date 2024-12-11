package com.fu.fe.minhtq.prm392g5fa24bl5.favorites;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fu.fe.minhtq.prm392g5fa24bl5.R;
import com.google.android.material.tabs.TabLayout;


public class FavoritesFragment extends Fragment {
    private TabLayout tabLayout;

    private void bindingView()
    {
        tabLayout = getView().findViewById(R.id.tabLayout);
//        viewPager = getView().findViewById(R.id.viewPager);
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
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bindingView();
        tabLayout.addTab(tabLayout.newTab().setText("Món ăn tự tạo"));
        tabLayout.addTab(tabLayout.newTab().setText("Món ăn yêu thích"));
        TabLayout.Tab tab = tabLayout.getTabAt(0); // Get the first tab
        if (tab != null) {
            TextView tabTextView = new TextView(getContext());
            tabTextView.setTextSize(20);
            tabTextView.setText("Món ăn tự tạo");
            tabTextView.setTypeface(tabTextView.getTypeface(), Typeface.BOLD);
            tab.setCustomView(tabTextView);
        }
        TabLayout.Tab tab2 = tabLayout.getTabAt(1); // Get the first tab
        if (tab2 != null) {
            TextView tabTextView = new TextView(getContext());
            tabTextView.setTextSize(20);
            tabTextView.setText("Món ăn yêu thích");
            tabTextView.setTypeface(tabTextView.getTypeface(), Typeface.BOLD);
            tab2.setCustomView(tabTextView);
        }

        // Set initial fragment
        replaceFragment(new Saved_Fragment());

        // Handle tab selection
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        replaceFragment(new Saved_Fragment());
                        break;
                    case 1:
                        replaceFragment(new Favorite_Fragment());
                        break;
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}