package org.example;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.example.database.JDBConnectionWrapper;
import org.example.launcher.BookLauncherSingleton;
import org.example.launcher.UserLauncherSingleton;
import org.example.model.Book;
import org.example.repository.BookRepository;
import org.example.repository.BookRepositoryMySql;
import org.example.service.BookService;
import org.example.service.BookServiceImpl;
import org.example.view.BookView;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

public class Main extends Application {
    public static void main(String[] args) {
        // Database setup
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        UserLauncherSingleton.getInstance(stage);
    }
}
