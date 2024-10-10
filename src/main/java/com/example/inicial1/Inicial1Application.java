package com.example.inicial1;

import com.example.inicial1.repositories.MutantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Inicial1Application {
	private static final Logger logger = LoggerFactory.getLogger(Inicial1Application.class);

	@Autowired
	private MutantRepository mutantRepository;

	public static void main(String[] args) {
		SpringApplication.run(Inicial1Application.class, args);
		System.out.println("funcionando");
	}
}