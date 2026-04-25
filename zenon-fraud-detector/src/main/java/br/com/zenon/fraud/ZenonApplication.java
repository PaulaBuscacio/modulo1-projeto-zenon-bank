package br.com.zenon.fraud;

import java.math.BigDecimal;

public class ZenonApplication {
  static void main() {
    Customer originCustomer1 = new Customer("C1231006815", new BigDecimal("170136.0"), new BigDecimal("160296.36"));
    Customer desCustomer1 = new Customer("M1979787155", new BigDecimal("0.0"), new BigDecimal("0.0"));
    Customer originCustomer2 = new Customer("C1280323807", new BigDecimal("850002.52"), new BigDecimal("0.0"));
    Customer destCustomer2 = new Customer("C873221189", new BigDecimal("6510099.11"), new BigDecimal("7360101.63"));

    Transaction transaction1 = Transaction.createTransaction(1, "PAYMENT", new BigDecimal("9839.64"), originCustomer1, desCustomer1 , "0", "0");
    Transaction transaction2 = Transaction.createTransaction(743, "CASH_OUT", new BigDecimal("850002.52"),originCustomer2, destCustomer2, "1", "0");

    System.out.println("Transacao 1:\n" +transaction1 + "\n\nTransacao 2:\n" + transaction2);


  }


}
