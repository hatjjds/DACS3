package com.example.dacs3.DetailActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dacs3.Home.RcvNewAnimeAdapter;
import com.example.dacs3.Model.NewModel;
import com.example.dacs3.PlayerActiviti;
import com.example.dacs3.R;

import java.util.List;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.detailViewHolder> {

    public DetailAdapter(Context context, List<NewModel> listData) {
        this.context = context;
        this.listData = listData;
    }

    private Context context;
    private List<NewModel> listData;

    @NonNull
    @Override
    public detailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.rec_detail_item,parent,false);
        return new detailViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull detailViewHolder holder, int position) {
        NewModel model=listData.get(position);
        holder.epNumber.setText(model.getEp_number().toString());
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, PlayerActiviti.class);
                Bundle bundle=new Bundle();
                bundle.putString("ep_number",model.getEp_number());
                bundle.putString("src",model.getSrc());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class detailViewHolder extends RecyclerView.ViewHolder{
        TextView epNumber;
        ConstraintLayout constraintLayout;
        public detailViewHolder(@NonNull View itemView) {
            super(itemView);
            epNumber=itemView.findViewById(R.id.ep_number);
            constraintLayout=itemView.findViewById(R.id.layout_button_play);
        }
    }
}
