package org.example.service;

import org.example.model.User;

import java.util.List;

public interface UserService {
    public List<User> read();
    public boolean login(String userName , String password);
    public boolean register(String userName , String password);
}
