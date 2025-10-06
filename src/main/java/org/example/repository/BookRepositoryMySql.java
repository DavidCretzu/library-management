package org.example.repository;

import org.example.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepositoryMySql implements BookRepository {
    private Connection connection;

    public BookRepositoryMySql(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Book> read() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM book";
        try(Statement statement  = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);) {
            while(result.next()){
                Book book = new Book(
                        result.getInt("id"),
                        result.getString("title"),
                        result.getString("author"),
                        result.getDate("published_date").toLocalDate(),
                        result.getInt("number")
                );
                books.add(book);
            }
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        return books;
    }


    @Override
    public Book findById(long id) {
        String sql = "SELECT * FROM book WHERE id = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setLong(1 , id);
            ResultSet result  = statement.executeQuery();
            if(result.next()){
                return new Book(
                        result.getLong("id"),
                        result.getString("title"),
                        result.getString("author"),
                        result.getDate("published_date").toLocalDate(),
                        result.getInt("number")
                );
            }
        }catch (SQLException e){
            return null;
        }
        return null;
    }

    @Override
    public Book updateBook(Book book, long id) {
        String sql = "UPDATE Book SET title = ?, author = ?, published_date = ?, number = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setDate(3, Date.valueOf(book.getPublishedDate()));
            stmt.setInt(4, book.getNumber());
            stmt.setLong(5, id);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                book.setId((int) id);
                return book;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // update failed
    }

    @Override
    public void deleteBook(Book book) {
        String sql = "DELETE FROM Book WHERE title = ? AND author = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean createBook(Book book) {
        String sql = "INSERT INTO Book (title, author, published_date, number) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setDate(3, Date.valueOf(book.getPublishedDate()));
            stmt.setInt(4, book.getNumber());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
