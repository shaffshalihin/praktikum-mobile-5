package com.ishmah.praktikum3.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.ishmah.praktikum3.R;
import com.ishmah.praktikum3.data.BookRepository;
import com.ishmah.praktikum3.model.Book;

public class AddBookFragment extends Fragment {

    private ImageView imgPreview;
    private View layoutNoCover;
    private TextInputEditText etTitle, etAuthor, etYear, etBlurb, etReview;
    private AutoCompleteTextView spinnerGenre;
    private RatingBar ratingBar;
    private Uri selectedImageUri = null;

    private ActivityResultLauncher<Intent> galleryLauncher;

    private static final String[] GENRES = {
        "Picture Books", "First Books", "See to Read", "Self Development",
        "Productivity", "Finance", "History", "Biography", "Thriller",
        "Fiction", "Psychology"
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        galleryLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    Uri uri = result.getData().getData();
                    if (uri != null) {
                        selectedImageUri = uri;
                        imgPreview.setImageURI(uri);
                        imgPreview.setVisibility(View.VISIBLE);
                        layoutNoCover.setVisibility(View.GONE);
                    }
                }
            }
        );
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_book, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imgPreview    = view.findViewById(R.id.img_preview);
        layoutNoCover = view.findViewById(R.id.layout_no_cover);
        etTitle       = view.findViewById(R.id.et_title);
        etAuthor      = view.findViewById(R.id.et_author);
        etYear        = view.findViewById(R.id.et_year);
        etBlurb       = view.findViewById(R.id.et_blurb);
        etReview      = view.findViewById(R.id.et_review);
        spinnerGenre  = view.findViewById(R.id.spinner_genre);
        ratingBar     = view.findViewById(R.id.rating_bar);

        MaterialButton btnPickImage = view.findViewById(R.id.btn_pick_image);
        MaterialButton btnSave      = view.findViewById(R.id.btn_save);

        ArrayAdapter<String> genreAdapter = new ArrayAdapter<>(
            requireContext(), android.R.layout.simple_dropdown_item_1line, GENRES);
        spinnerGenre.setAdapter(genreAdapter);

        btnPickImage.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            galleryLauncher.launch(intent);
        });

        btnSave.setOnClickListener(v -> saveBook());
    }

    private void saveBook() {
        String title   = text(etTitle);
        String author  = text(etAuthor);
        String yearStr = text(etYear);
        String genre   = spinnerGenre.getText().toString().trim();
        String blurb   = text(etBlurb);
        String review  = text(etReview);
        float  rating  = ratingBar.getRating();

        if (title.isEmpty() || author.isEmpty() || yearStr.isEmpty()
                || genre.isEmpty() || blurb.isEmpty()) {
            Toast.makeText(requireContext(),
                "Mohon lengkapi field: Judul, Penulis, Tahun, Genre, dan Sinopsis!",
                Toast.LENGTH_SHORT).show();
            return;
        }

        int year;
        try {
            year = Integer.parseInt(yearStr);
            if (year < 1000 || year > 2100) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            Toast.makeText(requireContext(), "Tahun terbit tidak valid!", Toast.LENGTH_SHORT).show();
            return;
        }

        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setYear(year);
        book.setGenre(genre);
        book.setBlurb(blurb);
        book.setReview(review.isEmpty() ? "-" : review);
        book.setRating(rating == 0f ? 3.0f : rating);
        book.setCoverResId(R.drawable.cover_placeholder_1);
        if (selectedImageUri != null) {
            book.setCoverUri(selectedImageUri.toString());
        }

        BookRepository.getInstance().addBook(book);
        Toast.makeText(requireContext(), "\"" + title + "\" berhasil ditambahkan!", Toast.LENGTH_LONG).show();

        resetForm();
    }

    private void resetForm() {
        etTitle.setText("");
        etAuthor.setText("");
        etYear.setText("");
        spinnerGenre.setText("", false);
        etBlurb.setText("");
        etReview.setText("");
        ratingBar.setRating(0f);
        selectedImageUri = null;
        imgPreview.setImageDrawable(null);
        imgPreview.setVisibility(View.GONE);
        layoutNoCover.setVisibility(View.VISIBLE);
    }

    private String text(TextInputEditText et) {
        return et.getText() != null ? et.getText().toString().trim() : "";
    }
}
