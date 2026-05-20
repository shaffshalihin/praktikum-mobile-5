package com.ishmah.praktikumm2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class StoryHighlightAdapter extends RecyclerView.Adapter<StoryHighlightAdapter.ViewHolder> {

    private List<DataDummy.StoryHighlight> stories;
    private OnStoryClickListener listener;

    public interface OnStoryClickListener {
        void onStoryClick(DataDummy.StoryHighlight story);
    }

    public StoryHighlightAdapter(List<DataDummy.StoryHighlight> stories, OnStoryClickListener listener) {
        this.stories = stories;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_story, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DataDummy.StoryHighlight story = stories.get(position);
        Glide.with(holder.itemView.getContext()).load(story.imageRes).centerCrop().into(holder.ivStory);
        holder.tvStoryName.setText(story.title);

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onStoryClick(story);
            }
        });
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivStory;
        TextView tvStoryName;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivStory = itemView.findViewById(R.id.iv_story);
            tvStoryName = itemView.findViewById(R.id.tv_story_name);
        }
    }
}