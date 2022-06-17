package com.example.dacs3.Home;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.dacs3.Model.NewModel;
import com.example.dacs3.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private TabLayout tabLayoutHome;
    private ViewPager2 viewPagerHome;
    private View viewHome;
    private SearchView searchView;
    private List<NewModel> animeNewList;
    private RcvNewAnimeAdapter newAnimeAdapter;

    private static final String Json_URL = "http://192.168.1.103/Json/select.php";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewHome = inflater.inflate(R.layout.fragment_home, container, false);
        tabLayoutHome=viewHome.findViewById(R.id.tablayout_home);
        viewPagerHome=viewHome.findViewById(R.id.viewpager_home);
        animeNewList=new ArrayList<>();
        newAnimeAdapter=new RcvNewAnimeAdapter(getContext(),animeNewList);

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
        setHasOptionsMenu(true);

        new GetData().execute(Json_URL);
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

    public class GetData extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder current = new StringBuilder();
            URL url;
            try {
                url = new URL(strings[0]);
                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                String line="";
                while ((line=bufferedReader.readLine())!=null) {
                    current.append(line);
                }
                bufferedReader.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return current.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONArray array=new JSONArray(s);
                for(int i=0;i<array.length();i++){
                    JSONObject jsonObject1=array.getJSONObject(i);
                    NewModel model=new NewModel();
                    model.setName_anime(jsonObject1.getString("ten_anime"));
                    animeNewList.add(model);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_view, menu);
        SearchManager searchManager= (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        super.onCreateOptionsMenu(menu,inflater);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                newAnimeAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                newAnimeAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }
}