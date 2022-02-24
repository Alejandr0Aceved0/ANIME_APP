package com.example.animesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animesapp.Models.animes;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private Context mContext;
    private ArrayList<animes> mListAnimes;
    private OnItemClickListener mlistener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener( OnItemClickListener listener ){
        mlistener = listener;
    }

    public Adapter ( Context context, ArrayList<animes> moviesList){
        mContext = context;
        mListAnimes = moviesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext)
                .inflate(R.layout.animes, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        animes item = mListAnimes.get(position);
        String name = item.getTitle();
        String rating = item.getRating();
        String image = item.getImage();

//        holder.tvRating.setText(rating);
//        holder.tvTitle.setText(name);
        Picasso.with(mContext).load(image).fit().centerInside().into(holder.imgvPhoto);
    }

    @Override
    public int getItemCount() {

        return mListAnimes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //inicialize tv
        TextView tvTitle;
        TextView tvRating;
        TextView tvDescription;
        TextView tvAdded;
        TextView tvRelease;
        TextView tvRuntime;
        //inicialize imgv
        ImageView imgvPhoto;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgvPhoto = itemView.findViewById(R.id.animeImg);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mlistener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            mlistener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

}