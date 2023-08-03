package com.retailer.reward.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRewardDetail {

  private Long customerId;

  private CustomerMonthlyRewardPoint customerMonthlyRewardPoint;

}
