package com.example.animesapp;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.animesapp.Models.animesList;
import com.squareup.picasso.Picasso;


import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    private ImageView imgAnimeDetail;
    private TextView animeTitleDetail;
    private TextView animeStatusDetail;
    private TextView animeIdDetail;
    private TextView animeRatingDetail;
    private TextView animePopularityDetail;
    private TextView animeYearDetail;
    private TextView animeSynopsisDetail;
    private TextView animeAiringDetail;
    private animesList itemDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anime_details);
        itemDetail = (animesList) getIntent().getExtras().getSerializable("itemDetail");
        setTitle(getClass().getSimpleName());
        animeTitleDetail = findViewById(R.id.animeTitleDetail);
        animeTitleDetail.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Anton-Regular.ttf"));
        imgAnimeDetail = findViewById(R.id.animeImgDetail);
        animeStatusDetail = findViewById(R.id.statusAnimeDetails);
        animeSynopsisDetail = findViewById(R.id.synopsisAnimeDetails);
        animeIdDetail = findViewById(R.id.idAnimeDatails);
        animeYearDetail = findViewById(R.id.yearAnimeDatails);
        animeRatingDetail = findViewById(R.id.ratingAnimeDatails);
        animePopularityDetail = findViewById(R.id.popularityAnimeDetails);
        animeAiringDetail = findViewById(R.id.airingAnimeDetails);
        //imgItemDetail.setImageResource((itemDetail.getImage()));
        // Picasso.with(mContext).load(image).fit().centerInside().into(holder.animeImg);
        Picasso.with(this).load(itemDetail.getImage()).fit().centerInside().into(imgAnimeDetail);
        animeTitleDetail.setText(itemDetail.getTitle());
        animeStatusDetail.setText(itemDetail.getStatus());
        animeSynopsisDetail.setText(itemDetail.getSynopsis());
        animeIdDetail.setText(itemDetail.getMal_id());
        animeYearDetail.setText(itemDetail.getYear());
        animeRatingDetail.setText(itemDetail.getRating());
        animePopularityDetail.setText(itemDetail.getPopularity());
        animeAiringDetail.setText(itemDetail.getAiring());
    }

}



