package org.example.service;
import org.example.model.Book;
import java.util.List;

public interface BookService {
    public List<Book> read();
    public Book findById(long id);
    public Book updateBook(Book book , long id);
    public boolean deleteBook(Book book);
    public boolean createBook(Book book);
    public boolean sellBook(long id);
}
