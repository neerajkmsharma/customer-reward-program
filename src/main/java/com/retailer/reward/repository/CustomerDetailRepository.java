package com.retailer.reward.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.retailer.reward.entity.CustomerDetail;

@Transactional
public interface CustomerDetailRepository extends CrudRepository<CustomerDetail, Long> {

  /**
   * This operation find Customer detail on the basis of customerId
   *
   * @param customerId
   * @return CustomerDetail
   */
  public CustomerDetail findByCustomerId(Long customerId);

}
