package com.indira.tp3.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.indira.tp3.R;
import com.indira.tp3.adapter.BookAdapter;
import com.indira.tp3.activities.DetailActivity;
import com.indira.tp3.model.Book;
import com.indira.tp3.utils.DataDummy;
import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment {
    private BookAdapter adapter;
    private List<Book> favorites = new ArrayList<>();
    private TextView tvEmpty;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewFav);
        tvEmpty = view.findViewById(R.id.tv_empty);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new BookAdapter(favorites, book -> {
            DetailActivity.start(getContext(), book);
        });
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateFavorites();
    }

    private void updateFavorites() {
        favorites.clear();
        for (Book b : DataDummy.getBooks()) {
            if (b.isLiked()) {
                favorites.add(b);
            }
        }
        adapter.updateList(favorites);

        if (tvEmpty != null) {
            if (favorites.isEmpty()) {
                tvEmpty.setVisibility(View.VISIBLE);
            } else {
                tvEmpty.setVisibility(View.GONE);
            }
        }
    }
}
