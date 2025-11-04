package org.example.launcher;

import javafx.stage.Stage;
import org.example.controller.BookController;
import org.example.database.JDBConnectionWrapper;
import org.example.model.Book;
import org.example.repository.BookRepository;
import org.example.repository.BookRepositoryMySql;
import org.example.service.BookService;
import org.example.service.BookServiceImpl;
import org.example.view.BookView;

import java.sql.Connection;
import java.util.List;

public class BookLauncherSingleton {
    private final BookRepository bookRepository;
    private final BookService bookService;
    private final BookController bookController;
    private final BookView bookView;
    private static BookLauncherSingleton instance; ///singleton instance

    public static BookLauncherSingleton getInstance(Stage primarystage){
        if(instance==null)
        {
            synchronized (BookLauncherSingleton.class)
            {
                instance = new BookLauncherSingleton(primarystage);
            }
        }
        return instance;
    }

    private BookLauncherSingleton(Stage stage){
        JDBConnectionWrapper connectionWrapper = new JDBConnectionWrapper("_library");
        Connection connection = connectionWrapper.getConnection();

        BookRepositoryMySql bookRepositoryMySql = new BookRepositoryMySql(connection); /// an implementation
        this.bookRepository = bookRepositoryMySql; ///an instantiation

        BookServiceImpl bookServiceImpl = new BookServiceImpl(bookRepository); /// dependency injection only now
        this.bookService = bookServiceImpl;

        List<Book> books = bookService.read();

        BookView view = new BookView(stage , books);
        this.bookView = view;

        BookController controller = new BookController(bookService , bookView);///cb
        this.bookController = controller;

    }

}
