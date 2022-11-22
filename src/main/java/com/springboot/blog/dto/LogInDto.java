package com.springboot.blog.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Data
public class LogInDto {
    private String usernameOrEmail;
    private String password;
}
