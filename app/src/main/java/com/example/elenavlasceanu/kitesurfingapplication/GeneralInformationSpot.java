package com.example.elenavlasceanu.kitesurfingapplication;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GeneralInformationSpot {
    //@SerializedName("name")
    // @Expose
    private String name;
    // @SerializedName("country")
    //@Expose
    private String country;
    // @SerializedName("whenToGo")
    //@Expose
    private String whenToGo;
    // @SerializedName("id")
    //@Expose
    private String id;
    //@SerializedName("isFavourite")
    //@Expose
    private Boolean isFavorite;


    ///////////////getter and setter///////////

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favourite) {
        isFavorite = favourite;
    }

    public GeneralInformationSpot(String name, String country, String whenToGo, String id, Boolean isFavourite) {
        this.name = name;
        this.country = country;
        this.whenToGo = whenToGo;
        this.id = id;
        this.isFavorite = isFavourite;
    }

    public GeneralInformationSpot(String name, String country,String whenToGo,String id) {
        this.name = name;
        this.country = country;
        this.whenToGo=whenToGo;
        this.id=id;
    }
}