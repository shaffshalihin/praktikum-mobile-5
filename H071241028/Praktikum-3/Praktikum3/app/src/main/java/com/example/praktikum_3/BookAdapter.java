package com.example.praktikum_3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
    private List<Book> books;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Book book);
    }

    public BookAdapter(List<Book> books, OnItemClickListener listener) {
        this.books = books;
        this.listener = listener;
    }

    public void updateData(List<Book> newBooks) {
        this.books = newBooks;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        holder.bind(books.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    static class BookViewHolder extends RecyclerView.ViewHolder {
        ImageView ivCover, ivLike;
        TextView tvTitle, tvAuthor, tvGenre, tvPageCount;
        RatingBar ratingBar;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            ivCover = itemView.findViewById(R.id.iv_book_cover);
            ivLike = itemView.findViewById(R.id.iv_like_status);
            tvTitle = itemView.findViewById(R.id.tv_book_title);
            tvAuthor = itemView.findViewById(R.id.tv_book_author);
            tvGenre = itemView.findViewById(R.id.tv_book_genre);
            tvPageCount = itemView.findViewById(R.id.tv_page_count);
            ratingBar = itemView.findViewById(R.id.rating_bar_small);
        }

        public void bind(Book book, OnItemClickListener listener) {
            tvTitle.setText(book.getTitle());
            tvAuthor.setText(book.getAuthor());
            tvGenre.setText(book.getGenre());
            tvPageCount.setText("📖 " + book.getPageCount() + " Pages");
            ratingBar.setRating(book.getRating());

            if (book.getImageUri() != null) {
                ivCover.setImageURI(book.getImageUri());
            } else if (book.getImageResId() != null) {
                ivCover.setImageResource(book.getImageResId());
            }

            ivLike.setImageResource(book.isLiked() ? R.drawable.ic_favorite_filled : R.drawable.ic_favorite_border);

            itemView.setOnClickListener(v -> listener.onItemClick(book));
        }
    }
}
