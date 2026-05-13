package com.example.libraryapp.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookRepository {
    private static BookRepository instance;
    private List<Book> books;

    private BookRepository() {
        books = new ArrayList<>();
        initDummyData();
    }

    public static BookRepository getInstance() {
        if (instance == null) {
            instance = new BookRepository();
        }
        return instance;
    }

    private void initDummyData() {
        books.add(new Book("To Kill a Mockingbird", "Harper Lee", 1960, "A tale of coming-of-age...", "Classic", 4.8f, "cover_mockingbird", 281, "English", "J. B. Lippincott & Co."));
        books.add(new Book("1984", "George Orwell", 1949, " futuristic vision...", "Dystopia", 4.7f, "cover_1984", 328, "English", "Secker & Warburg"));
        books.add(new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925, "story of Jay Gatsby...", "Classic", 4.5f, "cover_gatsby", 180, "English", "Charles Scribner's Sons"));
        books.add(new Book("Harry Potter", "J.K. Rowling", 1997, "Letters from Hogwarts...", "Fantasy", 4.9f, "cover_hp1", 309, "English", "Bloomsbury"));
        books.add(new Book("The Hobbit", "J.R.R. Tolkien", 1937, "Bilbo Baggins journey...", "Fantasy", 4.8f, "cover_hobbit", 310, "English", "George Allen & Unwin"));
        books.add(new Book("Dune", "Frank Herbert", 1965, "desert planet Arrakis...", "Sci-Fi", 4.7f, "cover_dune", 412, "English", "Chilton Books"));
        books.add(new Book("Atomic Habits", "James Clear", 2018, "improving every day...", "Self-Help", 4.8f, "cover_atomic", 320, "English", "Avery"));
        books.add(new Book("Sapiens", "Yuval Noah Harari", 2011, "narrative of humanity...", "Non-Fiction", 4.6f, "cover_sapiens", 443, "English", "Harper"));
        books.add(new Book("The Alchemist", "Paulo Coelho", 1988, "shepherd boy Santiago...", "Fiction", 4.6f, "cover_alchemist", 197, "Portuguese", "HarperOne"));
        books.add(new Book("Pride and Prejudice", "Jane Austen", 1813, "darling child novel...", "Classic", 4.7f, "cover_pride", 432, "English", "T. Egerton"));
        books.add(new Book("The Catcher in the Rye", "J.D. Salinger", 1951, "native New Yorker...", "Classic", 4.3f, "cover_catcher", 277, "English", "Little, Brown and Company"));
        books.add(new Book("Brave New World", "Aldous Huxley", 1932, "ideal society future...", "Dystopia", 4.4f, "cover_brave", 311, "English", "Chatto & Windus"));
        books.add(new Book("The Da Vinci Code", "Dan Brown", 2003, "code in works of Leonardo...", "Thriller", 4.4f, "cover_davinci", 454, "English", "Doubleday"));
        books.add(new Book("Think and Grow Rich", "Napoleon Hill", 1937, "money-making secrets...", "Self-Help", 4.5f, "cover_think", 238, "English", "The Ralston Society"));
        books.add(new Book("Bumi Manusia", "Pramoedya Ananta Toer", 1980, "Perjuangan Minke...", "Historical Fiction", 4.9f, "cover_bumi", 335, "Indonesian", "Hasta Mitra"));
        books.add(new Book("Laskar Pelangi", "Andrea Hirata", 2005, "Kisah 10 anak Belitung...", "Fiction", 4.8f, "cover_laskar", 529, "Indonesian", "Bentang Pustaka"));
        books.add(new Book("Psychology of Money", "Morgan Housel", 2020, "lessons on wealth...", "Non-Fiction", 4.7f, "cover_psychology", 256, "English", "Harriman House"));
    }

    public List<Book> getAllBooks() {
        List<Book> reversed = new ArrayList<>(books);
        java.util.Collections.reverse(reversed);
        return reversed;
    }

    public List<Book> getLikedBooks() {
        List<Book> liked = new ArrayList<>();
        for (Book b : books) {
            if (b.isLiked()) liked.add(b);
        }
        return liked;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public Book getBookById(int id) {
        for (Book b : books) {
            if (b.getId() == id) return b;
        }
        return null;
    }

    public List<String> getAllGenres() {
        List<String> genres = new ArrayList<>();
        genres.add("All");
        for (Book b : books) {
            if (!genres.contains(b.getGenre())) {
                genres.add(b.getGenre());
            }
        }
        return genres;
    }
}
