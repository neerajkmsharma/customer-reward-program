package com.retailer.reward.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;

import com.retailer.reward.CustomerRewardProgramApplicationTest;
import com.retailer.reward.entity.TransactionDetail;

@CustomerRewardProgramApplicationTest
public class TransactionDetailRepositoryTest {

  @Mock
  private TransactionDetailRepository transactionDetailRepository;

  private final List<TransactionDetail> transactionDetailList = new ArrayList<>();

  @BeforeEach
  public void setup() {
    transactionDetailList.add(TransactionDetail.builder()
      .customerId(1L)
      .transactionId(11L)
      .transactionAmount(new BigDecimal("145.00"))
      .transactionDate(Timestamp.valueOf("2023-07-15 10:10:10.0"))
      .build());
    transactionDetailList.add(TransactionDetail.builder()
      .customerId(1L)
      .transactionId(12L)
      .transactionAmount(new BigDecimal("140.00"))
      .transactionDate(Timestamp.valueOf("2023-07-10 10:10:10.0"))
      .build());
  }

  // @Test
  void findAllByCustomerIdAndTransactionDateBetween() {

    given(transactionDetailRepository.findAllByCustomerIdAndTransactionDateBetween(1L,
      Timestamp.valueOf("2023-06-23 10:10:10.0"), Timestamp.valueOf("2023-07-22 10:10:10.0")))
        .willReturn(transactionDetailList);

    final List<TransactionDetail> transactionDetailListMock =
      transactionDetailRepository.findAllByCustomerIdAndTransactionDateBetween(1L,
        Timestamp.valueOf("2023-06-23 10:10:10.0"), Timestamp.valueOf("2023-07-22 10:10:10.0"));

    assertThat(transactionDetailListMock).isNotNull();

    assertThat(transactionDetailListMock.size()).isEqualTo(2);
  }

  // @Test
  void findAllByCustomerIdAndTransactionDateBetween_NotTransactionFound() {

    given(transactionDetailRepository.findAllByCustomerIdAndTransactionDateBetween(1L,
      Timestamp.valueOf("2023-06-23 10:10:10.0"), Timestamp.valueOf("2023-07-22 10:10:10.0"))).willReturn(null);

    final List<TransactionDetail> transactionDetailListMock =
      transactionDetailRepository.findAllByCustomerIdAndTransactionDateBetween(1L,
        Timestamp.valueOf("2023-06-23 10:10:10.0"), Timestamp.valueOf("2023-07-22 10:10:10.0"));

    assertThat(transactionDetailListMock).isNull();

  }
}
