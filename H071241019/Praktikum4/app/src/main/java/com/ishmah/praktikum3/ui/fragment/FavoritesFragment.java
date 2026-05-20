package com.ishmah.praktikum3.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ishmah.praktikum3.R;
import com.ishmah.praktikum3.adapter.BookAdapter;
import com.ishmah.praktikum3.data.BookRepository;
import com.ishmah.praktikum3.model.Book;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FavoritesFragment extends Fragment {

    private BookAdapter adapter;
    private RecyclerView recyclerView;
    private View emptyState;
    private View progressFavorites;

    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final Handler handler = new Handler(Looper.getMainLooper());

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView      = view.findViewById(R.id.recycler_favorites);
        emptyState        = view.findViewById(R.id.empty_state);
        progressFavorites = view.findViewById(R.id.progress_favorites);

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new BookAdapter(requireContext(), BookRepository.getInstance().getLikedBooks());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (adapter == null) return;

        // Tampilkan ProgressBar, sembunyikan konten
        progressFavorites.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        emptyState.setVisibility(View.GONE);

        executor.execute(() -> {
            try { Thread.sleep(600); } catch (InterruptedException ignored) {}

            // Load data favorit di background thread
            List<Book> liked = BookRepository.getInstance().getLikedBooks();

            handler.post(() -> {
                // Update UI di Main Thread
                adapter.updateList(liked);
                progressFavorites.setVisibility(View.GONE);
                updateEmptyState();
            });
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        executor.shutdown();
    }

    private void updateEmptyState() {
        boolean isEmpty = BookRepository.getInstance().getLikedBooks().isEmpty();
        emptyState.setVisibility(isEmpty ? View.VISIBLE : View.GONE);
        recyclerView.setVisibility(isEmpty ? View.GONE : View.VISIBLE);
    }
}
