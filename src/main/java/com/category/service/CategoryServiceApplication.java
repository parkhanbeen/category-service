package com.category.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CategoryServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(CategoryServiceApplication.class, args);
  }

}
