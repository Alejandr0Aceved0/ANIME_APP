package com.example.animesapp.Models

import java.io.Serializable

class animesList(
        var mal_id: String,
        var title: String,
        var rating: String,
        var popularity: String,
        var status: String,
        var synopsis: String,
        var airing: String,
        var image: String,
        var season: String,
        var year: String) : Serializable