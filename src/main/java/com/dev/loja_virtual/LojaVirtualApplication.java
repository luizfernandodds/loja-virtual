package com.dev.loja_virtual;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javassist.expr.NewArray;

@SpringBootApplication
@EntityScan(basePackages = "com.dev.loja_virtual.model")
@ComponentScan(basePackages = {"com.*"})
@EnableJpaRepositories(basePackages = {"com.dev.loja_virtual.repository"})
@EnableTransactionManagement
public class LojaVirtualApplication {

	public static void main(String[] args) {
		
		System.out.println("Senha Encript: " + new BCryptPasswordEncoder().encode("1234"));
		
		
		SpringApplication.run(LojaVirtualApplication.class, args);
		
		
	}

}
