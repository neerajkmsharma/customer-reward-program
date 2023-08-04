package com.retailer.reward.model;

import java.util.List;

import com.retailer.reward.entity.TransactionDetail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerMonthlyTransaction {

  private List<TransactionDetail> fistLastMonthTransactions;

  private List<TransactionDetail> secondLastMonthTransactions;

  private List<TransactionDetail> thirdLastMonthTransaction;

}
