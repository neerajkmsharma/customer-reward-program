package com.retailer.reward.service;

import com.retailer.reward.model.CustomerRewardDetail;

public interface CustomerRewardService {

  CustomerRewardDetail getRewardPointsByCustomerId(Long customerId);
}
