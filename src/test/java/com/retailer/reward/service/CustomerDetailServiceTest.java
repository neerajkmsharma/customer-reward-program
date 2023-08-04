package com.retailer.reward.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.retailer.reward.CustomerRewardProgramApplicationTest;
import com.retailer.reward.entity.CustomerDetail;
import com.retailer.reward.repository.CustomerDetailRepository;

@CustomerRewardProgramApplicationTest
class CustomerDetailServiceTest {

  @InjectMocks
  private CustomerDetailServiceImpl customerDetailService;

  @Mock
  private CustomerDetailRepository customerRepository;

  private CustomerDetail customerDetail;

  @BeforeEach
  public void setup() {
    customerDetail = CustomerDetail.builder()
      .customerId(1L)
      .customerName("Jack Smith")
      .build();

  }

  @DisplayName("Test for getCustomerIdById method")
  @Test
  void getCustomerIdById() {
    given(customerRepository.findByCustomerId(1L)).willReturn(customerDetail);

    final var customerDetailMocked = customerDetailService.getCustomerIdById(1L);

    assertThat(customerDetailMocked).isNotNull();

    assertThat(customerDetailMocked.getCustomerId()).isEqualTo(1L);
    assertThat(customerDetailMocked.getCustomerName()).isEqualTo("Jack Smith");
  }

}
