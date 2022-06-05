package com.example.dacs3.Home;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dacs3.Model.NewModel;
import com.example.dacs3.R;

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

public class NewAnime extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private List<NewModel> animeNewList;

    private static String Json_URL = "http://192.168.1.103/Json/select.php";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_new_anime, container, false);

        animeNewList = new ArrayList<>();
        recyclerView = view.findViewById(R.id.rcv_new_anime);

        new GetData().execute(Json_URL);

        return view;
    }

    public class GetData extends AsyncTask<String, Void, String>{

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
                    model.setId_anime(jsonObject1.getString("id_anime"));
                    model.setName_anime(jsonObject1.getString("ten_anime"));
                    model.setBackgroud(jsonObject1.getString("backgroud"));
                    model.setDetail(jsonObject1.getString("detail"));
                    model.setTotal_episodes(jsonObject1.getString("total_episodes"));
                    animeNewList.add(model);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            PutData(animeNewList);
        }
    }

    private void PutData(List<NewModel> animeList){
        RcvNewAnimeAdapter adaptery=new RcvNewAnimeAdapter(view.getContext(),animeList);
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 2));
        recyclerView.setAdapter(adaptery);
    }
}