package org.example.repository;

import org.example.model.User;

import java.util.List;

public interface UserRepository {
    public List<User> read();
    public boolean register(String userName , String password);
    public boolean login(String userName , String password);
//    public boolean saveUser(User user);
}
