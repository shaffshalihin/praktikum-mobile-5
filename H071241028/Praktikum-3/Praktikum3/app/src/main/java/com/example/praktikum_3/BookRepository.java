package com.example.praktikum_3;

import java.util.ArrayList;
import java.util.List;

public class BookRepository {
    private static List<Book> books = new ArrayList<>();

    static {
        // Dummy data (15 books) with actual cover images from drawable
        books.add(new Book(1, "To Kill a Mockingbird", "Harper Lee", 1960, "A classic of modern American literature.", null, R.drawable.to_kill_a_mockingbird, "Fiction", 4.8f, 324));
        books.add(new Book(2, "1984", "George Orwell", 1949, "A dystopian social science fiction novel.", null, R.drawable.book_1984, "Dystopian", 4.7f, 328));
        books.add(new Book(3, "The Great Gatsby", "F. Scott Fitzgerald", 1925, "A story of ambition and love.", null, R.drawable.the_great_gatsby, "Classic", 4.4f, 180));
        books.add(new Book(4, "The Catcher in the Rye", "J.D. Salinger", 1951, "A novel about teenage rebellion.", null, R.drawable.the_catcher_in_the_rye, "Fiction", 4.0f, 234));
        books.add(new Book(5, "Pride and Prejudice", "Jane Austen", 1813, "A romantic novel of manners.", null, R.drawable.pride_and_prejudice, "Romance", 4.9f, 432));
        books.add(new Book(6, "The Hobbit", "J.R.R. Tolkien", 1937, "A fantasy novel for children.", null, R.drawable.the_hobbit, "Fantasy", 4.8f, 310));
        books.add(new Book(7, "Brave New World", "Aldous Huxley", 1932, "A dystopian novel set in a futuristic World State.", null, R.drawable.brave_new_world, "Dystopian", 4.5f, 268));
        books.add(new Book(8, "The Alchemist", "Paulo Coelho", 1988, "A journey to find worldly success.", null, R.drawable.the_alchemist, "Adventure", 4.6f, 208));
        books.add(new Book(9, "Harry Potter", "J.K. Rowling", 1997, "The boy who lived.", null, R.drawable.harry_potter, "Fantasy", 4.9f, 309));
        books.add(new Book(10, "The Lord of the Rings", "J.R.R. Tolkien", 1954, "One ring to rule them all.", null, R.drawable.the_lord_of_the_rings, "Fantasy", 4.9f, 1178));
        books.add(new Book(11, "Animal Farm", "George Orwell", 1945, "A satirical allegorical novella.", null, R.drawable.animal_farm, "Satire", 4.6f, 112));
        books.add(new Book(12, "The Little Prince", "Antoine de Saint-Exupéry", 1943, "A philosophical story for all ages.", null, R.drawable.the_little_prince, "Philosophical", 4.8f, 96));
        books.add(new Book(13, "The Da Vinci Code", "Dan Brown", 2003, "A thriller involving a secret society.", null, R.drawable.the_da_vinci_code, "Thriller", 4.1f, 454));
        books.add(new Book(14, "Crime and Punishment", "Fyodor Dostoevsky", 1866, "A psychological drama about guilt.", null, R.drawable.crime_and_punishment, "Classic", 4.7f, 671));
        books.add(new Book(15, "Thinking, Fast and Slow", "Daniel Kahneman", 2011, "A book about the mind's two systems.", null, R.drawable.thinking_fast_and_slow, "Non-fiction", 4.5f, 499));
    }

    public static List<Book> getBooks() {
        return books;
    }

    public static List<Book> getFavoriteBooks() {
        List<Book> favorites = new ArrayList<>();
        for (Book book : books) {
            if (book.isLiked()) {
                favorites.add(book);
            }
        }
        return favorites;
    }

    public static List<Book> getContinueReadingBooks() {
        return books.subList(0, Math.min(books.size(), 4));
    }

    public static void addBook(Book book) {
        books.add(0, book);
    }
}
