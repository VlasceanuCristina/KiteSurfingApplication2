package com.example.elenavlasceanu.kitesurfingapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SpotViewHolder extends RecyclerView.ViewHolder {
    public TextView mTextViewSpot;
    public TextView mTextViewCountry;
    public RelativeLayout mItemLayout;
    public ImageButton mImageButtonBookmark;

    public SpotViewHolder(@NonNull View itemView) {
        super(itemView);

        mTextViewSpot = itemView.findViewById(R.id.textview_spot);
        mTextViewCountry = itemView.findViewById(R.id.textview_country);
        mItemLayout = itemView.findViewById(R.id.linearLayoutItemSpot);
        mImageButtonBookmark = itemView.findViewById(R.id.bookmark);
    }
}
