package com.ishmah.praktikum3.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.ishmah.praktikum3.R;
import com.ishmah.praktikum3.adapter.BookAdapter;
import com.ishmah.praktikum3.data.BookRepository;
import com.ishmah.praktikum3.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HomeFragment extends Fragment {

    private BookAdapter adapter;
    private RecyclerView recyclerView;
    private View progressSearch;
    private String activeGenre = "All";

    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final Handler handler = new Handler(Looper.getMainLooper());

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView   = view.findViewById(R.id.recycler_books);
        progressSearch = view.findViewById(R.id.progress_search);

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new BookAdapter(requireContext(), BookRepository.getInstance().getAllBooks());
        recyclerView.setAdapter(adapter);

        EditText etSearch = view.findViewById(R.id.et_search);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void afterTextChanged(Editable s) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                final String query = s.toString().trim();
                final String genre = activeGenre;

                // Tampilkan loading, sembunyikan list
                progressSearch.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);

                executor.execute(() -> {
                    try { Thread.sleep(500); } catch (InterruptedException ignored) {}

                    // Filter di background thread
                    List<Book> all = BookRepository.getInstance().getAllBooks();
                    List<Book> result = new ArrayList<>();
                    if (query.isEmpty()) {
                        for (Book b : all) {
                            if (genre.equals("All") || b.getGenre().equals(genre)) result.add(b);
                        }
                    } else {
                        for (Book b : all) {
                            if (b.getTitle().toLowerCase().contains(query.toLowerCase())) result.add(b);
                        }
                    }

                    final List<Book> filtered = result;
                    handler.post(() -> {
                        // Update UI di Main Thread
                        adapter.updateList(filtered);
                        progressSearch.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    });
                });
            }
        });

        ChipGroup chipGroup = view.findViewById(R.id.chip_group_genres);
        chipGroup.setOnCheckedStateChangeListener((group, checkedIds) -> {
            if (checkedIds.isEmpty()) {
                activeGenre = "All";
            } else {
                Chip chip = group.findViewById(checkedIds.get(0));
                activeGenre = chip != null ? chip.getText().toString() : "All";
            }

            final String genre       = activeGenre;
            final String searchQuery = etSearch.getText().toString().trim();

            progressSearch.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);

            executor.execute(() -> {
                try { Thread.sleep(500); } catch (InterruptedException ignored) {}

                List<Book> all = BookRepository.getInstance().getAllBooks();
                List<Book> result = new ArrayList<>();
                if (searchQuery.isEmpty()) {
                    for (Book b : all) {
                        if (genre.equals("All") || b.getGenre().equals(genre)) result.add(b);
                    }
                } else {
                    for (Book b : all) {
                        if (b.getTitle().toLowerCase().contains(searchQuery.toLowerCase())) result.add(b);
                    }
                }

                final List<Book> filtered = result;
                handler.post(() -> {
                    adapter.updateList(filtered);
                    progressSearch.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                });
            });
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (adapter != null) {
            adapter.updateList(BookRepository.getInstance().getAllBooks());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        executor.shutdown();
    }
}
