package org.example.service;

import org.example.model.Book;
import org.example.repository.BookRepository;
import org.example.repository.BookRepositoryMySql;

import java.util.List;

public class BookServiceImpl  implements BookService {

    private final BookRepository bookRepository; ///injected once not modified

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository; /// dependency injection in main
    }

    @Override
    public List<Book> read() {
       return bookRepository.read();
    }

    @Override
    public Book findById(long id) {
        Book book = bookRepository.findById(id);
       if ( book == null){
              throw new RuntimeException("Book with id :" + id + " not found");
       }
       return book;
    }

    @Override
    public Book updateBook(Book book, long id) {
        if (bookRepository.findById(id) == null) {
            throw new RuntimeException("Book with id: " + id + " not found.");
        }

        Book updatedBook = bookRepository.updateBook(book, id);
        if (updatedBook == null) {
            throw new RuntimeException("Cannot update book with id: " + id);
        }

        return updatedBook; // return updated book object
    }



    @Override
    public void deleteBook(Book book) {
        if (bookRepository.findById(book.getId()) == null) {
            throw new RuntimeException("Book  " + book.getTitle() + " not found.");
        }
        bookRepository.deleteBook(book);
    }

    @Override
    public boolean createBook(Book book) {

        return bookRepository.createBook(book);
    }
}
