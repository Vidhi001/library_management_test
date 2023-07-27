package com.example.minor_project1;

import com.example.minor_project1.models.Admin;
import com.example.minor_project1.models.SecuredUser;
import com.example.minor_project1.services.AdminServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MinorProject1Application implements CommandLineRunner {

	@Autowired
	AdminServices adminServices;
	public static void main(String[] args) {
		SpringApplication.run(MinorProject1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		Admin admin = Admin.builder()
//				.Name("Shyam")
//				.email("shyam@gmail.com")
//				.securedUser(SecuredUser.builder()
//						.username("Shyam001")
//						.password("shyam123")
//						.build())
//				.build();
//		adminServices.create(admin);
	}
}
