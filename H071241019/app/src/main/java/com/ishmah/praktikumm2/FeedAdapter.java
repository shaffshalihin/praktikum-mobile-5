package com.ishmah.praktikumm2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {

    private Context context;
    private List<FeedItem> feedList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onProfileClick(FeedItem item);
        void onPostClick(FeedItem item);
        void onLikeClick(FeedItem item);
        void onCommentClick(FeedItem item);
        void onRepostClick(FeedItem item);
        void onSendClick(FeedItem item);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public FeedAdapter(Context context, List<FeedItem> feedList) {
        this.context = context;
        this.feedList = feedList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_feed, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FeedItem item = feedList.get(position);

        holder.tvUsername.setText(item.getUsername());
        holder.tvCaptionUsername.setText(item.getUsername());
        holder.tvCaption.setText(item.getCaption());
        holder.tvLikeCount.setText(item.getLikeCount() + " likes");
        holder.tvCommentCount.setText("Lihat " + item.getCommentCount() + " komentar");
        holder.tvTimeAgo.setText(item.getTimeAgo());

        // Reset like button state on recycle
        holder.ivLikeButton.setColorFilter(ContextCompat.getColor(context, R.color.instagram_dark));
        holder.isLiked = false;

        // Reset synchronously before Glide loads to prevent stale image on recycle
        holder.ivProfile.setImageDrawable(null);
        Glide.with(context).clear(holder.ivProfile);
        Glide.with(context)
                .load(item.getProfileImageRes())
                .circleCrop()
                .into(holder.ivProfile);

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

        holder.ivProfile.setOnClickListener(v -> {
            if (listener != null) listener.onProfileClick(item);
        });

        holder.tvUsername.setOnClickListener(v -> {
            if (listener != null) listener.onProfileClick(item);
        });

        holder.tvCaptionUsername.setOnClickListener(v -> {
            if (listener != null) listener.onProfileClick(item);
        });

        holder.ivPost.setOnClickListener(v -> {
            if (listener != null) listener.onPostClick(item);
        });

        holder.ivLikeButton.setOnClickListener(v -> {
            holder.isLiked = !holder.isLiked;
            if (holder.isLiked) {
                holder.ivLikeButton.setColorFilter(ContextCompat.getColor(context, R.color.instagram_red));
            } else {
                holder.ivLikeButton.setColorFilter(ContextCompat.getColor(context, R.color.instagram_dark));
            }
            if (listener != null) listener.onLikeClick(item);
        });

        holder.ivCommentButton.setOnClickListener(v -> {
            if (listener != null) listener.onCommentClick(item);
        });

        holder.ivRepostButton.setOnClickListener(v -> {
            if (listener != null) listener.onRepostClick(item);
        });

        holder.ivSendButton.setOnClickListener(v -> {
            if (listener != null) listener.onSendClick(item);
        });
    }

    @Override
    public int getItemCount() {
        return feedList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView ivProfile;
        ImageView ivPost;
        ImageView ivLikeButton, ivCommentButton, ivRepostButton, ivSendButton, ivSaveButton;
        TextView tvUsername, tvCaptionUsername, tvCaption, tvLikeCount, tvCommentCount, tvTimeAgo;
        boolean isLiked = false;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfile = itemView.findViewById(R.id.iv_profile_feed);
            tvUsername = itemView.findViewById(R.id.tv_username_feed);
            ivPost = itemView.findViewById(R.id.iv_post_image);
            tvCaptionUsername = itemView.findViewById(R.id.tv_caption_username);
            tvCaption = itemView.findViewById(R.id.tv_caption);
            tvLikeCount = itemView.findViewById(R.id.tv_like_count);
            tvCommentCount = itemView.findViewById(R.id.tv_comment_count);
            tvTimeAgo = itemView.findViewById(R.id.tv_time_ago);
            ivLikeButton = itemView.findViewById(R.id.iv_like_button);
            ivCommentButton = itemView.findViewById(R.id.iv_comment_button);
            ivRepostButton = itemView.findViewById(R.id.iv_repost_button);
            ivSendButton = itemView.findViewById(R.id.iv_send_button);
            ivSaveButton = itemView.findViewById(R.id.iv_save_button);
        }
    }
}
