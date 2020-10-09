package com.boot.crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.boot.crud.service.TimeoutThreadService;

/**
 * @author Anirudha Pawar
 */

@SpringBootApplication
public class BootCrudDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootCrudDemoApplication.class, args);
	}

}
