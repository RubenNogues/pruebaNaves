package com.ships.prueba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableCaching
@ComponentScan(basePackages = {
    "com.ships.prueba.domain.service",
    "com.ships.prueba.infrastructure.controller.impl"
})
public class ShipsApplication {

  public static void main(String[] args) {
    SpringApplication.run(ShipsApplication.class, args);
  }
}