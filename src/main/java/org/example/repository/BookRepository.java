package org.example.repository;

import org.example.model.Book;

import java.util.List;

public interface BookRepository {
    //TO DO CRUD
    public List<Book> read();
    public Book findById(long id);
    public Book updateBook(Book book , long id);
    public boolean deleteBook(Book book);
    public boolean createBook(Book book);
    public boolean sellBook(long id);
}
