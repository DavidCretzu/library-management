package org.example;

import org.example.database.JDBConnectionWrapper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {

        JDBConnectionWrapper connectionWrapper = new JDBConnectionWrapper("_library");
        Connection connection = connectionWrapper.getConnection();

        if (connection != null) {
            System.out.println("Connection successful!");

            try {
                Statement statement = connection.createStatement();

                String sql = "SELECT * FROM book";
                ResultSet result = statement.executeQuery(sql);

                while (result.next()) {
                    int id = result.getInt("id");
                    String title = result.getString("title");
                    String author = result.getString("author");
                    int number = result.getInt("number");

                    System.out.println(id + " " + title + " " + author + " " + number);
                }

                result.close();
                statement.close();
                connection.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("Connection failed!");
        }
    }
}
