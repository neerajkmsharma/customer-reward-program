package com.retailer.reward;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(
  info = @Info(
    title = "Customer Reward Program API",
    version = "1.0",
    description = "Customer Reward Program API"))
public class CustomerRewardProgramApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerRewardProgramApplication.class, args);
	}

}
