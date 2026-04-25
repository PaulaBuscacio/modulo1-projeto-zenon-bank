package br.com.zenon.fraud;

import br.com.zenon.fraud.domain.Transaction;
import br.com.zenon.fraud.useCase.TransactionIngestor;

import java.util.List;

public class ZenonApplication {
  static void main() {

    TransactionIngestor transactionIngestor = new TransactionIngestor();
    List<Transaction> transactions = transactionIngestor.extractTransactionFromFile("data/PS_20174392719_1491204439457_log.csv");

    transactions.stream()
        .limit(10)
        .forEach(System.out::println);

//    Transaction t = new Transaction(1, "PAYMENT", null, null, null, "0", "0");
//    System.out.println(t);
  }


}
