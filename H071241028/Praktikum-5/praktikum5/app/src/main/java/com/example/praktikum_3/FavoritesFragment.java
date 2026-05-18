package com.example.praktikum_3;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FavoritesFragment extends Fragment {
    private BookAdapter adapter;
    private RecyclerView rvFavorites;
    private TextView tvEmpty;

    // --- PENAMBAHAN TUGAS BACKGROUND THREAD ---
    private ProgressBar progressBar;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final Handler handler = new Handler(Looper.getMainLooper());
    // -------------------------------------------

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        
        rvFavorites = view.findViewById(R.id.rv_favorites);
        tvEmpty = view.findViewById(R.id.tv_empty);

        // --- PENAMBAHAN TUGAS BACKGROUND THREAD ---
        progressBar = view.findViewById(R.id.progress_bar);
        // -------------------------------------------

        setupRecyclerView();
        
        return view;
    }

    private void setupRecyclerView() {
        adapter = new BookAdapter(new ArrayList<>(), book -> {
            Intent intent = new Intent(requireContext(), DetailActivity.class);
            intent.putExtra("BOOK_DATA", book);
            startActivity(intent);
        });
        rvFavorites.setLayoutManager(new LinearLayoutManager(requireContext()));
        rvFavorites.setAdapter(adapter);
        
        // --- PENAMBAHAN TUGAS BACKGROUND THREAD ---
        loadFavoriteBooks();
        // -------------------------------------------
    }

    // --- PENAMBAHAN TUGAS BACKGROUND THREAD ---
    private void loadFavoriteBooks() {
        progressBar.setVisibility(View.VISIBLE);
        rvFavorites.setVisibility(View.GONE);
        tvEmpty.setVisibility(View.GONE);

        executor.execute(() -> {
            List<Book> favorites = BookRepository.getFavoriteBooks();
            try {
                Thread.sleep(500); // Simulasi delay proses background
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            handler.post(() -> {
                adapter.updateData(favorites);
                progressBar.setVisibility(View.GONE);
                updateEmptyView(favorites.isEmpty());
            });
        });
    }
    // -------------------------------------------

    private void updateEmptyView(boolean isEmpty) {
        tvEmpty.setVisibility(isEmpty ? View.VISIBLE : View.GONE);
        rvFavorites.setVisibility(isEmpty ? View.GONE : View.VISIBLE);
    }

    @Override
    public void onResume() {
        super.onResume();
        // --- PENAMBAHAN TUGAS BACKGROUND THREAD ---
        loadFavoriteBooks();
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
