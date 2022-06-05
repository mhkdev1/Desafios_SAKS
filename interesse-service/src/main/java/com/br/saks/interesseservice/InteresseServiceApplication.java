package com.br.saks.interesseservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class InteresseServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InteresseServiceApplication.class, args);
	}

}
