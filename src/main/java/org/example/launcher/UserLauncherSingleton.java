package org.example.launcher;

import javafx.stage.Stage;
import org.example.controller.BookController;
import org.example.controller.UserController;
import org.example.database.JDBConnectionWrapper;
import org.example.model.Book;
import org.example.model.User;
import org.example.repository.BookRepositoryMySql;
import org.example.repository.UserRepository;
import org.example.repository.UserRepositoryMySql;
import org.example.service.BookServiceImpl;
import org.example.service.UserService;
import org.example.service.UserServiceImpl;
import org.example.view.BookView;
import org.example.view.UserView;

import java.sql.Connection;
import java.util.List;

public class UserLauncherSingleton {
    private final UserRepository userRepository;
    private final UserService userService;
    private final UserController userController;
    private final UserView userView;
    public static UserLauncherSingleton instance;

    public static UserLauncherSingleton getInstance(Stage primarystage){
        if(instance == null)
        {
            synchronized(UserLauncherSingleton.class)
            {
                instance = new UserLauncherSingleton(primarystage);
            }
        }
        return instance;
    }

    private UserLauncherSingleton(Stage stage){
        JDBConnectionWrapper connectionWrapper = new JDBConnectionWrapper("_library");
        Connection connection = connectionWrapper.getConnection();

        UserRepository userRepositoryMySql = new UserRepositoryMySql(connection); /// an implementation
        this.userRepository = userRepositoryMySql; ///an instantiation

        UserServiceImpl userServiceImpl = new UserServiceImpl(userRepository); /// dependency injection only now
        this.userService = userServiceImpl;

        List<User> users = userService.read();

        UserView view = new UserView(stage , users);
        this.userView = view;

        UserController controller = new UserController(userService , userView);///cb
        this.userController = controller;
    }

}
