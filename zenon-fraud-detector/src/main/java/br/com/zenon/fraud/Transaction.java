package br.com.zenon.fraud;

import java.math.BigDecimal;

public record Transaction(int step,
                          TransactionTypeEnum type,
                          BigDecimal amount,
                          Customer originCustomer,
                          Customer destCustomer,
                          Boolean isFraud,
                          Boolean isFlaggedFraud) {

  public static Transaction createTransaction(int step, String type, BigDecimal amount, Customer originCustomer, Customer destCustomer, String isFraud, String isFlaggedFraud) {
    return new Transaction(step, TransactionTypeEnum.valueOf(type), amount, originCustomer, destCustomer, convertToBoolean(isFraud), convertToBoolean(isFlaggedFraud));

  }

  @Override
  public String toString() {
    return
        "\tstep: " + step +
            "\n\ttype: " + type +
            "\n\tamount: " + amount +
            "\n\tnameOrig: " + originCustomer.name() +
            "\n\toldbalanceOrg: " + originCustomer.oldbalance() +
            "\n\tnewbalance: " + originCustomer.newbalance() +
            "\n\tnameDest: " + destCustomer.name() +
            "\n\toldbalanceDest: " + destCustomer.oldbalance() +
            "\n\tnewbalanceDest: " + destCustomer.newbalance() +
            "\n\tisFraud: " + isFraud +
            "\n\tisFlaggedFraud: " + isFlaggedFraud;
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
