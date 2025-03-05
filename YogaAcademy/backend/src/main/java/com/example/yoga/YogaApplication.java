package com.example.yoga;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
@OpenAPIDefinition
@SpringBootApplication
public class YogaApplication {

	public static void main(String[] args) {
		SpringApplication.run(YogaApplication.class, args);
	}

}
