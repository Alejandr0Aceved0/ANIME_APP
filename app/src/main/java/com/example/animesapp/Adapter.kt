package com.example.animesapp

import android.content.Context
import com.example.animesapp.Models.animes
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.example.animesapp.R
import com.squareup.picasso.Picasso
import android.widget.TextView
import java.util.ArrayList

class Adapter(private val mContext: Context, private val mListAnimes: ArrayList<animes>) : RecyclerView.Adapter<Adapter.ViewHolder>() {
    private var mlistener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        mlistener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(mContext)
                .inflate(R.layout.animes, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mListAnimes[position]
        val name = item.title
        val rating = item.rating
        val image = item.image

//        holder.tvRating.setText(rating);
//        holder.tvTitle.setText(name);
        Picasso.with(mContext).load(image).fit().centerInside().into(holder.imgvPhoto)
    }

    override fun getItemCount(): Int {
        return mListAnimes.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //inicialize tv
        var tvTitle: TextView? = null
        var tvRating: TextView? = null
        var tvDescription: TextView? = null
        var tvAdded: TextView? = null
        var tvRelease: TextView? = null
        var tvRuntime: TextView? = null

        //inicialize imgv
        var imgvPhoto: ImageView

        init {
            imgvPhoto = itemView.findViewById(R.id.animeImg)
            itemView.setOnClickListener {
                if (mlistener != null) {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        mlistener!!.onItemClick(position)
                    }
                }
            }
        }
    }
}