package com.retailer.reward.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.retailer.reward.CustomerRewardProgramApplicationTest;
import com.retailer.reward.model.CustomerRewardDetail;
import com.retailer.reward.model.ExceptionResponse;

@CustomerRewardProgramApplicationTest
public class CustomerRewardControllerTest {

  @Autowired
  private WebTestClient httpClient;

  @Nested
  public class GetRewardPointsByCustomerId {

    @Test
    void getRewardPointsByCustomerId() {

      final Long customerId = 111002L;

      getRewardPointsByCustomerId(customerId).exchange()
        .expectStatus()
        .isOk()
        .expectHeader()
        .contentType(APPLICATION_JSON)
        .expectBody()
        .jsonPath("$.customerId")
        .isEqualTo(customerId)
        .jsonPath("$.customerMonthlyRewardPoint")
        .isNotEmpty();
    }

    @Test
    void getRewardPointsByCustomerId_ValidateResponse() {

      final Long customerId = 111003L;

      final var customerRewardDetail = getRewardPointsByCustomerId(customerId).exchange()
        .expectStatus()
        .isOk()
        .expectBody(CustomerRewardDetail.class)
        .returnResult()
        .getResponseBody();

      assertEquals(111003L, customerRewardDetail.getCustomerId());

      assertEquals(60L, customerRewardDetail.getCustomerMonthlyRewardPoint()
        .getFirstLastMonthRewardPoints());
      assertEquals(680L, customerRewardDetail.getCustomerMonthlyRewardPoint()
        .getSecondLastMonthRewardPoints());
      assertEquals(1470L, customerRewardDetail.getCustomerMonthlyRewardPoint()
        .getThirdLastMonthRewardPoints());
      assertEquals(3000L, customerRewardDetail.getCustomerMonthlyRewardPoint()
        .getTotalRewardPoints());

    }

    @Test
    void getRewardPointsByCustomerId_NoFound() {

      final Long customerId = 1L;

      final var responseBody = getRewardPointsByCustomerId(customerId).exchange()
        .expectStatus()
        .isNotFound()
        .expectBody(ExceptionResponse.class)
        .returnResult()
        .getResponseBody();

      assertEquals(responseBody.getMessage(), ("Customer not found"));

    }

    @Test
    void getRewardPointsByCustomerId_BadRequest() {

      final Long customerId = 0L;

      final var responseBody = getRewardPointsByCustomerId(customerId).exchange()
        .expectStatus()
        .isBadRequest()
        .expectBody(ExceptionResponse.class)
        .returnResult()
        .getResponseBody();

      assertEquals(responseBody.getMessage(), ("Customer is not valid"));

    }

    @Test
    void getRewardPointsByCustomerId_CustomerIdNull() {

      final Long customerId = null;

      final var responseBody = getRewardPointsByCustomerId(customerId).exchange()
        .expectStatus()
        .isBadRequest()
        .expectBody(String.class)
        .returnResult()
        .getResponseBody();

      assertTrue(responseBody.contains("Failed to convert 'customerId' with value: 'null'"));

    }

    @Test
    void getRewardPointsByCustomerId_WrongURL() {

      final Long customerId = 0L;

      final var responseBody = getRewardPointsByCustomerId_WrongURL(customerId).exchange()
        .expectStatus()
        .isNotFound()
        .expectBody(String.class)
        .returnResult()
        .getResponseBody();

      assertTrue(responseBody.contains("Not Found"));

    }

    private WebTestClient.RequestHeadersSpec<?> getRewardPointsByCustomerId(final Long customerId) {
      return httpClient.get()
        .uri("/customer-reward-program/api/v1/" + customerId + "/reward-points");
    }

    private WebTestClient.RequestHeadersSpec<?> getRewardPointsByCustomerId_WrongURL(final Long customerId) {
      return httpClient.get()
        .uri("/customer-reward-program/api/v1/" + customerId + "/reward-pointsXXXXX");
    }
  }
}
