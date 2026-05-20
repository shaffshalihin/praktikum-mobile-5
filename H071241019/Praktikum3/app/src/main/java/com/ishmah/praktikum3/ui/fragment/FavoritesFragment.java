package com.ishmah.praktikum3.ui.fragment;

import android.os.Bundle;
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

public class FavoritesFragment extends Fragment {

    private BookAdapter adapter;
    private RecyclerView recyclerView;
    private View emptyState;

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
        emptyState = view.findViewById(R.id.empty_state);

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new BookAdapter(requireContext(), BookRepository.getInstance().getLikedBooks());
        recyclerView.setAdapter(adapter);

        updateEmptyState();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (adapter != null) {
            adapter.updateList(BookRepository.getInstance().getLikedBooks());
            updateEmptyState();
        }
    }

    private void updateEmptyState() {
        boolean isEmpty = BookRepository.getInstance().getLikedBooks().isEmpty();
        emptyState.setVisibility(isEmpty ? View.VISIBLE : View.GONE);
        recyclerView.setVisibility(isEmpty ? View.GONE : View.VISIBLE);
    }
}
