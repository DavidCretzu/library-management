package org.example.repository;

import org.example.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryMySql implements UserRepository {

    private Connection connection;

    public UserRepositoryMySql(Connection connection){
        this.connection = connection;
    }

    @Override
    public List<User> read(){
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM user";
        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);) {
            while(resultSet.next()){
                User user = new User(resultSet.getInt("id") ,
                        resultSet.getString("username"),
                        resultSet.getString("password")
                );
                users.add(user);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return users;
    }
    @Override
    public boolean login(String userName , String password)
    {
        String sql = "SELECT * FROM user WHERE username = ? AND password = ? ";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1 , userName);
            statement.setString(2 , password);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return true;
            }
        }catch(SQLException e){
            return false;
        }
        return false;
    }

    @Override
    public boolean register(String userName , String password ){
        String sql = "INSERT INTO user (username , password) VALUES ( ? , ?) ";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1 , userName);
            statement.setString(2 , password);

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
