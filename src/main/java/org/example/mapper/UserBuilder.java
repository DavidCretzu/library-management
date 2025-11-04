package org.example.mapper;
import org.mindrot.jbcrypt.BCrypt;

import org.example.model.User;

public class UserBuilder {
    private User user;

    public UserBuilder()
    {
        this.user = new User();
    }
    public UserBuilder setId(int id){
        user.setId(id);
        return this;
    }

    public UserBuilder setUserName(String userName){
        user.setUserName(userName);
        return this;
    }

    public UserBuilder setPassword(String password){
        user.setPassword(password);
        return this;
    }

    public User build(){
        return this.user;
    }


}
