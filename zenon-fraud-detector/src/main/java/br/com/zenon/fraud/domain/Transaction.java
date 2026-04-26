package br.com.zenon.fraud.domain;

import br.com.zenon.fraud.domain.validation.ValidationUtils;
import br.com.zenon.fraud.domain.vo.TransactionCustomer;

import java.math.BigDecimal;
import java.util.Objects;

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
    if(Objects.isNull(value)) {
      throw new IllegalArgumentException(propertyName + " should not be null");
    }
    return value.equals("1");
  }

  private static Integer validateStep(Integer step) {
    if (Objects.isNull(step) || step < 1) {
      throw new IllegalArgumentException("step should be positive: " + step);
    }
    return step;
  }

}
