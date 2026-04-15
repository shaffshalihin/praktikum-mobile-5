package com.indira.tp222.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.indira.tp222.R;
import com.indira.tp222.activity.StoryDetailActivity;
import com.indira.tp222.model.Highlight;

import java.util.ArrayList;
import java.util.List;

public class HighlightAdapter extends RecyclerView.Adapter<HighlightAdapter.ViewHolder> {

    private Context context;
    private List<Highlight> highlightList;

    public HighlightAdapter(Context context, List<Highlight> highlightList) {
        this.context = context;
        this.highlightList = highlightList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_highlight, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Highlight highlight = highlightList.get(position);

        String firstImage = highlight.getStoryImageUris().get(0);
        try {
            int coverRes = Integer.parseInt(firstImage);
            holder.ivHighlightCover.setImageResource(coverRes);
        } catch (NumberFormatException e) {
            holder.ivHighlightCover.setImageURI(Uri.parse(firstImage));
        }
        holder.tvHighlightTitle.setText(highlight.getTitle());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, StoryDetailActivity.class);
            intent.putExtra("HIGHLIGHT_TITLE", highlight.getTitle());
            intent.putStringArrayListExtra("STORY_IMAGES", (ArrayList<String>) highlight.getStoryImageUris());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return highlightList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivHighlightCover;
        TextView tvHighlightTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivHighlightCover = itemView.findViewById(R.id.ivHighlightCover);
            tvHighlightTitle = itemView.findViewById(R.id.tvHighlightTitle);
        }
    }
}