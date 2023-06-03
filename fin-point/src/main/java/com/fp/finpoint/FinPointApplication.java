package com.fp.finpoint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
public class FinPointApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinPointApplication.class, args);
	}

}
