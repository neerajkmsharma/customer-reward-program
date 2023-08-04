package com.retailer.reward.service;

import static com.retailer.reward.util.CustomerRewardProgramConstant.DAYS_IN_MONTH;
import static com.retailer.reward.util.CustomerRewardProgramConstant.INT_THREE;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_TWO;

import java.sql.Timestamp;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retailer.reward.exception.CustomerNotFoundException;
import com.retailer.reward.model.CustomerMonthlyRewardPoint;
import com.retailer.reward.model.CustomerRewardDetail;
import com.retailer.reward.repository.TransactionDetailRepository;
import com.retailer.reward.service.helper.CustomerRewardServiceHelper;

@Service
public class CustomerRewardServiceImpl extends CustomerRewardServiceHelper implements CustomerRewardService {

  @Autowired
  private TransactionDetailRepository transactionDetailRepository;

  @Autowired
  private CustomerDetailService customerDetailService;

  @Override
  public CustomerRewardDetail getRewardPointsByCustomerId(final Long customerId) {

    if (customerDetailService.getCustomerIdById(customerId) == null) {
      throw new CustomerNotFoundException("Customer not found");
    }

    final var lastMonthTimestamp = getDateBasedOnOffSetDays(DAYS_IN_MONTH);
    final var lastSecondMonthTimestamp = getDateBasedOnOffSetDays(INTEGER_TWO * DAYS_IN_MONTH);
    final var lastThirdMonthTimestamp = getDateBasedOnOffSetDays(INT_THREE * DAYS_IN_MONTH);

    final var lastMonthTransactions = transactionDetailRepository
      .findAllByCustomerIdAndTransactionDateBetween(customerId, lastMonthTimestamp, Timestamp.from(Instant.now()));
    final var lastSecondMonthTransactions = transactionDetailRepository
      .findAllByCustomerIdAndTransactionDateBetween(customerId, lastSecondMonthTimestamp, lastMonthTimestamp);
    final var lastThirdMonthTransactions = transactionDetailRepository
      .findAllByCustomerIdAndTransactionDateBetween(customerId, lastThirdMonthTimestamp, lastSecondMonthTimestamp);

    final var firstLastMonthRewardPoints = getRewardPointsPerMonth(lastMonthTransactions);
    final var secondLastMonthRewardPoints = getRewardPointsPerMonth(lastSecondMonthTransactions);
    final var thirdLastMonthRewardPoints = getRewardPointsPerMonth(lastThirdMonthTransactions);

    return CustomerRewardDetail.builder()
      .customerId(customerId)
      .customerMonthlyRewardPoint(CustomerMonthlyRewardPoint.builder()
        .firstLastMonthRewardPoints(firstLastMonthRewardPoints)
        .secondLastMonthRewardPoints(secondLastMonthRewardPoints)
        .thirdLastMonthRewardPoints(thirdLastMonthRewardPoints)
        .totalRewardPoints(firstLastMonthRewardPoints + thirdLastMonthRewardPoints + thirdLastMonthRewardPoints)
        .build())
      .build();
  }

}
