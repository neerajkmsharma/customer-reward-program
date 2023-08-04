package com.retailer.reward.service;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.retailer.reward.CustomerRewardProgramApplicationTest;
import com.retailer.reward.entity.CustomerDetail;
import com.retailer.reward.repository.TransactionDetailRepository;

@CustomerRewardProgramApplicationTest
public class CustomerRewardServiceTest {

  @InjectMocks
  private CustomerRewardServiceImpl customerRewardService;

  @Mock
  private TransactionDetailRepository transactionDetailRepository;

  private CustomerDetail customerDetail;

  @BeforeEach
  public void setup() {
    customerDetail = CustomerDetail.builder()
      .customerId(1L)
      .customerName("Jack Smith")
      .build();

  }
}
