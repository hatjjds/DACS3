package com.example.dacs3.DetailActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.WindowDecorActionBar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.dacs3.Home.RcvNewAnimeAdapter;
import com.example.dacs3.Model.ModelSrc;
import com.example.dacs3.Model.NewModel;
import com.example.dacs3.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class DetailActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<NewModel> listEp;

    private static final String Json_URL="http://192.168.1.103/Json/GetVideo.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colordetaillayout)));

        ImageView imageView = findViewById(R.id.detail_image);
        TextView textView = findViewById(R.id.detail_name_anime);
        TextView detail = findViewById(R.id.detail);
        TextView total_episodes = findViewById(R.id.total_episodes);
        recyclerView = findViewById(R.id.rcv_detail);

        listEp=new ArrayList<>();

        Bundle bundle = getIntent().getExtras();
        String id=bundle.getString("id_anime");
        String mBackgroud = bundle.getString("backgroud");
        String mName = bundle.getString("name");
        String mDetail = bundle.getString("detail");
        String mTotal_episodes = bundle.getString("total_episodes");

//        Toast.makeText(this, id.toString(), Toast.LENGTH_SHORT).show();

        Glide.with(this).load(mBackgroud).into(imageView);
        textView.setText(mName.toString());
        detail.setText(mDetail.toString());
        total_episodes.setText(mTotal_episodes.toString());

        getData(Json_URL,id);

    }

    private void getData(String url,String id){
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST,url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Toast.makeText(DetailActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                        try {
                            JSONArray jsonArray=new JSONArray(response);
                            for(int i=0;i<response.length();i++){
                                JSONObject object=jsonArray.getJSONObject(i);
                                NewModel model=new NewModel();
                                model.setEp_number(object.getString("ep_number"));
                                model.setSrc(object.getString("src"));
                                listEp.add(model);
//                                Toast.makeText(DetailActivity.this, listEp.toString(), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        PutData(listEp);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DetailActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param=new HashMap<>();
                param.put("id_anime",id);
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }
    private void PutData(List<NewModel> animeList){
        DetailAdapter adapter=new DetailAdapter(this,animeList);
        recyclerView.setLayoutManager(new GridLayoutManager(this,4));
        recyclerView.setAdapter(adapter);
    }

}