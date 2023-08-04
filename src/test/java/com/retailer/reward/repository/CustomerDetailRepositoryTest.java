package com.retailer.reward.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.retailer.reward.CustomerRewardProgramApplicationTest;
import com.retailer.reward.entity.CustomerDetail;

@CustomerRewardProgramApplicationTest
class CustomerDetailRepositoryTest {

  @Mock
  private CustomerDetailRepository customerDetailRepository;

  private CustomerDetail customerDetail;

  @BeforeEach
  public void setup() {
    customerDetail = CustomerDetail.builder()
      .customerId(1L)
      .customerName("Jack Smith")
      .build();
  }

  @Test
  void findByCustomerId() {

    given(customerDetailRepository.findByCustomerId(1L)).willReturn(customerDetail);

    final CustomerDetail customerDetailMocked = customerDetailRepository.findByCustomerId(1L);
    assertThat(customerDetailMocked).isNotNull();

    assertThat(customerDetailMocked.getCustomerId()).isEqualTo(1L);
    assertThat(customerDetailMocked.getCustomerName()).isEqualTo("Jack Smith");
  }

  @Test
  void findByCustomerId_NotFound() {

    given(customerDetailRepository.findByCustomerId(2L)).willReturn(null);

    final CustomerDetail customerDetailMocked = customerDetailRepository.findByCustomerId(2L);
    assertThat(customerDetailMocked).isNull();

  }

}
