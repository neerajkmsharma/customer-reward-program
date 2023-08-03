package com.retailer.reward.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retailer.reward.entity.CustomerDetail;
import com.retailer.reward.repository.CustomerDetailRepository;

@Service
public class CustomerDetailServiceImpl implements CustomerDetailService {

  @Autowired
  private CustomerDetailRepository customerRepository;

  @Override
  public CustomerDetail getCustomerIdById(final Long customerId) {
    return customerRepository.findByCustomerId(customerId);
  }

}
