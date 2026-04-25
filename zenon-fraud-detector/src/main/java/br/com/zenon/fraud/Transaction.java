package br.com.zenon.fraud;

import java.math.BigDecimal;

public record Transaction(int step,
                                    TransactionTypeEnum type,
                                    BigDecimal amount,
                                    String nameOrig,
                                    BigDecimal oldbalanceOrg,
                                    BigDecimal newbalanceOrig,
                                    String nameDest,
                                    BigDecimal oldbalanceDest,
                                    BigDecimal newbalanceDest,
                                    Boolean isFraud,
                                    Boolean isFlaggedFraud) {

  public static Transaction createTransaction(int step, String type, BigDecimal amount, String nameOrig, BigDecimal oldbalanceOrg, BigDecimal newbalanceOrig, String nameDest, BigDecimal oldbalanceDest, BigDecimal newbalanceDest, String isFraud, String isFlaggedFraud) {
   return new Transaction(step, TransactionTypeEnum.valueOf(type), amount, nameOrig, oldbalanceOrg, newbalanceOrig, nameDest, oldbalanceDest, newbalanceDest, convertToBoolean(isFraud), convertToBoolean(isFlaggedFraud));

  }

  @Override
  public String toString() {
    return
        "\tstep: " + step +
        "\n\ttype: " + type +
        "\n\tamount: " + amount +
        "\n\tnameOrig: " + nameOrig +
        "\n\toldbalanceOrg: " + oldbalanceOrg +
        "\n\tnewbalanceOrig: " + newbalanceOrig +
        "\n\tnameDest: " + nameDest  +
        "\n\toldbalanceDest: " + oldbalanceDest +
        "\n\tnewbalanceDest: " + newbalanceDest +
        "\n\tisFraud: " + isFraud +
        "\n\tisFlaggedFraud: " + isFlaggedFraud;
  }

  private static Boolean convertToBoolean(String value) {
    if (value == null) return null;
    value = value.trim();
    if (value.equals("1")){
      return Boolean.TRUE;
    } else {
      return Boolean.FALSE;
    }

  }
}
