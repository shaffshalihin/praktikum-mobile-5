package com.ishmah.praktikum3.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ishmah.praktikum3.DetailActivity;
import com.ishmah.praktikum3.R;
import com.ishmah.praktikum3.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private final Context context;
    private List<Book> bookList;
    private List<Book> fullList;
    private int lastPosition = -1;

    public BookAdapter(Context context, List<Book> bookList) {
        this.context = context;
        this.bookList = new ArrayList<>(bookList);
        this.fullList = new ArrayList<>(bookList);
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_book, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = bookList.get(position);

        holder.tvTitle.setText(book.getTitle());
        holder.tvAuthor.setText(book.getAuthor());
        holder.tvGenre.setText(book.getGenre());
        holder.tvYear.setText(String.valueOf(book.getYear()));
        holder.tvRating.setText(String.format("★ %.1f", book.getRating()));

        if (book.getCoverUri() != null && !book.getCoverUri().isEmpty()) {
            Glide.with(context)
                    .load(book.getCoverUri())
                    .placeholder(book.getCoverResId())
                    .error(book.getCoverResId())
                    .centerCrop()
                    .into(holder.imgCover);
        } else {
            Glide.with(context)
                    .load(book.getCoverResId())
                    .centerCrop()
                    .into(holder.imgCover);
        }

        holder.imgLike.setImageResource(book.isLiked() ? R.drawable.ic_favorite : R.drawable.ic_favorite_border);

        setGenreChipStyle(holder.tvGenre, book.getGenre());

        holder.cardView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("book_id", book.getId());
            context.startActivity(intent);
        });

        holder.imgLike.setOnClickListener(v -> {
            book.setLiked(!book.isLiked());
            holder.imgLike.setImageResource(book.isLiked() ? R.drawable.ic_favorite : R.drawable.ic_favorite_border);
            holder.imgLike.startAnimation(AnimationUtils.loadAnimation(context, R.anim.bounce));
        });

        setAnimation(holder.cardView, position);
    }

    private void setAnimation(View view, int position) {
        if (position > lastPosition) {
            view.setAlpha(0f);
            view.setTranslationY(60f);
            view.animate()
                    .alpha(1f)
                    .translationY(0f)
                    .setDuration(400)
                    .setStartDelay(position * 50L)
                    .start();
            lastPosition = position;
        }
    }

    private void setGenreChipStyle(TextView tvGenre, String genre) {
        int bgColor;
        switch (genre) {
            case "Picture Books":    bgColor = 0xFFFF6B9D; break;
            case "First Books":      bgColor = 0xFF6BCB77; break;
            case "See to Read":      bgColor = 0xFF4D96FF; break;
            case "Self Development": bgColor = 0xFFFF9F43; break;
            case "Productivity":     bgColor = 0xFF48DBFB; break;
            case "Finance":          bgColor = 0xFF1DD1A1; break;
            case "History":          bgColor = 0xFFEE5A24; break;
            case "Biography":        bgColor = 0xFF9C88FF; break;
            case "Thriller":         bgColor = 0xFFFF5252; break;
            case "Fiction":          bgColor = 0xFFFECA57; break;
            case "Psychology":       bgColor = 0xFFFF6348; break;
            default:                 bgColor = 0xFF8395A7; break;
        }
        tvGenre.setBackgroundTintList(android.content.res.ColorStateList.valueOf(bgColor));
    }

    public void filter(String query) {
        List<Book> filtered = new ArrayList<>();
        for (Book b : fullList) {
            if (b.getTitle().toLowerCase().contains(query.toLowerCase())) filtered.add(b);
        }
        bookList = filtered;
        lastPosition = -1;
        notifyDataSetChanged();
    }

    public void filterByGenre(String genre) {
        List<Book> filtered = new ArrayList<>();
        for (Book b : fullList) {
            if (genre.equals("All") || b.getGenre().equals(genre)) filtered.add(b);
        }
        bookList = filtered;
        lastPosition = -1;
        notifyDataSetChanged();
    }

    public void updateList(List<Book> newList) {
        fullList = new ArrayList<>(newList);
        bookList = new ArrayList<>(newList);
        lastPosition = -1;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    static class BookViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView imgCover, imgLike;
        TextView tvTitle, tvAuthor, tvGenre, tvYear, tvRating;

        BookViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_book);
            imgCover = itemView.findViewById(R.id.img_cover);
            imgLike = itemView.findViewById(R.id.img_like);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvAuthor = itemView.findViewById(R.id.tv_author);
            tvGenre = itemView.findViewById(R.id.tv_genre);
            tvYear = itemView.findViewById(R.id.tv_year);
            tvRating = itemView.findViewById(R.id.tv_rating);
        }
    }
}