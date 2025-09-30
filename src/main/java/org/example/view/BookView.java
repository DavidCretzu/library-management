package org.example.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.model.Book;
import org.example.service.BookService;

import java.time.LocalDate;
import java.util.List;

public class BookView extends Application {

    private static BookService bookService; ///todo delete

    private TextField idField = new TextField("Auto-Generated Book ID");
    private  TextField titleField = new TextField();
    private TextField authorField = new TextField();
    private DatePicker datePicker =  new DatePicker();
    private Spinner<Integer> numberSpinner = new Spinner<>(1 , 100000 , 1);

    public static void setBookService(BookService service) {
        bookService = service;
    }
    @Override/// todo not use this method , but do a constructor(gets stage )  bookview not extend application .
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Book Viewer");

        TableView<Book> tableView = new TableView<>();

        TableColumn<Book, Long> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Book, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Book, String> authorCol = new TableColumn<>("Author");
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));

        TableColumn<Book, LocalDate> dateCol = new TableColumn<>("Published Date");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("publishedDate"));

        TableColumn<Book, Integer> numberCol = new TableColumn<>("Number");
        numberCol.setCellValueFactory(new PropertyValueFactory<>("number"));

        tableView.getColumns().addAll(idCol, titleCol, authorCol, dateCol, numberCol);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        GridPane form = new GridPane();
        form.setPadding(new Insets(10));
        form.setHgap(10);
        form.setVgap(10);

        TextField idField = new TextField("Auto-generated");
        idField.setEditable(false);
    /// text fields should be defined as attributes of class view , instantiated still here but not declared here

        form.add(new Label("ID:"), 0, 0);
        form.add(idField, 1, 0, 2, 1);

        form.add(new Label("Title:"), 0, 1);
        form.add(titleField, 1, 1);

        form.add(new Label("Author:"), 0, 2);
        form.add(authorField, 1, 2);

        form.add(new Label("Published Date:"), 0, 3);
        form.add(datePicker, 1, 3);

        form.add(new Label("Number:"), 0, 4);
        form.add(numberSpinner, 1, 4);

        Button saveButton = new Button("Save");
        Button updateButton = new Button("Update");
        Button deleteButton = new Button("Delete");

        HBox buttonBox = new HBox(10, saveButton, updateButton, deleteButton);
        buttonBox.setAlignment(Pos.CENTER);
        form.add(buttonBox, 0, 5, 2, 1);

        // Add books from database
        List<Book> books;
        if (bookService != null) {
            books = bookService.read();
        } else {
            books = List.of(
                    new Book("The Hobbit", "J.R.R. Tolkien", LocalDate.of(1937, 9, 21), 5),
                    new Book("1984", "George Orwell", LocalDate.of(1949, 6, 8), 10),
                    new Book("Clean Code", "Robert C. Martin", LocalDate.of(2008, 8, 1), 3)
            );
        }

        tableView.getItems().addAll(books);

        BorderPane root = new BorderPane();
        root.setCenter(tableView);
        root.setBottom(form);

        Scene scene = new Scene(root, 700, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
