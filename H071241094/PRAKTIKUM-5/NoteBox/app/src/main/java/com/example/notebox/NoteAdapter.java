package com.example.notebox;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteVH> {

    public interface OnNoteActionListener {
        void onDelete(Note note);
        void onItemClick(Note note);
    }

    private List<Note> notes;
    private final OnNoteActionListener listener;

    public NoteAdapter(List<Note> notes, OnNoteActionListener listener) {
        this.notes    = notes;
        this.listener = listener;
    }

    public void updateNotes(List<Note> newNotes) {
        this.notes = newNotes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NoteVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_note, parent, false);
        return new NoteVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteVH h, int position) {
        Note note = notes.get(position);
        h.tvTitle.setText(note.getTitle());
        h.tvBody.setText(note.getBody() != null && !note.getBody().isEmpty()
                ? note.getBody() : "Tidak ada isi catatan.");
        h.tvCategory.setText(note.getCategory());
        h.tvDate.setText("📅 " + note.getDate());

        try {
            h.vAccent.setBackgroundColor(Color.parseColor(note.getColor()));
        } catch (Exception ignored) {}
        h.itemView.setOnClickListener(v -> listener.onItemClick(note));


        h.btnDelete.setOnClickListener(v -> listener.onDelete(note));
    }

    @Override
    public int getItemCount() { return notes.size(); }

    static class NoteVH extends RecyclerView.ViewHolder {
        TextView    tvTitle, tvBody, tvCategory, tvDate;
        View        vAccent;
        ImageButton btnDelete;

        NoteVH(@NonNull View v) {
            super(v);
            tvTitle    = v.findViewById(R.id.tv_note_title);
            tvBody     = v.findViewById(R.id.tv_note_body);
            tvCategory = v.findViewById(R.id.tv_note_category);
            tvDate     = v.findViewById(R.id.tv_note_date);
            vAccent    = v.findViewById(R.id.view_accent);
            btnDelete  = v.findViewById(R.id.btn_delete_note);
        }
    }
}