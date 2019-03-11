package com.example.elenavlasceanu.kitesurfingapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    /////////////atributes//////////////////////

    private RecyclerView mRecyclerViewSpots;
    private List<GeneralInformationSpot> mSpots = new ArrayList<>();
    private static String token;

    /////////////retrofit/////////
    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://internship-2019.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create());
    Retrofit retrofit = builder.build();
    UserClient userClient = retrofit.create(UserClient.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login();
        getAll();
    }

    /////////////insert filter button in action bar////////

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.filter_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filter_item_bar:
                Intent intent = new Intent(MainActivity.this, FilterActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    /////////////create the user and get token//////////////

    private void login() {
        Login login = new Login("vlasceanucristina7@gmail.com");
        Call<ResponseBody> call = userClient.login(login);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {

                    try {
                        String auxiliar = response.body().string();
                        token = auxiliar.substring(20, 30);
                        Toast.makeText(MainActivity.this, token, Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else {
                    Toast.makeText(MainActivity.this, "Login not correct", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error get user", Toast.LENGTH_SHORT).show();
            }
        });
    }


    ///////////////////get general information about all spots///////////

    private void getAll() {
        Call<List<GeneralInformationSpot>> call = userClient.getAllSpots();
        call.enqueue(new Callback<List<GeneralInformationSpot>>() {
            @Override
            public void onResponse(Call<List<GeneralInformationSpot>> call, Response<List<GeneralInformationSpot>> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "succes", Toast.LENGTH_SHORT).show();
                    mSpots = response.body();
                    /////////populate recycler view/////////
                    loadDataList(mSpots);
                } else {
                    Toast.makeText(MainActivity.this, response.errorBody().toString(), Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<List<GeneralInformationSpot>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "error failure all spots", Toast.LENGTH_SHORT).show();
            }
        });

    }


    ///////////////put information in recycelview//////////


    private void loadDataList(List<GeneralInformationSpot> spotsList) {
        //Get a reference to the RecyclerView//

        mRecyclerViewSpots = findViewById(R.id.recycler_view_spots);
        SpotAdapter spotAdapter = new SpotAdapter(spotsList);

        //set the adapter to the recycler view
        mRecyclerViewSpots.setAdapter(spotAdapter);

        //Use a LinearLayoutManager with default vertical orientation//

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        mRecyclerViewSpots.setLayoutManager(layoutManager);

        //Set the Adapter to the RecyclerView//

        mRecyclerViewSpots.setAdapter(spotAdapter);
    }


    //////////////get list of countries//////

    private void getCountries() {
        Call<Array> call = userClient.getCountries();
        call.enqueue(new Callback<Array>() {
            @Override
            public void onResponse(Call<Array> call, Response<Array> response) {
                Array list = response.body();
                if (list != null) {
                    Toast.makeText(MainActivity.this, "succes", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Array> call, Throwable t) {
                Toast.makeText(MainActivity.this, "error failure get countries", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
