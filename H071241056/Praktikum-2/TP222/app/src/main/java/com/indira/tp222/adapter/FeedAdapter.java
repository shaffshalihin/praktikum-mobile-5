package com.indira.tp222.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.indira.tp222.R;
import com.indira.tp222.activity.OtherUserProfileActivity;
import com.indira.tp222.activity.ProfileActivity;
import com.indira.tp222.model.Post;

import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {

    private Context context;
    private List<Post> postList;

    public FeedAdapter(Context context, List<Post> postList) {
        this.context = context;
        this.postList = postList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = postList.get(position);

        // Set data ke view
        holder.ivProfile.setImageResource(post.getProfileImage());
        holder.tvUsername.setText(post.getUsername());
        holder.tvUsernameCaption.setText(post.getUsername());
        holder.ivPost.setImageResource(post.getPostImage());
        holder.tvCaption.setText(post.getCaption());
        holder.tvLikes.setText(formatLikes(post.getLikes()));
        holder.tvTimeAgo.setText(post.getTimeAgo());

        // Set like button status
        if (post.isLiked()) {
            holder.ivLike.setImageResource(R.drawable.heart_filled);
        } else {
            holder.ivLike.setImageResource(R.drawable.heart_outline);
        }

        // Like button
        holder.ivLike.setOnClickListener(v -> {
            if (post.isLiked()) {
                post.setLiked(false);
                post.setLikes(post.getLikes() - 1);
                holder.ivLike.setImageResource(R.drawable.heart_outline);
            } else {
                post.setLiked(true);
                post.setLikes(post.getLikes() + 1);
                holder.ivLike.setImageResource(R.drawable.heart_filled);
            }
            holder.tvLikes.setText(formatLikes(post.getLikes()));
            Toast.makeText(context, post.isLiked() ? "Liked!" : "Unliked", Toast.LENGTH_SHORT).show();
        });

        // Klik ke profile (foto profile)
        holder.ivProfile.setOnClickListener(v -> {
            Intent intent = new Intent(context, OtherUserProfileActivity.class); // UBAH INI
            intent.putExtra("USERNAME", post.getUsername());
            context.startActivity(intent);
        });

        // Klik ke profile (username)
        holder.tvUsername.setOnClickListener(v -> {
            Intent intent = new Intent(context, OtherUserProfileActivity.class); // UBAH INI
            intent.putExtra("USERNAME", post.getUsername());
            context.startActivity(intent);
        });

        // Klik ke profile (username di caption)
        holder.tvUsernameCaption.setOnClickListener(v -> {
            Intent intent = new Intent(context, OtherUserProfileActivity.class); // UBAH INI
            intent.putExtra("USERNAME", post.getUsername());
            context.startActivity(intent);
        });

    }

    private String formatLikes(int likes) {
        if (likes >= 1000) {
            return (likes / 1000) + "." + ((likes % 1000) / 100) + "K likes";
        }
        return likes + " likes";
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivProfile, ivPost, ivLike, ivComment, ivShare, ivSave;
        TextView tvUsername, tvUsernameCaption, tvCaption, tvLikes, tvTimeAgo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfile = itemView.findViewById(R.id.navProfileImg);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvUsernameCaption = itemView.findViewById(R.id.tvUsernameCaption);
            ivPost = itemView.findViewById(R.id.ivPost);
            ivLike = itemView.findViewById(R.id.ivLike);
            ivComment = itemView.findViewById(R.id.ivComment);
            ivShare = itemView.findViewById(R.id.ivShare);
            ivSave = itemView.findViewById(R.id.ivSave);
            tvCaption = itemView.findViewById(R.id.tvCaption);
            tvLikes = itemView.findViewById(R.id.tvLikes);
            tvTimeAgo = itemView.findViewById(R.id.tvTimeAgo);
        }
    }
}