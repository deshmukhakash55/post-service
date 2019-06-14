package com.xperphile.postservice.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = {"com.xperphile.postservice.controller", "com.xperphile.postservice.dto", "com.xperphile.postservice.service", "com.xperphile.postservice.listener", "com.xperphile.postservice.configuration"})
@EntityScan(basePackages = {"com.xperphile.postservice.dao"} )
@EnableJpaRepositories(basePackages = {"com.xperphile.postservice.repository"})
@EnableAutoConfiguration
@SpringBootApplication
public class PostServiceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(PostServiceApplication.class, args);
	}

}
