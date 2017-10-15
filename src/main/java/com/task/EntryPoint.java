package com.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@EnableScheduling
public class EntryPoint {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(EntryPoint.class, args);
	}

}
