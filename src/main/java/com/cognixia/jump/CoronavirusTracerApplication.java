package com.cognixia.jump;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CoronavirusTracerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoronavirusTracerApplication.class, args);
	}

}
