package com.example.tp3;

import java.util.ArrayList;

public class DataSource {
    public static ArrayList<Book> books = new ArrayList<>();

    public static void initDummyData() {
        if (books.isEmpty()) {
            books.add(new Book("1", "Jika Kita Tak Pernah Jadi Apa-Apa", "Alfi Syahrin", "2019", "Menemukan ketenangan di tengah tekanan hidup.", R.drawable.jika, 4.8f, "Self-Help"));
            books.add(new Book("2", "Nanti Juga Sembuh Sendiri", "Helo Bagas", "2021", "Pesan penguat hati melewati masa sulit.", R.drawable.nanti, 4.6f, "Self-Help"));
            books.add(new Book("3", "Self Healing", "Ardi Mohamad", "2022", "Panduan berdamai dengan diri sendiri.", R.drawable.selfhealing, 4.7f, "Pengembangan Diri"));
            books.add(new Book("4", "Yang Katanya Cemara", "Vania Wilona", "2023", "Tentang rumah yang tidak lagi menjadi tempat pulang.", R.drawable.yangkatanya, 4.8f, "Pengembangan Diri"));
            books.add(new Book("5", "Jejak Kata di Tanah Papua", "Yulianus Magai", "2021", "Refleksi mendalam tentang realitas pedalaman Papua.", R.drawable.jejakkata, 4.8f, "Sastra"));
            books.add(new Book("6", "Dongeng Dari Bumi Papua Barat", "Belghita Yenusi, dkk.", "2022", "Kumpulan cerita rakyat dan tradisi Papua Barat.", R.drawable.dongeng, 4.7f, "Budaya"));
            books.add(new Book("7", "Sagu Papua Untuk Dunia", "Ahmad Arif", "2019", "Potensi sagu sebagai pangan masa depan.", R.drawable.sagu, 4.9f, "Pengetahuan Lokal"));
            books.add(new Book("8", "Laskar Pelangi", "Andrea Hirata", "2005", "Kisah perjuangan 10 anak di Belitung.", R.drawable.laskarpelangi, 4.8f, "Novel"));
            books.add(new Book("9", "3726 MDPL", "Riawani Elyta", "2016", "Pencarian jati diri di puncak Rinjani.", R.drawable.mdpl, 4.7f, "Novel"));
            books.add(new Book("10", "Dilan 1990", "Pidi Baiq", "2014", "Kisah romansa masa SMA.", R.drawable.dilan, 4.5f, "Romansa"));
            books.add(new Book("11", "Perahu Kertas", "Dee Lestari", "2009", "Kisah persahabatan dan cinta.", R.drawable.perahukertas, 4.5f, "Fiksi"));
            books.add(new Book("12", "Hujan", "Tere Liye", "2016", "Tentang persahabatan dan melupakan.", R.drawable.hujan, 4.7f, "Sci-Fi"));
            books.add(new Book("13", "Cantik Itu Luka", "Eka Kurniawan", "2002", "Kisah keluarga yang tragis.", R.drawable.cantikituluka, 4.8f, "Fiksi"));
            books.add(new Book("14", "Bumi Manusia", "Pramoedya A. Toer", "1980", "Kisah Minke di era kolonial.", R.drawable.bumimanusia, 4.9f, "Sejarah"));
            books.add(new Book("15", "Pulang", "Leila S. Chudori", "2012", "Eksil politik Indonesia.", R.drawable.pulang, 4.8f, "Sejarah"));
        }
    }
}