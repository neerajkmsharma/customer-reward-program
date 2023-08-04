package com.retailer.reward.service.helper;

import static com.retailer.reward.util.CustomerRewardProgramConstant.DECIMAL_TWO;
import static com.retailer.reward.util.CustomerRewardProgramConstant.FIRST_REWARD_LIMIT;
import static com.retailer.reward.util.CustomerRewardProgramConstant.SECOND_REWARD_LIMIT;
import static java.math.BigDecimal.ZERO;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.retailer.reward.entity.TransactionDetail;

public class CustomerRewardServiceHelper {

  protected Long getRewardPointsPerMonth(final List<TransactionDetail> transactions) {
    return transactions.stream()
      .map(this::calculateRewardPoints)
      .collect(Collectors.summingLong(BigDecimal::longValue));
  }

  protected BigDecimal calculateRewardPoints(final TransactionDetail transactionDetail) {

    final var transactionAmount = transactionDetail.getTransactionAmount();

    final var overSecondRewardLimitTransaction = transactionAmount.subtract(SECOND_REWARD_LIMIT);

    var rewardPoints = ZERO;

    if (overSecondRewardLimitTransaction.compareTo(ZERO) > INTEGER_ZERO) {
      /*
       * A customer receives 2 points for each dollar spent over $100 in every
       * transaction
       */
      rewardPoints = rewardPoints.add((overSecondRewardLimitTransaction.multiply(DECIMAL_TWO)));
    }

    if (transactionAmount.compareTo(FIRST_REWARD_LIMIT) > INTEGER_ZERO) {
      // Plus 1 reward point for each dollar spent over $50 in every transaction
      rewardPoints = rewardPoints.add(FIRST_REWARD_LIMIT);
    }

    return rewardPoints;

  }

  protected Timestamp getDateBasedOnOffSetMonths(final int months) {
    return Timestamp.valueOf(LocalDateTime.now()
      .minusMonths(months));
  }
}
