package com.tarun.ses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class StockExchangeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockExchangeServiceApplication.class, args);
	}
}
