package com.source.lunina;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.security.autoconfigure.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class LuninaApplication {

	public static void main(String[] args) {
		SpringApplication.run(LuninaApplication.class, args);
	}

}
