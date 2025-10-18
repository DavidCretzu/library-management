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
        this.bookView.addSellButtonListener(new SellButtonListener());
    }

    private class SaveButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
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
                bookView.showAlert("Success", "Book Saved", "The book was saved successfully!");
            }
            else {
                bookView.showAlert("Error", "Database Error", "Could not save the book. Please try again.");
            }
        }
    }

    private class DeleteButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
            Book delBook = bookView.getTableView().getSelectionModel().getSelectedItem();
            if (delBook == null) {
                bookView.showAlert("Warning", "No Selection", "Please select a book to delete.");
                return;
            }

            if(bookService.deleteBook(delBook) == true){
                bookView.deleteBookOl(delBook);
                bookView.showAlert("Success", "Book Deleted", "The selected book was deleted successfully!");
            }
            else{
                bookView.showAlert("Error", "Database Error", "Could not delete the selected book.");
            }

        }
    }

    private class UpdateButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
            Book selectedBook = bookView.getTableView().getSelectionModel().getSelectedItem();
            if (selectedBook == null) {
                bookView.showAlert("Warning", "No Selection", "Please select a book to update.");
                return;
            }
            if ( bookView.getAuthorField() == null || bookView.getTitleField() == null || bookView.getDatePicker() == null || bookView.getNumberSpinner() == 0) {
                bookView.showAlert("Warning", "Missing Data", "Please fill in all fields before updating.");
                return;
            }
            /// build updated book from input
            Book updatedBook = new BookBuilder()
                    .setTitle(bookView.getTitleField())
                    .setAuthor(bookView.getAuthorField())
                    .setPublishedDate(bookView.getDatePicker())
                    .setNumber(bookView.getNumberSpinner())
                    .build();


            /// update in database
            Book result = bookService.updateBook(updatedBook, selectedBook.getId());

            if (result != null) {
                bookView.updateBookOl(selectedBook, result);
                bookView.showAlert("Success", "Book Updated", "The book was updated successfully!");
            } else {
                bookView.showAlert("Error", "Database Error", "Could not update the selected book.");
            }
        }
    }

    private class SellButtonListener implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent actionEvent){
            Book selectedBook = bookView.getTableView().getSelectionModel().getSelectedItem();
            if (selectedBook == null) {
                bookView.showAlert("Warning", "No Selection", "Please select a book to sell.");
                return;
            }

            boolean sold = bookService.sellBook(selectedBook.getId());
            if (sold) {
                bookView.sellBookOl(selectedBook.getId());
                bookView.getTableView().refresh();
                bookView.showAlert("Success", "Book Sold", "One copy of the book has been sold.");
            } else {
                bookView.showAlert("Error", "Database Error", "Could not sell the selected book.");
            }
        }
    }


}



