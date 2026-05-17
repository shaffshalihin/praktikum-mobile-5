package com.example.notebox;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NoteDetailActivity extends AppCompatActivity {

    private EditText etTitle, etBody;
    private Spinner spCategory;
    private SharedPrefManager spm;
    private String currentUser;
    private long noteId;
    private String noteColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);

        spm = SharedPrefManager.getInstance(this);
        currentUser = spm.getCurrentUser();

        etTitle = findViewById(R.id.et_detail_title);
        etBody = findViewById(R.id.et_detail_body);
        spCategory = findViewById(R.id.sp_detail_category);
        Button btnBack = findViewById(R.id.btn_detail_back);
        Button btnSave = findViewById(R.id.btn_detail_save);

        String[] categories = {"Personal", "Kerja", "Ide", "Penting", "Lainnya"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categories);
        spCategory.setAdapter(adapter);

        noteId = getIntent().getLongExtra("NOTE_ID", -1);

        if (noteId != -1) {
            loadNoteData(categories);
        }

        btnBack.setOnClickListener(v -> finish());
        btnSave.setOnClickListener(v -> updateNote());
    }

    private void loadNoteData(String[] categories) {
        List<Note> notes = spm.getNotes(currentUser);
        for (Note note : notes) {
            if (note.getId() == noteId) {
                etTitle.setText(note.getTitle());
                etBody.setText(note.getBody());
                noteColor = note.getColor();

                for (int i = 0; i < categories.length; i++) {
                    if (categories[i].equalsIgnoreCase(note.getCategory())) {
                        spCategory.setSelection(i);
                        break;
                    }
                }
                break;
            }
        }
    }

    private void updateNote() {
        String updatedTitle = etTitle.getText().toString().trim();
        String updatedBody = etBody.getText().toString().trim();
        String updatedCat = spCategory.getSelectedItem().toString();

        if (updatedTitle.isEmpty()) {
            Toast.makeText(this, "⚠️ Judul tidak boleh kosong!", Toast.LENGTH_SHORT).show();
            return;
        }

        List<Note> notes = spm.getNotes(currentUser);
        for (int i = 0; i < notes.size(); i++) {
            if (notes.get(i).getId() == noteId) {
                SimpleDateFormat sdfDisplay = new SimpleDateFormat("dd MMM yyyy", new Locale("id", "ID"));
                SimpleDateFormat sdfRaw = new SimpleDateFormat("EEE MMM dd yyyy", Locale.ENGLISH);
                Date now = new Date();

                Note updatedNote = new Note(noteId, updatedTitle, updatedBody, updatedCat,
                        noteColor, sdfDisplay.format(now), sdfRaw.format(now));

                notes.set(i, updatedNote);
                break;
            }
        }

        spm.saveNotes(currentUser, notes);
        Toast.makeText(this, "✅ Catatan berhasil diperbarui!", Toast.LENGTH_SHORT).show();
        finish();
    }
}