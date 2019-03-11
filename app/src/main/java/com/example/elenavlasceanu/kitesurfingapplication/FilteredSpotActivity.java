package com.example.elenavlasceanu.kitesurfingapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FilteredSpotActivity extends AppCompatActivity {
    private RecyclerView mRecyclerViewSpots;
    private List<GeneralInformationSpot> mSpots = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //////get information from intent///
        Intent intent = getIntent();
        String country = intent.getStringExtra("country");
        int windProbability = intent.getIntExtra("windProbability", 0);

        /////////////retrofit//////////

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://internship-2019.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        UserClient userClient = retrofit.create(UserClient.class);
        Call<List<GeneralInformationSpot>> call = userClient.filterSpots(country, windProbability);
        call.enqueue(new Callback<List<GeneralInformationSpot>>() {
            @Override
            public void onResponse(Call<List<GeneralInformationSpot>> call, Response<List<GeneralInformationSpot>> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(FilteredSpotActivity.this, "succes", Toast.LENGTH_SHORT).show();
                    mSpots = response.body();
                    /////////populate recycler view/////////
                    loadDataList(mSpots);
                } else {
                    Toast.makeText(FilteredSpotActivity.this, response.errorBody().toString(), Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<List<GeneralInformationSpot>> call, Throwable t) {
                Toast.makeText(FilteredSpotActivity.this, "error failure all spots", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void loadDataList(List<GeneralInformationSpot> spotsList) {
        //Get a reference to the RecyclerView//

        mRecyclerViewSpots = findViewById(R.id.recycler_view_spots);
        SpotAdapter spotAdapter = new SpotAdapter(spotsList);

        //set the adapter to the recycler view
        mRecyclerViewSpots.setAdapter(spotAdapter);

        //Use a LinearLayoutManager with default vertical orientation//

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(FilteredSpotActivity.this);
        mRecyclerViewSpots.setLayoutManager(layoutManager);

        //Set the Adapter to the RecyclerView//

        mRecyclerViewSpots.setAdapter(spotAdapter);
    }

}