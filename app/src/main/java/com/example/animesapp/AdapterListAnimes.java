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

public class AdapterListAnimes extends RecyclerView.Adapter<AdapterListAnimes.ViewHolder> {

    private Context mContext;
    private ArrayList<animes> mListAnimes;
    private OnItemClickListener mlistener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener( OnItemClickListener listener ){
        mlistener = listener;
    }

    public AdapterListAnimes(Context context, ArrayList<animes> animesList){
        mContext = context;
        mListAnimes = animesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext)
                .inflate(R.layout.animeslist, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {



        animes item = mListAnimes.get(position);
        String title = item.getTitle();
        String status = item.getStatus();
        String popularity = item.getPopularity();
        String image = item.getImage();

        holder.animeTitle.setText(title);
        holder.animePopularity.setText(popularity);
        holder.animeStatus.setText(status);
        Picasso.with(mContext).load(image).fit().centerInside().into(holder.animeImg);
    }


    @Override
    public int getItemCount() {

        return mListAnimes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //inicialize tv
        TextView animeTitle;
        TextView animePopularity;
        TextView animeStatus;
        //inicialize imgv
        ImageView animeImg;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            animeTitle = itemView.findViewById(R.id.animeTitle);
            animePopularity = itemView.findViewById(R.id.animePopularity);
            animeStatus = itemView.findViewById(R.id.animeStatus);
            animeImg = itemView.findViewById(R.id.animeImg);

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