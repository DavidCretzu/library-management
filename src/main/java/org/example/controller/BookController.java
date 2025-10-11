package org.example.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.example.model.Book;
import org.example.service.BookService;
import org.example.view.BookView;
import org.example.mapper.BookBuilder;
import java.time.LocalDate;

public class BookController {
    private final BookService bookService;
    private final BookView bookView;

    public BookController(BookService bookService, BookView bookView) {
        this.bookService = bookService;
        this.bookView = bookView;
        this.bookView.addSaveButtonListener(new SaveButtonListener());
        this.bookView.addDeleteButtonListener(new DeleteButtonListener());
        this.bookView.addUpdateButtonListener(new UpdateButtonListener());

    }

    private class SaveButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {

///           Book book  = bookView.getTableView().getSelectionModel().getSelectedItem();   use for update and delete

            String title = bookView.getTitleField();
            String author = bookView.getAuthorField();
            LocalDate publishedDate = bookView.getDatePicker();
            int number = bookView.getNumberSpinner();

            Book book = new BookBuilder()
                    .setTitle(title)
                    .setAuthor(author)
                    .setPublishedDate(publishedDate)
                    .setNumber(number)
                    .build(); //Book Builder

            boolean savedBook = bookService.createBook(book);

            if (savedBook) {
                bookView.saveBookOl(book);
            }
        }
    }

    private class DeleteButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
            Book delBook = bookView.getTableView().getSelectionModel().getSelectedItem();

            bookService.deleteBook(delBook);
            bookView.deleteBookOl(delBook);
        }
    }

    private class UpdateButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
            Book selectedBook = bookView.getTableView().getSelectionModel().getSelectedItem();
            if (selectedBook == null) return; // nothing selected

            // build updated book from input fields
            Book updatedBook = new BookBuilder()
                    .setTitle(bookView.getTitleField())
                    .setAuthor(bookView.getAuthorField())
                    .setPublishedDate(bookView.getDatePicker())
                    .setNumber(bookView.getNumberSpinner())
                    .build();

            // update in database
            Book result = bookService.updateBook(updatedBook, selectedBook.getId());

            // update in ObservableList / TableView
            bookView.updateBookOl(selectedBook, result);
        }
    }


}



