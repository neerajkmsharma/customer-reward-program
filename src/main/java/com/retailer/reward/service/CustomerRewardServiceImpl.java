package com.retailer.reward.service;

import static com.retailer.reward.util.CustomerRewardProgramConstant.INT_THREE;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ONE;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_TWO;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retailer.reward.exception.CustomerNotFoundException;
import com.retailer.reward.model.CustomerMonthlyRewardPoint;
import com.retailer.reward.model.CustomerMonthlyTransaction;
import com.retailer.reward.model.CustomerRewardDetail;
import com.retailer.reward.repository.TransactionDetailRepository;
import com.retailer.reward.service.helper.CustomerRewardServiceHelper;

import lombok.extern.slf4j.Slf4j;

/**
 * @implNote This class is implementation class for CustomerRewardService. It
 *           extends CustomerRewardServiceHelper to access the helper methods
 *
 */
@Service
@Slf4j
public class CustomerRewardServiceImpl extends CustomerRewardServiceHelper implements CustomerRewardService {

  @Autowired
  private TransactionDetailRepository transactionDetailRepository;

  @Autowired
  private CustomerDetailService customerDetailService;

  @Override
  public CustomerRewardDetail getRewardPointsByCustomerId(final Long customerId) {

    if (customerDetailService.getCustomerIdById(customerId) == null) {
      log.debug("Customer {} not found in DB", customerId);
      throw new CustomerNotFoundException("Customer not found");
    }

    log.info("Valid customer is found the Database");

    final var monthlyTransactions = getMonthlyTransactions(customerId);

    // Get the Monthly reward points
    final var firstLastMonthRewardPoints = getRewardPointsPerMonth(monthlyTransactions.getFistLastMonthTransactions());

    final var secondLastMonthRewardPoints =
      getRewardPointsPerMonth(monthlyTransactions.getSecondLastMonthTransactions());

    final var thirdLastMonthRewardPoints = getRewardPointsPerMonth(monthlyTransactions.getThirdLastMonthTransaction());

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

  /**
   * This method is to get transaction details monthly wise and return in one
   * Object
   *
   * @param customerId
   * @return CustomerMonthlyTransaction
   */
  private CustomerMonthlyTransaction getMonthlyTransactions(final Long customerId) {

    // Get the dates in Timestamp for each month
    final var fistLastMonthTimestamp = getDateBasedOnOffSetMonths(INTEGER_ONE);
    final var secondLastMonthTimestamp = getDateBasedOnOffSetMonths(INTEGER_TWO);
    final var thirdLastMonthTimestamp = getDateBasedOnOffSetMonths(INT_THREE);

    // Fetch the transaction detail from DB as per dates
    final var fistLastMonthTransactions = transactionDetailRepository.findAllByCustomerIdAndTransactionDateBetween(
      customerId, fistLastMonthTimestamp, Timestamp.valueOf(LocalDateTime.now()));
    final var secondLastMonthTransactions = transactionDetailRepository
      .findAllByCustomerIdAndTransactionDateBetween(customerId, secondLastMonthTimestamp, fistLastMonthTimestamp);
    final var thirdLastMonthTransaction = transactionDetailRepository
      .findAllByCustomerIdAndTransactionDateBetween(customerId, thirdLastMonthTimestamp, secondLastMonthTimestamp);

    log.info("Monthly transaction are received successfully from Database");

    return CustomerMonthlyTransaction.builder()
      .fistLastMonthTransactions(fistLastMonthTransactions)
      .secondLastMonthTransactions(secondLastMonthTransactions)
      .thirdLastMonthTransaction(thirdLastMonthTransaction)
      .build();

  }
}
