package com.example.elenavlasceanu.kitesurfingapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FilteredSpotActivity extends AppCompatActivity {
    private RecyclerView mRecyclerViewFilteredSpots;
    private List<GeneralInformationSpot> mFilteredSpots = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtered_spot);
        Intent intent = getIntent();
        String country = intent.getStringExtra("country");
        int windProbability = intent.getIntExtra("windProbability", 0);
        FilterCriteria filterCriteria = new FilterCriteria(windProbability, country);

        /////////////retrofit//////////

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://internship-2019.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        UserClient userClient = retrofit.create(UserClient.class);
        Call<ResponseBody> call = userClient.filterSpots(filterCriteria);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(FilteredSpotActivity.this, "succes", Toast.LENGTH_SHORT).show();
                    try {
                        String json = response.body().string();
                        JSONObject obj = new JSONObject(json);
                        JSONArray array = obj.getJSONArray("result");
                        for (int i = 0; i < array.length(); i++) {

                            JSONObject object = array.getJSONObject(i);
                            String country = object.getString("country");
                            String name = object.getString("name");
                            String whenToGo = object.getString("whenToGo");
                            String id = object.getString("id");
                            GeneralInformationSpot spot = new GeneralInformationSpot(name, country, whenToGo, id);
                            mFilteredSpots.add(spot);
                        }
                        Toast.makeText(FilteredSpotActivity.this, mFilteredSpots.get(10).getCountry(), Toast.LENGTH_SHORT).show();
                        /////////populate recycler view/////////
                        loadDataList(mFilteredSpots);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    Toast.makeText(FilteredSpotActivity.this, response.errorBody().toString(), Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(FilteredSpotActivity.this, "error failure all spots", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void loadDataList(List<GeneralInformationSpot> spotsList) {
        //Get a reference to the RecyclerView//

        mRecyclerViewFilteredSpots = findViewById(R.id.recycler_view_filtered_spots);
        SpotFilteredAdapter spotAdapter = new SpotFilteredAdapter(spotsList, FilteredSpotActivity.this);

        //set the adapter to the recycler view
        mRecyclerViewFilteredSpots.setAdapter(spotAdapter);

        //Use a LinearLayoutManager with default vertical orientation//

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(FilteredSpotActivity.this);
        mRecyclerViewFilteredSpots.setLayoutManager(layoutManager);

        //Set the Adapter to the RecyclerView//

        mRecyclerViewFilteredSpots.setAdapter(spotAdapter);
    }
}