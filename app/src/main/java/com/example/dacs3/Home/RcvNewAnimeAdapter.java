package com.example.dacs3.Home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dacs3.DetailActivity.DetailActivity;
import com.example.dacs3.Model.NewModel;
import com.example.dacs3.R;

import java.util.List;

public class RcvNewAnimeAdapter extends RecyclerView.Adapter<RcvNewAnimeAdapter.ViewHolder> {
    private Context context;
    private List<NewModel> mData;

    public RcvNewAnimeAdapter(Context context, List<NewModel> mData) {
        this.context = context;
        this.mData = mData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.rcv_new_anime,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NewModel model=mData.get(position);
        holder.name.setText(model.getName_anime().toString());
        Glide.with(context).load(model.getBackgroud()).into(holder.backgroud);

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, DetailActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("id_anime",model.getId_anime());
                bundle.putString("backgroud",model.getBackgroud());
                bundle.putString("name",model.getName_anime());
                bundle.putString("detail",model.getDetail());
                bundle.putString("total_episodes",model.getTotal_episodes());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        ImageView backgroud;
        ConstraintLayout constraintLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.tv_view_home);
            backgroud=itemView.findViewById(R.id.img_view_home);
            constraintLayout=itemView.findViewById(R.id.home_layout);
        }
    }
}
