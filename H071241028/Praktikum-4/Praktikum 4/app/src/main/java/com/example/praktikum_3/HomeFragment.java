package com.example.praktikum_3;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HomeFragment extends Fragment {
    private BookAdapter adapter;
    private RecyclerView rvBooks, rvContinueReading;
    private SearchView searchView;
    private ChipGroup chipGroupGenres;
    
    // --- PENAMBAHAN TUGAS BACKGROUND THREAD ---
    private ProgressBar progressBar;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final Handler handler = new Handler(Looper.getMainLooper());
    // -------------------------------------------

    private String currentGenre = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        
        rvBooks = view.findViewById(R.id.rv_books);
        rvContinueReading = view.findViewById(R.id.rv_continue_reading);
        searchView = view.findViewById(R.id.search_view);
        chipGroupGenres = view.findViewById(R.id.chip_group_genres);
        
        // --- PENAMBAHAN TUGAS BACKGROUND THREAD ---
        progressBar = view.findViewById(R.id.progress_bar);
        // -------------------------------------------

        setupRecyclerViews();
        setupSearchView();
        setupGenreFilter();
        
        return view;
    }

    private void setupRecyclerViews() {
        adapter = new BookAdapter(new ArrayList<>(), this::openDetail);
        rvBooks.setLayoutManager(new LinearLayoutManager(requireContext()));
        rvBooks.setAdapter(adapter);

        ContinueReadingAdapter continueReadingAdapter = new ContinueReadingAdapter(BookRepository.getContinueReadingBooks(), this::openDetail);
        rvContinueReading.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        rvContinueReading.setAdapter(continueReadingAdapter);
        
        // --- PENAMBAHAN TUGAS BACKGROUND THREAD ---
        loadInitialData();
        // -------------------------------------------
    }

    // --- PENAMBAHAN TUGAS BACKGROUND THREAD ---
    private void loadInitialData() {
        progressBar.setVisibility(View.VISIBLE);
        executor.execute(() -> {
            List<Book> books = BookRepository.getBooks();
            try {
                Thread.sleep(500); // Simulasi proses loading
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            handler.post(() -> {
                adapter.updateData(books);
                progressBar.setVisibility(View.GONE);
            });
        });
    }
    // -------------------------------------------

    private void openDetail(Book book) {
        Intent intent = new Intent(requireContext(), DetailActivity.class);
        intent.putExtra("BOOK_DATA", book);
        startActivity(intent);
    }

    private void setupSearchView() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) { return false; }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterBooks(newText, currentGenre);
                return true;
            }
        });
    }

    private void setupGenreFilter() {
        List<Book> allBooks = BookRepository.getBooks();
        Set<String> genreSet = new HashSet<>();
        for (Book book : allBooks) {
            genreSet.add(book.getGenre());
        }
        List<String> genres = new ArrayList<>(genreSet);
        Collections.sort(genres);

        chipGroupGenres.removeAllViews();
        addGenreChip("Popular", null, true);

        for (String genre : genres) {
            addGenreChip(genre, genre, false);
        }
    }

    private void addGenreChip(String label, String genreValue, boolean isChecked) {
        Chip chip = new Chip(requireContext());
        chip.setText(label);
        chip.setCheckable(true);
        chip.setChecked(isChecked);
        
        updateChipStyle(chip);
        
        chip.setOnCheckedChangeListener((buttonView, isCheckedNow) -> {
            if (isCheckedNow) {
                currentGenre = genreValue;
                filterBooks(searchView.getQuery().toString(), genreValue);
                
                for (int i = 0; i < chipGroupGenres.getChildCount(); i++) {
                    updateChipStyle((Chip) chipGroupGenres.getChildAt(i));
                }
            }
        });
        chipGroupGenres.addView(chip);
    }

    private void updateChipStyle(Chip chip) {
        if (chip.isChecked()) {
            chip.setChipBackgroundColorResource(R.color.orange_primary);
            chip.setTextColor(ContextCompat.getColor(requireContext(), R.color.text_white));
        } else {
            chip.setChipBackgroundColorResource(R.color.card_dark);
            chip.setTextColor(ContextCompat.getColor(requireContext(), R.color.text_gray));
        }
    }

    // --- PENAMBAHAN TUGAS BACKGROUND THREAD ---
    private void filterBooks(String query, String genre) {
        progressBar.setVisibility(View.VISIBLE);
        executor.execute(() -> {
            List<Book> allBooks = BookRepository.getBooks();
            List<Book> filteredList = new ArrayList<>();

            for (Book book : allBooks) {
                boolean matchesQuery = query == null || query.isEmpty() || 
                                     book.getTitle().toLowerCase().contains(query.toLowerCase());
                boolean matchesGenre = genre == null || book.getGenre().equals(genre);

                if (matchesQuery && matchesGenre) {
                    filteredList.add(book);
                }
            }
            
            try {
                Thread.sleep(300); // Simulasi delay pencarian
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            handler.post(() -> {
                adapter.updateData(filteredList);
                progressBar.setVisibility(View.GONE);
            });
        });
    }
    // -------------------------------------------

    @Override
    public void onResume() {
        super.onResume();
        // --- PENAMBAHAN TUGAS BACKGROUND THREAD ---
        loadInitialData();
        // -------------------------------------------
    }

    // --- PENAMBAHAN TUGAS BACKGROUND THREAD ---
    @Override
    public void onDestroy() {
        super.onDestroy();
        executor.shutdown();
    }
    // -------------------------------------------
}
