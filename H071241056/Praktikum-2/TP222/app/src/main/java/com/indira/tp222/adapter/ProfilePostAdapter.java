package com.indira.tp222.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.indira.tp222.R;
import com.indira.tp222.activity.PostDetailActivity;
import com.indira.tp222.model.Post;

import java.util.List;

public class ProfilePostAdapter extends RecyclerView.Adapter<ProfilePostAdapter.ViewHolder> {

    private Context context;
    private List<Post> postList;

    public ProfilePostAdapter(Context context, List<Post> postList) {
        this.context = context;
        this.postList = postList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_profile_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = postList.get(position);

        // TAMPILKAN GAMBAR dengan prioritas URI
        if (post.hasImageUri()) {
            Glide.with(context)
                    .load(Uri.parse(post.getPostImageUri()))
                    .placeholder(R.drawable.wlpp) //
                    .error(R.drawable.ic_profile_background)
                    .centerCrop()
                    .into(holder.ivPostImage);
        } else {
            holder.ivPostImage.setImageResource(post.getPostImage());
            holder.ivPostImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, PostDetailActivity.class);
            intent.putExtra("POST_IMAGE_URI", post.getPostImageUri());
            intent.putExtra("POST_IMAGE_RES", post.getPostImage());
            intent.putExtra("POST_CAPTION", post.getCaption());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPostImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPostImage = itemView.findViewById(R.id.ivPostImage);
        }
    }
}