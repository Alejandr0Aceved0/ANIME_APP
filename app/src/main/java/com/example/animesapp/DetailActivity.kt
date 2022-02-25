package com.example.animesapp

import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import com.example.animesapp.Models.animesList
import android.os.Bundle
import android.graphics.Typeface
import android.widget.ImageView
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {

    private var itemDetail: animesList? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.anime_details)
        itemDetail = intent.extras!!.getSerializable("itemDetail") as animesList?
        title = javaClass.simpleName
        val animeTitleDetail = findViewById<TextView>(R.id.animeTitleDetail)
        animeTitleDetail.setTypeface(Typeface.createFromAsset(assets, "fonts/Anton-Regular.ttf"))
        val imgAnimeDetail = findViewById<ImageView>(R.id.animeImgDetail)
        val animeStatusDetail = findViewById<TextView>(R.id.statusAnimeDetails)
        val animeSynopsisDetail = findViewById<TextView>(R.id.synopsisAnimeDetails)
        val animeIdDetail = findViewById<TextView>(R.id.idAnimeDatails)
        val animeYearDetail = findViewById<TextView>(R.id.yearAnimeDatails)
        val animeRatingDetail = findViewById<TextView>(R.id.ratingAnimeDatails)
        val animePopularityDetail = findViewById<TextView>(R.id.popularityAnimeDetails)
        val animeAiringDetail = findViewById<TextView>(R.id.airingAnimeDetails)
        Picasso.with(this).load(itemDetail!!.image).fit().centerInside().into(imgAnimeDetail)
        animeTitleDetail.setText(itemDetail!!.title)
        animeStatusDetail.setText(itemDetail!!.status)
        animeSynopsisDetail.setText(itemDetail!!.synopsis)
        animeIdDetail.setText(itemDetail!!.mal_id)
        animeYearDetail.setText(itemDetail!!.year)
        animeRatingDetail.setText(itemDetail!!.rating)
        animePopularityDetail.setText(itemDetail!!.popularity)
        animeAiringDetail.setText(itemDetail!!.airing)
    }
}