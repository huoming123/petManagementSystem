package com.design.petStoreManagement;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.design.petStoreManagement.mapper")
@ComponentScan(value="com.design")
public class PetStoreManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetStoreManagementApplication.class, args);
	}

}
