package com.mall.mercadillo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class MercadilloApplication {

	public static void main(String[] args) {
		SpringApplication.run(MercadilloApplication.class, args);
	}

}
