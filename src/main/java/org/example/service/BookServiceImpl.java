package org.example.service;

import org.example.model.Book;
import org.example.repository.BookRepository;

import java.util.List;

public class BookServiceImpl  implements Service{

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> read() {
       return bookRepository.read();
    }

    @Override
    public Book findById(long id) {
       if ( bookRepository.findById(id) == null){
        throw new RuntimeException("Book with id :" + id + " not found");
       }

       return bookRepository.findById(id);
    }

    @Override
    public Book updateBook(Book book, long id) {
        if ( bookRepository.findById(id) == null){
            throw new RuntimeException("Book with id :" + id + " not found");
        }
        if (bookRepository.updateBook(book , id) == null){
            throw new RuntimeException("Cannot update book with id: " + id);
        }

        return bookRepository.updateBook(book, id);
    }

    @Override
    public void deleteBook(long id) {
        if (bookRepository.findById(id) == null) {
            throw new RuntimeException("Book with id " + id + " not found.");
        }
        bookRepository.deleteBook(id);
    }

    @Override
    public void createBook(Book book) {
        bookRepository.createBook(book);
    }
}
