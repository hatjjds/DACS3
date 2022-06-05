package com.example.dacs3.Home;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class HomeViewPagerAdapter extends FragmentStateAdapter {

    public HomeViewPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 3:
                return new Directory();
            case 0:
                return new NewAnime();
            case 1:
                return new FilmSeries();
            case 2:
                return new OddMovies();
            default:
                return new NewAnime();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }

}
