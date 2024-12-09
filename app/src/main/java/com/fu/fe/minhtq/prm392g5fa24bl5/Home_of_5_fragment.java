package com.fu.fe.minhtq.prm392g5fa24bl5;

import android.os.Bundle;

import com.fu.fe.minhtq.prm392g5fa24bl5.viewpager.BottomNavViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

public class Home_of_5_fragment extends AppCompatActivity {

    private ViewPager2 pager;

    private BottomNavigationView bottomNavigation;

    private void bindingView()
    {
        pager = findViewById(R.id.pager);
        bottomNavigation = findViewById(R.id.bottom_navigation);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_of_5_fragment);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        bindingView();
        BottomNavViewPagerAdapter myViewPagerAdapter = new BottomNavViewPagerAdapter(this);
        pager.setAdapter(myViewPagerAdapter);

        bottomNavigation.setOnItemSelectedListener(this::onNavigationItemSelected);

        pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 0:
                        bottomNavigation.setSelectedItemId(R.id.bottom_home);
                        break;
                    case 1:
                        bottomNavigation.setSelectedItemId(R.id.bottom_people);
                        break;
                    case 2:
                        bottomNavigation.setSelectedItemId(R.id.bottom_barchart);
                        break;
                    case 3:
                        bottomNavigation.setSelectedItemId(R.id.bottom_book);
                        break;
                    case 4:
                        bottomNavigation.setSelectedItemId(R.id.bottom_calender);
                        break;
                }
            }

        });
    }

    private boolean onNavigationItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();
        if (id == R.id.bottom_home) {
            pager.setCurrentItem(0);
        } else if (id == R.id.bottom_people) {
            pager.setCurrentItem(1);
        } else if (id == R.id.bottom_barchart) {
            pager.setCurrentItem(2);
        } else if (id == R.id.bottom_book) {
            pager.setCurrentItem(3);
        } else if (id == R.id.bottom_calender) {
            pager.setCurrentItem(4);
        }
        return true;
    }
}

////    private AppBarConfiguration appBarConfiguration;
//    private ActivityHomeOf5ActvityBinding binding;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
////        binding = ActivityHomeOf5ActvityBinding.inflate(getLayoutInflater());
////        setContentView(binding.getRoot());
////
////        setSupportActionBar(binding.toolbar);
////
////        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home_of5_actvity);
////        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
////        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
////
////        binding.fab.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
////                        .setAnchorView(R.id.fab)
////                        .setAction("Action", null).show();
////            }
////        });
//    }
//
////    @Override
////    public boolean onSupportNavigateUp() {
////        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home_of5_actvity);
////        return NavigationUI.navigateUp(navController, appBarConfiguration)
////                || super.onSupportNavigateUp();
////    }
//}