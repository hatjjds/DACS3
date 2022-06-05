package com.example.dacs3.News;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class NewsViewPagerAdapter extends FragmentStateAdapter {
    public NewsViewPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new NewsAnime();
            case 1:
                return new Ranking();
            default:
                return new NewsAnime();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
