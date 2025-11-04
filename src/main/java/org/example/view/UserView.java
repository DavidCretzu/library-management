package org.example.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.model.User;

import java.util.List;

public class UserView {

    // Login controls
    private final TextField loginUsernameField;
    private final PasswordField loginPasswordField;
    private final Button loginButton;

    // Register controls
    private final TextField registerUsernameField;
    private final PasswordField registerPasswordField;
    private final PasswordField registerConfirmPasswordField;
    private final Button registerButton;

    // Option to switch views (tabs)
    private final TabPane tabPane;


    public UserView(Stage primaryStage , List<User> users) {
        primaryStage.setTitle("Authentication");

        //  Login Tab
        GridPane loginGrid = new GridPane();
        loginGrid.setPadding(new Insets(16));
        loginGrid.setHgap(10);
        loginGrid.setVgap(10);

        loginUsernameField = new TextField();
        loginPasswordField = new PasswordField();
        loginButton = new Button("Login");

        loginGrid.add(new Label("Username:"), 0, 0);
        loginGrid.add(loginUsernameField, 1, 0);
        loginGrid.add(new Label("Password:"), 0, 1);
        loginGrid.add(loginPasswordField, 1, 1);

        HBox loginButtons = new HBox(10, loginButton);
        loginButtons.setAlignment(Pos.CENTER_RIGHT);
        loginGrid.add(loginButtons, 1, 2);

        Tab loginTab = new Tab("Login", loginGrid);
        loginTab.setClosable(false);

        //Register Tab
        GridPane registerGrid = new GridPane();
        registerGrid.setPadding(new Insets(16));
        registerGrid.setHgap(10);
        registerGrid.setVgap(10);

        registerUsernameField = new TextField();
        registerPasswordField = new PasswordField();
        registerConfirmPasswordField = new PasswordField();
        registerButton = new Button("Register");

        registerGrid.add(new Label("Username:"), 0, 0);
        registerGrid.add(registerUsernameField, 1, 0);
        registerGrid.add(new Label("Password:"), 0, 1);
        registerGrid.add(registerPasswordField, 1, 1);
        registerGrid.add(new Label("Confirm Password:"), 0, 2);
        registerGrid.add(registerConfirmPasswordField, 1, 2);

        HBox registerButtons = new HBox(10, registerButton);
        registerButtons.setAlignment(Pos.CENTER_RIGHT);
        registerGrid.add(registerButtons, 1, 3);

        Tab registerTab = new Tab("Register", registerGrid);
        registerTab.setClosable(false);

        // TabPane
        tabPane = new TabPane(loginTab, registerTab);

        //  info
        VBox root = new VBox(8, tabPane);
        root.setPadding(new Insets(12));

        Scene scene = new Scene(root, 360, 240);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Getters (so a controller can attach listeners later)
    public TextField getLoginUsernameField() { return loginUsernameField; }
    public PasswordField getLoginPasswordField() { return loginPasswordField; }
    public Button getLoginButton() { return loginButton; }

    public TextField getRegisterUsernameField() { return registerUsernameField; }
    public PasswordField getRegisterPasswordField() { return registerPasswordField; }
    public PasswordField getRegisterConfirmPasswordField() { return registerConfirmPasswordField; }
    public Button getRegisterButton() { return registerButton; }

    public TabPane getTabPane() { return tabPane; }

    // buttons action set
    public void addLoginButtonListener(EventHandler<ActionEvent> loginButtonListener){
        this.loginButton.setOnAction(loginButtonListener);
    }
    public void addRegisterButtonListener(EventHandler<ActionEvent> registerButtonListener) {
        this.registerButton.setOnAction(registerButtonListener);
    }
    public void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }


}
