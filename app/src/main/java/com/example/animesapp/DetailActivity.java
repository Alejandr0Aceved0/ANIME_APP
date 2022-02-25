package com.example.animesapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.animesapp.Models.animes;
import com.example.animesapp.Models.animesList;
import com.squareup.picasso.Picasso;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    private ImageView imgItemDetail;
    private TextView tvTituloDetail;
    private TextView tvDescripcionDetail;
    private animesList itemDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anime_details);
        setTitle(getClass().getSimpleName());
        imgItemDetail = findViewById(R.id.animeImgDetail);
        tvTituloDetail = findViewById(R.id.animeTitleDetail);
        tvDescripcionDetail = findViewById(R.id.animeSynopsisDetail);
        itemDetail = (animesList) getIntent().getExtras().getSerializable("itemDetail");
        //imgItemDetail.setImageResource((itemDetail.getImage()));
       // Picasso.with(mContext).load(image).fit().centerInside().into(holder.animeImg);
        Picasso.with(this).load(itemDetail.getImage()).fit().centerInside().into(imgItemDetail);
        tvTituloDetail.setText(itemDetail.getTitle());
        tvDescripcionDetail.setText(itemDetail.getSynopsis());
    }

}



