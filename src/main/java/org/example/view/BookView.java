package org.example.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

    private TextField idField;
    private TextField titleField;
    private TextField authorField;
    private DatePicker datePicker;
    private final Spinner<Integer> numberSpinner;
    private final TableView<Book> tableView;
    private Button updateButton;
    private Button deleteButton;
    private Button saveButton;
    private final ObservableList<Book> books;

    public BookView(Stage primaryStage , List<Book> booksList) {/// no service no logic
        primaryStage.setTitle("Book Viewer");
        books = FXCollections.observableArrayList(booksList);

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
        tableView.setItems(books);

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

        updateButton = new Button("Update");
        deleteButton = new Button("Delete");
        saveButton =  new Button("Save");

        HBox buttonBox = new HBox(10, saveButton, updateButton, deleteButton);
        buttonBox.setAlignment(Pos.CENTER);
        form.add(buttonBox, 0, 5, 2, 1);

        BorderPane root = new BorderPane();
        root.setCenter(tableView);
        root.setBottom(form);

        Scene scene = new Scene(root, 700, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void addSaveButtonListener(EventHandler<ActionEvent> saveButtonListener){
        saveButton.setOnAction(saveButtonListener);

    }

    public void addDeleteButtonListener(EventHandler<ActionEvent> deleteButtonListener){
        deleteButton.setOnAction(deleteButtonListener);
    }

    public void addUpdateButtonListener(EventHandler<ActionEvent> updateButtonListener){
        updateButton.setOnAction(updateButtonListener);
    }

    public void saveBookOl(Book book){
        this.books.add(book);
    }

    public void deleteBookOl(Book bookToDelete) {
        books.removeIf(b -> b.getTitle().equals(bookToDelete.getTitle()) &&
                        b.getAuthor().equals(bookToDelete.getAuthor()));
    }


    public void updateBookOl(Book oldBook, Book newBook) {
        // update ObservableList by matching ID
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == oldBook.getId()) {
                books.set(i, newBook);
                break;
            }
        }
    }



    public int getIdField() { return Integer.parseInt(idField.toString());} ///wrapper classes and primitive(int vs iNTEGER)
    public String getTitleField() { return titleField.getText(); }
    public String getAuthorField() { return authorField.getText(); }
    public LocalDate getDatePicker() { return datePicker.getValue(); }
    public int getNumberSpinner() { return numberSpinner.getValue(); }
    public TableView<Book> getTableView() { return tableView; }


}
