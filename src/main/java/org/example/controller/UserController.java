package org.example.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.example.mapper.UserBuilder;
import org.example.model.User;
import org.example.service.UserService;
import org.example.view.UserView;

import javax.swing.*;

public class UserController {
    private final UserService userService;
    private final UserView userView;

    public UserController(UserService userService , UserView userView)
    {
        this.userService = userService;
        this.userView = userView;
        this.userView.addLoginButtonListener(new LoginButtonListener());
        this.userView.addRegisterButtonListener(new RegisterButtonListener());
    }

    private class LoginButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent){
            String userName = userView.getLoginUsernameField().getText();
            String password = userView.getLoginPasswordField().getText();

            if(userService.login(userName , password))
                userView.showAlert("COMPLETED" , "Login Successful"  , "You have logged in successful!" );
            else
                userView.showAlert("ERROR" , "Login not successful" , "Password or username is incorrect!");
        }
    }

    private class RegisterButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent){
            String userName = userView.getRegisterUsernameField().getText();
            String password = userView.getRegisterPasswordField().getText();
            String confirmPassword = userView.getRegisterConfirmPasswordField().getText();
            if(!confirmPassword.equals(password))
                userView.showAlert("ERROR" , "Register not Successful" , "Password must match!");

            if(userService.register(userName , password)) ///check uniqueness in service or repository
            {
                userView.showAlert("COMPLETED" , "Register Successful" , "You have registered successful!");
            }
            else
            {
                userView.showAlert("ERROR" , "Register not Succesfull" , "You did not Register Successfully!");
            }
        }
    }
}
