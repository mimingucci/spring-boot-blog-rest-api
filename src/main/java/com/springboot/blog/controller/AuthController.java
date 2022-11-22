package com.springboot.blog.controller;

import com.springboot.blog.dto.JwtAuthResponse;
import com.springboot.blog.dto.LogInDto;
import com.springboot.blog.dto.SignUpDto;
import com.springboot.blog.entity.Role;
import com.springboot.blog.entity.Users;
import com.springboot.blog.repository.RoleRepository;
import com.springboot.blog.repository.UsersRepository;
import com.springboot.blog.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> handleSignIn(@RequestBody LogInDto logInDto){
       Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(logInDto.getUsernameOrEmail(), logInDto.getPassword()));
        SecurityContext sc=SecurityContextHolder.getContext();
        sc.setAuthentication(authentication);
        String token=tokenProvider.generateToken(authentication);
        UserDetails userDetails= (UserDetails) authentication.getPrincipal();
        System.out.println(userDetails.getPassword());
        JwtAuthResponse jwtAuthResponse=new JwtAuthResponse(token);

        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }
    @PostMapping("/signup")
    public ResponseEntity<?> handleSignUp(@RequestBody SignUpDto signUpDto){
        if(usersRepository.existsByUsername(signUpDto.getUsername())){
            return new ResponseEntity<>("Username exists", HttpStatus.BAD_REQUEST);
        }
        if(usersRepository.existsByEmail(signUpDto.getEmail())){
            return new ResponseEntity<>("Email exists", HttpStatus.BAD_REQUEST);
        }
        Users users=new Users();
        users.setName(signUpDto.getName());
        users.setEmail(signUpDto.getEmail());
        users.setUsername(signUpDto.getUsername());
        users.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        Role roles=roleRepository.findFirstRoleByName("ROLE_USER").get();
        users.setRoles(Collections.singleton(roles));
        usersRepository.save(users);
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }


}
