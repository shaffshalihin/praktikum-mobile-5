package com.indira.tp3.utils;

import android.net.Uri;
import com.indira.tp3.R;
import com.indira.tp3.model.Book;
import java.util.ArrayList;
import java.util.List;

public class DataDummy {
    private static List<Book> books = null;

    public static List<Book> getBooks() {
        if (books == null) {
            books = new ArrayList<>();
            // Add books with drawable resources converted to Uri
            books.add(new Book("The Great Gatsby", "F. Scott Fitzgerald", "2023", "A story of wealth and love in the Jazz Age.", "Classic", "4.5", "Amazing prose!", Uri.parse("android.resource://com.indira.tp3/" + R.drawable.the_great_gatsby), false));
            books.add(new Book("1984", "George Orwell", "2023", "Dystopian future where Big Brother watches all.", "Dystopian", "4.8", "Very scary and real.", Uri.parse("android.resource://com.indira.tp3/" + R.drawable.book1), false));
            books.add(new Book("To Kill a Mockingbird", "Harper Lee", "2022", "Racial injustice in the American South.", "Classic", "4.9", "Heartbreaking.", Uri.parse("android.resource://com.indira.tp3/" + R.drawable.mocking_birl), false));
            books.add(new Book("The Hobbit", "J.R.R. Tolkien", "2023", "A hobbit's adventure to reclaim a treasure.", "Fantasy", "4.7", "Magical journey.", Uri.parse("android.resource://com.indira.tp3/" + R.drawable.the_great_gatsby), false));
            books.add(new Book("Moby Dick", "Herman Melville", "2021", "Obsession with a white whale.", "Adventure", "4.0", "Long but deep.", Uri.parse("android.resource://com.indira.tp3/" + R.drawable.moby_dick), false));
            books.add(new Book("Pride and Prejudice", "Jane Austen", "2023", "Love and social class in England.", "Romance", "4.6", "Timeless classic.", Uri.parse("android.resource://com.indira.tp3/" + R.drawable.pride_judice), false));
            books.add(new Book("The Shining", "Stephen King", "2022", "A family trapped in a haunted hotel.", "Horror", "4.4", "Terrifying!", Uri.parse("android.resource://com.indira.tp3/" + R.drawable.book1), false));
            books.add(new Book("Dune", "Frank Herbert", "2023", "Desert planet adventure.", "Sci-Fi", "4.7", "Masterpiece!", Uri.parse("android.resource://com.indira.tp3/" + R.drawable.mocking_birl), false));
            books.add(new Book("The Martian", "Andy Weir", "2023", "Stranded on Mars.", "Sci-Fi", "4.8", "Funny and smart.", Uri.parse("android.resource://com.indira.tp3/" + R.drawable.the_great_gatsby), false));
            books.add(new Book("Harry Potter", "J.K. Rowling", "2022", "A young wizard's journey.", "Fantasy", "4.9", "Magical!", Uri.parse("android.resource://com.indira.tp3/" + R.drawable.book1), false));
            books.add(new Book("The Alchemist", "Paulo Coelho", "2023", "A shepherd's journey.", "Philosophical", "4.3", "Inspirational.", Uri.parse("android.resource://com.indira.tp3/" + R.drawable.pride_judice), false));
            books.add(new Book("Atomic Habits", "James Clear", "2023", "Building good habits.", "Self-Help", "4.9", "Life changing!", Uri.parse("android.resource://com.indira.tp3/" + R.drawable.moby_dick), false));
            books.add(new Book("The Silent Patient", "Alex Michaelides", "2022", "Psychological thriller.", "Thriller", "4.6", "Mind-blowing twist!", Uri.parse("android.resource://com.indira.tp3/" + R.drawable.mocking_birl), false));
            books.add(new Book("Becoming", "Michelle Obama", "2021", "Former First Lady's memoir.", "Biography", "4.8", "Inspiring.", Uri.parse("android.resource://com.indira.tp3/" + R.drawable.pride_judice), false));
            books.add(new Book("Sapiens", "Yuval Noah Harari", "2022", "Brief history of humankind.", "History", "4.7", "Fascinating!", Uri.parse("android.resource://com.indira.tp3/" + R.drawable.book1), false));
        }
        return books;
    }
}
