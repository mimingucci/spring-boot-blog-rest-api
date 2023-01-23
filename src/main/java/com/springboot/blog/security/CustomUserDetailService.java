package com.springboot.blog.security;


import com.springboot.blog.entity.Role;
import com.springboot.blog.entity.Users;
import com.springboot.blog.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private static final long serialVersionUID = 1L;

    @Autowired
    private UsersRepository usersRepository;

//    @Autowired
//    public CustomUserDetailService(UsersRepository usersRepository) {
//        this.usersRepository = usersRepository;
//    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        Users users=usersRepository.findUsersByUsernameOrEmail(usernameOrEmail, usernameOrEmail).orElseThrow(()->new UsernameNotFoundException("User not found"));
        return new User(users.getUsername(), users.getPassword(), mapRoles(users.getRoles()));

    }
    public Collection<? extends GrantedAuthority> mapRoles(Set<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }


}
