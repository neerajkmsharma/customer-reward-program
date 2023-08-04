package com.retailer.reward.exception;

public class CustomerNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public CustomerNotFoundException(final String message) {
    super(message);
  }
}
