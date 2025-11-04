package org.example.service;

import org.example.repository.UserRepository;
import org.example.model.User;

import java.util.List;

public class UserServiceImpl implements UserService{
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){this.userRepository = userRepository;}

    @Override
    public List<User> read(){
        return userRepository.read();
    }

    @Override
    public boolean login(String userName , String password){
        if(userRepository.login(userName , password))
            return true;
        return false;
    }

    @Override
    public boolean register(String userName , String password){
       if(userRepository.register(userName , password)) ///  check uniqueness later on
           return true;
       return false;
    }
}
