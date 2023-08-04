package com.retailer.reward.service.helper;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;

import com.retailer.reward.CustomerRewardProgramApplicationTest;
import com.retailer.reward.entity.TransactionDetail;

@CustomerRewardProgramApplicationTest
class CustomerRewardServiceHelperTest {

  @InjectMocks
  CustomerRewardServiceHelper customerRewardServiceHelper;

  private final List<TransactionDetail> fistLastMonthTransactionList = new ArrayList<>();

  private TransactionDetail transactionDetail;

  @BeforeEach
  public void setup() {

    transactionDetail = TransactionDetail.builder()
      .customerId(111002L)
      .transactionId(11L)
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

  @DisplayName("Test for method getRewardPointsPerMonth method for valid scenario")
  @Test
  void getRewardPointsPerMonth() {

    final Long rewardPoints = customerRewardServiceHelper.getRewardPointsPerMonth(fistLastMonthTransactionList);

    assertThat(rewardPoints).isNotNull()
      .isEqualTo(270);
  }

  @DisplayName("Test for method calculateRewardPoints method for valid scenario")
  @Test
  void calculateRewardPoints() {

    transactionDetail.setTransactionAmount(new BigDecimal("120.00"));

    final BigDecimal rewardPoints = customerRewardServiceHelper.calculateRewardPoints(transactionDetail);

    assertThat(rewardPoints).isNotNull()
      .isEqualByComparingTo(BigDecimal.valueOf(90));

  }

  @DisplayName("Test for method calculateRewardPoints method for Zero transaction amount")
  @Test
  void calculateRewardPoints_ZeroAmout() {

    transactionDetail.setTransactionAmount(new BigDecimal("0"));

    final BigDecimal rewardPoints = customerRewardServiceHelper.calculateRewardPoints(transactionDetail);

    assertThat(rewardPoints).isNotNull()
      .isEqualByComparingTo(BigDecimal.valueOf(0));
  }

  @DisplayName("Test for method calculateRewardPoints method for Null transaction amount")
  @Test
  void calculateRewardPoints_NullAmout() {

    transactionDetail.setTransactionAmount(null);

    final BigDecimal rewardPoints = customerRewardServiceHelper.calculateRewardPoints(transactionDetail);

    assertThat(rewardPoints).isNotNull()
      .isEqualByComparingTo(BigDecimal.valueOf(0));
  }

  @DisplayName("Test for method calculateRewardPoints method for Negative transaction amount")
  @Test
  void calculateRewardPoints_NegativeAmout() {

    transactionDetail.setTransactionAmount(BigDecimal.valueOf(555)
      .negate());

    final BigDecimal rewardPoints = customerRewardServiceHelper.calculateRewardPoints(transactionDetail);

    assertThat(rewardPoints).isNotNull()
      .isEqualByComparingTo(BigDecimal.valueOf(0));
  }

  @DisplayName("ParameterizedTest for method calculateRewardPoints method for valid transaction amount")
  @ParameterizedTest
  @ValueSource(
    strings = { "145.00", "140.00" })
  void calculateRewardPoints_Paramater(final String arg) {

    transactionDetail.setTransactionAmount(new BigDecimal(arg));

    final BigDecimal rewardPoints = customerRewardServiceHelper.calculateRewardPoints(transactionDetail);

    assertThat(rewardPoints).isNotNull();
  }
}
