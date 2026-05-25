package com.indira.tp3.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.indira.tp3.R;
import com.indira.tp3.model.Book;
import com.indira.tp3.utils.DataDummy;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DetailActivity extends AppCompatActivity {
    private static final String EXTRA_BOOK_TITLE = "book_title";
    private Book book;
    private Button btnLike;

    public static void start(Context context, Book book) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(EXTRA_BOOK_TITLE, book.getTitle());
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        String title = getIntent().getStringExtra(EXTRA_BOOK_TITLE);
        if (title == null) {
            Toast.makeText(this, "Error loading book", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Find the book by title
        book = null;
        for (Book b : DataDummy.getBooks()) {
            if (b.getTitle().equals(title)) {
                book = b;
                break;
            }
        }

        if (book == null) {
            Toast.makeText(this, "Book not found", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        TextView tvTitle = findViewById(R.id.tv_detail_title);
        TextView tvAuthor = findViewById(R.id.tv_detail_author);
        TextView tvYear = findViewById(R.id.tv_detail_year);
        TextView tvBlurb = findViewById(R.id.tv_detail_blurb);
        TextView tvGenre = findViewById(R.id.tv_detail_genre);
        TextView tvRating = findViewById(R.id.tv_detail_rating);
        TextView tvReview = findViewById(R.id.tv_detail_review);
        ImageView ivCover = findViewById(R.id.iv_detail_cover);
        btnLike = findViewById(R.id.btn_like);

        tvTitle.setText(book.getTitle());
        tvAuthor.setText("by " + book.getAuthor());
        tvYear.setText("Published: " + book.getYear());
        tvBlurb.setText(book.getBlurb());
        tvGenre.setText("Genre: " + book.getGenre());
        tvRating.setText("Rating: " + book.getRating() + " / 5.0");
        tvReview.setText("Review: " + book.getReview());

        // Handle cover image safely
        if (book.getCoverImageUri() != null) {
            try {
                ivCover.setImageURI(book.getCoverImageUri());
            } catch (Exception e) {
                ivCover.setImageResource(R.drawable.ic_book_placeholder);
            }
        } else {
            ivCover.setImageResource(R.drawable.ic_book_placeholder);
        }

        updateLikeButton();

        btnLike.setOnClickListener(v -> {
            book.setLiked(!book.isLiked());
            updateLikeButton();
            Toast.makeText(this, book.isLiked() ? "Added to Favorites" : "Removed from Favorites", Toast.LENGTH_SHORT).show();
        });

        // Setup Bottom Navigation in Detail
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home || itemId == R.id.nav_favorites || itemId == R.id.nav_add) {
                // Return to MainActivity and let it handle fragment switching
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtra("TARGET_FRAGMENT", itemId);
                startActivity(intent);
                finish();
                return true;
            }
            return false;
        });
    }

    private void updateLikeButton() {
        if (book.isLiked()) {
            btnLike.setText("❤️");
            btnLike.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
        } else {
            btnLike.setText("Like");
            btnLike.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
        }
    }
}
