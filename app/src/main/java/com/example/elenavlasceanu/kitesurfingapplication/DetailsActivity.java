package com.example.elenavlasceanu.kitesurfingapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
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

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        //////get details/////////

        Call<ResponseBody> call = userClient.getDetails(id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {

                    try {
                        String spot = response.body().string();
                        JSONObject obj = new JSONObject(spot);
                        JSONArray array = obj.getJSONArray("result");
                        JSONObject object = array.getJSONObject(0);
                        mTextViewCountry.setText(object.getString("country"));
                        mTextViewLatitude.setText(object.getString("latitude"));
                        mTextViewLongitude.setText(object.getString("longitude"));
                        mtextViewWhenToGo.setText(object.getString("whenToGo"));
                        mTextViewWindProbability.setText(object.getString("windProbability"));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else {
                    Toast.makeText(DetailsActivity.this, response.errorBody().toString(), Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(DetailsActivity.this, "error failure all spots", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
