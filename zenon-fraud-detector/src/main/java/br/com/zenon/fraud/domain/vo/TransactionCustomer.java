package br.com.zenon.fraud.domain.vo;

import br.com.zenon.fraud.domain.validation.ValidationUtils;

import java.math.BigDecimal;

public record TransactionCustomer(String name,
                                  BigDecimal oldBalance,
                                  BigDecimal newBalance) {

  public TransactionCustomer(String name, String oldBalance, String newBalance) {
    this(ValidationUtils.validateNullOrEmptyString(name, "name"), ValidationUtils.validateValue(oldBalance, "oldBalance"), ValidationUtils.validateValue(newBalance, "newBalance"));
  }
}
