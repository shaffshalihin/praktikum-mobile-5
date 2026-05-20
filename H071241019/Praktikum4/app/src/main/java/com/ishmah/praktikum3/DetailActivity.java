package com.ishmah.praktikum3;

import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.ishmah.praktikum3.data.BookRepository;
import com.ishmah.praktikum3.model.Book;

public class DetailActivity extends AppCompatActivity {

    private Book book;
    private ImageView imgLike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        int bookId = getIntent().getIntExtra("book_id", -1);
        for (Book b : BookRepository.getInstance().getAllBooks()) {
            if (b.getId() == bookId) {
                book = b;
                break;
            }
        }
        if (book == null) {
            finish();
            return;
        }
        bindViews();
    }

    private void bindViews() {
        ImageView imgCover = findViewById(R.id.detail_cover);
        TextView tvTitle = findViewById(R.id.detail_title);
        TextView tvAuthor = findViewById(R.id.detail_author);
        TextView tvYear = findViewById(R.id.detail_year);
        TextView tvGenre = findViewById(R.id.detail_genre);
        TextView tvBlurb = findViewById(R.id.detail_blurb);
        TextView tvReview = findViewById(R.id.detail_review);
        TextView tvRatingNum = findViewById(R.id.detail_rating_num);
        RatingBar ratingBar = findViewById(R.id.detail_rating_bar);
        imgLike = findViewById(R.id.detail_like);
        ImageView btnBack = findViewById(R.id.btn_back);

        if (book.getCoverUri() != null && !book.getCoverUri().isEmpty()) {
            Glide.with(this)
                    .load(book.getCoverUri())
                    .placeholder(book.getCoverResId())
                    .error(book.getCoverResId())
                    .centerCrop()
                    .into(imgCover);
        } else {
            Glide.with(this)
                    .load(book.getCoverResId())
                    .centerCrop()
                    .into(imgCover);
        }

        tvTitle.setText(book.getTitle());
        tvAuthor.setText("by " + book.getAuthor());
        tvYear.setText(String.valueOf(book.getYear()));
        tvGenre.setText(book.getGenre());
        tvBlurb.setText(book.getBlurb());
        tvReview.setText("\"" + book.getReview() + "\"");
        tvRatingNum.setText(String.format("%.1f / 5.0", book.getRating()));
        ratingBar.setRating(book.getRating());

        updateLikeIcon();

        imgLike.setOnClickListener(v -> {
            BookRepository.getInstance().toggleLike(book.getId());
            updateLikeIcon();
            imgLike.startAnimation(AnimationUtils.loadAnimation(this, R.anim.bounce));
        });

        btnBack.setOnClickListener(v -> finish());
    }

    private void updateLikeIcon() {
        imgLike.setImageResource(book.isLiked() ? R.drawable.ic_favorite : R.drawable.ic_favorite_border);
    }
}