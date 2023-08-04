package com.retailer.reward;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@OpenAPIDefinition(
  info = @Info(
    title = "Customer Reward Program API",
    version = "1.0",
    description = "Customer Reward Program API"))
@Slf4j
public class CustomerRewardProgramApplication {

  public static void main(final String[] args) {
    log.info("Customer Reward Program is starting");
    SpringApplication.run(CustomerRewardProgramApplication.class, args);
  }

}
