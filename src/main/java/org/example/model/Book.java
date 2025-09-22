package org.example.model;

import java.time.LocalDate;

public class Book {
    private long id;
    private String title;
    private String author;
    private LocalDate publishedDate;
    private int number ;

    public Book(long id, String title, String author, LocalDate publishDate, int number) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publishedDate = publishDate;
        this.number = number;
    }

    /// getters
    public String getTitle() {
        return title;
    }
    public long getId() {
        return id;
    }
    public String getAuthor() {
        return author;
    }
    public int getNumber() {
        return number;
    }
    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    /// setters
    public void setId(long id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setPublishDate(LocalDate publishDate) {
        this.publishedDate = publishDate;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setNumber(int number) {
        this.number = number;
    }

    /// extra tostring method for tests
    public String toString(){
        return "Book{ " + getTitle() + " by " + getAuthor() + " , published in " + getPublishedDate() + " , copies " + getNumber() +" }";
    }
}
