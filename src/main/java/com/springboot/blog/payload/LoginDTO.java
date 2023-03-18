package com.springboot.blog.payload;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class LoginDTO {
    @NotNull
    private String usernameOrEmail;
    @NotNull
    private String password;


}
