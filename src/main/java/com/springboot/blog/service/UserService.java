package com.springboot.blog.service;

import com.springboot.blog.entity.User;

public interface UserService {
    public Boolean findUserByUsernameOrEmail(String usernameOrEmail);


}
