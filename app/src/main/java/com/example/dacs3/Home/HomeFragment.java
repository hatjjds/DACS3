package com.example.dacs3.Home;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.dacs3.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class HomeFragment extends Fragment {
    private TabLayout tabLayoutHome;
    private ViewPager2 viewPagerHome;
    private View viewHome;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewHome = inflater.inflate(R.layout.fragment_home, container, false);
        tabLayoutHome=viewHome.findViewById(R.id.tablayout_home);
        viewPagerHome=viewHome.findViewById(R.id.viewpager_home);

        setHasOptionsMenu(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Trang chủ");

        HomeViewPagerAdapter adapter=new HomeViewPagerAdapter(this);
        viewPagerHome.setAdapter(adapter);

        new TabLayoutMediator(tabLayoutHome, viewPagerHome, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if (position == 0) {
                    tab.setText("Tập mới");
                } else if (position == 1) {
                    tab.setText("Anime bộ");
                } else if (position == 2) {
                    tab.setText("Anime lẻ");
                } else {
                    tab.setText("Danh mục");
                }
            }
        }).attach();

        setTabDividers();

        return viewHome;
    }
    private void setTabDividers() {
        View root=tabLayoutHome.getChildAt(0);
        if(root instanceof LinearLayout){
            ((LinearLayout) root).setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
            GradientDrawable drawable=new GradientDrawable();
            drawable.setColor(Color.GRAY);
            drawable.setSize(2,1);
            ((LinearLayout) root).setDividerPadding(10);
            ((LinearLayout) root).setDividerDrawable(drawable);
        }
    }
}