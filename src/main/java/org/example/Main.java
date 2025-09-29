package org.example;

import javafx.application.Application;
import org.example.database.JDBConnectionWrapper;
import org.example.model.Book;
import org.example.repository.BookRepository;
import org.example.repository.BookRepositoryMySql;
import org.example.service.BookService;
import org.example.service.BookServiceImpl;
import org.example.view.BookView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        JDBConnectionWrapper wrapper  = new JDBConnectionWrapper("_library");
        Connection connection = wrapper.getConnection();

        BookRepository bookRepository = new BookRepositoryMySql(connection); ///dependency injection
        BookService bookService = new BookServiceImpl(bookRepository);

        BookView.setBookService(bookService);
        Application.launch(BookView.class, args);
    }
}
