package com.indira.tp3.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FavoritesFragment extends Fragment {
    private BookAdapter adapter;
    private List<Book> favorites = new ArrayList<>();
    private TextView tvEmpty;
    private ProgressBar progressBar;
    
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewFav);
        tvEmpty = view.findViewById(R.id.tv_empty);
        progressBar = view.findViewById(R.id.progressBarFav);

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
        if (progressBar != null) progressBar.setVisibility(View.VISIBLE);
        if (tvEmpty != null) tvEmpty.setVisibility(View.GONE);

        executorService.execute(() -> {
            try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }

            List<Book> favList = new ArrayList<>();
            for (Book b : DataDummy.getBooks()) {
                if (b.isLiked()) {
                    favList.add(b);
                }
            }

            mainHandler.post(() -> {
                favorites = favList;
                adapter.updateList(favorites);
                
                if (progressBar != null) progressBar.setVisibility(View.GONE);
                
                if (tvEmpty != null) {
                    if (favorites.isEmpty()) {
                        tvEmpty.setVisibility(View.VISIBLE);
                    } else {
                        tvEmpty.setVisibility(View.GONE);
                    }
                }
            });
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        executorService.shutdown();
    }
}
