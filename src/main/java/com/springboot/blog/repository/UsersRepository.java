package com.springboot.blog.repository;

import com.springboot.blog.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users>  findUsersByUsername(String username);
    Optional<Users> findUsersByUsernameOrEmail(String username, String email);
    Optional<Users> findUsersByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
