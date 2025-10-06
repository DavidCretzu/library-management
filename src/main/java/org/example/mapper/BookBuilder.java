package org.example.mapper;

import org.example.model.Book;

import java.time.LocalDate;

public class BookBuilder {

    private Book book;

    public BookBuilder()
    {
        this.book = new Book();
    }
    public BookBuilder setId(int id)
    {
        book.setId(id);
        return this;
    }
    public BookBuilder setTitle(String title)
    {
        book.setTitle(title);
        return this;
    }
    public BookBuilder setAuthor(String author) {
        book.setAuthor(author);
        return this;
    }

    public BookBuilder setPublishedDate(LocalDate date) {
        book.setPublishDate(date);
        return this;
    }

    public BookBuilder setNumber(int number) {
        book.setNumber(number);
        return this;
    }

    public Book build()
    {
        return this.book;
    }
}
