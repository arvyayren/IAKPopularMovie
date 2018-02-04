package com.example.user.iakpopularmovie.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.iakpopularmovie.DataMovie;
import com.example.user.iakpopularmovie.R;
import com.example.user.iakpopularmovie.activity.DetailMovieActivity;

import java.util.ArrayList;

/**
 * Created by user on 27/01/2018.
 */

public class AdapterMovie extends RecyclerView.Adapter<AdapterMovie.MyViewHolder>{

    Context c;
    ArrayList <DataMovie> datamovies;
    public AdapterMovie(Context context, ArrayList<DataMovie> datamovies) {
        c = context;
        this.datamovies=datamovies;
    }

    @Override
    public AdapterMovie.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.movie_item,null,false);
        MyViewHolder holder = new MyViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(AdapterMovie.MyViewHolder holder, final int position) {
        //set data
        holder.txtJudul.setText(datamovies.get(position).getTitle());
        Glide.with(c).load("https://image.tmdb.org/t/p/w500/"+datamovies.get(position)
        .getPosterPath()).into(holder.imgmovie);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(c, DetailMovieActivity.class);
                i.putParcelableArrayListExtra("DATA_MOVIE", datamovies);
                i.putExtra("DATA_POSISI", position);
                c.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return datamovies.size();
    }
    //inisiasi imageview, textview, dll
    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView txtJudul;
        ImageView imgmovie;

        public MyViewHolder(View itemView) {
            super(itemView);
                  txtJudul  = (TextView)itemView.findViewById(R.id.tv_item_judulfilm);
                imgmovie = (ImageView)itemView.findViewById(R.id.iv_item_gambarfilm);
            }
        }
    }

