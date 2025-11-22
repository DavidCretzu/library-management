package org.example.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import org.example.launcher.BookLauncherSingleton;
import org.example.mapper.UserBuilder;
import org.example.model.User;
import org.example.service.UserService;
import org.example.view.UserView;

import javax.swing.*;

public class UserController {
    private final UserService userService;
    private final UserView userView;
    private final Stage stage;

    public UserController(UserService userService , UserView userView , Stage stage)
    {
        this.userService = userService;
        this.userView = userView;
        this.userView.addLoginButtonListener(new LoginButtonListener());
        this.userView.addRegisterButtonListener(new RegisterButtonListener());
        this.stage = stage;
    }

    private class LoginButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent){
            String userName = userView.getLoginUsernameField().getText();
            String password = userView.getLoginPasswordField().getText();

            if(userService.login(userName , password))/// if user < 3 char or pass dn have digit just like stage update a private method for user validation and for password validation which return boolean or message of what it does not have clean code less lines
                stageUpdate();
            else
                userView.showAlert("ERROR" , "Login not successful" , "Password or username is incorrect!");
        }
        private void stageUpdate(){
            stage.close();
            Stage bookStage = new Stage();
            BookLauncherSingleton.getInstance(bookStage);
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

            if(invalidPassword(password) == null && userService.register(userName , password)) ///check uniqueness and validate in service or repository
            {
                userView.showAlert("COMPLETED" , "Register Successful" , "You have registered successful!");
            }
            else
            {
                if(invalidPassword(password) == null)
                    userView.showAlert("ERROR" , "Register not Succesfull" , "Username already exists !");
                else
                    userView.showAlert("ERROR" , "Register not Succesfull" , "Username already exists !");
            }
        }

        private String invalidPassword(String password){
            if(password.length() < 5)
                return "Password must have at least 5 charachters !";
            if(password.contains(" "))
                return "Password must not contain empty spaces !";
            if(!password.matches(".*\\d.*") )
                return "Password must have at least one digit !";
            if(!password.matches(".*[A-Z].*"))
                return "Password must have at least one capital letter !";
            if(!password.matches(".*[-+_!@#$%^&*., ?]*."))
                return "Password must have at least one special charachter !";
            return null;
        }
    }
}
