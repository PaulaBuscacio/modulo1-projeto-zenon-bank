package br.com.zenon.fraud.domain;

import java.math.BigDecimal;
import java.util.Objects;

public record Transaction(Integer step,
                          TransactionTypeEnum type,
                          BigDecimal amount,
                          TransactionCustomer origin,
                          TransactionCustomer recipient,
                          Boolean isFraud,
                          Boolean isFlaggedFraud) {


  public  Transaction (Integer step, String type, BigDecimal amount, TransactionCustomer origin, TransactionCustomer recipient, String isFraud, String isFlaggedFraud) {
    this(step, TransactionTypeEnum.valueOf(type), amount, origin, recipient, convertToBoolean(isFraud), convertToBoolean(isFlaggedFraud));
  }

  private static Boolean convertToBoolean(String value) {
    if (value == null) return null;
    value = value.trim();
    if (value.equals("1")) {
      return Boolean.TRUE;
    } else {
      return Boolean.FALSE;
    }
  }

}
