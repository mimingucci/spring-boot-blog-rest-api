package com.springboot.blog;

import com.springboot.blog.entity.Role;
import com.springboot.blog.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootBlogRestApiApplication{
    @Autowired
	public RoleRepository roleRepository;
	public static void main(String[] args) {
		SpringApplication.run(SpringBootBlogRestApiApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		Role admin=new Role();
//		admin.setName("ROLE_ADMIN");
//		roleRepository.save(admin);
//		Role user=new Role();
//		user.setName("ROLE_USER");
//		roleRepository.save(user);
//	}
}
