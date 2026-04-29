package com.example.praktikum_3;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.button.MaterialButton;
import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    private ImageView ivCover, btnBack, btnLikeIcon;
    private TextView tvTitle, tvAuthor, tvGenre, tvBlurb;
    private RatingBar ratingBar;
    private MaterialButton btnRead;
    private RecyclerView rvRecommendations;
    private Book currentBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ivCover = findViewById(R.id.iv_detail_cover);
        tvTitle = findViewById(R.id.tv_detail_title);
        tvAuthor = findViewById(R.id.tv_detail_author);
        tvGenre = findViewById(R.id.tv_detail_genre);
        tvBlurb = findViewById(R.id.tv_detail_blurb);
        ratingBar = findViewById(R.id.rating_bar);
        btnRead = findViewById(R.id.btn_read);
        btnBack = findViewById(R.id.btn_back);
        btnLikeIcon = findViewById(R.id.btn_like_icon);
        rvRecommendations = findViewById(R.id.rv_recommendations);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            currentBook = getIntent().getParcelableExtra("BOOK_DATA", Book.class);
        } else {
            currentBook = getIntent().getParcelableExtra("BOOK_DATA");
        }

        if (currentBook != null) {
            // Find reference in Repo for global state
            for (Book b : BookRepository.getBooks()) {
                if (b.getId() == currentBook.getId()) {
                    currentBook = b;
                    break;
                }
            }
            displayBookDetails(currentBook);
            setupRecommendations();
        }

        btnBack.setOnClickListener(v -> finish());
        
        btnLikeIcon.setOnClickListener(v -> {
            currentBook.setLiked(!currentBook.isLiked());
            updateLikeIcon(currentBook.isLiked());
        });
    }

    private void displayBookDetails(Book book) {
        tvTitle.setText(book.getTitle());
        tvAuthor.setText(book.getAuthor());
        tvGenre.setText(book.getGenre());
        tvBlurb.setText(book.getBlurb());
        ratingBar.setRating(book.getRating());

        if (book.getImageUri() != null) {
            ivCover.setImageURI(book.getImageUri());
        } else if (book.getImageResId() != null) {
            ivCover.setImageResource(book.getImageResId());
        }

        updateLikeIcon(book.isLiked());
    }

    private void updateLikeIcon(boolean isLiked) {
        // PERBAIKAN: Gunakan drawable hati (heart) dari project, bukan bintang bawaan Android
        btnLikeIcon.setImageResource(isLiked ? R.drawable.ic_favorite_filled : R.drawable.ic_favorite_border);
    }

    private void setupRecommendations() {
        List<Book> allBooks = BookRepository.getBooks();
        List<Book> recommendations = new ArrayList<>();
        for (Book b : allBooks) {
            if (b.getId() != currentBook.getId() && b.getGenre().equals(currentBook.getGenre())) {
                recommendations.add(b);
            }
        }
        
        // If no similar genre, show some other books
        if (recommendations.isEmpty()) {
            for (int i = 0; i < Math.min(allBooks.size(), 5); i++) {
                if (allBooks.get(i).getId() != currentBook.getId()) {
                    recommendations.add(allBooks.get(i));
                }
            }
        }

        ContinueReadingAdapter adapter = new ContinueReadingAdapter(recommendations, book -> {
            // Re-open detail for recommended book
            displayBookDetails(book);
            currentBook = book;
            setupRecommendations(); // Refresh recommendations for the new book
        });
        
        rvRecommendations.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvRecommendations.setAdapter(adapter);
    }
}
