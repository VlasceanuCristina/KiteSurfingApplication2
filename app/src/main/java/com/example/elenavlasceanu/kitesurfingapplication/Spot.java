package com.example.elenavlasceanu.kitesurfingapplication;

import com.google.gson.annotations.SerializedName;

public class Spot {
    //@SerializedName("id")
    private String id;
    //@SerializedName("name")
    private String name;
    //@SerializedName("longitude")
    private String longitude;
    // @SerializedName("latitude")
    private String latitude;
    //  @SerializedName("windProbability")
    private String windProbability;
    // @SerializedName("country")
    private String country;
    //  @SerializedName("whenToGo")
    private String whenToGo;
    // @SerializedName("isFavorite")
    private boolean isFavorite;

    public Spot(String id, String name, String longitude, String latitude, String windProbability, String country, String whenToGo, boolean isFavorite) {
        this.id = id;
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.windProbability = windProbability;
        this.country = country;
        this.whenToGo = whenToGo;
        this.isFavorite = isFavorite;
    }


    public Spot(String spot, String country) {
        name = spot;
        this.country = country;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getWindProbability() {
        return windProbability;
    }

    public void setWindProbability(String windProbability) {
        this.windProbability = windProbability;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getWhenToGo() {
        return whenToGo;
    }

    public void setWhenToGo(String whenToGo) {
        this.whenToGo = whenToGo;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
