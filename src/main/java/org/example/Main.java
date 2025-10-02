package org.example;

import javafx.application.Platform;
import javafx.stage.Stage;
import org.example.database.JDBConnectionWrapper;
import org.example.repository.BookRepository;
import org.example.repository.BookRepositoryMySql;
import org.example.service.BookService;
import org.example.service.BookServiceImpl;
import org.example.view.BookView;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        // Database setup
        JDBConnectionWrapper wrapper = new JDBConnectionWrapper("_library");
        Connection connection = wrapper.getConnection();

        BookRepository bookRepository = new BookRepositoryMySql(connection);
        BookService bookService = new BookServiceImpl(bookRepository);

        // Start JavaFX
        Platform.startup(() -> {
            Stage stage = new Stage();
            new BookView(stage, bookService);
        });

    }
}
