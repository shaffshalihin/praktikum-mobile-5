package com.example.notebox;

public class Note {
    private long   id;
    private String title;
    private String body;
    private String category;
    private String color;
    private String date;
    private String dateRaw;

    public Note(long id, String title, String body,
                String category, String color,
                String date, String dateRaw) {
        this.id       = id;
        this.title    = title;
        this.body     = body;
        this.category = category;
        this.color    = color;
        this.date     = date;
        this.dateRaw  = dateRaw;
    }

    public long   getId()       { return id; }
    public String getTitle()    { return title; }
    public String getBody()     { return body; }
    public String getCategory() { return category; }
    public String getColor()    { return color; }
    public String getDate()     { return date; }
    public String getDateRaw()  { return dateRaw; }
}
