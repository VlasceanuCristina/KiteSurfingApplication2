package com.example.elenavlasceanu.kitesurfingapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FilterActivity extends AppCompatActivity {
   private EditText mEditTextCountry;
   private EditText mEditTextWindProbility;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        mEditTextCountry=(EditText) findViewById(R.id.edittext_filter_country);
        mEditTextWindProbility=(EditText) findViewById(R.id.edittext_filter_windprobability);
    }
    public void filterButtonOnClick(View view){
        Intent intent= new Intent(FilterActivity.this,FilteredSpotActivity.class);
        intent.putExtra("country",mEditTextCountry.getText());
        intent.putExtra("windProbability",mEditTextWindProbility.getText());
        startActivity(intent);
    }
}
