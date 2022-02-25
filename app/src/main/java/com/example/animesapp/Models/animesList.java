package com.example.animesapp.Models;

public class animesList implements  Comparable<animes>{

    private String mal_id;
    private String title;
    private String rating;
    private String popularity;
    private String status;
    private String synopsis;
    private String airing;
    private String image;
    private String season;
    private String year;


    public animesList(String mal_id, String title, String rating, String popularity, String status, String synopsis, String airing, String image, String season, String Year) {
        this.title = title;
        this.rating = rating;
        this.popularity = popularity;
        this.status = status;
        this.synopsis = synopsis;
        this.airing = airing;
        this.image = image;
        this.season = season;
        this.year = year;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }


    public String getMal_id() {
        return mal_id;
    }

    public void setMal_id(String mal_id) {
        this.mal_id = mal_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getAiring() {
        return airing;
    }

    public void setAiring(String airing) {
        this.airing = airing;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    @Override
    public int compareTo(animes animes) {

        if (Integer.parseInt(animes.getYear() ) < Integer.parseInt(popularity)) {
            return -1;
        } else if (Integer.parseInt(getPopularity()) < Integer.parseInt(popularity)) {
            return 0;
        } else {
            return 1;
        }
    }
}
