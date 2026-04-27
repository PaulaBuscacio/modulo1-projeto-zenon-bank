package br.com.zenon.fraud.domain;

import br.com.zenon.fraud.domain.validation.ValidationUtils;
import br.com.zenon.fraud.domain.vo.TransactionCustomer;

import java.math.BigDecimal;
import java.util.Optional;

public record Transaction(Integer step,
                          TransactionTypeEnum type,
                          BigDecimal amount,
                          TransactionCustomer origin,
                          TransactionCustomer recipient,
                          Boolean isFraud,
                          Boolean isFlaggedFraud) {


  public Transaction(Integer step, String type, String amount, TransactionCustomer origin, TransactionCustomer recipient, String isFraud, String isFlaggedFraud) {
    this(validateStep(step), TransactionTypeEnum.valueOf(type), ValidationUtils.validateValue(amount, "amount"), origin, recipient, convertToBoolean(isFraud, "isFraud"), convertToBoolean(isFlaggedFraud, "isFraudFlagged"));
  }

  private static Boolean convertToBoolean(String value, String propertyName) {

    Optional.ofNullable(value).orElseThrow(() -> new IllegalArgumentException(propertyName + " should not be null"));
    return value.equals("1");
  }

  private static Integer validateStep(Integer step) {

    Optional.ofNullable(step).filter(v -> v >= 1).orElseThrow(() -> new IllegalArgumentException("step should be positive: " + step));
    return step;
  }

}
