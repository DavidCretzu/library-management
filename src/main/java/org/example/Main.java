package org.example;

import org.example.database.JDBConnectionWrapper;
import org.example.model.Book;
import org.example.repository.BookRepository;
import org.example.repository.BookRepositoryMySql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        JDBConnectionWrapper wrapper  = new JDBConnectionWrapper("_library");

        java.sql.Connection connection = wrapper.getConnection();

        /// Crete new book,
        BookRepository bookRepository = new BookRepositoryMySql(connection);
        Book book = new Book(1, "Book 1", "Author 1", LocalDate.now(), 1);
        bookRepository.createBook(book);
        ///  out all books
        bookRepository.read().forEach(System.out::println);

    }
}
