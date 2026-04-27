package com.example.praktikum_3;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class FavoritesFragment extends Fragment {
    private BookAdapter adapter;
    private RecyclerView rvFavorites;
    private TextView tvEmpty;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        
        rvFavorites = view.findViewById(R.id.rv_favorites);
        tvEmpty = view.findViewById(R.id.tv_empty);

        setupRecyclerView();
        
        return view;
    }

    private void setupRecyclerView() {
        List<Book> favorites = BookRepository.getFavoriteBooks();
        adapter = new BookAdapter(favorites, book -> {
            Intent intent = new Intent(requireContext(), DetailActivity.class);
            intent.putExtra("BOOK_DATA", book);
            startActivity(intent);
        });
        rvFavorites.setLayoutManager(new LinearLayoutManager(requireContext()));
        rvFavorites.setAdapter(adapter);
        
        updateEmptyView(favorites.isEmpty());
    }

    private void updateEmptyView(boolean isEmpty) {
        tvEmpty.setVisibility(isEmpty ? View.VISIBLE : View.GONE);
        rvFavorites.setVisibility(isEmpty ? View.GONE : View.VISIBLE);
    }

    @Override
    public void onResume() {
        super.onResume();
        List<Book> favorites = BookRepository.getFavoriteBooks();
        adapter.updateData(favorites);
        updateEmptyView(favorites.isEmpty());
    }
}
