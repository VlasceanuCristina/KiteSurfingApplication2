package com.example.elenavlasceanu.kitesurfingapplication;

import com.google.gson.JsonArray;

import org.json.JSONArray;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UserClient {

    /////get user and token/////

    @POST("api-user-get")
    Call<ResponseBody> login(@Body Login login);


    ///////get countries////

    @Headers({
            "Content-Type: application/json",
            "token: TIYUdHWud7"
    })
    @POST("api-spot-get-countries")
    Call<Array> getCountries();


    //////get information general about spots////

    @Headers({
            "Content-Type: application/json",
            "token: TIYUdHWud7"
    })
    @POST("api-spot-get-all")
    Call<ResponseBody> getAllSpots();


    ///////filter spots//////////

    @Headers({
            "Content-Type: application/json",
            "token: TIYUdHWud7"
    })
    @POST("api-spot-get-all")
    Call<ResponseBody> filterSpots(@Body FilterCriteria filterCriteria);

    ///////get details about a spot/////

    @Headers({
            "Content-Type: application/json",
            "token: TIYUdHWud7"
    })
    @POST("api-spot-get-details")
    Call<ResponseBody> getDetails(@Body String spotId);

    ///// addd to favourite/////

    @Headers({
            "Content-Type: application/json",
            "token: TIYUdHWud7"
    })
    @POST("api-spot-favorites-add")
    Call<ResponseBody> addToFavourite(@Body String spotId);

    //////remove from favourite/////

    @Headers({
            "Content-Type: application/json",
            "token: TIYUdHWud7"
    })
    @POST("api-spot-favorites-remove")
    Call<ResponseBody> removeFromFavourite();
}
