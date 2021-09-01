package com.domainproducts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DomainproductsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DomainproductsApplication.class, args);
		//This is spring boot application 
	}

}
