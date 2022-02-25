package com.example.animesapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
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
import java.util.List;
import java.util.stream.Collectors;

public class AdapterListAnimes extends RecyclerView.Adapter<AdapterListAnimes.ViewHolder> {

    private Context mContext;
    private ArrayList<animes> mListAnimes;
    private OnItemClickListener mlistener;
    private List<animes> animesItems;

    public AdapterListAnimes(Context context, ArrayList<animes> animesList){
        mContext = context;
        mListAnimes = animesList;
        this.animesItems = new ArrayList<>();
        animesItems.addAll(mListAnimes);
    }


    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener( OnItemClickListener listener ){
        mlistener = listener;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext)
                .inflate(R.layout.animeslist, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        animes item = mListAnimes.get(position);
        String title = item.getTitle();
        String season = item.getSeason();
        String year = item.getYear();
        String status = item.getStatus();
        String popularity = item.getPopularity();
        String image = item.getImage();

        holder.animeTitle.setText(title);
        holder.animeSeason.setText("Season: "+ season);
        holder.animeYear.setText("Year: " + year);
        holder.animePopularity.setText("Popularity: "+popularity);
        holder.animeStatus.setText("Status: "+ status);

        if(status.equals("Finished Airing")){
            holder.animeStatus.setBackgroundColor(R.color.redFinished);
            holder.animeStatus.setTextColor(R.color.white);
        }
        if(status.equals("Currently Airing")){
            holder.animeStatus.setBackgroundColor(R.color.greenSuccess);
            holder.animeStatus.setTextColor(R.color.white);
        }
        Picasso.with(mContext).load(image).fit().centerInside().into(holder.animeImg);
    }


    @Override
    public int getItemCount() {

        return mListAnimes.size();
    }

    public void filter(String strSearch){
        if (strSearch.length() ==0){
            mListAnimes.clear();
            mListAnimes.addAll(animesItems);
        }else{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                mListAnimes.clear();
                List<animes> collect = animesItems.stream()
                        .filter(i -> i.getTitle().contains(strSearch))
                        .collect(Collectors.toList());
                mListAnimes.addAll(collect);
            }else{
                mListAnimes.clear();
                for (animes i : animesItems){
                    if (i.getTitle().contains(strSearch)){
                        mListAnimes.add(i);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //inicialize tv
        TextView animeTitle;
        TextView animeYear;
        TextView animeSeason;
        TextView animePopularity;
        TextView animeStatus;
        //inicialize imgv
        ImageView animeImg;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            animeTitle = itemView.findViewById(R.id.animeTitle);
            animeYear = itemView.findViewById(R.id.animeYear);
            animeSeason = itemView.findViewById(R.id.animeSeason);
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