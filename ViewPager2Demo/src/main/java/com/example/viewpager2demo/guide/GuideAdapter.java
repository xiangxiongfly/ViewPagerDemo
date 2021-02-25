package com.example.viewpager2demo.guide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewpager2demo.R;

public class GuideAdapter extends RecyclerView.Adapter<GuideAdapter.MyViewHolder> {

    private final LayoutInflater inflater;
    private final int[] mImgIds;

    public GuideAdapter(Context context, int[] imgIds) {
        inflater = LayoutInflater.from(context);
        mImgIds = imgIds;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(inflater.inflate(R.layout.item_guide, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.imageView.setImageResource(mImgIds[position]);
    }

    @Override
    public int getItemCount() {
        return mImgIds.length;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView;
        }
    }
}
