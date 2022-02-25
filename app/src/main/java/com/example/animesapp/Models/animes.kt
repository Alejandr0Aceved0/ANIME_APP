package com.example.animesapp.Models

import com.example.animesapp.Models.animes

class animes(mal_id: String?, var title: String, var rating: String, var popularity: String, var status: String, var synopsis: String, var airing: String, var image: String, var season: String, var year: String) : Comparable<animes> {
    var mal_id: String? = null
    override fun compareTo(animes: animes): Int {
        return if (animes.popularity.toInt() < popularity.toInt()) {
            -1
        } else if (popularity.toInt() < popularity.toInt()) {
            0
        } else {
            1
        }
    }
}