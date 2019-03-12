package com.example.elenavlasceanu.kitesurfingapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SpotFilteredViewHolder extends RecyclerView.ViewHolder {
    public TextView mTextViewSpot;
    public TextView mTextViewCountry;

    public SpotFilteredViewHolder(@NonNull View itemView) {
        super(itemView);

        mTextViewSpot = itemView.findViewById(R.id.textview_spot);
        mTextViewCountry = itemView.findViewById(R.id.textview_country);

    }
}
