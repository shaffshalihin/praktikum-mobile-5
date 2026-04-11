package com.indira.tp222.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.indira.tp222.R;

import java.util.List;

public class StoryPagerAdapter extends RecyclerView.Adapter<StoryPagerAdapter.ViewHolder> {

    private Context context;
    private List<String> imageUris;

    public StoryPagerAdapter(Context context, List<String> imageUris) {
        this.context = context;
        this.imageUris = imageUris;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_story_page, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String imageUri = imageUris.get(position);

        try {
            int resId = Integer.parseInt(imageUri);
            holder.ivStoryImage.setImageResource(resId);
        } catch (NumberFormatException e) {
            holder.ivStoryImage.setImageURI(Uri.parse(imageUri));
        }

        holder.ivStoryImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }

    @Override
    public int getItemCount() {
        return imageUris.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivStoryImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivStoryImage = itemView.findViewById(R.id.ivStoryImage);
        }
    }
}