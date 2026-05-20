package com.ishmah.praktikum3.data;

import com.ishmah.praktikum3.model.Book;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {
    private static BookRepository instance;
    private final List<Book> books;

    private BookRepository() {
        books = new ArrayList<>(DataDummy.getBooks());
    }

    public static BookRepository getInstance() {
        if (instance == null) {
            instance = new BookRepository();
        }
        return instance;
    }

    public List<Book> getAllBooks() { return books; }

    public List<Book> getLikedBooks() {
        List<Book> liked = new ArrayList<>();
        for (Book b : books) {
            if (b.isLiked()) liked.add(b);
        }
        return liked;
    }

    public void addBook(Book book) {
        book.setId(books.size() + 1);
        book.setAddedTimestamp(System.currentTimeMillis());
        books.add(0, book);
    }

    public void toggleLike(int bookId) {
        for (Book b : books) {
            if (b.getId() == bookId) {
                b.setLiked(!b.isLiked());
                break;
            }
        }
    }
}