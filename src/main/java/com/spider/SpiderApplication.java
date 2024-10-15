package com.spider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main class of the application
 */
@SpringBootApplication
public class SpiderApplication {
	/**
	 * Starts the Spring application
	 */
	public static void main (String[] args) {
		System.out.println("Spider Web Application Started");
		SpringApplication.run (SpiderApplication.class, args);
	}
}
