package com.springboot.blog.service.impl;

import com.springboot.blog.entity.User;
import com.springboot.blog.repository.UserRepository;
import com.springboot.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repo;


    @Override
    public Boolean findUserByUsernameOrEmail(String usernameOrEmail) {
        try {
            User user=repo.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail).get();
            if(user.getUsername().equals(usernameOrEmail) || user.getEmail().equals(usernameOrEmail)){
               return true;
            }else{
                return false;
            }
        }catch (Exception e){
            return false;
        }

    }
}
