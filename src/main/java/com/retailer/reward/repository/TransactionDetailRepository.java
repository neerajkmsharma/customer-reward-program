package com.retailer.reward.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.retailer.reward.entity.TransactionDetail;

public interface TransactionDetailRepository extends CrudRepository<TransactionDetail, Long> {

  public List<TransactionDetail> findAllByCustomerId(Long customerId);

  public List<TransactionDetail> findAllByCustomerIdAndTransactionDateBetween(Long customerId, Timestamp fromTimestamp,
    Timestamp toTimestamp);
}
