package org.example.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class JDBConnectionWrapper {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "root";
    private static final String PASSWORD = "Idfisrael,1";
    //    private static final String DATABASENAME = "_library";
    private Connection connection;

    public JDBConnectionWrapper(String schema){ /// schema = database name

        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(URL + schema , USER , PASSWORD );
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        return connection;
    }
}