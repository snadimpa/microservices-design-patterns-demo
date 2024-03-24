package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductDbSyncServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductDbSyncServiceApplication.class, args);
	}

}
