package com.retailer.reward.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerMonthlyRewardPoint {

  private Long firstLastMonthRewardPoints;

  private Long secondLastMonthRewardPoints;

  private Long thirdLastMonthRewardPoints;

  private Long totalRewardPoints;
}
