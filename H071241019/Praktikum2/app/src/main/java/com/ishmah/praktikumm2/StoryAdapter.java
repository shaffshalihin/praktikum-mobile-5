package com.ishmah.praktikumm2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.ViewHolder> {

    private Context context;
    private List<StoryItem> storyList;
    private OnStoryClickListener listener;

    public interface OnStoryClickListener {
        void onStoryClick(int position);
    }

    public void setOnStoryClickListener(OnStoryClickListener listener) {
        this.listener = listener;
    }

    public StoryAdapter(Context context, List<StoryItem> storyList) {
        this.context = context;
        this.storyList = storyList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_story, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StoryItem item = storyList.get(position);
        Glide.with(context).load(item.getStoryImageRes()).centerCrop().into(holder.ivStory);
        holder.tvStoryName.setText(item.getStoryName());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) listener.onStoryClick(position);
        });
    }

    @Override
    public int getItemCount() {
        return storyList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivStory;
        TextView tvStoryName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivStory = itemView.findViewById(R.id.iv_story);
            tvStoryName = itemView.findViewById(R.id.tv_story_name);
        }
    }
}