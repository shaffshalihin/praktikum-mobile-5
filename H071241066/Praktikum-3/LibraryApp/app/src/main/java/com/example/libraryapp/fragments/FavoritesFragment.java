package com.example.libraryapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libraryapp.DetailActivity;
import com.example.libraryapp.R;
import com.example.libraryapp.adapters.BookAdapter;
import com.example.libraryapp.models.Book;
import com.example.libraryapp.models.BookRepository;

import java.util.List;

public class FavoritesFragment extends Fragment {

    private RecyclerView recyclerView;
    private BookAdapter adapter;
    private LinearLayout layoutEmpty;

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
        
        recyclerView = view.findViewById(R.id.recycler_favorites);
        layoutEmpty = view.findViewById(R.id.layout_empty);
        
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        
        loadFavorites();
    }

    @Override
    public void onResume() {
        super.onResume();
        loadFavorites();
    }

    private void loadFavorites() {
        List<Book> likedBooks = BookRepository.getInstance().getLikedBooks();
        
        if (likedBooks.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            layoutEmpty.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            layoutEmpty.setVisibility(View.GONE);
            
            adapter = new BookAdapter(getContext(), likedBooks, book -> {
                Intent intent = new Intent(getContext(), DetailActivity.class);
                intent.putExtra("book_id", book.getId());
                startActivity(intent);
            });
            recyclerView.setAdapter(adapter);
        }
    }
}
