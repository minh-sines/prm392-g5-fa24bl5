package com.fu.fe.minhtq.prm392g5fa24bl5.viewpager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.fu.fe.minhtq.prm392g5fa24bl5.fragment.CaloriesFragment;
import com.fu.fe.minhtq.prm392g5fa24bl5.favorites.FavoritesFragment;
import com.fu.fe.minhtq.prm392g5fa24bl5.fragment.HomeFragment;
import com.fu.fe.minhtq.prm392g5fa24bl5.fragment.ScheduleFragment;
import com.fu.fe.minhtq.prm392g5fa24bl5.fragment.SocialFragment;

public class BottomNavViewPagerAdapter extends FragmentStateAdapter {
    public BottomNavViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position)
        {
            case 0:
                return new HomeFragment();
            case 1:
                return new SocialFragment();
            case 2:
                return new CaloriesFragment();
            case 3:
                return new FavoritesFragment();
            case 4:
                return new ScheduleFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}

