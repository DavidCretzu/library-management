package org.example.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.example.model.Book;
import org.example.service.BookService;

import java.time.LocalDate;
import java.util.List;

public class BookView {

    private final TextField idField;
    private final TextField titleField;
    private final TextField authorField;
    private final DatePicker datePicker;
    private final Spinner<Integer> numberSpinner;
    private final TableView<Book> tableView;

    public BookView(Stage primaryStage, BookService bookService) {
        primaryStage.setTitle("Book Viewer");

        tableView = new TableView<>();
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

        idField = new TextField("Auto-generated");
        idField.setEditable(false);
        titleField = new TextField();
        authorField = new TextField();
        datePicker = new DatePicker();
        numberSpinner = new Spinner<>(1, 100000, 1);

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

    public TextField getIdField() { return idField; }
    public TextField getTitleField() { return titleField; }
    public TextField getAuthorField() { return authorField; }
    public DatePicker getDatePicker() { return datePicker; }
    public Spinner<Integer> getNumberSpinner() { return numberSpinner; }
    public TableView<Book> getTableView() { return tableView; }
}
