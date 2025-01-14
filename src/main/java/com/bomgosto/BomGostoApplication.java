package com.bomgosto;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BomGostoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BomGostoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {}

}
