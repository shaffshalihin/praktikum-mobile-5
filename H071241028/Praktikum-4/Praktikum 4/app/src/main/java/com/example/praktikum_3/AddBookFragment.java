package com.example.praktikum_3;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddBookFragment extends Fragment {
    private ImageView ivCoverPreview;
    private EditText etTitle, etAuthor, etYear, etBlurb, etPages;
    private AutoCompleteTextView etGenre;
    private FloatingActionButton btnSelectImage;
    private MaterialButton btnSave;
    private Uri selectedImageUri = null;

    private final String[] GENRES = {
            "Fiction", "Dystopian", "Classic", "Romance", "Fantasy",
            "Adventure", "Satire", "Philosophical", "Thriller", "Non-fiction"
    };

    private final ActivityResultLauncher<Intent> selectImageLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    selectedImageUri = result.getData().getData();
                    ivCoverPreview.setImageURI(selectedImageUri);
                }
            }
    );

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_book, container, false);

        // Inisialisasi View
        ivCoverPreview = view.findViewById(R.id.iv_cover_preview);
        etTitle = view.findViewById(R.id.et_title);
        etAuthor = view.findViewById(R.id.et_author);
        etYear = view.findViewById(R.id.et_year);
        etGenre = view.findViewById(R.id.et_genre);
        etBlurb = view.findViewById(R.id.et_blurb);
        etPages = view.findViewById(R.id.et_pages);
        
        btnSelectImage = view.findViewById(R.id.btn_select_image);
        btnSave = view.findViewById(R.id.btn_save);

        // Setup Dropdown Genre
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_dropdown_item_1line, GENRES);
        etGenre.setAdapter(adapter);

        btnSelectImage.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            selectImageLauncher.launch(intent);
        });

        btnSave.setOnClickListener(v -> saveBook());

        return view;
    }

    private void saveBook() {
        String title = etTitle.getText().toString().trim();
        String author = etAuthor.getText().toString().trim();
        String yearStr = etYear.getText().toString().trim();
        String genre = etGenre.getText().toString().trim();
        String blurb = etBlurb.getText().toString().trim();
        String pagesStr = etPages.getText().toString().trim();

        if (title.isEmpty() || author.isEmpty() || yearStr.isEmpty() || genre.isEmpty() || blurb.isEmpty() || pagesStr.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int year = Integer.parseInt(yearStr);
            int pages = Integer.parseInt(pagesStr);
            int nextId = BookRepository.getBooks().size() + 1;

            Book newBook = new Book(nextId, title, author, year, blurb, selectedImageUri, null, genre, 0.0f, pages);
            BookRepository.addBook(newBook);

            Toast.makeText(requireContext(), "Book added successfully", Toast.LENGTH_SHORT).show();
            clearFields();
        } catch (NumberFormatException e) {
            Toast.makeText(requireContext(), "Year and Pages must be numbers", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearFields() {
        etTitle.setText("");
        etAuthor.setText("");
        etYear.setText("");
        etGenre.setText("");
        etBlurb.setText("");
        etPages.setText("");
        ivCoverPreview.setImageResource(R.drawable.book_placeholder);
        selectedImageUri = null;
    }
}
