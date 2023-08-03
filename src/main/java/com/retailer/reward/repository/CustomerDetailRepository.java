package com.retailer.reward.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.retailer.reward.entity.CustomerDetail;


@Repository
@Transactional
public interface CustomerDetailRepository extends CrudRepository<CustomerDetail,Long> {
  public CustomerDetail findByCustomerId(Long customerId);
  
}
