package com.example.animesapp

import com.example.animesapp.Models.animesList
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.annotation.SuppressLint
import android.content.Context
import com.squareup.picasso.Picasso
import android.content.Intent
import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import java.util.ArrayList
import java.util.stream.Collectors

class AdapterListAnimes(private val mContext: Context, private val mListAnimes: ArrayList<animesList>) : RecyclerView.Adapter<AdapterListAnimes.ViewHolder>() {
    private val mlistener: OnItemClickListener? = null
    private val animesItems: MutableList<animesList>

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(mContext)
                .inflate(R.layout.animeslist, parent, false)
        return ViewHolder(v)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mListAnimes[position]
        val title = item.title
        val season = item.season
        val year = item.year
        val status = item.status
        val popularity = item.popularity
        val image = item.image
        holder.animeTitle.text = title
        holder.animeSeason.text = season
        holder.animeYear.text = year
        holder.animePopularity.text = popularity
        holder.animeStatus.text = status
        if (status == "Finished Airing") {
            holder.animeStatus.setBackgroundColor(R.color.redFinished)
            holder.animeStatus.setTextColor(R.color.white)
        }
        if (status == "Currently Airing") {
            holder.animeStatus.setBackgroundColor(R.color.greenSuccess)
            holder.animeStatus.setTextColor(R.color.white)
        }
        Picasso.with(mContext).load(image).fit().centerInside().into(holder.animeImg)
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra("itemDetail", item)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return mListAnimes.size
    }

    fun filter(strSearch: String) {
        if (strSearch.length == 0) {
            mListAnimes.clear()
            mListAnimes.addAll(animesItems)
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                mListAnimes.clear()
                val collect = animesItems.stream()
                        .filter { i: animesList -> i.title.contains(strSearch) }
                        .collect(Collectors.toList())
                mListAnimes.addAll(collect)
            } else {
                mListAnimes.clear()
                for (i in animesItems) {
                    if (i.title.contains(strSearch)) {
                        mListAnimes.add(i)
                    }
                }
            }
        }
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //inicialize tv
        var animeTitle: TextView
        var animeYear: TextView
        var animeSeason: TextView
        var animePopularity: TextView
        var animeStatus: TextView

        //inicialize imgv
        var animeImg: ImageView

        init {
            animeTitle = itemView.findViewById(R.id.animeTitle)
            animeYear = itemView.findViewById(R.id.yearAnime)
            animeSeason = itemView.findViewById(R.id.seasonAnime)
            animePopularity = itemView.findViewById(R.id.popularityAnime)
            animeStatus = itemView.findViewById(R.id.statusAnime)
            animeImg = itemView.findViewById(R.id.animeImgCard)
            itemView.setOnClickListener {
                if (mlistener != null) {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        mlistener.onItemClick(position)
                    }
                }
            }
        }
    }

    init {
        animesItems = ArrayList()
        animesItems.addAll(mListAnimes)
    }
}