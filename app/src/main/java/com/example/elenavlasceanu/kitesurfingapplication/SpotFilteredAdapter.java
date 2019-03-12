package com.example.elenavlasceanu.kitesurfingapplication;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SpotFilteredAdapter extends RecyclerView.Adapter<SpotViewHolder> {
    private List<GeneralInformationSpot> mSpots;
    private Context mContext;

    public SpotFilteredAdapter(List<GeneralInformationSpot> spots, Context context) {
        mSpots = spots;
        mContext = context;
    }

    @NonNull
    @Override
    public SpotViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View spotView = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.spot_item, viewGroup, false);
        return new SpotViewHolder(spotView);
    }

    @Override
    public void onBindViewHolder(@NonNull SpotViewHolder spotViewHolder, int i) {
        GeneralInformationSpot spot = mSpots.get(i);
        spotViewHolder.mTextViewSpot.setText(spot.getName());
        spotViewHolder.mTextViewCountry.setText(spot.getCountry());
    }

    @Override
    public int getItemCount() {
        return mSpots.size();
    }
}

