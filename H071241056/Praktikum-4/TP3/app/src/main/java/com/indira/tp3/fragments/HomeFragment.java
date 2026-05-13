package com.indira.tp3.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Spinner;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.indira.tp3.R;
import com.indira.tp3.activities.DetailActivity;
import com.indira.tp3.adapter.BookAdapter;
import com.indira.tp3.model.Book;
import com.indira.tp3.utils.DataDummy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private BookAdapter adapter;
    private List<Book> allBooks;
    private List<Book> currentDisplayList;
    private Spinner spinnerGenre;
    private SearchView searchView;
    private ProgressBar progressBar;
    private String currentGenreFilter = "All";
    private String currentSearchQuery = "";
    
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        spinnerGenre = view.findViewById(R.id.spinner_genre);
        searchView = view.findViewById(R.id.searchView);
        progressBar = view.findViewById(R.id.progressBar);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        allBooks = DataDummy.getBooks();
        // Sort by year descending (newest first)
        allBooks.sort((b1, b2) -> b2.getYear().compareTo(b1.getYear()));
        currentDisplayList = new ArrayList<>(allBooks);

        adapter = new BookAdapter(currentDisplayList, book -> {
            DetailActivity.start(getContext(), book);
        });
        recyclerView.setAdapter(adapter);

        setupGenreSpinner();
        setupSearchView();

        return view;
    }

    private void setupGenreSpinner() {
        List<String> genres = allBooks.stream()
                .map(Book::getGenre)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        genres.add(0, "All");

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, genres);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGenre.setAdapter(spinnerAdapter);

        spinnerGenre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentGenreFilter = genres.get(position);
                applyFilters();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                currentGenreFilter = "All";
                applyFilters();
            }
        });
    }

    private void setupSearchView() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                currentSearchQuery = query;
                applyFilters();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                currentSearchQuery = newText;
                applyFilters();
                return true;
            }
        });
    }

    private void applyFilters() {
        progressBar.setVisibility(View.VISIBLE);
        
        executorService.execute(() -> {
            try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }

            List<Book> filtered = new ArrayList<>(allBooks);

            if (!currentGenreFilter.equals("All")) {
                filtered = filtered.stream()
                        .filter(book -> book.getGenre().equalsIgnoreCase(currentGenreFilter))
                        .collect(Collectors.toList());
            }

            if (!currentSearchQuery.isEmpty()) {
                String query = currentSearchQuery.toLowerCase();
                filtered = filtered.stream()
                        .filter(book -> book.getTitle().toLowerCase().contains(query))
                        .collect(Collectors.toList());
            }

            final List<Book> result = filtered;
            mainHandler.post(() -> {
                currentDisplayList = result;
                adapter.updateList(currentDisplayList);
                progressBar.setVisibility(View.GONE);
            });
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        executorService.shutdown();
    }
}
