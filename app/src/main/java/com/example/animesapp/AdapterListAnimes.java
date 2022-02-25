package com.example.animesapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animesapp.Models.animes;
import com.example.animesapp.Models.animesList;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdapterListAnimes extends RecyclerView.Adapter<AdapterListAnimes.ViewHolder> {

    private Context mContext;
    private ArrayList<animesList> mListAnimes;
    private OnItemClickListener mlistener;
    private List<animesList> animesItems;

    public AdapterListAnimes(Context context, ArrayList<animesList> animesList){
        mContext = context;
        mListAnimes = animesList;
        this.animesItems = new ArrayList<>();
        animesItems.addAll(mListAnimes);
    }


    public interface OnItemClickListener{
        void onItemClick(int position);
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

        animesList item = mListAnimes.get(position);
        String title = item.getTitle();
        String season = item.getSeason();
        String year = item.getYear();
        String status = item.getStatus();
        String popularity = item.getPopularity();
        String image = item.getImage();

        holder.animeTitle.setText(title);
        holder.animeSeason.setText( season);
        holder.animeYear.setText(year);
        holder.animePopularity.setText(popularity);
        holder.animeStatus.setText(status);

        if(status.equals("Finished Airing")){
            holder.animeStatus.setBackgroundColor(R.color.redFinished);
            holder.animeStatus.setTextColor(R.color.white);
        }
        if(status.equals("Currently Airing")){
            holder.animeStatus.setBackgroundColor(R.color.greenSuccess);
            holder.animeStatus.setTextColor(R.color.white);
        }
        Picasso.with(mContext).load(image).fit().centerInside().into(holder.animeImg);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
                intent.putExtra("itemDetail", item);
                holder.itemView.getContext().startActivity(intent);
            }
        });
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
                List<animesList> collect = animesItems.stream()
                        .filter(i -> i.getTitle().contains(strSearch))
                        .collect(Collectors.toList());
                mListAnimes.addAll(collect);
            }else{
                mListAnimes.clear();
                for (animesList i : animesItems){
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
            animeYear = itemView.findViewById(R.id.yearAnime);
            animeSeason = itemView.findViewById(R.id.seasonAnime);
            animePopularity = itemView.findViewById(R.id.popularityAnime);
            animeStatus = itemView.findViewById(R.id.statusAnime);
            animeImg = itemView.findViewById(R.id.animeImgCard);

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