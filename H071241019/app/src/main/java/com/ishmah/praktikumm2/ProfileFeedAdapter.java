package com.ishmah.praktikumm2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ProfileFeedAdapter extends RecyclerView.Adapter<ProfileFeedAdapter.ViewHolder> {

    private Context context;
    private List<FeedItem> feedList;

    public ProfileFeedAdapter(Context context, List<FeedItem> feedList) {
        this.context = context;
        this.feedList = feedList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_profile_feed, parent, false);
        int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        int itemWidth = screenWidth / 3;
        view.setLayoutParams(new ViewGroup.LayoutParams(itemWidth, itemWidth));
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FeedItem item = feedList.get(position);
        if (item == null) return;

        // Reset synchronously before Glide loads to prevent stale image on recycle
        holder.ivPost.setImageDrawable(null);
        Glide.with(context).clear(holder.ivPost);

        if (item.getPostImageUri() != null) {
            Glide.with(context)
                    .load(item.getPostImageUri())
                    .centerCrop()
                    .placeholder(android.R.color.darker_gray)
                    .into(holder.ivPost);
        } else {
            Glide.with(context)
                    .load(item.getPostImageRes())
                    .centerCrop()
                    .placeholder(android.R.color.darker_gray)
                    .into(holder.ivPost);
        }

        holder.ivPost.setOnClickListener(v -> {
            Intent intent = new Intent(context, PostDetailActivity.class);
            intent.putExtra("username", item.getUsername());
            intent.putExtra("profile_image_res", item.getProfileImageRes());
            if (item.getPostImageUri() != null) {
                intent.putExtra("post_image_uri", item.getPostImageUri().toString());
            } else {
                intent.putExtra("post_image", item.getPostImageRes());
            }
            intent.putExtra("caption", item.getCaption());
            intent.putExtra("like_count", item.getLikeCount());
            intent.putExtra("comment_count", item.getCommentCount());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return feedList != null ? feedList.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPost;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPost = itemView.findViewById(R.id.iv_profile_post);
        }
    }
}
