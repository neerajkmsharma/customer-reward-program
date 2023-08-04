package com.retailer.reward.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.retailer.reward.CustomerRewardProgramApplicationTest;
import com.retailer.reward.entity.CustomerDetail;
import com.retailer.reward.entity.TransactionDetail;
import com.retailer.reward.exception.CustomerNotFoundException;
import com.retailer.reward.model.CustomerRewardDetail;
import com.retailer.reward.repository.TransactionDetailRepository;

@CustomerRewardProgramApplicationTest
@ExtendWith(SpringExtension.class)
class CustomerRewardServiceTest {

  @InjectMocks
  private CustomerRewardServiceImpl customerRewardService;

  @Mock
  CustomerDetailServiceImpl customerDetailService;

  @Mock
  private TransactionDetailRepository transactionDetailRepository;

  private final List<TransactionDetail> fistLastMonthTransactionList = new ArrayList<>();

  private CustomerDetail customerDetail;

  @BeforeEach
  public void setup() {

    customerDetail = CustomerDetail.builder()
      .customerId(111002L)
      .customerName("Adam Gilchrist")
      .build();

    fistLastMonthTransactionList.add(TransactionDetail.builder()
      .customerId(111002L)
      .transactionId(11L)
      .transactionAmount(new BigDecimal("145.00"))
      .transactionDate(Timestamp.valueOf("2023-07-15 10:10:10.0"))
      .build());
    fistLastMonthTransactionList.add(TransactionDetail.builder()
      .customerId(111002L)
      .transactionId(12L)
      .transactionAmount(new BigDecimal("140.00"))
      .transactionDate(Timestamp.valueOf("2023-07-10 10:10:10.0"))
      .build());

  }

  @DisplayName("Test for getCustomerIdById method for valid scenario")
  @Test
  void getRewardPointsByCustomerId() {

    final Long customerId = 111002L;

    when(transactionDetailRepository.findAllByCustomerIdAndTransactionDateBetween(ArgumentMatchers.any(),
      ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(fistLastMonthTransactionList);

    given(customerDetailService.getCustomerIdById(customerId)).willReturn(customerDetail);

    final CustomerRewardDetail customerRewardDetail = customerRewardService.getRewardPointsByCustomerId(customerId);

    assertThat(customerRewardDetail.getCustomerId()).isEqualTo(111002L);
    assertNotNull(customerRewardDetail.getCustomerMonthlyRewardPoint());
    assertThat(customerRewardDetail.getCustomerMonthlyRewardPoint()
      .getFirstLastMonthRewardPoints()).isEqualTo(270L);
    assertThat(customerRewardDetail.getCustomerMonthlyRewardPoint()
      .getSecondLastMonthRewardPoints()).isEqualTo(270L);
    assertThat(customerRewardDetail.getCustomerMonthlyRewardPoint()
      .getThirdLastMonthRewardPoints()).isEqualTo(270L);
  }

  @DisplayName("Test for getCustomerIdById method where Invalid CustomerId")
  @Test
  void getRewardPointsByCustomerId_InvalidCustomerId() {

    final Long customerId = 0L;

    when(transactionDetailRepository.findAllByCustomerIdAndTransactionDateBetween(ArgumentMatchers.any(),
      ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(fistLastMonthTransactionList);

    final CustomerNotFoundException customerNotFoundException =
      assertThrows(CustomerNotFoundException.class, () -> customerRewardService.getRewardPointsByCustomerId(customerId),
        "Expected getRewardPointsByCustomerId(customerId) to throw, but it didn't");

    assertTrue(customerNotFoundException.getMessage()
      .contains("Customer not found"));

  }

}
