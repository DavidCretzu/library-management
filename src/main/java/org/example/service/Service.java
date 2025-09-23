package org.example.service;
import org.example.model.Book;
import java.util.List;

public interface Service {
    public List<Book> read();
    public Book findById(long id);
    public Book updateBook(Book book , long id);
    public void deleteBook(long id);
    public void createBook(Book book);
}
