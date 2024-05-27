package com.bufferzero.bank.services;

import com.bufferzero.bank.entities.User;
import com.bufferzero.bank.repository.UserRepoitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepoitory userRepoitory;

    public User saveUser(User user){
        User savedUser = userRepoitory.save(user);
        return savedUser;
    }
}
