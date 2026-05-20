package com.ishmah.praktikum3.data;

import com.ishmah.praktikum3.R;
import com.ishmah.praktikum3.model.Book;
import java.util.ArrayList;
import java.util.List;

public class DataDummy {

    public static List<Book> getBooks() {
        List<Book> list = new ArrayList<>();

        list.add(new Book(1, "The Very Hungry Caterpillar", "Eric Carle", 1969, "Picture Books",
                "A tiny caterpillar eats through a huge variety of foods before becoming a beautiful butterfly.",
                "A beautifully illustrated story that teaches children about counting and nature. Perfect for toddlers!",
                4.8f, R.drawable.cover_placeholder_1,
                "https://covers.openlibrary.org/b/isbn/0399213015-L.jpg"));

        list.add(new Book(2, "Goodnight Moon", "Margaret Wise Brown", 1947, "First Books",
                "In this classic bedtime story, a young bunny says goodnight to everything around him.",
                "A soothing, poetic bedtime book that has helped generations of children wind down for sleep.",
                4.7f, R.drawable.cover_placeholder_2,
                "https://covers.openlibrary.org/b/isbn/0064430170-L.jpg"));

        list.add(new Book(3, "Where the Wild Things Are", "Maurice Sendak", 1963, "See to Read",
                "Max is sent to bed without supper and travels to a land of wild things where he becomes their king.",
                "A masterpiece of children's literature. Sendak's imagination runs wild and the illustrations are iconic.",
                4.9f, R.drawable.cover_placeholder_3,
                "https://covers.openlibrary.org/b/isbn/0060254920-L.jpg"));

        list.add(new Book(4, "Atomic Habits", "James Clear", 2018, "Self Development",
                "An easy and proven way to build good habits and break bad ones using tiny behavioral changes.",
                "One of the most impactful self-help books I've read. The 1% better every day concept changed my life.",
                4.8f, R.drawable.cover_placeholder_4,
                "https://covers.openlibrary.org/b/isbn/0735211299-L.jpg"));

        list.add(new Book(5, "Deep Work", "Cal Newport", 2016, "Productivity",
                "Rules for focused success in a distracted world. Deep work is the ability to focus without distraction.",
                "Newport makes a compelling case for why focused, distraction-free work is the key to success.",
                4.6f, R.drawable.cover_placeholder_5,
                "https://covers.openlibrary.org/b/isbn/1455586692-L.jpg"));

        list.add(new Book(6, "The Psychology of Money", "Morgan Housel", 2020, "Finance",
                "Timeless lessons on wealth, greed, and happiness. Doing well with money has little to do with IQ.",
                "This book completely changed how I think about money. Housel's storytelling is masterful.",
                4.7f, R.drawable.cover_placeholder_6,
                "https://covers.openlibrary.org/b/isbn/0857197681-L.jpg"));

        list.add(new Book(7, "Sapiens", "Yuval Noah Harari", 2011, "History",
                "A brief history of humankind from the Stone Age to the Silicon Age.",
                "Mind-blowing perspective on human history. Harari challenges everything you thought you knew.",
                4.7f, R.drawable.cover_placeholder_7,
                "https://covers.openlibrary.org/b/isbn/0062316095-L.jpg"));

        list.add(new Book(8, "Becoming", "Michelle Obama", 2018, "Biography",
                "Former First Lady Michelle Obama chronicles the experiences that have shaped her extraordinary life.",
                "Inspiring, honest, and beautifully written. Michelle Obama's story is one of resilience and grace.",
                4.8f, R.drawable.cover_placeholder_8,
                "https://covers.openlibrary.org/b/isbn/1524763136-L.jpg"));

        list.add(new Book(9, "The Silent Patient", "Alex Michaelides", 2019, "Thriller",
                "A woman shoots her husband five times and then never speaks another word. A gripping psychological thriller.",
                "One of the best thrillers I've ever read. The plot twist at the end completely floored me!",
                4.5f, R.drawable.cover_placeholder_9,
                "https://covers.openlibrary.org/b/isbn/1250301696-L.jpg"));

        list.add(new Book(10, "The Midnight Library", "Matt Haig", 2020, "Fiction",
                "Between life and death there is a library with infinite books — each a chance at another life.",
                "A beautiful, life-affirming novel about regret, possibility, and the meaning of a life well lived.",
                4.6f, R.drawable.cover_placeholder_10,
                "https://covers.openlibrary.org/b/isbn/0525559477-L.jpg"));

        list.add(new Book(11, "Thinking, Fast and Slow", "Daniel Kahneman", 2011, "Psychology",
                "Nobel laureate Kahneman explores the two systems that drive the way we think: fast intuition and slow reason.",
                "Dense but absolutely worth it. Kahneman's insights into human cognition are profound and eye-opening.",
                4.5f, R.drawable.cover_placeholder_11,
                "https://covers.openlibrary.org/b/isbn/0374533555-L.jpg"));

        list.add(new Book(12, "Grit", "Angela Duckworth", 2016, "Psychology",
                "The power of passion and perseverance — why talent alone doesn't guarantee success.",
                "Duckworth's research is compelling and motivating. Made me rethink what it takes to achieve goals.",
                4.4f, R.drawable.cover_placeholder_12,
                "https://covers.openlibrary.org/b/isbn/1501111108-L.jpg"));

        list.add(new Book(13, "Ikigai", "Héctor García", 2016, "Self Development",
                "The Japanese secret to a long and happy life — finding your reason for being.",
                "A short but deeply meaningful book. The Japanese wisdom here is both practical and philosophical.",
                4.3f, R.drawable.cover_placeholder_13,
                "https://covers.openlibrary.org/b/isbn/0143130722-L.jpg"));

        list.add(new Book(14, "The 5 AM Club", "Robin Sharma", 2018, "Productivity",
                "Own your morning, elevate your life with a revolutionary morning routine used by top performers.",
                "Motivating and practical. The 20/20/20 formula for mornings transformed my daily routine.",
                4.2f, R.drawable.cover_placeholder_14,
                "https://covers.openlibrary.org/b/isbn/1443456624-L.jpg"));

        list.add(new Book(15, "Educated", "Tara Westover", 2018, "Biography",
                "A memoir about self-invention — from an isolated survivalist family in Idaho to Cambridge University.",
                "One of the most remarkable memoirs I've ever read. Westover's journey is unforgettable.",
                4.9f, R.drawable.cover_placeholder_15,
                "https://covers.openlibrary.org/b/isbn/0399590501-L.jpg"));

        // Urutkan dari yang terbaru
        list.sort((a, b) -> Long.compare(b.getAddedTimestamp(), a.getAddedTimestamp()));
        return list;
    }
}
