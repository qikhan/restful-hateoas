package com.qk.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.qk.controller,com.qk.service")
public class Application {

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}
}