package com.ishmah.praktikum3.ui.fragment;

import android.os.Bundle;
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

public class HomeFragment extends Fragment {

    private BookAdapter adapter;
    private String activeGenre = "All";

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

        RecyclerView recyclerView = view.findViewById(R.id.recycler_books);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        adapter = new BookAdapter(requireContext(), BookRepository.getInstance().getAllBooks());
        recyclerView.setAdapter(adapter);

        EditText etSearch = view.findViewById(R.id.et_search);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void afterTextChanged(Editable s) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String query = s.toString().trim();
                if (query.isEmpty()) {
                    adapter.filterByGenre(activeGenre);
                } else {
                    adapter.filter(query);
                }
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
            String searchQuery = etSearch.getText().toString().trim();
            if (searchQuery.isEmpty()) {
                adapter.filterByGenre(activeGenre);
            } else {
                adapter.filter(searchQuery);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (adapter != null) {
            adapter.updateList(BookRepository.getInstance().getAllBooks());
        }
    }
}


