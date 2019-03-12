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

public class SpotAdapter extends RecyclerView.Adapter<SpotViewHolder> {
    private List<GeneralInformationSpot> mSpots;
    private Context mContext;

    public SpotAdapter(List<GeneralInformationSpot> spots,Context context) {
        mSpots = spots;
        mContext=context;
    }

    @NonNull
    @Override
    public SpotViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View giftView = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.spot_item, viewGroup, false);
        return new SpotViewHolder(giftView);
    }

    @Override
    public void onBindViewHolder(@NonNull SpotViewHolder spotViewHolder, int i) {
       GeneralInformationSpot spot = mSpots.get(i);
        spotViewHolder.mTextViewSpot.setText(spot.getName());
        spotViewHolder.mTextViewCountry.setText(spot.getCountry());
        final  String id=spot.getId();
        spotViewHolder.mItemLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(mContext, DetailsActivity.class);
                intent.putExtra("id",id);
                mContext.startActivity(intent);
                return false;
            }
        });


        spotViewHolder.mImageButtonBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // spotViewHolder.mImageButtonBookmark.setBackgroundResource(R.drawable.star_on);

                Retrofit.Builder builder = new Retrofit.Builder()
                        .baseUrl("https://internship-2019.herokuapp.com/")
                        .addConverterFactory(GsonConverterFactory.create());
                Retrofit retrofit = builder.build();
                UserClient userClient = retrofit.create(UserClient.class);
                Call<ResponseBody> call = userClient.addToFavourite("id");
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(mContext, "succes", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(mContext, response.errorBody().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }


                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(mContext, "error failure all spots", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });
    }

    @Override
    public int getItemCount() {
        return mSpots.size();
    }
}
