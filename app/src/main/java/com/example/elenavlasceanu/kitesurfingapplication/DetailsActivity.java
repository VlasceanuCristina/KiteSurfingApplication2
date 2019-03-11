package com.example.elenavlasceanu.kitesurfingapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailsActivity extends AppCompatActivity {
   private TextView mTextViewCountry;
   private TextView mTextViewLongitude;
   private TextView mTextViewLatitude;
   private TextView mTextViewWindProbability;
   private TextView mtextViewWhenToGo;

    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://internship-2019.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create());
    Retrofit retrofit = builder.build();
    UserClient userClient = retrofit.create(UserClient.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        mTextViewCountry = (TextView) findViewById(R.id.textview_detalis_country);
        mTextViewLatitude = (TextView) findViewById(R.id.textview_detalis_latitude);
        mTextViewLongitude = (TextView) findViewById(R.id.textview_details_logitide);
        mTextViewWindProbability = (TextView) findViewById(R.id.edittext_filter_windprobability);
        mtextViewWhenToGo = (TextView) findViewById(R.id.textview_whentogo);


        ////get SpotId from intent////
        Intent intent=getIntent();
        String id=intent.getStringExtra("id");


        //////get details/////////
        Call<Spot> call = userClient.getDetails(id);
        call.enqueue(new Callback<Spot>() {
            @Override
            public void onResponse(Call<Spot> call, Response<Spot> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(DetailsActivity.this, "succes", Toast.LENGTH_SHORT).show();
                   Spot spot = response.body();
                   mTextViewCountry.setText(spot.getCountry());
                   mTextViewLatitude.setText(spot.getLatitude());
                   mTextViewLongitude.setText(spot.getLongitude());
                   mtextViewWhenToGo.setText(spot.getWhenToGo());
                   mTextViewWindProbability.setText(spot.getWindProbability());

                } else {
                    Toast.makeText(DetailsActivity.this, response.errorBody().toString(), Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<Spot> call, Throwable t) {
                Toast.makeText(DetailsActivity.this, "error failure all spots", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
