package com.fu.fe.minhtq.prm392g5fa24bl5.HomePage;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.fu.fe.minhtq.prm392g5fa24bl5.R;
import com.fu.fe.minhtq.prm392g5fa24bl5.favorites.Favorite_Fragment;
import com.fu.fe.minhtq.prm392g5fa24bl5.favorites.Saved_Fragment;
import com.google.android.material.tabs.TabLayout;

public class RecipeListHomeActivity extends AppCompatActivity {
    private TabLayout tabLayout;

    private void bindingView()
    {
        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Phổ biến nhất"));
        tabLayout.addTab(tabLayout.newTab().setText("Mới nhất"));
        TabLayout.Tab tab = tabLayout.getTabAt(0); // Get the first tab
        if (tab != null) {
            TextView tabTextView = new TextView(this);
            tabTextView.setTextSize(20);
            tabTextView.setText("Phổ biến nhất");
            tabTextView.setTypeface(tabTextView.getTypeface(), Typeface.BOLD);
            tab.setCustomView(tabTextView);
        }
        TabLayout.Tab tab2 = tabLayout.getTabAt(1); // Get the first tab
        if (tab2 != null) {
            TextView tabTextView = new TextView(this);
            tabTextView.setTextSize(20);
            tabTextView.setText("Mới nhất");
            tabTextView.setTypeface(tabTextView.getTypeface(), Typeface.BOLD);
            tab2.setCustomView(tabTextView);
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, new Popular_Fragment());  // Replace the container with the default fragment
        transaction.commit();

        // Handle tab selection
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment selectedFragment = null;
                switch (tab.getPosition()) {
                    case 0:
                        selectedFragment = new Popular_Fragment();
                        break;
                    case 1:
                        selectedFragment = new Newest_Fragment();
                        break;
                }
                if (selectedFragment != null) {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, selectedFragment);
                    transaction.commit();
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recipe_list_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        bindingView();



    }
}

