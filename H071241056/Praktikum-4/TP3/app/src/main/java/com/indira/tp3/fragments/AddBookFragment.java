package com.indira.tp3.fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.indira.tp3.R;
import com.indira.tp3.model.Book;
import com.indira.tp3.utils.DataDummy;

public class AddBookFragment extends Fragment {
    private static final int PICK_IMAGE = 1;
    private EditText etTitle, etAuthor, etYear, etBlurb, etGenre, etRating, etReview;
    private ImageView ivCover;
    private Uri coverUri;
    private Button btnSelectImage, btnSave;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_book, container, false);
        etTitle = view.findViewById(R.id.et_title);
        etAuthor = view.findViewById(R.id.et_author);
        etYear = view.findViewById(R.id.et_year);
        etBlurb = view.findViewById(R.id.et_blurb);
        etGenre = view.findViewById(R.id.et_genre);
        etRating = view.findViewById(R.id.et_rating);
        etReview = view.findViewById(R.id.et_review);
        ivCover = view.findViewById(R.id.iv_cover);
        btnSelectImage = view.findViewById(R.id.btn_select_image);
        btnSave = view.findViewById(R.id.btn_save);

        btnSelectImage.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, PICK_IMAGE);
        });

        btnSave.setOnClickListener(v -> {
            if (etTitle.getText().toString().isEmpty() || etAuthor.getText().toString().isEmpty()) {
                Toast.makeText(getContext(), "Title and Author are required!", Toast.LENGTH_SHORT).show();
                return;
            }

            Book newBook = new Book(
                    etTitle.getText().toString(),
                    etAuthor.getText().toString(),
                    etYear.getText().toString().isEmpty() ? "2024" : etYear.getText().toString(),
                    etBlurb.getText().toString().isEmpty() ? "No description" : etBlurb.getText().toString(),
                    etGenre.getText().toString().isEmpty() ? "General" : etGenre.getText().toString(),
                    etRating.getText().toString().isEmpty() ? "3.0" : etRating.getText().toString(),
                    etReview.getText().toString().isEmpty() ? "No review yet" : etReview.getText().toString(),
                    coverUri,
                    false
            );
            DataDummy.getBooks().add(0, newBook); // Add to top as newest
            Toast.makeText(getContext(), "Book added successfully!", Toast.LENGTH_LONG).show();
            clearForm();
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            coverUri = data.getData();
            ivCover.setImageURI(coverUri);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void clearForm() {
        etTitle.setText("");
        etAuthor.setText("");
        etYear.setText("");
        etBlurb.setText("");
        etGenre.setText("");
        etRating.setText("");
        etReview.setText("");
        ivCover.setImageResource(R.drawable.ic_book_placeholder);
        coverUri = null;
    }
}