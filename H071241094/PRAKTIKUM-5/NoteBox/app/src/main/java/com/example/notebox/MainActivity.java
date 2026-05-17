package com.example.notebox;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.switchmaterial.SwitchMaterial;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    private SharedPrefManager spm;
    private String currentUser;

    private View pageHome, pageNotes, pageSettings;
    private FloatingActionButton fab;


    private TextView tvGreeting, tvDate, tvStatTotal, tvStatFav, tvStatToday;
    private RecyclerView rvHomeNotes;
    private NoteAdapter homeAdapter;

    private RecyclerView rvAllNotes;
    private NoteAdapter  allAdapter;
    private EditText etSearchNotes;
    private String selectedCategoryFilter = "Semua"; // Status filter aktif
    private final List<Button> filterButtons = new ArrayList<>();

    private SwitchMaterial swDark, swNotif, swAnim;
    private TextView tvProfileName, tvProfileUsername, tvProfileInitial;
    private TextView tvSettingsUsername;


    private final String[] NOTE_COLORS = {
            "#e8650a","#2196F3","#4CAF50","#9C27B0","#F44336","#FF9800"
    };
    private String selectedColor = NOTE_COLORS[0];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        spm         = SharedPrefManager.getInstance(this);
        currentUser = spm.getCurrentUser();
        if (currentUser == null) {
            startActivity(new Intent(this, LoginActivity.class));
            finish(); return;
        }

        boolean dark = spm.isDarkMode(currentUser);
        AppCompatDelegate.setDefaultNightMode(
                dark ? AppCompatDelegate.MODE_NIGHT_YES
                        : AppCompatDelegate.MODE_NIGHT_NO);

        setContentView(R.layout.activity_main);

        initViews();
        initBottomNav();
        setupSearchAndFilter();
        loadUserInfo();
        loadSettings();
        showPage(pageHome);
        refreshAll();
    }

    // ─────────────────────────────────────────────
    private void initViews() {
        pageHome     = findViewById(R.id.page_home);
        pageNotes    = findViewById(R.id.page_notes);
        pageSettings = findViewById(R.id.page_settings);
        fab          = findViewById(R.id.fab_add);
        fab.setOnClickListener(v -> openAddNoteDialog());

        // Home
        tvGreeting   = findViewById(R.id.tv_greeting);
        tvDate       = findViewById(R.id.tv_date);
        tvStatTotal  = findViewById(R.id.tv_stat_total);
        tvStatFav    = findViewById(R.id.tv_stat_fav);
        tvStatToday  = findViewById(R.id.tv_stat_today);
        rvHomeNotes  = findViewById(R.id.rv_home_notes);
        rvHomeNotes.setLayoutManager(new LinearLayoutManager(this));

        homeAdapter  = new NoteAdapter(spm.getNotes(currentUser), new NoteAdapter.OnNoteActionListener() {
            @Override
            public void onDelete(Note note) { confirmDelete(note); }

            @Override
            public void onItemClick(Note note) { openEditNoteDialog(note); }
        });
        rvHomeNotes.setAdapter(homeAdapter);

        etSearchNotes = findViewById(R.id.et_search_notes);
        rvAllNotes  = findViewById(R.id.rv_all_notes);
        rvAllNotes.setLayoutManager(new LinearLayoutManager(this));

        allAdapter  = new NoteAdapter(new ArrayList<>(), new NoteAdapter.OnNoteActionListener() {
            @Override
            public void onDelete(Note note) { confirmDelete(note); }

            @Override
            public void onItemClick(Note note) { openEditNoteDialog(note); }
        });
        rvAllNotes.setAdapter(allAdapter);


        swDark             = findViewById(R.id.sw_dark);
        swNotif            = findViewById(R.id.sw_notif);
        swAnim             = findViewById(R.id.sw_anim);
        tvProfileName      = findViewById(R.id.tv_profile_name);
        tvProfileUsername  = findViewById(R.id.tv_profile_username);
        tvProfileInitial   = findViewById(R.id.tv_profile_initial);
        tvSettingsUsername = findViewById(R.id.tv_settings_username);

        swDark.setOnCheckedChangeListener(this::onDarkToggle);
        swNotif.setOnCheckedChangeListener((b,c)-> {
            spm.setNotifEnabled(currentUser, c);
            toast(c ? "🔔 Notifikasi diaktifkan" : "🔕 Notifikasi dimatikan");
        });
        swAnim.setOnCheckedChangeListener((b,c)-> {
            spm.setAnimEnabled(currentUser, c);
            toast("✅ Preferensi disimpan");
        });

        findViewById(R.id.btn_clear_notes).setOnClickListener(v -> confirmClearAll());
        findViewById(R.id.btn_logout).setOnClickListener(v -> doLogout());
    }


    private void setupSearchAndFilter() {
        etSearchNotes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterAndSearchNotes();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        filterButtons.add(findViewById(R.id.btn_filter_all));
        filterButtons.add(findViewById(R.id.btn_filter_personal));
        filterButtons.add(findViewById(R.id.btn_filter_kerja));
        filterButtons.add(findViewById(R.id.btn_filter_ide));
        filterButtons.add(findViewById(R.id.btn_filter_penting));
        filterButtons.add(findViewById(R.id.btn_filter_lainnya));


        for (Button btn : filterButtons) {
            btn.setOnClickListener(v -> {
                selectedCategoryFilter = btn.getText().toString();
                updateFilterButtonsUI(btn);
                filterAndSearchNotes();
            });
        }
    }

    private void filterAndSearchNotes() {
        List<Note> allOriginalNotes = spm.getNotes(currentUser);
        String query = etSearchNotes.getText().toString().trim().toLowerCase();

        List<Note> filteredList = allOriginalNotes.stream().filter(note -> {
            boolean matchCategory = selectedCategoryFilter.equals("Semua") ||
                    note.getCategory().equalsIgnoreCase(selectedCategoryFilter);


            boolean matchQuery = query.isEmpty() ||
                    note.getTitle().toLowerCase().contains(query) ||
                    (note.getBody() != null && note.getBody().toLowerCase().contains(query));

            return matchCategory && matchQuery;
        }).collect(Collectors.toList());

        allAdapter.updateNotes(filteredList);
    }

    private void updateFilterButtonsUI(Button clickedButton) {
        for (Button btn : filterButtons) {
            if (btn == clickedButton) {
                btn.setBackgroundResource(R.drawable.bg_button_accent);
                btn.setTextColor(Color.WHITE);
            } else {
                btn.setBackgroundResource(R.drawable.bg_tag);
                btn.setTextColor(Color.parseColor("#757575"));
            }
        }
    }

    private void initBottomNav() {
        BottomNavigationView bnv = findViewById(R.id.bottom_nav);
        bnv.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home)     { showPage(pageHome);     return true; }
            if (id == R.id.nav_notes)    { showPage(pageNotes);    return true; }
            if (id == R.id.nav_settings) { showPage(pageSettings); return true; }
            return false;
        });
    }

    private void showPage(View target) {
        pageHome.setVisibility(View.GONE);
        pageNotes.setVisibility(View.GONE);
        pageSettings.setVisibility(View.GONE);
        target.setVisibility(View.VISIBLE);

        fab.setVisibility(target == pageSettings ? View.GONE : View.VISIBLE);
    }

    // ─────────────────────────────────────────────
    private void loadUserInfo() {
        User user = spm.getUserByUsername(currentUser);
        String name = (user != null) ? user.getDisplayName() : currentUser;
        String initial = (name != null && !name.isEmpty())
                ? String.valueOf(name.charAt(0)).toUpperCase() : "?";

        tvGreeting.setText("Halo, " + name + "! 👋");
        tvProfileName.setText(name);
        tvProfileUsername.setText("@" + currentUser);
        tvProfileInitial.setText(initial);
        tvSettingsUsername.setText(currentUser);

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd MMMM yyyy",
                new Locale("id", "ID"));
        tvDate.setText(sdf.format(new Date()));
    }

    private void loadSettings() {
        swDark.setChecked(spm.isDarkMode(currentUser));
        swNotif.setChecked(spm.isNotifEnabled(currentUser));
        swAnim.setChecked(spm.isAnimEnabled(currentUser));
    }

    private void onDarkToggle(CompoundButton b, boolean checked) {
        spm.setDarkMode(currentUser, checked);
        AppCompatDelegate.setDefaultNightMode(
                checked ? AppCompatDelegate.MODE_NIGHT_YES
                        : AppCompatDelegate.MODE_NIGHT_NO);
        toast(checked ? "🌙 Tema Gelap Aktif" : "☀️ Tema Terang Aktif");
    }


    private void refreshAll() {
        List<Note> notes = spm.getNotes(currentUser);

        tvStatTotal.setText(String.valueOf(notes.size()));
        long fav   = notes.stream().filter(n -> "Penting".equals(n.getCategory())).count();
        String tod = new SimpleDateFormat("EEE MMM dd yyyy", Locale.ENGLISH).format(new Date());
        long today = notes.stream().filter(n -> tod.equals(n.getDateRaw())).count();
        tvStatFav.setText(String.valueOf(fav));
        tvStatToday.setText(String.valueOf(today));

        List<Note> preview = notes.subList(0, Math.min(4, notes.size()));
        homeAdapter.updateNotes(preview);

        filterAndSearchNotes();
    }

    private void openAddNoteDialog() {
        View dlgView = LayoutInflater.from(this).inflate(R.layout.dialog_add_note, null);
        EditText etTitle    = dlgView.findViewById(R.id.et_note_title);
        EditText etBody     = dlgView.findViewById(R.id.et_note_body);
        Spinner  spCategory = dlgView.findViewById(R.id.sp_category);
        LinearLayout llColors = dlgView.findViewById(R.id.ll_colors);

        ArrayAdapter<String> catAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,
                new String[]{"Personal","Kerja","Ide","Penting","Lainnya"});
        spCategory.setAdapter(catAdapter);

        selectedColor = NOTE_COLORS[0];
        buildColorPicker(llColors);

        new AlertDialog.Builder(this)
                .setView(dlgView)
                .setPositiveButton("Simpan", (d, w) -> {
                    String title = etTitle.getText().toString().trim();
                    String body  = etBody.getText().toString().trim();
                    String cat   = spCategory.getSelectedItem().toString();
                    if (title.isEmpty()) { toast("⚠️ Judul tidak boleh kosong!"); return; }

                    SimpleDateFormat sdfDisplay = new SimpleDateFormat("dd MMM yyyy", new Locale("id","ID"));
                    SimpleDateFormat sdfRaw     = new SimpleDateFormat("EEE MMM dd yyyy", Locale.ENGLISH);
                    Date now = new Date();

                    Note note = new Note(System.currentTimeMillis(),
                            title, body, cat, selectedColor,
                            sdfDisplay.format(now), sdfRaw.format(now));
                    spm.addNote(currentUser, note);
                    refreshAll();
                    toast("✅ Catatan berhasil disimpan!");
                })
                .setNegativeButton("Batal", null)
                .setTitle("Catatan Baru")
                .show();
    }

    private void openEditNoteDialog(Note note) {
        View dlgView = LayoutInflater.from(this).inflate(R.layout.dialog_add_note, null);
        EditText etTitle    = dlgView.findViewById(R.id.et_note_title);
        EditText etBody     = dlgView.findViewById(R.id.et_note_body);
        Spinner  spCategory = dlgView.findViewById(R.id.sp_category);
        LinearLayout llColors = dlgView.findViewById(R.id.ll_colors);

        etTitle.setText(note.getTitle());
        etBody.setText(note.getBody());

        String[] categories = {"Personal","Kerja","Ide","Penting","Lainnya"};
        ArrayAdapter<String> catAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, categories);
        spCategory.setAdapter(catAdapter);

        for (int i = 0; i < categories.length; i++) {
            if (categories[i].equalsIgnoreCase(note.getCategory())) {
                spCategory.setSelection(i);
                break;
            }
        }

        selectedColor = note.getColor();
        buildColorPicker(llColors);

        new AlertDialog.Builder(this)
                .setView(dlgView)
                .setPositiveButton("Perbarui", (d, w) -> {
                    String title = etTitle.getText().toString().trim();
                    String body  = etBody.getText().toString().trim();
                    String cat   = spCategory.getSelectedItem().toString();
                    if (title.isEmpty()) { toast("⚠️ Judul tidak boleh kosong!"); return; }

                    List<Note> notes = spm.getNotes(currentUser);
                    for (int i = 0; i < notes.size(); i++) {
                        if (notes.get(i).getId() == note.getId()) {
                            SimpleDateFormat sdfDisplay = new SimpleDateFormat("dd MMM yyyy", new Locale("id","ID"));
                            SimpleDateFormat sdfRaw     = new SimpleDateFormat("EEE MMM dd yyyy", Locale.ENGLISH);
                            Date now = new Date();

                            Note updatedNote = new Note(note.getId(), title, body, cat, selectedColor,
                                    sdfDisplay.format(now), sdfRaw.format(now));
                            notes.set(i, updatedNote);
                            break;
                        }
                    }

                    spm.saveNotes(currentUser, notes);
                    refreshAll();
                    toast("✅ Catatan berhasil diperbarui!");
                })
                .setNegativeButton("Batal", null)
                .setTitle("Edit Catatan")
                .show();
    }

    private void buildColorPicker(LinearLayout container) {
        container.removeAllViews();
        for (String hex : NOTE_COLORS) {
            View dot = new View(this);
            int  dp  = (int)(28 * getResources().getDisplayMetrics().density);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(dp, dp);
            lp.setMarginEnd((int)(8 * getResources().getDisplayMetrics().density));
            dot.setLayoutParams(lp);
            dot.setBackground(makeCircle(hex));
            dot.setTag(hex);
            dot.setOnClickListener(v -> {
                selectedColor = hex;
                for (int i = 0; i < container.getChildCount(); i++) {
                    container.getChildAt(i).setAlpha(0.4f);
                }
                v.setAlpha(1f);
                v.setScaleX(1.25f);
                v.setScaleY(1.25f);
            });
            if (hex.equals(selectedColor)) { dot.setAlpha(1f); dot.setScaleX(1.25f); dot.setScaleY(1.25f); }
            else dot.setAlpha(0.4f);
            container.addView(dot);
        }
    }

    private android.graphics.drawable.GradientDrawable makeCircle(String hex) {
        android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
        gd.setShape(android.graphics.drawable.GradientDrawable.OVAL);
        gd.setColor(Color.parseColor(hex));
        return gd;
    }

    private void confirmDelete(Note note) {
        new AlertDialog.Builder(this)
                .setTitle("Hapus Catatan")
                .setMessage("Yakin ingin menghapus \"" + note.getTitle() + "\"?")
                .setPositiveButton("Hapus", (d,w) -> {
                    spm.deleteNote(currentUser, note.getId());
                    refreshAll();
                    toast("🗑️ Catatan dihapus");
                })
                .setNegativeButton("Batal", null)
                .show();
    }

    private void confirmClearAll() {
        new AlertDialog.Builder(this)
                .setTitle("Hapus Semua Catatan")
                .setMessage("Tindakan ini tidak bisa dibatalkan. Lanjutkan?")
                .setPositiveButton("Hapus Semua", (d,w) -> {
                    spm.clearNotes(currentUser);
                    refreshAll();
                    toast("🗑️ Semua catatan dihapus");
                })
                .setNegativeButton("Batal", null)
                .show();
    }

    private void doLogout() {
        spm.clearCurrentUser();
        toast("Berhasil keluar 👋");
        startActivity(new Intent(this, LoginActivity.class));
        finishAffinity();
    }

    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshAll();
    }
}