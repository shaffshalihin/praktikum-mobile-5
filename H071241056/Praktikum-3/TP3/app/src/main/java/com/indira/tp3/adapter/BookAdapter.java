package com.indira.tp3.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.indira.tp3.R;
import com.indira.tp3.model.Book;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
    private List<Book> books;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Book book);
    }

    public BookAdapter(List<Book> books, OnItemClickListener listener) {
        this.books = books;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Book book = books.get(position);
        holder.title.setText(book.getTitle());
        holder.author.setText(book.getAuthor());
        holder.year.setText(book.getYear());

        // Show like status
        if (book.isLiked()) {
            holder.likeIndicator.setImageResource(R.drawable.ic_favorite);
        } else {
            holder.likeIndicator.setImageResource(R.drawable.ic_favorite_border);
        }

        // Set cover image if available
        if (book.getCoverImageUri() != null) {
            holder.coverImage.setImageURI(book.getCoverImageUri());
        }

        holder.itemView.setOnClickListener(v -> listener.onItemClick(book));
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void updateList(List<Book> newList) {
        this.books = newList;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, author, year;
        ImageView likeIndicator, coverImage;

        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title);
            author = itemView.findViewById(R.id.tv_author);
            year = itemView.findViewById(R.id.tv_year);
            likeIndicator = itemView.findViewById(R.id.iv_like_indicator);
            coverImage = itemView.findViewById(R.id.iv_cover_small);
        }
    }
}