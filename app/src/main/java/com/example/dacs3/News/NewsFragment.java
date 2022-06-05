package com.example.dacs3.News;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
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

public class NewsFragment extends Fragment {
    private View v;
    private TabLayout tabLayoutNews;
    private ViewPager2 viewPagerNews;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_news, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Tin tức");

        tabLayoutNews = v.findViewById(R.id.news_tablayout);
        viewPagerNews = v.findViewById(R.id.news_viewpager);

        NewsViewPagerAdapter adapter = new NewsViewPagerAdapter(this);
        viewPagerNews.setAdapter(adapter);

        new TabLayoutMediator(tabLayoutNews, viewPagerNews, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if (position == 0) {
                    tab.setText("Tin tức");
                } else {
                    tab.setText("Xếp Hạng");
                }
            }
        }).attach();

        setTabDividers();

        return v;
    }

    private void setTabDividers() {
        View root=tabLayoutNews.getChildAt(0);
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