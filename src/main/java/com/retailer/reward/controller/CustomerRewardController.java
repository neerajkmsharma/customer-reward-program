package com.retailer.reward.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retailer.reward.exception.CustomerRewardProgramException;
import com.retailer.reward.model.CustomerRewardDetail;
import com.retailer.reward.service.CustomerRewardService;

import jakarta.validation.ValidationException;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/customer-reward-program/api/v1")
@Slf4j
public class CustomerRewardController {

  @Autowired
  private CustomerRewardService rewardsService;

  @GetMapping(
    value = "/{customerId}/reward-points",
    produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CustomerRewardDetail> getRewardPointsByCustomerId(@PathVariable(
    required = false) @NotNull final Long customerId) throws CustomerRewardProgramException {

    if (customerId == null || customerId == 0L) {
      log.debug("Invalid Customer id {}", customerId);
      throw new ValidationException("Customer is not valid");
    }

    final CustomerRewardDetail customerRewardDetail = rewardsService.getRewardPointsByCustomerId(customerId);

    return new ResponseEntity<>(customerRewardDetail, HttpStatus.OK);
  }

}
